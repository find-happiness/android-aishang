package com.aishang.app.ui.hotel;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.R;
import com.aishang.app.data.model.JHotelListResult;
import com.aishang.app.data.model.JHotelPriceCatListResult;
import com.aishang.app.data.model.JHotelStarLevelListResult;
import com.aishang.app.data.model.JSysZoneResult;
import com.aishang.app.data.model.JTagListResult;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.NetWorkType;
import com.aishang.app.util.NetworkUtil;
import com.aishang.app.widget.ClearEditText;
import com.aishang.app.widget.SpacesItemDecoration;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class HotelListActivity extends BaseActivity implements HotelMvpView {

  private static final String TAG = "HotelListActivity";
  private static final String ZONE_ID = "zone_id";
  private static final String FILTER_WORDS = "FilterWords";
  //@Bind(R.id.searchbox) SearchBox search;
  @Bind(R.id.toolbar) Toolbar toolbar;

  @Inject HotelPresenter presenter;
  //@Bind(R.id.toolbar_title) TextView toolbarTitle;
  @Bind(R.id.tv_price) TextView tvPrice;
  @Bind(R.id.swipe_refresh) XRecyclerView mRecyclerView;
  @Bind(R.id.avloadingIndicatorView) AVLoadingIndicatorView avloadingIndicatorView;
  @Bind(R.id.no_data_hotel) TextView noDataHotel;
  @Bind(R.id.layoutRoot) CoordinatorLayout layoutRoot;
  @Bind(R.id.edit_search) ClearEditText editSearch;

  private int selectZoneID = 1;
  private int selectPrice;
  private int selectStarLevel;
  private int selectType = 0;
  private Date checkInDate;
  private Date checkOutDate;
  private String mFilterWords = "";

  @Inject HotelAdapter hotelAdapter;
  private Dialog progressDialog;

  public static Intent getIntent(Activity activity, int zoneID) {
    Intent intent = new Intent(activity, HotelListActivity.class);
    intent.putExtra(ZONE_ID, zoneID);
    return intent;
  }

  public static Intent getIntent(Activity activity, String filterWords) {
    Intent intent = new Intent(activity, HotelListActivity.class);
    intent.putExtra(FILTER_WORDS, filterWords);
    return intent;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    presenter.attachView(this);
    setContentView(R.layout.activity_hotel_list);
    ButterKnife.bind(this);

    checkInDate = new Date(System.currentTimeMillis() + 86400000L);
    checkOutDate = new Date(System.currentTimeMillis() + 2 * 86400000L);
    selectZoneID = this.getIntent().getIntExtra(ZONE_ID, 1);
    mFilterWords = this.getIntent().getStringExtra(FILTER_WORDS);

    //Log.i(TAG, "onCreate: selectZoneID----------------------->" + selectZoneID);
    hotelAdapter.setCheckInDate(checkInDate);
    hotelAdapter.setCheckOutDate(checkOutDate);
    initToolbar();
    initRefreshLayout();
    proLoad();
  }

  private void initToolbar() {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    initSearch();
    toolbar.setNavigationIcon(R.mipmap.iconfont_livesvg);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onBackPressed();
      }
    });
  }

  private void initSearch() {
    editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
          CommonUtil.hideSoftInput(HotelListActivity.this);

          if (!TextUtils.equals(v.getText(), mFilterWords)) {
            mFilterWords = v.getText().toString().trim();
            proLoad();
          }
          return true;
        }
        return false;
      }
    });
  }

  @Override public void onBackPressed() {
    super.onBackPressed();

    CommonUtil.hideSoftInput(HotelListActivity.this);
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

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.detachView();
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
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
              selectType, NetWorkType.loadMore);
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
        selectZoneID, selectPrice, mFilterWords, selectType, type);
  }

  private void asynHotel(int start, String checkinDate, String chinkOutDate, int selectZoneID,
      int priceID, String filterWords, int selectType, NetWorkType type) {
    String json =
        AiShangUtil.generHotelParam(0, filterWords, start, 10, 0, checkinDate, chinkOutDate,
            selectZoneID, 0, priceID, 0, 0, 0, "", 0, "\\," + selectType, 0, 1);

    presenter.loadHotel(1, json, type);
  }

  @OnClick(R.id.tv_position) void onZoneClick() {
    progressDialog = DialogFactory.createProgressDialog(this, R.string.listview_loading);
    progressDialog.show();
    presenter.loadZone(false, 0, AiShangUtil.gennerSysZone(2));
  }

  @OnClick(R.id.tv_star_level) void onTypelick() {
    progressDialog = DialogFactory.createProgressDialog(this, R.string.listview_loading);
    progressDialog.show();
    //presenter.loadStarLevelList(false, 0);

    presenter.loadType(1, AiShangUtil.generTagListParam(2, 0, 9, 0));
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
    //mRecyclerView.refreshComplete();
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

    //Log.i(TAG, "showSysZoneDialog: " + starListEntities.size());
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

        if (bakSelectStarLevel != selectStarLevel) proLoad();
      }
    }, getString(R.string.star_level_select)).show();
  }

  @Override public void showSyncTypeDialog(final JTagListResult.Tag[] catListEntities) {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }

    //Log.i(TAG, "showSysZoneDialog: " + catListEntities.size());
    final String[] items = new String[catListEntities.length + 1];
    items[0] = this.getString(R.string.unlimited);
    int cur = 0;
    int index = 0;
    for (JTagListResult.Tag cat : catListEntities) {
      items[index + 1] = cat.getName();
      if (selectType == cat.getId()) {
        cur = index + 1;
      }
      index++;
    }
    //Log.i(TAG, "showSysZoneDialog: item size :" + items.length);
    DialogFactory.createSingleChoiceDialog(this, items, cur, new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialog, int which) {
        int bakSelectType = selectType;
        selectType = which == 0 ? 0 : catListEntities[which - 1].getId();
        dialog.dismiss();

        if (bakSelectType != selectType) proLoad();
      }
    }, getString(R.string.type_select)).show();
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
