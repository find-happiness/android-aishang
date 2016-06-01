package com.aishang.app.ui.income;

import com.aishang.app.data.model.JCheckinRecordResult;
import com.aishang.app.data.model.JMyVacationListResult;
import com.aishang.app.ui.base.MvpView;
import java.util.List;

/**
 * Created by song on 2016/2/15.
 */
public interface IncomeMvpView extends MvpView {
  void showError(String error);

  void showEmpty();

  void refreshList(List<JCheckinRecordResult.CheckinRecordListBean> items);

  void loadMoreList(List<JCheckinRecordResult.CheckinRecordListBean> items, int total);
}
