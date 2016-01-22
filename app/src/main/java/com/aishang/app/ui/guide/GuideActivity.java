package com.aishang.app.ui.guide;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.aishang.app.R;
import com.aishang.app.ui.login.LoginActivity;
import com.aishang.app.ui.main.MainActivity;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;

public class GuideActivity extends AppCompatActivity {
  private IndicatorViewPager indicatorViewPager;
  private LayoutInflater inflate;

  @Override protected void onCreate(Bundle arg0) {
    super.onCreate(arg0);
    setContentView(R.layout.activity_guide);
    ViewPager viewPager = (ViewPager) findViewById(R.id.guide_viewPager);
    Indicator indicator = (Indicator) findViewById(R.id.guide_indicator);
    indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
    inflate = LayoutInflater.from(getApplicationContext());
    indicatorViewPager.setAdapter(adapter);
  }

  private IndicatorViewPager.IndicatorPagerAdapter adapter =
      new IndicatorViewPager.IndicatorViewPagerAdapter() {
        private int[] images = { R.mipmap.p1, R.mipmap.p2, R.mipmap.p3 };

        @Override public View getViewForTab(int position, View convertView, ViewGroup container) {
          if (convertView == null) {
            convertView = inflate.inflate(R.layout.tab_guide, container, false);
          }

          return convertView;
        }

        @Override public View getViewForPage(int position, View convertView, ViewGroup container) {
          if (convertView == null) {
            convertView = new ImageView(getApplicationContext());
            convertView.setLayoutParams(
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT));
          }
          ImageView iv = (ImageView)convertView;
          ((ImageView)convertView).setImageResource(images[position]);
          iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

          if (position >= images.length-1) {
            convertView.setOnClickListener(new View.OnClickListener() {
              @Override public void onClick(View v) {
                Activity act = GuideActivity.this;
                Intent intent = MainActivity.getStartIntent(act, true);
                act.startActivity(intent);
              }
            });
          }

          return convertView;
        }

        @Override public int getCount() {
          return images.length;
        }
      };
}
