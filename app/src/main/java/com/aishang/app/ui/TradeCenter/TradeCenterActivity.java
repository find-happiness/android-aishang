package com.aishang.app.ui.TradeCenter;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.ui.base.BaseActivity;

public class TradeCenterActivity extends BaseActivity {

  @Bind(R.id.toolbar_title) TextView toolbarTitle;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.content_container) LinearLayout contentContainer;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_trade_center);
    ButterKnife.bind(this);

  }
}
