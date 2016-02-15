package com.aishang.app.ui.RecommenCustomer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;

public class RecommenCustomerActivity extends AppCompatActivity {

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.view_pager) ViewPager viewPager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recommen_customer);
    ButterKnife.bind(this);

    viewPager.setAdapter(new FragmentPagerAdapter(this.getSupportFragmentManager()) {
      @Override public Fragment getItem(int position) {
        return new BrokerCenterFragment();
      }

      @Override public int getCount() {
        return 1;
      }
    });
  }
}
