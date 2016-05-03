package com.aishang.app.ui.about;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

public class KnowWeActivity extends AppCompatActivity {

  final int[] page = new int[] { R.mipmap.konw_we_1, R.mipmap.konw_we_2, R.mipmap.konw_we_3 ,R.mipmap.konw_we_4, R.mipmap.konw_we_5, R.mipmap.konw_we_6, R.mipmap.konw_we_7};
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.vertical_viewpager) RecyclerViewPager mRecyclerView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_know_we);
    ButterKnife.bind(this);
    initToolbar();
    initViewPager();
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

  private void initViewPager() {
    LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    mRecyclerView.setLayoutManager(layout);

    mRecyclerView.setAdapter(new MyAdapter());
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    public ViewHolder(View itemView) {
      super(itemView);
    }
  }

  class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      ImageView iv = new ImageView(KnowWeActivity.this);
      //iv.setImageResource(images[position]);
      iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
      return new ViewHolder(iv);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
      ((ImageView) holder.itemView).setImageResource(page[position]);
    }

    @Override public int getItemCount() {
      return page.length;
    }
  }
}
