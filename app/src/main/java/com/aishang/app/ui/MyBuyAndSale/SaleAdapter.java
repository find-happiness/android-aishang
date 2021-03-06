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
public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.ViewHolder> {

  List<JRentalListResult.RentalItem> items;

  @Inject public SaleAdapter() {
    items = new ArrayList<JRentalListResult.RentalItem>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sale, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(final ViewHolder holder, int position) {

    final JRentalListResult.RentalItem item = items.get(position);

    holder.name.setText(item.getName());
    holder.type.setText("项目类型:");
    holder.buyDate.setText("购买日期:" + item.getPurchaseDate().split(" ")[0]);
    holder.dealDate.setText("成交日期:");
    holder.moveInDate.setText("交房日期:");
    holder.price.setText("成交价格:"+item.getPriceText());
    holder.roomNum.setText("项目房号:");
    holder.address.setText("项目地址:" + item.getAddress());
    holder.status.setText(item.getStatus()==0?"状态":"状态");
    holder.huxing.setText("户型:");

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
    @Bind(R.id.type) TextView type;
    @Bind(R.id.huxing) TextView huxing;
    @Bind(R.id.address) TextView address;
    @Bind(R.id.room_num) TextView roomNum;
    @Bind(R.id.buy_date) TextView buyDate;
    @Bind(R.id.move_in_date) TextView moveInDate;
    @Bind(R.id.deal_date) TextView dealDate;
    @Bind(R.id.price) TextView price;
    @Bind(R.id.status) TextView status;

    public Context getContext() {
      return this.itemView.getContext();
    }

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}