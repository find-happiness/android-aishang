package com.aishang.app.ui.TravelList;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JNewsListResult;
import com.aishang.app.data.model.JSysZoneResult;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.NetWorkType;
import com.aishang.app.util.NetworkUtil;
import com.aishang.app.util.ViewUtil;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import java.util.List;
import javax.inject.Inject;

public class TravelListActivity extends BaseActivity implements TravelListMvpView {

  private static final String TAG = "TravelListActivity";
  @Inject TravelListPresenter mPersenter;

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.searchbox) SearchBox searchBox;
  @Bind(R.id.swipe_refresh) XRecyclerView mRecyclerView;
  @Bind(R.id.avloadingIndicatorView) AVLoadingIndicatorView avloadingIndicatorView;
  @Bind(R.id.no_data_in_sale) TextView noDataInSale;
  @Bind(R.id.layoutRoot) FrameLayout layoutRoot;

  private String filterWords = "";

  @Inject TravelAdapter adapter;

  private int selectZoneID = 0;
  private int selectType;
  private int selectPrice;
  private Dialog progressDialog;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    setContentView(R.layout.activity_travel_list);
    ButterKnife.bind(this);
    mPersenter.attachView(this);
    initToolbar();
    initRefreshLayout();
    setImageSizeToAdapter();
    proLoad();
  }

  @Override protected void onDestroy() {
    mPersenter.detachView();
    super.onDestroy();
  }

  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_hotel, menu);
    return true;
  }

  private void setImageSizeToAdapter() {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    this.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    int mScreenWidth = localDisplayMetrics.widthPixels;
    int pacing = ViewUtil.dpToPx(8);
    int width = (mScreenWidth - 4 * pacing) / 3;
    adapter.setImgSize(width, width * 3 / 4);
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
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        openSearch();
        return true;
      }
    });
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
        if (NetworkUtil.isNetworkConnected(TravelListActivity.this)) {
          asynTravel(NetWorkType.refresh);
        } else {
          mRecyclerView.refreshComplete();
          CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
        }
      }

      @Override public void onLoadMore() {
        if (NetworkUtil.isNetworkConnected(TravelListActivity.this)) {
          asynTravel(adapter.getItemCount(), selectZoneID, 0, 0, 0, "", filterWords, 1, 10,
              NetWorkType.loadMore);
        } else {
          //mRecyclerView.loadMoreComplete();
          mRecyclerView.cancelLoadMore();
          CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
        }
      }
    });
    mRecyclerView.setAdapter(adapter);
  }

  public void openSearch() {
    searchBox.revealFromMenuItem(R.id.action_search, this);
    searchBox.setSearchListener(new SearchBox.SearchListener() {

      @Override public void onSearchOpened() {
        // Use this to tint the screen
        searchBox.setSearchString(filterWords);
      }

      @Override public void onSearchClosed() {
        // Use this to un-tint the screen
        closeSearch();
      }

      @Override public void onSearchTermChanged(String term) {
        // React to the search term changing
        // Called after it has updated results
      }

      @Override public void onSearch(String searchTerm) {
        filterWords = searchTerm;
        proLoad();
      }

      @Override public void onSearchEmpty() {
        if (!TextUtils.isEmpty(filterWords)) {
          filterWords = "";
          proLoad();
        }
      }

      @Override public void onResultClick(SearchResult result) {
        //React to result being clicked
      }

      @Override public void onSearchCleared() {
      }
    });
  }

  protected void closeSearch() {
    searchBox.hideCircularly(this);
  }

  private void proLoad() {
    if (NetworkUtil.isNetworkConnected(this)) {
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
    //        String param = AiShangUtil.generLoupanProductParam(0, 0, 0, 0, 10, 0,
    //                0, filterWords, 0, "", "", selectZoneID, 0, selectPrice, 0,
    //                0, selectType, "", "", "", 0);
    asynTravel(0, selectZoneID, 0, 0, 0, "", filterWords, 1, 10, type);
  }

  private void asynTravel(int recStart, int zoneID, int catID, int filterTypeID, int beVIPHome,
      String tagIDs, String fKeyWords, int fOrderType, int recCount, NetWorkType type) {
    String json =
        AiShangUtil.generNewsParam(recStart, zoneID, catID, filterTypeID, beVIPHome, tagIDs,
            fKeyWords, fOrderType, recCount);

    mPersenter.loadTravel(1, json, type);
  }

  @Override public void refreshList(List<JNewsListResult.JNewsListItem> loupanProducts) {
    if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
      avloadingIndicatorView.setVisibility(View.GONE);
    }

    if (noDataInSale.getVisibility() == View.VISIBLE) {
      noDataInSale.setVisibility(View.GONE);
    }

    // Log.i(TAG, "refreshHotel: " + items.size());

    adapter.getItems().clear();
    adapter.getItems().addAll(loupanProducts);
    adapter.notifyDataSetChanged();
    mRecyclerView.refreshComplete();
  }

  @Override
  public void loadMoreList(List<JNewsListResult.JNewsListItem> loupanProducts, int total) {

    if (noDataInSale.getVisibility() == View.VISIBLE) {
      noDataInSale.setVisibility(View.GONE);
    }

    // Log.i(TAG, "loadMoreHotel: " + items.size() + "  total " + total);
    mRecyclerView.loadMoreComplete();
    adapter.getItems().addAll(loupanProducts);
    adapter.notifyDataSetChanged();
    mRecyclerView.refreshComplete();

    if (adapter.getItems().size() >= total) {
      adapter.notifyDataSetChanged();
      mRecyclerView.loadMoreComplete();
    }
  }

  @Override public void showSysZoneDialog(final List<JSysZoneResult.Zone> zones) {

    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }

    //Log.i(TAG, "showSysZoneDialog: " + zones.size());
    final String[] items = new String[zones.size() + 1];
    items[0] = this.getString(R.string.unlimited);
    int cur = 0;
    int index = 0;
    for (JSysZoneResult.Zone zone : zones) {
      items[index + 1] = zone.getName();
      if (selectZoneID == zone.getZoneID()) {
        cur = index + 1;
      }
      index++;
    }
    //Log.i(TAG, "showSysZoneDialog: item size :" + items.length);
    DialogFactory.createSingleChoiceDialog(this, items, cur, new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialog, int which) {

        int bakZoneID = selectZoneID;

        selectZoneID = which == 0 ? 2 : zones.get(which - 1).getZoneID();
        dialog.dismiss();

        if (bakZoneID != selectZoneID) proLoad();
      }
    }, getString(R.string.zone_select)).show();
  }

  @Override public void showError(String error) {
    if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
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

  //@OnClick(R.id.tv_zone) void onClickZone() {
  //  progressDialog = DialogFactory.createProgressDialog(this, R.string.listview_loading);
  //  progressDialog.show();
  //  mPersenter.loadZone(false, 0, AiShangUtil.gennerSysZone(2));
  //}
}
