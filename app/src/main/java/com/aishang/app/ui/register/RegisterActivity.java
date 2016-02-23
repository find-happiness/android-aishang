package com.aishang.app.ui.register;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.ui.base.BaseActivity;
import com.rengwuxian.materialedittext.MaterialEditText;
import javax.inject.Inject;

public class RegisterActivity extends BaseActivity implements RegisterMvpView {

  @Inject RegisterPresenter mPresenter;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.btn_get_verification_code) TextView btnGetVerificationCode;
  @Bind(R.id.phone) MaterialEditText phone;
  @Bind(R.id.verify_code) MaterialEditText verifyCode;
  @Bind(R.id.password) MaterialEditText password;
  @Bind(R.id.btn_register) Button btnRegister;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    mPresenter.attachView(this);
    setContentView(R.layout.activity_register);
    ButterKnife.bind(this);
    initView();
  }
  @Override protected void onDestroy() {
    mPresenter.detachView();
    super.onDestroy();
  }
  private void initView() {
    initToolbar();
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
}
