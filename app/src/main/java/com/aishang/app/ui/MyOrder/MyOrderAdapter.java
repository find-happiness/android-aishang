package com.aishang.app.ui.MyOrder;

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
import com.aishang.app.data.model.JMyVacationApplyListResult;
import com.aishang.app.ui.HotelDetail.HotelDetailActivity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/2/15.
 */
public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

  public Date checkInDate;
  public Date checkOutDate;

  List<JMyVacationApplyListResult.JItem> items;

  @Inject public MyOrderAdapter() {
    items = new ArrayList<JMyVacationApplyListResult.JItem>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_order, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(final ViewHolder holder, int position) {

    final JMyVacationApplyListResult.JItem hotel = items.get(position);

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

  public List<JMyVacationApplyListResult.JItem> getItems() {
    return items;
  }

  public void setItems(List<JMyVacationApplyListResult.JItem> items) {
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
    @Bind(R.id.status) TextView status;
    @Bind(R.id.room_num) TextView roomNum;
    @Bind(R.id.time) TextView time;
    @Bind(R.id.address) TextView address;
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
