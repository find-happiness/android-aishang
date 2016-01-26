package com.aishang.app.ui.main.main;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JLoupanProductListResult;
import com.aishang.app.injection.ActivityContext;
import com.aishang.app.ui.insaleDetail.InSaleDetailActivity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/1/17.
 */
@ActivityContext public class ZaiShouAdapter extends BaseAdapter {

  private final Context activity;

  private List<JLoupanProductListResult.Product> products;

  private List<JLoupanProductListResult.Loupan> loupans;

  @Inject public ZaiShouAdapter(Context activity) {
    this.activity = activity;
    loupans = new ArrayList<>();
    products = new ArrayList<>();
  }

  @Override public int getCount() {
    return loupans.size();
  }

  @Override public Object getItem(int position) {
    return loupans.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }

  public void setLoupans(List<JLoupanProductListResult.Loupan> loupans) {
    this.loupans = loupans;
  }

  public void setProducts(List<JLoupanProductListResult.Product> products) {
    this.products = products;
  }

  public List<JLoupanProductListResult.Product> getProducts() {
    return products;
  }

  public List<JLoupanProductListResult.Loupan> getLoupans() {
    return loupans;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    HotYouJiHolder holder = null;
    if (convertView == null) {
      convertView = View.inflate(activity, R.layout.item_in_sale, null);
      holder = new HotYouJiHolder(convertView);
    } else {
      holder = (HotYouJiHolder) convertView.getTag();
    }

    JLoupanProductListResult.Loupan loupan = loupans.get(position);
    int loupanid = loupan.getLoupanID();
    JLoupanProductListResult.Product product = null;
    for (JLoupanProductListResult.Product p : products) {
      if (p.getLoupanID() == loupanid) {
        product = p;
        break;
      }
    }

    holder.name.setText(product.getTitle());
    convertView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent = new Intent(activity, InSaleDetailActivity.class);
        activity.startActivity(intent);
      }
    });

    Picasso.with(activity)
        .load(product.getImageUrl().replace("_237", "_800"))
        .error(R.mipmap.banner)
        .placeholder(R.mipmap.banner)
        .into(holder.imgInSale);

    holder.priceText.setText(product.getPriceText());
    holder.tese.setText(product.getPromotion());
    holder.address.setText(loupan.getAddress());

    convertView.setTag(holder);

    return convertView;
  }

  /**
   * This class contains all butterknife-injected Views & Layouts from layout file
   * 'item_in_sale.xml'
   * for easy to all layout elements.
   */
  static class HotYouJiHolder {

    @Bind(R.id.img_in_sale) ImageView imgInSale;
    @Bind(R.id.name) TextView name;
    @Bind(R.id.price_text) TextView priceText;
    @Bind(R.id.time) TextView time;
    @Bind(R.id.address) TextView address;
    @Bind(R.id.tese) TextView tese;
    @Bind(R.id.look) Button look;
    @Bind(R.id.youji) Button youji;
    @Bind(R.id.buy) Button buy;

    public HotYouJiHolder(View itemView) {
      ButterKnife.bind(this, itemView);
    }
  }
}
