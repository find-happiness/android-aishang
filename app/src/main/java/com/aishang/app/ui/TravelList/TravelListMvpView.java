package com.aishang.app.ui.TravelList;

import com.aishang.app.data.model.JNewsListResult;
import com.aishang.app.data.model.JSysZoneResult;
import com.aishang.app.data.model.LoupanProduct;
import com.aishang.app.data.model.News;
import com.aishang.app.ui.base.MvpView;
import java.util.List;

/**
 * Created by song on 2016/2/17.
 */
public interface TravelListMvpView extends MvpView {
  void refreshList(List<News> items);

  void loadMoreList(List<News> items, int total);

  void showSysZoneDialog(List<JSysZoneResult.Zone> zones);

  void showError(String error);

  void showEmpty();
}
