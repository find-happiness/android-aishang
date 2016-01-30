package com.aishang.app.ui.HotelDetail;

import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JHotelDetailResult;
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
public class HotelDetailPresenter extends BasePresenter<HotelDetailMvpView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public HotelDetailPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(HotelDetailMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }
    public void loadHotelDetail( int version, String json) {
        loadHotelDetail(false,version,json);
    }

    public void loadHotelDetail(boolean allowMemoryCacheVersion, int version, String json) {
        checkViewAttached();

        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }

        mSubscription = mDataManager.syncHotelDetail(version, json)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(new Subscriber<JHotelDetailResult>() {
                @Override public void onCompleted() {

                }

                @Override public void onError(Throwable e) {
                    getMvpView().showError("网络异常:" + e.toString());
                }

                @Override public void onNext(JHotelDetailResult result) {
                    if (result.getResult()
                        .toUpperCase()
                        .equals(Constants.RESULT_SUCCESS.toUpperCase())) {
                        getMvpView().bindDataToView(result);
                    } else {
                        getMvpView().showHotelDetailError(result.getResult());
                    }
                }
            });
    }

}
