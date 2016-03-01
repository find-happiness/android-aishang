package com.aishang.app.ui.bank;

import com.aishang.app.data.model.JMemberBankAccount;
import com.aishang.app.data.model.JMemberBankListResult;
import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/2/15.
 */
public interface BankListMvpView extends MvpView {
  void showError(String error);

  void showSuccess(JMemberBankAccount[] bankAccounts);

  void showEmpty();
}
