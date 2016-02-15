package com.aishang.app.ui.RecommenCustomer;

import android.content.DialogInterface;
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
import butterknife.OnClick;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.RegexUtils;
import com.aishang.app.widget.ClearEditText;
import javax.inject.Inject;

public class RecommenCustomerActivity extends BaseActivity implements RecommentMvpView {

  @Inject ReccommentPresenter presenter;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.name) ClearEditText name;
  @Bind(R.id.phone) ClearEditText phone;
  @Bind(R.id.age) ClearEditText age;
  @Bind(R.id.job) ClearEditText job;
  @Bind(R.id.address) ClearEditText address;
  @Bind(R.id.intent) ClearEditText intent;
  @Bind(R.id.intent_price) ClearEditText intentPrice;
  @Bind(R.id.commit) ClearEditText commit;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;

  final String[] genders = new String[] { "保密", "男", "女" };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    presenter.attachView(this);

    setContentView(R.layout.activity_recommen_customer);
    ButterKnife.bind(this);
    initToolbar();
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
    DialogFactory.createGenericErrorDialog(this, "添加成功").show();
  }

  @OnClick(R.id.gender) void genderClick() {
    DialogFactory.createSingleChoiceDialog(this, genders, 0, new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialog, int which) {

      }
    }, getString(R.string.gender));
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
    String json = AiShangUtil.generContactsAddParam(phone, name, cookie, member);

    presenter.pushContacts(1, json);
  }
}
