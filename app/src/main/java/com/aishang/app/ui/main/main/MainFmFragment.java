package com.aishang.app.ui.main.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import android.widget.TextView;
import butterknife.OnEditorAction;
import com.aishang.app.R;
import com.aishang.app.data.model.JMrePromParam;
import com.aishang.app.data.model.JMrePromResult;
import com.aishang.app.data.model.ViewModel;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.insaleDetail.InSaleDetailActivity;
import com.aishang.app.ui.main.MainActivity;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.LocalImageHolderView;
import com.aishang.app.util.NetworkUtil;
import com.aishang.app.widget.NonScrollGridView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import com.google.gson.Gson;
import com.quinny898.library.persistentsearch.SearchBox;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by song on 2016/1/16.
 */
public class MainFmFragment extends Fragment implements MainFmMvpView {

  private static final String TAG = "MainFmFragment";

  @Inject MainFmPresenter mMainPresenter;

  HotHotelAdapter hotHotelAdapter;
  HotYouJiAdapter hotYouJiAdapter;
  ZaiShouAdapter zaiShouAdapter;

  @Bind(R.id.convenientBanner) ConvenientBanner banner;

  @Bind(R.id.gridview_in_sale) NonScrollGridView gvInSale;
  @Bind(R.id.gridview_hot_hotel) NonScrollGridView gvHotHotel;
  @Bind(R.id.gridview_hot_youji) NonScrollGridView gvYouJi;

  @Bind(R.id.main_scrollview) ScrollView scrollView;

  @Bind(R.id.edit_search) EditText editSearch;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((MainActivity) getActivity()).getActivityComponent().inject(this);
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    View view = inflater.inflate(R.layout.fragment_main, null);
    ButterKnife.bind(this, view);
    mMainPresenter.attachView(this);
    initView();

