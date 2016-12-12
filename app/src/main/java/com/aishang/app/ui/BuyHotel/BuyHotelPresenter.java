package com.aishang.app.ui.BuyHotel;

import android.util.Log;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.AlipayPreModel;
import com.aishang.app.data.model.JHotelRoomPriceResult;
import com.aishang.app.data.model.JMemberProfileResult;
import com.aishang.app.data.model.JMyVacationApplyResult;
import com.aishang.app.data.model.JMyVacationListResult;
import com.aishang.app.data.model.JResult;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.util.Constants;
import com.aishang.app.util.OrderInfoUtil2_0;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;
import java.net.SocketTimeoutException;
import java.util.Map;
import javax.inject.Inject;
import okhttp3.Call;
import okhttp3.Response;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by song on 2016/2/15.
 */
public class BuyHotelPresenter extends BasePresenter<BuyHotelMvpView> {

  private static final String TAG = "BuyHotelPresenter";
  private final DataManager mDataManager;
  private Subscription subscription;
  private Subscription subscriptionProfile;
  private Subscription subscriptionVacation;
  private Subscription subscriptionHotelPrice;
  private Subscription subscriptionAliSign;

  @Inject public BuyHotelPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(BuyHotelMvpView mvpView) {
    super.attachView(mvpView);
  }

  public void postData(int version, String json) {
    postData(false, version, json);
  }

  public void postData(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (subscription != null && !subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }

    subscription = mDataManager.syncMyVacationApply(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JMyVacationApplyResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e.toString());
            //getMvpView().showError("网络异常");
          }

          @Override public void onNext(JMyVacationApplyResult result) {

            getMvpView().showBuyResult(result);

            //if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
            //
            //} else {
            //  getMvpView().showError(result.getResult());
            //}
          }
        });
  }

  public void loadProfile(int version, String json) {
    checkViewAttached();

    if (subscriptionProfile != null && !subscriptionProfile.isUnsubscribed()) {
      subscriptionProfile.unsubscribe();
    }

    subscriptionProfile = mDataManager.sysMemberProfile(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JMemberProfileResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e.toString());
            //getMvpView().showError("网络异常");
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

  public void loadHotelPrice(int version, String json) {
    checkViewAttached();

    if (subscriptionHotelPrice != null && !subscriptionHotelPrice.isUnsubscribed()) {
      subscriptionHotelPrice.unsubscribe();
    }

    subscriptionHotelPrice = mDataManager.syncHotelPrice(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JHotelRoomPriceResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e.toString());
            //getMvpView().showError("网络异常");
          }

          @Override public void onNext(JHotelRoomPriceResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().showHotelRoomPrice(result);
            } else {
              getMvpView().showError(result.getResult());
            }
          }
        });
  }

  public void loadVacation(int version, String json) {
    checkViewAttached();

    if (subscriptionVacation != null && !subscriptionVacation.isUnsubscribed()) {
      subscriptionVacation.unsubscribe();
    }

    subscriptionVacation = mDataManager.syncMyVacationList(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JMyVacationListResult>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            //getMvpView().showError("网络异常");

            Log.e(TAG, "onError: " + e.toString());
          }

          @Override public void onNext(JMyVacationListResult result) {

            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {

              if (result.getMyVaList().length > 0) {
                getMvpView().showGetVacationSuccess(result);
              } else {
                getMvpView().showVacationEmpty();
              }
            } else {
              getMvpView().showError(result.getResult());
            }
          }
        });
  }

  public void alipaySign(String seller_id, String partner, final String out_trade_no,
      String subject, String body, String total_fee, String notify_url, String service,
      String payment_type, String _input_charset, String it_b_pay) {//, String sign_type
    ////checkViewAttached();
    //
    //if (subscriptionAliSign != null && !subscriptionAliSign.isUnsubscribed()) {
    //  subscriptionAliSign.unsubscribe();
    //}
    //
    //subscriptionAliSign =
    //    mDataManager.syncAlipayModel(seller_id, partner, out_trade_no, subject, body, total_fee,
    //        notify_url, service, payment_type, _input_charset, it_b_pay, sign_type)
    //        .observeOn(AndroidSchedulers.mainThread())
    //        .subscribeOn(Schedulers.io())
    //        .subscribe(new Subscriber<String>() {
    //          @Override public void onCompleted() {
    //          }
    //
    //          @Override public void onError(Throwable e) {
    //            //getMvpView().showError("网络异常");
    //
    //            Log.e(TAG, "onError: " + e.toString());
    //          }
    //
    //          @Override public void onNext(String result) {
    //            //Log.e(TAG, "onNext: " + new Gson().toJson(result));
    //            getMvpView().alipaySign(result);
    //          }
    //        });

    OkHttpUtils.post()
        .url("http://www.51triplife.com/IosAlipay/signatures_url.aspx")
        .addParams("seller_id", seller_id)
        .addParams("partner", partner)
        .addParams("out_trade_no", out_trade_no)
        .addParams("subject", subject)
        .addParams("body", body)
        .addParams("total_fee", total_fee)
        .addParams("notify_url", notify_url)
        .addParams("service", service)
        .addParams("payment_type", payment_type)
        .addParams("_input_charset", _input_charset)
        .addParams("it_b_pay", it_b_pay)
        //.addParams("sign_type", sign_type)
        .build()
        .execute(new StringCallback() {
          @Override public void onError(Call call, Exception e, int id) {
            Log.e(TAG, "onError: " + e.toString());

            if (e instanceof SocketTimeoutException) {
              getMvpView().showError("链接超时！");
            }
          }

          @Override public void onResponse(String response, int id) {
            getMvpView().alipaySign(response);
          }
        });
  }

  @Override public void detachView() {
    super.detachView();
    if (subscription != null) subscription.unsubscribe();
    if (subscriptionProfile != null) subscriptionProfile.unsubscribe();
    if (subscriptionVacation != null) subscriptionVacation.unsubscribe();
    if (subscriptionHotelPrice != null) subscriptionHotelPrice.unsubscribe();
    if (subscriptionAliSign != null) subscriptionAliSign.unsubscribe();
  }
}
