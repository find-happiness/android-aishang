package com.aishang.app.ui.HotelDetail;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.data.model.AdapterImgModel;
import com.aishang.app.data.model.HotelOrder;
import com.aishang.app.data.model.JHotelDetailResult;
import com.aishang.app.data.model.JHotelRoomCatListByhotelIDResult;
import com.aishang.app.data.model.SmallImgModel;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.BuyHotel.BuyHotelActivity;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.ui.login.LoginActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.BusProvider;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.NetworkUtil;
import com.aishang.app.widget.DrawableCenterButton;
import com.aishang.app.widget.HackyViewPager;
import com.aishang.app.widget.NonScrollGridView;
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
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import rx.functions.Action1;

public class HotelDetailActivity extends BaseActivity implements HotelDetailMvpView {
  private static final String TAG = "HotelDetailActivity";
  public static final String HOTEL_ID = "hotel_id";
  public static final String HOTEL_NAME = "hotel_name";
  public static final String CHECK_IN_DATE = "check_in_date";
  public static final String CHECK_OUT_DATE = "check_out_date";
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;
  @Bind(R.id.buy) TextView buy;
  @Bind(R.id.ll_action_button) LinearLayout llActionButton;
  @Bind(R.id.convenientBanner) ConvenientBanner convenientBanner;
  @Bind(R.id.tv_check_in_date) DrawableCenterButton tvCheckInDate;
  @Bind(R.id.tv_check_out_date) DrawableCenterButton tvCheckOutDate;
  @Bind(R.id.name) TextView name;
  @Bind(R.id.wv_rules) WebView wvRules;
  @Bind(R.id.map) MapView mapView;
  @Bind(R.id.gv_faclilite) NonScrollGridView gvFaclilite;
  @Bind(R.id.gv_services) NonScrollGridView gvService;
  @Bind(R.id.wv_special) WebView wvSpecial;
  @Bind(R.id.tag) TextView tag;
  @Bind(R.id.price) TextView price;
  @Bind(R.id.star_container) LinearLayout starContainer;
  @Bind(R.id.toolbar_title) TextView toolbarText;
  @Bind(R.id.room_container) LinearLayout roomContainer;
  @Bind(R.id.view_pager) HackyViewPager viewPager;
  @Bind(R.id.take_phone) ImageView takePhone;
  @Bind(R.id.address) TextView address;
  @Bind(R.id.server_container) LinearLayout serverContainer;
  @Bind(R.id.special_container) LinearLayout specialContainer;
  @Bind(R.id.rules_container) LinearLayout rulesContainer;

  private String hotelName;
  private int hotelID;
  private long checkInDate;
  private long checkOutDate;
  private int selectRoomNum = 1;
  private Dialog progressDialog;
  private RoomAdapter roomAdapter;
  int netCount = 0;
  private AMap aMap;

  /**
   * Return an Intent to start this Activity.
   */
  public static Intent getStartIntent(Context context, int hotelID, String hotelName,
      long checkInDate, long checkOutDate) {
    Intent intent = new Intent(context, HotelDetailActivity.class);
    intent.putExtra(HOTEL_ID, hotelID);
    intent.putExtra(HOTEL_NAME, hotelName);
    intent.putExtra(CHECK_IN_DATE, checkInDate);
    intent.putExtra(CHECK_OUT_DATE, checkOutDate);
    return intent;
  }

  @Inject HotelDetailPresenter presenter;

  @Bind(R.id.toolbar) Toolbar toolbar;

  private JHotelDetailResult hotel;
  DisplayMetrics outMetrics;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    presenter.attachView(this);

    outMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(outMetrics);

