package com.aishang.app.ui.KanFanTuan;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JMreActivityListResult;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.HotelDetail.HotelDetailActivity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/2/15.
 */
public class KanFangTuanAdapter extends RecyclerView.Adapter<KanFangTuanAdapter.ViewHolder> {

  public Date checkInDate;
  public Date checkOutDate;

  List<JMreActivityListResult.JActivityItem> items;

  @Inject public KanFangTuanAdapter() {
    items = new ArrayList<JMreActivityListResult.JActivityItem>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_kan_fang_tuan, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(final ViewHolder holder, int position) {

    final JMreActivityListResult.JActivityItem item = items.get(position);

    Picasso.with(holder.getContext())
        .load(AiShangService.IMG_URL + item.getImageUrl())
        .error(R.mipmap.banner)
        .placeholder(R.mipmap.banner)
        .into(holder.img);

    holder.name.setText(item.getTitle());
    holder.address.setText(item.getPosition());
    holder.content.setText(item.getShortDesc());
    holder.priceText.setText(item.getFee()+"");


    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        //intentToDetail(holder.getContext(), hotel.getHotelID(), hotel.getName());
      }
    });
  }

  @Override public int getItemCount() {
    return items.size();
  }

  public List<JMreActivityListResult.JActivityItem> getItems() {
    return items;
  }

  public void setItems(List<JMreActivityListResult.JActivityItem> items) {
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
    @Bind(R.id.img) ImageView img;
    @Bind(R.id.name) TextView name;
    @Bind(R.id.price_text) TextView priceText;
    @Bind(R.id.road) TextView road;
    @Bind(R.id.content) TextView content;
    @Bind(R.id.tese) TextView tese;
    @Bind(R.id.address) TextView address;

    public Context getContext() {
      return this.itemView.getContext();
    }

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
