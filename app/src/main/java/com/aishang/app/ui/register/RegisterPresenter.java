package com.aishang.app.ui.register;

import android.util.Log;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JMemberBankListResult;
import com.aishang.app.data.model.JResult;
import com.aishang.app.data.model.JSendCodeResult;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.util.Constants;
import javax.inject.Inject;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by song on 2016/1/28.
 */
public class RegisterPresenter extends BasePresenter<RegisterMvpView> {

  private static final String TAG = "RegisterPresenter";
  private final DataManager mDataManager;
  private Subscription mSubscription;
  private Subscription mSubmitSubscription;

  @Inject public RegisterPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(RegisterMvpView mvpView) {
    super.attachView(mvpView);
  }

  @Override public void detachView() {
    super.detachView();
    if (mSubscription != null) mSubscription.unsubscribe();
    if (mSubmitSubscription != null) mSubmitSubscription.unsubscribe();
  }

  public void checkPhone(int version, String json) {
    checkPhone(false, version, json);
  }

  public void checkPhone(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (mSubscription != null && !mSubscription.isUnsubscribed()) {
      mSubscription.unsubscribe();
    }
    getMvpView().showNetDialog();
    mSubscription = mDataManager.syncSendCode(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JSendCodeResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            getMvpView().dismissDialog();
            getMvpView().showGetCodeError(e.toString());
            Log.e(TAG, "onError: " + e.toString());
          }

          @Override public void onNext(JSendCodeResult result) {
            getMvpView().dismissDialog();
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              if (result.isStatus()) {
                getMvpView().showCheckPhoneSuccess();
              } else {
                getMvpView().showGetCodeError("该号码已经注册，请直接登录。");
              }
            } else {
              getMvpView().showGetCodeError(result.getResult());
            }
          }
        });
  }

  public void submit(int version, String json,String cookie) {
    submit(false, version, json,cookie);
  }

  public void submit(boolean allowMemoryCacheVersion, int version, String json,String cookie) {
    checkViewAttached();

    if (mSubmitSubscription != null && !mSubmitSubscription.isUnsubscribed()) {
      mSubmitSubscription.unsubscribe();
    }
    getMvpView().showNetDialog();
    mSubmitSubscription = mDataManager.syncMemberNoteRegister(version, json,cookie)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            getMvpView().dismissDialog();
            //getMvpView().showError("网络异常");
            Log.e(TAG, "onError: " + e.toString());
          }

          @Override public void onNext(JResult result) {
            getMvpView().dismissDialog();
            Log.i(TAG, "onNext: " + result.toString());
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().showRegisterSuccess();
            } else {
              getMvpView().showError(result.getResult());
            }
          }
        });
  }
}
