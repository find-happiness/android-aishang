package com.aishang.app.ui.MyOrder;

import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JHotelListResult;
import com.aishang.app.data.model.JMyVacationApplyListResult;
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
 * Created by song on 2016/2/15.
 */
public class MyOrderPresenter extends BasePresenter<MyOrderMvpView> {

  private final DataManager mDataManager;
  private Subscription subscription;

  @Inject public MyOrderPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(MyOrderMvpView mvpView) {
    super.attachView(mvpView);
  }

  public void loadMyOrder(int version, String json, NetWorkType type) {
    loadMyOrder(false, version, json, type);
  }

  public void loadMyOrder(boolean allowMemoryCacheVersion, int version, String json,
      final NetWorkType type) {
    checkViewAttached();

    if (subscription != null && !subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }

    subscription = mDataManager.sysMyVacationApplyList(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JMyVacationApplyListResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常:" + e.toString());
          }

          @Override public void onNext(JMyVacationApplyListResult result) {
            if (result.getResult()
                .toUpperCase()
                .equals(Constants.RESULT_SUCCESS.toUpperCase())) {

              if (result.getItemList().length <= 0) {
                getMvpView().showEmpty();
              } else {
                switch (type) {
                  case refresh:
                    getMvpView().refreshList(result.getItemList());
                    break;
                  case loadMore:
                    getMvpView().loadMoreList(result.getItemList(),
                        result.getTotalItem());
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
    if (subscription != null) subscription.unsubscribe();
  }
}
