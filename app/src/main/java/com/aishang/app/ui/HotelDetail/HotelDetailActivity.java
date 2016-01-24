package com.aishang.app.ui.HotelDetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.aishang.app.R;
import com.aishang.app.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HotelDetailActivity extends BaseActivity {

    @Inject
    HotelDetailPresenter presenter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getActivityComponent().inject(this);
        setContentView(R.layout.activity_hotel_detail);
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        toolbar.setTitle("");
        this.setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.mipmap.iconfont_livesvg);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}