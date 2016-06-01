package com.aishang.app.ui.income;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JCheckinRecordResult;
import com.aishang.app.util.CommonUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/2/15.
 */
public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.ViewHolder> {

  List<JCheckinRecordResult.CheckinRecordListBean> items;

  @Inject public IncomeAdapter() {
    items = new ArrayList<JCheckinRecordResult.CheckinRecordListBean>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_income, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(final ViewHolder holder, int position) {

    final JCheckinRecordResult.CheckinRecordListBean item = items.get(position);

    holder.price.setText("价格:" + item.getRoomCharge());
    holder.income.setText("+" + item.getRoomCharge() / 2);

    String type = "";
    switch (item.getPayType()) {
      case 1:
        type = "网上支付";
        break;
      case 2:
        type = "线下支付";
        break;
      case 3:
        type = "APP预订";
        break;
    }

    holder.type.setText(type);

    holder.date.setText(
        item.getInTime().substring(0, 10) + "至" + item.getOutTime().substring(0, 10));

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    try {
      int day = CommonUtil.getGapCount(sdf.parse(item.getInTime()), sdf.parse(item.getOutTime()));
      holder.day.setText("共" + day + "天");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override public int getItemCount() {
    return items.size();
  }

  public List<JCheckinRecordResult.CheckinRecordListBean> getItems() {
    return items;
  }

  public void setItems(List<JCheckinRecordResult.CheckinRecordListBean> items) {
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
    @Bind(R.id.type) TextView type;
    @Bind(R.id.income) TextView income;
    @Bind(R.id.date) TextView date;
    @Bind(R.id.price) TextView price;
    @Bind(R.id.day) TextView day;

    public Context getContext() {
      return this.itemView.getContext();
    }

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
