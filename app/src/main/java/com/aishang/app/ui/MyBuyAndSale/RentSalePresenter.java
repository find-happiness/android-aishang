package com.aishang.app.ui.MyBuyAndSale;

import android.util.Log;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JHotelListResult;
import com.aishang.app.data.model.JRentalListResult;
import com.aishang.app.data.model.JResult;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.util.Constants;
import com.aishang.app.util.NetWorkType;
import java.util.ArrayList;
import java.util.Arrays;
import javax.inject.Inject;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by song on 2016/2/15.
 */
public class RentSalePresenter extends BasePresenter<RentSaleMvpView> {
  private static final String TAG = "RentSalePresenter";
  private final DataManager mDataManager;
  private Subscription subscription;

  @Inject public RentSalePresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(RentSaleMvpView mvpView) {
    super.attachView(mvpView);
  }

  public void loadRentSale(int version, String json,final NetWorkType type) {
    loadRentSale(false, version, json,type);
  }

  public void loadRentSale(boolean allowMemoryCacheVersion, int version, String json,final NetWorkType type) {
    checkViewAttached();

    if (subscription != null && !subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }

    subscription = mDataManager.syncRentalList(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JRentalListResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常");
            Log.i(TAG, "onError: "+e.toString());
          }

          @Override public void onNext(JRentalListResult result) {
            if (result.getResult()
                .toUpperCase()
                .equals(Constants.RESULT_SUCCESS.toUpperCase())) {

              if (result.getRentalList().length <= 0 && type == NetWorkType.refresh) {
                getMvpView().showEmpty();
              } else {
                switch (type) {
                  case refresh:
                    getMvpView().refreshList(
                        new ArrayList<JRentalListResult.RentalItem>(Arrays.asList(result.getRentalList())));
                    break;
                  case loadMore:
                    getMvpView().loadMoreList(new ArrayList<JRentalListResult.RentalItem>(
                            Arrays.asList(result.getRentalList())),
                        result.getTotalCount());
                    break;
                }
              }
            } else {
              getMvpView().showError(result.getResult());
              //getMvpView().showEmpty();
            }
          }
        });
  }

  @Override public void detachView() {
    super.detachView();
    if (subscription != null) subscription.unsubscribe();
  }
}
