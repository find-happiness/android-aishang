package com.aishang.app.ui.ProjectJoint;

import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/2/15.
 */
public interface ProjectJoinMvpView extends MvpView{
  void showError(String error);
  void showSuccess();
}
