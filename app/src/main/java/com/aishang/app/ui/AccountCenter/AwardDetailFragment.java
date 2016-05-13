package com.aishang.app.ui.AccountCenter;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.data.model.JAwardDetailListV2Result;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.NetWorkType;
import com.aishang.app.util.NetworkUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AwardDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AwardDetailFragment extends Fragment implements AwardDetailMvpView {

  private static final int REC_COUNT = 10;
  private static final String TAG = "AwardDetailFragment";
  @Bind(R.id.recyclerView) XRecyclerView recyclerView;
  @Bind(R.id.no_data) TextView noData;

  @Inject AwardDetailPresenter presenter;
  @Bind(R.id.layoutRoot) FrameLayout layoutRoot;
  @Bind(R.id.swipe_refresh) SwipeRefreshLayout swipeRefreshLayout;

  @Inject AwardDetailAdapter adapter;



  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @return A new instance of fragment AwardDetailFragment.
   */
  public static AwardDetailFragment newInstance() {
    AwardDetailFragment fragment = new AwardDetailFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  public AwardDetailFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((AccountCenterActivity) this.getActivity()).getActivityComponent().inject(this);
    presenter.attachView(this);
    if (getArguments() != null) {

    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_award_detail, container, false);
    ButterKnife.bind(this, view);
    initRefreshLayout();
    initSwipeRefresh();
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    autoRefresh();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
    presenter.detachView();
  }

  @Override public void showError(String error) {
    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override
  public void showLoadMore(List<JAwardDetailListV2Result.DataListBean> dataList, int total) {
    noData.setVisibility(View.GONE);
    Log.i(TAG, "showLoadMore: " + adapter.getItemCount() +"   " + dataList.size());
    recyclerView.loadMoreComplete();
    adapter.addData(new ArrayList<JAwardDetailListV2Result.DataListBean>(dataList));
    adapter.notifyDataSetChanged();

    if (adapter.getItemCount() >= total) {

      recyclerView.loadMoreComplete();
      adapter.notifyDataSetChanged();
      recyclerView.loadMoreComplete();
    }
  }

  @Override public void showRefresh(List<JAwardDetailListV2Result.DataListBean> dataList) {
    noData.setVisibility(View.GONE);

    if (swipeRefreshLayout.isRefreshing()) swipeRefreshLayout.setRefreshing(false);
    adapter.clearDate();
    adapter.addData(dataList);
    adapter.notifyDataSetChanged();
    recyclerView.refreshComplete();
  }

  @Override public void showEmpty() {
    noData.setVisibility(View.VISIBLE);
    if (swipeRefreshLayout.isRefreshing()) swipeRefreshLayout.setRefreshing(false);
  }

  private void autoRefresh() {
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        refreshData();
      }
    }, 100);
  }

  private void initRefreshLayout() {
    LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(layoutManager);

    recyclerView.addItemDecoration(
        new HorizontalDividerItemDecoration.Builder(this.getActivity()).colorResId(
            android.R.color.transparent).sizeResId(R.dimen.spacing_medium).build());

    recyclerView.setPullRefreshEnabled(false);
    recyclerView.setLoadingMoreEnabled(true);

    recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
      @Override public void onRefresh() {

      }

      @Override public void onLoadMore() {

        if (swipeRefreshLayout.isRefreshing()) {
          return;
        }

        if (NetworkUtil.isNetworkConnected(AwardDetailFragment.this.getActivity())) {
          getAwardDetail(NetWorkType.loadMore, adapter.getItemCount() / REC_COUNT + 1);
        } else {
          recyclerView.cancelLoadMore();
          showError(AwardDetailFragment.this.getString(R.string.no_net));
        }
      }
    });

    recyclerView.setAdapter(adapter);
  }

  private void initSwipeRefresh() {
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        refreshData();
      }
    });
  }

  private void refreshData() {
    if (NetworkUtil.isNetworkConnected(this.getActivity())) {
      swipeRefreshLayout.setRefreshing(true);
      getAwardDetail(NetWorkType.refresh);
    } else {
      if (swipeRefreshLayout.isRefreshing()) swipeRefreshLayout.setRefreshing(false);
      showError(this.getString(R.string.no_net));
    }
  }

  private void getAwardDetail(NetWorkType type) {
    getAwardDetail(type, 1);
  }

  private void getAwardDetail(NetWorkType type, int start) {
    JMemberLoginResult result =
        BoilerplateApplication.get(this.getActivity()).getMemberLoginResult();
    String json = AiShangUtil.generAwardDetailListV2Param(
        BoilerplateApplication.get(this.getActivity()).getMemberAccount(),
        result.getData().getCookies(), start, REC_COUNT, "", "", true);

    presenter.loadData(1, json, type);
  }
}
