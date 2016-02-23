package com.aishang.app.data;

import android.util.Log;
import com.aishang.app.data.model.JBusinessListResult;
import com.aishang.app.data.model.JHotelDetailResult;
import com.aishang.app.data.model.JHotelListResult;
import com.aishang.app.data.model.JHotelPriceCatListResult;
import com.aishang.app.data.model.JLoupanPriceCatListResult;
import com.aishang.app.data.model.JLoupanProductCatListResult;
import com.aishang.app.data.model.JLoupanProductDetailResult;
import com.aishang.app.data.model.JLoupanProductListResult;
import com.aishang.app.data.model.JMemberBankListResult;
import com.aishang.app.data.model.JMemberImgEditResult;
import com.aishang.app.data.model.JMemberProfileEditResult;
import com.aishang.app.data.model.JMemberProfileResult;
import com.aishang.app.data.model.JMemberStatisticsResult;
import com.aishang.app.data.model.JMreActivityDetailResult;
import com.aishang.app.data.model.JMreActivityListResult;
import com.aishang.app.data.model.JMyBusinessBuyInListResult;
import com.aishang.app.data.model.JMyVacationApplyListResult;
import com.aishang.app.data.model.JRentalListResult;
import com.aishang.app.data.model.JResult;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.data.model.JMrePromResult;
import com.aishang.app.data.model.JNewsListResult;
import com.aishang.app.data.model.JSysZoneResult;
import com.aishang.app.data.model.JUploadFileResult;
import com.aishang.app.data.model.JVersionCheckResult;
import com.aishang.app.data.remote.AiShangService;
import com.google.gson.Gson;
import java.util.List;

import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.RequestBody;
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
    //Log.i(TAG, "syncLogin: v=" + version + "&q=" +json);
    return mAiShangService.login(version, json);
  }

  public Observable<String> syncLogin2(int version, String json) {
    //Log.i(TAG, "syncLogin: v=" + version + "&q=" +json);
    return mAiShangService.login2(version, json);
  }

  public Observable<JMrePromResult> syncProm(int version, String json) {
    return mAiShangService.mreProm(version, json);
  }

  public Observable<JVersionCheckResult> syncVersionCheck(int version, String json) {
    return mAiShangService.versionCheck(version, json).doOnNext(new Action1<JVersionCheckResult>() {
      @Override public void call(JVersionCheckResult jVersionCheckResult) {
        //Log.i(TAG, "call: " + jVersionCheckResult.toString());
        mPreferencesHelper.setVersionCheck(new Gson().toJson(jVersionCheckResult));
      }
    });
  }

  public Observable<JLoupanProductListResult> syncLoupanList(int version, String json) {
    return mAiShangService.loupanList(version, json);
  }

  public Observable<JHotelListResult> syncHotelList(int version, String json) {
    return mAiShangService.hotelList(version, json);
  }

  public Observable<JNewsListResult> syncTravel(int version, String json) {
    return mAiShangService.newsList(version, json);
  }

  public Observable<JMrePromResult> syncMreProm(int version, String json) {
    //Log.i(TAG, "syncMreProm: " + "?v=" + version+"&q="+ json);
    return mAiShangService.mreProm(version, json);
  }

  public Observable<JSysZoneResult> sysZone(int version, String json) {
    return mAiShangService.sysZone(version, json);
  }

  public Observable<JMemberProfileResult> sysMemberProfile(int version, String json) {
    return mAiShangService.memberProfile(version, json);
  }

  public Observable<JMemberStatisticsResult> syncMemberStatistics(int version, String json) {
    return mAiShangService.memberStatistics(version, json);
  }

  public Observable<JHotelDetailResult> syncHotelDetail(int version, String json) {
    return mAiShangService.hotelDetail(version, json);
  }

  public Observable<JHotelPriceCatListResult> syncHotelPriceCatList(int version) {
    return mAiShangService.hotelPriceCatList(version);
  }

  public Observable<JLoupanPriceCatListResult> syncLoupanPriceCatList(int version, String json) {
    return mAiShangService.loupanPriceCatList(version, json);
  }

  public Observable<JLoupanProductCatListResult> syncLoupanProductCatList(int version) {
    return mAiShangService.loupanProductCatList(version);
  }

  public Observable<JLoupanProductDetailResult> syncLoupanProductDetail(int version, String json) {
    return mAiShangService.loupanProductDetail(version, json);
  }

  public Observable<JMyVacationApplyListResult> sysMyVacationApplyList(int version, String json) {
    return mAiShangService.sysMyVacationApplyList(version, json);
  }

  public Observable<JBusinessListResult> sysBusinessList(int version, String json) {
    return mAiShangService.sysBusinessList(version, json);
  }

  public Observable<JMyBusinessBuyInListResult> sysMyBusinessBuyInList(int version, String json) {
    return mAiShangService.sysMyBusinessBuyInList(version, json);
  }

  public Observable<JResult> sysContactsAdd(int version, String json) {
    return mAiShangService.sysContactsAdd(version, json);
  }

  public Observable<JResult> sysCashWithDrawApply(int version, String json) {
    return mAiShangService.sysCashWithDrawApply(version, json);
  }

  public Observable<JRentalListResult> syncRentalList(int version, String json) {
    return mAiShangService.syncRentalList(version, json);
  }

  public Observable<JMreActivityListResult> syncMreActivityList(int version, String json) {
    return mAiShangService.syncMreActivityList(version, json);
  }

  public Observable<JMreActivityDetailResult> syncMreActivityDetail(int version, String json) {
    return mAiShangService.syncMreActivityDetail(version, json);
  }

  public Observable<JResult> syncMreActivityEnroll(int version, String json) {
    return mAiShangService.syncMreActivityEnroll(version, json);
  }

  public Observable<JResult> syncProjecCtooperation(int version, String json) {
    return mAiShangService.syncProjecCtooperation(version, json);
  }

  public Observable<JResult> syncSubscription(int version, String json) {
    return mAiShangService.syncSubscription(version, json);
  }

  public Observable<JResult> syncPasswordChange(int version, String json) {
    return mAiShangService.syncPasswordChange(version, json);
  }

  public Observable<JMemberProfileEditResult> syncMemberProfileEdit(int version, String json) {
    return mAiShangService.memberProfileEdit(version, json);
  }

  public Observable<JMemberProfileEditResult> syncMemberProfileBasicEdit(int version, String json) {
    return mAiShangService.memberProfileBasicEdit(version, json);
  }

  public Observable<JUploadFileResult> syncFileUpload(Map<String, RequestBody> body) {
    return mAiShangService.uploadFile(body);
  }

  public Observable<JMemberImgEditResult> syncUploadMemberImg(Map<String, RequestBody> body) {
    return mAiShangService.syncUploadMemberImg(body);
  }

  public Observable<JMemberBankListResult> syncBankList(int version, String json) {
    return mAiShangService.syncBankList(version, json);
  }

  public Observable<JMemberBankListResult> syncBankEdit(int version, String json) {
    return mAiShangService.syncBankEdit(version, json);
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
