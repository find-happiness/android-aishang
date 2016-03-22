package com.aishang.app.ui.BankEdit;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JMemberBankAccount;
import com.aishang.app.util.CommonUtil;

public class BankEditActivity extends AppCompatActivity {

  public static final String ACCOUNT = "account";

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.bankName) TextView bankName;
  @Bind(R.id.holder) TextView holder;
  @Bind(R.id.accountNumber) TextView accountNumber;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;

  JMemberBankAccount account;

  public static Intent getIntent(Activity activity, JMemberBankAccount memberBankAccount) {

    Intent intent = new Intent(activity, BankEditActivity.class);
    intent.putExtra(ACCOUNT, memberBankAccount);
    return intent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bank_add);
    ButterKnife.bind(this);
    account = (JMemberBankAccount) this.getIntent().getSerializableExtra(ACCOUNT);
    initToolbar();
    initView();
  }

  private void initView() {
    bankName.setText(account.getBankName());
    holder.setText(account.getHolder());
    accountNumber.setText(account.getAccountNumber());
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_save, menu);
    return true;
  }

  private void initToolbar() {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_save) {

          if (isAccountNumberEmpty()) {
            showError("银行帐号不能为空！");
            return true;
          }

          if (isBankHolderEmpty()) {
            showError("持卡人不能为空！");
            return true;
          }

          if (isBankNameEmpty()) {
            showError("银行名字不能为空！");
            return true;
          }

          Intent intent = new Intent();

          account.setAccountNumber(accountNumber.getText().toString().trim());
          account.setBankName(bankName.getText().toString().trim());
          account.setHolder(holder.getText().toString().trim());
          intent.putExtra(ACCOUNT, account);
          BankEditActivity.this.setResult(101,
              intent);// 设置回传数据。resultCode值是1，这个值在主窗口将用来区分回传数据的来源，以做不同的处理
          BankEditActivity.this.finish();// 关闭子窗口
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

  private boolean isBankNameEmpty() {
    return TextUtils.isEmpty(bankName.getText().toString().trim());
  }

  private boolean isAccountNumberEmpty() {
    return TextUtils.isEmpty(accountNumber.getText().toString().trim());
  }

  private boolean isBankHolderEmpty() {
    return TextUtils.isEmpty(holder.getText().toString().trim());
  }

  private void showError(String error) {
    CommonUtil.showSnackbar(error, layoutRoot);
  }
}
