package com.aishang.app.ui.MemberGiftcard;

import android.util.Log;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JMemberBankListResult;
import com.aishang.app.data.model.JMemberGiftcardResult;
import com.aishang.app.data.model.JResult;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.util.Constants;
import javax.inject.Inject;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by song on 2016/2/16.
 */
public class MemberGiftcarPresenter extends BasePresenter<MemberGiftcarMvpView> {

  private static final String TAG = "MemberGiftcarPresenter";
  private final DataManager mDataManager;
  private Subscription subscriptionBank;

  @Inject public MemberGiftcarPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(MemberGiftcarMvpView mvpView) {
    super.attachView(mvpView);
  }

  public void loadMemberGiftcard(int version, String json) {
    loadMemberGiftcard(false, version, json);
  }

  public void loadMemberGiftcard(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (subscriptionBank != null && !subscriptionBank.isUnsubscribed()) {
      subscriptionBank.unsubscribe();
    }

    subscriptionBank = mDataManager.syncGetMemberGiftcard(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JMemberGiftcardResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e.toString());
          }

          @Override public void onNext(JMemberGiftcardResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              if (result.getMemberGiftcardList().size() > 0) {
                getMvpView().loadMemberGiftcardSuccess(result);
              } else {
                getMvpView().loadMemberGiftcardEmpty();
              }
            } else {
              getMvpView().showError(result.getResult());
            }
          }
        });
  }

  @Override public void detachView() {
    super.detachView();
    if (subscriptionBank != null) subscriptionBank.unsubscribe();
  }
}