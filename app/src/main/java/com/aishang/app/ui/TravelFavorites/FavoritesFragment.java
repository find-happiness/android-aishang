package com.aishang.app.ui.TravelFavorites;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.data.model.JNewsListResult;
import com.aishang.app.data.model.MyTravel;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.NetWorkType;
import com.aishang.app.util.NetworkUtil;
import com.aishang.app.util.ViewUtil;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;
import com.shizhefei.fragment.LazyFragment;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import java.util.List;
import javax.inject.Inject;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link FavoritesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoritesFragment extends LazyFragment implements TravelFavoritesMvpView {

  @Inject TravelFavoritesPresenter presenter;
  @Inject TravelAdapter adapter;

  private static final String ARG_PARAM1 = "TYPE";
  @Bind(R.id.swipe_refresh) XRecyclerView mRecyclerView;
  @Bind(R.id.avloadingIndicatorView) AVLoadingIndicatorView avloadingIndicatorView;
  @Bind(R.id.no_data_in_sale) TextView noDataInSale;
  @Bind(R.id.layoutRoot) FrameLayout layoutRoot;

  private TravelType mParam1;

  // The request code must be 0 or greater.
  private static final int PLUS_ONE_REQUEST_CODE = 0;

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @return A new instance of fragment FavoritesFragment.
   */
  public static FavoritesFragment newInstance(TravelType type) {
    FavoritesFragment fragment = new FavoritesFragment();
    Bundle bundle = new Bundle();
    if (type == TravelType.participation) {
      bundle.putInt(ARG_PARAM1, 1);
    } else if (type == TravelType.collect) {
      bundle.putInt(ARG_PARAM1, 2);
    } else if (type == TravelType.release) {
      bundle.putInt(ARG_PARAM1, 3);
    }

    fragment.setArguments(bundle);
    return fragment;
  }

  public FavoritesFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ((TravelFavoritesActivity) getActivity()).getActivityComponent().inject(this);
    presenter.attachView(this);
    if (getArguments() != null) {
      switch (getArguments().getInt(ARG_PARAM1)) {
        case 1:
          mParam1 = TravelType.participation;
          break;
        case 2:
          mParam1 = TravelType.collect;
          break;
        case 3:
          mParam1 = TravelType.release;
          break;
      }
    }
  }

  @Override public void onDestroy() {
    presenter.detachView();
    super.onDestroy();
  }

  @Override public void onAttach(Activity context) {
    super.onAttach(context);
  }

  @Override protected void onCreateViewLazy(Bundle savedInstanceState) {
    super.onCreateViewLazy(savedInstanceState);
    setContentView(R.layout.fragment_favorites);
    ButterKnife.bind(this, this.getContentView());
    initRefreshLayout();
    setImageSizeToAdapter();
    proLoad();
  }

  @Override public void onDetach() {
    super.onDetach();
    presenter.detachView();
  }

  private void setImageSizeToAdapter() {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    int mScreenWidth = localDisplayMetrics.widthPixels;
    int pacing = ViewUtil.dpToPx(8);
    int width = (mScreenWidth - 4 * pacing) / 3;
    adapter.setImgSize(width, width * 3 / 4);
  }

  private void initRefreshLayout() {
    LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    mRecyclerView.setLayoutManager(layoutManager);

    mRecyclerView.addItemDecoration(
        new HorizontalDividerItemDecoration.Builder(this.getActivity()).colorResId(
            android.R.color.darker_gray).sizeResId(R.dimen.divider).build());
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
        if (NetworkUtil.isNetworkConnected(FavoritesFragment.this.getActivity())) {
          asynTravel(NetWorkType.refresh);
        } else {
          mRecyclerView.refreshComplete();
          CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
        }
      }

      @Override public void onLoadMore() {
        if (NetworkUtil.isNetworkConnected(FavoritesFragment.this.getActivity())) {
          asynTravel(adapter.getItemCount() + 1, 10, NetWorkType.loadMore);
        } else {
          //mRecyclerView.loadMoreComplete();
          mRecyclerView.cancelLoadMore();
          CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
        }
      }
    });
    mRecyclerView.setAdapter(adapter);
  }

  private void proLoad() {
    if (NetworkUtil.isNetworkConnected(this.getActivity())) {
      if (avloadingIndicatorView.getVisibility() != View.VISIBLE) {
        avloadingIndicatorView.setVisibility(View.VISIBLE);
      }

      if (noDataInSale.getVisibility() == View.VISIBLE) {
        noDataInSale.setVisibility(View.GONE);
      }

      asynTravel(NetWorkType.refresh);
    } else {

      if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
        avloadingIndicatorView.setVisibility(View.GONE);
      }

      if (noDataInSale.getVisibility() == View.VISIBLE) {
        noDataInSale.setVisibility(View.GONE);
      }
      CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
    }
  }

  private void asynTravel(NetWorkType type) {
    asynTravel(0, 10, type);
  }

  private void asynTravel(int recStart, int recCount, NetWorkType type) {

    String member = BoilerplateApplication.get(this.getActivity()).getMemberAccount();
    String cookie = BoilerplateApplication.get(this.getActivity())
        .getMemberLoginResult()
        .getData()
        .getCookies();

    if (mParam1 == TravelType.participation) {
      String json = AiShangUtil.generParticipationParam(member, cookie, recCount, recStart);
      presenter.loadTravelParticipation(1, json, type);
    } else if (mParam1 == TravelType.collect) {
      String json = AiShangUtil.generCollectParam(member, cookie, recCount, recStart);
      presenter.loadTravelCollect(1, json, type);
    } else if (mParam1 == TravelType.release) {
      String json = AiShangUtil.generReleaseParam(member, cookie, recCount, recStart);
      presenter.loadTravelRelease(1, json, type);
    }
  }

  @Override public void refreshListCollect(List<MyTravel> items, int total) {
    if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
      avloadingIndicatorView.setVisibility(View.GONE);
    }

    if (noDataInSale.getVisibility() == View.VISIBLE) {
      noDataInSale.setVisibility(View.GONE);
    }

    // Log.i(TAG, "refreshHotel: " + items.size());

    adapter.getItems().clear();
    adapter.getItems().addAll(items);
    adapter.notifyDataSetChanged();
    mRecyclerView.refreshComplete();
    if (adapter.getItems().size() >= total) {
      mRecyclerView.loadMoreComplete();
      adapter.notifyDataSetChanged();
      mRecyclerView.loadMoreComplete();
    }
  }

  @Override public void loadMoreListCollect(List<MyTravel> items, int total) {
    if (noDataInSale.getVisibility() == View.VISIBLE) {
      noDataInSale.setVisibility(View.GONE);
    }

    // Log.i(TAG, "loadMoreHotel: " + items.size() + "  total " + total);
    mRecyclerView.loadMoreComplete();
    adapter.getItems().addAll(items);
    adapter.notifyDataSetChanged();
    //mRecyclerView.refreshComplete();

    if (adapter.getItems().size() >= total) {
      mRecyclerView.loadMoreComplete();
      adapter.notifyDataSetChanged();
      mRecyclerView.loadMoreComplete();
    }
  }

  @Override public void refreshListParticipation(List<MyTravel> items, int total) {
    if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
      avloadingIndicatorView.setVisibility(View.GONE);
    }

    if (noDataInSale.getVisibility() == View.VISIBLE) {
      noDataInSale.setVisibility(View.GONE);
    }

    // Log.i(TAG, "refreshHotel: " + items.size());

    adapter.getItems().clear();
    adapter.getItems().addAll(items);
    adapter.notifyDataSetChanged();
    mRecyclerView.refreshComplete();
    if (adapter.getItems().size() >= total) {
      mRecyclerView.loadMoreComplete();
      adapter.notifyDataSetChanged();
      mRecyclerView.loadMoreComplete();
    }
  }

  @Override public void loadMoreListParticipation(List<MyTravel> items, int total) {
    if (noDataInSale.getVisibility() == View.VISIBLE) {
      noDataInSale.setVisibility(View.GONE);
    }

    // Log.i(TAG, "loadMoreHotel: " + items.size() + "  total " + total);
    mRecyclerView.loadMoreComplete();
    adapter.getItems().addAll(items);
    adapter.notifyDataSetChanged();
    //mRecyclerView.refreshComplete();

    if (adapter.getItems().size() >= total) {
      adapter.notifyDataSetChanged();
      mRecyclerView.loadMoreComplete();
    }
  }

  @Override public void refreshListRelease(List<MyTravel> items, int total) {
    if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
      avloadingIndicatorView.setVisibility(View.GONE);
    }

    if (noDataInSale.getVisibility() == View.VISIBLE) {
      noDataInSale.setVisibility(View.GONE);
    }

    // Log.i(TAG, "refreshHotel: " + items.size());

    adapter.getItems().clear();
    adapter.getItems().addAll(items);
    adapter.notifyDataSetChanged();
    mRecyclerView.refreshComplete();
    if (adapter.getItems().size() >= total) {
      mRecyclerView.loadMoreComplete();
      adapter.notifyDataSetChanged();
      mRecyclerView.loadMoreComplete();
    }
  }

  @Override public void loadMoreListRelease(List<MyTravel> items, int total) {
    if (noDataInSale.getVisibility() == View.VISIBLE) {
      noDataInSale.setVisibility(View.GONE);
    }

    // Log.i(TAG, "loadMoreHotel: " + items.size() + "  total " + total);
    mRecyclerView.loadMoreComplete();
    adapter.getItems().addAll(items);
    adapter.notifyDataSetChanged();
    //mRecyclerView.refreshComplete();

    if (adapter.getItems().size() >= total) {
      mRecyclerView.loadMoreComplete();
      adapter.notifyDataSetChanged();
      mRecyclerView.loadMoreComplete();
    }
  }

  @Override public void showError(String error) {
    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override public void showEmpty() {
    if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
      avloadingIndicatorView.setVisibility(View.GONE);
    }

    if (noDataInSale.getVisibility() != View.VISIBLE) {
      noDataInSale.setVisibility(View.VISIBLE);
    }

    mRecyclerView.refreshComplete();
    adapter.getItems().clear();
    adapter.notifyDataSetChanged();
  }
}
