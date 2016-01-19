package com.aishang.app.ui.insaleDetail;

import com.aishang.app.data.DataManager;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.ui.insale.InSaleMvpView;
import javax.inject.Inject;
import rx.Subscription;

/**
 * Created by song on 2016/1/19.
 */
public class InSaleDetailPresenter extends BasePresenter<InSaleDetailMvpView>{
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
}