    if (NetworkUtil.isNetworkConnected(getActivity())) {

      JMrePromParam param = new JMrePromParam();
      param.setLocation(11);
      param.setCount(4);

      mMainPresenter.loadBanner(false, 1, new Gson().toJson(param));
    }
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    scrollView.scrollTo(0, 0);
  }

  @Override public void onResume() {
    super.onResume();
    banner.startTurning(5000);
  }

  @Override public void onPause() {
    super.onPause();
    banner.stopTurning();
  }

  private void initView() {
    initBanner();
    initGvHothotel();
    initGvYouJi();
    initGvZaiShou();
    initSearch();
  }

  private void initSearch() {

    editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_SEARCH == actionId) {
          hideSoftInputWindows();
          return true;
        }
        return false;
      }
    });
  }

  private void hideSoftInputWindows() {
    InputMethodManager inputMethodManager =
        (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
    inputMethodManager.hideSoftInputFromWindow(editSearch.getApplicationWindowToken(), 0);
  }

  private void initBanner() {

    int[] size = CommonUtil.getHeightWithScreenWidth(this.getActivity(), 16, 9);
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size[0], size[1]);

    banner.setLayoutParams(layoutParams);

    //List<Integer> localImages = new ArrayList<>();
    //localImages.add(R.mipmap.banner);
    //localImages.add(R.mipmap.banner);
    //localImages.add(R.mipmap.banner);
    //localImages.add(R.mipmap.banner);
    //
    //banner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
    //  @Override public LocalImageHolderView createHolder() {
    //    return new LocalImageHolderView();
    //  }
    //}, localImages)
    //    //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
    //    .setPageIndicator(new int[] { R.mipmap.ellipse_nomal, R.mipmap.ellipse_select })
    //        //设置指示器的方向
    //    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
    ////设置翻页的效果，不需要翻页效果可用不设
    ////.setPageTransformer(Transformer.DefaultTransformer);    //集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
    ////        convenientBanner.setManualPageable(false);//设置不能手动影响
    //
    //banner.setOnItemClickListener(new OnItemClickListener() {
    //  @Override public void onItemClick(int position) {
    //    InSaleDetailActivity.navigate((MainActivity) MainFmFragment.this.getActivity(), banner,
    //        new ViewModel("item",
    //            "http://img5.imgtn.bdimg.com/it/u=236513344,827617441&fm=11&gp=0.jpg"));
    //  }
    //});
  }

  private void initGvHothotel() {
    ArrayList<Integer> hotel = new ArrayList<>();
    for (int i = 0; i < 6; i++) {
      hotel.add(i);
    }
    hotHotelAdapter = new HotHotelAdapter(getActivity());
    hotHotelAdapter.setHotels(hotel);
    gvHotHotel.setAdapter(hotHotelAdapter);
    gvHotHotel.setOnTouchListener(new View.OnTouchListener() {
      @Override public boolean onTouch(View v, MotionEvent event) {
        return MotionEvent.ACTION_MOVE == event.getAction();
      }
    });
  }

  private void initGvZaiShou() {
    ArrayList<Integer> zaiShou = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      zaiShou.add(i);
    }
    zaiShouAdapter = new ZaiShouAdapter(getContext());
    zaiShouAdapter.setZaiShous(zaiShou);
    gvInSale.setAdapter(zaiShouAdapter);
    gvInSale.setOnTouchListener(new View.OnTouchListener() {

      @Override public boolean onTouch(View v, MotionEvent event) {
        return MotionEvent.ACTION_MOVE == event.getAction();
      }
    });
  }

  private void initGvYouJi() {
    ArrayList<Integer> youji = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      youji.add(i);
    }
    hotYouJiAdapter = new HotYouJiAdapter(getContext());
    hotYouJiAdapter.setHotYouJis(youji);
    gvYouJi.setAdapter(hotYouJiAdapter);
    gvYouJi.setOnTouchListener(new View.OnTouchListener() {

      @Override public boolean onTouch(View v, MotionEvent event) {
        //return MotionEvent.ACTION_MOVE == event.getAction() ? true
        //        : false;
        return MotionEvent.ACTION_MOVE == event.getAction();
      }
    });
  }

  @OnClick(R.id.btn_zaishou) void OnZaiShouClick() {
    mMainPresenter.IntentToZaiShou();
  }

  @OnClick(R.id.btn_huanzu) void onHuanZuClick() {
    mMainPresenter.IntentToHuanZu();
  }

  @OnClick(R.id.btn_youji) void onYouJiClick() {
    mMainPresenter.IntentToTravelList();
  }

  @OnClick(R.id.btn_xiangmuhezu) void onXiangMuHeZuoClick() {
    mMainPresenter.IntentToProjectJoint();
  }

  @OnClick(R.id.btn_dujiawu) void btnDuJiaWu() {
  }

  @OnClick(R.id.btn_store) void btnStore() {
  }

  @OnClick(R.id.btn_kanfangtuan) void btnKanFangDuan() {
    mMainPresenter.IntentToKanFanTuan();
  }

  @OnClick(R.id.btn_fabuchuzu) void btnFaBuChuZu() {
  }

  @Override public void showBanner(List<JMrePromResult.Ad> ads) {
    banner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
      @Override public LocalImageHolderView createHolder() {
        return new LocalImageHolderView();
      }
    }, ads)
        //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        .setPageIndicator(new int[] { R.mipmap.ellipse_nomal, R.mipmap.ellipse_select })
            //设置指示器的方向
        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
  }

  @Override public void showLoupan() {

  }

  @Override public void showHotel() {

  }

  @Override public void showTrave() {

  }

  @Override public void showBannerEmpty() {

  }

  @Override public void showLoupanEmpty() {

  }

  @Override public void showHotelEmpty() {

  }

  @Override public void showError(String error) {
    Log.i(TAG, "showError: " + error);
  }

  public class LocalImageHolderView implements Holder<JMrePromResult.Ad> {
    private ImageView imageView;

    public View createView(Context context) {
      imageView = new ImageView(context);
      imageView.setScaleType(ImageView.ScaleType.FIT_XY);
      return imageView;
    }

    @Override public void UpdateUI(Context context, int position, JMrePromResult.Ad data) {

      Picasso.with(context)
          .load(AiShangService.AiShangHost + data.getImageUrl())
          .error(R.mipmap.banner)
          .placeholder(R.mipmap.banner)
          .into(imageView);
    }
  }
}
