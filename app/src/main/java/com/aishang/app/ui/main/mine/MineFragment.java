package com.aishang.app.ui.main.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aishang.app.R;
import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by song on 2016/1/16.
 */
public class MineFragment extends Fragment implements MineMvpView {
    @Inject
    MinePresenter mMinePresenter;

    @Bind(R.id.scroll_view)
    PullToZoomScrollViewEx scrollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_mine, null);
        ButterKnife.bind(this, view);
        initScrollView();
        return view;
    }

    private void initScrollView() {
        View headView = LayoutInflater.from(this.getContext()).inflate(R.layout.profile_head_view, null, false);
        View zoomView = LayoutInflater.from(this.getContext()).inflate(R.layout.profile_zoom_view, null, false);
        View contentView = LayoutInflater.from(this.getContext()).inflate(R.layout.profile_content_view, null, false);

        ViewHold hold = new ViewHold(contentView);

        //ButterKnife.bind(this,contentView);
        //ButterKnife.bind(this,zoomView);

        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);

        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        scrollView.setHeaderLayoutParams(localObject);
    }

    class ViewHold{
        @OnClick(R.id.btn_gen_ren_zhong_xin)
        void geRenZhongXinClick()
        {

        }
        @OnClick(R.id.btn_zhang_hu_zhong_xin)
        void zhangHuZhongXinClick()
        {

        }
        @OnClick(R.id.btn_wo_de_ding_dang)
        void woDeDingDanClick()
        {

        }
        @OnClick(R.id.btn_wo_de_fang_chang)
        void woDeFangChangClick()
        {

        }
        @OnClick(R.id.btn_jin_ji_ren_zhong_xin)
        void jinJiRenZhongXinClick()
        {

        }
        @OnClick(R.id.btn_you_ji_shou_chang_jia)
        void youJiShouChangJiaClick()
        {

        }
        @OnClick(R.id.btn_chong_zi_ti_xian)
        void chongZiTiXianClick()
        {

        }
        @OnClick(R.id.btn_wo_de_zu_shou)
        void woDeZuShouClick()
        {

        }
        @OnClick(R.id.btn_guan_yu_wo_men)
        void guanYuWoMenClick()
        {

        }

        public ViewHold(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
