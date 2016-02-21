package com.aishang.app.ui.BuyLouPan;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.R;
import com.aishang.app.data.model.JLoupanProductDetailResult;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.NetImageHolderView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BuyLouPanActivity extends BaseActivity {

  private static final String LOUPAN = "loupan";

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.convenientBanner) ConvenientBanner banner;
  @Bind(R.id.name) TextView name;
  @Bind(R.id.et_name) EditText etName;
  @Bind(R.id.phone) EditText phone;
  @Bind(R.id.et_address) EditText etAddress;
  @Bind(R.id.date) TextView date;
  @Bind(R.id.et_yuyu_address) EditText etYuyuAddress;
  @Bind(R.id.submint) Button submint;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;

  private JLoupanProductDetailResult loupan;

  public static Intent getStartIntent(Context ctx, JLoupanProductDetailResult loupan) {
    Intent intent = new Intent(ctx, BuyLouPanActivity.class);
    intent.putExtra(LOUPAN, loupan);
    return intent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_buy_lou_pan);
    ButterKnife.bind(this);
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    if (savedInstanceState != null && savedInstanceState.containsKey(LOUPAN)) {
      loupan = (JLoupanProductDetailResult) savedInstanceState.get(LOUPAN);
    } else {
      loupan = (JLoupanProductDetailResult) this.getIntent().getSerializableExtra(LOUPAN);
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

    setBanner(loupan.getDataSet().getImageList());
  }

  @OnClick(R.id.date) void onclickDate() {
    DialogFactory.createDatePickerDialog(this, Calendar.getInstance(),
        new DatePickerDialog.OnDateSetListener() {
          @Override
          public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            date.setText(year + "年" + monthOfYear + "月" + dayOfMonth + "日");
          }
        }).show();
  }

  private void setBanner(JLoupanProductDetailResult.Image[] images) {
    List<String> localImages = new ArrayList<>();
    for (JLoupanProductDetailResult.Image img : images) {
      localImages.add(img.getUrl());
    }

    banner.setPages(new CBViewHolderCreator<NetImageHolderView>() {
      @Override public NetImageHolderView createHolder() {
        return new NetImageHolderView();
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
}
