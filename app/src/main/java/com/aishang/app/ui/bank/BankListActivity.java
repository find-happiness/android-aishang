package com.aishang.app.ui.bank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.ui.BankAdd.BankAddActivity;
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

public class BankListActivity extends BaseActivity implements BankListMvpView {

  @Inject BankListPresenter presenter;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.recyclerView) RecyclerView recyclerView;
  @Bind(R.id.avloadingIndicatorView) AVLoadingIndicatorView avloadingIndicatorView;
  @Bind(R.id.no_data_hotel) TextView noDataHotel;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;
  @Bind(R.id.swipe_refresh) SwipeRefreshLayout swipeRefreshLayout;

  @Inject BankAdapter adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    presenter.attachView(this);

    setContentView(R.layout.activity_bank);
    ButterKnife.bind(this);

    initToolbar();
    initSwipeRefresh();
    initRefreshLayout();

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
    if (resultCode == RESULT_OK) {

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
    }
  }

  private void initToolbar() {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {

          Intent intent = new Intent(BankListActivity.this, BankAddActivity.class);

          BankListActivity.this.startActivityForResult(intent, 100);
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
}
