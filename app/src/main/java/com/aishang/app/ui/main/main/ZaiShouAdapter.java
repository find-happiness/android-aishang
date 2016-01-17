package com.aishang.app.ui.main.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

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
public class ZaiShouAdapter extends BaseAdapter {

    private final Context activity;

    private List<Integer> zaiShous;

    @Inject
    public ZaiShouAdapter( Context activity) {
        this.activity = activity;
        zaiShous = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return zaiShous.size();
    }

    @Override
    public Object getItem(int position) {
        return zaiShous.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setZaiShous(List<Integer> zaiShous)
    {
        this.zaiShous = zaiShous;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HotYouJiHolder holder = null;
        if(convertView == null)
        {
            convertView = View.inflate(activity, R.layout.item_in_sale,null);
            holder = new HotYouJiHolder(convertView);
        }else {
            holder = (HotYouJiHolder)convertView.getTag();
        }

        holder.imgInSale.setImageResource(R.mipmap.banner);

        convertView.setTag(holder);

        return convertView;
    }

    class HotYouJiHolder {

        @Bind(R.id.img_in_sale)
        ImageView imgInSale;

        public HotYouJiHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
