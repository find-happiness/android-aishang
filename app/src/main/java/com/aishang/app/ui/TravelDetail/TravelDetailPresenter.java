package com.aishang.app.ui.TravelDetail;

import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JNewsDetailResult;
import com.aishang.app.data.model.JNewsListResult;
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
 * Created by song on 2016/2/17.
 */
public class TravelDetailPresenter extends BasePresenter<TravelDetailMvpView> {
  private static final String TAG = "TravelDetailPresenter";

  private final DataManager mDataManager;
  private Subscription mSubscription;

  @Inject public TravelDetailPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(TravelDetailMvpView mvpView) {
    super.attachView(mvpView);
  }

  @Override public void detachView() {
    super.detachView();
    if (mSubscription != null) mSubscription.unsubscribe();
  }

  public void loadTravelDetail(int version, String json) {
    loadTravelDetail(false, version, json);
  }

  public void loadTravelDetail(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (mSubscription != null && !mSubscription.isUnsubscribed()) {
      mSubscription.unsubscribe();
    }

    this.getMvpView().showDialog();
    mSubscription = mDataManager.syncTravelDetail(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JNewsDetailResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().dimissDialog();
            getMvpView().showError("网络异常");
          }

          @Override public void onNext(JNewsDetailResult result) {
            getMvpView().dimissDialog();
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().loadTraveDetailFinish(result);
            } else {
              getMvpView().showError(result.getResult());
              //getMvpView().showEmpty();
            }
          }
        });
  }
}