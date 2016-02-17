package com.aishang.app.ui.KanFanTuan;

import com.aishang.app.data.model.JMreActivityListResult;
import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/2/15.
 */
public interface KanFangTuanMvpView extends MvpView{
  void showError(String error);
  void showEmpty();
  void refreshList(JMreActivityListResult.JActivityItem[] items);
  void loadMoreList(JMreActivityListResult.JActivityItem[] items, int total);
}
