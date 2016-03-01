package com.aishang.app.ui.MyBuyAndSale;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JRentalListResult;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/2/17.
 */
public class RentAdapter extends RecyclerView.Adapter<RentAdapter.ViewHolder> {

  List<JRentalListResult.RentalItem> items;

  @Inject public RentAdapter() {
    items = new ArrayList<JRentalListResult.RentalItem>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buy, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(final ViewHolder holder, int position) {

    final JRentalListResult.RentalItem item = items.get(position);

    holder.name.setText(item.getName());
    holder.address.setText("地址:" + item.getAddress());
    holder.roomNum.setText("房间数:");

    if (item.getStatus() == 0) {
      holder.status.setText("状态");
    } else {
      holder.status.setText("状态");
    }

    holder.rentDate.setText(
        "出租时间:" + item.getResStartDate().split(" ")[0] + "-" + item.getResEndDate().split(" ")[0]);

    holder.price.setText(item.getPriceText());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        //intentToDetail(holder.getContext(), hotel.getHotelID(), hotel.getName());
      }
    });
  }

  @Override public int getItemCount() {
    return items.size();
  }

  public List<JRentalListResult.RentalItem> getItems() {
    return items;
  }

  public void setItems(List<JRentalListResult.RentalItem> items) {
    this.items = items;
  }

  private void intentToDetail(Context ctx, int hotelID, String hotelName) {

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
    @Bind(R.id.rent_date) TextView rentDate;
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