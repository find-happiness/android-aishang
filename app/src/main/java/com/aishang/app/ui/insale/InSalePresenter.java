package com.aishang.app.ui.insale;

import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JLoupanPriceCatListResult;
import com.aishang.app.data.model.JLoupanProductCatListResult;
import com.aishang.app.data.model.JLoupanProductListResult;
import com.aishang.app.data.model.JSysZoneResult;
import com.aishang.app.data.model.LoupanProduct;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.util.Constants;
import com.aishang.app.util.NetWorkType;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by song on 2016/1/18.
 */
public class InSalePresenter extends BasePresenter<InSaleMvpView> {
  private static final String TAG = "InSalePresenter";

  private final DataManager mDataManager;
  private Subscription mSubscription;
  private Subscription mZoneSubscription;
  private Subscription mPriceSubscription;

  @Inject public InSalePresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(InSaleMvpView mvpView) {
    super.attachView(mvpView);
  }

  @Override public void detachView() {
    super.detachView();
    if (mSubscription != null) mSubscription.unsubscribe();
    if (mZoneSubscription != null) mZoneSubscription.unsubscribe();
    if (mPriceSubscription != null) mPriceSubscription.unsubscribe();
  }

  public void loadLoupanProduct(int version, String json, NetWorkType type) {
    loadLoupanProduct(false, version, json, type);
  }

  public void loadLoupanProduct(boolean allowMemoryCacheVersion, int version, String json,
      final NetWorkType type) {
    checkViewAttached();

    if (mSubscription != null && !mSubscription.isUnsubscribed()) {
      mSubscription.unsubscribe();
    }

    mSubscription = mDataManager.syncLoupanList(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JLoupanProductListResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常:" + e.toString());
          }

          @Override public void onNext(JLoupanProductListResult hotelListResult) {
            if (hotelListResult.getResult()
                .toUpperCase()
                .equals(Constants.RESULT_SUCCESS.toUpperCase())) {

              if (hotelListResult.getLoupanList().length <= 0 && type == NetWorkType.refresh) {
                getMvpView().showLoupanProductEmpty();
              } else {
                List<LoupanProduct> loupanProducts = new ArrayList<LoupanProduct>();
                for (JLoupanProductListResult.Loupan loupan : hotelListResult.getLoupanList()) {
                  int loupanid = loupan.getLoupanID();
                  for (JLoupanProductListResult.Product product : hotelListResult.getProductList()) {
                    if (product.getLoupanID() == loupanid) {
                      loupanProducts.add(new LoupanProduct(product, loupan));
                      continue;
                    }
                  }
                }
                switch (type) {
                  case refresh:
                    getMvpView().refreshLoupanProduct(loupanProducts);
                    break;
                  case loadMore:
                    getMvpView().loadMoreLoupanProduct(loupanProducts,
                        hotelListResult.getProductCount());
                    break;
                }
              }
            } else {
              getMvpView().showError(hotelListResult.getResult());
              //getMvpView().showLoupanProductEmpty();
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

  public void loadPriceCatList(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (mPriceSubscription != null && !mPriceSubscription.isUnsubscribed()) {
      mPriceSubscription.unsubscribe();
    }

    mPriceSubscription = mDataManager.syncLoupanPriceCatList(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JLoupanPriceCatListResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常:" + e.toString());
          }

          @Override public void onNext(JLoupanPriceCatListResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().showSysPriceDialog(
                  new ArrayList<JLoupanPriceCatListResult.Cat>(Arrays.asList(result.getCatList())));
            } else {
              getMvpView().showError(result.getResult());
            }
          }
        });
  }

  public void loadLoupanProductCatList(boolean allowMemoryCacheVersion, int version) {
    checkViewAttached();

    if (mPriceSubscription != null && !mPriceSubscription.isUnsubscribed()) {
      mPriceSubscription.unsubscribe();
    }

    mPriceSubscription = mDataManager.syncLoupanProductCatList(version)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JLoupanProductCatListResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常:" + e.toString());
          }

          @Override public void onNext(JLoupanProductCatListResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().showLoupanProductCatDialog(
                  new ArrayList<JLoupanProductCatListResult.Cat>(
                      Arrays.asList(result.getCatList())));
            } else {
              getMvpView().showError(result.getResult());
            }
          }
        });
  }
}
