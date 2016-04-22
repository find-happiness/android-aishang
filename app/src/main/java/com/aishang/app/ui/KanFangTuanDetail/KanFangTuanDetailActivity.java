package com.aishang.app.ui.KanFangTuanDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.R;
import com.aishang.app.data.model.JHotelDetailResult;
import com.aishang.app.data.model.JMreActivityDetailResult;
import com.aishang.app.data.model.JMreActivityListResult;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.CanTuan.CanTuanActivity;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.NetImageHolderView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class KanFangTuanDetailActivity extends BaseActivity implements KanFangTuanDetailMvpView {
  public static final String ACTIVITY = "activity";

  @Inject KanFangTuanDetailPresenter presenter;

  @Bind(R.id.toolbar_title) TextView toolbarTitle;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.convenientBanner) ConvenientBanner convenientBanner;
  @Bind(R.id.tv_title_name) TextView tvTitleName;
  @Bind(R.id.price) TextView price;
  @Bind(R.id.postion) TextView postion;
  @Bind(R.id.time) TextView time;
  @Bind(R.id.enrollTime) TextView enrollTime;
  @Bind(R.id.wv_description) WebView wvDescription;
  @Bind(R.id.wv_know) WebView wvKnow;
  @Bind(R.id.avloadingIndicatorView) AVLoadingIndicatorView avloadingIndicatorView;
  @Bind(R.id.no_data_hotel) TextView noDataHotel;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;

  JMreActivityListResult.JActivityItem activityItem;

  /**
   * Return an Intent to start this Activity.
   */
  public static Intent getStartIntent(Context context,
      JMreActivityListResult.JActivityItem activityItem) {
    Intent intent = new Intent(context, KanFangTuanDetailActivity.class);
    intent.putExtra(ACTIVITY, activityItem);
    return intent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    setContentView(R.layout.activity_kan_fang_duan_detail);
    ButterKnife.bind(this);

    if (savedInstanceState != null && savedInstanceState.containsKey(ACTIVITY)) {
      activityItem = (JMreActivityListResult.JActivityItem) savedInstanceState.get(ACTIVITY);
    } else {
      activityItem =
          (JMreActivityListResult.JActivityItem) this.getIntent().getSerializableExtra(ACTIVITY);
    }

    initToolbar();
    setTitle();
    setPostion();
    setPrice();
    setTime();
    initWebViewKnow();
    initWebViewDescription(activityItem.getDescription());
    setGalleryImg();
  }

  @Override protected void onDestroy() {
    presenter.detachView();
    super.onDestroy();
  }

  @Override public void showError(String error) {

  }

  @Override public void showKanFangTuanDetailError(String error) {

  }

  @Override public void bindDataToView(JMreActivityDetailResult result) {

  }

  @Override public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
    outState.putSerializable(ACTIVITY, activityItem);
    super.onSaveInstanceState(outState, outPersistentState);
  }

  @OnClick(R.id.cantuan) void onclickCanTuan() {
    Intent intent = CanTuanActivity.getStartIntent(this, activityItem.getActivityID());
    this.startActivity(intent);
  }

  @OnClick(R.id.take_phone) void onTakePhone() {

    if (!CommonUtil.isCanTakePhone(this)) {
      CommonUtil.showSnackbar("您的设备不能打电话!", layoutRoot);
      return;
    }

    if (!TextUtils.isEmpty(activityItem.getContactPhone())) {
      CommonUtil.intentToCall(activityItem.getContactPhone(), this);
    } else {
      CommonUtil.showSnackbar("没有获取到电话号码!", layoutRoot);
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

  private void initWebViewKnow() {
    wvKnow.setVisibility(WebView.VISIBLE);
    // wv.getSettings().setSupportZoom(true);
    wvKnow.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    // wv.getSettings().setBuiltInZoomControls(true);
    wvKnow.loadUrl("file:///android_asset/can_tuan_xue_zhi.html");
  }

  private void initWebViewDescription(String content) {
    wvKnow.setVisibility(WebView.VISIBLE);
    // wv.getSettings().setSupportZoom(true);
    wvKnow.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    content = CommonUtil.htmldecode(content);
    wvDescription.getSettings().setDefaultTextEncodingName("utf-8");
    //wv.loadData(content, "text/html; charset=UTF-8", null);// 这种写法可以正确解码

    //content = content.replaceAll("<img src=",
    //    "<img width=\"100%\" style=\"background:url(http://ueditor.baidu.com/ueditor/lang/zh-cn/images/localimage.png) no-repeat center center;border:1px solid #ddd\" src=");

    wvDescription.loadDataWithBaseURL(AiShangService.AiShangHost, content, "text/html",
        "charset=UTF-8", null);
  }

  private void setTitle() {
    tvTitleName.setText(activityItem.getTitle());
    toolbarTitle.setText(activityItem.getTitle());
  }

  private void setTime() {
    time.setText(
        activityItem.getStartTime().split(" ")[0] + "-" + activityItem.getEndTime().split(" ")[0]);
    enrollTime.setText(
        activityItem.getEnrollStartTime().split(" ")[0] + "-" + activityItem.getEnrollEndTime()
            .split(" ")[0]);
  }

  private void setPrice() {
    price.setText(activityItem.getFee() + "元");
  }

  private void setPostion() {
    postion.setText(activityItem.getPosition());
  }

  private void setGalleryImg() {

    List<String> ads = new ArrayList<String>();
    ads.add(AiShangService.IMG_URL + activityItem.getImageUrl());

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
}
