package com.aishang.app.ui.AccountCenter;

import android.util.Log;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JCommonIntegralResult;
import com.aishang.app.data.model.JMemberBankListResult;
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
public class AccountCenterPresenter extends BasePresenter<AccountCenterMvpView> {

  private static final String TAG = "AccountCenterPresenter";
  private final DataManager mDataManager;
  private Subscription subscription;

  @Inject public AccountCenterPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(AccountCenterMvpView mvpView) {
    super.attachView(mvpView);
  }

  public void loadData(int version, String json) {
    loadData(false, version, json);
  }

  public void loadData(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (subscription != null && !subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }

    subscription = mDataManager.syncCommonIntegral(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JCommonIntegralResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            Log.e(TAG, "onError: "+e.toString() );
            //getMvpView().showError("网络异常");
          }

          @Override public void onNext(JCommonIntegralResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
                getMvpView().showSuccess(result);
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
