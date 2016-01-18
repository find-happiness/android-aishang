package com.aishang.app.ui.insale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.ui.base.BaseActivity;

import javax.inject.Inject;

public class InSaleActivity extends BaseActivity implements InSaleMvpView{

  @Inject
  InSalePresenter mPersenter;

  @Bind(R.id.toolbar)
  Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    setContentView(R.layout.activity_in_sale);
    ButterKnife.bind(this);
    mPersenter.attachView(this);
    initToolbar();
  }

  private void initToolbar()
  {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    toolbar.setNavigationIcon(R.mipmap.iconfont_livesvg);
  }


}

