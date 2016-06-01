package com.aishang.app.ui.income;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JCheckinRecordResult;
import com.aishang.app.ui.BrokerCenter.BrokerCenterActivity;
import com.aishang.app.ui.ChangePassword.ChangePasswordActivity;
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
public class IncomePresenter extends BasePresenter<IncomeMvpView> {
  private static final String TAG = "IncomePresenter";
  private final DataManager mDataManager;
  private Subscription subscription;

  @Inject public IncomePresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(IncomeMvpView mvpView) {
    super.attachView(mvpView);
  }

  public void loadCheckinRecord(int version, String json) {
    loadCheckinRecord(false, version, json);
  }

  public void loadCheckinRecord(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (subscription != null && !subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }

    subscription = mDataManager.syncCheckinRecord(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JCheckinRecordResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e.toString());
          }

          @Override public void onNext(JCheckinRecordResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {

              if (result.getCheckinRecordList().size() <= 0) {
                getMvpView().showEmpty();
              } else {
                getMvpView().refreshList(result.getCheckinRecordList());
              }
            } else {
              getMvpView().showError(result.getResult());
              //getMvpView().showEmpty();
            }
          }
        });
  }

  @Override public void detachView() {
    super.detachView();
    if (subscription != null) subscription.unsubscribe();
  }

  public void intentToChangePsw() {
    Activity act = (BrokerCenterActivity) getMvpView();
    Intent intent = new Intent(act, ChangePasswordActivity.class);
    act.startActivity(intent);
    act.finish();
  }
}
