package com.aishang.app.ui.hotel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JHotelListResult;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.HotelDetail.HotelDetailActivity;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.ViewUtil;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/1/30.
 */
public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {

  private int width = -1;
  private int height = -1;

  public Date checkInDate;
  public Date checkOutDate;

  List<JHotelListResult.Hotel> hotels;

  @Inject public HotelAdapter() {
    hotels = new ArrayList<JHotelListResult.Hotel>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel, parent, false);
    ViewHolder holder = new ViewHolder(view);

    initImgSize(holder);

    holder.imgInSale.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
    holder.imgInSale.setScaleType(ImageView.ScaleType.CENTER_CROP);

    return holder;
  }

  @Override public void onBindViewHolder(final ViewHolder holder, int position) {

    final JHotelListResult.Hotel hotel = hotels.get(position);
    Picasso.with(holder.getContext())
        .load(AiShangService.IMG_URL + hotel.getImageUrl())
        .error(R.mipmap.banner)
        .placeholder(R.mipmap.banner)
        .into(holder.imgInSale);

    holder.name.setText(hotel.getName());
    holder.tese.setText(TextUtils.isEmpty(hotel.getTags()) ? "暂无描述" : hotel.getTags());

    String priceText = hotel.getPriceText();
    if (priceText.contains(".")) {
      priceText = priceText.substring(0, priceText.indexOf("."));
    }
    holder.priceText.setText("￥" + priceText);
    holder.type.setText(hotel.getRoomDepict());
    holder.houseType.setText(hotel.getPropertyCat());
    holder.address.setText(hotel.getAddress());
    holder.total.setText("共有" + hotel.getMaxRooms() + "套房源");
    //holder.address.setText();

    //holder.imgInSale.getViewTreeObserver()
    //    .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
    //      @SuppressLint("NewApi") @Override public void onGlobalLayout() {
    //        int height = holder.imgInSale.getMeasuredHeight();
    //
    //        if (height != 0) {
    //          holder.imgInSale.setLayoutParams(
    //              new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
    //                  LinearLayout.LayoutParams.WRAP_CONTENT));
    //
    //          if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
    //            holder.imgInSale.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    //          } else {
    //            holder.imgInSale.getViewTreeObserver().removeGlobalOnLayoutListener(this);
    //          }
    //        }
    //      }
    //    });

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        intentToDetail(holder.getContext(), hotel.getHotelID(), hotel.getName());
      }
    });
  }

  @Override public int getItemCount() {
    return hotels.size();
  }

  public void setCheckInDate(Date checkInDate) {
    this.checkInDate = checkInDate;
  }

  public void setCheckOutDate(Date checkOutDate) {
    this.checkOutDate = checkOutDate;
  }

  public List<JHotelListResult.Hotel> getHotels() {
    return hotels;
  }

  public void setHotels(List<JHotelListResult.Hotel> hotels) {
    this.hotels = hotels;
  }

  private void intentToDetail(Context ctx, int hotelID, String hotelName) {
    Intent intent =
        HotelDetailActivity.getStartIntent(ctx, hotelID, hotelName, checkInDate.getTime(),
            checkOutDate.getTime());
    ctx.startActivity(intent);
  }

  private void initImgSize(ViewHolder holder) {
    Activity activity = (Activity) holder.getContext();

    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    int mScreenWidth = localDisplayMetrics.widthPixels;
    int pacing = ViewUtil.dpToPx(8);
    width = (mScreenWidth - 2 * pacing);
    height = width * 9 / 16;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.img_in_sale) ImageView imgInSale;
    @Bind(R.id.name) TextView name;
    @Bind(R.id.price_text) TextView priceText;
    @Bind(R.id.type) TextView type;
    @Bind(R.id.house_type) TextView houseType;
    @Bind(R.id.address) TextView address;
    @Bind(R.id.tese) TextView tese;
    @Bind(R.id.total) TextView total;

    public Context getContext() {
      return this.itemView.getContext();
    }

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
