package com.aishang.app.ui.AccountCenter;

import com.aishang.app.data.model.JAwardDetailListV2Result;
import com.aishang.app.data.model.JCommonIntegralResult;
import com.aishang.app.ui.base.MvpView;
import java.util.List;

/**
 * Created by song on 2016/2/15.
 */
public interface AwardDetailMvpView extends MvpView {
  void showError(String error);

  void showLoadMore(List<JAwardDetailListV2Result.DataListBean> dataList,int total);

  void showRefresh(List<JAwardDetailListV2Result.DataListBean> dataList);

  void showEmpty();
}
