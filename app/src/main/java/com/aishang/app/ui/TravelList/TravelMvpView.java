package com.aishang.app.ui.TravelList;

import com.aishang.app.data.model.JBusinessListResult;
import com.aishang.app.data.model.JNewsListResult;
import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/2/16.
 */
public interface TravelMvpView extends MvpView {
  void showError(String error);

  void showEmpty();

  void refreshList(JNewsListResult.JNewsListItem[] items);

  void loadMoreList(JNewsListResult.JNewsListItem[] items, int total);
}
