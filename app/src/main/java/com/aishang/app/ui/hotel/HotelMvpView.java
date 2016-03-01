package com.aishang.app.ui.hotel;

import com.aishang.app.data.model.JHotelListResult;
import com.aishang.app.data.model.JHotelPriceCatListResult;
import com.aishang.app.data.model.JHotelStarLevelListResult;
import com.aishang.app.data.model.JSysZoneResult;
import com.aishang.app.ui.base.MvpView;
import java.util.List;

/**
 * Created by song on 2016/1/19.
 */
public interface HotelMvpView extends MvpView {
  void refreshHotel(List<JHotelListResult.Hotel> hotels);
  void loadMoreHotel(List<JHotelListResult.Hotel> hotels,int total);
  void showSysZoneDialog(List<JSysZoneResult.Zone> zones);
  void showSysPriceDialog(List<JHotelPriceCatListResult.Cat> prices);
  void showSyncStarLevelDialog(List<JHotelStarLevelListResult.StarListEntity> starListEntities);
  void showError(String error);
  void showHotelEmpty();
}
