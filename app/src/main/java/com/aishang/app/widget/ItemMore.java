package com.aishang.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aishang.app.R;

/**
 * Created by song on 2016/1/17.
 */
public class ItemMore extends LinearLayout {
    private ImageView img;
    private TextView tvContent;

    public ItemMore(Context context) {
        this(context, null);
    }

    public ItemMore(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_more, null);

        img = (ImageView) view.findViewById(R.id.icon);
        tvContent = (TextView) view.findViewById(R.id.text);

        if (attrs != null) {
            //初始化状态View
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ImgWithText);

            int zoomViewResId = a.getResourceId(R.styleable.ImgWithText_img, 0);
            if (zoomViewResId > 0) {
                img.setImageResource(zoomViewResId);
            }

            int headerViewResId = a.getResourceId(R.styleable.ImgWithText_text, 0);
            if (headerViewResId > 0) {
                tvContent.setText(headerViewResId);
            }

            a.recycle();
        }

        this.addView(view);
    }


    public void setImgDrawable(int drawableID) {
        this.img.setBackgroundResource(drawableID);
    }

    public void setText(int textID) {
        this.tvContent.setText(textID);
    }
}