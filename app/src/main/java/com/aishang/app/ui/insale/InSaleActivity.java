package com.aishang.app.ui.insale;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.R;
import com.aishang.app.data.model.JLoupanPriceCatListResult;
import com.aishang.app.data.model.JLoupanProductCatListResult;
import com.aishang.app.data.model.JSysZoneResult;
import com.aishang.app.data.model.LoupanProduct;
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
import java.util.List;
import javax.inject.Inject;

public class InSaleActivity extends BaseActivity implements InSaleMvpView {
  private static final String TAG = "InSaleActivity";
  @Inject InSalePresenter mPersenter;

  @Bind(R.id.toolbar) Toolbar toolbar;
  //@Bind(R.id.searchbox) SearchBox searchBox;
  @Bind(R.id.swipe_refresh) XRecyclerView mRecyclerView;
  @Bind(R.id.avloadingIndicatorView) AVLoadingIndicatorView avloadingIndicatorView;
  @Bind(R.id.no_data_in_sale) TextView noDataInSale;
  @Bind(R.id.layoutRoot) FrameLayout layoutRoot;
  @Bind(R.id.edit_search) ClearEditText editSearch;

  private String filterWords = "";

  @Inject LoupanProductAdapter adapter;

  private int selectZoneID = 1;
  private int selectType;
  private int selectPrice;
  private Dialog progressDialog;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    setContentView(R.layout.activity_in_sale);
    ButterKnife.bind(this);
    mPersenter.attachView(this);
    initToolbar();
    initRefreshLayout();
    proLoad();
  }

  @Override public void onBackPressed() {
    super.onBackPressed();
    CommonUtil.hideSoftInput(this);
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
          CommonUtil.hideSoftInput(InSaleActivity.this);

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
        if (NetworkUtil.isNetworkConnected(InSaleActivity.this)) {
          asynLoupanProduct(NetWorkType.refresh);
        } else {
          mRecyclerView.refreshComplete();
          CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
        }
      }

      @Override public void onLoadMore() {
        if (NetworkUtil.isNetworkConnected(InSaleActivity.this)) {
          asynLoupanProduct(adapter.getItemCount(), selectType, selectZoneID, selectPrice,
              filterWords, NetWorkType.loadMore);
        } else {
          //mRecyclerView.loadMoreComplete();
          mRecyclerView.cancelLoadMore();
          CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
        }
      }
    });
    mRecyclerView.setAdapter(adapter);
  }

  @Override protected void onDestroy() {
    mPersenter.detachView();
    super.onDestroy();
  }

  private void proLoad() {
    if (NetworkUtil.isNetworkConnected(this)) {
      if (avloadingIndicatorView.getVisibility() != View.VISIBLE) {
        avloadingIndicatorView.setVisibility(View.VISIBLE);
      }

      if (noDataInSale.getVisibility() == View.VISIBLE) {
        noDataInSale.setVisibility(View.GONE);
      }

      asynLoupanProduct(NetWorkType.refresh);
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

  private void asynLoupanProduct(NetWorkType type) {

    //        String param = AiShangUtil.generLoupanProductParam(0, 0, 0, 0, 10, 0,
    //                0, filterWords, 0, "", "", selectZoneID, 0, selectPrice, 0,
    //                0, selectType, "", "", "", 0);
    asynLoupanProduct(0, selectType, selectZoneID, selectPrice, filterWords, type);
  }

  private void asynLoupanProduct(int start, int selectType, int selectZoneID, int priceID,
      String filterWords, NetWorkType type) {
    String json =
        AiShangUtil.generLoupanProductParam(0, 0, 0, start, 10, 0, 0, filterWords, 0, "", "",
            selectZoneID, 0, priceID, 0, 0, selectType, "", "", "", 0);

    mPersenter.loadLoupanProduct(1, json, type);
  }

  @Override public void refreshLoupanProduct(List<LoupanProduct> loupanProducts) {
    if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
      avloadingIndicatorView.setVisibility(View.GONE);
    }

    if (noDataInSale.getVisibility() == View.VISIBLE) {
      noDataInSale.setVisibility(View.GONE);
    }

    // Log.i(TAG, "refreshHotel: " + hotels.size());

    adapter.getLoupanProducts().clear();
    adapter.getLoupanProducts().addAll(loupanProducts);
    adapter.notifyDataSetChanged();
    mRecyclerView.refreshComplete();
  }

  @Override public void loadMoreLoupanProduct(List<LoupanProduct> loupanProducts, int total) {

    if (noDataInSale.getVisibility() == View.VISIBLE) {
      noDataInSale.setVisibility(View.GONE);
    }

    // Log.i(TAG, "loadMoreHotel: " + hotels.size() + "  total " + total);
    mRecyclerView.loadMoreComplete();
    adapter.getLoupanProducts().addAll(loupanProducts);
    adapter.notifyDataSetChanged();
    //mRecyclerView.refreshComplete();

    Log.i(TAG, "loadMoreHotel: "
        + loupanProducts.size()
        + "  total "
        + total
        + "  "
        + adapter.getItemCount());
    if (adapter.getLoupanProducts().size() >= total) {
      mRecyclerView.loadMoreComplete();
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

  @Override public void showSysPriceDialog(final List<JLoupanPriceCatListResult.Cat> prices) {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }

    //Log.i(TAG, "showSysZoneDialog: " + prices.size());
    final String[] items = new String[prices.size() + 1];
    items[0] = this.getString(R.string.unlimited);
    int cur = 0;
    int index = 0;
    for (JLoupanPriceCatListResult.Cat cat : prices) {
      items[index + 1] = cat.getName();
      if (selectPrice == cat.getId()) {
        cur = index + 1;
      }
      index++;
    }
    //Log.i(TAG, "showSysZoneDialog: item size :" + items.length);
    DialogFactory.createSingleChoiceDialog(this, items, cur, new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialog, int which) {

        int bakSelectPrice = selectPrice;
        selectPrice = which == 0 ? 0 : prices.get(which - 1).getId();
        dialog.dismiss();

        if (bakSelectPrice != selectPrice) proLoad();
      }
    }, getString(R.string.price_select)).show();
  }

  @Override
  public void showLoupanProductCatDialog(final List<JLoupanProductCatListResult.Cat> cats) {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }

    //Log.i(TAG, "showSysZoneDialog: " + prices.size());
    final String[] items = new String[cats.size() + 1];
    items[0] = this.getString(R.string.unlimited);
    int cur = 0;
    int index = 0;
    for (JLoupanProductCatListResult.Cat cat : cats) {
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
        selectType = which == 0 ? 0 : cats.get(which - 1).getId();
        dialog.dismiss();

        if (bakSelectType != selectType) proLoad();
      }
    }, getString(R.string.type_select)).show();
  }

  @Override public void showError(String error) {
    if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
  }

  @Override public void showLoupanProductEmpty() {
    if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
      avloadingIndicatorView.setVisibility(View.GONE);
    }

    if (noDataInSale.getVisibility() != View.VISIBLE) {
      noDataInSale.setVisibility(View.VISIBLE);
    }

    mRecyclerView.refreshComplete();
    adapter.getLoupanProducts().clear();
    adapter.notifyDataSetChanged();
  }

  @OnClick(R.id.tv_zone) void onClickZone() {
    progressDialog = DialogFactory.createProgressDialog(this, R.string.listview_loading);
    progressDialog.show();
    mPersenter.loadZone(false, 0, AiShangUtil.gennerSysZone(2));
  }

  @OnClick(R.id.tv_type) void onClickType() {
    progressDialog = DialogFactory.createProgressDialog(this, R.string.listview_loading);
    progressDialog.show();
    mPersenter.loadLoupanProductCatList(false, 0);
  }

  @OnClick(R.id.tv_price) void onClickPrice() {
    progressDialog = DialogFactory.createProgressDialog(this, R.string.listview_loading);
    progressDialog.show();

    mPersenter.loadPriceCatList(false, 1, AiShangUtil.generLoupanPriceCatListParam(0));
  }

  @OnClick(R.id.tv_tese) void onClickTese() {

  }
}

