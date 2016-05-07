package com.aishang.app.ui.main.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JNewsListResult;
import com.aishang.app.data.model.News;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.injection.ActivityContext;
import com.aishang.app.ui.TravelDetail.TravelDetailActivity;
import com.aishang.app.util.ViewUtil;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/1/17.
 */
@ActivityContext public class HotYouJiAdapter extends BaseAdapter {

  private final Activity activity;

  private List<News> hotYouJis;

  int[] imgSize = new int[2];

  @Inject public HotYouJiAdapter(Activity activity) {
    this.activity = activity;
    hotYouJis = new ArrayList<>();

    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    int mScreenWidth = localDisplayMetrics.widthPixels;
    int pacing = ViewUtil.dpToPx(8);
    int width = (mScreenWidth - 2 * pacing);

    imgSize[0] = width;
    imgSize[1] = width * 400 / 640;
  }

  @Override public int getCount() {
    return hotYouJis.size();
  }

  @Override public Object getItem(int position) {
    return hotYouJis.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }

  public void setHotYouJis(List<News> hotYouJis) {
    this.hotYouJis = hotYouJis;
  }

  public void clearData() {
    hotYouJis.clear();
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder = null;
    if (convertView == null) {
      convertView = View.inflate(activity, R.layout.item_travel, null);
      holder = new ViewHolder(convertView);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }
    final News news = hotYouJis.get(position);

    final JNewsListResult.NewsListEntity item = news.getNews();

    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(imgSize[0], imgSize[1]);
    holder.image.setLayoutParams(layoutParams);
    Picasso.with(activity)
        .load(AiShangService.IMG_URL + item.getImageUrl())
        .error(R.mipmap.banner)
        .placeholder(R.mipmap.banner)
        .into(holder.image);

    holder.shortDesc.setText(item.getTitle() + "");
    holder.dianzhang.setText(item.getHits() + "");
    holder.pinglun.setText(news.getEnshrinedCount() + "");
    holder.reward.setText(item.getNewsID() + "");
    holder.userName.setText("作者:" + news.getUserName());
    holder.localOrDate.setText(news.getZoneName());
    convertView.setTag(holder);

    convertView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent =
            TravelDetailActivity.getStartIntent(activity, item.getNewsID(), item.getStaticUrl(),
                item.getImageUrl());
        activity.startActivity(intent);
      }
    });
    return convertView;
  }

  /**
   * This class contains all butterknife-injected Views & Layouts from layout file 'item_youji.xml'
   * for easy to all layout elements.
   *
   * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers
   *         (http://github.com/avast)
   */
  static class ViewHolder {
    @Bind(R.id.image) ImageView image;
    @Bind(R.id.short_desc) TextView shortDesc;
    @Bind(R.id.user_name) TextView userName;
    @Bind(R.id.dianzhang) TextView dianzhang;
    @Bind(R.id.pinglun) TextView pinglun;
    @Bind(R.id.reward) TextView reward;
    @Bind(R.id.local_or_date) TextView localOrDate;

    ViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }
}
