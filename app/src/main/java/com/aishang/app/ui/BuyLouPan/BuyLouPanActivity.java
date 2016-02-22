package com.aishang.app.ui.BuyLouPan;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.R;
import com.aishang.app.data.model.JLoupanProductDetailResult;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.NetImageHolderView;
import com.aishang.app.util.RegexUtils;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;

public class BuyLouPanActivity extends BaseActivity implements BuyLoupanMvpView {

  private static final String LOUPAN = "loupan";

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.convenientBanner) ConvenientBanner banner;
  @Bind(R.id.name) TextView name;
  @Bind(R.id.et_name) EditText etName;
  @Bind(R.id.phone) EditText phone;
  @Bind(R.id.et_address) EditText etAddress;
  @Bind(R.id.date) TextView date;
  @Bind(R.id.et_yuyu_address) EditText etYuyuAddress;

  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;

  @Inject BuyLoupanPresenter presenter;

  ProgressDialog progressDialog;

  private JLoupanProductDetailResult loupan;

  public static Intent getStartIntent(Context ctx, JLoupanProductDetailResult loupan) {
    Intent intent = new Intent(ctx, BuyLouPanActivity.class);
    intent.putExtra(LOUPAN, loupan);
    return intent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    presenter.attachView(this);

    setContentView(R.layout.activity_buy_lou_pan);
    ButterKnife.bind(this);
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    if (savedInstanceState != null && savedInstanceState.containsKey(LOUPAN)) {
      loupan = (JLoupanProductDetailResult) savedInstanceState.get(LOUPAN);
    } else {
      loupan = (JLoupanProductDetailResult) this.getIntent().getSerializableExtra(LOUPAN);
    }

    initToolbar();
    initBanner();
  }

  @OnClick(R.id.submint) void onClickSubmit() {

    CommonUtil.hideSoftInput(this);

    if (loupan == null) {
      showError("没有获取到楼盘数据，请稍后再试！");
      return;
    }

    if (isNameEmpty()) {
      showError("联系人姓名不能为空！");
      return;
    }

    if (isPhoneEmpty() || !isPhone()) {
      showError(this.getString(R.string.error_phone));
      return;
    }

    if (isAddressEmpty()) {
      showError("联系人住址不能为空！");
      return;
    }

    progressDialog = DialogFactory.createProgressDialog(this, R.string.posting);
    progressDialog.show();
    post();
  }

  private void initToolbar() {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    toolbar.setNavigationIcon(R.mipmap.iconfont_livesvg);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onBackPressed();
      }
    });
  }

  private boolean isNameEmpty() {
    return TextUtils.isEmpty(etName.getText());
  }

  private boolean isPhoneEmpty() {
    return TextUtils.isEmpty(phone.getText());
  }

  private boolean isAddressEmpty() {
    return TextUtils.isEmpty(etAddress.getText());
  }

  private boolean isPhone() {
    return RegexUtils.checkMobile(phone.getText().toString().trim());
  }

  private void post() {

    //var strJson = "{ rentalID:" + loupanId + ", name:'" + guestName + "', phone:'" + guestPhone + "', address:'" + guestAddress + "',negotiationTime:'" + negotiationTime + "',  zoneIDLevel2:'" + province + "',  zoneIDLevel3:'" + city + "',zoneIDLevel4:'" + area + "',  zoneIText:'" + zoneIText + "'}"

    Object objDate = date.getTag();
    String dateTime = "";
    if (objDate != null) {
      dateTime = (String) objDate;
    }

    String json = AiShangUtil.generSubscriptionParam(loupan.getDataSet().getLoupanProductID() + "",
        etName.getText().toString().trim(), phone.getText().toString().trim(),
        etAddress.getText().toString().trim(), dateTime, "", "", "",
        etYuyuAddress.getText().toString().trim());

    presenter.postData(0, json);
  }

  private void dismissDialog() {
    if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
  }

  private void initBanner() {

    int[] size = CommonUtil.getHeightWithScreenWidth(this, 16, 9);
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size[0], size[1]);

    banner.setLayoutParams(layoutParams);

    setBanner(loupan.getDataSet().getImageList());
  }

  @OnClick(R.id.date) void onclickDate() {
    DialogFactory.createDatePickerDialog(this, Calendar.getInstance(),
        new DatePickerDialog.OnDateSetListener() {
          @Override
          public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            date.setText(year + "年" + monthOfYear + "月" + dayOfMonth + "日");
            date.setTag(year + "-" + monthOfYear + "-" + dayOfMonth);
          }
        }).show();
  }

  private void setBanner(JLoupanProductDetailResult.Image[] images) {
    List<String> localImages = new ArrayList<>();
    for (JLoupanProductDetailResult.Image img : images) {
      localImages.add(img.getUrl());
    }

    banner.setPages(new CBViewHolderCreator<NetImageHolderView>() {
      @Override public NetImageHolderView createHolder() {
        return new NetImageHolderView();
      }
    }, localImages)
        //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        .setPageIndicator(new int[] { R.mipmap.ellipse_nomal, R.mipmap.ellipse_select })
            //设置指示器的方向
        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
    //设置翻页的效果，不需要翻页效果可用不设
    //.setPageTransformer(Transformer.DefaultTransformer);    //集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
    //        convenientBanner.setManualPageable(false);//设置不能手动影响

    //title.setText(itemTitle);
  }

  @Override public void showError(String error) {
    dismissDialog();

    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override public void showSuccess() {
    dismissDialog();
  }
}
