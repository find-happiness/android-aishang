package com.aishang.app.ui.BankAdd;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.R;
import com.aishang.app.data.model.JMemberBankAccount;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;

public class BankAddActivity extends AppCompatActivity {

  public static final String ACCOUNT = "account";

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.bankName) TextView bankName;
  @Bind(R.id.holder) TextView holder;
  @Bind(R.id.accountNumber) TextView accountNumber;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;
  @Bind(R.id.aphle_btn) View rlBank;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bank_add);
    ButterKnife.bind(this);
    initToolbar();
    initEtBankName();
  }

  private void initEtBankName() {
    bankName.setInputType(InputType.TYPE_NULL);

    bankName.setOnTouchListener(new View.OnTouchListener() {
      @Override public boolean onTouch(View v, MotionEvent event) {
        return true;
      }
    });

    rlBank.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        DialogFactory.createSingleChoiceDialog(BankAddActivity.this, R.array.bank, -1,
            new DialogInterface.OnClickListener() {
              @Override public void onClick(DialogInterface dialog, int which) {
                bankName.setText(
                    BankAddActivity.this.getResources().getStringArray(R.array.bank)[which]);
                bankName.setTag(which);
                dialog.dismiss();
              }
            }, R.string.select_bank).show();
      }
    });
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

  @OnClick(R.id.submint) public void onClick() {

    if (isAccountNumberEmpty()) {
      showError("银行帐号不能为空！");
      return;
    }

    if (isBankHolderEmpty()) {
      showError("持卡人不能为空！");
      return;
    }

    if (isBankNameEmpty()) {
      showError("银行名字不能为空！");
      return;
    }

    Intent intent = new Intent();

    JMemberBankAccount account = new JMemberBankAccount();
    account.setAccountNumber(accountNumber.getText().toString().trim());
    account.setBankName(bankName.getText().toString().trim());
    account.setHolder(holder.getText().toString().trim());
    account.setId(0);
    intent.putExtra(ACCOUNT, account);

    BankAddActivity.this.setResult(100, intent);// 设置回传数据。resultCode值是1，这个值在主窗口将用来区分回传数据的来源，以做不同的处理
    BankAddActivity.this.finish();// 关闭子窗口
  }
}
