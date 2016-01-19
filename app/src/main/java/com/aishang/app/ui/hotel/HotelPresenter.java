package com.aishang.app.ui.hotel;

import com.aishang.app.data.DataManager;
import com.aishang.app.ui.base.BasePresenter;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by song on 2016/1/19.
 */
public class HotelPresenter extends BasePresenter<HotelMvpView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public HotelPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(HotelMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }
}
