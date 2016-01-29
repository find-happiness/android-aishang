package com.aishang.app.ui.main.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.data.model.JMemberProfileResult;
import com.aishang.app.data.model.JMemberStatisticsResult;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.main.MainActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.Constants;
import com.ecloud.pulltozoomview.PullToZoomBase;
import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import javax.inject.Inject;

/**
 * Created by song on 2016/1/16.
 */
public class MineFragment extends Fragment implements MineMvpView {
  private static final String TAG = "MineFragment";
  @Inject MinePresenter mMinePresenter;

  @Bind(R.id.scroll_view) PullToZoomScrollViewEx scrollView;
  ContentViewHolder contentholder;
  HeadViewHolder headViewHolder;
  @Bind(R.id.layoutRoot) CoordinatorLayout layoutRoot;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((MainActivity) getActivity()).getActivityComponent().inject(this);
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    View view = inflater.inflate(R.layout.fragment_mine, null);
    ButterKnife.bind(this, view);
    mMinePresenter.attachView(this);
    initScrollView();
    return view;
  }

  @Override public void onResume() {
    super.onResume();
    JMemberLoginResult result =
        BoilerplateApplication.get(this.getActivity()).getMemberLoginResult();

    if (result != null) {
      headViewHolder.rlLoginAfter.setVisibility(View.VISIBLE);
      headViewHolder.llActionButton.setVisibility(View.GONE);
      headViewHolder.ivHeadDefault.setVisibility(View.GONE);
      setUserData(result);
    } else {
      headViewHolder.rlLoginAfter.setVisibility(View.GONE);
      headViewHolder.llActionButton.setVisibility(View.VISIBLE);
      headViewHolder.ivHeadDefault.setVisibility(View.VISIBLE);
    }
  }

  private void setUserData(JMemberLoginResult result) {
    JMemberLoginResult.Data data = result.getData();
    Picasso.with(this.getActivity())
        .load(AiShangService.IMG_URL + data.getImageUrl())
        .error(R.mipmap.ic_img_user_default)
        .placeholder(R.mipmap.ic_img_user_default)
        .into(headViewHolder.ivUserHead);

    headViewHolder.tvAwardLeft.setText(data.getAwardLeft() + "");
    headViewHolder.tvCreditLeft.setText(data.getCreditLeft() + "");
    headViewHolder.tvMemberAccount.setText(
        getString(R.string.member_account, data.getMemberID() + ""));
    headViewHolder.tvUserName.setText(data.getMemberName());
  }

  private void initScrollView() {
    View headView =
        LayoutInflater.from(this.getContext()).inflate(R.layout.profile_head_view, null, false);
    View zoomView =
        LayoutInflater.from(this.getContext()).inflate(R.layout.profile_zoom_view, null, false);
    View contentView =
        LayoutInflater.from(this.getContext()).inflate(R.layout.profile_content_view, null, false);

    contentholder = new ContentViewHolder(contentView);
    headViewHolder = new HeadViewHolder(headView);

    //ButterKnife.bind(this,contentView);
    //ButterKnife.bind(this,zoomView);

    scrollView.setHeaderView(headView);
    scrollView.setZoomView(zoomView);
    scrollView.setScrollContentView(contentView);

    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    int mScreenHeight = localDisplayMetrics.heightPixels;
    int mScreenWidth = localDisplayMetrics.widthPixels;
    LinearLayout.LayoutParams localObject =
        new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
    scrollView.setHeaderLayoutParams(localObject);

    scrollView.setOnPullZoomListener(new PullToZoomBase.OnPullZoomListener() {
      @Override public void onPullZooming(int newScrollValue) {

      }

      @Override public void onPullZoomEnd() {
        JMemberLoginResult result = ((BoilerplateApplication) BoilerplateApplication.get(
            MineFragment.this.getActivity())).getMemberLoginResult();

        String phone = ((BoilerplateApplication) BoilerplateApplication.get(
            MineFragment.this.getActivity())).getMemberAccount();

        String psw = ((BoilerplateApplication) BoilerplateApplication.get(
            MineFragment.this.getActivity())).getMemberPsw();
        if (result != null && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(psw)) {

          String json = AiShangUtil.gennerMemberStatistics(psw, phone);
          mMinePresenter.loadProfile(false, 2, json);

          headViewHolder.loadingView.setVisibility(View.VISIBLE);
        }
      }
    });
  }

  /**
   * 普通用户和CRM用户登录失败后统一的的回调函数，根据错误做相应的处理
   */
  public void loginFaild(String errorStr) {

    headViewHolder.loadingView.setVisibility(View.GONE);

    if (Constants.RESULT_MBNOTEXISTORPSWERROR.equals(errorStr)) {// 用户名或密码错误

      CommonUtil.showSnackbar(R.string.login_nameorpsw_wrong, layoutRoot);
    } else if (Constants.RESULT_MBLOCKED.equals(errorStr)) {// 用户被锁定

      // Toast.makeText(this, R.string.login_memberLocked,
      // Toast.LENGTH_LONG)
      // .show();
      CommonUtil.showSnackbar(R.string.login_memberLocked, layoutRoot);
    } else if (Constants.RESULT_MBNOTACTIVED.equals(errorStr)) {
      CommonUtil.showSnackbar(R.string.login_memberNotActived, layoutRoot);
      // Toast.makeText(this, R.string.login_memberNotActived,
      // Toast.LENGTH_LONG).show();
    } else if (Constants.RESULT_MBCHANGEPSW.equals(errorStr)) {
      //intentToChangePsw();
      Snackbar.make(layoutRoot, R.string.login_changePassword, Snackbar.LENGTH_LONG)
          .setAction(R.string.dialog_action_ok, new View.OnClickListener() {
            @Override public void onClick(View v) {
              mMinePresenter.intentToChangePsw();
            }
          })
          .setDuration(Snackbar.LENGTH_LONG)
          .show();
    } else {
      //BaseHelper.toastError(this, errorStr);
      CommonUtil.showSnackbar(errorStr, layoutRoot);
    }
  }

  @Override public void updataMember(JMemberStatisticsResult result) {

    //TODO 更改接口
    headViewHolder.loadingView.setVisibility(View.GONE);

    //JMemberLoginResult.Data data = result.getData();
    JMemberStatisticsResult.Data data = result.getData();
    //Picasso.with(this.getActivity())
    //    .load(AiShangService.IMG_URL + data.getImageUrl())
    //    .error(R.mipmap.ic_img_user_default)
    //    .placeholder(R.mipmap.ic_img_user_default)
    //    .into(headViewHolder.ivUserHead);

    headViewHolder.tvAwardLeft.setText(data.getAwardLeft() + "");
    headViewHolder.tvCreditLeft.setText(data.getCreditLeft() + "");
    headViewHolder.tvMemberAccount.setText(
        getString(R.string.member_account, data.getMemberID() + ""));
    //headViewHolder.tvUserName.setText(data.getMemberName());

  }

  @Override public void showError(String error) {
    headViewHolder.loadingView.setVisibility(View.GONE);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  class ContentViewHolder {
    @OnClick(R.id.btn_gen_ren_zhong_xin) void geRenZhongXinClick() {
      mMinePresenter.intentToMemberCenter();
    }

    @OnClick(R.id.btn_zhang_hu_zhong_xin) void zhangHuZhongXinClick() {
      mMinePresenter.intentToAccoutCenter();
    }

    @OnClick(R.id.btn_wo_de_ding_dang) void woDeDingDanClick() {
      mMinePresenter.intentToMyOrder();
    }

    @OnClick(R.id.btn_wo_de_fang_chang) void woDeFangChangClick() {
      mMinePresenter.intentToMyHouse();
    }

    @OnClick(R.id.btn_jin_ji_ren_zhong_xin) void jinJiRenZhongXinClick() {
      mMinePresenter.intentToBrokerCenter();
    }

    @OnClick(R.id.btn_you_ji_shou_chang_jia) void youJiShouChangJiaClick() {
      mMinePresenter.intentToTravelFavorites();
    }

    @OnClick(R.id.btn_chong_zi_ti_xian) void chongZiTiXianClick() {
      mMinePresenter.intentToCashWithDrawApply();
    }

    @OnClick(R.id.btn_wo_de_zu_shou) void woDeZuShouClick() {
      mMinePresenter.intentToMyBuyAndSale();
    }

    @OnClick(R.id.btn_guan_yu_wo_men) void guanYuWoMenClick() {
      mMinePresenter.intentToAbout();
    }

    public ContentViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }

  /**
   * This class contains all butterknife-injected Views & Layouts from layout file
   * 'profile_head_view.xml'
   * for easy to all layout elements.
   *
   * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers
   *         (http://github.com/avast)
   */
  class HeadViewHolder {
    @Bind(R.id.iv_user_head) CircleImageView ivUserHead;
    @Bind(R.id.tv_user_name) TextView tvUserName;
    @Bind(R.id.tv_member_account) TextView tvMemberAccount;
    @Bind(R.id.tv_awardLeft) TextView tvAwardLeft;
    @Bind(R.id.tv_creditLeft) TextView tvCreditLeft;
    @Bind(R.id.rl_login_after) RelativeLayout rlLoginAfter;
    @Bind(R.id.ll_action_button) LinearLayout llActionButton;
    @Bind(R.id.img_ping_lun) ImageView imgPingLun;
    @Bind(R.id.img_share) ImageView imgShare;
    @Bind(R.id.head_default) CircleImageView ivHeadDefault;
    @Bind(R.id.avloadingIndicatorView) AVLoadingIndicatorView loadingView;

    @OnClick(R.id.tv_register) void registerClick() {
      mMinePresenter.intentToRegister();
    }

    @OnClick(R.id.tv_login) void loginClick() {
      mMinePresenter.intentToLogin();
    }

    HeadViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }
}
