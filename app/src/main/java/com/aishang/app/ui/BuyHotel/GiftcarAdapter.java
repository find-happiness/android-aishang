package com.aishang.app.ui.BuyHotel;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JMemberGiftcardResult;
import com.aishang.app.injection.ActivityContext;
import com.aishang.app.util.EventPosterHelper;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/9/14.
 */
public class GiftcarAdapter extends RecyclerView.Adapter<GiftcarAdapter.ViewHolder> {

  List<JMemberGiftcardResult.MemberGiftcardListEntity> hotels;
  private int selectIndex = -1;
  @Inject EventPosterHelper eventPosterHelper;
  Context ctx;

  @Inject public GiftcarAdapter(@ActivityContext Context ctx) {
    hotels = new ArrayList<>();
    this.ctx = ctx;
  }

  public void setHotels(List<JMemberGiftcardResult.MemberGiftcardListEntity> hotels) {
    this.hotels = hotels;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_member_giftcard, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(final ViewHolder holder, final int position) {

    //holder.address.setText();
    if (position == selectIndex) {
      holder.cardInfoContaier.setBackgroundColor(ctx.getResources().getColor(R.color.primary));
    } else {
      holder.cardInfoContaier.setBackgroundColor(Color.GRAY);
    }
    final JMemberGiftcardResult.MemberGiftcardListEntity bean = hotels.get(position);
    holder.price.setText(bean.getValue() + "");
    holder.name.setText(bean.getGiftName());
    holder.status.setText(bean.getStatus() == 0 ? "未使用" : "已使用");
    holder.date.setText("使用时间:" + (bean.getStatus() == 0 ? (bean.getStartDate().substring(0, 10)
        + "至"
        + bean.getEndDate().substring(0, 10)) : bean.getStartDate().substring(0, 10)));

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (selectIndex == position) {
          selectIndex = -1;
          eventPosterHelper.postEventSafely(new Integer(1));
        } else {
          eventPosterHelper.postEventSafely(bean);
          selectIndex = position;
        }

        notifyDataSetChanged();
      }
    });
  }

  @Override public int getItemCount() {
    return hotels.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.price) TextView price;
    @Bind(R.id.name) TextView name;
    @Bind(R.id.status) TextView status;
    @Bind(R.id.date) TextView date;
    @Bind(R.id.card_info_contaier) RelativeLayout cardInfoContaier;

    public Context getContext() {
      return this.itemView.getContext();
    }

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}