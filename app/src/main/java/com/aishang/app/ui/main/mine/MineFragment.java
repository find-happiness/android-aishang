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
}
