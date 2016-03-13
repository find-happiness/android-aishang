package com.aishang.app.ui.TravelFavorites;

import com.aishang.app.data.model.JNewsListResult;
import com.aishang.app.data.model.JSysZoneResult;
import com.aishang.app.data.model.MyTravel;
import com.aishang.app.ui.base.MvpView;
import java.util.List;

/**
 * Created by song on 2016/3/13.
 */
public interface TravelFavoritesMvpView extends MvpView {
  void refreshListRelease(List<MyTravel> items, int total);

  void loadMoreListRelease(List<MyTravel> items, int total);

  void showError(String error);

  void showEmpty();

  void refreshListCollect(List<MyTravel> items, int total);

  void loadMoreListCollect(List<MyTravel> items, int total);

  void refreshListParticipation(List<MyTravel> items, int total);

  void loadMoreListParticipation(List<MyTravel> items, int total);
}
