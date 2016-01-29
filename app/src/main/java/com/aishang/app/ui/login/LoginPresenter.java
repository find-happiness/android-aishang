package com.aishang.app.ui.login;

import android.app.Activity;
import android.content.Intent;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JMemberLoginParam;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.ui.ChangePassword.ChangePasswordActivity;
import com.aishang.app.ui.ForgetPossword.ForgetPosswordActivity;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.ui.main.MainActivity;
import com.aishang.app.ui.register.RegisterActivity;

import com.google.gson.Gson;
import javax.inject.Inject;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by song on 2016/1/20.
 */
public class LoginPresenter extends BasePresenter<LoginMvpView> {
  private static final String TAG = "LoginPresenter";

  private final DataManager mDataManager;
  private Subscription mSubscription;

  @Inject public LoginPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(LoginMvpView mvpView) {
    super.attachView(mvpView);
  }

  @Override public void detachView() {
    super.detachView();
    if (mSubscription != null) mSubscription.unsubscribe();
  }

  public void Login(int version, final String json) {
    checkViewAttached();

    //TODO 密码错误的时候 json解析出错
    mSubscription = mDataManager.syncLogin(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JMemberLoginResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().showError(e.toString());
          }

          @Override public void onNext(JMemberLoginResult jMemberLoginResult) {
            if (jMemberLoginResult.getResult().toUpperCase().equals("success".toUpperCase())) {
              getMvpView().loginScuess(jMemberLoginResult);

              JMemberLoginParam param = new Gson().fromJson(json, JMemberLoginParam.class);

              getMvpView().saveLoginData(param.getMemberAccount(), param.getPassword());
            } else {
              getMvpView().loginFaild(jMemberLoginResult.getResult());
            }
          }
        });
  }

  public void intentToMain() {
    Activity act = (LoginActivity) getMvpView();
    Intent intent = MainActivity.getStartIntent(act, true);
    act.startActivity(intent);
  }

  public void intentToForgetPsw() {
    Activity act = (LoginActivity) getMvpView();
    Intent intent = new Intent(act, ForgetPosswordActivity.class);
    act.startActivity(intent);
  }

  public void intentToRegister() {
    Activity act = (LoginActivity) getMvpView();
    Intent intent = new Intent(act, RegisterActivity.class);
    act.startActivity(intent);
  }

  public void intentToChangePsw() {
    Activity act = (LoginActivity) getMvpView();
    Intent intent = new Intent(act, ChangePasswordActivity.class);
    act.startActivity(intent);
  }
}
