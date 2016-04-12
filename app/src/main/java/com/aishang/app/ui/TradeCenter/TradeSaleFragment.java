package com.aishang.app.ui.TradeCenter;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.R;
import com.aishang.app.data.model.JLoupanProductDetailResult;
import com.aishang.app.data.model.JLoupanProductVIPViewResult;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.NetworkUtil;
import com.aishang.app.widget.NonScrollGridView;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.functions.Func1;

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
  @Bind(R.id.layoutRoot) NestedScrollView layoutRoot;
  @Bind(R.id.loupan_name) TextView loupanName;
  @Bind(R.id.loupan_address) TextView loupanAddress;
  @Bind(R.id.type) TextView type;
  @Bind(R.id.move_in_date) TextView moveInDate;
  @Bind(R.id.buy_date) TextView buyDate;
  @Bind(R.id.price) TextView price;
  @Bind(R.id.gridview_loupan_img) NonScrollGridView gridviewLoupanImg;
  @Bind(R.id.gridview_zhenshu_img) NonScrollGridView gridviewZhenshuImg;
  @Bind(R.id.description) WebView description;
  @Bind(R.id.transportation) WebView transportation;

  private String mParam1;
  private String mParam2;
  private Dialog progress;

  private int selectLoupanIndex = 0;

  Observable<List<String>> loupanNames;

  List<JLoupanProductVIPViewResult.ZoneListBean.LoupanListBean> loupanBeans;

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
    this.loupanBeans = loupanBeans;
    loupanNames = Observable.from(loupanBeans)
        .map(new Func1<JLoupanProductVIPViewResult.ZoneListBean.LoupanListBean, String>() {
          @Override public String call(
              JLoupanProductVIPViewResult.ZoneListBean.LoupanListBean loupanListBean) {
            return loupanListBean.getName();
          }
        })
        .toList();
    JLoupanProductVIPViewResult.ZoneListBean.LoupanListBean bean =
        loupanBeans.get(selectLoupanIndex);

    loupanName.setText(bean.getName());
    loupanAddress.setText(bean.getAddress());

    presenter.loadLoupanProductDetail(1,
        AiShangUtil.generLoupanProductDetail(bean.getProductID(), 1, 1));
  }

  @Override public void bindLoupanDetail(JLoupanProductDetailResult result) {
    JLoupanProductDetailResult.Data dataSet = result.getDataSet();
    JLoupanProductDetailResult.Data.LoupanData loupanData = dataSet.getLoupanData();

    AiShangUtil.setWebViewContent(description, loupanData.getProjectIntro());

    AiShangUtil.setWebViewContent(transportation, loupanData.getTransportation());


  }

  @OnClick({ R.id.loupan_name, R.id.type, R.id.buy_date }) public void onClick(View view) {
    switch (view.getId()) {
      case R.id.loupan_name:
        showLoupanNameDialog();
        break;
      case R.id.type:
        break;
      case R.id.buy_date:
        break;
    }
  }

  void showLoupanNameDialog() {
    loupanNames.subscribe(new Action1<List<String>>() {
      @Override public void call(final List<String> strings) {

        DialogFactory.createSingleChoiceDialog(TradeSaleFragment.this.getActivity(),
            strings.toArray(new String[strings.size()]), selectLoupanIndex,
            new DialogInterface.OnClickListener() {
              @Override public void onClick(DialogInterface dialog, int which) {

                int bakSelectPrice = selectLoupanIndex;
                selectLoupanIndex = which;
                dialog.dismiss();

                if (bakSelectPrice != selectLoupanIndex) {
                  loupanName.setText(strings.get(which));
                  loupanAddress.setText(loupanBeans.get(which).getAddress());
                  presenter.loadLoupanProductDetail(1, AiShangUtil.generLoupanProductDetail(
                      loupanBeans.get(selectLoupanIndex).getProductID(), 1, 1));
                }
              }
            }, "项目名称").show();
      }
    });
  }
}
