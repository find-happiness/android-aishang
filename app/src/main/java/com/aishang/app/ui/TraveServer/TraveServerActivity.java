package com.aishang.app.ui.TraveServer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.aishang.app.R;
import com.aishang.app.util.CommonUtil;

public class TraveServerActivity extends AppCompatActivity {

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.building) ImageView building;
  @Bind(R.id.btn_back) Button btnBack;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_trave_server);
    ButterKnife.bind(this);
    initToolbar();
    initBuildingView();
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

  private void initBuildingView(){

    //BitmapDescriptor bd = BitmapDescriptorFactory.fromResource(R.mipmap.building);

    int[] size = CommonUtil.getHeightWithScreenWidth(this,640,549);

    building.setLayoutParams(new RelativeLayout.LayoutParams(size[0],size[1]));

  }

  @OnClick(R.id.btn_back) public void onClick() {
    onBackPressed();
  }
}
