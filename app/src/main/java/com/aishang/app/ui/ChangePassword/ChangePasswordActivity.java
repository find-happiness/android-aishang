package com.aishang.app.ui.ChangePassword;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.NetworkUtil;
import javax.inject.Inject;

public class ChangePasswordActivity extends BaseActivity implements ChangePswMvpView {

  private static final String TAG = "ChangePasswordActivity";
  @Inject ChangePswPresenter presenter;

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.old) EditText old;
  @Bind(R.id.new_psw) EditText newPsw;
  @Bind(R.id.again) EditText again;
  @Bind(R.id.btn_forget) TextView btnForget;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;

  ProgressDialog progressDialog;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    presenter.attachView(this);

    setContentView(R.layout.activity_change_password2);
    ButterKnife.bind(this);
    initToolbar();
  }

  private void initToolbar() {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_push) {
          post();
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

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_push, menu);
    return true;
  }

  @Override public void onBackPressed() {
    super.onBackPressed();
    CommonUtil.hideSoftInput(this);
  }

  private boolean isOldPsdEmpty() {
    return TextUtils.isEmpty(old.getText().toString().trim());
  }

  private boolean isNewEmpty() {
    return TextUtils.isEmpty(newPsw.getText().toString().trim());
  }

  private boolean isAgainEmpty() {
    String againStr = again.getText().toString();
    String newStr = newPsw.getText().toString();

    return TextUtils.isEmpty(againStr) || !againStr.equals(newStr);
  }

  private void showOldError() {
    showError("旧密码不能为空！");
  }

  private void newError() {
    showError("新密码不能为空！");
  }

  private void againError() {
    showError("输入的两次密码不一样！");
  }

  private void post() {
    CommonUtil.hideSoftInput(this);
    if (NetworkUtil.isNetworkConnected(this)) {
      if (isOldPsdEmpty()) {
        showOldError();
        return;
      }
      if (isNewEmpty()) {
        newError();
        return;
      }
      if (isAgainEmpty()) {
        againError();
        return;
      }

      progressDialog = DialogFactory.createProgressDialog(this, R.string.posting);
      progressDialog.show();

      JMemberLoginResult result = BoilerplateApplication.get(this).getMemberLoginResult();

      Log.i(TAG, "post: " + BoilerplateApplication.get(this).getMemberPsw());

      String json =
          AiShangUtil.generChangePwdParams(CommonUtil.getEncodeMD5(old.getText().toString()),
              CommonUtil.getEncodeMD5(newPsw.getText().toString()),
              BoilerplateApplication.get(this).getMemberAccount(), result.getData().getCookies());

      presenter.postData(0, json);
    } else {
      CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
    }
  }

  @Override public void showError(String error) {
    dimissDialog();
    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override public void showSuccess() {
    dimissDialog();
    CommonUtil.showSnackbar("密码修改成功", layoutRoot);
  }

  private void dimissDialog() {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }
  }
}
