package com.aishang.app.ui.main.main;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.R;
import com.aishang.app.data.model.JHotelListResult;
import com.aishang.app.data.model.JLoupanProductListResult;
import com.aishang.app.data.model.JMrePromParam;
import com.aishang.app.data.model.JMrePromResult;
import com.aishang.app.data.model.JNewsListResult;
import com.aishang.app.data.model.JSysZoneResult;
import com.aishang.app.data.model.LoupanProduct;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.HotelDetail.HotelDetailActivity;
import com.aishang.app.ui.TravelDetail.TravelDetailActivity;
import com.aishang.app.ui.insaleDetail.InSaleDetailActivity;
import com.aishang.app.ui.main.MainActivity;
import com.aishang.app.ui.main.mine.MineFragment;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.NetworkUtil;
import com.aishang.app.widget.NonScrollGridView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/1/16.
 */
public class MainFmFragment extends Fragment implements MainFmMvpView {
  enum AdMode {
    NEWS("NEWS"), LOUPAN("LOUPAN"), HOTEL("HOTEL"), OTHER("OTHER");

    private AdMode(String mode) {
      this.mode = mode;
    }

    public String toString() {
      return this.mode;
    }

    private String mode; // 自定义数据域，private为了封装。
  }

  private static final String TAG = "MainFmFragment";
  private static final int NET_COUNT = 4;

  @Inject MainFmPresenter mMainPresenter;

  HotHotelAdapter hotHotelAdapter;
  HotYouJiAdapter hotYouJiAdapter;
  ZaiShouAdapter zaiShouAdapter;

  @Bind(R.id.convenientBanner) ConvenientBanner banner;

  @Bind(R.id.gridview_in_sale) NonScrollGridView gvInSale;
  @Bind(R.id.gridview_hot_hotel) NonScrollGridView gvHotHotel;
  @Bind(R.id.gridview_hot_youji) NonScrollGridView gvYouJi;

  //@Bind(R.id.main_scrollview) ScrollView scrollView;

  @Bind(R.id.edit_search) EditText editSearch;
  @Bind(R.id.avloadingIndicatorView_in_sale) AVLoadingIndicatorView avloadingIndicatorViewInSale;
  @Bind(R.id.no_data_in_sale) TextView noDataInSale;
  @Bind(R.id.avloadingIndicatorView_hotel) AVLoadingIndicatorView avloadingIndicatorViewHotel;
  @Bind(R.id.no_data_hotel) TextView noDataHotel;
  @Bind(R.id.avloadingIndicatorView_youji) AVLoadingIndicatorView avloadingIndicatorViewYouji;
  @Bind(R.id.no_data_youji) TextView noDataYouji;
  @Bind(R.id.swipe_refresh) SwipeRefreshLayout swipeRefresh;
  @Bind(R.id.layoutRoot) CoordinatorLayout layoutRoot;

  private Dialog progressDialog;

  private int network = 0;

  private int selectZoneID = 1;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((MainActivity) getActivity()).getActivityComponent().inject(this);
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  @Override public void onDestroy() {
    mMainPresenter.detachView();
    super.onDestroy();
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    View view = inflater.inflate(R.layout.fragment_main, null);
    ButterKnife.bind(this, view);
    mMainPresenter.attachView(this);
    initView();
    autoRefresh();
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    //scrollView.scrollTo(0, 0);
  }

  @Override public void onResume() {
    super.onResume();
    banner.startTurning(5000);
  }

