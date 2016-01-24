package com.aishang.app.ui.CashWithDrawApply;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.aishang.app.R;
import com.aishang.app.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CashWithDrawApplyActivity extends BaseActivity {

  @Bind(R.id.toolbar)
  Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_cash_with_draw_apply);

    ButterKnife.bind(this);

    initToolbar();
  }

  private void initToolbar()
  {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    toolbar.setNavigationIcon(R.mipmap.iconfont_livesvg);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });
  }
}
