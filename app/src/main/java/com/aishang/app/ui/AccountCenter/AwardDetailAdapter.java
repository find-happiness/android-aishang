package com.aishang.app.ui.AccountCenter;

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
import com.aishang.app.data.model.JAwardDetailListV2Result;
import com.aishang.app.data.model.JMemberBankAccount;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.bank.BankListActivity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/2/15.
 */
public class AwardDetailAdapter extends RecyclerView.Adapter<AwardDetailAdapter.ViewHolder> {
  List<JAwardDetailListV2Result.DataListBean> items;

  @Inject public AwardDetailAdapter() {
    items = new ArrayList<JAwardDetailListV2Result.DataListBean>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_award_detail, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(final ViewHolder holder, int position) {

    final JAwardDetailListV2Result.DataListBean item = items.get(position);
  }

  @Override public int getItemCount() {
    return items.size();
  }

  public void clearDate(){
    items.clear();
  }

  public void addData(List<JAwardDetailListV2Result.DataListBean> list){
    items.addAll(list);
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

    public Context getContext() {
      return this.itemView.getContext();
    }

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
