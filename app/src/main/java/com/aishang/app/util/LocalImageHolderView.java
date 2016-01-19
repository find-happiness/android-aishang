package com.aishang.app.util;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by song on 2016/1/19.
 */
public class LocalImageHolderView implements Holder<Integer> {
  private ImageView imageView;
  public View createView(Context context) {
    imageView = new ImageView(context);
    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    return imageView;
  }
  public void UpdateUI(Context context, final int position, Integer data) {
    imageView.setImageResource(data);
  }
}