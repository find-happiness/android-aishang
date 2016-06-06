package com.aishang.app.ui.TravelList;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JSysZoneResult;
import com.aishang.app.data.model.News;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.NetWorkType;
import com.aishang.app.util.NetworkUtil;
import com.aishang.app.util.ViewUtil;
import com.aishang.app.widget.ClearEditText;
import com.aishang.app.widget.SpacesItemDecoration;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;
import java.util.List;
import javax.inject.Inject;

public class TravelListActivity extends BaseActivity implements TravelListMvpView {

  private static final String TAG = "TravelListActivity";
  @Inject TravelListPresenter mPersenter;

  @Bind(R.id.toolbar) Toolbar toolbar;
  //@Bind(R.id.searchbox) SearchBox searchBox;
  @Bind(R.id.swipe_refresh) XRecyclerView mRecyclerView;
  @Bind(R.id.avloadingIndicatorView) AVLoadingIndicatorView avloadingIndicatorView;
  @Bind(R.id.no_data_in_sale) TextView noDataInSale;
  @Bind(R.id.layoutRoot) FrameLayout layoutRoot;
  @Bind(R.id.edit_search) ClearEditText editSearch;

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


  @Override public void onBackPressed() {
    super.onBackPressed();
    CommonUtil.hideSoftInput(this);
  }

  private void setImageSizeToAdapter() {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    this.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    int mScreenWidth = localDisplayMetrics.widthPixels;
    int pacing = ViewUtil.dpToPx(8);
    int width = (mScreenWidth - 2 * pacing);
    adapter.setImgSize(width, width * 2 / 3);
  }

  private void initToolbar() {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    toolbar.setNavigationIcon(R.mipmap.iconfont_livesvg);
    initSearch();
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onBackPressed();
      }
    });
  }

  private void initRefreshLayout() {
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    mRecyclerView.setLayoutManager(layoutManager);

    mRecyclerView.addItemDecoration(new SpacesItemDecoration(
        this.getResources().getDimensionPixelSize(R.dimen.spacing_medium)));

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
          asynTravel(adapter.getItemCount(), selectZoneID, 2, 1, 0, "", filterWords, 1, 10,
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

  private void initSearch() {
    editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
          CommonUtil.hideSoftInput(TravelListActivity.this);

          if (!TextUtils.equals(v.getText(), filterWords)) {
            filterWords = v.getText().toString().trim();
            proLoad();
          }

          return true;
        }
        return false;
      }
    });
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
    asynTravel(0, selectZoneID, 2, 1, 0, "", filterWords, 1, 10, type);
  }

  private void asynTravel(int recStart, int zoneID, int catID, int filterTypeID, int beVIPHome,
      String tagIDs, String fKeyWords, int fOrderType, int recCount, NetWorkType type) {
    String json =
        AiShangUtil.generNewsParam(recStart, zoneID, catID, filterTypeID, beVIPHome, tagIDs,
            fKeyWords, fOrderType, recCount);

    mPersenter.loadTravel(1, json, type);
  }

  @Override public void refreshList(List<News> newses) {
    if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
      avloadingIndicatorView.setVisibility(View.GONE);
    }

    if (noDataInSale.getVisibility() == View.VISIBLE) {
      noDataInSale.setVisibility(View.GONE);
    }

    // Log.i(TAG, "refreshHotel: " + items.size());

    adapter.getItems().clear();
    adapter.getItems().addAll(newses);
    adapter.notifyDataSetChanged();
    mRecyclerView.refreshComplete();
  }

  @Override public void loadMoreList(List<News> loupanProducts, int total) {

    if (noDataInSale.getVisibility() == View.VISIBLE) {
      noDataInSale.setVisibility(View.GONE);
    }

    // Log.i(TAG, "loadMoreHotel: " + items.size() + "  total " + total);
    mRecyclerView.loadMoreComplete();
    adapter.getItems().addAll(loupanProducts);
    adapter.notifyDataSetChanged();
    //mRecyclerView.refreshComplete();

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
