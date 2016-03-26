package com.aishang.app.ui.MyCard;

import com.aishang.app.data.model.JMemberBankAccount;
import com.aishang.app.data.model.JMyVacationListResult;
import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/2/15.
 */
public interface MyCardMvpView extends MvpView {
  void showError(String error);

  void refreshList(JMyVacationListResult.JMyVacationListMyVaList[] items);

  void showEmpty();

  void refreshCompleted();
}
