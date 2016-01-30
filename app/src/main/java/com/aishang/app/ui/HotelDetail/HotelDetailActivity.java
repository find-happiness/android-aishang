package com.aishang.app.ui.HotelDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JHotelDetailResult;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.NetworkUtil;
import com.aishang.app.widget.DrawableCenterButton;
import com.bigkoo.convenientbanner.ConvenientBanner;
import java.util.Date;
import javax.inject.Inject;

public class HotelDetailActivity extends BaseActivity implements HotelDetailMvpView {

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
  @Bind(R.id.btn_look_you_ji) TextView btnLookYouJi;
  @Bind(R.id.description) WebView description;
  @Bind(R.id.map) View map;

  private String hotelName;
  private int hotelID;
  private long checkInDate;
  private long checkOutDate;

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

    initToolbar();
    proLoad();
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

      asynHotelDetail();
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

  @Override public void showError(String error) {

  }

  @Override public void showHotelDetailError(String error) {

  }

  @Override public void bindDataToView(JHotelDetailResult result) {



    setWebViewContent(description, result.getDataSet().getBaseInfo().getDescription());
  }

  /**
   * set webview content
   * @param content content html
   */
  private void setWebViewContent(final WebView wv, String content) {
    content = CommonUtil.htmldecode(content);
    wv.getSettings().setDefaultTextEncodingName("utf-8");
    wv.loadData(content, "text/html; charset=UTF-8", null);// 这种写法可以正确解码

    // updateExpandable(wv);

    wv.setWebViewClient(new WebViewClient() {
      boolean loadingFinished = true;
      boolean redirect = false;

      @Override public boolean shouldOverrideUrlLoading(WebView view, String urlNewString) {
        if (!loadingFinished) {
          redirect = true;
        }

        loadingFinished = false;
        wv.loadUrl(urlNewString);
        return true;
      }

      public void onPageStarted(WebView view, String url) {
        loadingFinished = false;
        // SHOW LOADING IF IT ISNT ALREADY VISIBLE
      }

      @Override public void onPageFinished(WebView view, String url) {
        if (!redirect) {
          loadingFinished = true;
        }

        if (loadingFinished && !redirect) {
          wv.setLayoutParams(
              new android.widget.LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                  LinearLayout.LayoutParams.WRAP_CONTENT));
        } else {
          redirect = false;
        }
      }
    });
  }
}