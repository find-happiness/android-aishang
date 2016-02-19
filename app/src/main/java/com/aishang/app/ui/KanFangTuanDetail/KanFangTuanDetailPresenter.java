package com.aishang.app.ui.KanFangTuanDetail;

import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JMreActivityDetailResult;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.util.Constants;
import javax.inject.Inject;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by song on 2016/1/19.
 */
public class KanFangTuanDetailPresenter extends BasePresenter<KanFangTuanDetailMvpView>{
  private final DataManager mDataManager;
  private Subscription mSubscription;

  @Inject
  public KanFangTuanDetailPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override
  public void attachView(KanFangTuanDetailMvpView mvpView) {
    super.attachView(mvpView);
  }

  @Override
  public void detachView() {
    super.detachView();
    if (mSubscription != null) mSubscription.unsubscribe();
  }

  public void loadKanFangTuanDetail(int version, String json) {
    loadKanFangTuanDetail(false, version, json);
  }

  public void loadKanFangTuanDetail(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (mSubscription != null && !mSubscription.isUnsubscribed()) {
      mSubscription.unsubscribe();
    }

    mSubscription = mDataManager.syncMreActivityDetail(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JMreActivityDetailResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常:" + e.toString());
          }

          @Override public void onNext(JMreActivityDetailResult result) {
            if (result.getResult()
                .toUpperCase()
                .equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().bindDataToView(result);
            } else {
              getMvpView().showKanFangTuanDetailError(result.getResult());
            }
          }
        });
  }

}
