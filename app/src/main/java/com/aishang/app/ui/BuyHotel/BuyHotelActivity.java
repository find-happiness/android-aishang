package com.aishang.app.ui.BuyHotel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.data.model.HotelOrder;
import com.aishang.app.data.model.JHotelDetailResult;
import com.aishang.app.data.model.JHotelRoomCatListByhotelIDResult;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.data.model.JMemberProfileResult;
import com.aishang.app.data.model.JMyVacationListResult;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.NetImageHolderView;
import com.aishang.app.util.NetworkUtil;
import com.aishang.app.util.NumberPickerDelegate;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

public class BuyHotelActivity extends BaseActivity implements BuyHotelMvpView {

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
  private JHotelDetailResult hotel;
  private ArrayList<HotelOrder> orderList;
  private String checkInDate;
  private String checkOutDate;
  JMyVacationListResult vacationResult;

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

    initToolbar();
    initBanner();
    bindData();

    loadMemberProfile();
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    outState.putString(CHECK_IN_DATE, checkInDate);
    outState.putString(CHECK_OUT_DATE, checkOutDate);
    outState.putParcelableArrayList(ORDER, orderList);
    outState.putSerializable(HOTEL, hotel);

    super.onSaveInstanceState(outState);
  }

  private void loadMemberProfile() {
    if (NetworkUtil.isNetworkConnected(this)) {
      showProgressDialog();

      JMemberLoginResult result = BoilerplateApplication.get(this).getMemberLoginResult();
      String json =
          AiShangUtil.generMemberProfileParam(BoilerplateApplication.get(this).getMemberAccount(),
              result.getData().getCookies());

      presenter.loadProfile(2, json);
    } else {
      showError(this.getString(R.string.no_net));
    }
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
    bindRoom();
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
          holder.guestNum.setText("1");
          holder.guestNum.setTag(1);

          holder.guestNum.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
              DialogFactory.createNumberPicker(act, (int) holder.guestNum.getTag(), 10, 1, "入住人数",
                  new NumberPickerDelegate() {
                    @Override public void OnPick(NumberPicker picker, int num) {
                      holder.guestNum.setTag(num);
                      holder.guestNum.setText("" + num);
                    }
                  }).show();
            }
          });
          orderContainer.addView(view);

          return roomcat.getBasicPrice() * hotelOrder.getRoomNum();
        }
      }).reduce(new Func2<Integer, Integer, Integer>() {
        @Override public Integer call(Integer integer, Integer integer2) {
          return integer + integer2;
        }
      }).subscribe(new Action1<Integer>() {
        @Override public void call(Integer integer) {
          totalPrice.setText("￥" + integer.toString());
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
    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override public void showSuccess() {

  }

  @Override public void showGetProfileSuccess(JMemberProfileResult result) {
    if (result == null) {
      Toast.makeText(this, R.string.error_not_login, Toast.LENGTH_LONG).show();
      this.finish();
    } else {
      guestName.setText(result.getData().getMemberName());
      phone.setText(result.getData().getMobilePhone());
      email.setText(result.getData().getEmail());

      loadVacation();
    }
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

  @OnClick(R.id.card) public void onClick() {

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
  }

  static class RoomViewHolder {
    @Bind(R.id.tv_check_in_date) TextView tvCheckInDate;
    @Bind(R.id.tv_check_out_date) TextView tvCheckOutDate;
    @Bind(R.id.room_type) TextView roomType;
    @Bind(R.id.room_num) TextView roomNum;
    @Bind(R.id.price) TextView price;
    @Bind(R.id.guest_num) TextView guestNum;

    RoomViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }
}