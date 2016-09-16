package com.aishang.app.ui.BuyHotel;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.data.model.JMemberGiftcardResult;
import com.aishang.app.ui.MemberGiftcard.MemberGiftcarMvpView;
import com.aishang.app.ui.MemberGiftcard.MemberGiftcarPresenter;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.NetworkUtil;
import com.aishang.app.widget.SpacesItemDecoration;
import com.shizhefei.fragment.LazyFragment;
import java.util.ArrayList;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link GiftcarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GiftcarFragment extends LazyFragment implements MemberGiftcarMvpView {
  private static final String TAG = "RechargeFragment";

  private static final String ARG_PARAM1 = "param1";
  @Bind(R.id.recyclerView) RecyclerView recyclerView;
  @Bind(R.id.empty_view) RelativeLayout noData;

  @Inject GiftcarAdapter adapter;
  @Inject MemberGiftcarPresenter giftcarPresenter;

  ArrayList<JMemberGiftcardResult.MemberGiftcardListEntity> memberGiftcardStatus1 =
      new ArrayList<>();
  @Bind(R.id.no_data_text) TextView noDataText;

  private int mHotelID;

  // The request code must be 0 or greater.
  private static final int PLUS_ONE_REQUEST_CODE = 0;

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @return A new instance of fragment FavoritesFragment.
   */
  public static GiftcarFragment newInstance(int hotelID) {
    GiftcarFragment fragment = new GiftcarFragment();
    Bundle bundle = new Bundle();
    bundle.putInt(ARG_PARAM1, hotelID);
    fragment.setArguments(bundle);
    return fragment;
  }

  public static GiftcarFragment newInstance() {
    GiftcarFragment fragment = new GiftcarFragment();
    return fragment;
  }

  public GiftcarFragment() {
    // Required empty public constructor
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);

    ((BaseActivity) this.getActivity()).getActivityComponent().inject(this);

    giftcarPresenter.attachView(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();

    giftcarPresenter.detachView();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mHotelID = getArguments().getInt(ARG_PARAM1);
    }
  }

  @Override public void onAttach(Activity context) {
    super.onAttach(context);
  }

  @Override protected void onCreateViewLazy(Bundle savedInstanceState) {
    super.onCreateViewLazy(savedInstanceState);

    setContentView(R.layout.fragment_member_giftcar);
    ButterKnife.bind(this, this.getContentView());
    noDataText.setText("没有可用的优惠券！");
    initNetwork();
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override public void onDetach() {
    super.onDetach();
  }

  @Override protected void onResumeLazy() {
    super.onResumeLazy();
  }

  @Override protected void onPauseLazy() {
    super.onPauseLazy();
  }

  @Override public void onDestroyViewLazy() {
    super.onDestroyViewLazy();
    ButterKnife.unbind(this);
  }

  private void initNetwork() {
    if (NetworkUtil.isNetworkConnected(this.getActivity())) {
      loadMemberGiftcard();
    } else {
      showError(this.getString(R.string.no_net));
    }
  }

  private void loadMemberGiftcard() {

    giftcarPresenter.loadMemberGiftcard(0, gennerGiftcardParam());
  }

  private String gennerGiftcardParam() {
    String cookie = BoilerplateApplication.get(this.getActivity())
        .getMemberLoginResult()
        .getData()
        .getCookies();

    String member = BoilerplateApplication.get(this.getActivity()).getMemberAccount();
    return AiShangUtil.generMemberGiftcardParam(member, cookie, "0", mHotelID + "", "", "");
  }

  private void initRecyclerView() {
    LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(layoutManager);

    recyclerView.addItemDecoration(new SpacesItemDecoration(
        this.getResources().getDimensionPixelSize(R.dimen.spacing_medium)));
    recyclerView.setAdapter(adapter);
  }

  @Override public void showError(String error) {
  }

  @Override public void loadMemberGiftcardSuccess(JMemberGiftcardResult result) {

    Observable.from(result.getMemberGiftcardList())
        .groupBy(new Func1<JMemberGiftcardResult.MemberGiftcardListEntity, Integer>() {
          @Override public Integer call(
              JMemberGiftcardResult.MemberGiftcardListEntity memberGiftcardListBean) {
            return memberGiftcardListBean.getStatus();
          }
        })
        .subscribe(
            new Subscriber<GroupedObservable<Integer, JMemberGiftcardResult.MemberGiftcardListEntity>>() {
              @Override public void onCompleted() {
                adapter.setHotels(memberGiftcardStatus1);
                adapter.notifyDataSetChanged();
              }

              @Override public void onError(Throwable e) {
              }

              @Override public void onNext(
                  final GroupedObservable<Integer, JMemberGiftcardResult.MemberGiftcardListEntity> observable) {

                observable.subscribe(new Action1<JMemberGiftcardResult.MemberGiftcardListEntity>() {
                  @Override public void call(JMemberGiftcardResult.MemberGiftcardListEntity bean) {
                    if (observable.getKey() == 0) {
                      memberGiftcardStatus1.add(bean);
                    }
                  }
                });
              }
            });
  }

  @Override public void loadMemberGiftcardEmpty() {
    recyclerView.setVisibility(View.GONE);
    noData.setVerticalGravity(View.VISIBLE);
  }
}
