package com.aishang.app.ui.TravelDetail;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JCriticismListResult;
import com.aishang.app.data.remote.AiShangService;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;

/**
 * Created by song on 2016/4/16.
 */
public class CriticismAdapter extends BaseAdapter {

  private static final String TAG = "CriticismAdapter";
  private Activity activity;

  private List<JCriticismListResult.CriticismListEntity> CriticismList;

  public CriticismAdapter(Activity activity,
      List<JCriticismListResult.CriticismListEntity> criticismList) {
    this.activity = activity;
    CriticismList = criticismList;
  }

  public List<JCriticismListResult.CriticismListEntity> getCriticismList() {
    return CriticismList;
  }

  @Override public int getCount() {
    return CriticismList.size();
  }

  @Override public JCriticismListResult.CriticismListEntity getItem(int position) {
    return CriticismList.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {

    ViewHolder holder = null;

    JCriticismListResult.CriticismListEntity item = CriticismList.get(position);

    //    if (convertView == null) {
    convertView = View.inflate(activity, R.layout.item_criticism, null);
    holder = new ViewHolder(convertView);
    convertView.setTag(holder);
    //} else {
    //  holder = (ViewHolder) convertView.getTag();
    //}

    holder.content.setText(item.getContent());

    holder.createTime.setText(item.getCreateDate());

    holder.name.setText(item.getNickname());

    if(TextUtils.isEmpty(item.getNickname())){
      holder.name.setText("匿名用户");
    }

    Picasso.with(activity)
        .load(AiShangService.IMG_URL + item.getImageUrl())
        .error(R.mipmap.ic_img_user_default)
        .placeholder(R.mipmap.ic_img_user_default)
        .into(holder.headView);

    return convertView;
  }

  static class ViewHolder {
    @Bind(R.id.head_view) CircleImageView headView;
    @Bind(R.id.name) TextView name;
    @Bind(R.id.create_time) TextView createTime;
    @Bind(R.id.content) TextView content;

    ViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }
}
