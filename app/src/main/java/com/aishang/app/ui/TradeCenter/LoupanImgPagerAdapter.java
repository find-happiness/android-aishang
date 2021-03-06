package com.aishang.app.ui.TradeCenter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import com.aishang.app.R;
import com.aishang.app.data.model.AdapterImgModel;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.HotelDetail.HotelDetailActivity;
import com.aishang.app.util.BusProvider;
import com.squareup.picasso.Picasso;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by song on 2016/3/20.
 */
class LoupanImgPagerAdapter extends PagerAdapter {

  private static final String TAG = "LoupanImgPagerAdapter";

  Context ctx;
  private String[] sDrawables;

  public LoupanImgPagerAdapter(Context ctx, String[] sDrawables) {
    this.ctx = ctx;
    this.sDrawables = sDrawables;
  }

  @Override public int getCount() {
    return sDrawables.length;
  }

  @Override public View instantiateItem(ViewGroup container, final int position) {
    PhotoView photoView = new PhotoView(container.getContext());
    Picasso.with(ctx)
        .load(AiShangService.IMG_URL + sDrawables[position])
        .error(R.mipmap.banner)
        .placeholder(R.mipmap.banner)
        .into(photoView);

    // Now just add PhotoView to ViewPager and return it
    container.addView(photoView, ViewPager.LayoutParams.MATCH_PARENT,
        ViewPager.LayoutParams.MATCH_PARENT);

    photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
      @Override public void onViewTap(View view, float x, float y) {
        BusProvider.getInstance().post(AdapterImgModel.create(x, y, position));
      }
    });

    return photoView;
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
  }

  @Override public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }
}