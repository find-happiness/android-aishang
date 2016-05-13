package com.aishang.app.ui.AccountCenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aishang.app.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreditLeftFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreditLeftFragment extends Fragment {


  public CreditLeftFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @return A new instance of fragment CreditLeftFragment.
   */
  public static CreditLeftFragment newInstance() {
    CreditLeftFragment fragment = new CreditLeftFragment();
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_credit_left, container, false);
  }
}
