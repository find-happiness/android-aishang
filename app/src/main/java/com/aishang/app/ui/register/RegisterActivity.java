package com.aishang.app.ui.register;

import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.OkHttpUtils;
import com.aishang.app.util.RegexUtils;
import com.rengwuxian.materialedittext.MaterialEditText;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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

  private Subscription subscriptionAgain;

  private static final int AGAIN_GET_CODE = 60;

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

    if (subscriptionAgain != null) subscriptionAgain.unsubscribe();

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

      btnGetVerificationCode.setClickable(false);

      subscriptionAgain = Observable.interval(1, TimeUnit.SECONDS)
          .observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .limit(AGAIN_GET_CODE)
          .subscribe(new Subscriber<Long>() {
            @Override public void onCompleted() {
              //Log.i(TAG, "onCompleted: ---------->");
              btnGetVerificationCode.setClickable(true);
              btnGetVerificationCode.setText("重新获取");
            }

            @Override public void onError(Throwable e) {
            }

            @Override public void onNext(Long aLong) {
              //Log.i(TAG, "onNext: ------->" + aLong);
              btnGetVerificationCode.setText("重新发送(" + (AGAIN_GET_CODE - aLong) + ")");
            }
          });
    }
  }

  @OnClick(R.id.btn_register) void onClickRegister() {

    final String strPhone = phone.getText().toString().trim();
    final String psw = password.getText().toString().trim();
    final String code = verifyCode.getText().toString().trim();
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

    Observable.from(OkHttpUtils.getInstance()
        .getCookieStore()
        .get(HttpUrl.parse(AiShangService.AiShangHost + "mobile/member/sendCode.ashx")))
        .map(new Func1<Cookie, String>() {
          @Override public String call(Cookie cookie) {
            if (cookie == null) {
              new IllegalArgumentException("验证码有误，请重新获取！");
            }
            return cookie.toString();
          }
        })
        .subscribe(new Subscriber<String>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            CommonUtil.showSnackbar(e.toString(), layoutRoot);
          }

          @Override public void onNext(String s) {
            mPresenter.submit(1, AiShangUtil.generMemberNoteRegisterParam(strPhone, code,
                CommonUtil.getEncodeMD5(psw).toUpperCase()), s);
          }
        });
  }

  @Override public void showError(String error) {
    CommonUtil.hideSoftInput(this);
    CommonUtil.showSnackbar(error, layoutRoot);
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
    //CommonUtil.showSnackbar("注册成功！", layoutRoot);

    DialogFactory.createGenericSuccessDialog(this, "注册成功！", new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialog, int which) {
        RegisterActivity.this.finish();
      }
    }).show();
  }

  @Override public void showNetDialog() {
    progressDialog = DialogFactory.createProgressDialog(this, R.string.posting);
    progressDialog.show();
  }

  @Override public void showGetCodeError(String error) {
    CommonUtil.hideSoftInput(this);
    CommonUtil.showSnackbar(error, layoutRoot);
    if (subscriptionAgain != null) {
      subscriptionAgain.unsubscribe();
    }

    btnGetVerificationCode.setClickable(true);
    btnGetVerificationCode.setText("获取验证码");
  }
}
