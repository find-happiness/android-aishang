package com.aishang.app.ui.MemberGiftcard;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.aishang.app.R;
import com.aishang.app.data.model.JMemberGiftcardResult;
import com.aishang.app.widget.SpacesItemDecoration;
import com.shizhefei.fragment.LazyFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MemberGiftcarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemberGiftcarFragment extends LazyFragment {
  private static final String TAG = "RechargeFragment";

  private static final String ARG_PARAM1 = "param1";
  @Bind(R.id.recyclerView) RecyclerView recyclerView;
  @Bind(R.id.empty_view) RelativeLayout noData;

  private List<JMemberGiftcardResult.MemberGiftcardListEntity> mParam1;

  // The request code must be 0 or greater.
  private static final int PLUS_ONE_REQUEST_CODE = 0;

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @return A new instance of fragment FavoritesFragment.
   */
  public static MemberGiftcarFragment newInstance(ArrayList<? extends Parcelable> beans) {
    MemberGiftcarFragment fragment = new MemberGiftcarFragment();
    Bundle bundle = new Bundle();
    bundle.putParcelableArrayList(ARG_PARAM1, beans);
    fragment.setArguments(bundle);
    return fragment;
  }

  public MemberGiftcarFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getParcelableArrayList(ARG_PARAM1);
    }
  }

  @Override public void onAttach(Activity context) {
    super.onAttach(context);
  }

  @Override protected void onCreateViewLazy(Bundle savedInstanceState) {
    super.onCreateViewLazy(savedInstanceState);
    setContentView(R.layout.fragment_member_giftcar);
    ButterKnife.bind(this, this.getContentView());

    if (mParam1.size() > 0) {
      noData.setVisibility(View.GONE);
      recyclerView.setVisibility(View.VISIBLE);
      initRecyclerView();
    } else {
      recyclerView.setVisibility(View.GONE);
      noData.setVisibility(View.VISIBLE);
    }
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

  private void initRecyclerView() {
    LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(layoutManager);

    recyclerView.addItemDecoration(new SpacesItemDecoration(
        this.getResources().getDimensionPixelSize(R.dimen.spacing_medium)));

    recyclerView.setAdapter(new MyAdapter(mParam1));
  }

  public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    List<JMemberGiftcardResult.MemberGiftcardListEntity> hotels;

    public MyAdapter(List<JMemberGiftcardResult.MemberGiftcardListEntity> beanList) {
      hotels = beanList;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_member_giftcard, parent, false);
      return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(final ViewHolder holder, int position) {

      //holder.address.setText();

      JMemberGiftcardResult.MemberGiftcardListEntity bean = hotels.get(position);
      holder.price.setText(bean.getValue() + "");
      holder.name.setText(bean.getGiftName());
      holder.status.setText(bean.getStatus() == 0 ? "未使用" : "已使用");
      holder.date.setText("使用时间:" + (bean.getStatus() == 0 ? (bean.getStartDate().substring(0, 10)
          + "至"
          + bean.getEndDate().substring(0, 10)) : bean.getStartDate().substring(0, 10)));
    }

    @Override public int getItemCount() {
      return hotels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

      @Bind(R.id.price) TextView price;
      @Bind(R.id.name) TextView name;
      @Bind(R.id.status) TextView status;
      @Bind(R.id.date) TextView date;

      public Context getContext() {
        return this.itemView.getContext();
      }

      public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
      }
    }
  }
}
