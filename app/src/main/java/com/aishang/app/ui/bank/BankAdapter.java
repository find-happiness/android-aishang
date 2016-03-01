package com.aishang.app.ui.bank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JMemberBankAccount;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/2/15.
 */
public class BankAdapter extends RecyclerView.Adapter<BankAdapter.ViewHolder> {
  List<JMemberBankAccount> items;


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

    public Context getContext() {
      return this.itemView.getContext();
    }

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
