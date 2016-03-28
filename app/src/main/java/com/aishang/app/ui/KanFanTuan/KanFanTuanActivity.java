package com.aishang.app.ui.KanFanTuan;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.RelativeLayout;
import android.widget.TextView;
import com.aishang.app.R;
import com.aishang.app.data.model.JMreActivityListResult;
import com.aishang.app.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.NetWorkType;
import com.aishang.app.util.NetworkUtil;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import java.util.ArrayList;
import java.util.Arrays;
import javax.inject.Inject;

//TODO 看房团网络请求
public class KanFanTuanActivity extends BaseActivity implements KanFangTuanMvpView {

  @Inject KanFangTuanAdapter adapter;
  @Inject KanFangTuanPresenter presenter;
  @Bind(R.id.toolbar) Toolbar toolbar;

  @Bind(R.id.swipe_refresh) XRecyclerView mRecyclerView;
  @Bind(R.id.avloadingIndicatorView) AVLoadingIndicatorView avloadingIndicatorView;
  @Bind(R.id.no_data_hotel) TextView noDataHotel;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    presenter.attachView(this);

    setContentView(R.layout.activity_kan_fan_tuan);
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
    CommonUtil.showSnackbar(errorStr, layoutRoot);
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

  @Override public void refreshList(JMreActivityListResult.JActivityItem[] items) {

    if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
      avloadingIndicatorView.setVisibility(View.GONE);
    }

    if (noDataHotel.getVisibility() == View.VISIBLE) {
      noDataHotel.setVisibility(View.GONE);
    }

    adapter.getItems().clear();
    adapter.getItems()
        .addAll(new ArrayList<JMreActivityListResult.JActivityItem>(Arrays.asList(items)));
    adapter.notifyDataSetChanged();
    mRecyclerView.refreshComplete();
  }

  @Override public void loadMoreList(JMreActivityListResult.JActivityItem[] items, int total) {

    if (noDataHotel.getVisibility() == View.VISIBLE) {
      noDataHotel.setVisibility(View.GONE);
    }

    mRecyclerView.loadMoreComplete();
    adapter.getItems()
        .addAll(new ArrayList<JMreActivityListResult.JActivityItem>(Arrays.asList(items)));
    adapter.notifyDataSetChanged();
    //mRecyclerView.refreshComplete();

    if (adapter.getItems().size() >= total) {
      mRecyclerView.loadMoreComplete();
      adapter.notifyDataSetChanged();
      mRecyclerView.loadMoreComplete();
    }
  }

  private void proLoad() {
    if (NetworkUtil.isNetworkConnected(this)) {
      if (avloadingIndicatorView.getVisibility() != View.VISIBLE) {
        avloadingIndicatorView.setVisibility(View.VISIBLE);
      }

      if (noDataHotel.getVisibility() == View.VISIBLE) {
        noDataHotel.setVisibility(View.GONE);
      }

      asynActivity(NetWorkType.refresh);
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
        new HorizontalDividerItemDecoration.Builder(this).colorResId(android.R.color.darker_gray)
            .sizeResId(R.dimen.divider)
            .build());
    mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallScaleRipple);
    mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
    //mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
    //
    //View header = LayoutInflater.from(this)
    //    .inflate(R.layout.recyclerview_header, (ViewGroup) findViewById(android.R.id.content),
    //        false);
    // mRecyclerView.addHeaderView(header);

    mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
      @Override public void onRefresh() {
        if (NetworkUtil.isNetworkConnected(KanFanTuanActivity.this)) {
          asynActivity(NetWorkType.refresh);
        } else {
          mRecyclerView.refreshComplete();
          CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
        }
      }

      @Override public void onLoadMore() {
        if (NetworkUtil.isNetworkConnected(KanFanTuanActivity.this)) {
          asynActivity(0, 0, 1, "", "", 0, adapter.getItemCount(), 10, NetWorkType.loadMore);
        } else {
          //mRecyclerView.loadMoreComplete();
          mRecyclerView.cancelLoadMore();
          CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
        }
      }
    });
    mRecyclerView.setAdapter(adapter);
  }

  private void asynActivity(NetWorkType type) {
    //corpID:0,catID:0,beAll:1,memberAccount:'',cookie:'',beEnrolled:0,recStart:" + pageIndex + ", recCount:10
    asynActivity(0, 0, 1, "", "", 0, 0, 10, type);
  }

  private void asynActivity(int corpID, int catID, int beAll, String memberAccount, String cookie,
      int beEnrolled, int recStart, int recCount, NetWorkType type) {

    String json =
        AiShangUtil.gennerMreActivityDetailParam(corpID, catID, beAll, memberAccount, cookie,
            beEnrolled, recStart, recCount);

    presenter.MreActivityDetail(3, json, type);
  }
}
