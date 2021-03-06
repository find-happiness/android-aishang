package com.aishang.app.ui.main.main;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;

import android.util.Log;
import android.widget.DatePicker;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JHotelListResult;
import com.aishang.app.data.model.JLoupanProductListResult;
import com.aishang.app.data.model.JMrePromResult;
import com.aishang.app.data.model.JNewsListResult;
import com.aishang.app.data.model.JSysZoneResult;
import com.aishang.app.data.model.News;
import com.aishang.app.data.model.Ribot;
import com.aishang.app.ui.ExchangeHouse.ExchangeHouseActivity;
import com.aishang.app.ui.KanFanTuan.KanFanTuanActivity;
import com.aishang.app.ui.ProjectJoint.ProjectJointActivity;
import com.aishang.app.ui.TradeCenter.TradeCenterActivity;
import com.aishang.app.ui.TravelList.TravelListActivity;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.ui.hotel.HotelListActivity;
import com.aishang.app.ui.insale.InSaleActivity;

import com.aishang.app.ui.store.StoreActivity;
import com.aishang.app.util.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func4;
import rx.functions.Func5;
import rx.schedulers.Schedulers;

/**
 * Created by song on 2016/1/16.
 */
public class MainFmPresenter extends BasePresenter<MainFmMvpView> {

  private final DataManager mDataManager;
  private Subscription mBannerSubscription;
  private Subscription mLoupanSubscription;
  private Subscription mHotelSubscription;
  private Subscription mTraveSubscription;
  private Subscription mZoneSubscription;

  private List<Ribot> mCachedRibots;

  @Inject public MainFmPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(MainFmMvpView mvpView) {
    super.attachView(mvpView);
  }

  @Override public void detachView() {
    super.detachView();
    if (mBannerSubscription != null) mBannerSubscription.unsubscribe();
    if (mLoupanSubscription != null) mLoupanSubscription.unsubscribe();
    if (mHotelSubscription != null) mHotelSubscription.unsubscribe();
    if (mTraveSubscription != null) mTraveSubscription.unsubscribe();
    if (mZoneSubscription != null) mZoneSubscription.unsubscribe();
  }

  public void loadBanner(int version, String json) {
    loadBanner(false, version, json);
  }

