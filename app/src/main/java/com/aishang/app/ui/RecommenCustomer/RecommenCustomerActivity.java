package com.aishang.app.ui.RecommenCustomer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.RegexUtils;
import com.aishang.app.widget.ClearEditText;
import com.happiness.alterview.OnItemClickListener;
import javax.inject.Inject;

public class RecommenCustomerActivity extends BaseActivity implements RecommentMvpView {

  @Inject ReccommentPresenter presenter;
  @Bind(R.id.toolbar) Toolbar toolbar;

  final String[] GENDER = new String[] { "保密", "男", "女" };

  @Bind(R.id.name) ClearEditText name;
  @Bind(R.id.phone) ClearEditText phone;
  @Bind(R.id.address) ClearEditText address;
  @Bind(R.id.intent) ClearEditText intent;
  @Bind(R.id.commit) ClearEditText commit;
  @Bind(R.id.layoutRoot) LinearLayout layoutRoot;

  @Bind(R.id.age) ClearEditText age;
  @Bind(R.id.work) ClearEditText work;
  @Bind(R.id.gender) TextView gender;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    presenter.attachView(this);
    setContentView(R.layout.activity_recommen_customer);
    ButterKnife.bind(this);
    initToolbar();
    gender.setTag(-1);
  }

  @Override protected void onDestroy() {
    presenter.detachView();
    super.onDestroy();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_push, menu);
    return true;
  }

  @Override public void showError(String error) {
    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override public void showSuccess() {
    DialogFactory.createGenericSuccessDialog(this, "添加成功", new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialog, int which) {
        RecommenCustomerActivity.this.finish();
      }
    }).show();
  }

  private void initToolbar() {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);

    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_push) {
          submit();
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

  public boolean isEmptyName() {
    return TextUtils.isEmpty(name.getText());
  }

  public boolean isEmptyPhone() {
    return TextUtils.isEmpty(phone.getText());
  }

  private boolean isPhone() {
    return (RegexUtils.checkPhone(phone.getText().toString()) || RegexUtils.checkMobile(
        phone.getText().toString()));
  }

  private void submit() {
    if (isEmptyName()) {
      showError(getString(R.string.no_name));
      return;
    }
    if (isEmptyPhone()) {
      showError(getString(R.string.no_phone));
      return;
    }

    if (!isPhone()) {
      showError(getString(R.string.error_phone));
      return;
    }

    asynPush(name.getText().toString(), phone.getText().toString());
  }

  private void asynPush(String name, String phone) {


    String cookie = BoilerplateApplication.get(this).getMemberLoginResult().getData().getCookies();
    String member = BoilerplateApplication.get(this).getMemberAccount();
    String json = AiShangUtil.generContactsAddParam(phone, name, cookie, member,
        address.getText().toString().trim(), intent.getText().toString().trim(),
        commit.getText().toString().trim(), age.getText().toString().trim(),
        GENDER[(int) gender.getTag()], work.getText().toString().trim());

    presenter.pushContacts(1, json);
  }

  @OnClick(R.id.gender) void onclickGender() {
    CommonUtil.hideSoftInput(this);
    DialogFactory.createIosSheetAlertDialog(this, null, GENDER, new OnItemClickListener() {
      @Override public void onItemClick(Object o, int position) {

        if (position < 0) return;
        gender.setTag((position - 1));
        gender.setText(GENDER[position]);
      }
    }).show();
  }
}
