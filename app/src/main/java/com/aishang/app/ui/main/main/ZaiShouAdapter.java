package com.aishang.app.ui.main.main;

import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JLoupanProductListResult;
import com.aishang.app.data.model.LoupanProduct;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.injection.ActivityContext;
import com.aishang.app.ui.insaleDetail.InSaleDetailActivity;
import com.aishang.app.util.ViewUtil;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/1/17.
 */
@ActivityContext public class ZaiShouAdapter extends BaseAdapter {

  private final Activity activity;

  //private List<JLoupanProductListResult.Product> products;
  //
  //private List<JLoupanProductListResult.Loupan> loupans;
  int[] imgSize = new int[2];

  private List<LoupanProduct> loupanProducts;

  @Inject public ZaiShouAdapter(Activity activity) {
    this.activity = activity;
    loupanProducts = new ArrayList<>();
    //products = new ArrayList<>();

    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    int mScreenWidth = localDisplayMetrics.widthPixels;
    int pacing = ViewUtil.dpToPx(8);
    int width = (mScreenWidth - 2 * pacing);

    imgSize[0] = width;
    imgSize[1] = width * 400 / 640;
  }

  @Override public int getCount() {
    return loupanProducts.size();
  }

  @Override public Object getItem(int position) {
    return loupanProducts.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }

  //public void setLoupans(List<JLoupanProductListResult.Loupan> loupans) {
  //  this.loupans = loupans;
  //}
  //
  //public void setProducts(List<JLoupanProductListResult.Product> products) {
  //  this.products = products;
  //}

  public void setLoupanProducts(List<LoupanProduct> loupanProducts) {
    this.loupanProducts = loupanProducts;
  }

  public void clearData() {
    loupanProducts.clear();
  }

  public List<LoupanProduct> getLoupanProducts() {
    return loupanProducts;
  }

  //public List<JLoupanProductListResult.Loupan> getLoupans() {
  //  return loupans;
  //}

  @Override public View getView(int position, View convertView, ViewGroup parent) {

    HotYouJiHolder holder = null;
    if (convertView == null) {
      convertView = View.inflate(activity, R.layout.item_in_sale, null);
      holder = new HotYouJiHolder(convertView);
    } else {
      holder = (HotYouJiHolder) convertView.getTag();
    }

    final JLoupanProductListResult.Loupan loupan = loupanProducts.get(position).getLoupan();
    final int loupanid = loupan.getLoupanID();
    final JLoupanProductListResult.Product product = loupanProducts.get(position).getProduct();

    holder.name.setText(loupan.getName() + "|" + product.getTitle());
    holder.imgInSale.setLayoutParams(new FrameLayout.LayoutParams(imgSize[0], imgSize[1]));
    Picasso.with(activity)
        .load(AiShangService.IMG_URL + product.getImageUrl())
        .error(R.mipmap.banner)
        .placeholder(R.mipmap.banner)
        .into(holder.imgInSale);

    if (product.getPrice() <= 0) {
      holder.priceText.setText("暂无价格");
    } else {
      holder.priceText.setText("￥" + product.getPrice() / 10000 + "万元");
    }
    //holder.priceText.setText("￥" + product.getPrice() / 10000 + "万元");
    holder.address.setText(loupan.getAddress());

    convertView.setTag(holder);

    convertView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent = InSaleDetailActivity.getStartIntent(activity, product.getLoupanProductID(),
            loupan.getName());
        activity.startActivity(intent);
      }
    });

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
    @Bind(R.id.address) TextView address;

    public HotYouJiHolder(View itemView) {
      ButterKnife.bind(this, itemView);
    }
  }
}
