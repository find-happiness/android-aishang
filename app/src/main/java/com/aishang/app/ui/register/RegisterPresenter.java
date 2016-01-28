package com.aishang.app.ui.register;

import com.aishang.app.data.DataManager;
import com.aishang.app.ui.base.BasePresenter;
import javax.inject.Inject;
import rx.Subscription;

/**
 * Created by song on 2016/1/28.
 */
public class RegisterPresenter extends BasePresenter<RegisterMvpView> {

  private final DataManager mDataManager;
  private Subscription mSubscription;

  @Inject public RegisterPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }
  
  @Override public void attachView(RegisterMvpView mvpView) {
    super.attachView(mvpView);
  }

  @Override public void detachView() {
    super.detachView();
  }
}
