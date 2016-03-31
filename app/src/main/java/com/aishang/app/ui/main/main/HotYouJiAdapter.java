package com.aishang.app.ui.main.main;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/1/17.
 */
@ActivityContext public class HotYouJiAdapter extends BaseAdapter {

  private final Context activity;

  private List<News> hotYouJis;

  @Inject public HotYouJiAdapter(Context activity) {
    this.activity = activity;
    hotYouJis = new ArrayList<>();
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
      convertView = View.inflate(activity, R.layout.item_youji, null);
      holder = new ViewHolder(convertView);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }
    final News news = hotYouJis.get(position);

    final JNewsListResult.NewsListEntity item = news.getNews();

    Picasso.with(activity)
        .load(AiShangService.IMG_URL + item.getImageUrl())
        .error(R.mipmap.banner)
        .placeholder(R.mipmap.banner)
        .into(holder.imgHotYouji);

    Picasso.with(activity)
        .load(AiShangService.IMG_URL + news.getUserImageUrl())
        .error(R.mipmap.img_head_default)
        .placeholder(R.mipmap.img_head_default)
        .into(holder.head);

    holder.shuoshuo.setText(item.getTitle() + "");
    holder.dianzhang.setText(item.getHits() + "");
    holder.pinglun.setText(news.getEnshrinedCount() + "");
    holder.reward.setText(item.getNewsID() + "");
    holder.name.setText(item.getSource() + "");
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
    @Bind(R.id.img_hot_youji) ImageView imgHotYouji;
    @Bind(R.id.head) CircleImageView head;
    @Bind(R.id.name) TextView name;
    @Bind(R.id.shuoshuo) TextView shuoshuo;
    @Bind(R.id.reward) TextView reward;
    @Bind(R.id.pinglun) TextView pinglun;
    @Bind(R.id.dianzhang) TextView dianzhang;

    ViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }
}
