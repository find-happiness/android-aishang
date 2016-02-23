package com.aishang.app.ui.CashWithDrawApply;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.BusProvider;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.shizhefei.fragment.LazyFragment;
import com.squareup.otto.Subscribe;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link CashWithDrawFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CashWithDrawFragment extends LazyFragment implements CashWithDrawApplyMvpView {

  private static final String TAG = "CashWithDrawFragment";
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  @Inject CashWithDrawApplyPresenter presenter;
  @Bind(R.id.tv_price_jifen) TextView tvPriceJifen;
  @Bind(R.id.amount) MaterialEditText amount;
  @Bind(R.id.bank) MaterialEditText bank;
  @Bind(R.id.holder) MaterialEditText holder;
  @Bind(R.id.accountNumber) MaterialEditText accountNumber;
  @Bind(R.id.layoutRoot) ScrollView layoutRoot;



  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @return A new instance of fragment CashWithDrawFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static CashWithDrawFragment newInstance() {
    CashWithDrawFragment fragment = new CashWithDrawFragment();
    return fragment;
  }

  public CashWithDrawFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ((CashWithDrawApplyActivity) getActivity()).getActivityComponent().inject(this);
    presenter.attachView(this);

    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }
  @Override public void onDestroy() {
    presenter.detachView();
    super.onDestroy();
  }
  @Override protected void onResumeLazy() {
    super.onResumeLazy();
    BusProvider.getInstance().register(this);
  }

  @Override protected void onPauseLazy() {
    super.onPauseLazy();
    BusProvider.getInstance().unregister(this);
  }

  @Override protected void onCreateViewLazy(Bundle savedInstanceState) {
    super.onCreateViewLazy(savedInstanceState);
    setContentView(R.layout.fragment_cash_with_draw);
    ButterKnife.bind(this, this.getContentView());
  }

  @Override public void onAttach(Activity context) {
    super.onAttach(context);
  }

  @Override public void onDetach() {
    super.onDetach();
  }

  @Subscribe public void onPost(PostEvent event) {
    Log.i(TAG, "onPost: CashWithDrawFragment------>" + event.getFragment());
    if (event.getFragment() != 1) return;

    if (isEmptyAccountNumber()) {
      CommonUtil.showSnackbar("账号不能为空", layoutRoot);
      return;
    }

    if (isEmptyAmount()) {
      CommonUtil.showSnackbar("提现金额不能为空", layoutRoot);
      return;
    }

    if (isEmptyBank()) {
      CommonUtil.showSnackbar("开户行不能为空", layoutRoot);
      return;
    }

    if (isEmptyHolder()) {
      CommonUtil.showSnackbar("持卡人不能为空", layoutRoot);
      return;
    }

    asynPost();
  }

  @Override public void onDestroyViewLazy() {
    super.onDestroyViewLazy();
    ButterKnife.unbind(this);
  }

  public boolean isEmptyHolder() {
    return TextUtils.isEmpty(holder.getText());
  }

  public boolean isEmptyBank() {
    return TextUtils.isEmpty(bank.getText());
  }

  public boolean isEmptyAmount() {
    return TextUtils.isEmpty(amount.getText());
  }

  public boolean isEmptyAccountNumber() {
    return TextUtils.isEmpty(accountNumber.getText());
  }

  private void asynPost() {
    String cookie = BoilerplateApplication.get(this.getActivity())
        .getMemberLoginResult()
        .getData()
        .getCookies();
    String member = BoilerplateApplication.get(this.getActivity()).getMemberAccount();
    String json = AiShangUtil.generCashWithDrawApplyParam(accountNumber.getText().toString(),
        amount.getText().toString(), holder.getText().toString(), bank.getText().toString(), member,
        cookie);

    presenter.postCashWithDrawApply(1, json);
  }

  @Override public void showError(String error) {
    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override public void showSuccess() {
    DialogFactory.createGenericSuccessDialog(this.getActivity(), "提现申请成功提交").show();
  }
}
