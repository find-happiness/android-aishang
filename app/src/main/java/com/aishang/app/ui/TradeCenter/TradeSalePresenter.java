package com.aishang.app.ui.TradeCenter;

import android.util.Log;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JLoupanProductDetailResult;
import com.aishang.app.data.model.JLoupanProductVIPViewResult;
import com.aishang.app.data.model.JResult;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.util.Constants;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by song on 2016/4/12.
 */
public class TradeSalePresenter extends BasePresenter<TradeSaleMvpView> {
  private static final String TAG = "TravelDetailPresenter";

  private final DataManager mDataManager;
  private Subscription mSubscription;

  @Inject public TradeSalePresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(TradeSaleMvpView mvpView) {
    super.attachView(mvpView);
  }

  @Override public void detachView() {
    super.detachView();
    if (mSubscription != null) mSubscription.unsubscribe();
  }

  public void loadLoupanProductVIPView(int version, String json) {
    loadLoupanProductVIPView(false, version, json);
  }

  public void loadLoupanProductVIPView(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (mSubscription != null && !mSubscription.isUnsubscribed()) {
      mSubscription.unsubscribe();
    }

    this.getMvpView().showDialog();
    mSubscription = mDataManager.syncLoupanProductVIPView(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JLoupanProductVIPViewResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().dimissDialog();
            //getMvpView().showError("网络异常");
          }

          @Override public void onNext(JLoupanProductVIPViewResult result) {
            getMvpView().dimissDialog();
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {

              Observable.from(result.getZoneList())
                  .flatMap(
                      new Func1<JLoupanProductVIPViewResult.ZoneListBean, Observable<JLoupanProductVIPViewResult.ZoneListBean.LoupanListBean>>() {
                        @Override
                        public Observable<JLoupanProductVIPViewResult.ZoneListBean.LoupanListBean> call(
                            JLoupanProductVIPViewResult.ZoneListBean zoneListBean) {
                          return Observable.from(zoneListBean.getLoupanList());
                        }
                      })
                  .toList()
                  .subscribe(
                      new Action1<List<JLoupanProductVIPViewResult.ZoneListBean.LoupanListBean>>() {
                        @Override public void call(
                            List<JLoupanProductVIPViewResult.ZoneListBean.LoupanListBean> loupanListBeen) {
                          getMvpView().bindLoupan(loupanListBeen);
                        }
                      });
            } else {
              getMvpView().showError(result.getResult());
              //getMvpView().showEmpty();
            }
          }
        });
  }

  public void loadLoupanProductDetail(int version, String json) {
    loadLoupanProductDetail(false, version, json);
  }

  public void loadLoupanProductDetail(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (mSubscription != null && !mSubscription.isUnsubscribed()) {
      mSubscription.unsubscribe();
    }

    getMvpView().showDialog();
    mSubscription = mDataManager.syncLoupanProductDetail(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JLoupanProductDetailResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().dimissDialog();
            //getMvpView().showError("网络异常");
            Log.e(TAG, "onError: " + e.toString());
          }

          @Override public void onNext(JLoupanProductDetailResult result) {
            getMvpView().dimissDialog();
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().bindLoupanDetail(result);
            } else {
              getMvpView().showError(result.getResult());
            }
          }
        });
  }

  public void postNewsCriticism(int version, String json) {
    checkViewAttached();

    if (mSubscription != null && !mSubscription.isUnsubscribed()) {
      mSubscription.unsubscribe();
    }

    this.getMvpView().showDialog();
    mSubscription = mDataManager.syncNewsCriticism(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().dimissDialog();
            //getMvpView().showError("网络异常");
          }

          @Override public void onNext(JResult result) {
            getMvpView().dimissDialog();
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              //getMvpView().loadTraveDetailFinish(result);
            } else {
              getMvpView().showError(result.getResult());
              //getMvpView().showEmpty();
            }
          }
        });
  }
}
