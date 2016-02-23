package com.aishang.app.ui.MemberCenter;

import android.util.Log;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JMemberImgEditResult;
import com.aishang.app.data.model.JMemberProfileEditResult;
import com.aishang.app.data.model.JMemberProfileResult;
import com.aishang.app.data.model.JUploadFileResult;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.util.Constants;
import java.io.File;
import java.util.HashMap;
import javax.inject.Inject;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by song on 2016/2/15.
 */
public class MemberCenterPresenter extends BasePresenter<MemberCenterMvpView> {

  private static final String TAG = "MemberCenterPresenter";
  private final DataManager mDataManager;
  private Subscription subscription;
  private Subscription subscriptionEdit;
  private Subscription subscriptionImg;

  @Inject public MemberCenterPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(MemberCenterMvpView mvpView) {
    super.attachView(mvpView);
  }

  public void loadProfile(int version, String json) {
    loadProfile(false, version, json);
  }

  public void postProfileEdit(int version, String json) {
    postProfileEdit(false, version, json);
  }

  public void postProfileEdit(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (subscriptionEdit != null && !subscriptionEdit.isUnsubscribed()) {
      subscriptionEdit.unsubscribe();
    }

    subscriptionEdit = mDataManager.syncMemberProfileBasicEdit(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JMemberProfileEditResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常");
          }

          @Override public void onNext(JMemberProfileEditResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().postSuccess(result);
            } else {
              getMvpView().showError(result.getResult());
            }
          }
        });
  }

  public void loadProfile(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (subscription != null && !subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }

    subscription = mDataManager.sysMemberProfile(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JMemberProfileResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常");
          }

          @Override public void onNext(JMemberProfileResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().showGetProfileSuccess(result);
            } else {
              getMvpView().showError(result.getResult());
            }
          }
        });
  }

  public void postImg(int version, String json, File file) {
    postImg(false, version, json, file);
  }

  public void postImg(boolean allowMemoryCacheVersion, int version, String json, File file) {
    checkViewAttached();

    if (subscriptionImg != null && !subscriptionImg.isUnsubscribed()) {
      subscriptionImg.unsubscribe();
    }
    Log.i(TAG, "postData: " + file.getPath() + "   " + file.length());
    HashMap<String, RequestBody> map = new HashMap<>();

    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
    RequestBody jsonBody = RequestBody.create(MediaType.parse("application/json"), json);
    map.put("q", jsonBody);
    map.put("sltpic", requestBody);
    map.put("v", RequestBody.create(MediaType.parse("text/plain"), version + ""));

    subscriptionImg = mDataManager.syncUploadMemberImg(map)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JMemberImgEditResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e.toString());
            getMvpView().showUploadFileError("网络异常");
          }

          @Override public void onNext(JMemberImgEditResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().showUploadFileSuccess();
            } else {
              getMvpView().showUploadFileError(result.getResult());
            }
          }
        });
  }

  @Override public void detachView() {
    super.detachView();

    if (subscription != null) subscription.unsubscribe();
    if (subscriptionEdit != null) subscriptionEdit.unsubscribe();
    if (subscriptionImg != null) subscriptionImg.unsubscribe();
  }
}
