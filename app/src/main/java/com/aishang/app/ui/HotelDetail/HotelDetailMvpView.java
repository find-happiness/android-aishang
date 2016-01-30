package com.aishang.app.ui.HotelDetail;

import com.aishang.app.data.model.JHotelDetailResult;
import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/1/19.
 */
public interface HotelDetailMvpView extends MvpView {
  void showError(String error);
  void showHotelDetailError(String error);
  void bindDataToView(JHotelDetailResult result);
}
