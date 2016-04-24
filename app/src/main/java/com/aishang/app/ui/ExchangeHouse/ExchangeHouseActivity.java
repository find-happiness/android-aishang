package com.aishang.app.ui.ExchangeHouse;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JHotelRoomFacilitesCatListResult;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.RegexUtils;
import com.aishang.app.widget.NonScrollGridView;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.rengwuxian.materialedittext.MaterialEditText;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;

public class ExchangeHouseActivity extends BaseActivity implements ExchangeHouseMvpView {

  private static final String TAG = "ExchangeHouseActivity";
  private static final int REQUEST_CAMERA_CODE = 11;
  private static final int REQUEST_PREVIEW_CODE = 22;

  @Inject ExchangeHousePresenter presenter;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.expandable_text) TextView expandableText;
  @Bind(R.id.expand_collapse) ImageButton expandCollapse;
  @Bind(R.id.expand_text_view) ExpandableTextView expandTextView;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;
  @Bind(R.id.banner) ImageView banner;
  @Bind(R.id.owner) MaterialEditText owner;
  @Bind(R.id.id_number) MaterialEditText idNumber;
  @Bind(R.id.house_address) MaterialEditText houseAddress;
  @Bind(R.id.room_type) MaterialEditText roomType;
  @Bind(R.id.area) MaterialEditText area;
  @Bind(R.id.contactsPhone) MaterialEditText contactsPhone;
  @Bind(R.id.Email) MaterialEditText Email;
  @Bind(R.id.facilitesType1) NonScrollGridView facilitesType1;
  @Bind(R.id.facilitesType2) NonScrollGridView facilitesType2;
  private ProgressDialog progressDialog;

  private String selectImgUrl;

  private boolean loadImgScuess = false;

  private FaciliteAdapter facilitesType1Adapter;
  private FaciliteAdapter facilitesType2Adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    presenter.attachView(this);
    setContentView(R.layout.activity_exchange_house);
    this.getWindow()
        .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
            | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    ButterKnife.bind(this);

    initToolbar();
    initBanner();
    initFaciliteType1();
    initFaciliteType2();
    expandTextView.setText(this.getString(R.string.holiday_house_rule));
    facilitesType1Adapter = new FaciliteAdapter(this);
    facilitesType1.setAdapter(facilitesType1Adapter);
    facilitesType2Adapter = new FaciliteAdapter(this);
    facilitesType2.setAdapter(facilitesType2Adapter);
    presenter.loadFacilites(1);
  }

  @Override protected void onDestroy() {
    presenter.detachView();
    super.onDestroy();
  }

  private void initFaciliteType1() {
    facilitesType1.setOnTouchListener(new View.OnTouchListener() {

      @Override public boolean onTouch(View v, MotionEvent event) {
        return MotionEvent.ACTION_MOVE == event.getAction();
      }
    });
  }

  private void initFaciliteType2() {
    facilitesType1.setOnTouchListener(new View.OnTouchListener() {

      @Override public boolean onTouch(View v, MotionEvent event) {
        //return MotionEvent.ACTION_MOVE == event.getAction() ? true
        //        : false;
        return MotionEvent.ACTION_MOVE == event.getAction();
      }
    });
  }

  private void initBanner() {
    int[] size = CommonUtil.getHeightWithScreenWidth(this, 8, 3);

    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(size[0], size[1]);

    banner.setLayoutParams(param);
  }

  private void initToolbar() {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_push) {
          postDate();
          return true;
        }
        return false;
      }
    });
    toolbar.setNavigationIcon(R.mipmap.iconfont_livesvg);

    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        ExchangeHouseActivity.this.onBackPressed();
      }
    });
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_push, menu);
    return true;
  }

  private void postDate() {

    CommonUtil.hideSoftInput(this);

    if (isOwnerEmpty()) {
      showOwnerError();
      return;
    }

    if (isIDNumberEmpty() || !RegexUtils.checkIdCard(idNumber.getText().toString().trim())) {
      showIDNumberError();
      return;
    }

    if (isAddressEmpty()) {
      showAddressError();
      return;
    }

    if (isAreaEmpty()) {
      showAreaError();
      return;
    }

    //if (isHouseNumberuEmpty()) {
    //  showHouseNumberError();
    //  return;
    //}

    if (isTypeEmpty()) {
      showHouseTypeError();
      return;
    }

    if (isEmailEmpty() || !RegexUtils.checkEmail(Email.getText().toString().trim())) {
      showEmailError();
      return;
    }

    if (isAddressEmpty()) {
      showAddressError();
      return;
    }

    //if (isStartTimeEmpty()) {
    //  showStartTimeError();
    //  return;
    //}
    //
    //if (isEndTimeEmpty()) {
    //  showEndTimeError();
    //  return;
    //}

    if (isPhoneEmpty() || !RegexUtils.checkMobile(contactsPhone.getText().toString().trim())) {
      showPhoneError();
      return;
    }

    //if (!loadImgScuess) {
    //  showImgError();
    //  return;
    //}

    post();
  }

  private void post() {

    String selectImgData =
        ""; //= CommonUtil.bitmaptoString(BitmapFactory.decodeFile(selectImgUrl));

    //Log.i(TAG, "post: imgData size " + selectImgData.length());

    Observable.zip(facilitesType1Adapter.getChecked(), facilitesType2Adapter.getChecked(),
        new Func2<String, String, String>() {
          @Override public String call(String s, String s2) {
            return AiShangUtil.generProjectChangeParam(owner.getText().toString().trim(),
                idNumber.getText().toString().trim(), houseAddress.getText().toString().trim(),
                roomType.getText().toString().trim(), area.getText().toString().trim(),
                contactsPhone.getText().toString().trim(), Email.getText().toString().trim(), s, s2,
                "1");
          }
        }).subscribe(new Action1<String>() {
      @Override public void call(String s) {
        presenter.postData(1, s);
      }
    });
  }

  private boolean isOwnerEmpty() {
    return TextUtils.isEmpty(owner.getText().toString().trim());
  }

  private boolean isIDNumberEmpty() {
    return TextUtils.isEmpty(idNumber.getText().toString().trim());
  }

  private boolean isAddressEmpty() {
    return TextUtils.isEmpty(houseAddress.getText().toString().trim());
  }

  private boolean isTypeEmpty() {
    return TextUtils.isEmpty(roomType.getText().toString().trim());
  }

  private boolean isAreaEmpty() {
    return TextUtils.isEmpty(area.getText().toString().trim());
  }

  private boolean isEmailEmpty() {
    return TextUtils.isEmpty(Email.getText().toString().trim());
  }

  private boolean isPhoneEmpty() {
    return TextUtils.isEmpty(contactsPhone.getText())
        || TextUtils.getTrimmedLength(contactsPhone.getText()) <= 0;
  }

  private void showOwnerError() {
    showError("产权人不能为空！");
  }

  private void showImgError() {
    showError("房产证图片有误！");
  }

  private void showPhoneError() {
    showError(this.getString(R.string.error_phone));
  }

  private void showAddressError() {
    showError("房屋地址不能为空！");
  }

  private void showIDNumberError() {
    showError("身份证好有误！");
  }

  private void showHouseNumberError() {
    showError("房间号不能为空！");
  }

  private void showHouseTypeError() {
    showError("户型不能为空！");
  }

  private void showEmailError() {
    showError("输入邮箱有误！");
  }

  private void showAreaError() {
    showError("面积不能为空！");
  }

  private void showStartTimeError() {
    showError("开始时间不能为空！");
  }

  private void showEndTimeError() {
    showError("结束时间不能为空！");
  }

  @Override public void showError(String error) {
    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override public void showSuccess() {
    CommonUtil.showSnackbar("提交成功", layoutRoot);
  }

  @Override public void showFacilites(
      List<JHotelRoomFacilitesCatListResult.HotelRoomFacilitesCatListEntity> entitys) {

    Observable.from(entitys)
        .groupBy(
            new Func1<JHotelRoomFacilitesCatListResult.HotelRoomFacilitesCatListEntity, Integer>() {
              @Override public Integer call(
                  JHotelRoomFacilitesCatListResult.HotelRoomFacilitesCatListEntity entity) {
                return entity.getFacilitesType();
              }
            })
        .subscribe(
            new Action1<GroupedObservable<Integer, JHotelRoomFacilitesCatListResult.HotelRoomFacilitesCatListEntity>>() {
              @Override public void call(
                  final GroupedObservable<Integer, JHotelRoomFacilitesCatListResult.HotelRoomFacilitesCatListEntity> hotelRoomFacilitesCatListEntityGroupedObservable) {

                hotelRoomFacilitesCatListEntityGroupedObservable.subscribe(
                    new Action1<JHotelRoomFacilitesCatListResult.HotelRoomFacilitesCatListEntity>() {
                      @Override public void call(
                          JHotelRoomFacilitesCatListResult.HotelRoomFacilitesCatListEntity entity) {
                        if (hotelRoomFacilitesCatListEntityGroupedObservable.getKey() == 1) {
                          facilitesType1Adapter.facilites.add(entity);
                          facilitesType1Adapter.notifyDataSetChanged();
                        } else {
                          facilitesType2Adapter.facilites.add(entity);
                          facilitesType2Adapter.notifyDataSetChanged();
                        }
                      }
                    });
              }
            });
  }

  @Override public void showEmptyFacilite() {

  }

  @Override public void showNetProgress() {
    progressDialog = DialogFactory.createProgressDialog(this, R.string.listview_loading);
    progressDialog.show();
  }

  @Override public void dimissNetProgress() {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }
  }

  //@OnClick(R.id.update_img) void onClickUpdateImg() {
  //
  //  PhotoPickerIntent intent = new PhotoPickerIntent(ExchangeHouseActivity.this);
  //  intent.setSelectModel(SelectModel.SINGLE);
  //  intent.setShowCarema(true); // 是否显示拍照， 默认false
  //  // intent.setImageConfig(config);
  //  startActivityForResult(intent, REQUEST_CAMERA_CODE);
  //}

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK) {
      switch (requestCode) {
        // 选择照片
        case REQUEST_CAMERA_CODE:
          ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);

          if (list.size() > 0) {
            //selectImgUrl = list.get(0);
            //Picasso.with(this).load(new File(selectImgUrl)).into(updateImg, new Callback() {
            //  @Override public void onSuccess() {
            //    loadImgScuess = true;
            //  }
            //
            //  @Override public void onError() {
            //    loadImgScuess = false;
            //  }
            //});
          }
          break;
      }
    }
  }

  class FaciliteAdapter extends BaseAdapter {

    List<JHotelRoomFacilitesCatListResult.HotelRoomFacilitesCatListEntity> facilites;

    Activity act;

    private Map<Integer, String> checked = new HashMap<>();

    public FaciliteAdapter(Activity act) {
      this.facilites = new ArrayList<>();
      this.act = act;
    }

    @Override public int getCount() {
      return facilites.size();
    }

    @Override
    public JHotelRoomFacilitesCatListResult.HotelRoomFacilitesCatListEntity getItem(int position) {
      return facilites.get(position);
    }

    @Override public long getItemId(int position) {
      return position;
    }

    @Override public View getView(final int position, View convertView, ViewGroup parent) {
      CheckBox checkBox = new CheckBox(act);

      final String str = facilites.get(position).getFacilitesName();

      checkBox.setText(str);

      checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          if (isChecked) {
            if (!checked.containsKey(position)) checked.put(position, str);
          } else {
            checked.remove(position);
          }
        }
      });
      return checkBox;
    }

    public Observable<String> getChecked() {
      return Observable.from(checked.values()).reduce(new Func2<String, String, String>() {
        @Override public String call(String s, String s2) {
          return s + "," + s2;
        }
      });
    }
  }
}
