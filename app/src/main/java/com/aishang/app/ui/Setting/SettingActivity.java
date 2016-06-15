package com.aishang.app.ui.Setting;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.DataCleanManager;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.StorageUtils;
import com.squareup.picasso.Picasso;
import java.io.File;
import javax.inject.Inject;

public class SettingActivity extends BaseActivity implements SettingMvpView {
  private static final String TAG = "SettingActivity";
  private static final String PICASSO_CACHE = "picasso-cache";
  @Inject SettingPresenter presenter;

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.clear_cache) RelativeLayout clearCache;
  @Bind(R.id.switch_push) SwitchCompat switchPush;
  @Bind(R.id.logout) RelativeLayout logout;
  ProgressDialog progressDialog;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    this.getActivityComponent().inject(this);
    presenter.attachView(this);

    setContentView(R.layout.activity_setting);

    ButterKnife.bind(this);

    initToolbar();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.detachView();
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

  @OnClick(R.id.clear_cache) void onclickClearCache() {

    DialogFactory.createSimpleDialog(this, android.R.string.dialog_alert_title,
        R.string.clear_cache, new DialogInterface.OnClickListener() {
          @Override public void onClick(DialogInterface dialog, int which) {

            File cache =
                new File(SettingActivity.this.getApplicationContext().getCacheDir(), PICASSO_CACHE);

            Log.i(TAG, "clear cache: " + cache.getPath());
            DataCleanManager.cleanApplicationData(SettingActivity.this, cache.getPath());
          }
        }, null).show();
  }

  @OnClick(R.id.logout) void onclickLogout() {
    if (isLogIn()) {
      DialogFactory.createSimpleDialog(this, "", "您确定退出吗?", getString(R.string.dialog_action_ok),
          getString(R.string.dialog_action_cancel), new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {

              JMemberLoginResult result =
                  BoilerplateApplication.get(SettingActivity.this).getMemberLoginResult();
              showProgressDialog();
              presenter.logout(0, AiShangUtil.generMamberLogoutParam(
                  BoilerplateApplication.get(SettingActivity.this).getMemberAccount(),
                  result.getData().getCookies()));
            }
          }, null).show();
    } else {
      DialogFactory.createSimpleOkErrorDialog(this, R.string.dialog_error_title,
          R.string.error_not_login).show();
    }
  }

  @Override public void showError(String error) {
    dimissDialog();
  }

  @Override public void showSuccess() {
    dimissDialog();

    BoilerplateApplication.get(this).setMemberAccount("");
    BoilerplateApplication.get(this).setMemberLoginResult(null);
    BoilerplateApplication.get(this).setMemberPsw("");
  }

  private void showProgressDialog() {
    progressDialog = DialogFactory.createProgressDialog(this, R.string.listview_loading);
    progressDialog.show();
  }

  private void dimissDialog() {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }
  }

  private boolean isLogIn() {
    JMemberLoginResult result = BoilerplateApplication.get(this).getMemberLoginResult();
    if (result != null && !TextUtils.isEmpty(result.getData().getCookies())) {
      return true;
    }

    return false;
  }
}
