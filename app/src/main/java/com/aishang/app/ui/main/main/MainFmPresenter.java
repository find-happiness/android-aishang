package com.aishang.app.ui.main.main;

import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.Ribot;
import com.aishang.app.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by song on 2016/1/16.
 */
public class MainFmPresenter extends BasePresenter<MainFmMvpView> {

    private MainFmMvpView mainFmMvpView;

    private final DataManager mDataManager;
    private Subscription mSubscription;

    private List<Ribot> mCachedRibots;

    @Inject
    public MainFmPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainFmMvpView mvpView) {
        mainFmMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mainFmMvpView = null;
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadBanner()
    {
        loadBanner(false);
    }

    public void loadBanner(boolean allowMemoryCacheVersion) {
        checkViewAttached();

        if (mSubscription != null && !mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();

    }

    private Observable<List<Ribot>> getRibotsObservable(boolean allowMemoryCacheVersion) {
        if (allowMemoryCacheVersion && mCachedRibots != null) {
            return Observable.just(mCachedRibots);
        } else {
            return mDataManager.getRibots()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io());
        }
    }
}
