package com.aishang.app.ui.income;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.data.model.JCheckinRecordResult;
import com.aishang.app.data.model.JMyVacationListResult;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.Constants;
import com.aishang.app.util.NetworkUtil;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;

public class IncomeActivity extends BaseActivity implements IncomeMvpView {

  private static final String ROOM_GUID = "roomGUID";
  @Bind(R.id.toolbar_title) TextView toolbarTitle;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.XRecyclerView) com.jcodecraeer.xrecyclerview.XRecyclerView mRecyclerView;
  @Bind(R.id.avloadingIndicatorView) AVLoadingIndicatorView avloadingIndicatorView;
  @Bind(R.id.no_data_hotel) TextView noDataHotel;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;
  @Inject IncomePresenter presenter;
  @Inject IncomeAdapter adapter;

  public static Intent getStartIntent(String roomGUID, Context ctx) {

    Intent intent = new Intent(ctx, IncomeActivity.class);

    intent.putExtra(ROOM_GUID, roomGUID);

    return intent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    presenter.attachView(this);
    setContentView(R.layout.activity_my_house);
    ButterKnife.bind(this);
    initToolbar();
    initRefreshLayout();
    proLoad();
  }

  @Override protected void onDestroy() {
    presenter.detachView();
    super.onDestroy();
  }

  private void initToolbar() {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    toolbar.setNavigationIcon(R.mipmap.iconfont_livesvg);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onBackPressed();
      }
    });
  }

  @Override public void showError(String errorStr) {

    if (Constants.RESULT_MBNOTEXISTORPSWERROR.equals(errorStr)) {// 用户名或密码错误

      CommonUtil.showSnackbar(R.string.login_nameorpsw_wrong, layoutRoot);
    } else if (Constants.RESULT_MBLOCKED.equals(errorStr)) {// 用户被锁定

      // Toast.makeText(this, R.string.login_memberLocked,
      // Toast.LENGTH_LONG)
      // .show();
      CommonUtil.showSnackbar(R.string.login_memberLocked, layoutRoot);
    } else if (Constants.RESULT_MBNOTACTIVED.equals(errorStr)) {
      CommonUtil.showSnackbar(R.string.login_memberNotActived, layoutRoot);
      // Toast.makeText(this, R.string.login_memberNotActived,
      // Toast.LENGTH_LONG).show();
    } else if (Constants.RESULT_MBCHANGEPSW.equals(errorStr)) {
      //intentToChangePsw();
      Snackbar.make(layoutRoot, R.string.login_changePassword, Snackbar.LENGTH_LONG)
          .setAction(R.string.dialog_action_ok, new View.OnClickListener() {
            @Override public void onClick(View v) {
              presenter.intentToChangePsw();
            }
          })
          .setDuration(Snackbar.LENGTH_LONG)
          .show();
    } else {
      //BaseHelper.toastError(this, errorStr);
      CommonUtil.showSnackbar(errorStr, layoutRoot);
    }
  }

  @Override public void showEmpty() {
    if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
      avloadingIndicatorView.setVisibility(View.GONE);
    }

    if (noDataHotel.getVisibility() != View.VISIBLE) {
      noDataHotel.setVisibility(View.VISIBLE);
    }

    mRecyclerView.refreshComplete();
    adapter.getItems().clear();

    adapter.notifyDataSetChanged();
  }

  @Override public void refreshList(List<JCheckinRecordResult.CheckinRecordListBean> items) {

    if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
      avloadingIndicatorView.setVisibility(View.GONE);
    }

    if (noDataHotel.getVisibility() == View.VISIBLE) {
      noDataHotel.setVisibility(View.GONE);
    }

    adapter.getItems().clear();
    adapter.getItems().addAll(items);
    adapter.notifyDataSetChanged();
    mRecyclerView.refreshComplete();
  }

  @Override
  public void loadMoreList(List<JCheckinRecordResult.CheckinRecordListBean> items, int total) {

    //if (noDataHotel.getVisibility() == View.VISIBLE) {
    //  noDataHotel.setVisibility(View.GONE);
    //}
    //
    //mRecyclerView.loadMoreComplete();
    //adapter.getItems()
    //    .addAll(new ArrayList<JMyBusinessBuyInListResult.BuyIn>(Arrays.asList(items)));
    //adapter.notifyDataSetChanged();
    ////mRecyclerView.refreshComplete();
    //
    //if (adapter.getItems().size() >= total) {
    //  adapter.notifyDataSetChanged();
    //  mRecyclerView.loadMoreComplete();
    //}
  }

  private void proLoad() {
    if (NetworkUtil.isNetworkConnected(this)) {
      if (avloadingIndicatorView.getVisibility() != View.VISIBLE) {
        avloadingIndicatorView.setVisibility(View.VISIBLE);
      }

      if (noDataHotel.getVisibility() == View.VISIBLE) {
        noDataHotel.setVisibility(View.GONE);
      }

      asynMyVacationList();
    } else {

      if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
        avloadingIndicatorView.setVisibility(View.GONE);
      }

      if (noDataHotel.getVisibility() == View.VISIBLE) {
        noDataHotel.setVisibility(View.GONE);
      }
      CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
    }
  }

  private void initRefreshLayout() {
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    mRecyclerView.setLayoutManager(layoutManager);

    mRecyclerView.addItemDecoration(
        new HorizontalDividerItemDecoration.Builder(this).colorResId(android.R.color.transparent)
            .sizeResId(R.dimen.spacing_small)
            .build());
    mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallScaleRipple);
    mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
    //mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
    //
    //View header = LayoutInflater.from(this)
    //    .inflate(R.layout.recyclerview_header, (ViewGroup) findViewById(android.R.id.content),
    //        false);
    // mRecyclerView.addHeaderView(header);

    mRecyclerView.setLoadingMoreEnabled(false);
    mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
      @Override public void onRefresh() {
        if (NetworkUtil.isNetworkConnected(IncomeActivity.this)) {
          asynMyVacationList();
        } else {
          mRecyclerView.refreshComplete();
          CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
        }
      }

      @Override public void onLoadMore() {
        //if (NetworkUtil.isNetworkConnected(MyHouseActivity.this)) {
        //  asynMyVacationList();
        //} else {
        //  //mRecyclerView.loadMoreComplete();
        //  mRecyclerView.cancelLoadMore();
        //  CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
        //}
      }
    });
    mRecyclerView.setAdapter(adapter);
  }

  private void asynMyVacationList() {

    String roomGUID = this.getIntent().getStringExtra(ROOM_GUID);
    String json = AiShangUtil.generCheckinRecordParam(roomGUID);

    presenter.loadCheckinRecord(0, json);
  }
}
