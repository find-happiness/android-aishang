package com.aishang.app.ui.ProjectJoint;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.RegexUtils;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.rengwuxian.materialedittext.MaterialEditText;
import javax.inject.Inject;

public class ProjectJointActivity extends BaseActivity implements ProjectJoinMvpView {

  @Inject ProjectJoinPresenter presenter;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.expandable_text) TextView expandableText;
  @Bind(R.id.expand_collapse) ImageButton expandCollapse;
  @Bind(R.id.expand_text_view) ExpandableTextView expandTextView;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;
  @Bind(R.id.name) MaterialEditText name;
  @Bind(R.id.address) MaterialEditText address;
  @Bind(R.id.projectCount) MaterialEditText projectCount;
  @Bind(R.id.contacts) MaterialEditText contacts;
  @Bind(R.id.contactsPhone) MaterialEditText contactsPhone;
  @Bind(R.id.contactsEmail) MaterialEditText contactsEmail;
  @Bind(R.id.special) MaterialEditText special;
  private ProgressDialog progressDialog;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    presenter.attachView(this);
    setContentView(R.layout.activity_project_joint);
    this.getWindow()
        .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
            | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    ButterKnife.bind(this);
    initToolbar();

    expandTextView.setText(this.getString(R.string.project_rule));
  }
  @Override protected void onDestroy() {
    presenter.detachView();
    super.onDestroy();
  }
  private void initToolbar() {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_push) {
          postDate();
          return true;
        }
        return false;
      }
    });
    toolbar.setNavigationIcon(R.mipmap.iconfont_livesvg);

    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        ProjectJointActivity.this.onBackPressed();
      }
    });
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_push, menu);
    return true;
  }

  private void postDate() {

    CommonUtil.hideSoftInput(this);

    if (isNameEmpty()) {
      showNameError();
      return;
    }

    if (isAddressEmpty()) {
      showAddressError();
      return;
    }

    if (isPhoneEmpty() || !RegexUtils.checkMobile(contactsPhone.getText().toString().trim())) {
      showPhoneError();
      return;
    }

    post();
  }

  private void post() {

    String json = AiShangUtil.generProjecCtooperationParam(name.getText().toString().trim(),
        address.getText().toString().trim(), projectCount.getText().toString().trim(),
        contacts.getText().toString().trim(), contactsPhone.getText().toString().trim(),
        contactsEmail.getText().toString().trim(), special.getText().toString().trim());

    progressDialog = DialogFactory.createProgressDialog(this, R.string.posting);
    progressDialog.show();
    presenter.postData(1, json);
  }

  private boolean isNameEmpty() {
    return TextUtils.isEmpty(name.getText().toString().trim());
  }

  private boolean isPhoneEmpty() {
    return TextUtils.isEmpty(contactsPhone.getText())
        || TextUtils.getTrimmedLength(contactsPhone.getText()) <= 0;
  }

  private boolean isAddressEmpty() {
    return TextUtils.isEmpty(address.getText().toString().trim());
  }

  private void showNameError() {
    showError("项目名称不能为空！");
  }

  private void showPhoneError() {
    showError(this.getString(R.string.error_phone));
  }

  private void showAddressError() {
    showError("项目地址不能为空");
  }

  @Override public void showError(String error) {
    dimissDialog();
    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override public void showSuccess() {
    dimissDialog();
    CommonUtil.showSnackbar("提交成功", layoutRoot);
  }

  private void dimissDialog() {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }
  }
}
