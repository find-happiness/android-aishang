package com.aishang.app.ui.TradeCenter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.R;
import com.aishang.app.data.model.AdapterImgModel;
import com.aishang.app.data.model.JLoupanProductDetailResult;
import com.aishang.app.data.model.JLoupanProductVIPViewResult;
import com.aishang.app.data.model.SmallImgModel;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.BusProvider;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.NetImageHolderView;
import com.aishang.app.util.NetworkUtil;
import com.aishang.app.util.ViewUtil;
import com.aishang.app.widget.HackyViewPager;
import com.aishang.app.widget.NonScrollGridView;
import com.squareup.otto.Subscribe;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * A fragment with a Google +1 button.
 * Use the {@link TradeSaleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TradeSaleFragment extends Fragment implements TradeSaleMvpView {

  private static final String TAG = "TradeSaleFragment";
  @Inject TradeSalePresenter presenter;

  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
  @Bind(R.id.layoutRoot) FrameLayout layoutRoot;
  @Bind(R.id.loupan_name) TextView loupanName;
  @Bind(R.id.loupan_address) TextView loupanAddress;
  @Bind(R.id.type) TextView type;
  @Bind(R.id.move_in_date) TextView moveInDate;
  @Bind(R.id.buy_date) TextView buyDate;
  @Bind(R.id.price) EditText price;
  @Bind(R.id.gridview_loupan_img) NonScrollGridView gridviewLoupanImg;
  @Bind(R.id.gridview_zhenshu_img) NonScrollGridView gridviewZhenshuImg;
  @Bind(R.id.description) WebView description;
  @Bind(R.id.transportation) WebView transportation;
  @Bind(R.id.view_pager) HackyViewPager viewPager;

  private String mParam1;
  private String mParam2;
  private Dialog progress;

  private int selectLoupanIndex = 0;

  Observable<List<String>> loupanNames;
  DisplayMetrics outMetrics;
  List<JLoupanProductVIPViewResult.ZoneListBean.LoupanListBean> loupanBeans;

  public TradeSaleFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment TradeSaleFragment.
   */
  public static TradeSaleFragment newInstance(String param1, String param2) {
    TradeSaleFragment fragment = new TradeSaleFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((TradeCenterActivity) getActivity()).getActivityComponent().inject(this);
    presenter.attachView(this);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment

    outMetrics = new DisplayMetrics();
    this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);

    View view = inflater.inflate(R.layout.fragment_trade_sale, container, false);

    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initView();
    if (NetworkUtil.isNetworkConnected(this.getActivity())) {
      presenter.loadLoupanProductVIPView(0,
          AiShangUtil.generLoupanProductVIPViewParam("02368400000",
              "CAA82AACA9D475482AA516AC8DE8D4CA", 1));
    } else {
      CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
    }
  }

  @Override public void onResume() {
    super.onResume();
    BusProvider.getInstance().register(this);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override public void onStop() {
    super.onStop();
    BusProvider.getInstance().unregister(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();

    if (presenter != null) presenter.detachView();
  }

  @Override public void showDialog() {
    progress = DialogFactory.createProgressDialog(this.getActivity(), R.string.listview_loading);
    progress.show();
  }

  @Override public void dimissDialog() {
    if (progress != null && progress.isShowing()) {
      progress.dismiss();
    }
  }

  @Override public void showError(String error) {
    CommonUtil.showSnackbar(error, layoutRoot);
  }

  @Override public void bindLoupan(
      List<JLoupanProductVIPViewResult.ZoneListBean.LoupanListBean> loupanBeans) {
    this.loupanBeans = loupanBeans;
    loupanNames = Observable.from(loupanBeans)
        .map(new Func1<JLoupanProductVIPViewResult.ZoneListBean.LoupanListBean, String>() {
          @Override public String call(
              JLoupanProductVIPViewResult.ZoneListBean.LoupanListBean loupanListBean) {
            return loupanListBean.getName();
          }
        })
        .toList();
    JLoupanProductVIPViewResult.ZoneListBean.LoupanListBean bean =
        loupanBeans.get(selectLoupanIndex);

    loupanName.setText(bean.getName());
    loupanAddress.setText(bean.getAddress());

    presenter.loadLoupanProductDetail(1,
        AiShangUtil.generLoupanProductDetail(bean.getProductID(), 1, 1));
  }

  @Override public void bindLoupanDetail(JLoupanProductDetailResult result) {
    JLoupanProductDetailResult.Data dataSet = result.getDataSet();
    JLoupanProductDetailResult.Data.LoupanData loupanData = dataSet.getLoupanData();

    AiShangUtil.setWebViewContent(description, loupanData.getProjectIntro());

    AiShangUtil.setWebViewContent(transportation, loupanData.getTransportation());

    gridviewLoupanImg.setAdapter(
        new ImgAdapter(this.getActivity(), Arrays.asList(result.getDataSet().getImageList())));

    moveInDate.setText(dataSet.getMoveInDate());
  }

  @OnClick({ R.id.loupan_name, R.id.type, R.id.buy_date }) public void onClick(View view) {
    switch (view.getId()) {
      case R.id.loupan_name:
        showLoupanNameDialog();
        break;
      case R.id.type:
        break;
      case R.id.buy_date:
        showBuyDataDialog();
        break;
    }
  }

  @Subscribe public void onclickSmallImg(SmallImgModel model) {
    String[] photos = model.getPhotos();

    viewPager.setAdapter(new LoupanImgPagerAdapter(this.getActivity(), photos));

    viewPager.setCurrentItem(model.getIndex());

    viewPager.setVisibility(View.VISIBLE);

    viewPager.clearAnimation();

    int[] locations = model.getLocations().get(model.getIndex());

    viewPager.setTag(model);

    PropertyValuesHolder pvhSX = PropertyValuesHolder.ofFloat(View.SCALE_X,
        (float) model.getSize()[0] / (float) outMetrics.widthPixels, 1f);
    PropertyValuesHolder pvhSY = PropertyValuesHolder.ofFloat(View.SCALE_Y,
        (float) model.getSize()[1] / (float) outMetrics.heightPixels, 1f);

    PropertyValuesHolder pvhTX = PropertyValuesHolder.ofFloat(View.X, locations[0], 0);
    PropertyValuesHolder pvhTY = PropertyValuesHolder.ofFloat(View.Y, locations[1], 0);

    PropertyValuesHolder pvhA = PropertyValuesHolder.ofFloat(View.ALPHA, 0, 1);

    viewPager.setPivotY(0.5f);
    viewPager.setPivotY(0.5f);
    ObjectAnimator animator =
        ObjectAnimator.ofPropertyValuesHolder(viewPager, pvhSX, pvhSY, pvhTX, pvhTY, pvhA);
    animator.setDuration(300);
    animator.start();
  }

  @Subscribe public void onTapAdapterImg(AdapterImgModel model) {
    viewPager.clearAnimation();

    SmallImgModel roomImgModel = (SmallImgModel) viewPager.getTag();
    int[] locations = roomImgModel.getLocations().get(model.getIndex());

    PropertyValuesHolder pvhSX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f,
        (float) roomImgModel.getSize()[0] / (float) outMetrics.widthPixels);
    PropertyValuesHolder pvhSY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f,
        (float) roomImgModel.getSize()[1] / (float) outMetrics.heightPixels);

    PropertyValuesHolder pvhTX = PropertyValuesHolder.ofFloat(View.X, 0, locations[0]);
    PropertyValuesHolder pvhTY =
        PropertyValuesHolder.ofFloat(View.Y, 0, locations[1] - roomImgModel.getSize()[1] / 2);

    PropertyValuesHolder pvhA = PropertyValuesHolder.ofFloat(View.ALPHA, 1, 0);

    ObjectAnimator animator =
        ObjectAnimator.ofPropertyValuesHolder(viewPager, pvhSX, pvhSY, pvhTX, pvhTY, pvhA);
    animator.setDuration(300);
    animator.start();

    animator.addListener(new Animator.AnimatorListener() {
      @Override public void onAnimationStart(Animator animation) {

      }

      @Override public void onAnimationEnd(Animator animation) {
        viewPager.setVisibility(View.GONE);
      }

      @Override public void onAnimationCancel(Animator animation) {

      }

      @Override public void onAnimationRepeat(Animator animation) {

      }
    });
  }

  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK && View.VISIBLE == viewPager.getVisibility()) {
      BusProvider.getInstance().post(AdapterImgModel.create(0, 0, viewPager.getCurrentItem()));
      return false;
    }
    return true;
  }

  void showLoupanNameDialog() {
    loupanNames.subscribe(new Action1<List<String>>() {
      @Override public void call(final List<String> strings) {

        DialogFactory.createSingleChoiceDialog(TradeSaleFragment.this.getActivity(),
            strings.toArray(new String[strings.size()]), selectLoupanIndex,
            new DialogInterface.OnClickListener() {
              @Override public void onClick(DialogInterface dialog, int which) {

                int bakSelectPrice = selectLoupanIndex;
                selectLoupanIndex = which;
                dialog.dismiss();

                if (bakSelectPrice != selectLoupanIndex) {
                  loupanName.setText(strings.get(which));
                  loupanAddress.setText(loupanBeans.get(which).getAddress());
                  presenter.loadLoupanProductDetail(1, AiShangUtil.generLoupanProductDetail(
                      loupanBeans.get(selectLoupanIndex).getProductID(), 1, 1));
                }
              }
            }, "项目名称").show();
      }
    });
  }

  private void initView() {
    initViewPager();
    initLoupanGridView();
    initZhenShuGridView();
  }

  private void initViewPager() {

    viewPager.setFocusable(true);
    viewPager.setFocusableInTouchMode(true);
    viewPager.setOnKeyListener(new View.OnKeyListener() {
      @Override public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
            && event.getAction() == KeyEvent.ACTION_DOWN
            && View.VISIBLE == viewPager.getVisibility()) {
          BusProvider.getInstance().post(AdapterImgModel.create(0, 0, viewPager.getCurrentItem()));
          return true;
        }
        return false;
      }
    });
  }

  private void initLoupanGridView() {
    gridviewLoupanImg.setOnTouchListener(new View.OnTouchListener() {

      @Override public boolean onTouch(View v, MotionEvent event) {
        //return MotionEvent.ACTION_MOVE == event.getAction() ? true
        //        : false;
        return MotionEvent.ACTION_MOVE == event.getAction();
      }
    });

    gridviewLoupanImg.getViewTreeObserver()
        .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

          @SuppressLint("NewApi") @Override public void onGlobalLayout() {

            int height = gridviewLoupanImg.getMeasuredHeight();

            if (height != 0) {
              gridviewLoupanImg.setLayoutParams(
                  new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                      LinearLayout.LayoutParams.WRAP_CONTENT));

              gridviewLoupanImg.requestLayout();
              if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
                gridviewLoupanImg.getViewTreeObserver().removeOnGlobalLayoutListener(this);
              } else {
                gridviewLoupanImg.getViewTreeObserver().removeGlobalOnLayoutListener(this);
              }
            }
          }
        });

    gridviewLoupanImg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        final ImgAdapter adapter = (ImgAdapter) gridviewLoupanImg.getAdapter();
        Observable.zip(adapter.getImgUrls(), getGridViewItemPoation(gridviewLoupanImg),
            new Func2<List<String>, List<int[]>, SmallImgModel>() {
              @Override public SmallImgModel call(List<String> strings, List<int[]> localPostions) {
                return SmallImgModel.create(position, strings.toArray(new String[strings.size()]),
                    localPostions, adapter.getViewSize());
              }
            }).subscribe(new Action1<SmallImgModel>() {
          @Override public void call(SmallImgModel smallImgModel) {
            BusProvider.getInstance().post(smallImgModel);
          }
        });
      }
    });
  }

  private Observable<List<int[]>> getGridViewItemPoation(GridView gridView) {
    return Observable.range(0, gridView.getChildCount()).map(new Func1<Integer, int[]>() {
      @Override public int[] call(Integer integer) {
        int[] locations1 = new int[2];
        gridviewLoupanImg.getChildAt(integer).getLocationOnScreen(locations1);
        Log.i(TAG, "call: " + locations1.toString());
        return locations1;
      }
    }).toList();
  }

  private void initZhenShuGridView() {
    gridviewZhenshuImg.setOnTouchListener(new View.OnTouchListener() {

      @Override public boolean onTouch(View v, MotionEvent event) {
        //return MotionEvent.ACTION_MOVE == event.getAction() ? true
        //        : false;
        return MotionEvent.ACTION_MOVE == event.getAction();
      }
    });

    gridviewZhenshuImg.getViewTreeObserver()
        .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

          @SuppressLint("NewApi") @Override public void onGlobalLayout() {

            int height = gridviewZhenshuImg.getMeasuredHeight();

            if (height != 0) {
              gridviewLoupanImg.setLayoutParams(
                  new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                      LinearLayout.LayoutParams.WRAP_CONTENT));

              gridviewZhenshuImg.requestLayout();
              if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
                gridviewZhenshuImg.getViewTreeObserver().removeOnGlobalLayoutListener(this);
              } else {
                gridviewZhenshuImg.getViewTreeObserver().removeGlobalOnLayoutListener(this);
              }
            }
          }
        });
  }

  private void showBuyDataDialog() {
    DialogFactory.createDatePickerDialog(this.getActivity(), Calendar.getInstance(),
        new DatePickerDialog.OnDateSetListener() {
          @Override
          public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            buyDate.setText(year + "年" + monthOfYear + "月" + dayOfMonth + "日");
          }
        }).show();
  }

  static class ImgAdapter extends BaseAdapter {

    private final Context activity;

    private List<JLoupanProductDetailResult.Image> images;

    int[] imgSize = new int[2];

    public ImgAdapter(Activity activity, List<JLoupanProductDetailResult.Image> images) {
      this.activity = activity;
      this.images = images;

      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      int mScreenWidth = localDisplayMetrics.widthPixels;
      int pacing = ViewUtil.dpToPx(8);
      int width = (mScreenWidth - 2 * pacing - ViewUtil.dpToPx(4)) / 2;

      imgSize[0] = width;
      imgSize[1] = width * 9 / 16;
    }

    @Override public int getCount() {
      return images.size();
    }

    @Override public Object getItem(int position) {
      return images.get(position);
    }

    @Override public long getItemId(int position) {
      return position;
    }

    public Observable<List<String>> getImgUrls() {
      return Observable.from(images).map(new Func1<JLoupanProductDetailResult.Image, String>() {
        @Override public String call(JLoupanProductDetailResult.Image image) {
          return image.getUrl();
        }
      }).toList();
    }

    private int[] getViewSize() {
      return imgSize;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {

      NetImageHolderView holder = new NetImageHolderView();

      convertView = holder.createView(activity);

      convertView.setLayoutParams(new LinearLayout.LayoutParams(imgSize[0], imgSize[1]));

      holder.UpdateUI(activity, position, AiShangService.IMG_URL + images.get(position).getUrl());
      return convertView;
    }
  }
}
