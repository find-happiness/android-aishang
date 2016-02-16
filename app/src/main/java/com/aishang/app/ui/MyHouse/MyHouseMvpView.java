package com.aishang.app.ui.MyHouse;

import com.aishang.app.data.model.JMyBusinessBuyInListResult;
import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/2/15.
 */
public interface MyHouseMvpView extends MvpView {
  void showError(String error);

  void showEmpty();

  void refreshList(JMyBusinessBuyInListResult.BuyIn[] items);

  void loadMoreList(JMyBusinessBuyInListResult.BuyIn[] items, int total);
}
