package com.aishang.app.ui.TravelDetail;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.NetWorkType;
import com.aishang.app.util.NetworkUtil;

public class TravelDetailActivity extends BaseActivity {
  public static final String NEWS_ID = "news_id";
  public static final String NEWS_URL = "news_url";
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.webview) WebView webview;
  @Bind(R.id.layoutRoot) CoordinatorLayout layoutRoot;
  private Dialog progress;

  private int newsID;

  private String newsUrl;

  /**
   * Return an Intent to start this Activity.
   */
  public static Intent getStartIntent(Context context, int newsId, String newsUrl) {
    Intent intent = new Intent(context, TravelDetailActivity.class);
    intent.putExtra(NEWS_ID, newsId);
    intent.putExtra(NEWS_URL, newsUrl);
    return intent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_travel_detail);
    ButterKnife.bind(this);

    newsID = this.getIntent().getIntExtra(NEWS_ID, -1);
    newsUrl = this.getIntent().getStringExtra(NEWS_URL);

    initToolbar();
    initWebView();

    if (NetworkUtil.isNetworkConnected(this)) {
      //if (avloadingIndicatorView.getVisibility() != View.VISIBLE) {
      //  avloadingIndicatorView.setVisibility(View.VISIBLE);
      //}
      //
      //if (noDataHotel.getVisibility() == View.VISIBLE) {
      //  noDataHotel.setVisibility(View.GONE);
      //}

      progress = DialogFactory.createProgressDialog(this, R.string.listview_loading);
      progress.show();

      webview.loadUrl(AiShangService.AiShangHost + getIntent().getStringExtra(NEWS_URL));
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

  private void initWebView() {
    webview.setVisibility(WebView.VISIBLE);
    webview.getSettings().setSupportZoom(true);
    webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    webview.getSettings().setBuiltInZoomControls(true);
    webview.getSettings().setJavaScriptEnabled(true);
    webview.clearCache(true);
    webview.setWebViewClient(new WebViewClient() {
      @Override public void onPageFinished(WebView view, String url) {
        if (progress != null && progress.isShowing()) {
          progress.dismiss();
        }
      }
    });
  }
}
