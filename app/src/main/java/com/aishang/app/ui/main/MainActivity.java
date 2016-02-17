package com.aishang.app.ui.main;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.aishang.app.R;
import com.aishang.app.data.SyncService;
import com.aishang.app.data.model.Ribot;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AndroidComponentUtil;
import com.aishang.app.util.Constants;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.DownloadService;
import com.aishang.app.util.NetworkUtil;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.viewpager.SViewPager;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView {
    private static final String TAG = "MainActivity";
    private static final String EXTRA_TRIGGER_SYNC_FLAG =
            "com.aishang.app.ui.main.MainActivity.EXTRA_TRIGGER_SYNC_FLAG";
    private IndicatorViewPager indicatorViewPager;
    @Inject
    MainPresenter mMainPresenter;

    @Bind(R.id.tabmain_viewPager)
    SViewPager mViewPager;
    @Bind(R.id.tabmain_indicator)
    FixedIndicatorView mIndicator;

    /**
     * Return an Intent to start this Activity.
     * triggerDataSyncOnCreate allows disabling the background sync service onCreate. Should
     * only be set to false during testing.
     */
    public static Intent getStartIntent(Context context, boolean triggerDataSyncOnCreate) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_TRIGGER_SYNC_FLAG, triggerDataSyncOnCreate);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        indicatorViewPager = new IndicatorViewPager(mIndicator, mViewPager);
        indicatorViewPager.setAdapter(new MainPageAdapter(getSupportFragmentManager(), this));
        indicatorViewPager.setPageOffscreenLimit(3);
        mViewPager.setCanScroll(true);
        mMainPresenter.attachView(this);

        if (getIntent().getBooleanExtra(EXTRA_TRIGGER_SYNC_FLAG, true)) {
            startService(SyncService.getStartIntent(this));
        }

        if (NetworkUtil.isNetworkConnected(this) && !AndroidComponentUtil.isServiceRunning(this, DownloadService.class)) {
            mMainPresenter.loadVersionCheck();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMainPresenter.detachView();
    }

    /*****
     * MVP View methods implementation
     *****/
    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_ribots)).show();
    }

    @Override
    public void showRibots(List<Ribot> ribots) {

    }

    @Override
    public void showRibotsEmpty() {

    }

    @Override
    public void upData(final String apkUrl) {

        DialogFactory.createSimpleDialog(this, R.string.newUpdateAvailable,
                R.string.newUpdateAvailable, R.string.updata_now, R.string.updata_next,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(MainActivity.this, DownloadService.class);
                        intent.putExtra(Constants.APK_DOWNLOAD_URL, apkUrl);
                        MainActivity.this.startService(intent);
                    }
                }, null).show();
    }
}
