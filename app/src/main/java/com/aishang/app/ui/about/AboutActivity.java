package com.aishang.app.ui.about;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.R;
import com.aishang.app.ui.base.BaseActivity;

public class AboutActivity extends BaseActivity {

  @Bind(R.id.toolbar) Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_about);

    ButterKnife.bind(this);

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

  @OnClick(R.id.know_we) void onKnowWeClick() {
    Intent intent = new Intent(this, KnowWeActivity.class);
    this.startActivity(intent);
  }

  @OnClick(R.id.play_travel) void OnTravalCkick() {
    Intent intent = new Intent(this, TravelActivity.class);
    this.startActivity(intent);
  }

  @OnClick(R.id.borker) void onBorkerClick() {
    Intent intent = new Intent(this, BrokerActivity.class);
    this.startActivity(intent);
  }

  @OnClick(R.id.join_we) void onJointWeClick() {
    Intent intent = new Intent(this, HongBaoActivity.class);
    this.startActivity(intent);
  }

  @OnClick(R.id.issues) void onIssuesClick() {
    Intent intent = new Intent(this, IssuesActivity.class);
    this.startActivity(intent);
  }

  @OnClick(R.id.member) void onMemberClick() {
    Intent intent = new Intent(this, MemberRuleActivity.class);
    this.startActivity(intent);
  }
}
