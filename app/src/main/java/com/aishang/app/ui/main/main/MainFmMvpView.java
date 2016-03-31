package com.aishang.app.ui.main.main;

import com.aishang.app.data.model.JHotelListResult;
import com.aishang.app.data.model.JLoupanProductListResult;
import com.aishang.app.data.model.JMrePromResult;
import com.aishang.app.data.model.JNewsListResult;
import com.aishang.app.data.model.JSysZoneResult;
import com.aishang.app.data.model.News;
import com.aishang.app.ui.base.MvpView;
import java.util.List;

/**
 * Created by song on 2016/1/16.
 */
public interface MainFmMvpView extends MvpView {

  void showBanner(List<JMrePromResult.Ad> ads);

  void showLoupan(List<JLoupanProductListResult.Product> products,
      List<JLoupanProductListResult.Loupan> loupans);

  void showHotel(List<JHotelListResult.Hotel> hotels);

  void showError(String error);

  void showTrave(List<News> newsList);

  void showTraveEmpty();

  void showBannerEmpty();

  void showLoupanEmpty();

  void showHotelEmpty();

  void addNetCount();

  void showSysZoneDialog(List<JSysZoneResult.Zone> zones);
}
