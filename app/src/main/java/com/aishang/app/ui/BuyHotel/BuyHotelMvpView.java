package com.aishang.app.ui.BuyHotel;
import com.aishang.app.data.model.JMemberProfileResult;
import com.aishang.app.data.model.JMyVacationListResult;
import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/2/15.
 */
public interface BuyHotelMvpView extends MvpView{
  void showError(String error);
  void showSuccess();
  void showGetProfileSuccess(JMemberProfileResult result);
  void showGetVacationSuccess(JMyVacationListResult result);
  void showVacationEmpty();
}