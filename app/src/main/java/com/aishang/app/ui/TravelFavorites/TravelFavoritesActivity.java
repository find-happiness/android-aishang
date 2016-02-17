package com.aishang.app.ui.TravelFavorites;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.ui.CashWithDrawApply.CashWithDrawFragment;
import com.aishang.app.ui.CashWithDrawApply.RechargeFragment;
import com.aishang.app.ui.base.BaseActivity;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;

public class TravelFavoritesActivity extends BaseActivity {

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.moretab_indicator) ScrollIndicatorView mIndicator;
  @Bind(R.id.moretab_viewPager) ViewPager mViewPager;
  private IndicatorViewPager indicatorViewPager;
  final String[] titles = new String[] { "已发布", "已参与", "已收藏" };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_travel_favorites);
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
    mIndicator.setScrollBar(new ColorBar(this, Color.rgb(0x45, 0xa5, 0xe2), 4));
    indicatorViewPager.setPageOffscreenLimit(2);
    indicatorViewPager.setAdapter(
        new IndicatorViewPager.IndicatorFragmentPagerAdapter(this.getSupportFragmentManager()) {

          @Override public int getCount() {
            return titles.length;
          }

          @Override public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
              convertView = LayoutInflater.from(TravelFavoritesActivity.this)
                  .inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(titles[position % titles.length]);
            textView.setPadding(20, 0, 20, 0);
            return convertView;
          }

          @Override public Fragment getFragmentForPage(int position) {
            return FavoritesFragment.newInstance();
          }
        });
  }
}
