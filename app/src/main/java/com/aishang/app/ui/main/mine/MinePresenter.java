package com.aishang.app.ui.main.mine;

import android.app.Activity;
import android.content.Intent;

import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.Ribot;
import com.aishang.app.ui.AccountCenter.AccountCenterActivity;
import com.aishang.app.ui.BrokerCenter.BrokerCenterActivity;
import com.aishang.app.ui.CashWithDrawApply.CashWithDrawApplyActivity;
import com.aishang.app.ui.MemberCenter.MemberCenterActivity;
import com.aishang.app.ui.MyBuyAndSale.BuyAndSaleActivity;
import com.aishang.app.ui.MyHouse.MyHouseActivity;
import com.aishang.app.ui.MyOrder.MyOrderActivity;
import com.aishang.app.ui.TravelFavorites.TravelFavoritesActivity;
import com.aishang.app.ui.about.AboutActivity;
import com.aishang.app.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by song on 2016/1/16.
 */
public class MinePresenter extends BasePresenter<MineMvpView> {


    private MineMvpView mineMvpView;

    private final DataManager mDataManager;
    private Subscription mSubscription;

    private List<Ribot> mCachedRibots;

    @Inject
    public MinePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MineMvpView mvpView) {
        mineMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mineMvpView = null;
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void intentToMemberCenter()
    {
        Activity act = ((MineFragment) mineMvpView).getActivity();

        Intent intent = new Intent(act, MemberCenterActivity.class);

        act.startActivity(intent);
    }

    public void intentToAccoutCenter()
    {
        Activity act = ((MineFragment) mineMvpView).getActivity();

        Intent intent = new Intent(act, AccountCenterActivity.class);

        act.startActivity(intent);
    }

    public void intentToMyOrder()
    {
        Activity act = ((MineFragment) mineMvpView).getActivity();

        Intent intent = new Intent(act, MyOrderActivity.class);

        act.startActivity(intent);
    }

    public void intentToMyHouse()
    {
        Activity act = ((MineFragment) mineMvpView).getActivity();

        Intent intent = new Intent(act, MyHouseActivity.class);

        act.startActivity(intent);
    }

    public void intentToBrokerCenter()
    {
        Activity act = ((MineFragment) mineMvpView).getActivity();

        Intent intent = new Intent(act, BrokerCenterActivity.class);

        act.startActivity(intent);
    }

    public void intentToTravelFavorites()
    {
        Activity act = ((MineFragment) mineMvpView).getActivity();

        Intent intent = new Intent(act, TravelFavoritesActivity.class);

        act.startActivity(intent);
    }

    public void intentToCashWithDrawApply()
    {
        Activity act = ((MineFragment) mineMvpView).getActivity();

        Intent intent = new Intent(act, CashWithDrawApplyActivity.class);

        act.startActivity(intent);
    }

    public void intentToMyBuyAndSale()
    {
        Activity act = ((MineFragment) mineMvpView).getActivity();

        Intent intent = new Intent(act, BuyAndSaleActivity.class);

        act.startActivity(intent);
    }
    public void intentToAbout()
    {
        Activity act = ((MineFragment) mineMvpView).getActivity();

        Intent intent = new Intent(act, AboutActivity.class);

        act.startActivity(intent);
    }
}
