package com.aishang.app.data;

import android.util.Log;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.data.model.JMrePromResult;
import com.aishang.app.data.model.JVersionCheckResult;
import com.aishang.app.data.remote.AiShangService;
import com.google.gson.Gson;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import com.aishang.app.data.local.DatabaseHelper;
import com.aishang.app.data.local.PreferencesHelper;
import com.aishang.app.data.model.Ribot;
import com.aishang.app.util.EventPosterHelper;

@Singleton public class DataManager {
  private static final String TAG = "DataManager";
  private final AiShangService mAiShangService;
  private final DatabaseHelper mDatabaseHelper;
  private final PreferencesHelper mPreferencesHelper;
  private final EventPosterHelper mEventPoster;

  @Inject public DataManager(AiShangService aiShangService, PreferencesHelper preferencesHelper,
      DatabaseHelper databaseHelper, EventPosterHelper eventPosterHelper) {
    mAiShangService = aiShangService;
    mPreferencesHelper = preferencesHelper;
    mDatabaseHelper = databaseHelper;
    mEventPoster = eventPosterHelper;
  }

  public PreferencesHelper getPreferencesHelper() {
    return mPreferencesHelper;
  }

  public Observable<Ribot> syncRibots() {
    return mAiShangService.getRibots().concatMap(new Func1<List<Ribot>, Observable<Ribot>>() {
      @Override public Observable<Ribot> call(List<Ribot> ribots) {
        return mDatabaseHelper.setRibots(ribots);
      }
    });
  }

  public Observable<List<Ribot>> getRibots() {
    return mDatabaseHelper.getRibots().distinct();
  }

  /// Helper method to post events from doOnCompleted.
  private Action0 postEventAction(final Object event) {
    return new Action0() {
      @Override public void call() {
        mEventPoster.postEventSafely(event);
      }
    };
  }

  public Observable<JMemberLoginResult> syncLogin(int version, String json) {
    return mAiShangService.login(version, json);
  }

  public Observable<JMrePromResult> syncProm(int version, String json) {
    return mAiShangService.mreProm(version, json);
  }

  public Observable<JVersionCheckResult> syncVersionCheck(int version, String json) {
    return mAiShangService.versionCheck(version, json).doOnNext(new Action1<JVersionCheckResult>() {
      @Override public void call(JVersionCheckResult jVersionCheckResult) {
        Log.i(TAG, "call: "+jVersionCheckResult.toString());
        mPreferencesHelper.setVersionCheck(new Gson().toJson(jVersionCheckResult));
      }
    });
  }

  public JVersionCheckResult getVersionCheck() {
    String json = mPreferencesHelper.getVersionCheck();

    Log.i(TAG, "getVersionCheck: " + json);

    if (json != null && !json.equals("")) {
      return new Gson().fromJson(json, JVersionCheckResult.class);
    }
    return null;
  }
}
