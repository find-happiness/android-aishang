package com.aishang.app.ui.BrokerCenter;

import com.aishang.app.data.model.JBusinessListResult;
import com.aishang.app.data.model.JMyVacationApplyListResult;
import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/2/15.
 */
public interface BrokerCenterMvpView extends MvpView{
  void showError(String error);
  void showEmpty();
  void refreshList(JBusinessListResult.Business[] items);
  void loadMoreList(JBusinessListResult.Business[] items, int total);
}
