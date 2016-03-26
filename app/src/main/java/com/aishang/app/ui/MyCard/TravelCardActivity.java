package com.aishang.app.ui.MyCard;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.data.model.JMyVacationListResult;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.NetWorkType;
import com.aishang.app.util.NetworkUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import java.util.ArrayList;
import java.util.Arrays;
import javax.inject.Inject;

public class TravelCardActivity extends BaseActivity implements MyCardMvpView {
  @Inject MyCardPresenter presenter;
  @Inject MyCardAdapter adapter;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.swipe_refresh) SwipeRefreshLayout swipeRefreshLayout;
  @Bind(R.id.avloadingIndicatorView) AVLoadingIndicatorView avloadingIndicatorView;
  @Bind(R.id.no_data_hotel) TextView noDataHotel;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;
  @Bind(R.id.recyclerView) XRecyclerView mRecyclerView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    presenter.attachView(this);
    setContentView(R.layout.activity_travel_card);
    ButterKnife.bind(this);
    initToolbar();
    initSwipeRefresh();
    initRefreshLayout();
    autoRefresh();
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

  private void autoRefresh() {
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        proLoad();
      }
    }, 100);
  }

  private void initSwipeRefresh() {
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        asynMyOrder();
      }
    });
  }

  @Override public void showError(String error) {

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

  @Override public void refreshCompleted() {
    if (swipeRefreshLayout.isRefreshing()) {
      swipeRefreshLayout.setRefreshing(false);
    }
  }

  @Override public void refreshList(JMyVacationListResult.JMyVacationListMyVaList[] items) {

    if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
      avloadingIndicatorView.setVisibility(View.GONE);
    }

    if (noDataHotel.getVisibility() == View.VISIBLE) {
      noDataHotel.setVisibility(View.GONE);
    }

    adapter.getItems().clear();
    adapter.getItems()
        .addAll(new ArrayList<JMyVacationListResult.JMyVacationListMyVaList>(Arrays.asList(items)));
    adapter.notifyDataSetChanged();
    mRecyclerView.refreshComplete();
  }

  private void proLoad() {
    if (NetworkUtil.isNetworkConnected(this)) {
      if (avloadingIndicatorView.getVisibility() != View.VISIBLE) {
        avloadingIndicatorView.setVisibility(View.VISIBLE);
      }

      if (noDataHotel.getVisibility() == View.VISIBLE) {
        noDataHotel.setVisibility(View.GONE);
      }
      asynMyOrder();
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
            .sizeResId(R.dimen.spacing_medium)
            .build());

    mRecyclerView.setLoadingMoreEnabled(false);
    mRecyclerView.setPullRefreshEnabled(false);
    mRecyclerView.setAdapter(adapter);
  }

  private void asynMyOrder() {
    swipeRefreshLayout.setRefreshing(true);
    String cookie = BoilerplateApplication.get(this).getMemberLoginResult().getData().getCookies();
    String member = BoilerplateApplication.get(this).getMemberAccount();
    String json = AiShangUtil.generMyVacationListParam(member, cookie, "2012-01-01");
    presenter.loadData(0, json);
  }
}
