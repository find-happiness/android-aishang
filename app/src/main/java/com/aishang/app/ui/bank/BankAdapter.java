package com.aishang.app.ui.bank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JMemberBankAccount;
import com.aishang.app.data.remote.AiShangService;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/2/15.
 */
public class BankAdapter extends RecyclerView.Adapter<BankAdapter.ViewHolder> {
  List<JMemberBankAccount> items;

  private BankListActivity.ItemClickListener listener;

  public void setListener(BankListActivity.ItemClickListener listener) {
    this.listener = listener;
  }

  @Inject public BankAdapter() {
    items = new ArrayList<JMemberBankAccount>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bank, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(final ViewHolder holder, int position) {

    final JMemberBankAccount item = items.get(position);

    holder.type.setText(item.getType());

    holder.holder.setText(item.getHolder());
    holder.bankName.setText(item.getBankName());
    holder.accountNumber.setText(item.getAccountNumber());

    String cardImgUrl = "";
    switch (item.getBankName()) {
      case "中国银行":
        cardImgUrl = "/images/bank_%20(11).png";
        break;
      case "中国农业银行":
        cardImgUrl = "/images/bank_%20(5).png";
        break;
      case "中国工商银行":
        cardImgUrl = "/images/bank_%20(16).png";
        break;
      case "中国建设银行":
        cardImgUrl = "/images/bank_%20(2).png";
        break;
      case "招商银行":
        cardImgUrl = "/images/bank_%20(10).png";
        break;
      case "交通银行":
        cardImgUrl = "/images/bank_%20(3).png";
        break;
      case "中信银行":
        cardImgUrl = "/images/bank_%20(13).png";
        break;
      case "中国光大银行":
        cardImgUrl = "/images/bank_%20(17).png";
        break;
      case "中国民生银行":
        cardImgUrl = "/images/bank_%20(4).png";
        break;
      case "广发银行":
        cardImgUrl = "/images/bank_%20(18).png";
        break;
      case "华夏银行":
        cardImgUrl = "/images/bank_%20(1).png";
        break;
      case "浦发银行":
        cardImgUrl = "/images/bank_%20(7).png";
        break;
      case "兴业银行":
        cardImgUrl = "/images/bank_%20(9).png";
        break;
      case "中国邮政储蓄银行":
        cardImgUrl = "/images/bank_%20(12).png";
        break;
      default:
        cardImgUrl = "/images/bank_%20(11).png";
        break;
    }

    Picasso.with(holder.getContext())
        .load(AiShangService.IMG_URL + cardImgUrl)
        .error(R.mipmap.default_error)
        .placeholder(R.mipmap.default_error)
        .into(holder.cardTypeImg);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (listener != null) {
          listener.itemClick(item);
        }
      }
    });
  }

  @Override public int getItemCount() {
    return items.size();
  }

  public List<JMemberBankAccount> getItems() {
    return items;
  }

  public void setItems(List<JMemberBankAccount> items) {
    this.items = items;
  }

  /**
   * This class contains all butterknife-injected Views & Layouts from layout file
   * 'item_my_order.xml'
   * for easy to all layout elements.
   *
   * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers
   *         (http://github.com/avast)
   */
  static class ViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.bankName) TextView bankName;
    @Bind(R.id.holder) TextView holder;
    @Bind(R.id.accountNumber) TextView accountNumber;
    @Bind(R.id.type) TextView type;
    @Bind(R.id.card_type_img) ImageView cardTypeImg;

    public Context getContext() {
      return this.itemView.getContext();
    }

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
