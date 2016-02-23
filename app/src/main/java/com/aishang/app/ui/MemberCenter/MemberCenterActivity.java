package com.aishang.app.ui.MemberCenter;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.data.model.JMemberProfileEditParam;
import com.aishang.app.data.model.JMemberProfileEditResult;
import com.aishang.app.data.model.JMemberProfileParam;
import com.aishang.app.data.model.JMemberProfileResult;
import com.aishang.app.data.model.JUploadFileParam;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.ChangePassword.ChangePasswordActivity;
import com.aishang.app.ui.bank.BankListActivity;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.ui.edit.EditActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.FileUploadMvpView;
import com.aishang.app.util.FileUploadPresenter;
import com.aishang.app.util.NetworkUtil;
import com.aishang.app.util.StorageUtils;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;
import com.google.gson.Gson;
import com.happiness.alterview.OnItemClickListener;
import com.squareup.picasso.Picasso;
import com.yalantis.ucrop.UCrop;
import de.hdodenhof.circleimageview.CircleImageView;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MemberCenterActivity extends BaseActivity
    implements MemberCenterMvpView, FileUploadMvpView {
  public static final int EMAIL = 103;
  public static final int NE_CHENG = 102;
  public static final int TAKE_PIC = 100;
  public static final int SELECT_PIC = 101;
  public static final int REAL_NAME = 104;

  @Inject MemberCenterPresenter presenter;
  @Inject FileUploadPresenter filePresenter;

  private static final String TAG = "MemberCenterActivity";
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.profile_image) CircleImageView profileImage;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;
  @Bind(R.id.rl_ne_cheng) RelativeLayout rlNeCheng;
  @Bind(R.id.rl_real_name) RelativeLayout rlRealName;
  @Bind(R.id.rl_birthday) RelativeLayout rlBirthday;
  @Bind(R.id.rl_gender) RelativeLayout rlGender;
  @Bind(R.id.rl_bank) RelativeLayout rlBank;
  @Bind(R.id.rl_email) RelativeLayout rlEmail;
  @Bind(R.id.phone) TextView phone;
  private Bitmap mPhoto;
  private String mCurrentPhotoPath;
  ;
  Uri resultUri;

  private Uri mDestinationUri;

  ProgressDialog progressDialog;

  private boolean isGetProfile = false;

  private JMemberProfileResult profile;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    presenter.attachView(this);
    filePresenter.attachView(this);
    setContentView(R.layout.activity_member_center);
    ButterKnife.bind(this);

    initToolbar();

    if (NetworkUtil.isNetworkConnected(this)) {
      showProgressDialog();
      getProfile();
    } else {
      showError(this.getString(R.string.no_net));
    }
  }

  @Override protected void onDestroy() {
    presenter.detachView();
    filePresenter.detachView();
    super.onDestroy();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_push, menu);
    return true;
  }

  private void initToolbar() {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_push) {
          if (NetworkUtil.isNetworkConnected(MemberCenterActivity.this)) {
            showProgressDialog();
            post();
          } else {
            showError(MemberCenterActivity.this.getString(R.string.no_net));
          }
          return true;
        }
        return false;
      }
    });
    toolbar.setNavigationIcon(R.mipmap.iconfont_livesvg);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onBackPressed();
      }
    });
  }

  private void getProfile() {
    JMemberLoginResult result = BoilerplateApplication.get(this).getMemberLoginResult();
    String json =
        AiShangUtil.generMemberProfileParam(BoilerplateApplication.get(this).getMemberAccount(),
            result.getData().getCookies());

    presenter.loadProfile(2, json);
  }

  private void bindDataToView(JMemberProfileResult profile) {

    JMemberProfileResult.Data data = profile.getData();

    JMemberLoginResult result = BoilerplateApplication.get(this).getMemberLoginResult();

    Picasso.with(this)
        .load(AiShangService.IMG_URL + data.getImageUrl())
        .error(R.mipmap.ic_img_user_default)
        .placeholder(R.mipmap.ic_img_user_default)
        .into(profileImage);

    phone.setText(data.getMobilePhone() + "");

    rlEmail.setTag(data.getEmail());

    rlGender.setTag(data.getGender());

    rlRealName.setTag(data.getMemberName());
  }

  @OnClick(R.id.rl_head_img) void onClickRlHeadImg() {
    if (!isGetProfile) {
      CommonUtil.showSnackbar("没有请求到数据！", layoutRoot);
      return;
    }

    DialogFactory.createIosSheetAlertDialog(this, null, new String[] { "拍照", "从相册中选择" },
        new OnItemClickListener() {
          @Override public void onItemClick(Object o, int position) {

            switch (position) {
              case 0:
                try {
                  startActivityForResult(dispatchTakePictureIntent(), TAKE_PIC);
                } catch (IOException e) {
                  Log.i(TAG, "onItemClick: " + e.toString());
                }
                break;
              case 1:
                PhotoPickerIntent intent = new PhotoPickerIntent(MemberCenterActivity.this);
                intent.setSelectModel(SelectModel.SINGLE);
                intent.setShowCarema(false); // 是否显示拍照， 默认false
                // intent.setImageConfig(config);
                startActivityForResult(intent, SELECT_PIC);
                break;
            }
          }
        }).show();
  }

  @OnClick(R.id.rl_change_pwd) void onClickChangePwd() {
    if (!isGetProfile) {
      CommonUtil.showSnackbar("没有请求到数据！", layoutRoot);
      return;
    }
    Intent intent = new Intent(this, ChangePasswordActivity.class);
    this.startActivity(intent);
  }

  @OnClick(R.id.rl_ne_cheng) void onclickNeCheng() {
    if (!isGetProfile) {
      CommonUtil.showSnackbar("没有请求到数据！", layoutRoot);
      return;
    }
    Intent intent = EditActivity.getStartIntent(this, "呢称", "abc", NE_CHENG);

    this.startActivityForResult(intent, NE_CHENG);
  }

  @OnClick(R.id.rl_real_name) void onclickRealName() {
    if (!isGetProfile) {
      CommonUtil.showSnackbar("没有请求到数据！", layoutRoot);
      return;
    }
    Intent intent =
        EditActivity.getStartIntent(this, "真实姓名", (String) rlRealName.getTag(), REAL_NAME);

    this.startActivityForResult(intent, REAL_NAME);
  }

  @OnClick(R.id.rl_birthday) void onclickBirthday() {
    if (!isGetProfile) {
      CommonUtil.showSnackbar("没有请求到数据！", layoutRoot);
      return;
    }

    Calendar cal = null;

    Object obj = rlBirthday.getTag();

    if (obj != null) {
      cal = (Calendar) obj;
    } else {
      cal = Calendar.getInstance();
    }

    DialogFactory.createDatePickerDialog(this, cal, new DatePickerDialog.OnDateSetListener() {
      @Override public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, monthOfYear, dayOfMonth);
        rlBirthday.setTag(cal);
      }
    }).show();
  }

  @OnClick(R.id.rl_gender) void onclickGender() {
    if (!isGetProfile) {
      CommonUtil.showSnackbar("没有请求到数据！", layoutRoot);
      return;
    }

    DialogFactory.createIosSheetAlertDialog(this, null, new String[] { "保密", "女", "男" },
        new OnItemClickListener() {
          @Override public void onItemClick(Object o, int position) {
            rlGender.setTag((position - 1) + "");
          }
        }).show();
  }

  @OnClick(R.id.rl_bank) void onclickBank() {
    Intent intent = new Intent(this, BankListActivity.class);
    this.startActivity(intent);
  }

  @OnClick(R.id.rl_email) void onclickEmail() {
    if (!isGetProfile) {
      CommonUtil.showSnackbar("没有请求到数据！", layoutRoot);
      return;
    }
    Intent intent = EditActivity.getStartIntent(this, "邮箱", (String) rlEmail.getTag(), EMAIL);
    this.startActivityForResult(intent, EMAIL);
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK) {
      switch (requestCode) {
        case TAKE_PIC:
          cutImage(Uri.fromFile(new File(mCurrentPhotoPath)));
          break;
        case SELECT_PIC:
          ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);

          if (list.size() > 0) {
            cutImage(Uri.fromFile(new File(list.get(0))));
          }
          break;
        case UCrop.REQUEST_CROP:
          resultUri = UCrop.getOutput(data);
          Picasso.with(this).load(resultUri).into(profileImage);
          break;
        case NE_CHENG:
          rlNeCheng.setTag(data.getStringExtra(EditActivity.CONTENT));
          Log.i(TAG, "onActivityResult: " + data.getStringExtra(EditActivity.CONTENT));
          break;
        case EMAIL:
          rlEmail.setTag(data.getStringExtra(EditActivity.CONTENT));
          Log.i(TAG, "onActivityResult: " + data.getStringExtra(EditActivity.CONTENT));
          break;
        case REAL_NAME:
          rlRealName.setTag(data.getStringExtra(EditActivity.CONTENT));
          Log.i(TAG, "onActivityResult: "
              + data.getStringExtra(EditActivity.CONTENT)
              + "    "
              + (String) rlRealName.getTag()
              + "   abc");
          break;
      }
    } else if (resultCode == UCrop.RESULT_ERROR) {
      CommonUtil.showSnackbar("裁切图片失败", layoutRoot);
    }
  }

  private void showProgressDialog() {
    progressDialog = DialogFactory.createProgressDialog(this, R.string.listview_loading);
    progressDialog.show();
  }

  private void dimissDialog() {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
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
    mDestinationUri = Uri.fromFile(new File(StorageUtils.getCacheDirectory(this),
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

  @Override public void showError(String error) {
    dimissDialog();

    if (error.equals("userNotLoginOrTimeOut")) {
      CommonUtil.showSnackbar("用户未登录或会话过期！", layoutRoot);
    } else {
      CommonUtil.showSnackbar(error, layoutRoot);
    }
  }

  @Override public void showGetProfileSuccess(JMemberProfileResult profile) {
    dimissDialog();
    isGetProfile = true;
    this.profile = profile;
    bindDataToView(profile);
  }

  @Override public void postSuccess(JMemberProfileEditResult result) {

    String cookies = BoilerplateApplication.get(this).getMemberLoginResult().getData().getCookies();
    String member = BoilerplateApplication.get(this).getMemberAccount();

    JUploadFileParam param = new JUploadFileParam();
    param.setCredential(result.getCredential());
    param.setModel(result.getModel());
    param.setCookies(cookies);
    param.setMemberAccount(member);

    if (resultUri != null) {
      filePresenter.postData(2, new Gson().toJson(param), new File(resultUri.getPath()));
    } else {
      dimissDialog();
    }
  }

  private void post() {

    BoilerplateApplication app = BoilerplateApplication.get(this);

    final String json =
        AiShangUtil.generMemberProfileBasicEdit(app.getMemberLoginResult().getData().getCookies(),
            app.getMemberAccount(), Integer.parseInt((String) rlGender.getTag()),
            (String) rlEmail.getTag(), profile.getData().getCertifyType(),
            profile.getData().getCertifyID());

    presenter.postProfileEdit(2, json);

  }

  @Override public void showUploadFileError(String error) {
    dimissDialog();
    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override public void showUploadFileSuccess() {
    dimissDialog();
    DialogFactory.createSimpleOkErrorDialog(this, "scuess", "个人资料修改成功").show();
  }
}
