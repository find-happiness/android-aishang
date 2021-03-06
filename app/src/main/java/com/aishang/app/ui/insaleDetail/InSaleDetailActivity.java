package com.aishang.app.ui.insaleDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.R;
import com.aishang.app.data.model.JLoupanProductDetailResult;
import com.aishang.app.data.model.ViewModel;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.BuyLouPan.BuyLouPanActivity;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.NetworkUtil;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.CoordinateConverter;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class InSaleDetailActivity extends BaseActivity implements InSaleDetailMvpView {
  private static final String TAG = "InSaleDetailActivity";
  public static final String LOUPAN_PRODUCT_ID = "loupan_id";
  public static final String LOUPAN_PRODUCT_NAME = "loupan_name";
  private static final String EXTRA_IMAGE = "com.antonioleiva.materializeyourapp.extraImage";
  private static final String EXTRA_TITLE = "com.antonioleiva.materializeyourapp.extraTitle";

  @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
  @Bind(R.id.toolbar) Toolbar toolbar;
  //@Bind(R.id.title)
  //TextView title;
  @Bind(R.id.convenientBanner) ConvenientBanner banner;

  @Inject InSaleDetailPresenter presenter;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;
  @Bind(R.id.buy) TextView buy;
  @Bind(R.id.tv_title_name) TextView tvTitleName;
  @Bind(R.id.status) ImageView status;
  @Bind(R.id.avloadingIndicatorView) AVLoadingIndicatorView avloadingIndicatorView;
  @Bind(R.id.no_data_hotel) TextView noDataHotel;
  @Bind(R.id.rl_net_status) RelativeLayout rlNetStatus;
  @Bind(R.id.map) MapView mapView;
  @Bind(R.id.tese) TextView tese;
  @Bind(R.id.address) TextView address;
  @Bind(R.id.wv_description) WebView wvDescription;
  @Bind(R.id.type) TextView type;
  @Bind(R.id.huo_xing) TextView huoXing;
  @Bind(R.id.move_in_date) TextView moveInDate;
  @Bind(R.id.fang_chang_zeng) TextView fangChangZeng;
  @Bind(R.id.wv_environment) WebView wvEnvironment;
  @Bind(R.id.price_text) TextView priceText;

  private String loupanProductName;
  private int loupanProductID;
  private AMap aMap;

  private JLoupanProductDetailResult loupan;

  public static void navigate(AppCompatActivity activity, View transitionImage,
      ViewModel viewModel) {
    Intent intent = new Intent(activity, InSaleDetailActivity.class);
    intent.putExtra(EXTRA_IMAGE, viewModel.getImage());
    intent.putExtra(EXTRA_TITLE, viewModel.getText());

    ActivityOptionsCompat options =
        ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_IMAGE);
    ActivityCompat.startActivity(activity, intent, options.toBundle());
  }

  /**
   * Return an Intent to start this Activity.
   */
  public static Intent getStartIntent(Context context, int loupanID, String loupanName) {
    Intent intent = new Intent(context, InSaleDetailActivity.class);
    intent.putExtra(LOUPAN_PRODUCT_ID, loupanID);
    intent.putExtra(LOUPAN_PRODUCT_NAME, loupanName);
    return intent;
  }

  @SuppressWarnings("ConstantConditions") @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    //initActivityTransitions();
    setContentView(R.layout.activity_in_sale_detail);
    ButterKnife.bind(this);
    mapView.onCreate(savedInstanceState);
    presenter.attachView(this);
    loupanProductID = this.getIntent().getIntExtra(LOUPAN_PRODUCT_ID, -1);
    loupanProductName = this.getIntent().getStringExtra(LOUPAN_PRODUCT_NAME);
    initView();
    proLoad();

    //ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), EXTRA_IMAGE);
    //supportPostponeEnterTransition();
    //
    //setSupportActionBar(toolbar);
    //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    //
    //String itemTitle = getIntent().getStringExtra(EXTRA_TITLE);
    //collapsingToolbarLayout.setTitle(itemTitle);
    //collapsingToolbarLayout.setExpandedTitleColor(
    //    getResources().getColor(android.R.color.transparent));

  }

  /**
   * 方法必须重写
   */
  @Override protected void onResume() {
    super.onResume();
    mapView.onResume();
  }

  /**
   * 方法必须重写
   */
  @Override protected void onPause() {
    super.onPause();
    mapView.onPause();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.detachView();
    mapView.onDestroy();
  }

  /**
   * 方法必须重写
   */
  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    mapView.onSaveInstanceState(outState);
  }

  @Override public boolean dispatchTouchEvent(MotionEvent motionEvent) {
    try {
      return super.dispatchTouchEvent(motionEvent);
    } catch (NullPointerException e) {
      return false;
    }
  }

  @OnClick(R.id.take_phone) void oncliclTakePhone() {

    if (!CommonUtil.isCanTakePhone(this)) {
      CommonUtil.showSnackbar("您的设备不能打电话!", layoutRoot);
      return;
    }

    if (loupan != null) {
      try {
        CommonUtil.intentToCall("", this);
      } catch (Exception e) {
        Log.e(TAG, "oncliclTakePhone: " + e.toString());
      }
    } else {
      CommonUtil.showSnackbar("未获取到数据，请稍后再试！", layoutRoot);
    }
  }

  @OnClick(R.id.take_online) void oncliclTakeOnLine() {
    if (loupan != null) {
      CommonUtil.showSnackbar("在线交谈", layoutRoot);
    } else {
      CommonUtil.showSnackbar("未获取到数据，请稍后再试！", layoutRoot);
    }
  }

  @OnClick(R.id.buy) void onclickBuy() {
    if (loupan != null) {
      Intent intent = BuyLouPanActivity.getStartIntent(this, loupan);
      this.startActivity(intent);
    } else {
      CommonUtil.showSnackbar("未获取到数据，请稍后再试！", layoutRoot);
    }
  }

  private void initView() {
    initToolbar();
    initBanner();
    initCollapsingToolbar();
    initMap();
  }

  private void initMap() {
    if (aMap == null) {
      aMap = mapView.getMap();
      aMap.setOnMapTouchListener(new AMap.OnMapTouchListener() {
        @Override public void onTouch(MotionEvent motionEvent) {
          mapView.getParent().requestDisallowInterceptTouchEvent(true);
        }
      });

      int[] size = CommonUtil.getHeightWithScreenWidth(this, 4, 3);
      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size[0], size[1]);
      mapView.setLayoutParams(params);
    }
  }

  private void initCollapsingToolbar() {
    collapsingToolbarLayout.setTitle(loupanProductName);
    collapsingToolbarLayout.setExpandedTitleGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
  }

  private void proLoad() {
    if (NetworkUtil.isNetworkConnected(this)) {
      if (avloadingIndicatorView.getVisibility() != View.VISIBLE) {
        avloadingIndicatorView.setVisibility(View.VISIBLE);
      }

      if (noDataHotel.getVisibility() == View.VISIBLE) {
        noDataHotel.setVisibility(View.GONE);
      }

      asynHotelDetail();
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

  private void asynHotelDetail() {
    //String json = AiShangUtil.generHotelParam(0, "", 0, 10, 0, AiShangUtil.gennerCheckinData(),
    //    AiShangUtil.gennerCheckoutData(), selectZoneID, 0, 0, 0, 0, 0, "", 0, "", 0, 1);

    //presenter.loadHotel(1, json);

    presenter.loadLoupanProductDetail(1,
        AiShangUtil.generLoupanProductDetail(loupanProductID, 1, 1));
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

  private void initBanner() {

    int[] size = CommonUtil.getHeightWithScreenWidth(this, 16, 9);
    CollapsingToolbarLayout.LayoutParams layoutParams =
        new CollapsingToolbarLayout.LayoutParams(size[0], size[1]);

    banner.setLayoutParams(layoutParams);
  }

  private void initActivityTransitions() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Slide transition = new Slide();
      transition.excludeTarget(android.R.id.statusBarBackground, true);
      getWindow().setEnterTransition(transition);
      getWindow().setReturnTransition(transition);
    }
  }

  @Override public void showError(String error) {
    if (rlNetStatus.getVisibility() == View.GONE) rlNetStatus.setVisibility(View.VISIBLE);
    if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
      avloadingIndicatorView.setVisibility(View.GONE);
    }
    if (noDataHotel.getVisibility() == View.VISIBLE) {
      noDataHotel.setVisibility(View.GONE);
    }

    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override public void showLoupanProductDetailError(String error) {
    if (rlNetStatus.getVisibility() == View.GONE) rlNetStatus.setVisibility(View.VISIBLE);
    if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
      avloadingIndicatorView.setVisibility(View.GONE);
    }
    if (noDataHotel.getVisibility() != View.VISIBLE) {
      noDataHotel.setVisibility(View.VISIBLE);
    }
    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override public void bindDataToView(JLoupanProductDetailResult result) {

    loupan = result;

    if (rlNetStatus.getVisibility() == View.VISIBLE) rlNetStatus.setVisibility(View.GONE);

    JLoupanProductDetailResult.Data dataSet = result.getDataSet();
    JLoupanProductDetailResult.Data.LoupanData loupanData = dataSet.getLoupanData();

    AiShangUtil.setWebViewContent(wvDescription, loupanData.getProjectIntro());

    AiShangUtil.setWebViewContent(wvEnvironment, loupanData.getEnvironment());

    //Picasso.with(this).load(AiShangService.IMG_URL + loupanData.getMapStaticImg()).into(address);

    tese.setText(TextUtils.isEmpty(loupanData.getTags()) ? "暂无描述" : loupanData.getTags());

    address.setText(loupanData.getAddress());

    moveInDate.setText(dataSet.getMoveInDate());

    type.setText(dataSet.getTags());

    fangChangZeng.setText("有");

    huoXing.setText(dataSet.getTitle());

    tvTitleName.setText(loupanProductName + "|" + dataSet.getTitle());

    if (dataSet.getPrice() <= 0) {
      priceText.setText("暂无价格");
    } else {
      priceText.setText("￥" + dataSet.getPrice() / 10000 + "万元");
    }

    setBanner(dataSet.getImageList());

    bindMap(loupanData.getLat(), loupanData.getLng(), loupanProductName, loupanData.getAddress());
  }

  private void bindMap(float lat, float lng, String title, String address) {

    CoordinateConverter converter = new CoordinateConverter();
    LatLng latLng =
        converter.from(CoordinateConverter.CoordType.GOOGLE).coord(new LatLng(lat, lng)).convert();

    // 设置所有maker显示在当前可视区域地图中
    LatLngBounds bounds = new LatLngBounds.Builder().include(latLng).build();

    MarkerOptions markerOption = new MarkerOptions();
    markerOption.position(latLng);
    //markerOption.title(title).snippet(address);
    markerOption.draggable(false);
    markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.spotlight));
    Marker marker2 = aMap.addMarker(markerOption);
    marker2.showInfoWindow();

    aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10));
  }

  private void setBanner(JLoupanProductDetailResult.Image[] images) {
    List<String> localImages = new ArrayList<>();
    for (JLoupanProductDetailResult.Image img : images) {
      localImages.add(img.getUrl());
    }

    banner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
      @Override public LocalImageHolderView createHolder() {
        return new LocalImageHolderView();
      }
    }, localImages)
        //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        .setPageIndicator(new int[] { R.mipmap.ellipse_nomal, R.mipmap.ellipse_select })
        //设置指示器的方向
        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
    //设置翻页的效果，不需要翻页效果可用不设
    //.setPageTransformer(Transformer.DefaultTransformer);    //集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
    //        convenientBanner.setManualPageable(false);//设置不能手动影响

    //title.setText(itemTitle);
  }

  public class LocalImageHolderView implements Holder<String> {
    private ImageView imageView;

    public View createView(Context context) {
      imageView = new ImageView(context);
      imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      return imageView;
    }

    @Override public void UpdateUI(Context context, int position, String url) {
      Picasso.with(context)
          .load(AiShangService.AiShangHost + url)
          .error(R.mipmap.banner)
          .placeholder(R.mipmap.banner)
          .into(imageView);
    }
  }
}
