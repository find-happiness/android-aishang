package com.aishang.app.ui.main.more;

import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/1/16.
 */
public interface MoreMvpView extends MvpView {
  public void showError(String error);

  public void updata(String url);

  public void noUpdata();

  void dimissDialog();
}
