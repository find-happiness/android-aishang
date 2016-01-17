package com.aishang.app.ui.main.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aishang.app.R;
import com.aishang.app.injection.ActivityContext;
import com.aishang.app.injection.PerActivity;
import com.aishang.app.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by song on 2016/1/17.
 */
@ActivityContext
public class HotHotelAdapter extends BaseAdapter {

    private final Context activity;

    private List<Integer> hotHotels;

    @Inject
    public HotHotelAdapter(Context activity)
    {
        this.activity = activity;
        hotHotels = new ArrayList<>();
    }

    public void setHotels(List<Integer> hotels)
    {
        hotHotels = hotels;
    }

    @Override
    public int getCount() {
        return hotHotels.size();
    }

    @Override
    public Object getItem(int position) {
        return hotHotels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final HotHotelHolder holder;
        if(convertView == null)
        {
            convertView = View.inflate(activity, R.layout.item_hot_hotel,null);
            holder = new HotHotelHolder(convertView);
        }else {
            holder = (HotHotelHolder)convertView.getTag();
        }

        holder.imgHotel.setImageResource(R.mipmap.banner);
        holder.name.setText("name");
        holder.tese.setText("tese");
        holder.book.setText("book");
        holder.price.setText("price");

        holder.imgHotel.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressLint("NewApi")
            @Override
            public void onGlobalLayout() {
                int height =  holder.imgHotel.getMeasuredHeight();

                if (height != 0) {
                    holder.imgHotel
                            .setLayoutParams(new android.widget.RelativeLayout.LayoutParams(
                                    RelativeLayout.LayoutParams.MATCH_PARENT,
                                    RelativeLayout.LayoutParams.WRAP_CONTENT));

                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
                        holder.imgHotel.getViewTreeObserver()
                                .removeOnGlobalLayoutListener(this);

                    } else {
                        holder.imgHotel.getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
                    }
                }
            }
        });
        convertView.setTag(holder);

        return convertView;

    }

    class HotHotelHolder {

        @Bind(R.id.img_hot_hotel)
        ImageView imgHotel;

        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.tese)
        TextView tese;
        @Bind(R.id.book)
        Button book;
        @Bind(R.id.price_text)
        TextView price;

        public HotHotelHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
