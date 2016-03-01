package com.aishang.app.util;

import android.util.Log;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JUploadFileResult;
import com.aishang.app.ui.base.BasePresenter;
import java.io.File;
import java.util.HashMap;
import javax.inject.Inject;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by song on 2016/2/23.
 */
public class FileUploadPresenter extends BasePresenter<FileUploadMvpView> {

  private static final String TAG = "FileUploadPresenter";

  private final DataManager mDataManager;
  private Subscription subscription;

  @Inject public FileUploadPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(FileUploadMvpView mvpView) {
    super.attachView(mvpView);
  }

  public void postData(int version, String json, File file) {
    postData(false, version, json, file);
  }

  public void postData(boolean allowMemoryCacheVersion, int version, String json,
      File file) {
    checkViewAttached();

    if (subscription != null && !subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }
    Log.i(TAG, "postData: " + file.getPath() + "   " + file.length());
    HashMap<String, RequestBody> map = new HashMap<>();

    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
    RequestBody jsonBody = RequestBody.create(MediaType.parse("text/plain"), json);
    map.put("q", jsonBody);
    map.put("fileUpload", requestBody);
    map.put("v", RequestBody.create(MediaType.parse("text/plain"), version + ""));

    subscription = mDataManager.syncFileUpload(map)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JUploadFileResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e.toString());
            getMvpView().showUploadFileError("网络异常");
          }

          @Override public void onNext(JUploadFileResult result) {
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
  }
}
