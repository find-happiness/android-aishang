package com.aishang.app.ui.main.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.main.MainActivity;
import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.squareup.picasso.Picasso;
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
      setUserData(result);
    } else {
      headViewHolder.rlLoginAfter.setVisibility(View.GONE);
      headViewHolder.llActionButton.setVisibility(View.VISIBLE);
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
    @Bind(R.id.iv_user_head) ImageView ivUserHead;
    @Bind(R.id.tv_user_name) TextView tvUserName;
    @Bind(R.id.tv_member_account) TextView tvMemberAccount;
    @Bind(R.id.tv_awardLeft) TextView tvAwardLeft;
    @Bind(R.id.tv_creditLeft) TextView tvCreditLeft;
    @Bind(R.id.rl_login_after) RelativeLayout rlLoginAfter;
    @Bind(R.id.ll_action_button) LinearLayout llActionButton;
    @Bind(R.id.img_ping_lun) ImageView imgPingLun;
    @Bind(R.id.img_share) ImageView imgShare;

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
