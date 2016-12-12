package com.aishang.app.ui.MemberGiftcard;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.data.model.JMemberGiftcardResult;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.CashWithDrawApply.CashWithDrawFragment;
import com.aishang.app.ui.CashWithDrawApply.RechargeFragment;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.BusProvider;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.NetworkUtil;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

public class MemberGiftcardActivity extends BaseActivity implements MemberGiftcarMvpView {

  private static final String TAG = "MemberGiftcardActivity";
  @Bind(R.id.toolbar_title) TextView toolbarTitle;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.app_bar_layout) AppBarLayout appBarLayout;
  @Bind(R.id.moretab_indicator) ScrollIndicatorView mIndicator;
  @Bind(R.id.indicatorView) FrameLayout indicatorView;
  @Bind(R.id.moretab_viewPager) ViewPager mViewPager;

  @Inject MemberGiftcarPresenter presenter;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;
  private IndicatorViewPager indicatorViewPager;
  ProgressDialog progressDialog;

  ArrayList<JMemberGiftcardResult.MemberGiftcardListEntity> memberGiftcardStatus1 =
      new ArrayList<>();
  ArrayList<JMemberGiftcardResult.MemberGiftcardListEntity> memberGiftcardStatus2 =
      new ArrayList<>();

  final String[] titles = new String[] { "未使用", "已使用" };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    presenter.attachView(this);
    setContentView(R.layout.activity_member_giftcard);
    ButterKnife.bind(this);
    initView();
    if (NetworkUtil.isNetworkConnected(this)) {
      showProgressDialog();
      loadMemberGiftcard();
    } else {
      CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
    }
  }

  @Override public void showError(String error) {
    dimissDialog();
  }

  private void showProgressDialog() {
    progressDialog = DialogFactory.createProgressDialog(this, R.string.listview_loading);
    progressDialog.show();
  }

  private void dimissDialog() {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }
  }

  @Override public void loadMemberGiftcardSuccess(JMemberGiftcardResult result) {
    dimissDialog();
    Observable.from(result.getMemberGiftcardList())
        .groupBy(new Func1<JMemberGiftcardResult.MemberGiftcardListEntity, Integer>() {
          @Override public Integer call(
              JMemberGiftcardResult.MemberGiftcardListEntity memberGiftcardListBean) {
            return memberGiftcardListBean.getStatus();
          }
        })
        .subscribe(
            new Subscriber<GroupedObservable<Integer, JMemberGiftcardResult.MemberGiftcardListEntity>>() {
              @Override public void onCompleted() {
                Log.i(TAG, "onCompleted  ------ >: ");

                indicatorViewPager.setAdapter(new IndicatorViewPager.IndicatorFragmentPagerAdapter(
                    getSupportFragmentManager()) {

                  @Override public int getCount() {
                    return titles.length;
                  }

                  @Override
                  public View getViewForTab(int position, View convertView, ViewGroup container) {
                    if (convertView == null) {
                      convertView = LayoutInflater.from(MemberGiftcardActivity.this)
                          .inflate(R.layout.tab_top, container, false);
                    }
                    TextView textView = (TextView) convertView;
                    textView.setText(titles[position % titles.length]);
                    textView.setPadding(20, 0, 20, 0);
                    return convertView;
                  }

                  @Override public Fragment getFragmentForPage(int position) {
                    if (position == 0) {
                      return MemberGiftcarFragment.newInstance(memberGiftcardStatus1);
                    } else {
                      return MemberGiftcarFragment.newInstance(memberGiftcardStatus2);
                    }
                  }
                });
              }

              @Override public void onError(Throwable e) {

              }

              @Override public void onNext(
                  final GroupedObservable<Integer, JMemberGiftcardResult.MemberGiftcardListEntity> observable) {

                observable.subscribe(new Action1<JMemberGiftcardResult.MemberGiftcardListEntity>() {
                  @Override public void call(JMemberGiftcardResult.MemberGiftcardListEntity bean) {
                    if (observable.getKey() == 0) {
                      memberGiftcardStatus1.add(bean);
                    } else if (observable.getKey() == 1) {
                      memberGiftcardStatus2.add(bean);
                    }
                  }
                });
              }
            });
  }

  @Override public void loadMemberGiftcardEmpty() {
    dimissDialog();
    Snackbar.make(layoutRoot, "您还没有优惠券！", Snackbar.LENGTH_LONG)
        .setAction(android.R.string.ok, new View.OnClickListener() {
          @Override public void onClick(View v) {
            onBackPressed();
          }
        })
        .show();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.detachView();
    ButterKnife.unbind(this);
  }

  private void initView() {
    initTabView();
    initToolbar();
  }

  private void initTabView() {

    indicatorViewPager = new IndicatorViewPager(mIndicator, mViewPager);
    mIndicator.setScrollBar(new ColorBar(this, Color.rgb(0x45, 0xa5, 0xe2),
        getResources().getDimensionPixelSize(R.dimen.spacing_small)));
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

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
      ViewCompat.setElevation(appBarLayout,
          getResources().getDimensionPixelSize(R.dimen.spacing_small));
    }
  }

  private String gennerNetWorkParam() {
    String cookie = BoilerplateApplication.get(this).getMemberLoginResult().getData().getCookies();

    String member = BoilerplateApplication.get(this).getMemberAccount();
    return AiShangUtil.generMemberGiftcardParam(member, cookie, "", "", "", "");
  }

  private void loadMemberGiftcard() {

    presenter.loadMemberGiftcard(0, gennerNetWorkParam());
  }
}
