package com.aishang.app.ui.hotel;

import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JHotelListResult;
import com.aishang.app.data.model.JHotelPriceCatListResult;
import com.aishang.app.data.model.JHotelStarLevelListResult;
import com.aishang.app.data.model.JSysZoneResult;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.util.Constants;
import com.aishang.app.util.NetWorkType;
import java.util.ArrayList;
import java.util.Arrays;
import javax.inject.Inject;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by song on 2016/1/19.
 */
public class HotelPresenter extends BasePresenter<HotelMvpView> {

  private final DataManager mDataManager;
  private Subscription mZoneSubscription;
  private Subscription mHotelSubscription;
  private Subscription mPriceSubscription;
  private Subscription mStarLevelSubscription;

  @Inject public HotelPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(HotelMvpView mvpView) {
    super.attachView(mvpView);
  }

  public void loadHotel(int version, String json, NetWorkType type) {
    loadHotel(false, version, json, type);
  }

  public void loadHotel(boolean allowMemoryCacheVersion, int version, String json,
      final NetWorkType type) {
    checkViewAttached();

    if (mHotelSubscription != null && !mHotelSubscription.isUnsubscribed()) {
      mHotelSubscription.unsubscribe();
    }

    mHotelSubscription = mDataManager.syncHotelList(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JHotelListResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常:" + e.toString());
          }

          @Override public void onNext(JHotelListResult hotelListResult) {
            if (hotelListResult.getResult()
                .toUpperCase()
                .equals(Constants.RESULT_SUCCESS.toUpperCase())) {

              if (hotelListResult.getHotelList().length <= 0) {
                getMvpView().showHotelEmpty();
              } else {
                switch (type) {
                  case refresh:
                    getMvpView().refreshHotel(new ArrayList<JHotelListResult.Hotel>(
                        Arrays.asList(hotelListResult.getHotelList())));
                    break;
                  case loadMore:
                    getMvpView().loadMoreHotel(new ArrayList<JHotelListResult.Hotel>(
                            Arrays.asList(hotelListResult.getHotelList())),
                        hotelListResult.getTotalCount());
                    break;
                }
              }
            } else {
              getMvpView().showError(hotelListResult.getResult());
              getMvpView().showHotelEmpty();
            }
          }
        });
  }

  public void loadZone(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (mZoneSubscription != null && !mZoneSubscription.isUnsubscribed()) {
      mZoneSubscription.unsubscribe();
    }

    mZoneSubscription = mDataManager.sysZone(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JSysZoneResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常:" + e.toString());
          }

          @Override public void onNext(JSysZoneResult zoneResult) {
            if (zoneResult.getResult()
                .toUpperCase()
                .equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().showSysZoneDialog(
                  new ArrayList<JSysZoneResult.Zone>(Arrays.asList(zoneResult.getZoneList())));
            } else {
              getMvpView().showError(zoneResult.getResult());
            }
          }
        });
  }

  public void loadPriceCatList(boolean allowMemoryCacheVersion, int version) {
    checkViewAttached();

    if (mPriceSubscription != null && !mPriceSubscription.isUnsubscribed()) {
      mPriceSubscription.unsubscribe();
    }

    mPriceSubscription = mDataManager.syncHotelPriceCatList(version)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JHotelPriceCatListResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常:" + e.toString());
          }

          @Override public void onNext(JHotelPriceCatListResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().showSysPriceDialog(
                  new ArrayList<JHotelPriceCatListResult.Cat>(Arrays.asList(result.getCatList())));
            } else {
              getMvpView().showError(result.getResult());
            }
          }
        });
  }

  public void loadStarLevelList(boolean allowMemoryCacheVersion, int version) {
    checkViewAttached();

    if (mStarLevelSubscription != null && !mStarLevelSubscription.isUnsubscribed()) {
      mStarLevelSubscription.unsubscribe();
    }

    mStarLevelSubscription = mDataManager.syncHotelStarLevelList(version)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JHotelStarLevelListResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常:" + e.toString());
          }

          @Override public void onNext(JHotelStarLevelListResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().showSyncStarLevelDialog(
                  new ArrayList<JHotelStarLevelListResult.StarListEntity>(result.getStarList()));
            } else {
              getMvpView().showError(result.getResult());
            }
          }
        });
  }

  @Override public void detachView() {
    super.detachView();
    if (mZoneSubscription != null) mZoneSubscription.unsubscribe();
    if (mHotelSubscription != null) mHotelSubscription.unsubscribe();
    if (mPriceSubscription != null) mPriceSubscription.unsubscribe();
  }
}
