package com.aishang.app.ui.Setting;

import com.aishang.app.data.model.JMemberBankAccount;
import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/2/15.
 */
public interface SettingMvpView extends MvpView {
  void showError(String error);

  void showSuccess();
}
