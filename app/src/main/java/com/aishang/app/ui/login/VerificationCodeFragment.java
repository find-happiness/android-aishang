package com.aishang.app.ui.login;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.R;
import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnVerificationCodeFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VerificationCodeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VerificationCodeFragment extends Fragment {

  @Bind(R.id.phone) MaterialEditText phone;
  @Bind(R.id.verify_code) MaterialEditText verifyCode;
  private OnVerificationCodeFragmentInteractionListener mListener;

  public VerificationCodeFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @return A new instance of fragment VerificationCodeFragment.
   */
  public static VerificationCodeFragment newInstance(String param1, String param2) {
    VerificationCodeFragment fragment = new VerificationCodeFragment();
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_verification_code, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnVerificationCodeFragmentInteractionListener) {
      mListener = (OnVerificationCodeFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(
          context.toString() + " must implement OnPasswordFragmentInteractionListener");
    }
  }

  @Override public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  @OnClick(R.id.btn_login) void onLoginClick() {
    if (mListener != null) {
      mListener.onVerificationCodeLogin(phone.getText().toString(),
          verifyCode.getText().toString());
    }
  }

  @OnClick(R.id.btn_register) void onRegisterClick() {
    if (mListener != null) {
      mListener.onRegister();
    }
  }

  @OnClick(R.id.btn_get_verification_code) void getVerificationCodeClick() {

    if (mListener != null) {
      ((LoginActivity) mListener).getVerificationCode(phone.getText().toString());
    }
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
   * <p/>
   * See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnVerificationCodeFragmentInteractionListener {
    void onVerificationCodeLogin(String phone, String code);

    void onRegister();
  }
}
