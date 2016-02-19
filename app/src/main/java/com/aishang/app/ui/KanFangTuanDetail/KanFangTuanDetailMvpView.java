package com.aishang.app.ui.KanFangTuanDetail;

import com.aishang.app.data.model.JLoupanProductDetailResult;
import com.aishang.app.data.model.JMreActivityDetailResult;
import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/1/19.
 */
public interface KanFangTuanDetailMvpView extends MvpView{
  void showError(String error);
  void showKanFangTuanDetailError(String error);
  void bindDataToView(JMreActivityDetailResult result);
}
