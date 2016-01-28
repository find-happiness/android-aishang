package com.aishang.app.ui.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.R;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.RegexUtils;
import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnPasswordFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PasswordFragment extends Fragment {
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  @Bind(R.id.phone) MaterialEditText phone;
  @Bind(R.id.password) MaterialEditText password;
  @Bind(R.id.layoutRoot) CoordinatorLayout layoutRoot;

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  private OnPasswordFragmentInteractionListener mListener;

  public PasswordFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment PasswordFragment.
   */
  public static PasswordFragment newInstance(String param1, String param2) {
    PasswordFragment fragment = new PasswordFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_password, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @OnClick(R.id.btn_login) void onLoginClick() {

    String p = phone.getText().toString().trim();
    String pswd = password.getText().toString().trim();

    if (p.length() <= 0) {
      showLoginError(R.string.no_phone);
      return;
    }

    if (!RegexUtils.checkMobile(p)) {
      showLoginError(R.string.error_phone);
      return;
    }

    if (pswd.length() < 6) {
      showLoginError(R.string.error_password);
      return;
    }

    if (mListener != null) {
      mListener.onPswLogin(p, pswd);
    }
  }

  @OnClick(R.id.btn_register) void onRegisterClick() {
    if (mListener != null) {
      mListener.onRegister();
    }
  }

  @OnClick(R.id.btn_forget) void onForgetClick() {
    if (mListener != null) {
      mListener.onForgetPsw();
    }
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnPasswordFragmentInteractionListener) {
      mListener = (OnPasswordFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(
          context.toString() + " must implement OnPasswordFragmentInteractionListener");
    }
  }

  @Override public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  private void showLoginError(@StringRes int text) {
    //Snackbar.make(layoutRoot, text, Snackbar.LENGTH_LONG).show();
    CommonUtil.showSnackbar(text, layoutRoot);
  }

  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   * <p/>
   * See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnPasswordFragmentInteractionListener {
    void onPswLogin(String phone, String psw);

    void onRegister();

    void onForgetPsw();
  }
}
