package com.aishang.app.ui.AccountCenter;

import android.util.Log;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JAwardDetailListV2Result;
import com.aishang.app.data.model.JCommonIntegralResult;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.util.Constants;
import com.aishang.app.util.NetWorkType;
import javax.inject.Inject;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by song on 2016/2/15.
 */
public class AwardDetailPresenter extends BasePresenter<AwardDetailMvpView> {

  private static final String TAG = "AccountCenterPresenter";
  private final DataManager mDataManager;
  private Subscription subscription;

  @Inject public AwardDetailPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(AwardDetailMvpView mvpView) {
    super.attachView(mvpView);
  }

  public void loadData(int version, String json, NetWorkType type) {
    loadData(false, version, json, type);
  }

  public void loadData(boolean allowMemoryCacheVersion, int version, String json,
      final NetWorkType type) {
    checkViewAttached();

    if (subscription != null && !subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }

    subscription = mDataManager.syncAwardDetailListV2(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JAwardDetailListV2Result>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e.toString());
            //getMvpView().showError("网络异常");
          }

          @Override public void onNext(JAwardDetailListV2Result result) {

            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {

              if (result.getDataList().size() <= 0 && type == NetWorkType.refresh) {
                getMvpView().showEmpty();
              } else {
                switch (type) {
                  case refresh:
                    getMvpView().showRefresh(result.getDataList());
                    break;
                  case loadMore:
                    getMvpView().showLoadMore(result.getDataList(), result.getTotalCount());
                    break;
                }
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
  }
}
