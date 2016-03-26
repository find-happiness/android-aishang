package com.aishang.app.ui.MyCard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JMyVacationListResult;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/2/15.
 */
public class MyCardAdapter extends RecyclerView.Adapter<MyCardAdapter.ViewHolder> {
  List<JMyVacationListResult.JMyVacationListMyVaList> items;


  @Inject public MyCardAdapter() {
    items = new ArrayList<JMyVacationListResult.JMyVacationListMyVaList>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_card, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(final ViewHolder holder, int position) {

    final JMyVacationListResult.JMyVacationListMyVaList item = items.get(position);


    holder.name.setText(item.getHotelName());

    holder.type.setText("类型:" + item.getCardName());

    holder.roomNum.setText("卡号:" + item.getCardID());

    holder.jiefen.setText("换住积分:" + item.getCreditTotal());

    holder.dealDate.setText("交房日期:"+item.getEffectiveDate());

    holder.buyDate.setText("购买日期:" +item.getExpireDate());

    //TODO 添加金额
    holder.price.setText("购买金额:");

  }

  @Override public int getItemCount() {
    return items.size();
  }

  public List<JMyVacationListResult.JMyVacationListMyVaList> getItems() {
    return items;
  }

  public void setItems(List<JMyVacationListResult.JMyVacationListMyVaList> items) {
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
    @Bind(R.id.type) TextView type;
    @Bind(R.id.huxing) TextView huxing;
    @Bind(R.id.address) TextView address;
    @Bind(R.id.room_num) TextView roomNum;
    @Bind(R.id.buy_date) TextView buyDate;
    @Bind(R.id.deal_date) TextView dealDate;
    @Bind(R.id.jiefen) TextView jiefen;
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
