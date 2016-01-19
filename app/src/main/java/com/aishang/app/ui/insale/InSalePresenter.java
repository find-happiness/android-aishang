package com.aishang.app.ui.insale;

import com.aishang.app.data.DataManager;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.ui.main.MainMvpView;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by song on 2016/1/18.
 */
public class InSalePresenter extends BasePresenter<InSaleMvpView>{
    private static final String TAG = "InSalePresenter";

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public InSalePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(InSaleMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }
}
