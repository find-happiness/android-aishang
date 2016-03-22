package com.aishang.app.ui.bank;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.data.model.JMemberBankAccount;
import com.aishang.app.data.model.JMemberBankListResult;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.ui.BankAdd.BankAddActivity;
import com.aishang.app.ui.BankEdit.BankEditActivity;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.NetWorkType;
import com.aishang.app.util.NetworkUtil;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Action1;

public class BankListActivity extends BaseActivity implements BankListMvpView {

  private static final String TAG = "BankListActivity";

  private static final int EDIT_BANK = 101;
  private static final int ADD_BANK = 100;

  @Inject BankListPresenter presenter;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.recyclerView) XRecyclerView recyclerView;
  @Bind(R.id.avloadingIndicatorView) AVLoadingIndicatorView avloadingIndicatorView;
  @Bind(R.id.no_data_hotel) TextView noDataHotel;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;
  @Bind(R.id.swipe_refresh) SwipeRefreshLayout swipeRefreshLayout;

  @Inject BankAdapter adapter;

  private static final String[] ACTION = new String[] { "修改", "删除" };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    presenter.attachView(this);

    setContentView(R.layout.activity_bank);
    ButterKnife.bind(this);

    initToolbar();
    initSwipeRefresh();
    initRefreshLayout();

    adapter.setListener(new ItemClickListener() {
      @Override public void itemClick(final JMemberBankAccount bankAccount) {
        DialogFactory.createSingleChoiceDialog(BankListActivity.this, ACTION, -1,
            new DialogInterface.OnClickListener() {
              @Override public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                  case 0:
                    Intent intent = BankEditActivity.getIntent(BankListActivity.this, bankAccount);
                    BankListActivity.this.startActivityForResult(intent, EDIT_BANK);
                    break;
                  case 1:
                    showDeleteDialog(bankAccount);
                    break;
                }
                dialog.dismiss();
              }
            }, null).show();
      }
    });

    autoRefresh();
  }

  @Override protected void onResume() {
    super.onResume();
  }

  @Override protected void onDestroy() {
    presenter.detachView();
    super.onDestroy();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_add, menu);
    return true;
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == ADD_BANK) {

      JMemberBankAccount account =
          (JMemberBankAccount) data.getSerializableExtra(BankAddActivity.ACCOUNT);
      JMemberBankAccount[] accounts = new JMemberBankAccount[adapter.getItemCount() + 1];
      accounts[0] = account;
      int i = 1;
      for (JMemberBankAccount a : adapter.getItems()) {
        accounts[i] = a;
        i++;
      }

      editData(accounts);
    } else if (resultCode == EDIT_BANK) {
      JMemberBankAccount bankAccount =
          (JMemberBankAccount) data.getSerializableExtra(BankAddActivity.ACCOUNT);

      JMemberBankAccount[] accounts =
          adapter.getItems().toArray(new JMemberBankAccount[adapter.getItemCount()]);

      for (JMemberBankAccount account : accounts) {
        if (account.getId() == bankAccount.getId()) {
          account.setBankName(bankAccount.getBankName());
          account.setHolder(bankAccount.getHolder());
          account.setAccountNumber(bankAccount.getAccountNumber());
        }
      }
      editData(accounts);
    }
  }

  private void initToolbar() {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {

          Intent intent = new Intent(BankListActivity.this, BankAddActivity.class);

          BankListActivity.this.startActivityForResult(intent, ADD_BANK);
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

  private void autoRefresh() {
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        loadData();
      }
    }, 100);
  }

  private void initSwipeRefresh() {
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        loadData();
      }
    });
  }

  private void loadData() {
    if (NetworkUtil.isNetworkConnected(BankListActivity.this)) {
      swipeRefreshLayout.setRefreshing(true);
      getBanks();
    } else {
      if (swipeRefreshLayout.isRefreshing()) swipeRefreshLayout.setRefreshing(false);
      showError(BankListActivity.this.getString(R.string.no_net));
    }
  }

  private void editData(JMemberBankAccount[] memberBankAccounts) {
    if (NetworkUtil.isNetworkConnected(BankListActivity.this)) {
      swipeRefreshLayout.setRefreshing(true);
      postBanks(memberBankAccounts);
    } else {
      if (swipeRefreshLayout.isRefreshing()) swipeRefreshLayout.setRefreshing(false);
      showError(BankListActivity.this.getString(R.string.no_net));
    }
  }

  private void postBanks(JMemberBankAccount[] memberBankAccounts) {
    JMemberLoginResult result = BoilerplateApplication.get(this).getMemberLoginResult();
    String json =
        AiShangUtil.generMamberBankEditParam(BoilerplateApplication.get(this).getMemberAccount(),
            result.getData().getCookies(), 1, memberBankAccounts);

    presenter.editData(1, json);
  }

  private void getBanks() {
    JMemberLoginResult result = BoilerplateApplication.get(this).getMemberLoginResult();
    String json =
        AiShangUtil.generMamberBankListParam(BoilerplateApplication.get(this).getMemberAccount(),
            result.getData().getCookies());

    presenter.loadData(2, json);
  }

  private void initRefreshLayout() {
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(layoutManager);

    recyclerView.addItemDecoration(
        new HorizontalDividerItemDecoration.Builder(this).colorResId(android.R.color.darker_gray)
            .sizeResId(R.dimen.divider)
            .build());

    recyclerView.setPullRefreshEnabled(false);
    recyclerView.setLoadingMoreEnabled(false);
    recyclerView.setAdapter(adapter);
  }

  @Override public void showError(String error) {
    if (swipeRefreshLayout.isRefreshing()) swipeRefreshLayout.setRefreshing(false);
    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override public void showSuccess(JMemberBankAccount[] bankAccounts) {
    if (swipeRefreshLayout.isRefreshing()) swipeRefreshLayout.setRefreshing(false);
    adapter.getItems().clear();
    adapter.getItems().addAll(new ArrayList<JMemberBankAccount>(Arrays.asList(bankAccounts)));
    adapter.notifyDataSetChanged();
  }

  @Override public void showEmpty() {
    if (swipeRefreshLayout.isRefreshing()) swipeRefreshLayout.setRefreshing(false);

    avloadingIndicatorView.setVisibility(View.GONE);
    noDataHotel.setVisibility(View.VISIBLE);
    adapter.getItems().clear();
    adapter.notifyDataSetChanged();
  }

  private void showDeleteDialog(final JMemberBankAccount bankAccount) {
    DialogFactory.createSimpleDialog(BankListActivity.this, android.R.string.dialog_alert_title,
        R.string.confirm_to_delete, new DialogInterface.OnClickListener() {
          @Override public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();

            JMemberBankAccount[] accounts =
                adapter.getItems().toArray(new JMemberBankAccount[adapter.getItemCount()]);

            for (JMemberBankAccount account : accounts) {
              if (account.getId() == bankAccount.getId()) {
                account.setId(0 - account.getId());
              }
            }
            editData(accounts);
          }
        }, null).show();
  }

  protected interface ItemClickListener {
    void itemClick(JMemberBankAccount bankAccount);
  }
}
