package com.aishang.app.ui.login;

import android.os.Bundle;
import android.util.Log;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.R;
import com.aishang.app.data.model.JMemberLoginParam;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.google.gson.Gson;
import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements LoginMvpView{

  private static final String TAG = "LoginActivity";

  @Inject
  LoginPresenter presenter;

  @OnClick(R.id.btn_login)
  void onLoginClick() {
    login();
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
    presenter.attachView(this);
  }

  @Override public void login() {

    JMemberLoginParam param = new JMemberLoginParam();
    param.setMemberAccount("13883224451");
    param.setPassword(CommonUtil.getEncodeMD5("224451"));

    presenter.Login(2,new Gson().toJson(param));
  }

  @Override public void showError(String error) {
    Log.i(TAG, "showError: "+error);
    DialogFactory.createSimpleOkErrorDialog(this,"SORRY",error).show();
  }

  @Override public void loginScuess() {
    Log.i(TAG, "loginScuess: " + "loginScuess");
    presenter.intentToMain();
  }
}