  @Override public void onPause() {
    super.onPause();
    banner.stopTurning();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @OnClick(R.id.insale_more) void onclickInSaleMore() {
    mMainPresenter.IntentToZaiShou();
  }

  @OnClick(R.id.hotel_more) void onClickHotelMore() {
    mMainPresenter.IntentToHuanZu();
  }

  @OnClick(R.id.travel_more) void onClickTravelMore() {
    mMainPresenter.IntentToTravelList();
  }

  @OnClick(R.id.btn_zaishou) void OnZaiShouClick() {
    mMainPresenter.IntentToZaiShou();
  }

  @OnClick(R.id.btn_huanzu) void onHuanZuClick() {
    mMainPresenter.IntentToHuanZu();
  }

  @OnClick(R.id.btn_youji) void onYouJiClick() {
    mMainPresenter.IntentToTravelList();
  }

  @OnClick(R.id.btn_xiangmuhezu) void onXiangMuHeZuoClick() {
    mMainPresenter.IntentToProjectJoint();
  }

  @OnClick(R.id.btn_dujiawu) void btnDuJiaWu() {
    mMainPresenter.IntentToChangeHouse();
  }

  @OnClick(R.id.btn_store) void btnStore() {
  }

  @OnClick(R.id.btn_kanfangtuan) void btnKanFangDuan() {
    mMainPresenter.IntentToKanFanTuan();
  }

  @OnClick(R.id.btn_fabuchuzu) void btnFaBuChuZu() {
  }

  @OnClick(R.id.btn_city) void onClickCity() {
    progressDialog = DialogFactory.createProgressDialog(getActivity(), R.string.listview_loading);
    progressDialog.show();

    mMainPresenter.loadZone(false, 0, AiShangUtil.gennerSysZone(2));
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
    DialogFactory.createSingleChoiceDialog(getActivity(), items, cur,
        new DialogInterface.OnClickListener() {
          @Override public void onClick(DialogInterface dialog, int which) {

            int bakZoneID = selectZoneID;

            selectZoneID = which == 0 ? 2 : zones.get(which - 1).getZoneID();
            dialog.dismiss();

            if (bakZoneID != selectZoneID) autoRefresh();
          }
        }, getString(R.string.zone_select)).show();
  }

  @Override public void showBanner(final List<JMrePromResult.Ad> ads) {
    banner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
      @Override public LocalImageHolderView createHolder() {
        return new LocalImageHolderView();
      }
    }, ads)
        //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        .setPageIndicator(new int[] { R.mipmap.ellipse_nomal, R.mipmap.ellipse_select })
            //设置指示器的方向
        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);

    banner.setOnItemClickListener(new OnItemClickListener() {
      @Override public void onItemClick(int position) {

        String mode = ads.get(position).getModel().toUpperCase();
        String param = ads.get(position).getParam();
        Activity activity = MainFmFragment.this.getActivity();
        if (mode.equals(AdMode.HOTEL.toString())) {
          Intent intent = HotelDetailActivity.getStartIntent(activity, Integer.parseInt(param),
              ads.get(position).getParam2(), System.currentTimeMillis() + 86400000L,
              System.currentTimeMillis() + 2 * 86400000L);
          activity.startActivity(intent);
        } else if (mode.equals(AdMode.LOUPAN.toString())) {
          Intent intent = InSaleDetailActivity.getStartIntent(activity, Integer.parseInt(param),
              ads.get(position).getParam2());
          activity.startActivity(intent);
        } else if (mode.equals(AdMode.NEWS.toString())) {
          Intent intent = TravelDetailActivity.getStartIntent(activity, Integer.parseInt(param),
              ads.get(position).getParam2());
          activity.startActivity(intent);
        } else if (mode.equals(AdMode.OTHER.toString())) {
          Intent intent = new Intent();
          intent.setAction("android.intent.action.VIEW");
          if (param.startsWith("http://")) {
            intent.setData(Uri.parse(param));
          } else {
            intent.setData(Uri.parse("http://" + param));
          }
          activity.startActivity(intent);
        }
      }
    });
  }

  @Override public void showLoupan(List<JLoupanProductListResult.Product> products,
      List<JLoupanProductListResult.Loupan> loupans) {
    avloadingIndicatorViewInSale.setVisibility(View.GONE);
    noDataInSale.setVisibility(View.GONE);
    zaiShouAdapter = new ZaiShouAdapter(getContext());
    List<LoupanProduct> loupanProducts = new ArrayList<>();
    for (JLoupanProductListResult.Loupan l : loupans) {
      for (JLoupanProductListResult.Product p : products) {
        if (p.getLoupanID() == l.getLoupanID()) {
          LoupanProduct lp = new LoupanProduct(p, l);
          loupanProducts.add(lp);
        }
      }
    }
    zaiShouAdapter.setLoupanProducts(loupanProducts);
    gvInSale.setAdapter(zaiShouAdapter);
  }

  @Override public void showHotel(List<JHotelListResult.Hotel> hotels) {

    avloadingIndicatorViewHotel.setVisibility(View.GONE);
    noDataHotel.setVisibility(View.GONE);
    hotHotelAdapter = new HotHotelAdapter(getActivity());
    hotHotelAdapter.setHotels(hotels);
    gvHotHotel.setAdapter(hotHotelAdapter);
  }

  @Override public void showTrave(List<JNewsListResult.JNewsListItem> newsList) {
    avloadingIndicatorViewYouji.setVisibility(View.GONE);
    noDataYouji.setVisibility(View.GONE);
    hotYouJiAdapter = new HotYouJiAdapter(getContext());
    hotYouJiAdapter.setHotYouJis(newsList);
    gvYouJi.setAdapter(hotYouJiAdapter);
  }

  @Override public void showTraveEmpty() {
    avloadingIndicatorViewYouji.setVisibility(View.GONE);
    noDataYouji.setVisibility(View.VISIBLE);
    hotYouJiAdapter.clearData();
    hotYouJiAdapter.notifyDataSetChanged();
  }

  @Override public void showBannerEmpty() {

  }

  @Override public void showLoupanEmpty() {
    avloadingIndicatorViewInSale.setVisibility(View.GONE);
    noDataInSale.setVisibility(View.VISIBLE);
    zaiShouAdapter.clearData();
    zaiShouAdapter.notifyDataSetChanged();
  }

  @Override public void showHotelEmpty() {
    avloadingIndicatorViewHotel.setVisibility(View.GONE);
    noDataHotel.setVisibility(View.VISIBLE);
    hotHotelAdapter.clearData();
    hotHotelAdapter.notifyDataSetChanged();
  }

  @Override public void showError(String error) {
    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override public void addNetCount() {
    network++;

    if (network % NET_COUNT == 0) {
      swipeRefresh.setRefreshing(false);
      network = 0;
    }
  }

  private void initView() {
    initBanner();
    initGvHothotel();
    initGvYouJi();
    initGvZaiShou();
    initSearch();
    initSwipeRefresh();
  }

  private void initSwipeRefresh() {
    swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        loadData();
      }
    });
  }

  private void loadData() {
    if (NetworkUtil.isNetworkConnected(getActivity())) {
      swipeRefresh.setRefreshing(true);
      avloadingIndicatorViewInSale.setVisibility(View.VISIBLE);
      avloadingIndicatorViewHotel.setVisibility(View.VISIBLE);
      avloadingIndicatorViewYouji.setVisibility(View.VISIBLE);
      asynBanner();
      asynLoupan();
      asynHotel();
      asynTrvael();
    } else {
      if (swipeRefresh.isRefreshing()) swipeRefresh.setRefreshing(false);
      CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
    }

    //Log.i(TAG, "loadData: " + (swipeRefresh.isRefreshing()));
  }

  private void initSearch() {

    editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_SEARCH == actionId) {
          hideSoftInputWindows();
          return true;
        }
        return false;
      }
    });
  }

  private void hideSoftInputWindows() {
    InputMethodManager inputMethodManager =
        (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
    inputMethodManager.hideSoftInputFromWindow(editSearch.getApplicationWindowToken(), 0);
  }

  private void initBanner() {

    int[] size = CommonUtil.getHeightWithScreenWidth(this.getActivity(), 16, 9);
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size[0], size[1]);

    banner.setLayoutParams(layoutParams);

    //List<Integer> localImages = new ArrayList<>();
    //localImages.add(R.mipmap.banner);
    //localImages.add(R.mipmap.banner);
    //localImages.add(R.mipmap.banner);
    //localImages.add(R.mipmap.banner);
    //
    //banner.setPages(new CBViewHolderCreator<NetImageHolderView>() {
    //  @Override public NetImageHolderView createHolder() {
    //    return new NetImageHolderView();
    //  }
    //}, localImages)
    //    //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
    //    .setPageIndicator(new int[] { R.mipmap.ellipse_nomal, R.mipmap.ellipse_select })
    //        //设置指示器的方向
    //    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
    ////设置翻页的效果，不需要翻页效果可用不设
    ////.setPageTransformer(Transformer.DefaultTransformer);    //集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
    ////        convenientBanner.setManualPageable(false);//设置不能手动影响
    //
    //banner.setOnItemClickListener(new OnItemClickListener() {
    //  @Override public void onItemClick(int position) {
    //    InSaleDetailActivity.navigate((MainActivity) MainFmFragment.this.getActivity(), banner,
    //        new ViewModel("item",
    //            "http://img5.imgtn.bdimg.com/it/u=236513344,827617441&fm=11&gp=0.jpg"));
    //  }
    //});
  }

  private void initGvHothotel() {

    gvHotHotel.setOnTouchListener(new View.OnTouchListener() {
      @Override public boolean onTouch(View v, MotionEvent event) {
        return MotionEvent.ACTION_MOVE == event.getAction();
      }
    });
  }

  private void initGvZaiShou() {
    gvInSale.setOnTouchListener(new View.OnTouchListener() {

      @Override public boolean onTouch(View v, MotionEvent event) {
        return MotionEvent.ACTION_MOVE == event.getAction();
      }
    });
  }

  private void initGvYouJi() {
    gvYouJi.setOnTouchListener(new View.OnTouchListener() {

      @Override public boolean onTouch(View v, MotionEvent event) {
        //return MotionEvent.ACTION_MOVE == event.getAction() ? true
        //        : false;
        return MotionEvent.ACTION_MOVE == event.getAction();
      }
    });
  }

  private void asynBanner() {
    JMrePromParam param = new JMrePromParam();
    param.setLocation(11);
    param.setCount(4);

    mMainPresenter.loadBanner(1, new Gson().toJson(param));
  }

  private void asynLoupan() {
    String json =
        AiShangUtil.generLoupanProductParam(0, 0, 0, 0, 3, 0, 0, "", 0, "", "", selectZoneID, 0, 0,
            0, 0, 0, "", "", "", 0);
    mMainPresenter.loadLoupan(1, json);
  }

  private void asynHotel() {
    String json = AiShangUtil.generHotelParam(0, "", 0, 4, 0, AiShangUtil.gennerCheckinData(),
        AiShangUtil.gennerCheckoutData(), selectZoneID, 0, 0, 0, 0, 0, "", 0, "", 1, 1);

    mMainPresenter.loadHotel(1, json);
  }

  private void asynTrvael() {
    String json = AiShangUtil.generNewsParam(0, selectZoneID, 0, 0, 1, "", "", 1, 3);

    mMainPresenter.loadTravel(2, json);
  }

  private void autoRefresh() {
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        loadData();
      }
    }, 100);
  }

  public class LocalImageHolderView implements Holder<JMrePromResult.Ad> {
    private ImageView imageView;

    public View createView(Context context) {
      imageView = new ImageView(context);
      imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      return imageView;
    }

    @Override public void UpdateUI(Context context, int position, JMrePromResult.Ad data) {

      Picasso.with(context)
          .load(AiShangService.AiShangHost + data.getImageUrl())
          .error(R.mipmap.banner)
          .placeholder(R.mipmap.banner)
          .into(imageView);
    }
  }
}
