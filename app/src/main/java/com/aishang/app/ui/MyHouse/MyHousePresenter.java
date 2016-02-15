package com.aishang.app.ui.MyHouse;

import android.app.Activity;
import android.content.Intent;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JBusinessListResult;
import com.aishang.app.ui.BrokerCenter.BrokerCenterActivity;
import com.aishang.app.ui.BrokerCenter.BrokerCenterMvpView;
import com.aishang.app.ui.ChangePassword.ChangePasswordActivity;
import com.aishang.app.ui.RecommenCustomer.RecommenCustomerActivity;
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
public class MyHousePresenter extends BasePresenter<MyHouseMvpView> {

  private final DataManager mDataManager;
  private Subscription subscription;

  @Inject public MyHousePresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(MyHouseMvpView mvpView) {
    super.attachView(mvpView);
  }

  public void loadBusiness(int version, String json, NetWorkType type) {
    loadBusiness(false, version, json, type);
  }

  public void loadBusiness(boolean allowMemoryCacheVersion, int version, String json,
      final NetWorkType type) {
    checkViewAttached();

    if (subscription != null && !subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }

    subscription = mDataManager.sysBusinessList(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JBusinessListResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常");
          }

          @Override public void onNext(JBusinessListResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {

              if (result.getBusinessList().length <= 0) {
                getMvpView().showEmpty();
              } else {
                switch (type) {
                  case refresh:
                    getMvpView().refreshList(result.getBusinessList());
                    break;
                  case loadMore:
                    getMvpView().loadMoreList(result.getBusinessList(), result.getRecCount());
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

  public void intentToChangePsw() {
    Activity act = (BrokerCenterActivity) getMvpView();
    Intent intent = new Intent(act, ChangePasswordActivity.class);
    act.startActivity(intent);
    act.finish();
  }

  public void intentToContactsAdd() {
    Activity act = (BrokerCenterActivity) getMvpView();
    Intent intent = new Intent(act, RecommenCustomerActivity.class);
    act.startActivity(intent);
  }
}
