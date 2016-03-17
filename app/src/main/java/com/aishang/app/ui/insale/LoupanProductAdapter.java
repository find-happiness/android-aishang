package com.aishang.app.ui.insale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aishang.app.R;
import com.aishang.app.data.model.JLoupanProductListResult;
import com.aishang.app.data.model.LoupanProduct;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.insaleDetail.InSaleDetailActivity;
import com.aishang.app.util.ViewUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by song on 2016/1/30.
 */
public class LoupanProductAdapter extends RecyclerView.Adapter<LoupanProductAdapter.ViewHolder> {
  private int width = -1;
  private int height = -1;

  List<LoupanProduct> loupanProducts;

  public List<LoupanProduct> getLoupanProducts() {
    return loupanProducts;
  }

  public void setLoupanProducts(List<LoupanProduct> loupanProducts) {
    this.loupanProducts = loupanProducts;
  }

  @Inject public LoupanProductAdapter() {
    loupanProducts = new ArrayList<LoupanProduct>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_loupan_product, parent, false);
    ViewHolder holder = new ViewHolder(view);
    initImgSize(holder);

    holder.imgInSale.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
    holder.imgInSale.setScaleType(ImageView.ScaleType.CENTER_CROP);

    return holder;
  }

  @Override public void onBindViewHolder(final ViewHolder holder, int position) {
    final JLoupanProductListResult.Loupan loupan = loupanProducts.get(position).getLoupan();
    final JLoupanProductListResult.Product product = loupanProducts.get(position).getProduct();

    holder.name.setText(loupan.getName()+"|"+ product.getTitle());

    Picasso.with(holder.getContext())
        .load(AiShangService.IMG_URL + product.getImageUrl())
        .error(R.mipmap.banner)
        .placeholder(R.mipmap.banner)
        .into(holder.imgInSale);

    holder.priceText.setText(product.getPrice() / 10000 + "万元");
    holder.tese.setText(loupan.getPromotion());
    holder.address.setText(loupan.getAddress());
    holder.time.setText(loupan.getMoveInDate());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        Intent intent =
            InSaleDetailActivity.getStartIntent(holder.getContext(), product.getLoupanProductID(),
                loupan.getName());

        holder.getContext().startActivity(intent);
      }
    });
  }

  private void initImgSize(ViewHolder holder) {
    Activity activity = (Activity) holder.getContext();

    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    int mScreenWidth = localDisplayMetrics.widthPixels;
    int pacing = ViewUtil.dpToPx(8);
    width = (mScreenWidth - 2 * pacing);
    height = width * 9 / 16;
  }

  @Override public int getItemCount() {
    return loupanProducts.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.img_in_sale) ImageView imgInSale;
    @Bind(R.id.name) TextView name;
    @Bind(R.id.price_text) TextView priceText;
    @Bind(R.id.time) TextView time;
    @Bind(R.id.address) TextView address;
    @Bind(R.id.tese) TextView tese;
    @Bind(R.id.look) TextView look;
    @Bind(R.id.buy) TextView buy;

    public Context getContext() {
      return itemView.getContext();
    }

    public ViewHolder(View itemView) {
      super(itemView);

      ButterKnife.bind(this, itemView);
    }
  }
}
