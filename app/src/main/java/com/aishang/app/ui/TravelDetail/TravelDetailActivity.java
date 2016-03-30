package com.aishang.app.ui.TravelDetail;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.R;
import com.aishang.app.data.model.JNewsDetailResult;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.NetworkUtil;
import com.aishang.app.widget.SoftInputLinearLayout;
import javax.inject.Inject;

public class TravelDetailActivity extends BaseActivity implements TravelDetailMvpView {
  public static final String NEWS_ID = "news_id";
  public static final String NEWS_URL = "news_url";
  private static final String TAG = "TravelDetailActivity";

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.webview) WebView webview;
  @Bind(R.id.layoutRoot) SoftInputLinearLayout layoutRoot;

  @Inject TravelDetailPresenter presenter;

  @Bind(R.id.ll_action_btn_bottom) LinearLayout llActionBtnBottom;
  @Bind(R.id.et_pinglun) AppCompatEditText etPinglun;
  @Bind(R.id.pinglunContainer) RelativeLayout pinglunContainer;

  private Dialog progress;

  private int newsID;

  private String newsUrl;

  private boolean keyboardListenersAttached = false;

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
    this.getActivityComponent().inject(this);
    presenter.attachView(this);
    setContentView(R.layout.activity_travel_detail);
    ButterKnife.bind(this);
    newsID = this.getIntent().getIntExtra(NEWS_ID, -1);
    newsUrl = this.getIntent().getStringExtra(NEWS_URL);
    initToolbar();
    initWebView();
    attachKeyboardListeners();
    if (NetworkUtil.isNetworkConnected(this)) {
      presenter.loadTravelDetail(0, AiShangUtil.generTravelDetail(newsID));
    } else {
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
    webview.getSettings().setSupportZoom(false);
    webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    webview.getSettings().setBuiltInZoomControls(false);
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

  @Override public void loadTraveDetailFinish(JNewsDetailResult result) {
    AiShangUtil.setWebViewContent(webview, result.getContent());
  }

  @Override public void showDialog() {
    progress = DialogFactory.createProgressDialog(this, R.string.listview_loading);
    progress.show();
  }

  @Override public void showError(String error) {
    CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
  }

  @Override public void dimissDialog() {
    if (progress != null && progress.isShowing()) progress.dismiss();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.detachView();
  }

  @Override public void onBackPressed() {
    super.onBackPressed();

    CommonUtil.closeKeybord(etPinglun, this);
  }

  @OnClick(R.id.pinglun) public void onClick() {

    InputMethodManager inputMethodManager =
        (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
  }

  protected void attachKeyboardListeners() {
    if (keyboardListenersAttached) {
      return;
    }
    layoutRoot.setOnSoftKeyboardListener(new SoftInputLinearLayout.OnSoftKeyboardListener() {
      @Override public void onShown() {
        pinglunContainer.setVisibility(View.VISIBLE);
        llActionBtnBottom.setVisibility(View.GONE);
        etPinglun.requestFocus();
      }

      @Override public void onHidden() {
        pinglunContainer.setVisibility(View.GONE);
        llActionBtnBottom.setVisibility(View.VISIBLE);
      }
    });

    keyboardListenersAttached = true;
  }
}
