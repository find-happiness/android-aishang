package com.aishang.app.ui.CashWithDrawApply;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.util.BusProvider;
import com.aishang.app.util.EventPosterHelper;
import com.shizhefei.fragment.LazyFragment;
import com.squareup.otto.Subscribe;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link RechargeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RechargeFragment extends LazyFragment {
  private static final String TAG = "RechargeFragment";
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  // The request code must be 0 or greater.
  private static final int PLUS_ONE_REQUEST_CODE = 0;

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @return A new instance of fragment FavoritesFragment.
   */
  public static RechargeFragment newInstance() {
    RechargeFragment fragment = new RechargeFragment();
    return fragment;
  }

  public RechargeFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override public void onAttach(Activity context) {
    super.onAttach(context);
  }

  @Override protected void onCreateViewLazy(Bundle savedInstanceState) {
    super.onCreateViewLazy(savedInstanceState);
    setContentView(R.layout.fragment_recharge);
    ButterKnife.bind(this, this.getContentView());
  }

  @Override public void onDetach() {
    super.onDetach();
  }

  @Override protected void onResumeLazy() {
    super.onResumeLazy();
    BusProvider.getInstance().register(this);
  }

  @Override protected void onPauseLazy() {
    super.onPauseLazy();
    BusProvider.getInstance().unregister(this);
  }

  @Subscribe public void onPost(PostEvent event) {
    Log.i(TAG, "onPost: RechargeFragment------>" + event.getFragment());
    //if(fragid == 0){
    //  Log.i(TAG, "onPost: RechargeFragment------>");
    //}
  }
}
