package com.aishang.app.ui.KanFanTuan;

import com.aishang.app.data.model.JBusinessListResult;
import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/2/15.
 */
public interface KanFangTuanMvpView extends MvpView{
  void showError(String error);
  void showEmpty();
  void refreshList(JBusinessListResult.Business[] items);
  void loadMoreList(JBusinessListResult.Business[] items, int total);
}
