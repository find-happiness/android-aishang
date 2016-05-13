package com.aishang.app.ui.TravelFavorites;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JNewsListResult;
import com.aishang.app.data.model.MyTravel;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.TravelDetail.TravelDetailActivity;
import com.aishang.app.util.ViewUtil;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/2/16.
 */
public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.ViewHolder> {

  List<MyTravel> items;
  int imgWidth;
  int imgHeight;

  @Inject public TravelAdapter() {
    items = new ArrayList<MyTravel>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_travel, parent, false);

    ViewHolder holder = new ViewHolder(view);
    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(imgWidth, imgHeight);
    //layoutParams.setMargins(0, 0, ViewUtil.dpToPx(8), 0);
    layoutParams.setMargins(0, 0, 0, 0);
    holder.image.setLayoutParams(layoutParams);
    return holder;
  }

  @Override public void onBindViewHolder(final ViewHolder holder, int position) {

    final MyTravel news = items.get(position);

    Picasso.with(holder.getContext())
        .load(AiShangService.IMG_URL + news.getImageUrl())
        .error(R.mipmap.banner)
        .placeholder(R.mipmap.banner)
        .into(holder.image);

    holder.shortDesc.setText(news.getTitle() + "");
    holder.dianzhang.setText(news.getHits() + "");
    holder.pinglun.setText(news.getEnshrinedCount() + "");
    holder.reward.setText("奖金" + "");
    holder.userName.setText(
        "作者:" + (TextUtils.isEmpty(news.getUserName()) ? "" : news.getUserName()));
    holder.localOrDate.setText(news.getDate());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        intentToDetail(holder.getContext(), news);
      }
    });
  }

  @Override public int getItemCount() {
    return items.size();
  }

  public List<MyTravel> getItems() {
    return items;
  }

  public void setImgSize(int width, int height) {
    this.imgWidth = width;
    this.imgHeight = height;
  }

  public void setItems(List<MyTravel> items) {
    this.items = items;
  }

  private void intentToDetail(Context ctx, MyTravel item) {
    Intent intent = TravelDetailActivity.getStartIntent(ctx, item.getNewsID(), item.getStaticUrl(),
        item.getImageUrl());

    ctx.startActivity(intent);
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.image) ImageView image;
    @Bind(R.id.short_desc) TextView shortDesc;
    @Bind(R.id.user_name) TextView userName;
    @Bind(R.id.dianzhang) TextView dianzhang;
    @Bind(R.id.pinglun) TextView pinglun;
    @Bind(R.id.reward) TextView reward;
    @Bind(R.id.local_or_date) TextView localOrDate;

    public ViewHolder(View itemView) {
      super(itemView);

      ButterKnife.bind(this, itemView);
    }

    public Context getContext() {
      return this.itemView.getContext();
    }
  }
}