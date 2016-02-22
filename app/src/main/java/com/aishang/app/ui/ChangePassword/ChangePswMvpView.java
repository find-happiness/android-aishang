package com.aishang.app.ui.ChangePassword;

import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/2/15.
 */
public interface ChangePswMvpView extends MvpView{
  void showError(String error);
  void showSuccess();
}
