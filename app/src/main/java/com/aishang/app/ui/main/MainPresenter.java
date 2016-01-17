package com.aishang.app.ui.main;

import android.util.Log;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.Ribot;
import com.aishang.app.ui.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainMvpView> {

    private static final String TAG  = MainPresenter.class.getSimpleName();
    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public MainPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadRibots() {
        checkViewAttached();

//        mSubscription = mDataManager.getRibots()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Subscriber<List<Ribot>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, "There was an error loading the ribots.");
//                        getMvpView().showError();
//                    }
//
//                    @Override
//                    public void onNext(List<Ribot> ribots) {
//                        if (ribots.isEmpty()) {
//                            getMvpView().showRibotsEmpty();
//                        } else {
//                            getMvpView().showRibots(ribots);
//                        }
//                    }
//                });
    }

}
