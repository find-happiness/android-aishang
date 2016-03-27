package com.aishang.app.ui.ExchangeHouse;

import com.aishang.app.data.model.JHotelRoomFacilitesCatListResult;
import com.aishang.app.ui.base.MvpView;
import java.util.List;

/**
 * Created by song on 2016/2/15.
 */
public interface ExchangeHouseMvpView extends MvpView {
  void showError(String error);

  void showSuccess();

  void showFacilites(
      List<JHotelRoomFacilitesCatListResult.HotelRoomFacilitesCatListEntity> entitys);

  void showEmptyFacilite();

  void showNetProgress();

  void dimissNetProgress();
}
