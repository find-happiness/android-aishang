package com.aishang.app.ui.MyHouse;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JMyBusinessBuyInListResult;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/2/15.
 */
public class MyHouseAdapter extends RecyclerView.Adapter<MyHouseAdapter.ViewHolder> {

  List<JMyBusinessBuyInListResult.BuyIn> items;

  @Inject public MyHouseAdapter() {
    items = new ArrayList<JMyBusinessBuyInListResult.BuyIn>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_house, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(final ViewHolder holder, int position) {

    final JMyBusinessBuyInListResult.BuyIn item = items.get(position);

    holder.name.setText(item.getName());
    holder.inMoney.setText("");
    holder.outMoney.setText("");

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        //intentToDetail(holder.getContext(), hotel.getHotelID(), hotel.getName());
      }
    });
  }

  @Override public int getItemCount() {
    return items.size();
  }

  public List<JMyBusinessBuyInListResult.BuyIn> getItems() {
    return items;
  }

  public void setItems(List<JMyBusinessBuyInListResult.BuyIn> items) {
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

    @Bind(R.id.name) TextView name;
    @Bind(R.id.room_num) TextView roomNum;
    @Bind(R.id.address) TextView address;
    @Bind(R.id.commit) TextView commit;
    @Bind(R.id.in_money) TextView inMoney;
    @Bind(R.id.out_money) TextView outMoney;

    public Context getContext() {
      return this.itemView.getContext();
    }

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
