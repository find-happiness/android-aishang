package com.aishang.app.ui.TravelList;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JNewsListResult;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.TravelDetail.TravelDetailActivity;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.ViewUtil;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/1/30.
 */
public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.ViewHolder> {

  List<JNewsListResult.JNewsListItem> items;
  int imgWidth;
  int imgHeight;

  @Inject public TravelAdapter() {
    items = new ArrayList<JNewsListResult.JNewsListItem>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_travel, parent, false);

    ViewHolder holder = new ViewHolder(view);
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(imgWidth, imgHeight);
    //layoutParams.setMargins(0, 0, ViewUtil.dpToPx(8), 0);
    layoutParams.setMargins(0, 0, ViewUtil.dpToPx(8), 0);
    holder.img1.setLayoutParams(layoutParams);
    holder.img2.setLayoutParams(layoutParams);
    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(imgWidth, imgHeight);
    layoutParams2.setMargins(0, 0, 0, 0);
    holder.img3.setLayoutParams(layoutParams2);
    return holder;
  }

  @Override public void onBindViewHolder(final ViewHolder holder, int position) {

    final JNewsListResult.JNewsListItem item = items.get(position);
    Picasso.with(holder.getContext())
        .load(AiShangService.IMG_URL + item.getImageUrl())
        .error(R.mipmap.banner)
        .placeholder(R.mipmap.banner)
        .into(holder.img1);

    Picasso.with(holder.getContext())
        .load(AiShangService.IMG_URL + item.getImageUrl())
        .error(R.mipmap.banner)
        .placeholder(R.mipmap.banner)
        .into(holder.img2);

    Picasso.with(holder.getContext())
        .load(AiShangService.IMG_URL + item.getImageUrl())
        .error(R.mipmap.banner)
        .placeholder(R.mipmap.banner)
        .into(holder.img3);

    holder.shortDesc.setText(item.getShortDesc() + "");
    holder.dianzhang.setText(item.getHits() + "");
    holder.pinglun.setText(item.getSupports() + "");
    holder.reward.setText(item.getNewsID() + "");
    holder.date.setText(item.getDate() + "");

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        intentToDetail(holder.getContext(), item);
      }
    });
  }

  @Override public int getItemCount() {
    return items.size();
  }

  public List<JNewsListResult.JNewsListItem> getItems() {
    return items;
  }

  public void setImgSize(int width, int height) {
    this.imgWidth = width;
    this.imgHeight = height;
  }

  public void setItems(List<JNewsListResult.JNewsListItem> items) {
    this.items = items;
  }

  private void intentToDetail(Context ctx, JNewsListResult.JNewsListItem item) {
    Intent intent = TravelDetailActivity.getStartIntent(ctx, item.getNewsID(), item.getStaticUrl());

    ctx.startActivity(intent);
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.head) CircleImageView head;
    @Bind(R.id.user_name) TextView userName;
    @Bind(R.id.date) TextView date;
    @Bind(R.id.short_desc) TextView shortDesc;
    @Bind(R.id.img1) ImageView img1;
    @Bind(R.id.img2) ImageView img2;
    @Bind(R.id.img3) ImageView img3;
    @Bind(R.id.dianzhang) TextView dianzhang;
    @Bind(R.id.pinglun) TextView pinglun;
    @Bind(R.id.reward) TextView reward;

    public Context getContext() {
      return this.itemView.getContext();
    }

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
