package com.aishang.app.ui.MyBuyAndSale;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.ui.base.BaseActivity;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.viewpager.SViewPager;

public class BuyAndSaleActivity extends BaseActivity {
  private int[] tabIcons = { R.drawable.tab_rent_selector, R.drawable.tab_sale_selector };
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.tabmain_viewPager) SViewPager mViewPager;
  @Bind(R.id.tabmain_indicator) FixedIndicatorView mIndicator;
  private IndicatorViewPager indicatorViewPager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_buy_and_sale);
    ButterKnife.bind(this);
    initToolbar();
    initViewPager();
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
    mIndicator.setSplitMethod(FixedIndicatorView.SPLITMETHOD_EQUALS);
    mIndicator.setScrollBar(new ColorBar(getApplicationContext(), Color.rgb(0x45, 0xa5, 0xe2), 0,
        ScrollBar.Gravity.CENTENT_BACKGROUND));
    indicatorViewPager.setAdapter(
        new IndicatorViewPager.IndicatorFragmentPagerAdapter(this.getSupportFragmentManager()) {

          @Override public int getCount() {
            return tabIcons.length;
          }

          @Override public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
              convertView = (LinearLayout) LayoutInflater.from(BuyAndSaleActivity.this)
                  .inflate(R.layout.tab_ren_sale, container, false);
            }
            ImageView img = (ImageView) convertView.findViewById(R.id.tab);
            img.setImageResource(tabIcons[position]);
            return convertView;
          }

          @Override public Fragment getFragmentForPage(int position) {
            if (position == 0) {
              return RentFragment.newInstance("", "");
            } else {
              return SaleFragment.newInstance();
            }
          }
        });
  }
}
