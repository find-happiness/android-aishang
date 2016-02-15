package com.aishang.app.ui.MyOrder;

import com.aishang.app.data.model.JMyVacationApplyListResult;
import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/2/15.
 */
public interface MyOrderMvpView extends MvpView{
  void showError(String error);
  void showEmpty();
  void refreshList(JMyVacationApplyListResult.JItem[] items);
  void loadMoreList(JMyVacationApplyListResult.JItem[] items,int total);
}
