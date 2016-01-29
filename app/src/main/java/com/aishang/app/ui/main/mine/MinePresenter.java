package com.aishang.app.ui.main.mine;

import android.app.Activity;
import android.content.Intent;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JMemberProfileResult;
import com.aishang.app.data.model.JMemberStatisticsResult;
import com.aishang.app.data.model.Ribot;
import com.aishang.app.ui.AccountCenter.AccountCenterActivity;
import com.aishang.app.ui.BrokerCenter.BrokerCenterActivity;
import com.aishang.app.ui.CashWithDrawApply.CashWithDrawApplyActivity;
import com.aishang.app.ui.ChangePassword.ChangePasswordActivity;
import com.aishang.app.ui.MemberCenter.MemberCenterActivity;
import com.aishang.app.ui.MyBuyAndSale.BuyAndSaleActivity;
import com.aishang.app.ui.MyHouse.MyHouseActivity;
import com.aishang.app.ui.MyOrder.MyOrderActivity;
import com.aishang.app.ui.TravelFavorites.TravelFavoritesActivity;
import com.aishang.app.ui.about.AboutActivity;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.ui.login.LoginActivity;
import com.aishang.app.ui.register.RegisterActivity;
import com.aishang.app.util.Constants;
import java.util.List;
import javax.inject.Inject;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by song on 2016/1/16.
 */
public class MinePresenter extends BasePresenter<MineMvpView> {

  private final DataManager mDataManager;
  private Subscription mSubscription;
  private List<Ribot> mCachedRibots;

  @Inject public MinePresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(MineMvpView mvpView) {
    super.attachView(mvpView);
  }

  @Override public void detachView() {
    super.detachView();
    if (mSubscription != null) mSubscription.unsubscribe();
  }

  public void loadProfile(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (mSubscription != null && !mSubscription.isUnsubscribed()) {
      mSubscription.unsubscribe();
    }

    mSubscription = mDataManager.syncMemberStatistics(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JMemberStatisticsResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常:" + e.toString());
          }

          @Override public void onNext(JMemberStatisticsResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().updataMember(result);
            } else {
              getMvpView().loginFaild(result.getResult());
            }
          }
        });
  }

  public void intentToChangePsw() {
    Activity act = ((MineFragment) getMvpView()).getActivity();
    Intent intent = new Intent(act, ChangePasswordActivity.class);
    act.startActivity(intent);
  }

  public void intentToMemberCenter() {
    Activity act = ((MineFragment) getMvpView()).getActivity();

    Intent intent = new Intent(act, MemberCenterActivity.class);

    act.startActivity(intent);
  }

  public void intentToAccoutCenter() {
    Activity act = ((MineFragment) getMvpView()).getActivity();

    Intent intent = new Intent(act, AccountCenterActivity.class);

    act.startActivity(intent);
  }

  public void intentToMyOrder() {
    Activity act = ((MineFragment) getMvpView()).getActivity();

    Intent intent = new Intent(act, MyOrderActivity.class);

    act.startActivity(intent);
  }

  public void intentToMyHouse() {
    Activity act = ((MineFragment) getMvpView()).getActivity();

    Intent intent = new Intent(act, MyHouseActivity.class);

    act.startActivity(intent);
  }

  public void intentToBrokerCenter() {
    Activity act = ((MineFragment) getMvpView()).getActivity();

    Intent intent = new Intent(act, BrokerCenterActivity.class);

    act.startActivity(intent);
  }

  public void intentToTravelFavorites() {
    Activity act = ((MineFragment) getMvpView()).getActivity();

    Intent intent = new Intent(act, TravelFavoritesActivity.class);

    act.startActivity(intent);
  }

  public void intentToCashWithDrawApply() {
    Activity act = ((MineFragment) getMvpView()).getActivity();

    Intent intent = new Intent(act, CashWithDrawApplyActivity.class);

    act.startActivity(intent);
  }

  public void intentToMyBuyAndSale() {
    Activity act = ((MineFragment) getMvpView()).getActivity();

    Intent intent = new Intent(act, BuyAndSaleActivity.class);

    act.startActivity(intent);
  }

  public void intentToAbout() {
    Activity act = ((MineFragment) getMvpView()).getActivity();

    Intent intent = new Intent(act, AboutActivity.class);

    act.startActivity(intent);
  }

  public void intentToLogin() {
    Activity act = ((MineFragment) getMvpView()).getActivity();

    Intent intent = new Intent(act, LoginActivity.class);

    act.startActivity(intent);
  }

  public void intentToRegister() {
    Activity act = ((MineFragment) getMvpView()).getActivity();

    Intent intent = new Intent(act, RegisterActivity.class);

    act.startActivity(intent);
  }
}
