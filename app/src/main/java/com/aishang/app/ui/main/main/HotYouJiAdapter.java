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
public class HotYouJiAdapter extends BaseAdapter {

    private final Context activity;

    private List<Integer> hotYouJis;

    @Inject
    public HotYouJiAdapter(Context activity) {
        this.activity = activity;
        hotYouJis = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return hotYouJis.size();
    }

    @Override
    public Object getItem(int position) {
        return hotYouJis.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setHotYouJis(List<Integer> hotYouJis) {
        this.hotYouJis = hotYouJis;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HotYouJiHolder holder = null;
        if(convertView == null)
        {
            convertView = View.inflate(activity, R.layout.item_youji,null);
            holder = new HotYouJiHolder(convertView);
        }else {
            holder = (HotYouJiHolder)convertView.getTag();
        }

        holder.imgYouJi.setImageResource(R.mipmap.banner);

        convertView.setTag(holder);

        return convertView;
    }

    class HotYouJiHolder {

        @Bind(R.id.img_hot_youji)
        ImageView imgYouJi;

        public HotYouJiHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
