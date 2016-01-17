package com.aishang.app.ui.main.more;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aishang.app.R;

/**
 * Created by song on 2016/1/16.
 */
public class MoreFragment extends Fragment implements MoreMvpView{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_more,null);
    }
}
