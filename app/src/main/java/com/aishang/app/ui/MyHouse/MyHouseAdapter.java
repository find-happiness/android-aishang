package com.aishang.app.ui.MyHouse;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JMyVacationListResult;
import com.aishang.app.ui.income.IncomeActivity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by song on 2016/2/15.
 */
public class MyHouseAdapter extends RecyclerView.Adapter<MyHouseAdapter.ViewHolder> {

  private static final String TAG = "MyHouseAdapter";

  List<JMyVacationListResult.JMyVacationListMyVaList> items;

  int imgWidth;
  int imgHeigth;

  @Inject public MyHouseAdapter() {
    items = new ArrayList<JMyVacationListResult.JMyVacationListMyVaList>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_house, parent, false);
    final ViewHolder holder = new ViewHolder(view);

    holder.cardContent.getViewTreeObserver()
        .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
          @Override public void onGlobalLayout() {

            imgHeigth = holder.cardContent.getHeight();

            if (imgHeigth != 0) {

              imgWidth = (int) (130.0 / 82.0 * imgHeigth);

              holder.img.setLayoutParams(new LinearLayout.LayoutParams(imgWidth, imgHeigth));

              //Log.i(TAG, "onGlobalLayout: width :" + imgWidth + "   height : " + imgHeigth);

              if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                holder.cardContent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
              } else {
                holder.cardContent.getViewTreeObserver().removeGlobalOnLayoutListener(this);
              }
            }
          }
        });
    return holder;
  }

  @Override public void onBindViewHolder(final ViewHolder holder, int position) {

    final JMyVacationListResult.JMyVacationListMyVaList item = items.get(position);

    holder.name.setText(item.getCardName());
    holder.commit.setText(item.getHotelName());
    holder.price.setText(item.getBuyprice() + "元");
    holder.cardNumber.setText(item.getCardID());
    holder.roomNum.setText(item.getRoomText());

    int imgID = R.mipmap.card_vip_type_0;
    switch (item.getType()) {
      case 0:
        imgID = R.mipmap.card_vip_type_0;
        holder.next.setText("查看办证进度>>");
        break;
      case 1:
        imgID = R.mipmap.card_vip_type_1;
        holder.next.setText("查看经营收益>>");
        break;
    }

    switch (item.getStatus()) {

      case 0:
        holder.status3.setVisibility(View.GONE);
        holder.status4.setVisibility(View.GONE);
        holder.status5.setVisibility(View.GONE);
        break;
      case 1:
        holder.status4.setVisibility(View.GONE);
        holder.status5.setVisibility(View.GONE);
        break;
      case 2:
        holder.status5.setVisibility(View.GONE);
        break;
    }

    holder.img.setImageResource(imgID);

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        //intentToDetail(holder.getContext(), hotel.getHotelID(), hotel.getName());

        if (TextUtils.isEmpty(item.getRoomGUID())) return;

        if (item.getType() == 1) {
          Intent intent = IncomeActivity.getStartIntent(item.getRoomGUID(), holder.getContext());
          ((MyHouseActivity) holder.getContext()).startActivity(intent);
        }
      }
    });

    holder.next.setOnClickListener(new View.OnClickListener() {

      @Override public void onClick(View v) {

        //if (TextUtils.isEmpty(item.getRoomGUID())) return;

        if (item.getType() == 1) {
          Intent intent = IncomeActivity.getStartIntent(item.getRoomGUID(), holder.getContext());
          ((MyHouseActivity) holder.getContext()).startActivity(intent);
        } else {
          showStatus(holder);
        }
      }
    });

    holder.stausContainer.setOnClickListener(new View.OnClickListener() {

      @Override public void onClick(View v) {
        hildStatus(holder);
      }
    });
  }

  private void showStatus(final ViewHolder holder) {
    holder.stausContainer.setVisibility(View.VISIBLE);
    PropertyValuesHolder propertyValuesHolder =
        PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 0, 1);
    ObjectAnimator animator =
        ObjectAnimator.ofPropertyValuesHolder(holder.stausContainer, propertyValuesHolder);
    holder.stausContainer.setPivotX(0f);
    holder.stausContainer.setPivotY(0.5f);
    animator.setDuration(300);
    animator.start();
    PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 1, 0);
    ObjectAnimator alphaAnimator = ObjectAnimator.ofPropertyValuesHolder(holder.next, alpha);
    alphaAnimator.setDuration(300);
    alphaAnimator.start();
    alphaAnimator.addListener(new Animator.AnimatorListener() {
      @Override public void onAnimationStart(Animator animation) {

      }

      @Override public void onAnimationEnd(Animator animation) {
        holder.next.setVisibility(View.GONE);
      }

      @Override public void onAnimationCancel(Animator animation) {

      }

      @Override public void onAnimationRepeat(Animator animation) {

      }
    });
  }

  private void hildStatus(final ViewHolder holder) {

    PropertyValuesHolder propertyValuesHolder =
        PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 1, 0);
    ObjectAnimator animator =
        ObjectAnimator.ofPropertyValuesHolder(holder.stausContainer, propertyValuesHolder);
    holder.stausContainer.setPivotX(0f);
    holder.stausContainer.setPivotY(0.5f);
    animator.setDuration(300);
    animator.addListener(new Animator.AnimatorListener() {
      @Override public void onAnimationStart(Animator animation) {

      }

      @Override public void onAnimationEnd(Animator animation) {
        holder.stausContainer.setVisibility(View.GONE);
      }

      @Override public void onAnimationCancel(Animator animation) {

      }

      @Override public void onAnimationRepeat(Animator animation) {

      }
    });
    animator.start();
    holder.next.setVisibility(View.VISIBLE);
    PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 0, 1);
    ObjectAnimator alphaAnimator = ObjectAnimator.ofPropertyValuesHolder(holder.next, alpha);
    alphaAnimator.setDuration(300);
    alphaAnimator.start();
    alphaAnimator.addListener(new Animator.AnimatorListener() {
      @Override public void onAnimationStart(Animator animation) {

      }

      @Override public void onAnimationEnd(Animator animation) {
      }

      @Override public void onAnimationCancel(Animator animation) {

      }

      @Override public void onAnimationRepeat(Animator animation) {

      }
    });
  }

  @Override public int getItemCount() {
    return items.size();
  }

  public List<JMyVacationListResult.JMyVacationListMyVaList> getItems() {
    return items;
  }

  public void setItems(List<JMyVacationListResult.JMyVacationListMyVaList> items) {
    this.items = items;
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
    @Bind(R.id.commit) TextView commit;
    @Bind(R.id.room_num) TextView roomNum;
    @Bind(R.id.price) TextView price;
    @Bind(R.id.card_number) TextView cardNumber;
    @Bind(R.id.next) TextView next;
    @Bind(R.id.status1) TextView status1;
    @Bind(R.id.status2) TextView status2;
    @Bind(R.id.status3) TextView status3;
    @Bind(R.id.status4) TextView status4;
    @Bind(R.id.status5) TextView status5;
    @Bind(R.id.staus_container) LinearLayout stausContainer;
    @Bind(R.id.card_content) LinearLayout cardContent;

    public Context getContext() {
      return this.itemView.getContext();
    }

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
