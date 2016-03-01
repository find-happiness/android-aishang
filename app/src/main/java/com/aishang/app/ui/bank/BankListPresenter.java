package com.aishang.app.ui.bank;

import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JMemberBankListResult;
import com.aishang.app.data.model.JResult;
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
public class BankListPresenter extends BasePresenter<BankListMvpView> {

  private final DataManager mDataManager;
  private Subscription subscription;

  @Inject public BankListPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(BankListMvpView mvpView) {
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

    subscription = mDataManager.syncBankList(version, json)
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
                getMvpView().showSuccess(result.getBankAccountList());
              } else {
                getMvpView().showEmpty();
              }
            } else {
              getMvpView().showError(result.getResult());
            }
          }
        });
  }

  public void editData(int version, String json) {
    editData(false, version, json);
  }

  public void editData(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (subscription != null && !subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }

    subscription = mDataManager.syncBankEdit(version, json)
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
                getMvpView().showSuccess(result.getBankAccountList());
              } else {
                getMvpView().showEmpty();
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
