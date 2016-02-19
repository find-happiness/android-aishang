package com.aishang.app.ui.CanTuan;

import com.aishang.app.data.model.JBusinessListResult;
import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/2/15.
 */
public interface CanTuanMvpView extends MvpView{
  void showError(String error);
  void showSuccess();
}
