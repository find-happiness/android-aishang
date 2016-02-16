package com.aishang.app.ui.TravelList;

import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JHotelListResult;
import com.aishang.app.data.model.JNewsListResult;
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
 * Created by song on 2016/2/16.
 */
public class TravelPresenter extends BasePresenter<TravelMvpView> {

  private final DataManager mDataManager;
  private Subscription mHotelSubscription;

  @Inject public TravelPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(TravelMvpView mvpView) {
    super.attachView(mvpView);
  }

  public void loadTravel(int version, String json, NetWorkType type) {
    loadTravel(false, version, json, type);
  }

  public void loadTravel(boolean allowMemoryCacheVersion, int version, String json,
      final NetWorkType type) {
    checkViewAttached();

    if (mHotelSubscription != null && !mHotelSubscription.isUnsubscribed()) {
      mHotelSubscription.unsubscribe();
    }

    mHotelSubscription = mDataManager.syncTravel(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JNewsListResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常");
          }

          @Override public void onNext(JNewsListResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {

              if (result.getNewsList().length <= 0) {
                getMvpView().showEmpty();
              } else {
                switch (type) {
                  case refresh:
                    getMvpView().refreshList(result.getNewsList());
                    break;
                  case loadMore:
                    getMvpView().loadMoreList(result.getNewsList(), result.getTotalCount());
                    break;
                }
              }
            } else {
              getMvpView().showError(result.getResult());
              getMvpView().showEmpty();
            }
          }
        });
  }

  @Override public void detachView() {
    super.detachView();
    if (mHotelSubscription != null) mHotelSubscription.unsubscribe();
  }
}
