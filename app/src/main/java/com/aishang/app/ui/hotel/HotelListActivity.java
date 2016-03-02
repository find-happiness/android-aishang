package com.aishang.app.ui.hotel;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.R;
import com.aishang.app.data.model.JHotelListResult;
import com.aishang.app.data.model.JHotelPriceCatListResult;
import com.aishang.app.data.model.JHotelStarLevelListResult;
import com.aishang.app.data.model.JSysZoneResult;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.NetWorkType;
import com.aishang.app.util.NetworkUtil;
import com.aishang.app.widget.SpacesItemDecoration;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.inject.Inject;

public class HotelListActivity extends BaseActivity implements HotelMvpView {

  private static final String TAG = "HotelListActivity";
  @Bind(R.id.searchbox) SearchBox search;
  @Bind(R.id.toolbar) Toolbar toolbar;

  @Inject HotelPresenter presenter;
  @Bind(R.id.toolbar_title) TextView toolbarTitle;
  @Bind(R.id.tv_price) TextView tvPrice;
  @Bind(R.id.swipe_refresh) XRecyclerView mRecyclerView;
  @Bind(R.id.avloadingIndicatorView) AVLoadingIndicatorView avloadingIndicatorView;
  @Bind(R.id.no_data_hotel) TextView noDataHotel;
  @Bind(R.id.layoutRoot) CoordinatorLayout layoutRoot;

  private int selectZoneID = 1;
  private int selectPrice;
  private int selectStarLevel;
  private Date checkInDate;
  private Date checkOutDate;
  private String mFilterWords = "";

  @Inject HotelAdapter hotelAdapter;
  private Dialog progressDialog;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    presenter.attachView(this);
    setContentView(R.layout.activity_hotel_list);
    ButterKnife.bind(this);

    checkInDate = new Date(System.currentTimeMillis() + 86400000L);
    checkOutDate = new Date(System.currentTimeMillis() + 2 * 86400000L);

