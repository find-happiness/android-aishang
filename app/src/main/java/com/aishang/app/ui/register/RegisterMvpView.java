package com.aishang.app.ui.register;

import com.aishang.app.data.model.JMemberBankAccount;
import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/1/28.
 */
public interface RegisterMvpView extends MvpView {
  void showError(String error);

  void showCheckPhoneSuccess();

  void dismissDialog();

  void showRegisterSuccess();

  void showNetDialog();

  void showGetCodeError(String error);
}
