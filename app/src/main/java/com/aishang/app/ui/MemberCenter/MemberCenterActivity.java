package com.aishang.app.ui.MemberCenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.ChangePassword.ChangePasswordActivity;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;
import com.happiness.alterview.OnItemClickListener;
import com.squareup.picasso.Picasso;
import com.yalantis.ucrop.UCrop;
import de.hdodenhof.circleimageview.CircleImageView;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MemberCenterActivity extends BaseActivity {

  private static final String TAG = "MemberCenterActivity";
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.profile_image) CircleImageView profileImage;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;
  private Bitmap mPhoto;
  private String mCurrentPhotoPath;
  ;

  private Uri mDestinationUri;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_member_center);
    ButterKnife.bind(this);

    initToolbar();

    bindDataToView();
  }

  private void initToolbar() {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    toolbar.setNavigationIcon(R.mipmap.iconfont_livesvg);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onBackPressed();
      }
    });
  }

  private void bindDataToView() {
    JMemberLoginResult result = BoilerplateApplication.get(this).getMemberLoginResult();

    Picasso.with(this)
        .load(AiShangService.IMG_URL + result.getData().getImageUrl())
        .error(R.mipmap.ic_img_user_default)
        .placeholder(R.mipmap.ic_img_user_default)
        .into(profileImage);
  }

  @OnClick(R.id.rl_head_img) void onClickRlHeadImg() {
    DialogFactory.createIosSheetAlertDialog(this, new String[] { "拍照", "从相册中选择" },
        new OnItemClickListener() {
          @Override public void onItemClick(Object o, int position) {

            switch (position) {
              case 0:
                try {
                  startActivityForResult(dispatchTakePictureIntent(), 100);
                } catch (IOException e) {
                  Log.i(TAG, "onItemClick: " + e.toString());
                }
                break;
              case 1:
                PhotoPickerIntent intent = new PhotoPickerIntent(MemberCenterActivity.this);
                intent.setSelectModel(SelectModel.SINGLE);
                intent.setShowCarema(false); // 是否显示拍照， 默认false
                // intent.setImageConfig(config);
                startActivityForResult(intent, 101);
                break;
            }
          }
        }).show();
  }

  @OnClick(R.id.rl_change_pwd) void onClickChangePwd() {
    Intent intent = new Intent(this, ChangePasswordActivity.class);
    this.startActivity(intent);
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK) {
      switch (requestCode) {
        case 100:
          cutImage(Uri.fromFile(new File(mCurrentPhotoPath)));
          break;
        case 101:
          ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);

          if (list.size() > 0) {
            cutImage(Uri.fromFile(new File(list.get(0))));
          }
          break;
        case UCrop.REQUEST_CROP:
          final Uri resultUri = UCrop.getOutput(data);
          Picasso.with(this).load(resultUri).into(profileImage);
          break;
      }
    } else if (resultCode == UCrop.RESULT_ERROR) {

      CommonUtil.showSnackbar("裁切图片失败", layoutRoot);
    }
  }

  private void cutImage(Uri uri) {

    UCrop.Options options = new UCrop.Options();

    options.setMaxScaleMultiplier(5);
    options.setImageToCropBoundsAnimDuration(666);
    options.setDimmedLayerColor(Color.WHITE);
    options.setOvalDimmedLayer(true);
    options.setShowCropFrame(false);
    options.setCropGridStrokeWidth(5);
    options.setCropGridColor(Color.WHITE);
    options.setCropGridColumnCount(2);
    options.setCropGridRowCount(2);

    // Color palette
    options.setToolbarColor(ContextCompat.getColor(this, R.color.primary));
    options.setStatusBarColor(ContextCompat.getColor(this, R.color.primary));
    options.setActiveWidgetColor(ContextCompat.getColor(this, R.color.primary));
    options.setToolbarTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
    mDestinationUri = Uri.fromFile(new File(getCacheDir(),
        new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "crop_.jpg"));
    UCrop.of(uri, mDestinationUri)
        .withAspectRatio(1, 1)
        .withMaxResultSize(320, 320)
        .withOptions(options)
        .start(this);
  }

  public Intent dispatchTakePictureIntent() throws IOException {
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    // Ensure that there's a camera activity to handle the intent
    if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
      // Create the File where the photo should go
      File photoFile = createImageFile();
      // Continue only if the File was successfully created
      if (photoFile != null) {
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
      }
    }
    return takePictureIntent;
  }

  private File createImageFile() throws IOException {

    // Create an image file name
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String imageFileName = "JPEG_" + timeStamp + "_";
    File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
    if (!storageDir.exists()) {
      if (!storageDir.mkdir()) {
        throw new IOException();
      }
    }
    File image = new File(storageDir, imageFileName + ".jpg");
    // Save a file: path for use with ACTION_VIEW intents
    mCurrentPhotoPath = image.getAbsolutePath();
    return image;
  }
}
