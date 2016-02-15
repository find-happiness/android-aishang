package com.aishang.app.ui.RecommenCustomer;

import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JBusinessListResult;
import com.aishang.app.data.model.JResult;
import com.aishang.app.ui.BrokerCenter.BrokerCenterMvpView;
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
public class ReccommentPresenter extends BasePresenter<RecommentMvpView> {

  private final DataManager mDataManager;
  private Subscription subscription;

  @Inject public ReccommentPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(RecommentMvpView mvpView) {
    super.attachView(mvpView);
  }

  public void pushContacts(int version, String json) {
    pushContacts(false, version, json);
  }

  public void pushContacts(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (subscription != null && !subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }

    subscription = mDataManager.sysContactsAdd(version, json)
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

  @Override public void detachView() {
    super.detachView();
    if (subscription != null) subscription.unsubscribe();
  }
}
