package com.aishang.app.util;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.aishang.app.R;
import com.aishang.app.data.remote.AiShangService;
import com.bigkoo.convenientbanner.holder.Holder;
import com.squareup.picasso.Picasso;

/**
 * Created by song on 2016/2/19.
 */
public class NetImageHolderView implements Holder<String> {
  private ImageView imageView;

  public View createView(Context context) {
    imageView = new ImageView(context);
    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    return imageView;
  }

  @Override public void UpdateUI(Context context, int position, String url) {

    Picasso.with(context)
        .load(url)
        .error(R.mipmap.banner)
        .placeholder(R.mipmap.banner)
        .into(imageView);
  }
}