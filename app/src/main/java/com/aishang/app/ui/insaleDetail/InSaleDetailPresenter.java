package com.aishang.app.ui.insaleDetail;

import android.util.Log;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JHotelDetailResult;
import com.aishang.app.data.model.JLoupanProductDetailResult;
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
public class InSaleDetailPresenter extends BasePresenter<InSaleDetailMvpView>{
  private static final String TAG = "InSaleDetailPresenter";
  private final DataManager mDataManager;
  private Subscription mSubscription;

  @Inject
  public InSaleDetailPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override
  public void attachView(InSaleDetailMvpView mvpView) {
    super.attachView(mvpView);
  }

  @Override
  public void detachView() {
    super.detachView();
    if (mSubscription != null) mSubscription.unsubscribe();
  }

  public void loadLoupanProductDetail(int version, String json) {
    loadLoupanProductDetail(false, version, json);
  }

  public void loadLoupanProductDetail(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (mSubscription != null && !mSubscription.isUnsubscribed()) {
      mSubscription.unsubscribe();
    }

    mSubscription = mDataManager.syncLoupanProductDetail(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JLoupanProductDetailResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常");
            Log.e(TAG, "onError: "+ e.toString());
          }

          @Override public void onNext(JLoupanProductDetailResult result) {
            if (result.getResult()
                .toUpperCase()
                .equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().bindDataToView(result);
            } else {
              getMvpView().showLoupanProductDetailError(result.getResult());
            }
          }
        });
  }
}
