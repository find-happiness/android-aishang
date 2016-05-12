package com.aishang.app.ui.CashWithDrawApply;

import com.aishang.app.data.model.JMemberBankAccount;
import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/2/16.
 */
public interface CashWithDrawApplyMvpView extends MvpView {
  void showError(String error);

  void showSuccess();

  void loadBankSuccess(JMemberBankAccount[] banks);

  void loadBankEmpty();
}
