package com.aishang.app.ui.CanTuan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.Name;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.BusProvider;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.rengwuxian.materialedittext.MaterialEditText;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Action1;

public class CanTuanActivity extends BaseActivity implements CanTuanMvpView {

  public static final String ACTIVITY_ID = "activity_id";

  @Inject CanTuanPresenter presenter;

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;
  @Bind(R.id.name) MaterialEditText name;
  @Bind(R.id.male) RadioButton male;
  @Bind(R.id.female) RadioButton female;
  @Bind(R.id.sex) RadioGroup sex;
  @Bind(R.id.phone) MaterialEditText phone;
  @Bind(R.id.num_person) MaterialEditText numPerson;
  @Bind(R.id.commit) MaterialEditText commit;

  private int activityID = -1;
  private ProgressDialog progressDialog;

  public static Intent getStartIntent(Context context, int activityID) {
    Intent intent = new Intent(context, CanTuanActivity.class);
    intent.putExtra(ACTIVITY_ID, activityID);
    return intent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    presenter.attachView(this);
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    setContentView(R.layout.activity_can_tuan);

    if (savedInstanceState != null && savedInstanceState.containsKey(ACTIVITY_ID)) {
      activityID = savedInstanceState.getInt(ACTIVITY_ID, -1);
    } else {
      activityID = this.getIntent().getIntExtra(ACTIVITY_ID, -1);
    }

    ButterKnife.bind(this);
    initToolbar();
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

  @Override public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
    if (activityID != -1) outState.putSerializable(ACTIVITY_ID, activityID);
    super.onSaveInstanceState(outState, outPersistentState);
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
        onBackPressed();
      }
    });
  }

  private void postDate() {

    CommonUtil.hideSoftInput(this);

    if (isNameEmpty()) {
      showNameError();
      return;
    }

    if (isNumEmpty()) {
      showNumError();
      return;
    }

    if (isPhoneEmpty()) {
      showPhoneError();
    }
    post();
  }

  private void post() {

    int gender = -1;
    if (female.isChecked()) {
      gender = 0;
    } else if (male.isChecked()) gender = 1;

    String json =
        AiShangUtil.generMreActivityEnrollParam(activityID, "", "", name.getText().toString(),
            gender, phone.getText().toString(), Integer.parseInt(numPerson.getText().toString()),
            commit.getText().toString());

    progressDialog = DialogFactory.createProgressDialog(this, R.string.posting);
    progressDialog.show();
    presenter.postData(1, json);
  }

  private boolean isNameEmpty() {
    return TextUtils.isEmpty(name.getText());
  }

  private boolean isPhoneEmpty() {
    return TextUtils.isEmpty(phone.getText());
  }

  private boolean isNumEmpty() {
    return TextUtils.isEmpty(numPerson.getText());
  }

  private void showNameError() {
    showError("姓名不能为空！");
  }

  private void showPhoneError() {
    showError(this.getString(R.string.error_phone));
  }

  private void showNumError() {
    showError("参团人数不能为空");
  }

  @Override public void showError(String error) {
    dimissDialog();
    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override public void showSuccess() {
    dimissDialog();
    //CommonUtil.showSnackbar("报名成功", layoutRoot);
    //Observable.timer(1, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
    //  @Override public void call(Long aLong) {
    //    CanTuanActivity.this.finish();
    //  }
    //});

    DialogFactory.createGenericSuccessDialog(this, "报名成功", new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialog, int which) {
        CanTuanActivity.this.finish();
      }
    }).show();
  }

  private void dimissDialog() {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }
  }
}
