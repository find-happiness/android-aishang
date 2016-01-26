package com.aishang.app.data.remote;

import com.aishang.app.data.model.JHotelListResult;
import com.aishang.app.data.model.JLoupanProductListParam;
import com.aishang.app.data.model.JLoupanProductListResult;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.data.model.JMrePromResult;
import com.aishang.app.data.model.JVersionCheckResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;
import com.aishang.app.data.model.Ribot;

public interface AiShangService {

  String ENDPOINT = "https://api.ribot.io/";
  String AiShangHost = "http://www.aishang67.com/";

  @Headers("connection:Keep-Alive")
  @GET("mobile/member/memberLogin.ashx") Observable<JMemberLoginResult> login(
      @Query(value = "v") int version, @Query(value = "q") String q);

  @Headers("connection:Keep-Alive")
  @GET("mobile/other/mrePromList.ashx") Observable<JMrePromResult> mreProm(
      @Query(value = "v") int version, @Query(value = "q") String q);

  @Headers("connection:Keep-Alive")
  @GET("mobile/versionCheck.ashx") Observable<JVersionCheckResult> versionCheck(
      @Query(value = "v") int version, @Query(value = "q") String q);

  @Headers("connection:Keep-Alive")
  @GET("mobile/loupan/loupanProductList.ashx") Observable<JLoupanProductListResult> loupanList(
      @Query(value = "v") int version, @Query(value = "q") String q);

  @Headers("connection:Keep-Alive")
  @GET("mobile/hotel/hotelList.ashx") Observable<JHotelListResult> hotelList(
      @Query(value = "v") int version, @Query(value = "q") String q);

  @GET("ribots") Observable<List<Ribot>> getRibots();

  /******** Helper class that sets up a new services *******/
  class Creator {

    public static AiShangService newAiShangService() {
      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

      HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
      // set your desired log level
      logging.setLevel(HttpLoggingInterceptor.Level.BODY);

      OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
      // add your other interceptors …

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
