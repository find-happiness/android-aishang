package com.aishang.app.ui.MyHouse;

import android.app.Activity;
import android.content.Intent;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JMyVacationListResult;
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
public class MyHousePresenter extends BasePresenter<MyHouseMvpView> {

  private final DataManager mDataManager;
  private Subscription subscription;

  @Inject public MyHousePresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(MyHouseMvpView mvpView) {
    super.attachView(mvpView);
  }

  public void loadMyVacationList(int version, String json) {
    loadMyVacationList(false, version, json);
  }

  public void loadMyVacationList(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (subscription != null && !subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }

    subscription = mDataManager.syncMyVacationList(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JMyVacationListResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常");
          }

          @Override public void onNext(JMyVacationListResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {

              if (result.getMyVaList().length <= 0) {
                getMvpView().showEmpty();
              } else {
                getMvpView().refreshList(result.getMyVaList());
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
