package com.aishang.app.ui.HotelDetail;

import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.R;
import com.aishang.app.data.model.JHotelDetailResult;
import com.aishang.app.data.model.JHotelRoomCatListByhotelIDResult;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.BuyHotel.BuyHotelActivity;
import com.aishang.app.ui.BuyLouPan.BuyLouPanActivity;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.NetworkUtil;
import com.aishang.app.util.NumberPickerDelegate;
import com.aishang.app.widget.DrawableCenterButton;
import com.aishang.app.widget.NonScrollGridView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.github.aakira.expandablelayout.Utils;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

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
  @Bind(R.id.room_num) DrawableCenterButton roomNum;
  @Bind(R.id.name) TextView name;
  @Bind(R.id.wv_rules) WebView wvRules;
  @Bind(R.id.map) ImageView map;
  @Bind(R.id.gv_faclilite) NonScrollGridView gvFaclilite;
  @Bind(R.id.gv_services) NonScrollGridView gvService;
  @Bind(R.id.wv_special) WebView wvSpecial;
  @Bind(R.id.tag) TextView tag;
  @Bind(R.id.price) TextView price;
  @Bind(R.id.star_container) LinearLayout starContainer;
  @Bind(R.id.toolbar_title) TextView toolbarText;
  @Bind(R.id.room_container) LinearLayout roomContainer;

  private String hotelName;
  private int hotelID;
  private long checkInDate;
  private long checkOutDate;
  private int selectRoomNum = 1;
  private Dialog progressDialog;

  int netCount = 0;

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

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    presenter.attachView(this);
    setContentView(R.layout.activity_hotel_detail);
    ButterKnife.bind(this);
    hotelID = this.getIntent().getIntExtra(HOTEL_ID, -1);
    checkInDate = this.getIntent().getLongExtra(CHECK_IN_DATE, 0);
    checkOutDate = this.getIntent().getLongExtra(CHECK_OUT_DATE, 0);
    hotelName = this.getIntent().getStringExtra(HOTEL_NAME);
    tvCheckInDate.setText(AiShangUtil.dateFormat(new Date(checkInDate)));
    tvCheckOutDate.setText(AiShangUtil.dateFormat(new Date(checkOutDate)));
    name.setText(hotelName);
    toolbarText.setText(hotelName);
    roomNum.setText(getString(R.string.room_num, selectRoomNum));
    initToolbar();
    proLoad();
  }

  @Override protected void onDestroy() {
    presenter.detachView();
    super.onDestroy();
  }

  @OnClick(R.id.buy) void onclickBuy() {
    if (hotel != null) {
      Intent intent = BuyHotelActivity.getStartIntent(this, hotel);
      this.startActivity(intent);
    } else {
      CommonUtil.showSnackbar("未获取到数据，请稍后再试！", layoutRoot);
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
      //if (avloadingIndicatorView.getVisibility() != View.VISIBLE) {
      //  avloadingIndicatorView.setVisibility(View.VISIBLE);
      //}
      //
      //if (noDataHotel.getVisibility() == View.VISIBLE) {
      //  noDataHotel.setVisibility(View.GONE);
      //}
      progressDialog = DialogFactory.createProgressDialog(this, R.string.listview_loading);
      progressDialog.show();

      asynHotelDetail();
      asynHotelRoomCat();
    } else {

      //if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
      //  avloadingIndicatorView.setVisibility(View.GONE);
      //}
      //
      //if (noDataHotel.getVisibility() == View.VISIBLE) {
      //  noDataHotel.setVisibility(View.GONE);
      //}
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

    price.setText("￥" + result.getDataSet().getBaseInfo().getPriceText());
    setGalleryImg(result);

    String strRules = result.getDataSet().getBaseInfo().getRules();

    if (TextUtils.isEmpty(strRules)) {
      wvRules.setVisibility(View.GONE);
    } else {
      wvRules.setBackgroundColor(0x00000000);
      AiShangUtil.setWebViewContent(wvRules, strRules);
    }

    String strSpecial = result.getDataSet().getBaseInfo().getSpecial();

    if (TextUtils.isEmpty(strRules)) {
      wvSpecial.setVisibility(View.GONE);
    } else {
      wvSpecial.setBackgroundColor(0x00000000);
      AiShangUtil.setWebViewContent(wvSpecial, strSpecial);
    }

    bindFaclilite(result.getDataSet().getBaseInfo().getFacliliteList());
    bindService(result.getDataSet().getBaseInfo().getServiceList());
    bindStarLevel(result.getDataSet().getBaseInfo().getStarLevel());
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

    RoomAdapter adapter = new RoomAdapter(roomCats,this);

    for (int i = 0;i<roomCats.size();i++){
      roomContainer.addView(adapter.getView(i), i);
    }

    roomContainer.requestLayout();
    //bindHotelRoom(result.getDataSet().getRoomCatList());
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

  private void bindHotelRoom(JHotelDetailResult.Data.RoomCat[] roomCats) {

    int i = 0;
    for (JHotelDetailResult.Data.RoomCat room : roomCats) {

      View roomItem = LayoutInflater.from(this).inflate(R.layout.item_room_detail, null);
      final RelativeLayout buttonLayout = (RelativeLayout) roomItem.findViewById(R.id.button);
      final ExpandableRelativeLayout expandableLayout =
          (ExpandableRelativeLayout) roomItem.findViewById(R.id.expandableLayout);

      LinearLayout.LayoutParams params =
          new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
              LinearLayout.LayoutParams.WRAP_CONTENT);
      int spacing = getResources().getDimensionPixelSize(R.dimen.spacing_medium);
      params.setMargins(0, spacing, 0, 0);

      expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {
        @Override public void onPreOpen() {
          createRotateAnimator(buttonLayout, 0f, 90f).start();
        }

        @Override public void onPreClose() {
          createRotateAnimator(buttonLayout, 90f, 0f).start();
        }
      });

      //buttonLayout.setRotation(expandState.get(position) ? 180f : 0f);
      buttonLayout.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(final View v) {
          expandableLayout.toggle();
        }
      });

      roomItem.setLayoutParams(params);
      roomContainer.addView(roomItem, i++);
    }

    roomContainer.requestLayout();
  }

  public ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
    ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
    animator.setDuration(300);
    animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
    return animator;
  }

  @OnClick(R.id.tv_check_out_date) void onClickCheckOutDate() {
    final Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(checkOutDate);
    DialogFactory.createDatePickerDialog(this, cal, new DatePickerDialog.OnDateSetListener() {
      @Override public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        cal.set(year, monthOfYear, dayOfMonth);

        checkOutDate = cal.getTimeInMillis();
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
      }
    }).show();
  }

  @OnClick(R.id.room_num) void onclickRoomNum() {
    DialogFactory.createNumberPicker(this, selectRoomNum, 100, 1, "订购房间数",
        new NumberPickerDelegate() {
          @Override public void OnPick(NumberPicker picker, int num) {
            selectRoomNum = num;
            roomNum.setText(getString(R.string.room_num, num));
            Log.i(TAG, "OnPick: " + selectRoomNum);
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
          .setFlags(tm.getStatus() > 0 ? Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG : 0);
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