    setContentView(R.layout.activity_hotel_detail);
    ButterKnife.bind(this);
    mapView.onCreate(savedInstanceState);
    hotelID = this.getIntent().getIntExtra(HOTEL_ID, -1);
    checkInDate = this.getIntent().getLongExtra(CHECK_IN_DATE, 0);
    checkOutDate = this.getIntent().getLongExtra(CHECK_OUT_DATE, 0);
    hotelName = this.getIntent().getStringExtra(HOTEL_NAME);
    setCheckInDateToView(checkInDate);
    setCheckOutDateToView(checkOutDate);
    name.setText(hotelName);
    toolbarText.setText(hotelName);
    initToolbar();
    initMap();
    proLoad();
  }

  /**
   * 方法必须重写
   */
  @Override protected void onResume() {
    super.onResume();
    mapView.onResume();
    BusProvider.getInstance().register(this);
  }

  /**
   * 方法必须重写
   */
  @Override protected void onPause() {
    super.onPause();
    mapView.onPause();
    BusProvider.getInstance().unregister(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.detachView();
    mapView.onDestroy();
  }

  @Override protected void onStop() {
    super.onStop();
  }

  @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK && View.VISIBLE == viewPager.getVisibility()) {
      BusProvider.getInstance().post(AdapterImgModel.create(0, 0, viewPager.getCurrentItem()));
      return false;
    }
    return super.onKeyDown(keyCode, event);
  }

  @Subscribe public void onclickRoomCatImg(SmallImgModel model) {
    String[] photos = model.getPhotos();

    viewPager.setAdapter(new RoomImgPagerAdapter(this, photos));

    viewPager.setCurrentItem(model.getIndex());

    viewPager.setVisibility(View.VISIBLE);

    viewPager.clearAnimation();

    int[] locations = model.getLocations().get(model.getIndex());

    viewPager.setTag(model);

    PropertyValuesHolder pvhSX = PropertyValuesHolder.ofFloat(View.SCALE_X,
        (float) model.getSize()[0] / (float) outMetrics.widthPixels, 1f);

    PropertyValuesHolder pvhSY = PropertyValuesHolder.ofFloat(View.SCALE_Y,
        (float) model.getSize()[1] / (float) outMetrics.heightPixels, 1f);
    PropertyValuesHolder pvhTX = PropertyValuesHolder.ofFloat(View.X, locations[0], 0);
    PropertyValuesHolder pvhTY =
        PropertyValuesHolder.ofFloat(View.Y, locations[1] - model.getSize()[1] / 2, 0);
    PropertyValuesHolder pvhA = PropertyValuesHolder.ofFloat(View.ALPHA, 0, 1);
    viewPager.setPivotY(0.5f);
    viewPager.setPivotY(0.5f);
    ObjectAnimator animator =
        ObjectAnimator.ofPropertyValuesHolder(viewPager, pvhSX, pvhSY, pvhTX, pvhTY, pvhA);
    animator.setDuration(300);
    animator.start();
  }

  @Subscribe public void onTapRoomCatimg(AdapterImgModel model) {
    viewPager.clearAnimation();

    SmallImgModel roomImgModel = (SmallImgModel) viewPager.getTag();
    int[] locations = roomImgModel.getLocations().get(model.getIndex() % 3);
    PropertyValuesHolder pvhSX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f,
        (float) roomImgModel.getSize()[0] / (float) outMetrics.widthPixels);
    PropertyValuesHolder pvhSY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f,
        (float) roomImgModel.getSize()[1] / (float) outMetrics.heightPixels);
    PropertyValuesHolder pvhTX = PropertyValuesHolder.ofFloat(View.X, 0, locations[0]);
    PropertyValuesHolder pvhTY = PropertyValuesHolder.ofFloat(View.Y, 0, locations[1]);
    PropertyValuesHolder pvhA = PropertyValuesHolder.ofFloat(View.ALPHA, 1, 0);

    ObjectAnimator animator =
        ObjectAnimator.ofPropertyValuesHolder(viewPager, pvhSX, pvhSY, pvhTX, pvhTY, pvhA);
    animator.setDuration(300);
    animator.start();

    animator.addListener(new Animator.AnimatorListener() {
      @Override public void onAnimationStart(Animator animation) {

      }

      @Override public void onAnimationEnd(Animator animation) {
        viewPager.setVisibility(View.GONE);
      }

      @Override public void onAnimationCancel(Animator animation) {

      }

      @Override public void onAnimationRepeat(Animator animation) {

      }
    });
  }

  @Subscribe public void onBuyRoomClick(HotelOrder order) {

    if (hotel != null) {
      if (checkLogin()) {
        ArrayList<HotelOrder> orders = new ArrayList<>();
        orders.add(order);

        Intent intent = BuyHotelActivity.getStartIntent(HotelDetailActivity.this, hotel, orders,
            AiShangUtil.dateFormat(new Date(checkInDate)),
            AiShangUtil.dateFormat(new Date(checkOutDate)));
        HotelDetailActivity.this.startActivity(intent);
      } else {
        DialogFactory.createSimpleDialog(this, R.string.error_not_login, R.string.intent_login,
            android.R.string.ok, android.R.string.cancel, new DialogInterface.OnClickListener() {
              @Override public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                IntentToLogin();
              }
            }, new DialogInterface.OnClickListener() {
              @Override public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
              }
            }).show();
      }
    } else {
      CommonUtil.showSnackbar("未获取到数据，请稍后再试！", layoutRoot);
    }
  }

  /**
   * 方法必须重写
   */
  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    mapView.onSaveInstanceState(outState);
  }

  @OnClick(R.id.buy) void onclickBuy() {
    if (hotel != null) {
      if (checkLogin()) {

        if (roomAdapter == null || roomAdapter.getRoomCat().size() <= 0) {
          CommonUtil.showSnackbar("没有获取到房间数据，请稍后再试！", layoutRoot);
          return;
        }

        Log.i(TAG, "onclickBuy: room cat size" + roomAdapter.getRoomCat().size());

        roomAdapter.getOrderRooms().subscribe(new Action1<List<HotelOrder>>() {
          @Override public void call(List<HotelOrder> hotelOrders) {
            //Log.i(TAG, "onclickBuy: -------------------- >" + hotelOrders.size());
            if (hotelOrders.size() > 0) {
              if (hotelOrders.size() > 1) {
                CommonUtil.showSnackbar("只能同时预订一种房间！", layoutRoot);
              } else {
                Intent intent = BuyHotelActivity.getStartIntent(HotelDetailActivity.this, hotel,
                    new ArrayList<HotelOrder>(hotelOrders),
                    AiShangUtil.dateFormat(new Date(checkInDate)),
                    AiShangUtil.dateFormat(new Date(checkOutDate)));
                HotelDetailActivity.this.startActivity(intent);
              }
            } else {
              CommonUtil.showSnackbar("您没有选择房间！", layoutRoot);
            }
          }
        });
      } else {
        DialogFactory.createSimpleDialog(this, R.string.error_not_login, R.string.intent_login,
            android.R.string.ok, android.R.string.cancel, new DialogInterface.OnClickListener() {
              @Override public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                IntentToLogin();
              }
            }, new DialogInterface.OnClickListener() {
              @Override public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
              }
            }).show();
      }
    } else {
      CommonUtil.showSnackbar("未获取到数据，请稍后再试！", layoutRoot);
    }
  }

  private void IntentToLogin() {
    Intent intent = new Intent(this, LoginActivity.class);
    this.startActivity(intent);
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

  private void proLoad() {
    if (NetworkUtil.isNetworkConnected(this)) {
      progressDialog = DialogFactory.createProgressDialog(this, R.string.listview_loading);
      progressDialog.show();
      asynHotelDetail();
      asynHotelRoomCat();
    } else {
      CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
    }
  }

  private void asynHotelDetail() {
    //String json = AiShangUtil.generHotelParam(0, "", 0, 10, 0, AiShangUtil.gennerCheckinData(),
    //    AiShangUtil.gennerCheckoutData(), selectZoneID, 0, 0, 0, 0, 0, "", 0, "", 0, 1);

    //presenter.loadHotel(1, json);

    presenter.loadHotelDetail(1, AiShangUtil.generHotelDetailParam(this.hotelID, 1, 1, 1, 1, 1,
        AiShangUtil.dateFormat(new Date(checkInDate)),
        AiShangUtil.dateFormat(new Date(checkOutDate)), 1, 1, 1, 1));
  }

  private void asynHotelRoomCat() {

    presenter.loadHotelRoomCat(1, AiShangUtil.generHotelRoomCatByHotelIDParam(this.hotelID));
  }

  public View getLayoutRoot() {
    return layoutRoot;
  }

  @Override public void showError(String error) {
    dismissDialog();
    Log.e(TAG, "showError: " + error);
  }

  @Override public void showHotelDetailError(String error) {
    dismissDialog();
    Log.e(TAG, "showError: " + error);
  }

  private void dismissDialog() {

    netCount++;

    if (progressDialog != null && progressDialog.isShowing() && netCount >= 2) {
      progressDialog.dismiss();
    }
  }

  @Override public void bindDataToView(JHotelDetailResult result) {

    dismissDialog();

    hotel = result;
    String strTag = result.getDataSet().getBaseInfo().getTags();
    if (TextUtils.isEmpty(strTag)) {
      tag.setVisibility(View.GONE);
    } else {
      tag.setText(strTag);
    }

    String priceText = result.getDataSet().getBaseInfo().getPriceText();
    if (priceText.contains(".")) {
      priceText = priceText.substring(0, priceText.indexOf("."));
    }

    price.setText("￥" + priceText);
    setGalleryImg(result);

    String strRules = result.getDataSet().getBaseInfo().getRules();

    if (TextUtils.isEmpty(strRules)) {
      rulesContainer.setVisibility(View.GONE);
    } else {
      wvRules.setBackgroundColor(0x00000000);
      AiShangUtil.setWebViewContent(wvRules, strRules);
    }

    String strSpecial = result.getDataSet().getBaseInfo().getSpecial();

    if (TextUtils.isEmpty(strSpecial)) {
      specialContainer.setVisibility(View.GONE);
    } else {
      wvSpecial.setBackgroundColor(0x00000000);
      AiShangUtil.setWebViewContent(wvSpecial, strSpecial);
    }

    bindFaclilite(result.getDataSet().getBaseInfo().getFacliliteList());
    bindService(result.getDataSet().getBaseInfo().getServiceList());
    bindStarLevel(result.getDataSet().getBaseInfo().getStarLevel());
    bindMap(result.getDataSet().getBaseInfo().getLat(), result.getDataSet().getBaseInfo().getLng(),
        result.getDataSet().getBaseInfo().getName(),
        result.getDataSet().getBaseInfo().getAddress());
  }

  @Override public void showHotelRoomCatError(String error) {

    dismissDialog();
    Log.e(TAG, "showHotelRoomCatError: " + error);
  }

  @Override public void bindRoomCat(JHotelRoomCatListByhotelIDResult result) {

    dismissDialog();

    List<JHotelRoomCatListByhotelIDResult.GRoomTypeListEntity> roomTypeListEntities =
        result.getGRoomTypeList();

    List<JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity> roomCatListEntities =
        result.getHotelRoomCatList();

    List<RoomCat> roomCats = new ArrayList<>();

    for (JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity roomCatListEntity : roomCatListEntities) {
      for (JHotelRoomCatListByhotelIDResult.GRoomTypeListEntity roomTypeListEntity : roomTypeListEntities) {
        if (roomCatListEntity.getRoomTypeID() == roomTypeListEntity.getRoomTypeID()) {
          RoomCat item = checkRoomCat(roomCats, roomTypeListEntity.getRoomTypeID());
          if (item != null) {
            item.addCatEntity(roomCatListEntity);
          } else {
            List<JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity> items = new ArrayList<>();
            items.add(roomCatListEntity);
            roomCats.add(new RoomCat(roomTypeListEntity, items));
          }
        }
      }
    }

    roomAdapter = new RoomAdapter(roomCats, this);

    Log.i(TAG, "bindRoomCat: room cats size" + roomCats.size());

    for (int i = 0; i < roomCats.size(); i++) {
      roomContainer.addView(roomAdapter.getView(i), i);
    }

    roomContainer.requestLayout();

    if (roomCats.size() > 0) {

      new Handler().postDelayed(new Runnable() {
        @Override public void run() {
          roomAdapter.toggle(0);
        }
      }, 100);
    }
    //bindHotelRoom(result.getDataSet().getRoomCatList());
  }

  private void setCheckInDateToView(long cal) {
    tvCheckInDate.setText("入住日期:" + AiShangUtil.dateFormat(new Date(cal)));
  }

  private void setCheckOutDateToView(long cal) {
    tvCheckOutDate.setText("退房日期:" + AiShangUtil.dateFormat(new Date(cal)));
  }

  private RoomCat checkRoomCat(List<RoomCat> roomCats, int typeID) {
    for (RoomCat cat : roomCats) {
      if (cat.getRoomTypeListEntity().getRoomTypeID() == typeID) return cat;
    }
    return null;
  }

  private void bindStarLevel(int level) {

    for (int i = 0; i < level; i++) {
      ImageView iv = new ImageView(this);
      iv.setImageResource(R.mipmap.star_light);
      LinearLayout.LayoutParams params =
          new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
              LinearLayout.LayoutParams.WRAP_CONTENT);
      int spacing = getResources().getDimensionPixelSize(R.dimen.spacing_small);
      params.setMargins(0, spacing, spacing, spacing);
      iv.setLayoutParams(params);
      starContainer.addView(iv);
    }
  }

  private void bindFaclilite(JHotelDetailResult.Data.BaseInfo.Faclilite[] faclilites) {

    List<TextModel> models = new ArrayList<>();
    for (JHotelDetailResult.Data.BaseInfo.Faclilite f : faclilites) {
      TextModel tm = new TextModel(f.getName(), f.getEnable());
      models.add(tm);
    }

    TextAdapter adapter = new TextAdapter(models);

    gvFaclilite.setAdapter(adapter);
  }

  private void bindService(JHotelDetailResult.Data.BaseInfo.Service[] services) {

    List<TextModel> models = new ArrayList<>();
    for (JHotelDetailResult.Data.BaseInfo.Service f : services) {
      TextModel tm = new TextModel(f.getName(), f.getEnable());
      models.add(tm);
    }
    TextAdapter adapter = new TextAdapter(models);
    gvService.setAdapter(adapter);
  }

  private void bindMap(float lat, float lng, String title, String address) {

    CoordinateConverter converter = new CoordinateConverter();
    LatLng latLng =
        converter.from(CoordinateConverter.CoordType.GOOGLE).coord(new LatLng(lat, lng)).convert();

    // 设置所有maker显示在当前可视区域地图中
    LatLngBounds bounds = new LatLngBounds.Builder().include(latLng).build();

    MarkerOptions markerOption = new MarkerOptions();
    markerOption.position(latLng);
    this.address.setText(address);
    markerOption.draggable(false);
    markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.spotlight));
    Marker marker2 = aMap.addMarker(markerOption);
    marker2.showInfoWindow();

    aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10));
  }

  @OnClick(R.id.tv_check_out_date) void onClickCheckOutDate() {
    final Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(checkOutDate);
    DialogFactory.createDatePickerDialog(this, cal, new DatePickerDialog.OnDateSetListener() {
      @Override public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        cal.set(year, monthOfYear, dayOfMonth);
        checkOutDate = cal.getTimeInMillis();
        setCheckOutDateToView(checkOutDate);
      }
    }).show();
  }

  @OnClick(R.id.tv_check_in_date) void onClickCheckInDate() {
    final Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(checkInDate);
    DialogFactory.createDatePickerDialog(this, cal, new DatePickerDialog.OnDateSetListener() {
      @Override public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        cal.set(year, monthOfYear, dayOfMonth);
        checkInDate = cal.getTimeInMillis();
        setCheckInDateToView(checkInDate);
      }
    }).show();
  }

  private void setGalleryImg(JHotelDetailResult result) {

    List<String> ads = new ArrayList<String>();
    if (result.getDataSet().getBaseInfo().getImageTotal() > 0) {
      for (JHotelDetailResult.Data.SelectImage img : result.getDataSet().getSelectImageList()) {
        ads.add(img.getUrl());
      }
    } else {
      ads.add(result.getDataSet().getBaseInfo().getImageUrl());
    }

    //Log.i(TAG, "setGalleryImg: " + ads.size());

    int[] size = CommonUtil.getHeightWithScreenWidth(this, 16, 9);
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size[0], size[1]);

    convenientBanner.setLayoutParams(layoutParams);

    convenientBanner.setPages(new CBViewHolderCreator<NetImageHolderView>() {
      @Override public NetImageHolderView createHolder() {
        return new NetImageHolderView();
      }
    }, ads)
        //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        .setPageIndicator(new int[] { R.mipmap.ellipse_nomal, R.mipmap.ellipse_select })
        //设置指示器的方向
        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
  }

  private boolean checkLogin() {
    return BoilerplateApplication.get(this).getMemberLoginResult() != null;
  }

  @OnClick(R.id.take_phone) public void onClickTakePhone() {

    if (!CommonUtil.isCanTakePhone(this)) {
      CommonUtil.showSnackbar("您的设备不能打电话!", layoutRoot);
      return;
    }

    if (hotel != null) {
      try {
        CommonUtil.intentToCall("023-62976617", this);//
      } catch (Exception e) {
        Log.e(TAG, "oncliclTakePhone: " + e.toString());
      }
    } else {
      CommonUtil.showSnackbar("未获取到数据，请稍后再试！", layoutRoot);
    }
  }

  public class NetImageHolderView implements Holder<String> {
    private ImageView imageView;

    public View createView(Context context) {
      imageView = new ImageView(context);
      imageView.setScaleType(ImageView.ScaleType.FIT_XY);
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

  private class TextAdapter extends BaseAdapter {

    List<TextModel> dates;

    public TextAdapter(List<TextModel> dates) {
      this.dates = dates;
    }

    @Override public int getCount() {
      return dates.size();
    }

    @Override public Object getItem(int position) {
      return dates.get(position);
    }

    @Override public long getItemId(int position) {
      return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
      TextModel tm = dates.get(position);
      TextView tv = new TextView(HotelDetailActivity.this);
      tv.setText(tm.getText());
      tv.getPaint()
          .setFlags(tm.getStatus() > 0 ? 0 : Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
      tv.getPaint().setAntiAlias(true);
      return tv;
    }
  }

  private class TextModel {

    private String text;
    private int status;

    public TextModel(String text, int status) {
      this.text = text;
      this.status = status;
    }

    public String getText() {
      return text;
    }

    public void setText(String text) {
      this.text = text;
    }

    public int getStatus() {
      return status;
    }

    public void setStatus(int status) {
      this.status = status;
    }
  }
}