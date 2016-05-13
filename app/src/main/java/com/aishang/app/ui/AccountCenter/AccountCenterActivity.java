package com.aishang.app.ui.AccountCenter;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.data.model.JCommonIntegralResult;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.NetworkUtil;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.viewpager.SViewPager;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class AccountCenterActivity extends BaseActivity implements AccountCenterMvpView {

  @Inject AccountCenterPresenter presenter;

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.tabmain_indicator) FixedIndicatorView mIndicator;
  @Bind(R.id.view_pager) SViewPager mViewPager;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;
  private IndicatorViewPager indicatorViewPager;

  private Dialog progressDialog;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    presenter.attachView(this);

    setContentView(R.layout.activity_account_center);
    ButterKnife.bind(this);
    initToolbar();
    initViewPager();
    loadData();
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

  private void initViewPager() {
    indicatorViewPager = new IndicatorViewPager(mIndicator, mViewPager);
    indicatorViewPager.setPageOffscreenLimit(3);
    mViewPager.setCanScroll(true);
    mIndicator.setScrollBar(new ColorBar(this, Color.rgb(0x45, 0xa5, 0xe2), 4));
  }

  private void loadAccount() {
    JMemberLoginResult result = BoilerplateApplication.get(this).getMemberLoginResult();
    String json =
        AiShangUtil.generCommonIntegral(BoilerplateApplication.get(this).getMemberAccount(),
            result.getData().getCookies(), 1, 10, "", "");
    presenter.loadData(1, json);
  }

  private void loadData() {
    if (NetworkUtil.isNetworkConnected(this)) {
      showDialog();
      loadAccount();
    } else {
      showError(this.getString(R.string.no_net));
    }
  }

  private void showDialog() {
    progressDialog = DialogFactory.createProgressDialog(this, R.string.listview_loading);
    progressDialog.show();
  }

  private void dimissDialog() {
    if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.detachView();
  }

  @Override public void showError(String error) {
    dimissDialog();
    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override public void showSuccess(JCommonIntegralResult result) {
    dimissDialog();
    indicatorViewPager.setAdapter(
        new PageAdapter(getSupportFragmentManager(), this, (float) result.getAwardLeft(),
            result.getCreditLeft()));
  }

  public class PageAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
    private String[] names = {
        "通用积分", "换住积分",
    };
    private LayoutInflater inflater;
    private float awardLeft;
    private int creditLeft;

    public PageAdapter(FragmentManager fragmentManager, AccountCenterActivity context,
        float awardLeft, int creditLeft) {
      super(fragmentManager);
      this.awardLeft = awardLeft;
      this.creditLeft = creditLeft;

      inflater = LayoutInflater.from(context);
    }

    @Override public int getCount() {
      return names.length;
    }

    @Override public View getViewForTab(int position, View convertView, ViewGroup container) {
      if (convertView == null) {
        convertView = inflater.inflate(R.layout.tab_account_center, container, false);
      }

      ViewHolder viewHolder = new ViewHolder(convertView);

      if (position == 0) {
        viewHolder.left.setText(awardLeft + "");
      } else {
        viewHolder.left.setText(creditLeft + "");
      }

      viewHolder.name.setText(names[position]);

      return convertView;
    }

    @Override public Fragment getFragmentForPage(int position) {
      switch (position) {
        case 0:
          return AwardDetailFragment.newInstance();
        case 1:
          return AwardDetailFragment.newInstance();
      }
      return null;
    }

    class ViewHolder {
      @Bind(R.id.left) TextView left;
      @Bind(R.id.name) TextView name;

      ViewHolder(View view) {
        ButterKnife.bind(this, view);
      }
    }
  }
}
