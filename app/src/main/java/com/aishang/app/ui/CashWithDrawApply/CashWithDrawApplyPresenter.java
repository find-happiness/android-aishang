package com.aishang.app.ui.CashWithDrawApply;

import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JMemberBankListResult;
import com.aishang.app.data.model.JResult;
import com.aishang.app.ui.RecommenCustomer.RecommentMvpView;
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
public class CashWithDrawApplyPresenter extends BasePresenter<CashWithDrawApplyMvpView> {

  private final DataManager mDataManager;
  private Subscription subscription;
  private Subscription subscriptionBank;

  @Inject public CashWithDrawApplyPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(CashWithDrawApplyMvpView mvpView) {
    super.attachView(mvpView);
  }

  public void postCashWithDrawApply(int version, String json) {
    postCashWithDrawApply(false, version, json);
  }

  public void postCashWithDrawApply(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (subscription != null && !subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }

    subscription = mDataManager.sysCashWithDrawApply(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常");
          }

          @Override public void onNext(JResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().showSuccess();
            } else {
              getMvpView().showError(result.getResult());
            }
          }
        });
  }

  public void loadBank(int version, String json) {
    loadBank(false, version, json);
  }

  public void loadBank(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (subscriptionBank != null && !subscriptionBank.isUnsubscribed()) {
      subscriptionBank.unsubscribe();
    }

    subscriptionBank = mDataManager.syncBankList(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JMemberBankListResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常");
          }

          @Override public void onNext(JMemberBankListResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              if (result.getBankAccountList().length > 0) {
                getMvpView().loadBankSuccess(result.getBankAccountList());
              } else {
                getMvpView().loadBankEmpty();
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
    if(subscriptionBank != null) subscriptionBank.unsubscribe();
  }
}