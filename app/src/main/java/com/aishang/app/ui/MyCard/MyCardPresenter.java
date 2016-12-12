package com.aishang.app.ui.MyCard;

import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JMemberBankListResult;
import com.aishang.app.data.model.JMyVacationListResult;
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
public class MyCardPresenter extends BasePresenter<MyCardMvpView> {

  private final DataManager mDataManager;
  private Subscription subscription;

  @Inject public MyCardPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(MyCardMvpView mvpView) {
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

    subscription = mDataManager.syncMyVacationList(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JMyVacationListResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            getMvpView().refreshCompleted();
            getMvpView().showError("网络异常");
          }

          @Override public void onNext(JMyVacationListResult result) {

            getMvpView().refreshCompleted();
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {

              if (result.getMyVaList().length > 0) {
                getMvpView().refreshList(result);
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
