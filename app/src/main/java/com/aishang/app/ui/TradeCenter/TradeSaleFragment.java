package com.aishang.app.ui.TradeCenter;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JLoupanProductVIPViewResult;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.NetworkUtil;
import java.util.List;
import javax.inject.Inject;

/**
 * A fragment with a Google +1 button.
 * Use the {@link TradeSaleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TradeSaleFragment extends Fragment implements TradeSaleMvpView {

  @Inject TradeSalePresenter presenter;

  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
  @Bind(R.id.layoutRoot) FrameLayout layoutRoot;

  private String mParam1;
  private String mParam2;
  private Dialog progress;

  public TradeSaleFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment TradeSaleFragment.
   */
  public static TradeSaleFragment newInstance(String param1, String param2) {
    TradeSaleFragment fragment = new TradeSaleFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((TradeCenterActivity) getActivity()).getActivityComponent().inject(this);
    presenter.attachView(this);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_trade_sale, container, false);

    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    if (NetworkUtil.isNetworkConnected(this.getActivity())) {
      presenter.loadLoupanProductVIPView(0,
          AiShangUtil.generLoupanProductVIPViewParam("02368400000",
              "CAA82AACA9D475482AA516AC8DE8D4CA", 1));
    } else {
      CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
    }
  }

  @Override public void onResume() {
    super.onResume();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();

    if (presenter != null) presenter.detachView();
  }

  @Override public void showDialog() {
    progress = DialogFactory.createProgressDialog(this.getActivity(), R.string.listview_loading);
    progress.show();
  }

  @Override public void dimissDialog() {
    if (progress != null && progress.isShowing()) {
      progress.dismiss();
    }
  }

  @Override public void showError(String error) {
    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override public void bindLoupan(
      List<JLoupanProductVIPViewResult.ZoneListBean.LoupanListBean> loupanBeans) {

  }
}