    hotelAdapter.setCheckInDate(checkInDate);
    hotelAdapter.setCheckOutDate(checkOutDate);
    initToolbar();
    initRefreshLayout();
    proLoad();
  }

  private void initToolbar() {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        Log.i(TAG, "onMenuItemClick: menu click" + item.getItemId());
        if (!search.getSearchOpen()) {
          openSearch();
          return true;
        }
        return false;
      }
    });

    toolbar.setNavigationIcon(R.mipmap.iconfont_livesvg);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onBackPressed();
      }
    });
  }

  @Override public void onBackPressed() {
    if (search.getSearchOpen()) {
      closeSearch();
    } else {
      super.onBackPressed();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_hotel, menu);
    return true;
  }

  private void proLoad() {
    if (NetworkUtil.isNetworkConnected(this)) {
      if (avloadingIndicatorView.getVisibility() != View.VISIBLE) {
        avloadingIndicatorView.setVisibility(View.VISIBLE);
      }

      if (noDataHotel.getVisibility() == View.VISIBLE) {
        noDataHotel.setVisibility(View.GONE);
      }

      asynHotel(NetWorkType.refresh);
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

  public void openSearch() {

    search.revealFromMenuItem(R.id.action_search, this);
    //    for (int x = 0; x < 10; x++) {
    //      SearchResult option = new SearchResult("Result "
    //          + Integer.toString(x), getResources().getDrawable(
    //          R.mipmap.ic_history));
    //      search.addSearchable(option);
    //    }
    //search.setMenuListener(new SearchBox.MenuListener() {
    //
    //  @Override public void onMenuClick() {
    //    // Hamburger has been clicked
    //    Toast.makeText(HotelListActivity.this, "Menu click", Toast.LENGTH_LONG).show();
    //  }
    //});
    search.setSearchListener(new SearchBox.SearchListener() {

      @Override public void onSearchOpened() {
        // Use this to tint the screen
        if (!TextUtils.isEmpty(mFilterWords.trim())) search.setSearchString(mFilterWords);
      }

      @Override public void onSearchClosed() {
        // Use this to un-tint the screen
        closeSearch();

        String searchTerm = search.getSearchText().trim();
        if (TextUtils.isEmpty(searchTerm)) {
          mFilterWords = "";
        }
      }

      @Override public void onSearchTermChanged(String term) {
        // React to the search term changing
        // Called after it has updated results
      }

      @Override public void onSearch(String searchTerm) {
        mFilterWords = searchTerm;
        proLoad();
      }

      @Override public void onResultClick(SearchResult result) {
        //React to result being clicked
      }

      @Override public void onSearchCleared() {
      }
    });
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == 1234 && resultCode == RESULT_OK) {
      ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
      search.populateEditText(matches.get(0));
    }
    super.onActivityResult(requestCode, resultCode, data);
  }

  protected void closeSearch() {
    search.hideCircularly(this);
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
        if (NetworkUtil.isNetworkConnected(HotelListActivity.this)) {
          asynHotel(NetWorkType.refresh);
        } else {
          mRecyclerView.refreshComplete();
          CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
        }
      }

      @Override public void onLoadMore() {
        if (NetworkUtil.isNetworkConnected(HotelListActivity.this)) {
          asynHotel(hotelAdapter.getHotels().size(), AiShangUtil.dateFormat(checkInDate),
              AiShangUtil.dateFormat(checkOutDate), selectZoneID, selectPrice, mFilterWords,
              NetWorkType.loadMore);
        } else {
          //mRecyclerView.loadMoreComplete();
          mRecyclerView.cancelLoadMore();
          CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
        }
      }
    });
    mRecyclerView.setAdapter(hotelAdapter);
  }

  private void asynHotel(NetWorkType type) {
    //String json = AiShangUtil.generHotelParam(0, "", 0, 10, 0, AiShangUtil.gennerCheckinData(),
    //    AiShangUtil.gennerCheckoutData(), selectZoneID, 0, 0, 0, 0, 0, "", 0, "", 0, 1);

    //presenter.loadHotel(1, json);

    asynHotel(0, AiShangUtil.dateFormat(checkInDate), AiShangUtil.dateFormat(checkOutDate),
        selectZoneID, selectPrice, mFilterWords, type);
  }

  private void asynHotel(int start, String checkinDate, String chinkOutDate, int selectZoneID,
      int priceID, String filterWords, NetWorkType type) {
    String json =
        AiShangUtil.generHotelParam(0, filterWords, start, 10, 0, checkinDate, chinkOutDate,
            selectZoneID, 0, priceID, 0, 0, 0, "", 0, "", 0, 1);

    presenter.loadHotel(1, json, type);
  }

  @OnClick(R.id.tv_position) void onZoneClick() {
    progressDialog = DialogFactory.createProgressDialog(this, R.string.listview_loading);
    progressDialog.show();
    presenter.loadZone(false, 0, AiShangUtil.gennerSysZone(2));
  }

  @OnClick(R.id.tv_star_level) void onStarLevelClick() {
    progressDialog = DialogFactory.createProgressDialog(this, R.string.listview_loading);
    progressDialog.show();
    presenter.loadStarLevelList(false, 0);
  }

  @OnClick(R.id.tv_price) void priceClick() {
    progressDialog = DialogFactory.createProgressDialog(this, R.string.listview_loading);
    progressDialog.show();

    presenter.loadPriceCatList(false, 1);
  }

  @Override public void refreshHotel(List<JHotelListResult.Hotel> hotels) {

    if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
      avloadingIndicatorView.setVisibility(View.GONE);
    }

    if (noDataHotel.getVisibility() == View.VISIBLE) {
      noDataHotel.setVisibility(View.GONE);
    }

    Log.i(TAG, "refreshHotel: " + hotels.size());

    hotelAdapter.getHotels().clear();
    hotelAdapter.getHotels().addAll(hotels);
    hotelAdapter.notifyDataSetChanged();
    mRecyclerView.refreshComplete();
  }

  @Override public void loadMoreHotel(List<JHotelListResult.Hotel> hotels, int total) {

    if (noDataHotel.getVisibility() == View.VISIBLE) {
      noDataHotel.setVisibility(View.GONE);
    }
    mRecyclerView.loadMoreComplete();
    hotelAdapter.getHotels().addAll(hotels);
    hotelAdapter.notifyDataSetChanged();
    mRecyclerView.refreshComplete();
    Log.i(TAG,
        "loadMoreHotel: " + hotels.size() + "  total " + total + "  " + hotelAdapter.getHotels()
            .size());

    if (hotelAdapter.getHotels().size() >= total) {
      mRecyclerView.loadMoreComplete();
      hotelAdapter.notifyDataSetChanged();
      mRecyclerView.loadMoreComplete();
    }
  }

  @Override public void showSysZoneDialog(final List<JSysZoneResult.Zone> zones) {

    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }

    Log.i(TAG, "showSysZoneDialog: " + zones.size());
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
    Log.i(TAG, "showSysZoneDialog: item size :" + items.length);
    DialogFactory.createSingleChoiceDialog(this, items, cur, new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialog, int which) {

        int bakZoneID = selectZoneID;

        selectZoneID = which == 0 ? 2 : zones.get(which - 1).getZoneID();
        dialog.dismiss();

        if (bakZoneID != selectZoneID) proLoad();
      }
    }, getString(R.string.zone_select)).show();
  }

  @Override public void showSysPriceDialog(final List<JHotelPriceCatListResult.Cat> prices) {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }

    Log.i(TAG, "showSysZoneDialog: " + prices.size());
    final String[] items = new String[prices.size() + 1];
    items[0] = this.getString(R.string.unlimited);
    int cur = 0;
    int index = 0;
    for (JHotelPriceCatListResult.Cat cat : prices) {
      items[index + 1] = cat.getName();
      if (selectPrice == cat.getId()) {
        cur = index + 1;
      }
      index++;
    }
    Log.i(TAG, "showSysZoneDialog: item size :" + items.length);
    DialogFactory.createSingleChoiceDialog(this, items, cur, new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialog, int which) {

        int bakSelectPrice = selectPrice;
        selectPrice = which == 0 ? 0 : prices.get(which - 1).getId();
        dialog.dismiss();

        if (bakSelectPrice != selectPrice) proLoad();
      }
    }, getString(R.string.price_select)).show();
  }

  @Override public void showSyncStarLevelDialog(
      final List<JHotelStarLevelListResult.StarListEntity> starListEntities) {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }

    Log.i(TAG, "showSysZoneDialog: " + starListEntities.size());
    final String[] items = new String[starListEntities.size() + 1];
    items[0] = this.getString(R.string.unlimited);
    int cur = 0;
    int index = 0;
    for (JHotelStarLevelListResult.StarListEntity cat : starListEntities) {
      items[index + 1] = cat.getName();
      if (selectStarLevel == cat.getId()) {
        cur = index + 1;
      }
      index++;
    }
    Log.i(TAG, "showSysZoneDialog: item size :" + items.length);
    DialogFactory.createSingleChoiceDialog(this, items, cur, new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialog, int which) {

        int bakSelectStarLevel = selectStarLevel;
        selectStarLevel = which == 0 ? 0 : starListEntities.get(which - 1).getId();
        dialog.dismiss();

        if (bakSelectStarLevel != selectPrice) proLoad();
      }
    }, getString(R.string.star_level_select)).show();
  }

  @Override public void showError(String error) {
    if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
  }

  @Override public void showHotelEmpty() {
    if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
      avloadingIndicatorView.setVisibility(View.GONE);
    }

    if (noDataHotel.getVisibility() != View.VISIBLE) {
      noDataHotel.setVisibility(View.VISIBLE);
    }

    mRecyclerView.refreshComplete();
    hotelAdapter.getHotels().clear();
    hotelAdapter.notifyDataSetChanged();
  }
}
