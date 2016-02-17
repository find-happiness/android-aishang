package com.aishang.app.ui.CashWithDrawApply;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.BusProvider;
import com.aishang.app.util.CommonUtil;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;

public class CashWithDrawApplyActivity extends BaseActivity {
  private static final String TAG = "CashWithDrawActivity";
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.moretab_indicator) ScrollIndicatorView mIndicator;
  @Bind(R.id.moretab_viewPager) ViewPager mViewPager;
  private IndicatorViewPager indicatorViewPager;

  final String[] titles = new String[] { "充值", "申请提现" };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    setContentView(R.layout.activity_cash_with_draw_apply);
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    ButterKnife.bind(this);
    indicatorViewPager = new IndicatorViewPager(mIndicator, mViewPager);
    mIndicator.setScrollBar(new ColorBar(this, Color.rgb(0x45, 0xa5, 0xe2), 4));
    indicatorViewPager.setAdapter(
        new IndicatorViewPager.IndicatorFragmentPagerAdapter(this.getSupportFragmentManager()) {

          @Override public int getCount() {
            return 2;
          }

          @Override public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
              convertView = LayoutInflater.from(CashWithDrawApplyActivity.this)
                  .inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(titles[position % titles.length]);
            textView.setPadding(20, 0, 20, 0);
            return convertView;
          }

          @Override public Fragment getFragmentForPage(int position) {
            if (position == 0) {
              return RechargeFragment.newInstance();
            } else {
              return CashWithDrawFragment.newInstance();
            }
          }
        });
    //mViewPager.setCanScroll(true);

    initToolbar();
  }

  @Override protected void onResume() {
    super.onResume();
    BusProvider.getInstance().register(this);
  }

  @Override protected void onPause() {
    super.onPause();
    BusProvider.getInstance().unregister(this);
  }

  private void initToolbar() {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        Log.i(TAG, "onMenuItemClick: ----->");
        if (item.getItemId() == R.id.action_push) {
          BusProvider.getInstance().post(produceEvent());
          return true;
        }
        return false;
      }
    });
    toolbar.setNavigationIcon(R.mipmap.iconfont_livesvg);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onBackPressed();
      }
    });
  }

  @Override public void onBackPressed() {
    super.onBackPressed();
    CommonUtil.hideSoftInput(this);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_push, menu);
    return true;
  }

  public PostEvent produceEvent() {
    // Provide an initial value for location based on the last known position.
    return new PostEvent(indicatorViewPager.getCurrentItem());
  }
}
