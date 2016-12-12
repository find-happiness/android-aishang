package com.aishang.app.ui.main.more;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import com.aishang.app.R;
import com.aishang.app.ui.main.EventUpdata;
import com.aishang.app.ui.main.MainActivity;
import com.aishang.app.util.BusProvider;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.widget.ItemMore;
import com.squareup.otto.Subscribe;
import javax.inject.Inject;

/**
 * Created by song on 2016/1/16.
 */
public class MoreFragment extends Fragment implements MoreMvpView {

  @Inject MorePresenter morePresenter;
  @Bind(R.id.layoutRoot) LinearLayout layoutRoot;

  ProgressDialog progressDialog;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((MainActivity) this.getActivity()).getActivityComponent().inject(this);
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    View view = inflater.inflate(R.layout.fragment_more, null);
    morePresenter.attachView(this);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onResume() {
    super.onResume();
    BusProvider.getInstance().register(this);
  }

  @Override public void onPause() {
    super.onPause();
    BusProvider.getInstance().register(this);
  }

  @Override public void onDestroy() {
    morePresenter.detachView();
    super.onDestroy();
  }

  @OnClick(R.id.btn_check_updata) void checkUpdataClick() {

    progressDialog = DialogFactory.createProgressDialog(this.getActivity(), R.string.check_updata);
    progressDialog.show();
    morePresenter.checkVersion();
  }

  @OnClick(R.id.btn_suggestion) void suggestionClick() {
    morePresenter.intentToSuggestion();
  }

  @OnClick(R.id.btn_setting) void settingClcik() {
    morePresenter.intentToSetting();
  }

  @OnClick(R.id.btn_share) void shareClick() {
    showShare();
  }

  @Override public void showError(String error) {
    CommonUtil.showSnackbar(error, this.layoutRoot);
  }

  @Override public void updata(String url) {
    eventUpdata(url);
  }

  @Override public void noUpdata() {
    DialogFactory.createSimpleOkErrorDialog(this.getContext(), R.string.dialog_Ok_title,
        R.string.app_no_new_update).show();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  public void eventUpdata(String url) {
    BusProvider.getInstance().post(new EventUpdata(url));
  }

  @Override public void dimissDialog() {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }
  }

  private void showShare() {
    ShareSDK.initSDK(this.getActivity());
    OnekeyShare oks = new OnekeyShare();
    //关闭sso授权
    oks.disableSSOWhenAuthorize();

    // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
    //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
    // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
    //oks.setTitle(getString(R.string.share));
    oks.setTitle("爱尚旅居");
    // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
    oks.setTitleUrl("http://www.aishanglvju.com/");
    // text是分享文本，所有平台都需要这个字段
    oks.setText("爱尚旅居-改变一生的度假方式！");
    // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
    //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
    // url仅在微信（包括好友和朋友圈）中使用
    oks.setUrl("http://www.aishanglvju.com/");
    // comment是我对这条分享的评论，仅在人人网和QQ空间使用
    oks.setComment("爱尚旅居-改变一生的度假方式！");
    // site是分享此内容的网站名称，仅在QQ空间使用
    oks.setSite(getString(R.string.app_name));
    // siteUrl是分享此内容的网站地址，仅在QQ空间使用
    oks.setSiteUrl("http://www.aishanglvju.com/");

    // 启动分享GUI
    oks.show(this.getActivity());
  }
}
