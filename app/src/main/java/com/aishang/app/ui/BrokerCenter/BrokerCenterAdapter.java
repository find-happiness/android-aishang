package com.aishang.app.ui.BrokerCenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JBusinessListResult;
import com.aishang.app.ui.HotelDetail.HotelDetailActivity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/2/15.
 */
public class BrokerCenterAdapter extends RecyclerView.Adapter<BrokerCenterAdapter.ViewHolder> {

  public Date checkInDate;
  public Date checkOutDate;

  List<JBusinessListResult.ListJDataBean> items;

  @Inject public BrokerCenterAdapter() {
    items = new ArrayList<JBusinessListResult.ListJDataBean>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(final ViewHolder holder, int position) {

    final JBusinessListResult.ListJDataBean item = items.get(position);

    holder.name.setText(item.getName());
    holder.phone.setText(item.getTelephone());
    holder.status.setText(item.getStatus());
    holder.date.setText(item.getCreateTime());
    holder.price.setText("分佣" + item.getCommission());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        //intentToDetail(holder.getContext(), hotel.getHotelID(), hotel.getName());
      }
    });
  }

  @Override public int getItemCount() {
    return items.size();
  }

  public void setCheckInDate(Date checkInDate) {
    this.checkInDate = checkInDate;
  }

  public void setCheckOutDate(Date checkOutDate) {
    this.checkOutDate = checkOutDate;
  }

  public List<JBusinessListResult.ListJDataBean> getItems() {
    return items;
  }

  public void setItems(List<JBusinessListResult.ListJDataBean> items) {
    this.items = items;
  }

  private void intentToDetail(Context ctx, int hotelID, String hotelName) {
    Intent intent =
        HotelDetailActivity.getStartIntent(ctx, hotelID, hotelName, checkInDate.getTime(),
            checkOutDate.getTime());
    ctx.startActivity(intent);
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
    @Bind(R.id.name) TextView name;
    @Bind(R.id.phone) TextView phone;
    @Bind(R.id.status) TextView status;
    @Bind(R.id.date) TextView date;
    @Bind(R.id.price) TextView price;

    public Context getContext() {
      return this.itemView.getContext();
    }

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
