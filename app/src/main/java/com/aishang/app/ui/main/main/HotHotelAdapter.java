package com.aishang.app.ui.main.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aishang.app.R;
import com.aishang.app.data.model.JHotelListResult;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.injection.ActivityContext;
import com.aishang.app.injection.PerActivity;
import com.aishang.app.ui.HotelDetail.HotelDetailActivity;
import com.aishang.app.ui.main.MainActivity;

import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.Constants;
import com.aishang.app.util.ViewUtil;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by song on 2016/1/17.
 */
@ActivityContext public class HotHotelAdapter extends BaseAdapter {

  private final Context activity;

  private List<JHotelListResult.Hotel> hotHotels;

  int[] imgSize = new int[2];

  @Inject public HotHotelAdapter(Activity activity) {
    this.activity = activity;
    hotHotels = new ArrayList<>();

    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    int mScreenWidth = localDisplayMetrics.widthPixels;
    int pacing = ViewUtil.dpToPx(8);
    int width = (mScreenWidth - 2 * pacing - ViewUtil.dpToPx(4)) / 2;

    imgSize[0] = width;
    imgSize[1] = width * 9 / 16;
  }

  public void setHotels(List<JHotelListResult.Hotel> hotels) {
    hotHotels = hotels;
  }

  public void clearData() {
    hotHotels.clear();
  }

  @Override public int getCount() {
    return hotHotels.size();
  }

  @Override public Object getItem(int position) {
    return hotHotels.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {

    final HotHotelHolder holder;
    if (convertView == null) {
      convertView = View.inflate(activity, R.layout.item_hot_hotel, null);
      holder = new HotHotelHolder(convertView);
    } else {
      holder = (HotHotelHolder) convertView.getTag();
    }
    final JHotelListResult.Hotel hotel = hotHotels.get(position);
    Picasso.with(activity)
        .load(AiShangService.IMG_URL + hotel.getImageUrl())
        .error(R.mipmap.banner)
        .placeholder(R.mipmap.banner)
        .into(holder.imgHotel);
    holder.name.setText(hotel.getName());
    holder.tese.setText(hotel.getPromotionText());
    holder.price.setText("￥" + hotel.getPriceText());

    holder.imgHotel.setLayoutParams(new RelativeLayout.LayoutParams(imgSize[0], imgSize[1]));
    convertView.setTag(holder);

    convertView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent =
            HotelDetailActivity.getStartIntent(activity, hotel.getHotelID(), hotel.getName(),
                System.currentTimeMillis() + 86400000L, System.currentTimeMillis() + 2 * 86400000L);
        activity.startActivity(intent);
      }
    });

    return convertView;
  }

  class HotHotelHolder {

    @Bind(R.id.img_hot_hotel) ImageView imgHotel;

    @Bind(R.id.name) TextView name;
    @Bind(R.id.tese) TextView tese;
    @Bind(R.id.book) TextView book;
    @Bind(R.id.price_text) TextView price;

    public HotHotelHolder(View itemView) {
      ButterKnife.bind(this, itemView);
    }
  }
}
