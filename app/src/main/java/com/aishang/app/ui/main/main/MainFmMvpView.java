package com.aishang.app.ui.main.main;

import com.aishang.app.data.model.JLoupanProductListResult;
import com.aishang.app.data.model.JMrePromResult;
import com.aishang.app.ui.base.MvpView;
import java.util.List;

/**
 * Created by song on 2016/1/16.
 */
public interface MainFmMvpView extends MvpView {

  void showBanner(List<JMrePromResult.Ad> ads);

  void showLoupan(List<JLoupanProductListResult.Product> products,
      List<JLoupanProductListResult.Loupan> loupans);

  void showHotel();

  void showTrave();

  void showBannerEmpty();

  void showLoupanEmpty();

  void showHotelEmpty();

  void showError(String error);
}
