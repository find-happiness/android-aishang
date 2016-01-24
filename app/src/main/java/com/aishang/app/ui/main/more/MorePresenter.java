package com.aishang.app.ui.main.more;

import android.app.Activity;
import android.content.Intent;

import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.Ribot;
import com.aishang.app.ui.AccountCenter.AccountCenterActivity;
import com.aishang.app.ui.Setting.SettingActivity;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.ui.suggestion.SuggestionActivity;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by song on 2016/1/16.
 */
public class MorePresenter extends BasePresenter<MoreMvpView>{


    private MoreMvpView moreMvpView;

    private final DataManager mDataManager;
    private Subscription mSubscription;

    private List<Ribot> mCachedRibots;

    @Inject
    public MorePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MoreMvpView mvpView) {
        moreMvpView = mvpView;
    }

    @Override
    public void detachView() {
        moreMvpView = null;
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void intentToSuggestion()
    {
        Activity act = ((MoreFragment) moreMvpView).getActivity();

        Intent intent = new Intent(act, SuggestionActivity.class);

        act.startActivity(intent);
    }

    public void intentToSetting()
    {
        Activity act = ((MoreFragment) moreMvpView).getActivity();

        Intent intent = new Intent(act, SettingActivity.class);

        act.startActivity(intent);
    }
}
