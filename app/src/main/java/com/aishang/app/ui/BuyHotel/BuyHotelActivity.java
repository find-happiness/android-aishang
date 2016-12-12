package com.aishang.app.ui.BuyHotel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.BuildConfig;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.data.model.AlipayPreModel;
import com.aishang.app.data.model.AuthResult;
import com.aishang.app.data.model.HotelOrder;
import com.aishang.app.data.model.JHotelDetailResult;
import com.aishang.app.data.model.JHotelRoomCatListByhotelIDResult;
import com.aishang.app.data.model.JHotelRoomPriceResult;
import com.aishang.app.data.model.JMemberGiftcardResult;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.data.model.JMemberProfileResult;
import com.aishang.app.data.model.JMyVacationApplyResult;
import com.aishang.app.data.model.JMyVacationListResult;
import com.aishang.app.data.model.PayResult;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.Constants;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.EventPosterHelper;
import com.aishang.app.util.NetImageHolderView;
import com.aishang.app.util.NetworkUtil;
import com.aishang.app.util.OrderInfoUtil2_0;
import com.aishang.app.widget.HorizontalNumberPicker;
import com.alipay.sdk.app.PayTask;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.shizhefei.view.viewpager.SViewPager;
import com.squareup.otto.Subscribe;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.json.JSONObject;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

