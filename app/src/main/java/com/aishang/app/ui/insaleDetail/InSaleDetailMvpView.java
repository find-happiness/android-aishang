package com.aishang.app.ui.insaleDetail;

import com.aishang.app.data.model.JLoupanProductDetailResult;
import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/1/19.
 */
public interface InSaleDetailMvpView extends MvpView{
  void showError(String error);
  void showLoupanProductDetailError(String error);
  void bindDataToView(JLoupanProductDetailResult result);
}
