package com.aishang.app.ui.main.more;

import android.app.Activity;
import android.content.Intent;

import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JHotelListResult;
import com.aishang.app.data.model.JVersionCheckParam;
import com.aishang.app.data.model.JVersionCheckResult;
import com.aishang.app.data.model.Ribot;
import com.aishang.app.ui.AccountCenter.AccountCenterActivity;
import com.aishang.app.ui.Setting.SettingActivity;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.ui.suggestion.SuggestionActivity;

import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.Constants;
import com.aishang.app.util.NetWorkType;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by song on 2016/1/16.
 */
public class MorePresenter extends BasePresenter<MoreMvpView> {

  private final DataManager mDataManager;
  private Subscription mSubscription;
  private Subscription mSubscriptionVersion;

  @Inject public MorePresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(MoreMvpView mvpView) {
    super.attachView(mvpView);
  }

  @Override public void detachView() {
    if (mSubscription != null) mSubscription.unsubscribe();
    if (mSubscriptionVersion != null) mSubscription.unsubscribe();
  }

  public void checkVersion() {
    checkViewAttached();

    final int localVersion =
        CommonUtil.getVersionCode(((MoreFragment) this.getMvpView()).getActivity());
    JVersionCheckParam param = new JVersionCheckParam();
    param.setPlatform("android");
    param.setScreen("phone");
    param.setVersion("" + localVersion);

    if (mSubscriptionVersion != null && !mSubscriptionVersion.isUnsubscribed()) {
      mSubscriptionVersion.unsubscribe();
    }

    mSubscriptionVersion = mDataManager.syncVersionCheck(1,new Gson().toJson(param))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JVersionCheckResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().dimissDialog();
            getMvpView().showError("网络异常");
          }

          @Override public void onNext(JVersionCheckResult result) {
            getMvpView().dimissDialog();
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              if (result.getVersion() > localVersion) {
                getMvpView().updata(result.getSourceUrl());
              } else {
                getMvpView().noUpdata();
              }
            } else {
              getMvpView().showError("");
            }
          }
        });
  }

  public void intentToSuggestion() {
    Activity act = ((MoreFragment) this.getMvpView()).getActivity();

    Intent intent = new Intent(act, SuggestionActivity.class);

    act.startActivity(intent);
  }

  public void intentToSetting() {
    Activity act = ((MoreFragment) this.getMvpView()).getActivity();

    Intent intent = new Intent(act, SettingActivity.class);

    act.startActivity(intent);
  }
}
