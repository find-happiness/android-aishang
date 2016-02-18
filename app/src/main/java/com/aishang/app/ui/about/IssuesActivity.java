package com.aishang.app.ui.about;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.R;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class IssuesActivity extends AppCompatActivity {

  @Bind(R.id.toolbar) Toolbar toolbar;

  @Bind(R.id.expandableLayout1) ExpandableRelativeLayout expandableLayout1;

  @Bind(R.id.expandableLayout2) ExpandableRelativeLayout expandableLayout2;

  @Bind(R.id.expandableLayout3) ExpandableRelativeLayout expandableLayout3;

  @Bind(R.id.expandableLayout4) ExpandableRelativeLayout expandableLayout4;

  @Bind(R.id.expandableLayout5) ExpandableRelativeLayout expandableLayout5;

  @Bind(R.id.expandableLayout6) ExpandableRelativeLayout expandableLayout6;

  @Bind(R.id.expandableLayout7) ExpandableRelativeLayout expandableLayout7;

  @Bind(R.id.expandableLayout8) ExpandableRelativeLayout expandableLayout8;

  @Bind(R.id.expandableLayout9) ExpandableRelativeLayout expandableLayout9;
  @Bind(R.id.expandableLayout10) ExpandableRelativeLayout expandableLayout10;
  @Bind(R.id.expandableLayout11) ExpandableRelativeLayout expandableLayout11;
  @Bind(R.id.expandableLayout12) ExpandableRelativeLayout expandableLayout12;
  @Bind(R.id.expandableLayout13) ExpandableRelativeLayout expandableLayout13;
  @Bind(R.id.expandableLayout15) ExpandableRelativeLayout expandableLayout15;
  @Bind(R.id.expandableLayout16) ExpandableRelativeLayout expandableLayout16;
  @Bind(R.id.expandableLayout17) ExpandableRelativeLayout expandableLayout17;
  @Bind(R.id.expandableLayout18) ExpandableRelativeLayout expandableLayout18;
  @Bind(R.id.expandableLayout19) ExpandableRelativeLayout expandableLayout19;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_issues);
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
  @OnClick(R.id.head1) void onclickHead1() {
    expandableLayout1.toggle();
  }

  @OnClick(R.id.head2) void onclickHead2() {
    expandableLayout2.toggle();
  }

  @OnClick(R.id.head3) void onclickHead3() {
    expandableLayout3.toggle();
  }

  @OnClick(R.id.head4) void onclickHead4() {
    expandableLayout4.toggle();
  }

  @OnClick(R.id.head8) void onclickHead8() {
    expandableLayout8.toggle();
  }

  @OnClick(R.id.head5) void onclickHead5() {
    expandableLayout5.toggle();
  }

  @OnClick(R.id.head6) void onclickHead6() {
    expandableLayout6.toggle();
  }

  @OnClick(R.id.head7) void onclickHead7() {
    expandableLayout7.toggle();
  }

  @OnClick(R.id.head9) void onclickHead9() {
    expandableLayout9.toggle();
  }

  @OnClick(R.id.head10) void onclickHead10() {
    expandableLayout10.toggle();
  }

  @OnClick(R.id.head11) void onclickHead11() {
    expandableLayout11.toggle();
  }

  @OnClick(R.id.head12) void onclickHead12() {
    expandableLayout12.toggle();
  }

  @OnClick(R.id.head13) void onclickHead13() {
    expandableLayout13.toggle();
  }

  @OnClick(R.id.head15) void onclickHead15() {
    expandableLayout15.toggle();
  }

  @OnClick(R.id.head18) void onclickHead18() {
    expandableLayout18.toggle();
  }

  @OnClick(R.id.head16) void onclickHead16() {
    expandableLayout16.toggle();
  }

  @OnClick(R.id.head17) void onclickHead17() {
    expandableLayout17.toggle();
  }

  @OnClick(R.id.head19) void onclickHead19() {
    expandableLayout19.toggle();
  }
}
