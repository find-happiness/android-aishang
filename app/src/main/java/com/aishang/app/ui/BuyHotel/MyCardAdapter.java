package com.aishang.app.ui.BuyHotel;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JMyVacationListResult;
import com.aishang.app.injection.ActivityContext;
import com.aishang.app.util.EventPosterHelper;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/2/15.
 */
public class MyCardAdapter extends RecyclerView.Adapter<MyCardAdapter.ViewHolder> {
  List<JMyVacationListResult.MemberexCardListEntity> items;

  private int selectIndex = 0;

  Context ctx;
  @Inject EventPosterHelper eventPosterHelper;

  @Inject public MyCardAdapter(@ActivityContext Context ctx) {
    items = new ArrayList<JMyVacationListResult.MemberexCardListEntity>();
    this.ctx = ctx;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member_card, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(final ViewHolder holder, final int position) {

    final JMyVacationListResult.MemberexCardListEntity item = items.get(position);

    if (position == selectIndex) {
      holder.cardInfoContaier.setBackgroundColor(ctx.getResources().getColor(R.color.primary));
    } else {
      holder.cardInfoContaier.setBackgroundColor(Color.GRAY);
    }

    holder.roomNum.setText("卡号:" + item.getCardNumber());

    holder.jiefen.setText("换住积分:" + item.getIntegral());

    holder.dealDate.setText("有效日期:" + item.getValidDate());

    holder.buyDate.setText("购买日期:" + item.getCreateTime());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        //if (selectIndex == position) {
        //  selectIndex = -1;
        //  eventPosterHelper.postEventSafely(new Integer(2));
        //} else {

        //}

        if (selectIndex != position) {
          eventPosterHelper.postEventSafely(item);
          selectIndex = position;
          notifyDataSetChanged();
        }
      }
    });
  }

  @Override public int getItemCount() {
    return items.size();
  }

  public List<JMyVacationListResult.MemberexCardListEntity> getItems() {
    return items;
  }

  public void setItems(List<JMyVacationListResult.MemberexCardListEntity> items) {
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
    @Bind(R.id.room_num) TextView roomNum;
    @Bind(R.id.buy_date) TextView buyDate;
    @Bind(R.id.deal_date) TextView dealDate;
    @Bind(R.id.jiefen) TextView jiefen;
    @Bind(R.id.cardInfoContaier) CardView cardInfoContaier;

    public Context getContext() {
      return this.itemView.getContext();
    }

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
