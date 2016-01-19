package com.aishang.app.ui.HotelDetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aishang.app.R;
import com.aishang.app.ui.base.BaseActivity;

import javax.inject.Inject;

public class HotelDetailActivity extends BaseActivity {

    @Inject
    HotelDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getActivityComponent().inject(this);
        setContentView(R.layout.activity_hotel_detail);
    }
}
