package com.aishang.app.ui.CashWithDrawApply;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
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
import butterknife.OnClick;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.data.model.JMemberBankAccount;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.BusProvider;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.NetworkUtil;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.shizhefei.fragment.LazyFragment;
import com.squareup.otto.Subscribe;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link CashWithDrawFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CashWithDrawFragment extends LazyFragment implements CashWithDrawApplyMvpView {

  private static final String TAG = "CashWithDrawFragment";

  @Inject CashWithDrawApplyPresenter presenter;

  @Bind(R.id.layoutRoot) ScrollView layoutRoot;
  @Bind(R.id.bank) TextView bank;
  @Bind(R.id.amount) MaterialEditText amount;
  Dialog progressDialog;
  JMemberBankAccount[] banks;

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @return A new instance of fragment CashWithDrawFragment.
   */
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

  }

  @Override public void onDestroyViewLazy() {
    super.onDestroyViewLazy();
    ButterKnife.unbind(this);
  }

  public boolean isEmptyBank() {
    return TextUtils.isEmpty(bank.getText()) || bank.getTag() == null || banks == null;
  }

  public boolean isEmptyAmount() {
    return TextUtils.isEmpty(amount.getText());
  }

  private void asynPost() {
    String cookie = BoilerplateApplication.get(this.getActivity())
        .getMemberLoginResult()
        .getData()
        .getCookies();

    String member = BoilerplateApplication.get(this.getActivity()).getMemberAccount();
    String json =
        AiShangUtil.generCashWithDrawApplyParam(banks[(int) bank.getTag()].getAccountNumber(),
            amount.getText().toString(), banks[(int) bank.getTag()].getHolder(),
            bank.getText().toString(), member, cookie);

    presenter.postCashWithDrawApply(1, json);
  }

  private void loadBank() {
    if (NetworkUtil.isNetworkConnected(this.getActivity())) {

      progressDialog =
          DialogFactory.createProgressDialog(this.getActivity(), R.string.listview_loading);
      progressDialog.show();

      getBanks();
    } else {
      showError(this.getString(R.string.no_net));
    }
  }

  private void dimissDialog() {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }
  }

  private void getBanks() {
    JMemberLoginResult result =
        BoilerplateApplication.get(this.getActivity()).getMemberLoginResult();
    String json = AiShangUtil.generMamberBankListParam(
        BoilerplateApplication.get(this.getActivity()).getMemberAccount(),
        result.getData().getCookies());

    presenter.loadBank(2, json);
  }

  @Override public void showError(String error) {
    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override public void showSuccess() {
    DialogFactory.createGenericSuccessDialog(this.getActivity(), "提现申请成功提交").show();
  }

  @Override public void loadBankSuccess(JMemberBankAccount[] banks) {

    dimissDialog();

    if (this.banks == null) {
      this.banks = banks;
    }

    Observable.from(banks).map(new Func1<JMemberBankAccount, String>() {
      @Override public String call(JMemberBankAccount jMemberBankAccount) {
        String accountNumber = jMemberBankAccount.getAccountNumber();
        return jMemberBankAccount.getBankName() + "(" + accountNumber.substring(
            accountNumber.length() - 6) + ")";
      }
    }).toList().map(new Func1<List<String>, String[]>() {
      @Override public String[] call(List<String> strings) {
        return strings.toArray(new String[strings.size()]);
      }
    }).subscribe(new Subscriber<String[]>() {
      @Override public void onCompleted() {

      }

      @Override public void onError(Throwable e) {
        Log.e(TAG, "onError: " + e.toString());
      }

      @Override public void onNext(final String[] strings) {

        Object def = bank.getTag();

        DialogFactory.createSingleChoiceDialog(getActivity(), strings, def == null ? -1 : (int) def,
            new DialogInterface.OnClickListener() {
              @Override public void onClick(DialogInterface dialog, int which) {
                bank.setTag(which);
                bank.setText(strings[which]);
                dialog.dismiss();
              }
            }, "到账银行卡").show();
      }
    });
  }

  @Override public void loadBankEmpty() {
    dimissDialog();
    CommonUtil.showSnackbar("您没有添加银行账户！", layoutRoot);
  }

  @OnClick(R.id.submint) public void onClickSubmit() {


    if (isEmptyAmount()) {
      CommonUtil.showSnackbar("提现金额不能为空", layoutRoot);
      return;
    }

    if (isEmptyBank()) {
      CommonUtil.showSnackbar("开户行不能为空", layoutRoot);
      return;
    }

    CommonUtil.hideSoftInput(this.getActivity());

    asynPost();
  }

  @OnClick(R.id.bank) public void onClickBank() {
    if (banks == null) {
      loadBank();
    } else {
      loadBankSuccess(banks);
    }
  }
}
