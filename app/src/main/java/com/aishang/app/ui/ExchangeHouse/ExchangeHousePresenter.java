package com.aishang.app.ui.ExchangeHouse;

import android.util.Log;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JHotelRoomFacilitesCatListResult;
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
public class ExchangeHousePresenter extends BasePresenter<ExchangeHouseMvpView> {

  private static final String TAG = "ExchangeHousePresenter";
  private final DataManager mDataManager;
  private Subscription subscription;
  private Subscription facilitesSubscription;

  @Inject public ExchangeHousePresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(ExchangeHouseMvpView mvpView) {
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

    getMvpView().showNetProgress();
    subscription = mDataManager.syncProjectChange(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            getMvpView().dimissNetProgress();
            Log.i(TAG, "onError: "+e.toString());
            getMvpView().showError("网络异常");
          }

          @Override public void onNext(JResult result) {
            getMvpView().dimissNetProgress();
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().showSuccess();
            } else {
              getMvpView().showError(result.getResult());
            }
          }
        });
  }

  public void loadFacilites(int version) {
    checkViewAttached();

    if (facilitesSubscription != null && !facilitesSubscription.isUnsubscribed()) {
      facilitesSubscription.unsubscribe();
    }

    getMvpView().showNetProgress();
    facilitesSubscription = mDataManager.syncHotelRoomFacilitesCatList(version)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JHotelRoomFacilitesCatListResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            getMvpView().dimissNetProgress();
            getMvpView().showError("网络异常");
          }

          @Override public void onNext(JHotelRoomFacilitesCatListResult result) {
            getMvpView().dimissNetProgress();
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().showFacilites(result.getHotelRoomFacilitesCatList());
            } else {
              getMvpView().showError(result.getResult());
            }
          }
        });
  }

  @Override public void detachView() {
    super.detachView();
    if (subscription != null) subscription.unsubscribe();
    if (facilitesSubscription != null) facilitesSubscription.unsubscribe();
  }
}