public class BuyHotelActivity extends BaseActivity
    implements BuyHotelMvpView, PaymentFragment.OnFragmentInteractionListener,
    CardFragment.OnFragmentCardListener {

  private static final String TAG = "BuyHotelActivity";

  private static final String FRAGMENT_TAG_PAYMRNT = "payment";
  private static final String HOTEL = "hotel";
  private static final String ORDER = "order";
  private static final String CHECK_IN_DATE = "check_in_date";
  private static final String CHECK_OUT_DATE = "check_out_date";
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.convenientBanner) ConvenientBanner banner;
  @Bind(R.id.name) TextView name;
  @Bind(R.id.guest_name) TextView guestName;
  @Bind(R.id.phone) TextView phone;
  @Bind(R.id.email) TextView email;
  @Bind(R.id.submint) Button submint;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;
  @Bind(R.id.toolbar_title) TextView toolbarTitle;
  @Bind(R.id.star_container) LinearLayout starContainer;
  @Bind(R.id.type) TextView type;
  @Bind(R.id.address) TextView address;
  @Bind(R.id.order_container) LinearLayout orderContainer;
  @Bind(R.id.total_price) TextView totalPrice;

  @Inject BuyHotelPresenter presenter;
  ProgressDialog progressDialog;
  @Bind(R.id.card) TextView card;
  @Bind(R.id.drawerLayout) DrawerLayout drawerLayout;
  @Bind(R.id.view_pager) SViewPager viewPager;
  @Bind(R.id.title_2) TextView title2;
  @Bind(R.id.must_input2) TextView mustInput2;
  @Bind(R.id.title_3) TextView title3;
  @Bind(R.id.must_input3) TextView mustInput3;
  @Bind(R.id.title_4) TextView title4;
  @Bind(R.id.title_5) TextView title5;
  @Bind(R.id.payment_container) LinearLayout paymentContainer;
  @Bind(R.id.scroll) ScrollView scroll;
  private JHotelDetailResult hotel;
  private ArrayList<HotelOrder> orderList;
  private String checkInDate;
  private String checkOutDate;
  JMyVacationListResult vacationResult;
  @Inject EventPosterHelper eventPosterHelper;

  JMemberGiftcardResult.MemberGiftcardListEntity selectGift;
  JMyVacationListResult.MemberexCardListEntity selectCard;

  PAYMENT payType = PAYMENT.TONG_YONG_JI_FEN;

  RoomViewHolder holder;

  private static final int SDK_PAY_FLAG = 1;
  private static final int SDK_AUTH_FLAG = 2;

  private IWXAPI wecharApi;

  @Override public void onPaymentChange(PAYMENT payment) {
    payType = payment;
  }

  @Override public void onGiftCardCardClick() {
    drawerLayout.openDrawer(Gravity.RIGHT);

    viewPager.setCurrentItem(0, false);
  }

  @Override public void onMyCardClick() {
    drawerLayout.openDrawer(Gravity.RIGHT);

    viewPager.setCurrentItem(1, false);
  }

  @Override public void defaultCard(JMyVacationListResult.MemberexCardListEntity card) {
    onMyCarSelect(card);
  }

  public enum PAYMENT {

    TONG_YONG_JI_FEN,
    HUAN_ZU_JI_FEN,
    ALIPAY,
    WECHAT,
    UNKNOW

  }

  public static Intent getStartIntent(Context ctx, JHotelDetailResult loupan,
      ArrayList<HotelOrder> orders, String checkInDate, String checkOutDate) {
    Intent intent = new Intent(ctx, BuyHotelActivity.class);
    intent.putExtra(HOTEL, loupan);
    intent.putExtra(CHECK_IN_DATE, checkInDate);
    intent.putExtra(CHECK_OUT_DATE, checkOutDate);
    intent.putParcelableArrayListExtra(ORDER, orders);
    return intent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    presenter.attachView(this);
    setContentView(R.layout.activity_buy_hotel);
    ButterKnife.bind(this);
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    if (savedInstanceState != null && savedInstanceState.containsKey(HOTEL)) {
      hotel = (JHotelDetailResult) savedInstanceState.get(HOTEL);
    } else {
      hotel = (JHotelDetailResult) this.getIntent().getSerializableExtra(HOTEL);
    }

    if (savedInstanceState != null && savedInstanceState.containsKey(ORDER)) {
      orderList = savedInstanceState.getParcelableArrayList(ORDER);
    } else {
      orderList = this.getIntent().getParcelableArrayListExtra(ORDER);
    }

    if (savedInstanceState != null && savedInstanceState.containsKey(CHECK_IN_DATE)) {
      checkInDate = savedInstanceState.getString(CHECK_IN_DATE);
    } else {
      checkInDate = this.getIntent().getStringExtra(CHECK_IN_DATE);
    }

    if (savedInstanceState != null && savedInstanceState.containsKey(CHECK_OUT_DATE)) {
      checkOutDate = savedInstanceState.getString(CHECK_OUT_DATE);
    } else {
      checkOutDate = this.getIntent().getStringExtra(CHECK_OUT_DATE);
    }
    initView();

    bindData();

    initNetwork();

    wecharApi = WXAPIFactory.createWXAPI(this, "wxb4ba3c02aa476ea1");
  }

  @Override protected void onPause() {
    super.onPause();
    eventPosterHelper.getBus().unregister(this);
  }

  @Override protected void onResume() {
    super.onResume();
    eventPosterHelper.getBus().register(this);
  }

  private void initView() {
    initToolbar();
    initBanner();
    initPayment();
    initViewPage();
  }

  private void initViewPage() {

    viewPager.setCanScroll(false);

    viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
      @Override public Fragment getItem(int position) {
        switch (position) {
          case 0:
            return GiftcarFragment.newInstance(hotel.getDataSet().getBaseInfo().getHotelID());
          case 1:
            return CardFragment.newInstance();
        }
        return null;
      }

      @Override public int getCount() {
        return 2;
      }
    });
  }

  private void initPayment() {

    getSupportFragmentManager().beginTransaction()
        .replace(R.id.payment_container, PaymentFragment.newInstance("abc", "abc"),
            FRAGMENT_TAG_PAYMRNT)
        .commit();
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    outState.putString(CHECK_IN_DATE, checkInDate);
    outState.putString(CHECK_OUT_DATE, checkOutDate);
    outState.putParcelableArrayList(ORDER, orderList);
    outState.putSerializable(HOTEL, hotel);

    super.onSaveInstanceState(outState);
  }

  @Override protected void onDestroy() {
    super.onDestroy();

    presenter.detachView();
  }

  @Override public void onBackPressed() {
    if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
      drawerLayout.closeDrawer(Gravity.RIGHT);
      return;
    }
    super.onBackPressed();
  }

  private void initNetwork() {
    if (NetworkUtil.isNetworkConnected(this)) {
      showProgressDialog();

      loadMemberProfile();

      if (orderList != null && orderList.size() > 0) {
        JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity roomCat =
            orderList.get(0).getRoomCat();
        loadHotelRoomPrice(roomCat.getHotelID(), roomCat.getRoomCatID(), checkInDate, checkOutDate);
      }
    } else {
      showError(this.getString(R.string.no_net));
    }
  }

  private void loadMemberProfile() {

    JMemberLoginResult result = BoilerplateApplication.get(this).getMemberLoginResult();
    String json =
        AiShangUtil.generMemberProfileParam(BoilerplateApplication.get(this).getMemberAccount(),
            result.getData().getCookies());

    presenter.loadProfile(2, json);
  }

  private void loadVacation() {
    if (NetworkUtil.isNetworkConnected(this)) {
      String cookie =
          BoilerplateApplication.get(this).getMemberLoginResult().getData().getCookies();
      String member = BoilerplateApplication.get(this).getMemberAccount();
      String json = AiShangUtil.generMyVacationListParam(member, cookie, "2012-01-01");
      presenter.loadVacation(0, json);
    } else {
      showError(this.getString(R.string.no_net));
    }
  }

  private void loadHotelRoomPrice(int hotelID, int roomCatID, String startDate, String endDate) {

    presenter.loadHotelPrice(0,
        AiShangUtil.gennerHotelRoomPriceParam(hotelID, roomCatID, startDate, endDate));
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
    toolbarTitle.setText(hotel.getDataSet().getBaseInfo().getName());
  }

  private void initBanner() {

    int[] size = CommonUtil.getHeightWithScreenWidth(this, 16, 9);
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size[0], size[1]);

    banner.setLayoutParams(layoutParams);

    List<String> ads = new ArrayList<String>();
    if (hotel.getDataSet().getBaseInfo().getImageTotal() > 0) {
      for (JHotelDetailResult.Data.SelectImage img : hotel.getDataSet().getSelectImageList()) {
        ads.add(AiShangService.IMG_URL + img.getUrl());
      }
    } else {
      ads.add(hotel.getDataSet().getBaseInfo().getImageUrl());
    }

    banner.setPages(new CBViewHolderCreator<NetImageHolderView>() {
      @Override public NetImageHolderView createHolder() {
        return new NetImageHolderView();
      }
    }, ads)
        //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        .setPageIndicator(new int[] { R.mipmap.ellipse_nomal, R.mipmap.ellipse_select })
        //设置指示器的方向
        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
  }

  private void bindData() {
    JHotelDetailResult.Data dataSet = hotel.getDataSet();
    name.setText(hotel.getDataSet().getBaseInfo().getName());
    bindStarLevel(dataSet.getBaseInfo().getStarLevel());
    //type.setText(dataSet.getBaseInfo().get);
    address.setText(dataSet.getBaseInfo().getAddress());

    //bindMyInfo();
  }

  private void bindMyInfo() {
    //JMemberLoginResult result = BoilerplateApplication.get(this).getMemberLoginResult();
    //
    //if (result == null) {
    //  Toast.makeText(this, R.string.error_not_login, Toast.LENGTH_LONG).show();
    //  this.finish();
    //} else {
    //  guestName.setText(result.getData().getMemberName());
    //  phone.setText(BoilerplateApplication.get(this).getMemberAccount());
    //  //email.setText();
    //}
  }

  private void bindRoom() {
    if (orderList != null) {

      Observable.from(orderList).map(new Func1<HotelOrder, Integer>() {
        @Override public Integer call(HotelOrder hotelOrder) {
          final Activity act = BuyHotelActivity.this;

          View view =
              LayoutInflater.from(BuyHotelActivity.this).inflate(R.layout.item_room_order, null);

          final RoomViewHolder holder = new RoomViewHolder(view);

          LinearLayout.LayoutParams params =
              new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                  LinearLayout.LayoutParams.WRAP_CONTENT);
          int spacing = act.getResources().getDimensionPixelSize(R.dimen.spacing_medium);
          params.setMargins(0, spacing, 0, 0);

          view.setLayoutParams(params);
          JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity roomcat = hotelOrder.getRoomCat();
          JHotelRoomCatListByhotelIDResult.GRoomTypeListEntity roomType = hotelOrder.getType();
          holder.price.setText("￥" + roomcat.getBasicPrice());
          holder.roomType.setText(roomType.getRoomTypeName());
          holder.roomNum.setText("" + hotelOrder.getRoomNum());
          holder.tvCheckInDate.setText(checkInDate);
          holder.tvCheckOutDate.setText(checkOutDate);

          orderContainer.addView(view);

          return roomcat.getBasicPrice() * hotelOrder.getRoomNum();
        }
      }).reduce(new Func2<Integer, Integer, Integer>() {
        @Override public Integer call(Integer integer, Integer integer2) {
          return integer + integer2;
        }
      }).subscribe(new Action1<Integer>() {
        @Override public void call(Integer integer) {

        }
      });
    }
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

  private void showProgressDialog() {
    progressDialog = DialogFactory.createProgressDialog(this, R.string.listview_loading);
    progressDialog.show();
  }

  private void dimissDialog() {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }
  }

  @Override public void showError(String error) {

    dimissDialog();
    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override public void showBuyResult(JMyVacationApplyResult result) {

    if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
      if (payType == PAYMENT.TONG_YONG_JI_FEN || payType == PAYMENT.HUAN_ZU_JI_FEN) {
        showMessage("预定成功！");
      } else if (payType == PAYMENT.ALIPAY) {
        //alipayV2("subject", "body", ((Integer) totalPrice.getTag()) + "", result.getReservID());
        showProgressDialog();
        presenter.alipaySign("assojourn@163.com", "2088221847375344", result.getReservID(),
            hotel.getDataSet().getBaseInfo().getName(), hotel.getDataSet().getBaseInfo().getName(),
            com.aishang.app.BuildConfig.DEBUG ? "0.01" : (int) totalPrice.getTag() + "",
            "http://www.51triplife.com/IosAlipay/notify_url.aspx", "mobile.securitypay.pay", "1",
            "utf-8", "30m");
      } else if (payType == PAYMENT.WECHAT) {
        PayReq req = new PayReq();
      }
    } else {
      showError(result.getResult());
    }
  }

  private void showMessage(String message) {
    Snackbar.make(layoutRoot, message, Snackbar.LENGTH_LONG).show();
  }

  @SuppressLint("HandlerLeak") private Handler mAlipayHandler = new Handler() {
    @SuppressWarnings("unused") public void handleMessage(Message msg) {
      switch (msg.what) {
        case SDK_PAY_FLAG: {
          @SuppressWarnings("unchecked") PayResult payResult =
              new PayResult((Map<String, String>) msg.obj);
          /**
           对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
           */
          String resultInfo = payResult.getResult();// 同步返回需要验证的信息
          String resultStatus = payResult.getResultStatus();
          // 判断resultStatus 为9000则代表支付成功
          if (TextUtils.equals(resultStatus, "9000")) {
            // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
            //Toast.makeText(BuyHotelActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
            showMessage("支付成功");
          } else {

            Log.e(TAG, "handleMessage: " + resultInfo);

            // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
            //Toast.makeText(BuyHotelActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
            showMessage("支付失败!,您可以在旅居订单中支付！");
          }
          break;
        }
        case SDK_AUTH_FLAG: {
          @SuppressWarnings("unchecked") AuthResult authResult =
              new AuthResult((Map<String, String>) msg.obj, true);
          String resultStatus = authResult.getResultStatus();

          // 判断resultStatus 为“9000”且result_code
          // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
          if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(),
              "200")) {
            // 获取alipay_open_id，调支付时作为参数extern_token 的value
            // 传入，则支付账户为该授权账户
            Toast.makeText(BuyHotelActivity.this,
                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()),
                Toast.LENGTH_SHORT).show();
          } else {
            // 其他状态值则为授权失败
            Toast.makeText(BuyHotelActivity.this,
                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                .show();
          }
          break;
        }
        default:
          break;
      }
    }
  };

  private void wevhatPay(String content) {
    try {
      JSONObject json = new JSONObject(content);
      if (null != json && !json.has("retcode")) {
        PayReq req = new PayReq();
        //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
        req.appId = json.getString("appid");
        req.partnerId = json.getString("partnerid");
        req.prepayId = json.getString("prepayid");
        req.nonceStr = json.getString("noncestr");
        req.timeStamp = json.getString("timestamp");
        req.packageValue = json.getString("package");
        req.sign = json.getString("sign");
        req.extData = "app data"; // optional
        Toast.makeText(BuyHotelActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        wecharApi.sendReq(req);
      } else {
        Log.d("PAY_GET", "返回错误" + json.getString("retmsg"));
        Toast.makeText(BuyHotelActivity.this, "返回错误" + json.getString("retmsg"), Toast.LENGTH_SHORT)
            .show();
      }
    } catch (Exception e) {

    }
  }

  public void alipayV2(final String orderInfo) {

    ///**
    // * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
    // * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
    // * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
    // *
    // * orderInfo的获取必须来自服务端；
    // */

    dimissDialog();
    //Map<String, String> orderMap = CommonUtil.transStringToMap(orderInfo);
    //
    //Map<String, String> params =
    //    OrderInfoUtil2_0.buildOrderParamMap("", orderMap.get("subject"), orderMap.get("body"),
    //        orderMap.get("total_fee"), orderMap.get("out_trade_no"));
    //String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
    ////String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE);
    //final String order = orderParam + "&sign=" + (orderMap.get("sign")).replace("\"", "");
    final String order = orderInfo.replace("\"", "");
    Log.d(TAG, "alipayV2: " + order);

    Runnable payRunnable = new Runnable() {

      @Override public void run() {
        PayTask alipay = new PayTask(BuyHotelActivity.this);
        Map<String, String> result = alipay.payV2(order, true);
        Log.i("msp", result.toString());

        Message msg = new Message();
        msg.what = SDK_PAY_FLAG;
        msg.obj = result;
        mAlipayHandler.sendMessage(msg);
      }
    };

    Thread payThread = new Thread(payRunnable);
    payThread.start();
  }

  @Override public void showGetProfileSuccess(JMemberProfileResult result) {
    if (result == null) {
      Toast.makeText(this, R.string.error_not_login, Toast.LENGTH_LONG).show();
      this.finish();
    } else {
      guestName.setText(result.getData().getMemberName());
      phone.setText(result.getData().getMobilePhone());
      email.setText(result.getData().getEmail());
      ((PaymentFragment) getSupportFragmentManager().findFragmentByTag(
          FRAGMENT_TAG_PAYMRNT)).setMemberProfile(result);
      //loadVacation();
    }
  }

  @Override public void showHotelRoomPrice(final JHotelRoomPriceResult result) {
    dimissDialog();
    Observable.from(orderList).first().subscribe(new Action1<HotelOrder>() {
      @Override public void call(HotelOrder hotelOrder) {
        final Activity act = BuyHotelActivity.this;

        View view =
            LayoutInflater.from(BuyHotelActivity.this).inflate(R.layout.item_room_order, null);

        holder = new RoomViewHolder(view);

        LinearLayout.LayoutParams params =
            new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        int spacing = act.getResources().getDimensionPixelSize(R.dimen.spacing_medium);
        params.setMargins(0, spacing, 0, 0);

        view.setLayoutParams(params);
        JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity roomcat = hotelOrder.getRoomCat();
        JHotelRoomCatListByhotelIDResult.GRoomTypeListEntity roomType = hotelOrder.getType();
        holder.price.setText("￥" + result.getTotalPrice());
        holder.roomType.setText(roomType.getRoomTypeName());
        holder.roomNum.setText("" + hotelOrder.getRoomNum());
        holder.tvCheckInDate.setText(checkInDate);
        holder.tvCheckOutDate.setText(checkOutDate);

        orderContainer.addView(view);
      }
    });

    totalPrice.setText("￥" + result.getTotalPrice());
    totalPrice.setTag(result.getTotalPrice());

    ((PaymentFragment) getSupportFragmentManager().findFragmentByTag(
        FRAGMENT_TAG_PAYMRNT)).setPriceTotal(result.getTotalPrice());
  }

  @Override public void showGetVacationSuccess(JMyVacationListResult result) {

    dimissDialog();

    vacationResult = result;
    card.setText(result.getMyVaList()[0].getCardID());
    card.setTag(0);
  }

  @Override public void showVacationEmpty() {

    dimissDialog();
    card.setClickable(false);
    Snackbar.make(layoutRoot, "没有查询到旅居卡,暂时不能预订！", Snackbar.LENGTH_INDEFINITE)
        .setAction(android.R.string.ok, new View.OnClickListener() {
          @Override public void onClick(View v) {
            BuyHotelActivity.this.finish();
          }
        })
        .show();
  }

  @Override public void alipaySign(String sign) {

    alipayV2(sign);
  }

  @OnClick({ R.id.card, R.id.submint }) public void onClick(View view) {

    switch (view.getId()) {
      case R.id.card:
        Observable.from(vacationResult.getMyVaList())
            .map(new Func1<JMyVacationListResult.JMyVacationListMyVaList, String>() {
              @Override public String call(
                  JMyVacationListResult.JMyVacationListMyVaList jMyVacationListMyVaList) {
                return jMyVacationListMyVaList.getCardID();
              }
            })
            .toList()
            .subscribe(new Action1<List<String>>() {
              @Override public void call(final List<String> strings) {
                DialogFactory.createSingleChoiceDialog(BuyHotelActivity.this,
                    strings.toArray(new String[strings.size()]), (int) card.getTag(),
                    new DialogInterface.OnClickListener() {
                      @Override public void onClick(DialogInterface dialog, int which) {
                        card.setTag(which);
                        card.setText(strings.get(which));
                      }
                    }, "选择旅居卡").show();
              }
            });
        break;
      case R.id.submint:
        onSubmit();
        break;
    }
  }

  private void onSubmit() {
    if (totalPrice.getTag() != null) {
      JMemberLoginResult result = BoilerplateApplication.get(this).getMemberLoginResult();

      JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity roomCat =
          orderList.get(0).getRoomCat();

      int creditByCard =
          (int) totalPrice.getTag();// payType == PAYMENT.HUAN_ZU_JI_FEN ? (int) totalPrice.getTag() : 0;

      int pay = 1;

      if (payType == PAYMENT.TONG_YONG_JI_FEN) {
        pay = 1;
      } else if (payType == PAYMENT.HUAN_ZU_JI_FEN) {
        pay = 2;
      } else {
        pay = 4;
      }

      int guestCount = holder.numPicker.getValue();
      presenter.postData(0,
          AiShangUtil.generMyVacationApplyParam(BoilerplateApplication.get(this).getMemberAccount(),
              result.getData().getCookies(), hotel.getDataSet().getBaseInfo().getName(),
              checkInDate, checkOutDate, phone.getText().toString(), guestName.getText().toString(),
              hotel.getDataSet().getBaseInfo().getHotelID(), roomCat.getRoomCatID(), creditByCard,
              guestCount, pay, selectGift != null ? selectGift.getGUID() : "",
              selectCard != null ? selectCard.getID() : 0, creditByCard));
    }
  }

  @Subscribe public void onGiftcarSelect(JMemberGiftcardResult.MemberGiftcardListEntity gift) {

    ((PaymentFragment) getSupportFragmentManager().findFragmentByTag(
        FRAGMENT_TAG_PAYMRNT)).onGiftCardSelect(gift);

    drawerLayout.closeDrawer(Gravity.RIGHT);
    selectGift = gift;
  }

  @Subscribe public void onMyCarSelect(JMyVacationListResult.MemberexCardListEntity card) {

    ((PaymentFragment) getSupportFragmentManager().findFragmentByTag(
        FRAGMENT_TAG_PAYMRNT)).onMyCardSelect(card);

    drawerLayout.closeDrawer(Gravity.RIGHT);

    selectCard = card;
  }

  @Subscribe public void onUnselect(Integer index) {
    drawerLayout.closeDrawer(Gravity.RIGHT);

    switch (index) {
      case 1:
        ((PaymentFragment) getSupportFragmentManager().findFragmentByTag(
            FRAGMENT_TAG_PAYMRNT)).onGiftCardUnselect();
        selectGift = null;
        break;
      case 2:
        ((PaymentFragment) getSupportFragmentManager().findFragmentByTag(
            FRAGMENT_TAG_PAYMRNT)).onMyCardUnselect();
        selectCard = null;
        break;
    }
  }

  static class RoomViewHolder {
    @Bind(R.id.tv_check_in_date) TextView tvCheckInDate;
    @Bind(R.id.tv_check_out_date) TextView tvCheckOutDate;
    @Bind(R.id.room_type) TextView roomType;
    @Bind(R.id.room_num) TextView roomNum;
    @Bind(R.id.price) TextView price;
    @Bind(R.id.numPicker) HorizontalNumberPicker numPicker;

    RoomViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }
}