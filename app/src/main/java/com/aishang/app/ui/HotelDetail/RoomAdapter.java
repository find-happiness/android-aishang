package com.aishang.app.ui.HotelDetail;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.data.model.HotelOrder;
import com.aishang.app.data.model.JHotelRoomCatListByhotelIDResult;
import com.aishang.app.data.model.SmallImgModel;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.BuyHotel.BuyHotelActivity;
import com.aishang.app.ui.PhotoGallery.PhotoGalleryActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.BusProvider;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.EventPosterHelper;
import com.aishang.app.util.ViewUtil;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.github.aakira.expandablelayout.Utils;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by song on 2016/3/11.
 */
public class RoomAdapter {

  private static final String TAG = "RoomAdapter";
  private List<RoomCat> roomCat;
  private HotelDetailActivity context;
  int[] imgSize;

  private List<ViewHolder> viewHolders = new ArrayList<>();

  public RoomAdapter(List<RoomCat> roomCat, HotelDetailActivity context) {
    this.roomCat = roomCat;
    this.context = context;
    imgSize = calculationImageSize();
  }

  public List<RoomCat> getRoomCat() {
    return roomCat;
  }

  public void setRoomCat(List<RoomCat> roomCat) {
    this.roomCat = roomCat;
  }

  public View getView(int postion) {

    View roomItem = LayoutInflater.from(context).inflate(R.layout.item_room_detail, null);

    final ViewHolder holder = new ViewHolder(roomItem);
    viewHolders.add(holder);

    final RoomCat item = roomCat.get(postion);

    LinearLayout.LayoutParams params =
        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
    int spacing = context.getResources().getDimensionPixelSize(R.dimen.spacing_medium);
    params.setMargins(0, spacing, 0, 0);

    holder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {
      @Override public void onPreOpen() {
        createRotateAnimator(holder.button, 0f, 90f).start();
      }

      @Override public void onPreClose() {
        createRotateAnimator(holder.button, 90f, 0f).start();
      }
    });

