package com.aishang.app.ui.login;

import android.app.Activity;
import android.content.Intent;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.ui.ForgetPossword.ForgetPosswordActivity;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.ui.main.MainActivity;
import com.aishang.app.ui.register.RegisterActivity;

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

  public void Login(int version, String json) {
    checkViewAttached();
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
            if(jMemberLoginResult.getResult().toUpperCase().equals("success".toUpperCase()))
            {
              getMvpView().loginScuess();
            }
            else {
              getMvpView().showError(jMemberLoginResult.getResult());
            }
          }
        });
  }

  public void intentToMain()
  {
    Activity act = (LoginActivity) getMvpView();
    Intent intent = MainActivity.getStartIntent(act, true);
    act.startActivity(intent);
  }

  public void intentToForgetPsw()
  {
    Activity act = (LoginActivity) getMvpView();
    Intent intent = new Intent(act, ForgetPosswordActivity.class);
    act.startActivity(intent);
  }

  public void intentToRegister()
  {
    Activity act = (LoginActivity) getMvpView();
    Intent intent = new Intent(act, RegisterActivity.class);
    act.startActivity(intent);
  }
}
