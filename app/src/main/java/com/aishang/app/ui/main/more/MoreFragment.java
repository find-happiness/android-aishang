package com.aishang.app.ui.main.more;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aishang.app.R;
import com.aishang.app.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by song on 2016/1/16.
 */
public class MoreFragment extends Fragment implements MoreMvpView{

    @Inject
    MorePresenter morePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity)this.getActivity()).getActivityComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_more,null);
        morePresenter.attachView(this);
        ButterKnife.bind(this, view);
        return  view;
    }

    @OnClick(R.id.btn_check_updata)
    void checkUpdataClick()
    {

    }

    @OnClick(R.id.btn_suggestion)
    void suggestionClick()
    {
        morePresenter.intentToSuggestion();
    }
    @OnClick(R.id.btn_setting)
    void settingClcik()
    {
        morePresenter.intentToSetting();
    }

    @OnClick(R.id.btn_share)
    void shareClick()
    {

    }
}
