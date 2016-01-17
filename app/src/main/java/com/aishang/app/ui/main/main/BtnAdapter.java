package com.aishang.app.ui.main.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.aishang.app.R;
import com.aishang.app.injection.ActivityContext;
import com.aishang.app.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by song on 2016/1/16.
 */
public class BtnAdapter  extends BaseAdapter {

    private List<Integer> mBtns;

    private LayoutInflater inflater;

    private Context context;
    @Inject
    public BtnAdapter() {
        mBtns = new ArrayList<>();
    }

    public void setContent(Context context)
    {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setBtns(List<Integer> btns) {
        mBtns = btns;
    }

    @Override
    public int getCount() {
        return mBtns.size();
    }

    @Override
    public Object getItem(int position) {
        return mBtns.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView img = (ImageView)inflater.inflate(R.layout.tab_main,null);
        img.setImageResource(R.mipmap.dujiawu);
        return img;
    }
}