    //buttonLayout.setRotation(expandState.get(position) ? 180f : 0f);
    holder.button.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(final View v) {
        holder.expandableLayout.toggle();
      }
    });

    roomItem.setLayoutParams(params);

    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(imgSize[0], imgSize[1]);
    //layoutParams.setMargins(0, 0, ViewUtil.dpToPx(8), 0);
    layoutParams.setMargins(0, 0, ViewUtil.dpToPx(8), 0);
    holder.img1.setLayoutParams(layoutParams);
    holder.img1.setScaleType(ImageView.ScaleType.CENTER_CROP);
    holder.img2.setLayoutParams(layoutParams);
    holder.img2.setScaleType(ImageView.ScaleType.CENTER_CROP);
    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(imgSize[0], imgSize[1]);
    layoutParams2.setMargins(0, 0, 0, 0);
    holder.img3.setLayoutParams(layoutParams2);
    holder.img3.setScaleType(ImageView.ScaleType.CENTER_CROP);

    JHotelRoomCatListByhotelIDResult.GRoomTypeListEntity typeEntity = item.getRoomTypeListEntity();
    holder.type.setText(typeEntity.getRoomTypeName());
    holder.type.setTag(typeEntity);

    JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity defaultCat =
        item.getCatListEntities().get(0);

    bindData(holder, defaultCat);

    holder.orderRoomNum.setTag(0);
    holder.orderRoomNum.setText(0+"");

    holder.add.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        int num = (int) holder.orderRoomNum.getTag();
        if (num >= 0 && num < 99) {
          num++;
          holder.orderRoomNum.setText(num + "");
          holder.orderRoomNum.setTag(num);
        }

        if (num > 0) {
          //holder.sub.setVisibility(View.VISIBLE);
          //holder.orderRoomNum.setVisibility(View.VISIBLE);
        }
      }
    });

    holder.sub.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        int num = (int) holder.orderRoomNum.getTag();
        if (num > 0 && num <= 99) {
          num--;
          holder.orderRoomNum.setText(num + "");
          holder.orderRoomNum.setTag(num);
        }

        if (num <= 0) {
          //holder.sub.setVisibility(View.GONE);
         //holder.orderRoomNum.setVisibility(View.GONE);
        }
      }
    });

    //holder.sub.setVisibility(View.GONE);
    //holder.orderRoomNum.setVisibility(View.GONE);

    holder.roomCat.setText(defaultCat.getRoomCatName());

    final String[] strCat = new String[item.getCatListEntities().size()];

    int i = 0;
    for (JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity entity : item.getCatListEntities()) {
      strCat[i] = entity.getRoomCatName();
      i++;
    }

    holder.roomCat.setTag(0);
    holder.expandableLayout.setTag(item.getCatListEntities().get(0));

    holder.roomCat.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        DialogFactory.createSingleChoiceDialog(context, strCat, (int) holder.roomCat.getTag(),
            new DialogInterface.OnClickListener() {
              @Override public void onClick(DialogInterface dialog, int which) {
                holder.roomCat.setTag(which);
                JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity cat =
                    item.getCatListEntities().get(which);
                holder.roomCat.setText(cat.getRoomCatName());
                holder.expandableLayout.setTag(cat);
                bindData(holder, cat);
                dialog.dismiss();
              }
            }, "").show();
      }
    });

    holder.btnBuy.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        int roomNum = (int) holder.orderRoomNum.getTag();

        if (roomNum > 0) {
          HotelOrder order = new HotelOrder(
              (JHotelRoomCatListByhotelIDResult.GRoomTypeListEntity) holder.type.getTag(),
              (JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity) holder.expandableLayout.getTag(),
              (int) holder.orderRoomNum.getTag());

          BusProvider.getInstance().post(order);
        } else {
          CommonUtil.showSnackbar("您没有选择房间数！", context.getLayoutRoot());
        }
      }
    });

    //if(postion == 0) {
    //  holder.expandableLayout.setExpanded(true);
    //}
    return roomItem;
  }

  private void bindData(final ViewHolder holder,
      JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity entity) {

    holder.roomPrice.setText("￥" + entity.getBasicPrice());
    holder.bedCount.setText("床数:  " + entity.getBedCount());
    holder.bedType.setText("床型:  " + entity.getBedTypeName());
    holder.maxGuest.setText("可入住人数:  " + entity.getMaxGuest());
    holder.area.setText("面积:  " + entity.getTotalAreaMax() + "平方米");
    holder.comment.setText("描述:  " + entity.getComment());
    holder.floors.setText("楼层:  " + entity.getFloors());

    int i = 0;
    for (String strImgUrl : entity.getImagesList()) {
      i++;
      String prefix = strImgUrl.substring(strImgUrl.lastIndexOf("."));

      strImgUrl = strImgUrl.replace(prefix, "_237" + prefix);
      //Log.i(TAG, "bindData: " + strImgUrl);

      if (i > 3) return;
      ImageView imgView = null;
      switch (i) {
        case 1:
          imgView = holder.img1;
          break;
        case 2:
          imgView = holder.img2;
          break;
        case 3:
          imgView = holder.img3;
          break;
      }
      Picasso.with(context)
          .load(AiShangService.IMG_URL + strImgUrl)
          .error(R.mipmap.banner)
          .placeholder(R.mipmap.banner)
          .into(imgView);

      setOnclickToImg(imgView, i - 1,
          entity.getImagesList().toArray(new String[entity.getImagesList().size()]), holder);
    }

    if (i == 0) {
      holder.img1.setVisibility(View.GONE);
      holder.img2.setVisibility(View.GONE);
      holder.img3.setVisibility(View.GONE);
    } else if (i == 1) {
      holder.img1.setVisibility(View.VISIBLE);
      holder.img2.setVisibility(View.GONE);
      holder.img3.setVisibility(View.GONE);
    } else if (i == 2) {
      holder.img1.setVisibility(View.VISIBLE);
      holder.img2.setVisibility(View.VISIBLE);
      holder.img3.setVisibility(View.GONE);
    } else {
      holder.img1.setVisibility(View.VISIBLE);
      holder.img2.setVisibility(View.VISIBLE);
      holder.img3.setVisibility(View.VISIBLE);
    }
  }

  private void setOnclickToImg(ImageView imgView, final int index, final String[] photos,
      final ViewHolder holder) {
    imgView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        final int[] locations1 = new int[2];
        final int[] locations2 = new int[2];
        final int[] locations3 = new int[2];
        final int[] size = new int[2];
        holder.img1.getLocationOnScreen(locations1);
        holder.img2.getLocationOnScreen(locations2);
        holder.img3.getLocationOnScreen(locations3);

        ArrayList<int[]> potion = new ArrayList();
        potion.add(locations1);
        potion.add(locations2);
        potion.add(locations3);

        size[0] = v.getWidth();
        size[1] = v.getHeight();

        roomImgClick(index, photos, potion, size);
      }
    });
  }

  private void roomImgClick(int index, String[] photos, List<int[]> locations, int[] size) {
    //Intent intent = PhotoGalleryActivity.getIntent(context, photos, index);
    //context.startActivity(intent);
    BusProvider.getInstance().post(SmallImgModel.create(index, photos, locations, size));
  }

  private ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
    ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
    animator.setDuration(300);
    animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
    return animator;
  }

  private int[] calculationImageSize() {
    int[] imgSize = new int[2];

    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    context.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    int mScreenWidth = localDisplayMetrics.widthPixels;
    int pacing = ViewUtil.dpToPx(4);
    int width = (mScreenWidth - 2 * pacing - 2 * ViewUtil.dpToPx(8)) / 3;

    imgSize[0] = width;
    imgSize[1] = width * 9 / 16;

    return imgSize;
  }

  public Observable<List<HotelOrder>> getOrderRooms() {

    return Observable.from(viewHolders).filter(new Func1<ViewHolder, Boolean>() {
      @Override public Boolean call(ViewHolder viewHolder) {
        return (int) viewHolder.orderRoomNum.getTag() > 0;
      }
    }).map(new Func1<ViewHolder, HotelOrder>() {
      @Override public HotelOrder call(ViewHolder viewHolder) {
        return new HotelOrder(
            (JHotelRoomCatListByhotelIDResult.GRoomTypeListEntity) viewHolder.type.getTag(),
            (JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity) viewHolder.expandableLayout.getTag(),
            (int) viewHolder.orderRoomNum.getTag());
      }
    }).toList();
  }

  public void toggle(int index){
    viewHolders.get(index).button.performClick();
  }

  private boolean checkLogin() {
    return BoilerplateApplication.get(context).getMemberLoginResult() != null;
  }

  static class ViewHolder {
    @Bind(R.id.type) TextView type;
    @Bind(R.id.room_cat) TextView roomCat;
    @Bind(R.id.button) RelativeLayout button;
    @Bind(R.id.sub) ImageView sub;
    @Bind(R.id.order_room_num) TextView orderRoomNum;
    @Bind(R.id.add) ImageView add;
    @Bind(R.id.title) RelativeLayout title;
    @Bind(R.id.service1) ImageView service1;
    @Bind(R.id.service2) ImageView service2;
    @Bind(R.id.service3) ImageView service3;
    @Bind(R.id.service4) ImageView service4;
    @Bind(R.id.service5) ImageView service5;
    @Bind(R.id.ll_services) LinearLayout llServices;
    @Bind(R.id.room_price) TextView roomPrice;
    @Bind(R.id.img_1) ImageView img1;
    @Bind(R.id.img_2) ImageView img2;
    @Bind(R.id.img_3) ImageView img3;
    @Bind(R.id.ll_imgs) LinearLayout llImgs;
    @Bind(R.id.floors) TextView floors;
    @Bind(R.id.area) TextView area;
    @Bind(R.id.bedCount) TextView bedCount;
    @Bind(R.id.bedType) TextView bedType;
    @Bind(R.id.maxGuest) TextView maxGuest;
    @Bind(R.id.comment) TextView comment;
    @Bind(R.id.expandableLayout) ExpandableRelativeLayout expandableLayout;
    @Bind(R.id.buy) Button btnBuy;

    ViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }
}
