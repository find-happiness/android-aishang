package com.aishang.app.ui.TradeCenter;

import com.aishang.app.data.model.JLoupanProductVIPViewResult;
import com.aishang.app.ui.base.MvpView;
import java.util.List;

/**
 * Created by song on 2016/4/12.
 */
public interface TradeSaleMvpView extends MvpView {
  void showDialog();

  void dimissDialog();

  void showError(String error);

  void bindLoupan(List<JLoupanProductVIPViewResult.ZoneListBean.LoupanListBean> loupanBeans);
}
