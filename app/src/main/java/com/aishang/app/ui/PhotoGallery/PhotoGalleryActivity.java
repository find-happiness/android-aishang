package com.aishang.app.ui.PhotoGallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.widget.HackyViewPager;
import com.squareup.picasso.Picasso;
import uk.co.senab.photoview.PhotoView;

public class PhotoGalleryActivity extends AppCompatActivity {

  private static final String PHOTO = "Photo";
  private static final String START_INDEX = "start_index";
  @Bind(R.id.view_pager) HackyViewPager viewPager;
  @Bind(R.id.toolbar) Toolbar toolbar;

  public static Intent getIntent(Activity activity, String[] photos, int startIndex) {
    Intent intent = new Intent(activity, PhotoGalleryActivity.class);
    intent.putExtra(PHOTO, photos);
    intent.putExtra(START_INDEX, startIndex);
    return intent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_photo_gallery);
    ButterKnife.bind(this);
    initToolbar();
    String[] photos = this.getIntent().getStringArrayExtra(PHOTO);

    viewPager.setAdapter(new SamplePagerAdapter(this, photos));

    viewPager.setCurrentItem(this.getIntent().getIntExtra(START_INDEX, 0));
  }

  private void initToolbar() {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    toolbar.setNavigationIcon(R.mipmap.iconfont_livesvg);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onBackPressed();
      }
    });
  }

  static class SamplePagerAdapter extends PagerAdapter {

    Context ctx;
    private String[] sDrawables;

    public SamplePagerAdapter(Context ctx, String[] sDrawables) {
      this.ctx = ctx;
      this.sDrawables = sDrawables;
    }

    @Override public int getCount() {
      return sDrawables.length;
    }

    @Override public View instantiateItem(ViewGroup container, int position) {
      PhotoView photoView = new PhotoView(container.getContext());
      Picasso.with(ctx)
          .load(AiShangService.IMG_URL + sDrawables[position])
          .error(R.mipmap.banner)
          .placeholder(R.mipmap.banner)
          .into(photoView);

      // Now just add PhotoView to ViewPager and return it
      container.addView(photoView, ViewPager.LayoutParams.MATCH_PARENT,
          ViewPager.LayoutParams.MATCH_PARENT);

      return photoView;
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView((View) object);
    }

    @Override public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }
  }
}
