package com.aishang.app.ui.TravelDetail;

import com.aishang.app.data.model.JNewsDetailResult;
import com.aishang.app.data.model.JNewsListResult;
import com.aishang.app.data.model.JSysZoneResult;
import com.aishang.app.ui.base.MvpView;
import java.util.List;

/**
 * Created by song on 2016/2/17.
 */
public interface TravelDetailMvpView extends MvpView {
  void loadTraveDetailFinish(JNewsDetailResult result);

  void showDialog();

  void showError(String error);

  void dimissDialog();

  void hitFinish();

  void favoriteFinish();

  void criticismFinish();
}
