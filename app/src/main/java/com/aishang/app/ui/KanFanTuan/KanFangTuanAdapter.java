package com.aishang.app.ui.KanFanTuan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JMreActivityListResult;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.KanFangTuanDetail.KanFangTuanDetailActivity;
import com.aishang.app.util.ViewUtil;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/2/15.
 */
public class KanFangTuanAdapter extends RecyclerView.Adapter<KanFangTuanAdapter.ViewHolder> {

  public Date checkInDate;
  public Date checkOutDate;

  private int width = -1;
  private int height = -1;

  List<JMreActivityListResult.JActivityItem> items;

  @Inject public KanFangTuanAdapter() {
    items = new ArrayList<JMreActivityListResult.JActivityItem>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_kan_fang_tuan, parent, false);

    ViewHolder holder = new ViewHolder(view);
    initImgSize(holder);
    holder.img.setLayoutParams(new FrameLayout.LayoutParams(width, height));
    holder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);

    return holder;
  }

  @Override public void onBindViewHolder(final ViewHolder holder, int position) {

    final JMreActivityListResult.JActivityItem item = items.get(position);

    Picasso.with(holder.getContext())
        .load(AiShangService.IMG_URL + item.getImageUrl())
        .error(R.mipmap.banner)
        .placeholder(R.mipmap.banner)
        .into(holder.img);

    holder.date.setText("活动时间:" + formatDate(item.getStartTime().split(" ")[0]) + "-" + formatDate(
        item.getEndTime().split(" ")[0]));
    //holder.enrollDate.setText(
    //    formatDate(item.getEnrollStartTime().split(" ")[0]) + "-" + formatDate(
    //        item.getEnrollEndTime().split(" ")[0]));
    holder.name.setText(item.getPosition());
    //holder.address.setText(item.getPosition());
    holder.content.setText(item.getTitle());
    holder.priceText.setText(item.getFee() + "");
    holder.scoreTitle.setText(
        item.getScore1Title() + "\n" + item.getScore2Title() + "\n" + item.getScore3Title());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        //intentToDetail(holder.getContext(), hotel.getHotelID(), hotel.getName());
        intentToDetail(holder.getContext(), item);
      }
    });
  }

  @Override public int getItemCount() {
    return items.size();
  }

  private void initImgSize(ViewHolder holder) {
    Activity activity = (Activity) holder.getContext();

    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    int mScreenWidth = localDisplayMetrics.widthPixels;
    int pacing = ViewUtil.dpToPx(8);
    width = (mScreenWidth - 2 * pacing);
    height = width * 380 / 610;
  }

  public List<JMreActivityListResult.JActivityItem> getItems() {
    return items;
  }

  public void setItems(List<JMreActivityListResult.JActivityItem> items) {
    this.items = items;
  }

  private void intentToDetail(Context ctx, JMreActivityListResult.JActivityItem item) {
    Intent intent = KanFangTuanDetailActivity.getStartIntent(ctx, item);
    ctx.startActivity(intent);
  }

  private String formatDate(String date) {
    if (TextUtils.isEmpty(date)) return "";
    String str[] = date.split("-");
    if (str.length != 3) return "";
    return str[0] + "年" + str[1] + "月" + str[2] + "日";
  }

  /**
   * This class contains all butterknife-injected Views & Layouts from layout file
   * 'item_my_order.xml'
   * for easy to all layout elements.
   *
   * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers
   *         (http://github.com/avast)
   */
  static class ViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.img) ImageView img;
    @Bind(R.id.name) TextView name;
    @Bind(R.id.content) TextView content;
    @Bind(R.id.date) TextView date;
    @Bind(R.id.price_text) TextView priceText;
    @Bind(R.id.scoreTitle) TextView scoreTitle;

    public Context getContext() {
      return this.itemView.getContext();
    }

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
