package com.aishang.app.ui.BuyHotel;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.R;
import com.aishang.app.data.model.JMemberGiftcardResult;
import com.aishang.app.data.model.JMemberProfileResult;
import com.aishang.app.data.model.JMyVacationListResult;
import com.aishang.app.widget.RadioGroup;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentFragment extends Fragment {
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
  @Bind(R.id.tong_yong_ji_fen) RadioButton tongYongJiFen;
  @Bind(R.id.huan_zhu_ji_fen) RadioButton huanZhuJiFen;
  @Bind(R.id.alipay) RadioButton alipay;
  @Bind(R.id.wechat) RadioButton wechat;
  @Bind(R.id.paymentGroup) RadioGroup paymentGroup;
  @Bind(R.id.tong_yong_ji_fen_Content) LinearLayout tongYongJiFenContent;
  @Bind(R.id.huan_zhu_ji_fen_Content) LinearLayout huanZhuJiFenContent;
  @Bind(R.id.you_hui_quan) TextView youHuiQuan;
  @Bind(R.id.price) TextView price;
  @Bind(R.id.price_container) LinearLayout priceContainer;
  @Bind(R.id.name) TextView name;
  @Bind(R.id.status) TextView status;
  @Bind(R.id.content_container) LinearLayout contentContainer;
  @Bind(R.id.card_info_contaier) RelativeLayout cardInfoContaier;
  @Bind(R.id.date) TextView date;
  @Bind(R.id.giftCard_Container) CardView giftCardContainer;

  JMemberGiftcardResult.MemberGiftcardListEntity selectGift;
  JMyVacationListResult.MemberexCardListEntity selectCard;
  @Bind(R.id.pay_total_1) TextView payTotal1;
  @Bind(R.id.awardLeft) TextView awardLeft;
  @Bind(R.id.use_jifen_1) TextView useJifen1;
  @Bind(R.id.di_kou_jin_e_1) TextView diKouJinE1;
  @Bind(R.id.pay_total_2) TextView payTotal2;
  @Bind(R.id.card_number) TextView cardNumber;
  @Bind(R.id.cardCredit) TextView cardCredit;
  @Bind(R.id.use_jifen_2) TextView useJifen2;
  @Bind(R.id.di_kou_jin_e_2) TextView diKouJinE2;
  @Bind(R.id.space_line) View spaceLine;
  private int priceTotal;

  private OnFragmentInteractionListener mListener;

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment PaymentFragment.
   */
  public static PaymentFragment newInstance(String param1, String param2) {
    PaymentFragment fragment = new PaymentFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  public PaymentFragment() {
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
    View view = inflater.inflate(R.layout.fragment_payment, container, false);
    ButterKnife.bind(this, view);
    initView();
    return view;
  }

  private void initView() {
    initPayment();

    giftCardContainer.setVisibility(View.GONE);
  }

  private void initPayment() {
    tongYongJiFenContent.setVisibility(View.VISIBLE);
    huanZhuJiFenContent.setVisibility(View.GONE);
    paymentGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override public void onCheckedChanged(RadioGroup group, int checkedId) {
        BuyHotelActivity.PAYMENT payment = BuyHotelActivity.PAYMENT.UNKNOW;

        switch (checkedId) {
          case R.id.tong_yong_ji_fen:
            payment = BuyHotelActivity.PAYMENT.TONG_YONG_JI_FEN;
            break;
          case R.id.huan_zhu_ji_fen:
            payment = BuyHotelActivity.PAYMENT.HUAN_ZU_JI_FEN;
            break;
          case R.id.alipay:
            payment = BuyHotelActivity.PAYMENT.ALIPAY;
            break;
          case R.id.wechat:
            payment = BuyHotelActivity.PAYMENT.WECHAT;
            break;
        }

        onPaymentChange(payment);
      }
    });

    tongYongJiFen.toggle();
  }

  private void onPaymentChange(BuyHotelActivity.PAYMENT payment) {

    switch (payment) {
      case TONG_YONG_JI_FEN:
        tongYongJiFenContent.setVisibility(View.VISIBLE);
        huanZhuJiFenContent.setVisibility(View.GONE);
        break;
      case HUAN_ZU_JI_FEN:
        tongYongJiFenContent.setVisibility(View.GONE);
        huanZhuJiFenContent.setVisibility(View.VISIBLE);
        break;
      case ALIPAY:
        tongYongJiFenContent.setVisibility(View.GONE);
        huanZhuJiFenContent.setVisibility(View.GONE);
        break;
      case WECHAT:
        tongYongJiFenContent.setVisibility(View.GONE);
        huanZhuJiFenContent.setVisibility(View.GONE);
        break;
    }

    if (mListener != null) {
      mListener.onPaymentChange(payment);
    }
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(
          context.toString() + " must implement OnFragmentInteractionListener");
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

  public void onGiftCardSelect(JMemberGiftcardResult.MemberGiftcardListEntity bean) {
    giftCardContainer.setVisibility(View.VISIBLE);
    selectGift = bean;
    price.setText(bean.getValue() + "");
    name.setText(bean.getGiftName());
    status.setText(bean.getStatus() == 0 ? "未使用" : "已使用");
    date.setText("使用时间:" + (bean.getStatus() == 0 ? (bean.getStartDate().substring(0, 10)
        + "至"
        + bean.getEndDate().substring(0, 10)) : bean.getStartDate().substring(0, 10)));
  }

  public void onGiftCardUnselect() {
    giftCardContainer.setVisibility(View.GONE);
    selectGift = null;
  }

  public void onMyCardSelect(JMyVacationListResult.MemberexCardListEntity bean) {
    selectCard = bean;
    cardNumber.setText(bean.getCardNumber());
  }

  public void onMyCardUnselect() {
    giftCardContainer.setVisibility(View.GONE);
    selectGift = null;
  }

  public void setPriceTotal(int priceTotal) {
    payTotal1.setText("￥" + priceTotal);
    payTotal2.setText("￥" + priceTotal);
    this.priceTotal = priceTotal;
    diKouJinE1.setText("￥" + priceTotal);
    diKouJinE2.setText("￥" + priceTotal);
    useJifen1.setText(this.priceTotal + "");
    useJifen2.setText(this.priceTotal + "");
  }

  public void setMemberProfile(JMemberProfileResult memberProfile) {
    JMemberProfileResult.Data data = memberProfile.getData();
    awardLeft.setText(data.getCreditLeft() + "");
    cardCredit.setText(data.getCardCredit() + "");
  }

  @OnClick({ R.id.card_number, R.id.you_hui_quan }) public void onClick(View view) {
    switch (view.getId()) {
      case R.id.card_number:
        if (mListener != null) {
          mListener.onMyCardClick();
        }
        break;
      case R.id.you_hui_quan:
        if (mListener != null) {
          mListener.onGiftCardCardClick();
        }
        break;
    }
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
  public interface OnFragmentInteractionListener {
    void onPaymentChange(BuyHotelActivity.PAYMENT payment);

    void onGiftCardCardClick();

    void onMyCardClick();
  }
}
