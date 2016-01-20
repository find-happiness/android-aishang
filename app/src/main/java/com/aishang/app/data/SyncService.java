package com.aishang.app.data;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;

import android.util.Log;
import com.aishang.app.data.model.JMrePromResult;
import com.aishang.app.data.model.JVersionCheckParam;
import com.aishang.app.data.model.JVersionCheckResult;
import com.aishang.app.util.CommonUtil;
import com.google.gson.Gson;
import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.schedulers.Schedulers;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.data.model.Ribot;
import com.aishang.app.util.AndroidComponentUtil;
import com.aishang.app.util.NetworkUtil;

public class SyncService extends Service {

    private static final String TAG = SyncService.class.getSimpleName();

    @Inject DataManager mDataManager;
    private Subscription mSubscription;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SyncService.class);
    }

    public static boolean isRunning(Context context) {
        return AndroidComponentUtil.isServiceRunning(context, SyncService.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BoilerplateApplication.get(this).getComponent().inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        Log.i(TAG,"Starting sync...");

        if (!NetworkUtil.isNetworkConnected(this)) {
            Log.i(TAG,"Sync canceled, connection not available");
            AndroidComponentUtil.toggleComponent(this, SyncOnConnectionAvailable.class, true);
            stopSelf(startId);
            return START_NOT_STICKY;
        }

        if (mSubscription != null && !mSubscription.isUnsubscribed()) mSubscription.unsubscribe();

        JVersionCheckParam param = new JVersionCheckParam();
        param.setPlatform("android");
        param.setScreen("phone");
        param.setVersion(""+CommonUtil.getVersionCode(this));

        mSubscription = mDataManager.syncVersionCheck(1,new Gson().toJson(param))
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<JVersionCheckResult>() {
                    @Override public void onCompleted() {
                        //Log.i(TAG,"sync onCompleted...");
                        stopSelf();
                    }

                    @Override public void onError(Throwable e) {
                        //Log.i(TAG,"sync onError..." + e.toString());
                        stopSelf();
                    }

                    @Override public void onNext(JVersionCheckResult jVersionCheckResult) {
                        //Log.i(TAG,"sync onNext..." + jVersionCheckResult.toString());
                    }
                });

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mSubscription != null) mSubscription.unsubscribe();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static class SyncOnConnectionAvailable extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)
                    && NetworkUtil.isNetworkConnected(context)) {
                Log.i(TAG,"Connection is now available, triggering sync...");
                AndroidComponentUtil.toggleComponent(context, this.getClass(), false);
                context.startService(getStartIntent(context));
            }
        }
    }
}