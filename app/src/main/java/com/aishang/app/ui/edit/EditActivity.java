package com.aishang.app.ui.edit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.ui.MemberCenter.MemberCenterActivity;
import com.aishang.app.util.BusProvider;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.RegexUtils;
import com.rengwuxian.materialedittext.MaterialEditText;

public class EditActivity extends AppCompatActivity {

  private static final String TITLE = "title";
  public static final String CONTENT = "content";
  private static final String REQUESR_CODE = "requestCode";
  @Bind(R.id.toolbar_title) TextView toolbarTitle;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.et_content) MaterialEditText etContent;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;

  int mRequestCode;
  String title = "";
  String content = "";

  public static Intent getStartIntent(Activity ctx, String title, String content, int requestCode) {
    Intent intent = new Intent(ctx, EditActivity.class);
    intent.putExtra(TITLE, title);
    intent.putExtra(CONTENT, content);
    intent.putExtra(REQUESR_CODE, requestCode);
    return intent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit);
    ButterKnife.bind(this);

    if (savedInstanceState != null) {
      if (savedInstanceState.containsKey(TITLE)) {
        title = savedInstanceState.getString(TITLE);
      }

      if (savedInstanceState.containsKey(CONTENT)) {
        content = savedInstanceState.getString(CONTENT);
      }

      if (savedInstanceState.containsKey(REQUESR_CODE)) {
        mRequestCode = savedInstanceState.getInt(REQUESR_CODE, -1);
      }
    } else {

      title = getIntent().getStringExtra(TITLE);
      content = getIntent().getStringExtra(CONTENT);
      mRequestCode = getIntent().getIntExtra(REQUESR_CODE, -1);
    }
    toolbarTitle.setText(title);
    etContent.setHint(content);
    initToolbar();
  }

  private void initToolbar() {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_save) {

          String temp = etContent.getText().toString().trim();
          if (TextUtils.isEmpty(temp)) {
            CommonUtil.showSnackbar("修改内容不能为空！", layoutRoot);
            return true;
          }

          if (mRequestCode == MemberCenterActivity.EMAIL && !RegexUtils.checkEmail(temp)) {
            CommonUtil.showSnackbar("输入邮箱地址错误！", layoutRoot);
            return true;
          }

          Intent intent = new Intent();
          intent.putExtra(CONTENT, etContent.getText().toString().trim());// 把返回数据存入Intent
          EditActivity.this.setResult(RESULT_OK,
              intent);// 设置回传数据。resultCode值是1，这个值在主窗口将用来区分回传数据的来源，以做不同的处理
          EditActivity.this.finish();// 关闭子窗口
          return true;
        }
        return false;
      }
    });
    toolbar.setNavigationIcon(R.mipmap.iconfont_livesvg);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onBackPressed();
      }
    });
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    outState.putString(TITLE, title);
    outState.putString(CONTENT, content);
    outState.putInt(REQUESR_CODE, mRequestCode);
    super.onSaveInstanceState(outState);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_save, menu);
    return true;
  }
}
