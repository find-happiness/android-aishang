package com.aishang.app.ui.ProjectJoint;

import com.aishang.app.data.DataManager;
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
public class ProjectJoinPresenter extends BasePresenter<ProjectJoinMvpView> {

  private final DataManager mDataManager;
  private Subscription subscription;

  @Inject public ProjectJoinPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(ProjectJoinMvpView mvpView) {
    super.attachView(mvpView);
  }

  public void postData(int version, String json) {
    postData(false, version, json);
  }

  public void postData(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (subscription != null && !subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }

    subscription = mDataManager.syncProjecCtooperation(version, json)
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
