package com.aishang.app.ui.ExchangeHouse;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.R;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.RegexUtils;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import javax.inject.Inject;

public class ExchangeHouseActivity extends BaseActivity implements ExchangeHouseMvpView {

  private static final String TAG = "ExchangeHouseActivity";
  private static final int REQUEST_CAMERA_CODE = 11;
  private static final int REQUEST_PREVIEW_CODE = 22;

  @Inject ExchangeHousePresenter presenter;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.expandable_text) TextView expandableText;
  @Bind(R.id.expand_collapse) ImageButton expandCollapse;
  @Bind(R.id.expand_text_view) ExpandableTextView expandTextView;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;
  @Bind(R.id.owner) MaterialEditText owner;
  @Bind(R.id.id_number) MaterialEditText idNumber;
  @Bind(R.id.house_address) MaterialEditText houseAddress;
  @Bind(R.id.houseNumber) MaterialEditText houseNumber;
  @Bind(R.id.room_type) MaterialEditText roomType;
  @Bind(R.id.area) MaterialEditText area;
  @Bind(R.id.start_time) MaterialEditText startTime;
  @Bind(R.id.end_time) MaterialEditText endTime;
  @Bind(R.id.contactsPhone) MaterialEditText contactsPhone;
  @Bind(R.id.Email) MaterialEditText Email;
  @Bind(R.id.contactsMobile) MaterialEditText contactsMobile;
  @Bind(R.id.update_img) ImageView updateImg;
  private ProgressDialog progressDialog;

  private String selectImgUrl;

  private boolean loadImgScuess = false;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    presenter.attachView(this);
    setContentView(R.layout.activity_exchange_house);
    this.getWindow()
        .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
            | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    ButterKnife.bind(this);

    startTime.setInputType(InputType.TYPE_DATETIME_VARIATION_NORMAL);
    endTime.setInputType(InputType.TYPE_DATETIME_VARIATION_NORMAL);

    initToolbar();
    expandTextView.setText(this.getString(R.string.holiday_house_rule));
  }

  @OnClick(R.id.start_time) void onClickStartTime() {
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(System.currentTimeMillis() + 86400000L);
    DatePickerDialog datePickerDialog =
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
          @Override
          public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            startTime.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
          }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
    datePickerDialog.show();
  }

  @OnClick(R.id.end_time) void onClickEndTime() {
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(System.currentTimeMillis() + 2 * 86400000L);
    DatePickerDialog datePickerDialog =
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
          @Override
          public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            endTime.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
          }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
    datePickerDialog.show();
  }

  private void initToolbar() {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_push) {
          postDate();
          return true;
        }
        return false;
      }
    });
    toolbar.setNavigationIcon(R.mipmap.iconfont_livesvg);

    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        ExchangeHouseActivity.this.onBackPressed();
      }
    });
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_push, menu);
    return true;
  }

  private void postDate() {

    CommonUtil.hideSoftInput(this);

    if (isOwnerEmpty()) {
      showOwnerError();
      return;
    }

    if (isIDNumberEmpty() || !RegexUtils.checkIdCard(idNumber.getText().toString().trim())) {
      showIDNumberError();
      return;
    }

    if (isAddressEmpty()) {
      showAddressError();
      return;
    }

    if (isAreaEmpty()) {
      showAreaError();
      return;
    }

    if (isHouseNumberuEmpty()) {
      showHouseNumberError();
      return;
    }

    if (isTypeEmpty()) {
      showHouseTypeError();
      return;
    }

    if (isEmailEmpty() || !RegexUtils.checkEmail(Email.getText().toString().trim())) {
      showEmailError();
      return;
    }

    if (isAddressEmpty()) {
      showAddressError();
      return;
    }

    if (isStartTimeEmpty()) {
      showStartTimeError();
      return;
    }

    if (isEndTimeEmpty()) {
      showEndTimeError();
      return;
    }

    if (isPhoneEmpty() || !RegexUtils.checkMobile(contactsPhone.getText().toString().trim())) {
      showPhoneError();
      return;
    }

    //if (!loadImgScuess) {
    //  showImgError();
    //  return;
    //}

    post();
  }

  private void post() {

    String selectImgData =
        ""; //= CommonUtil.bitmaptoString(BitmapFactory.decodeFile(selectImgUrl));

    //Log.i(TAG, "post: imgData size " + selectImgData.length());

    String json = AiShangUtil.generProjectChangeParam(owner.getText().toString().trim(),
        idNumber.getText().toString().trim(), houseAddress.getText().toString().trim(),
        houseNumber.getText().toString().trim(), roomType.getText().toString().trim(),
        Float.parseFloat(area.getText().toString().trim()), startTime.getText().toString().trim(),
        endTime.getText().toString().trim(), contactsPhone.getText().toString().trim(),
        contactsMobile.getText().toString().trim(), Email.getText().toString().trim(),
        selectImgData);

    progressDialog = DialogFactory.createProgressDialog(this, R.string.posting);
    progressDialog.show();
    presenter.postData(1, json);
  }

  private boolean isOwnerEmpty() {
    return TextUtils.isEmpty(owner.getText().toString().trim());
  }

  private boolean isIDNumberEmpty() {
    return TextUtils.isEmpty(idNumber.getText().toString().trim());
  }

  private boolean isHouseNumberuEmpty() {
    return TextUtils.isEmpty(houseNumber.getText().toString().trim());
  }

  private boolean isAddressEmpty() {
    return TextUtils.isEmpty(houseAddress.getText().toString().trim());
  }

  private boolean isTypeEmpty() {
    return TextUtils.isEmpty(roomType.getText().toString().trim());
  }

  private boolean isAreaEmpty() {
    return TextUtils.isEmpty(area.getText().toString().trim());
  }

  private boolean isEmailEmpty() {
    return TextUtils.isEmpty(Email.getText().toString().trim());
  }

  private boolean isStartTimeEmpty() {
    return TextUtils.isEmpty(startTime.getText().toString().trim());
  }

  private boolean isEndTimeEmpty() {
    return TextUtils.isEmpty(endTime.getText().toString().trim());
  }

  private boolean isPhoneEmpty() {
    return TextUtils.isEmpty(contactsPhone.getText())
        || TextUtils.getTrimmedLength(contactsPhone.getText()) <= 0;
  }

  private void showOwnerError() {
    showError("产权人不能为空！");
  }

  private void showImgError() {
    showError("房产证图片有误！");
  }

  private void showPhoneError() {
    showError(this.getString(R.string.error_phone));
  }

  private void showAddressError() {
    showError("房屋地址不能为空！");
  }

  private void showIDNumberError() {
    showError("身份证好有误！");
  }

  private void showHouseNumberError() {
    showError("房间号不能为空！");
  }

  private void showHouseTypeError() {
    showError("户型不能为空！");
  }

  private void showEmailError() {
    showError("输入邮箱有误！");
  }

  private void showAreaError() {
    showError("面积不能为空！");
  }

  private void showStartTimeError() {
    showError("开始时间不能为空！");
  }

  private void showEndTimeError() {
    showError("结束时间不能为空！");
  }

  @Override public void showError(String error) {
    dimissDialog();
    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override public void showSuccess() {
    dimissDialog();
    CommonUtil.showSnackbar("提交成功", layoutRoot);
  }

  private void dimissDialog() {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }
  }

  @OnClick(R.id.update_img) void onClickUpdateImg() {

    PhotoPickerIntent intent = new PhotoPickerIntent(ExchangeHouseActivity.this);
    intent.setSelectModel(SelectModel.SINGLE);
    intent.setShowCarema(true); // 是否显示拍照， 默认false
    // intent.setImageConfig(config);
    startActivityForResult(intent, REQUEST_CAMERA_CODE);
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK) {
      switch (requestCode) {
        // 选择照片
        case REQUEST_CAMERA_CODE:
          ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);

          if (list.size() > 0) {
            selectImgUrl = list.get(0);
            Picasso.with(this).load(new File(selectImgUrl)).into(updateImg, new Callback() {
              @Override public void onSuccess() {
                loadImgScuess = true;
              }

              @Override public void onError() {
                loadImgScuess = false;
              }
            });
          }
          break;
      }
    }
  }
}
