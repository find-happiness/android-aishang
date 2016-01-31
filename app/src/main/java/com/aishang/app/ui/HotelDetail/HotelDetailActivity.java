package com.aishang.app.ui.HotelDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JHotelDetailResult;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.NetworkUtil;
import com.aishang.app.widget.DrawableCenterButton;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
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
    setGalleryImg(result);
    AiShangUtil.setWebViewContent(description, result.getDataSet().getBaseInfo().getDescription());
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
    CollapsingToolbarLayout.LayoutParams layoutParams =
        new CollapsingToolbarLayout.LayoutParams(size[0], size[1]);

    convenientBanner.setLayoutParams(layoutParams);

    convenientBanner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
      @Override public LocalImageHolderView createHolder() {
        return new LocalImageHolderView();
      }
    }, ads)
        //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        .setPageIndicator(new int[] { R.mipmap.ellipse_nomal, R.mipmap.ellipse_select })
            //设置指示器的方向
        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
  }

  public class LocalImageHolderView implements Holder<String> {
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
}