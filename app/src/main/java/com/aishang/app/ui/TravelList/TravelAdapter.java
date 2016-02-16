package com.aishang.app.ui.TravelList;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JNewsListResult;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.TravelDetail.TravelDetailActivity;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/2/16.
 */
public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.ViewHolder> {

  List<JNewsListResult.JNewsListItem> itemList;

  public List<JNewsListResult.JNewsListItem> getItemList() {
    return itemList;
  }

  public void setItemList(List<JNewsListResult.JNewsListItem> itemList) {
    this.itemList = itemList;
  }

  @Inject public TravelAdapter() {
    itemList = new ArrayList<JNewsListResult.JNewsListItem>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_youji, parent, false);

    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(final ViewHolder holder, int position) {
    final JNewsListResult.JNewsListItem item = itemList.get(position);

    Picasso.with(holder.getContext())
        .load(AiShangService.IMG_URL + item.getImageUrl())
        .error(R.mipmap.banner)
        .placeholder(R.mipmap.banner)
        .into(holder.imgHotYouji);

    holder.shuoshuo.setText(item.getShortDesc() + "");
    holder.dianzhang.setText(item.getHits() + "");
    holder.pinglun.setText(item.getSupports() + "");
    holder.reward.setText(item.getNewsID() + "");

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent = TravelDetailActivity.getStartIntent(holder.getContext(), item.getNewsID(),
            item.getStaticUrl());
        holder.getContext().startActivity(intent);
      }
    });
  }

  @Override public int getItemCount() {
    return itemList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.img_hot_youji) ImageView imgHotYouji;
    @Bind(R.id.head) CircleImageView head;
    @Bind(R.id.name) TextView name;
    @Bind(R.id.shuoshuo) TextView shuoshuo;
    @Bind(R.id.reward) TextView reward;
    @Bind(R.id.pinglun) TextView pinglun;
    @Bind(R.id.dianzhang) TextView dianzhang;

    public Context getContext() {
      return itemView.getContext();
    }

    public ViewHolder(View itemView) {
      super(itemView);

      ButterKnife.bind(this, itemView);
    }
  }
}