package com.aishang.app.ui.TravelDetail;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.data.model.JCriticismListResult;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.data.model.JNewsDetailResult;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.ui.login.LoginActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.NetworkUtil;
import com.aishang.app.widget.LinearListView;
import com.aishang.app.widget.SoftInputLinearLayout;
import com.aishang.app.widget.SuperSwipeRefreshLayout;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;
import javax.inject.Inject;

public class TravelDetailActivity extends BaseActivity implements TravelDetailMvpView {
  public static final String NEWS_ID = "news_id";
  public static final String NEWS_URL = "news_url";
  public static final String NEWS_IMG = "news_img";
  private static final String TAG = "TravelDetailActivity";

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.webview) WebView webview;
  @Bind(R.id.layoutRoot) SoftInputLinearLayout layoutRoot;

  @Inject TravelDetailPresenter presenter;

  @Bind(R.id.ll_action_btn_bottom) LinearLayout llActionBtnBottom;
  @Bind(R.id.et_pinglun) AppCompatEditText etPinglun;
  @Bind(R.id.pinglunContainer) RelativeLayout pinglunContainer;
  @Bind(R.id.image) ImageView image;
  @Bind(R.id.head) CircleImageView head;
  @Bind(R.id.user_name) TextView userName;
  @Bind(R.id.title) TextView title;
  @Bind(R.id.backNum) TextView backNum;
  @Bind(R.id.lookNum) TextView lookNum;
  @Bind(R.id.swipe_refresh) SuperSwipeRefreshLayout swipeRefresh;
  @Bind(R.id.criticismList) LinearListView criticismList;

  // Footer View
  private ProgressBar footerProgressBar;
  private TextView footerTextView;
  private ImageView footerImageView;

  private Dialog progress;

  private int newsID;

  private String newsUrl = "";
  private String newsImgUrl = "";

  private boolean keyboardListenersAttached = false;

  CriticismAdapter criticismAdapter;

  /**
   * Return an Intent to start this Activity.
   */
  public static Intent getStartIntent(Context context, int newsId, String newsUrl) {
    Intent intent = new Intent(context, TravelDetailActivity.class);
    intent.putExtra(NEWS_ID, newsId);
    intent.putExtra(NEWS_URL, newsUrl);
    return intent;
  }

  /**
   * Return an Intent to start this Activity.
   */
  public static Intent getStartIntent(Context context, int newsId, String newsUrl, String imgUrl) {
    Intent intent = new Intent(context, TravelDetailActivity.class);
    intent.putExtra(NEWS_ID, newsId);
    intent.putExtra(NEWS_URL, newsUrl);
    intent.putExtra(NEWS_IMG, imgUrl);
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
    newsImgUrl = this.getIntent().getStringExtra(NEWS_IMG);
    initView();
    attachKeyboardListeners();
    autoLoad();
  }

  private void initView() {
    initToolbar();
    initWebView();
    initBannerImg();
    initSwipeRefresh();
  }

  private void initSwipeRefresh() {
    swipeRefresh.setFooterView(createFooterView());
    swipeRefresh.setTargetScrollWithLayout(false);
    swipeRefresh.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {

      @Override public void onRefresh() {
        swipeRefresh.setRefreshing(false);
      }

      @Override public void onPullDistance(int distance) {
        // pull distance
      }

      @Override public void onPullEnable(boolean enable) {
      }
    });

    swipeRefresh.setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {

      @Override public void onLoadMore() {
        if (NetworkUtil.isNetworkConnected(TravelDetailActivity.this)) {
          footerTextView.setText("正在加载...");
          footerImageView.setVisibility(View.GONE);
          footerProgressBar.setVisibility(View.VISIBLE);
          loadCriticismList(criticismAdapter == null ? 1 : criticismAdapter.getCount() + 1);
        } else {
          CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
        }
      }

      @Override public void onPushEnable(boolean enable) {
        footerTextView.setText(enable ? "松开加载" : "上拉加载");
        footerImageView.setVisibility(View.VISIBLE);
        footerImageView.setRotation(enable ? 0 : 180);
      }

      @Override public void onPushDistance(int distance) {
        // TODO Auto-generated method stub

      }
    });
  }

  private View createFooterView() {
    View footerView =
        LayoutInflater.from(swipeRefresh.getContext()).inflate(R.layout.layout_footer, null);
    footerProgressBar = (ProgressBar) footerView.findViewById(R.id.footer_pb_view);
    footerImageView = (ImageView) footerView.findViewById(R.id.footer_image_view);
    footerTextView = (TextView) footerView.findViewById(R.id.footer_text_view);
    footerProgressBar.setVisibility(View.GONE);
    footerImageView.setVisibility(View.VISIBLE);
    footerImageView.setImageResource(R.drawable.down_arrow);
    footerTextView.setText("上拉加载更多...");
    return footerView;
  }

  private void initBannerImg() {
    int[] size = CommonUtil.getHeightWithScreenWidth(this, 16, 9);
    image.setLayoutParams(new FrameLayout.LayoutParams(size[0], size[1]));

    Picasso.with(this)
        .load(AiShangService.IMG_URL + newsImgUrl)
        .placeholder(R.mipmap.banner)
        .error(R.mipmap.banner)
        .into(image);
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

  private void loadData() {
    if (NetworkUtil.isNetworkConnected(this)) {
      swipeRefresh.setRefreshing(true);
      presenter.loadTravelDetail(0, AiShangUtil.generTravelDetail(newsID));
      loadCriticismList(1);
    } else {
      CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
    }
  }

  private void autoLoad() {
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        loadData();
      }
    }, 100);
  }

  private void loadCriticismList(int start) {
    presenter.getCriticismList(0, AiShangUtil.generCriticismListParam(newsID, start, 3));
  }

  @Override public void loadTraveDetailFinish(JNewsDetailResult result) {

    if (swipeRefresh.isRefreshing()) swipeRefresh.setRefreshing(false);

    AiShangUtil.setWebViewContent(webview, result.getContent());
    title.setText(result.getTitle());
    userName.setText(result.getAuthorName() + "");
  }

  @Override public void showDialog() {
    progress = DialogFactory.createProgressDialog(this, R.string.listview_loading);
    progress.show();
  }

  @Override public void showError(String error) {
    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override public void dimissDialog() {
    if (progress != null && progress.isShowing()) progress.dismiss();
  }

  @Override public void hitFinish() {
    CommonUtil.showSnackbar("成功点赞", layoutRoot);
  }

  @Override public void favoriteFinish() {
    CommonUtil.showSnackbar("收藏成功", layoutRoot);
  }

  @Override public void criticismFinish() {
    CommonUtil.showSnackbar("评论成功", layoutRoot);

    if (criticismAdapter != null) {
      criticismAdapter.getCriticismList().clear();
      criticismAdapter.notifyDataSetChanged();
    }

    loadCriticismList(1);
  }

  @Override public void criticismListFinish(JCriticismListResult result) {

    footerImageView.setVisibility(View.VISIBLE);
    footerProgressBar.setVisibility(View.GONE);
    swipeRefresh.setLoadMore(false);

    if (criticismAdapter == null) {
      criticismAdapter = new CriticismAdapter(this, result.getCriticismList());
      criticismList.setAdapter(criticismAdapter);
    } else {
      List<JCriticismListResult.CriticismListEntity> criticismList =
          criticismAdapter.getCriticismList();
      criticismList.addAll(result.getCriticismList());
      criticismAdapter.notifyDataSetChanged();
    }
  }

  @Override public void dimissCriticismList() {
    footerImageView.setVisibility(View.VISIBLE);
    footerProgressBar.setVisibility(View.GONE);
    swipeRefresh.setLoadMore(false);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.detachView();
  }

  @Override public void onBackPressed() {
    super.onBackPressed();
    CommonUtil.closeKeybord(etPinglun, this);
  }

  protected void attachKeyboardListeners() {
    if (keyboardListenersAttached) {
      return;
    }
    layoutRoot.setOnSoftKeyboardListener(new SoftInputLinearLayout.OnSoftKeyboardListener() {
      @Override public void onShown() {
        pinglunContainer.setVisibility(View.VISIBLE);
        llActionBtnBottom.setVisibility(View.GONE);
        //etPinglun.requestFocus();
      }

      @Override public void onHidden() {
        //etPinglun.clearFocus();
        pinglunContainer.setVisibility(View.GONE);
        llActionBtnBottom.setVisibility(View.VISIBLE);
      }
    });

    keyboardListenersAttached = true;
  }

  @OnClick({ R.id.btn_dianzhan, R.id.btn_shoucang, R.id.btn_pinglun, R.id.btn_share, R.id.send })
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_dianzhan:
        onclickDianZang();
        break;
      case R.id.btn_shoucang:
        onclickShouCang();
        break;
      case R.id.btn_pinglun:
        onClickPingLun();
        break;
      case R.id.btn_share:
        onClickShare();
        break;
      case R.id.send:
        onclickSend();
        break;
    }
  }

  private void onClickPingLun() {
    if (checkLogin()) {
      InputMethodManager inputMethodManager =
          (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    } else {
      showLoginDialog();
    }
  }

  private void onClickShare() {

    //TODO set share content
    ShareSDK.initSDK(this);
    OnekeyShare oks = new OnekeyShare();
    //关闭sso授权
    oks.disableSSOWhenAuthorize();

    // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
    //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
    // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
    oks.setTitle("分享");
    // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
    oks.setTitleUrl("http://sharesdk.cn");
    // text是分享文本，所有平台都需要这个字段
    oks.setText("我是分享文本");
    // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
    oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
    // url仅在微信（包括好友和朋友圈）中使用
    oks.setUrl("http://sharesdk.cn");
    // comment是我对这条分享的评论，仅在人人网和QQ空间使用
    oks.setComment("我是测试评论文本");
    // site是分享此内容的网站名称，仅在QQ空间使用
    oks.setSite(getString(R.string.app_name));
    // siteUrl是分享此内容的网站地址，仅在QQ空间使用
    oks.setSiteUrl("http://sharesdk.cn");

    // 启动分享GUI
    oks.show(this);
  }

  private void onclickDianZang() {
    if (checkLogin()) {
      String[] datas = getMemberAndCookies();
      if (datas == null) return;
      presenter.postNewsHits(0, AiShangUtil.generNewsHitsParam(datas[0], datas[1], newsID));
    } else {
      showLoginDialog();
    }
  }

  private void onclickShouCang() {
    if (checkLogin()) {
      String[] datas = getMemberAndCookies();
      if (datas == null) return;
      presenter.postFavoriteEdit(0, AiShangUtil.generFavoriteEditParam(datas[0], datas[1], newsID));
    } else {
      showLoginDialog();
    }
  }

  private void onclickSend() {
    if (checkLogin()) {
      if (TextUtils.isEmpty(etPinglun.getText())) {
        CommonUtil.showSnackbar("评论不能为空！", layoutRoot);
        return;
      }

      String[] datas = getMemberAndCookies();
      if (datas == null) return;
      presenter.postNewsCriticism(0, AiShangUtil.generNewsCriticismParam(datas[0], datas[1], newsID,
          etPinglun.getText().toString().trim()));
    } else {
      showLoginDialog();
    }
  }

  private boolean checkLogin() {
    return BoilerplateApplication.get(this).getMemberLoginResult() != null;
  }

  private void IntentToLogin() {
    Intent intent = new Intent(this, LoginActivity.class);
    this.startActivity(intent);
  }

  private void showLoginDialog() {
    DialogFactory.createSimpleDialog(this, R.string.error_not_login, R.string.intent_login,
        android.R.string.ok, android.R.string.cancel, new DialogInterface.OnClickListener() {
          @Override public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            IntentToLogin();
          }
        }, new DialogInterface.OnClickListener() {
          @Override public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
          }
        }).show();
  }

  private String[] getMemberAndCookies() {

    if (!checkLogin()) return null;

    String[] datas = new String[2];
    datas[0] = BoilerplateApplication.get(this).getMemberAccount();

    JMemberLoginResult result = BoilerplateApplication.get(this).getMemberLoginResult();
    datas[1] = result.getData().getCookies();
    return datas;
  }
}
