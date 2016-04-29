package com.aishang.app.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JCodeLoginResult;
import com.aishang.app.data.model.JMemberLoginParam;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.data.model.JSendCodeResult;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.ChangePassword.ChangePasswordActivity;
import com.aishang.app.ui.ForgetPossword.ForgetPosswordActivity;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.ui.main.MainActivity;
import com.aishang.app.ui.register.RegisterActivity;

import com.aishang.app.util.Constants;
import com.aishang.app.util.OkHttpUtils;
import com.google.gson.Gson;
import java.util.HashMap;
import javax.inject.Inject;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by song on 2016/1/20.
 */
public class LoginPresenter extends BasePresenter<LoginMvpView> {
  private static final String TAG = "LoginPresenter";

  private final DataManager mDataManager;
  private Subscription mPswLoginSubscription;
  private Subscription mCodeLoginSubscription;
  private Subscription mGetCodeSubscription;

  @Inject public LoginPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(LoginMvpView mvpView) {
    super.attachView(mvpView);
  }

  @Override public void detachView() {
    super.detachView();
    if (mPswLoginSubscription != null) mPswLoginSubscription.unsubscribe();
    if (mCodeLoginSubscription != null) mCodeLoginSubscription.unsubscribe();
    if (mGetCodeSubscription != null) mGetCodeSubscription.unsubscribe();
  }

  public void Login(int version, final String json) {
    checkViewAttached();

    if (mPswLoginSubscription != null && !mPswLoginSubscription.isUnsubscribed()) {
      mPswLoginSubscription.unsubscribe();
    }

    getMvpView().showNetDialog();
    mPswLoginSubscription = mDataManager.syncLogin(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JMemberLoginResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().dismissDialog();
            getMvpView().showError(e.toString());
          }

          @Override public void onNext(JMemberLoginResult jMemberLoginResult) {
            getMvpView().dismissDialog();
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

  public void getVerificationCode(int version, String json) {
    getVerificationCode(false, version, json);
  }

  public void getVerificationCode(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (mGetCodeSubscription != null && !mGetCodeSubscription.isUnsubscribed()) {
      mGetCodeSubscription.unsubscribe();
    }
    getMvpView().showNetDialog();
    mGetCodeSubscription = mDataManager.syncSendCode(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JSendCodeResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            //getMvpView().showError("网络异常");
            Log.e(TAG, "onError: " + e.toString());
          }

          @Override public void onNext(JSendCodeResult result) {
            getMvpView().dismissDialog();
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              if (result.isStatus()) {
                getMvpView().showGetVerificationSuccess();
              } else {
                getMvpView().showError("该号码已经注册，请直接登录。");
              }
            } else {
              getMvpView().showError(result.getResult());
            }
          }
        });
  }

  public void codeLogin(int version, final String json,String cookie) {
    checkViewAttached();

    if (mCodeLoginSubscription != null && !mCodeLoginSubscription.isUnsubscribed()) {
      mCodeLoginSubscription.unsubscribe();
    }
    getMvpView().showNetDialog();

    //HashMap<String, RequestBody> map = new HashMap<>();
    //RequestBody jsonBody = RequestBody.create(MediaType.parse("application/json"), json);
    //map.put("q", jsonBody);
    //map.put("v", RequestBody.create(MediaType.parse("text/plain"), version + ""));

    mCodeLoginSubscription = mDataManager.syncCodeLogin(cookie,json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JMemberLoginResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {

            Log.i(TAG, "onError: " + e);
            getMvpView().dismissDialog();
            getMvpView().showError(e.toString());
          }

          @Override public void onNext(JMemberLoginResult jMemberLoginResult) {
            getMvpView().dismissDialog();
            if (jMemberLoginResult.getResult().toUpperCase().equals("success".toUpperCase())) {
              getMvpView().loginScuess(jMemberLoginResult);
              JMemberLoginParam param = new Gson().fromJson(json, JMemberLoginParam.class);
              getMvpView().saveLoginData(param.getMemberAccount(), param.getPassword());
              //Log.i(TAG, "onNext: codelogin success!");
            } else {
              //Log.i(TAG, "onNext: " + new Gson().toJson(jMemberLoginResult));
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