  public void loadBanner(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (mBannerSubscription != null && !mBannerSubscription.isUnsubscribed()) {
      mBannerSubscription.unsubscribe();
    }

    mBannerSubscription = mDataManager.syncMreProm(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JMrePromResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常");
            getMvpView().addNetCount();
          }

          @Override public void onNext(JMrePromResult jMrePromResult) {
            if (jMrePromResult.getResult()
                .toUpperCase()
                .equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().showBanner(
                  new ArrayList<JMrePromResult.Ad>(Arrays.asList(jMrePromResult.getAdList())));
            } else {
              getMvpView().showError(jMrePromResult.getResult());
            }
            getMvpView().addNetCount();
          }
        });
  }

  public void loadLoupan(int version, String json) {
    loadLoupan(false, version, json);
  }

  public void loadLoupan(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (mLoupanSubscription != null && !mLoupanSubscription.isUnsubscribed()) {
      mLoupanSubscription.unsubscribe();
    }

    mLoupanSubscription = mDataManager.syncLoupanList(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JLoupanProductListResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常");
            getMvpView().addNetCount();
          }

          @Override public void onNext(JLoupanProductListResult loupanProductListResult) {
            if (loupanProductListResult.getResult()
                .toUpperCase()
                .equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().showLoupan(new ArrayList<JLoupanProductListResult.Product>(
                      Arrays.asList(loupanProductListResult.getProductList())),
                  new ArrayList<JLoupanProductListResult.Loupan>(
                      Arrays.asList(loupanProductListResult.getLoupanList())));
            } else {
              getMvpView().showError(loupanProductListResult.getResult());
              getMvpView().showLoupanEmpty();
            }
            getMvpView().addNetCount();
          }
        });
  }

  public void loadHotel(int version, String json) {
    loadHotel(false, version, json);
  }

  public void loadHotel(boolean allowMemoryCacheVersion, int version, String json) {
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
            getMvpView().showError("网络异常");
            getMvpView().addNetCount();
          }

          @Override public void onNext(JHotelListResult hotelListResult) {
            if (hotelListResult.getResult()
                .toUpperCase()
                .equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().showHotel(new ArrayList<JHotelListResult.Hotel>(
                  Arrays.asList(hotelListResult.getHotelList())));
            } else {
              getMvpView().showError(hotelListResult.getResult());
              getMvpView().showHotelEmpty();
            }
            getMvpView().addNetCount();
          }
        });
  }

  public void loadTravel(int version, String json) {
    loadTravel(false, version, json);
  }

  public void loadTravel(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (mTraveSubscription != null && !mTraveSubscription.isUnsubscribed()) {
      mTraveSubscription.unsubscribe();
    }

    mTraveSubscription = mDataManager.syncTravel(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JNewsListResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常");
            getMvpView().addNetCount();
          }

          @Override public void onNext(JNewsListResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              JNewsListResult.NewsCatListEntity cat = null;
              for (JNewsListResult.NewsCatListEntity item : result.getNewsCatList()) {
                if (item.getCatID() == 2) {
                  cat = item;
                }
              }

              if (cat != null && cat.getNewsList().size() > 0) {
                Observable.zip(Observable.from(cat.getNewsList()),
                    Observable.from(result.getUserImageUrlList()),
                    Observable.from(result.getZoneName()),
                    Observable.from(result.getEnshrinedCountList()),
                    Observable.from(result.getUserNameList()),
                    new Func5<JNewsListResult.NewsListEntity, String, String, Integer, String, News>() {
                      @Override public News call(JNewsListResult.NewsListEntity newsListEntity,
                          String userImage, String zoneName, Integer enshrinedCount,
                          String userName) {
                        return new News(newsListEntity, enshrinedCount, userImage, zoneName,
                            userName);
                      }
                    }).toList().subscribe(new Action1<List<News>>() {
                  @Override public void call(List<News> newses) {
                    getMvpView().showTrave(newses);
                  }
                });
              } else {
                getMvpView().showTraveEmpty();
              }
            } else {
              getMvpView().showError(result.getResult());
              getMvpView().showTraveEmpty();
            }

            getMvpView().addNetCount();
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
            getMvpView().showError("网络异常");
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

  private Observable<List<Ribot>> getRibotsObservable(boolean allowMemoryCacheVersion) {
    if (allowMemoryCacheVersion && mCachedRibots != null) {
      return Observable.just(mCachedRibots);
    } else {
      return mDataManager.getRibots()
          .observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io());
    }
  }

  protected void IntentToZaiShou() {
    Activity content = ((MainFmFragment) getMvpView()).getActivity();
    Intent intent = new Intent();
    intent.setClass(content, InSaleActivity.class);
    content.startActivity(intent);
  }

  protected void IntentToHuanZu() {
    Activity content = ((MainFmFragment) getMvpView()).getActivity();
    Intent intent = new Intent();
    intent.setClass(content, HotelListActivity.class);
    content.startActivity(intent);
  }

  protected void IntentToTravelList() {
    Activity content = ((MainFmFragment) getMvpView()).getActivity();
    Intent intent = new Intent();
    intent.setClass(content, TravelListActivity.class);
    content.startActivity(intent);
  }

  protected void IntentToProjectJoint() {
    Activity content = ((MainFmFragment) getMvpView()).getActivity();
    Intent intent = new Intent();
    intent.setClass(content, ProjectJointActivity.class);
    content.startActivity(intent);
  }

  protected void IntentToKanFanTuan() {
    Activity content = ((MainFmFragment) getMvpView()).getActivity();
    Intent intent = new Intent();
    intent.setClass(content, KanFanTuanActivity.class);
    content.startActivity(intent);
  }

  protected void IntentToChangeHouse() {
    Activity content = ((MainFmFragment) getMvpView()).getActivity();
    Intent intent = new Intent();
    intent.setClass(content, ExchangeHouseActivity.class);
    content.startActivity(intent);
  }

  protected void IntentToTradeCenter() {
    Activity content = ((MainFmFragment) getMvpView()).getActivity();
    Intent intent = new Intent();
    intent.setClass(content, TradeCenterActivity.class);
    content.startActivity(intent);
  }

  protected void IntentToStore() {
    Activity content = ((MainFmFragment) getMvpView()).getActivity();
    Intent intent = new Intent();
    intent.setClass(content, StoreActivity.class);
    content.startActivity(intent);
  }
}
