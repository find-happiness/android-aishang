package com.aishang.app.ui.MyBuyAndSale;

import com.aishang.app.data.model.JRentalListResult;
import com.aishang.app.ui.base.MvpView;
import java.util.List;

/**
 * Created by song on 2016/2/15.
 */
public interface RentSaleMvpView extends MvpView {
  void showError(String error);

  void refreshList(List<JRentalListResult.RentalItem> items);

  void showEmpty();

  void loadMoreList(List<JRentalListResult.RentalItem> items, int total);
}
