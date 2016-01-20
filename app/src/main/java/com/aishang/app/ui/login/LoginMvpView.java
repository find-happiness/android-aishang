package com.aishang.app.ui.login;

import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/1/20.
 */
public interface LoginMvpView  extends MvpView{
  void login();
  void showError(String error);
  void loginScuess();
}
