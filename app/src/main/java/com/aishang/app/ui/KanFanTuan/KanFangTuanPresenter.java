package com.aishang.app.ui.KanFanTuan;

import android.util.Log;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JMreActivityListResult;
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
public class KanFangTuanPresenter extends BasePresenter<KanFangTuanMvpView> {

  private static final String TAG = "KanFangTuanPresenter";
  private final DataManager mDataManager;
  private Subscription subscription;

  @Inject public KanFangTuanPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(KanFangTuanMvpView mvpView) {
    super.attachView(mvpView);
  }

  public void MreActivityDetail(int version, String json, NetWorkType type) {
    MreActivityDetail(false, version, json, type);
  }

  public void MreActivityDetail(boolean allowMemoryCacheVersion, int version, String json,
      final NetWorkType type) {
    checkViewAttached();

    if (subscription != null && !subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }

    subscription = mDataManager.syncMreActivityDetail(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JMreActivityListResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常");
            Log.e(TAG, "onError: " + e.toString());
          }

          @Override public void onNext(JMreActivityListResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {

              for (JMreActivityListResult.JActivityCatItem catItem : result.getActivityCatList()) {
                if (catItem.getCatID() == 0) {//all
                  if (catItem.getTotalCount() <= 0) {
                    getMvpView().showEmpty();
                  } else {
                    switch (type) {
                      case refresh:
                        getMvpView().refreshList(catItem.getActivityList());
                        break;
                      case loadMore:
                        getMvpView().loadMoreList(catItem.getActivityList(),
                            catItem.getTotalCount());
                        break;
                    }
                  }
                  return;
                }
              }

              getMvpView().showEmpty();
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
