package com.aishang.app.ui.BuyHotel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JHotelDetailResult;
import com.aishang.app.data.model.JLoupanProductDetailResult;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.NetImageHolderView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import java.util.ArrayList;
import java.util.List;

public class BuyHotelActivity extends BaseActivity {

  private static final String HOTEL = "hotel";

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.convenientBanner) ConvenientBanner banner;
  @Bind(R.id.name) TextView name;
  @Bind(R.id.et_room) TextView etRoom;
  @Bind(R.id.guest_name) EditText guestName;
  @Bind(R.id.phone) EditText phone;
  @Bind(R.id.email) EditText email;
  @Bind(R.id.submint) Button submint;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;

  private JHotelDetailResult hotel;
  public static Intent getStartIntent(Context ctx, JHotelDetailResult loupan) {
    Intent intent = new Intent(ctx, BuyHotelActivity.class);
    intent.putExtra(HOTEL, loupan);
    return intent;
  }
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_buy_hotel);
    ButterKnife.bind(this);
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    if (savedInstanceState != null && savedInstanceState.containsKey(HOTEL)) {
      hotel = (JHotelDetailResult) savedInstanceState.get(HOTEL);
    } else {
      hotel = (JHotelDetailResult) this.getIntent().getSerializableExtra(HOTEL);
    }

    initToolbar();
    initBanner();
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
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size[0], size[1]);

    banner.setLayoutParams(layoutParams);

    List<String> ads = new ArrayList<String>();
    if (hotel.getDataSet().getBaseInfo().getImageTotal() > 0) {
      for (JHotelDetailResult.Data.SelectImage img : hotel.getDataSet().getSelectImageList()) {
        ads.add(img.getUrl());
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
}