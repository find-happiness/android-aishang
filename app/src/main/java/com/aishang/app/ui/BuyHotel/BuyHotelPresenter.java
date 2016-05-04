package com.aishang.app.ui.BuyHotel;

import android.util.Log;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JMemberProfileResult;
import com.aishang.app.data.model.JMyVacationListResult;
import com.aishang.app.data.model.JResult;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.util.Constants;
import javax.inject.Inject;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by song on 2016/2/15.
 */
public class BuyHotelPresenter extends BasePresenter<BuyHotelMvpView> {

  private static final String TAG = "BuyHotelPresenter";
  private final DataManager mDataManager;
  private Subscription subscription;
  private Subscription subscriptionProfile;
  private Subscription subscriptionVacation;

  @Inject public BuyHotelPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(BuyHotelMvpView mvpView) {
    super.attachView(mvpView);
  }

  public void postData(int version, String json) {
    postData(false, version, json);
  }

  public void postData(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (subscription != null && !subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }

    subscription = mDataManager.syncSubscription(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常");
          }

          @Override public void onNext(JResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().showSuccess();
            } else {
              getMvpView().showError(result.getResult());
            }
          }
        });
  }

  public void loadProfile(int version, String json) {
    checkViewAttached();

    if (subscriptionProfile != null && !subscriptionProfile.isUnsubscribed()) {
      subscriptionProfile.unsubscribe();
    }

    subscriptionProfile = mDataManager.sysMemberProfile(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JMemberProfileResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e.toString());
            //getMvpView().showError("网络异常");
          }

          @Override public void onNext(JMemberProfileResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().showGetProfileSuccess(result);
            } else {
              getMvpView().showError(result.getResult());
            }
          }
        });
  }

  public void loadVacation(int version, String json) {
    checkViewAttached();

    if (subscriptionVacation != null && !subscriptionVacation.isUnsubscribed()) {
      subscriptionVacation.unsubscribe();
    }

    subscriptionVacation = mDataManager.syncMyVacationList(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JMyVacationListResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            //getMvpView().showError("网络异常");

            Log.e(TAG, "onError: " + e.toString());
          }

          @Override public void onNext(JMyVacationListResult result) {

            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {

              if (result.getMyVaList().length > 0) {
                getMvpView().showGetVacationSuccess(result);
              } else {
                getMvpView().showVacationEmpty();
              }
            } else {
              getMvpView().showError(result.getResult());
            }
          }
        });
  }

  @Override public void detachView() {
    super.detachView();
    if (subscription != null) subscription.unsubscribe();
    if (subscriptionProfile != null) subscriptionProfile.unsubscribe();
    if (subscriptionVacation != null) subscriptionVacation.unsubscribe();
  }
}
