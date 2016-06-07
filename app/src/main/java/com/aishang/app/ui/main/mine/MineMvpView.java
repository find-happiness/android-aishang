package com.aishang.app.ui.main.mine;

import com.aishang.app.data.model.JMemberProfileResult;
import com.aishang.app.data.model.JMemberStatisticsResult;
import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/1/16.
 */
public interface MineMvpView extends MvpView {
  void updataMember(JMemberProfileResult result);
  void showError(String error);
  void loginFaild(String str);
}
