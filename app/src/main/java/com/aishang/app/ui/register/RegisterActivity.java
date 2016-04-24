package com.aishang.app.ui.register;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
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
import com.rengwuxian.materialedittext.MaterialEditText;
import javax.inject.Inject;

public class RegisterActivity extends BaseActivity implements RegisterMvpView {

  private static final String TAG = "RegisterActivity";
  @Inject RegisterPresenter mPresenter;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.btn_get_verification_code) TextView btnGetVerificationCode;
  @Bind(R.id.phone) MaterialEditText phone;
  @Bind(R.id.verify_code) MaterialEditText verifyCode;
  @Bind(R.id.password) MaterialEditText password;
  @Bind(R.id.btn_register) Button btnRegister;
  @Bind(R.id.layoutRoot) FrameLayout layoutRoot;

  private ProgressDialog progressDialog;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    mPresenter.attachView(this);
    setContentView(R.layout.activity_register);
    ButterKnife.bind(this);
    initView();
  }

  @Override protected void onDestroy() {
    mPresenter.detachView();
    super.onDestroy();
  }

  private void initView() {
    initToolbar();
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

  @OnClick(R.id.btn_get_verification_code) void onGetVerificationCodeClick() {

    String strPhone = phone.getText().toString().trim();

    if (TextUtils.isEmpty(strPhone) || !RegexUtils.checkMobile(strPhone)) {
      CommonUtil.hideSoftInput(this);
      CommonUtil.showSnackbar(R.string.error_phone, layoutRoot);
    } else {
      mPresenter.checkPhone(1, AiShangUtil.generSendCodeParam(strPhone, "1", false));
    }
  }

  @OnClick(R.id.btn_register) void onClickRegister() {

    String strPhone = phone.getText().toString().trim();
    String psw = password.getText().toString().trim();
    String code = verifyCode.getText().toString().trim();
    CommonUtil.hideSoftInput(this);
    if (TextUtils.isEmpty(strPhone) || !RegexUtils.checkMobile(strPhone)) {
      CommonUtil.showSnackbar(R.string.error_phone, layoutRoot);
      return;
    }

    if (TextUtils.isEmpty(code)) {
      CommonUtil.showSnackbar("验证码不能为空", layoutRoot);
      return;
    }

    if (TextUtils.isEmpty(psw)) {
      CommonUtil.showSnackbar("密码不能为空", layoutRoot);
      return;
    }

    mPresenter.submit(1, AiShangUtil.generMemberNoteRegisterParam(strPhone, code,
        CommonUtil.getEncodeMD5(psw).toUpperCase()));
  }

  @Override public void showError(String error) {
    CommonUtil.hideSoftInput(this);
    CommonUtil.showSnackbar(error, layoutRoot);
    Log.e(TAG, "showError: " + error);
  }

  @Override public void showCheckPhoneSuccess() {
    CommonUtil.hideSoftInput(this);
    CommonUtil.showSnackbar("短信已发送，请注意查收", layoutRoot);
  }

  @Override public void dismissDialog() {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }
  }

  @Override public void showRegisterSuccess() {
    CommonUtil.hideSoftInput(this);
    CommonUtil.showSnackbar("注册成功！", layoutRoot);
  }

  @Override public void showNetDialog() {
    progressDialog = DialogFactory.createProgressDialog(this, R.string.posting);
    progressDialog.show();
  }
}
