package com.aishang.app.ui.BuyHotel;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.data.model.JMyVacationListResult;
import com.aishang.app.ui.MyCard.*;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.NetworkUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import java.util.ArrayList;
import java.util.Arrays;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentCardListener} interface
 * to handle interaction events.
 * Use the {@link CardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardFragment extends Fragment implements MyCardMvpView {

  @Bind(R.id.recyclerView) XRecyclerView mRecyclerView;
  @Bind(R.id.swipe_refresh) SwipeRefreshLayout swipeRefreshLayout;
  @Bind(R.id.avloadingIndicatorView) AVLoadingIndicatorView avloadingIndicatorView;
  @Bind(R.id.no_data_text) TextView noDataText;
  @Bind(R.id.empty_view) RelativeLayout noDataHotel;
  @Inject MyCardPresenter presenter;
  @Inject MyCardAdapter adapter;

  private OnFragmentCardListener mListener;

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @return A new instance of fragment CardFragment.
   */
  public static CardFragment newInstance() {
    CardFragment fragment = new CardFragment();
    return fragment;
  }

  public CardFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_card, container, false);
    ButterKnife.bind(this, view);

    initSwipeRefresh();
    initRefreshLayout();
    proLoad();

    return view;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    ((BaseActivity) context).getActivityComponent().inject(this);

    presenter.attachView(this);

    if (context instanceof OnFragmentCardListener) {
      mListener = (OnFragmentCardListener) context;
    } else {
      throw new RuntimeException(
          context.toString() + " must implement OnFragmentInteractionListener");
    }
  }

  @Override public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  private void autoRefresh() {
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        proLoad();
      }
    }, 100);
  }

  private void initSwipeRefresh() {
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        asynMyOrder();
      }
    });
  }

  @Override public void showError(String error) {

  }

  @Override public void showEmpty() {
    if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
      avloadingIndicatorView.setVisibility(View.GONE);
    }

    if (noDataHotel.getVisibility() != View.VISIBLE) {
      noDataHotel.setVisibility(View.VISIBLE);
    }

    mRecyclerView.refreshComplete();
    adapter.getItems().clear();
    adapter.notifyDataSetChanged();
  }

  @Override public void refreshCompleted() {
    if (swipeRefreshLayout.isRefreshing()) {
      swipeRefreshLayout.setRefreshing(false);
    }
  }

  @Override public void refreshList(JMyVacationListResult result) {

    if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
      avloadingIndicatorView.setVisibility(View.GONE);
    }

    if (noDataHotel.getVisibility() == View.VISIBLE) {
      noDataHotel.setVisibility(View.GONE);
    }

    adapter.getItems().clear();
    adapter.getItems().addAll(result.getMemberexCardList());

    adapter.notifyDataSetChanged();
    mRecyclerView.refreshComplete();

    if (mListener != null && result.getMemberexCardList().size() > 0) {
      mListener.defaultCard(result.getMemberexCardList().get(0));
    }
  }

  private void proLoad() {
    if (NetworkUtil.isNetworkConnected(this.getActivity())) {
      if (avloadingIndicatorView.getVisibility() != View.VISIBLE) {
        avloadingIndicatorView.setVisibility(View.VISIBLE);
      }

      if (noDataHotel.getVisibility() == View.VISIBLE) {
        noDataHotel.setVisibility(View.GONE);
      }
      asynMyOrder();
    } else {

      if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
        avloadingIndicatorView.setVisibility(View.GONE);
      }

      if (noDataHotel.getVisibility() == View.VISIBLE) {
        noDataHotel.setVisibility(View.GONE);
      }
    }
  }

  private void initRefreshLayout() {
    LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    mRecyclerView.setLayoutManager(layoutManager);

    mRecyclerView.addItemDecoration(
        new HorizontalDividerItemDecoration.Builder(this.getActivity()).colorResId(
            android.R.color.transparent).sizeResId(R.dimen.spacing_medium).build());

    mRecyclerView.setLoadingMoreEnabled(false);
    mRecyclerView.setPullRefreshEnabled(false);
    mRecyclerView.setAdapter(adapter);
  }

  private void asynMyOrder() {
    swipeRefreshLayout.setRefreshing(true);
    String cookie = BoilerplateApplication.get(this.getActivity())
        .getMemberLoginResult()
        .getData()
        .getCookies();
    String member = BoilerplateApplication.get(this.getActivity()).getMemberAccount();
    String json = AiShangUtil.generMyVacationListParam(member, cookie, "2012-01-01");
    presenter.loadData(0, json);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   * <p>
   * See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnFragmentCardListener {
    void defaultCard(JMyVacationListResult.MemberexCardListEntity card);
  }
}
