package com.aishang.app.ui.ExchangeHouse;

import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/2/15.
 */
public interface ExchangeHouseMvpView extends MvpView{
  void showError(String error);
  void showSuccess();
}
