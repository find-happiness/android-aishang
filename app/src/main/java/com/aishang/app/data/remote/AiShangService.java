package com.aishang.app.data.remote;

import com.aishang.app.data.model.JBusinessListResult;
import com.aishang.app.data.model.JHotelDetailResult;
import com.aishang.app.data.model.JHotelListResult;
import com.aishang.app.data.model.JHotelPriceCatListResult;
import com.aishang.app.data.model.JLoupanPriceCatListResult;
import com.aishang.app.data.model.JLoupanProductCatListResult;
import com.aishang.app.data.model.JLoupanProductDetailResult;
import com.aishang.app.data.model.JLoupanProductListResult;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.data.model.JMemberProfileResult;
import com.aishang.app.data.model.JMemberStatisticsResult;
import com.aishang.app.data.model.JMrePromResult;
import com.aishang.app.data.model.JMyBusinessBuyInListResult;
import com.aishang.app.data.model.JMyVacationApplyListResult;
import com.aishang.app.data.model.JNewsListResult;
import com.aishang.app.data.model.JRentalListResult;
import com.aishang.app.data.model.JResult;
import com.aishang.app.data.model.JSysZoneResult;
import com.aishang.app.data.model.JVersionCheckResult;
import com.aishang.app.data.model.Ribot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

public interface AiShangService {

  String ENDPOINT = "https://api.ribot.io/";
  String AiShangHost = "http://www.aishang67.cn:88/";
  String IMG_URL = "http://www.aishang67.cn";

  @Headers("connection:Keep-Alive") @GET("mobile/member/memberLogin.ashx")
  Observable<JMemberLoginResult> login(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/memberLogin.ashx")
  Observable<String> login2(@Query(value = "v") int version, @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/other/mrePromList.ashx")
  Observable<JMrePromResult> mreProm(@Query(value = "v") int version, @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/versionCheck.ashx")
  Observable<JVersionCheckResult> versionCheck(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/loupan/loupanProductList.ashx")
  Observable<JLoupanProductListResult> loupanList(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/hotel/hotelList.ashx")
  Observable<JHotelListResult> hotelList(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/NewsList.ashx")
  Observable<JNewsListResult> newsList(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/sysZone.ashx") Observable<JSysZoneResult> sysZone(
      @Query(value = "v") int version, @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/memberProfile.ashx")
  Observable<JMemberProfileResult> memberProfile(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/memberStatistics.ashx.ashx")
  Observable<JMemberStatisticsResult> memberStatistics(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/hotel/hotelPriceCatList.ashx")
  Observable<JHotelPriceCatListResult> hotelPriceCatList(@Query(value = "v") int version);

  @Headers("connection:Keep-Alive") @GET("mobile/hotel/hotelDetail.ashx")
  Observable<JHotelDetailResult> hotelDetail(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/loupan/loupanPriceCatList.ashx")
  Observable<JLoupanPriceCatListResult> loupanPriceCatList(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/loupan/loupanProductCatList.ashx")
  Observable<JLoupanProductCatListResult> loupanProductCatList(@Query(value = "v") int version);

  @Headers("connection:Keep-Alive") @GET("mobile/loupan/loupanProductDetail.ashx")
  Observable<JLoupanProductDetailResult> loupanProductDetail(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/myVacationApplyList.ashx")
  Observable<JMyVacationApplyListResult> sysMyVacationApplyList(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/businessList.ashx")
  Observable<JBusinessListResult> sysBusinessList(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/contactsAdd.ashx")
  Observable<JResult> sysContactsAdd(@Query(value = "v") int version, @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/myBusinessBuyInList.ashx")
  Observable<JMyBusinessBuyInListResult> sysMyBusinessBuyInList(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/cashWithDrawApply.ashx")
  Observable<JResult> sysCashWithDrawApply(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/rentalList.ashx")
  Observable<JRentalListResult> syncRentalList(@Query(value = "v") int version,
      @Query(value = "q") String q);


  @GET("ribots") Observable<List<Ribot>> getRibots();

  /******** Helper class that sets up a new services *******/
  class Creator {

    public static AiShangService newAiShangService() {
      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

      HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
      // set your desired log level
      logging.setLevel(HttpLoggingInterceptor.Level.BODY);

      OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

      // add logging as last interceptor
      httpClient.interceptors().add(logging);  // <-- this is the important line!

      Retrofit retrofit = new Retrofit.Builder().baseUrl(AiShangService.AiShangHost)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
          .client(httpClient.build())
          .build();

      return retrofit.create(AiShangService.class);
    }
  }
}
