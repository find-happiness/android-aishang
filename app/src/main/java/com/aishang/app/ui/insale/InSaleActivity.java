package com.aishang.app.ui.insale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;

public class InSaleActivity extends AppCompatActivity {

  @Bind(R.id.toolbar)
  Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_in_sale);
    ButterKnife.bind(this);
    initToolbar();
  }

  private void initToolbar()
  {

    toolbar.setTitle("");

    this.setSupportActionBar(toolbar);

    toolbar.setNavigationIcon(R.mipmap.iconfont_livesvg);
  }
}

