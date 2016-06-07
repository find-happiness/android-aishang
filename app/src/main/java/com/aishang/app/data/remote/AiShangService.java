package com.aishang.app.data.remote;

import com.aishang.app.data.model.JAwardDetailListV2Result;
import com.aishang.app.data.model.JBusinessListResult;
import com.aishang.app.data.model.JCheckinRecordResult;
import com.aishang.app.data.model.JCodeLoginResult;
import com.aishang.app.data.model.JCollectResult;
import com.aishang.app.data.model.JCommonIntegralResult;
import com.aishang.app.data.model.JCriticismListResult;
import com.aishang.app.data.model.JHotelDetailResult;
import com.aishang.app.data.model.JHotelListResult;
import com.aishang.app.data.model.JHotelPriceCatListResult;
import com.aishang.app.data.model.JHotelRoomCatListByhotelIDResult;
import com.aishang.app.data.model.JHotelRoomCatListResult;
import com.aishang.app.data.model.JHotelRoomFacilitesCatListResult;
import com.aishang.app.data.model.JHotelStarLevelListResult;
import com.aishang.app.data.model.JLoupanPriceCatListResult;
import com.aishang.app.data.model.JLoupanProductCatListResult;
import com.aishang.app.data.model.JLoupanProductDetailResult;
import com.aishang.app.data.model.JLoupanProductListResult;
import com.aishang.app.data.model.JLoupanProductVIPViewResult;
import com.aishang.app.data.model.JMemberBankListResult;
import com.aishang.app.data.model.JMemberGiftcardResult;
import com.aishang.app.data.model.JMemberImgEditResult;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.data.model.JMemberProfileEditResult;
import com.aishang.app.data.model.JMemberProfileResult;
import com.aishang.app.data.model.JMemberStatisticsResult;
import com.aishang.app.data.model.JMreActivityDetailResult;
import com.aishang.app.data.model.JMreActivityListResult;
import com.aishang.app.data.model.JMrePromResult;
import com.aishang.app.data.model.JMyBusinessBuyInListResult;
import com.aishang.app.data.model.JMyVacationApplyListResult;
import com.aishang.app.data.model.JMyVacationListResult;
import com.aishang.app.data.model.JNewsDetailResult;
import com.aishang.app.data.model.JNewsHitsResult;
import com.aishang.app.data.model.JNewsListResult;
import com.aishang.app.data.model.JParticipationReslut;
import com.aishang.app.data.model.JReleaseResult;
import com.aishang.app.data.model.JRentalListResult;
import com.aishang.app.data.model.JResult;
import com.aishang.app.data.model.JSendCodeResult;
import com.aishang.app.data.model.JSysZoneResult;
import com.aishang.app.data.model.JTagListResult;
import com.aishang.app.data.model.JUploadFileResult;
import com.aishang.app.data.model.JVersionCheckResult;
import com.aishang.app.data.model.Ribot;
import com.aishang.app.util.OkHttpUtils;
import com.aishang.app.util.gson.EmptyStringObjectAdapterFactory;
import com.aishang.app.util.okhttp.CookieJarImpl;
import com.aishang.app.util.okhttp.store.MemoryCookieStore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

public interface AiShangService {

  String ENDPOINT = "https://api.ribot.io/";
  String AiShangHost = "http://www.aishanglvju.com/";
  String IMG_URL = "http://www.aishanglvju.com/";

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

  @Headers("connection:Keep-Alive") @GET("mobile/businessListv2.ashx")
  Observable<JBusinessListResult> sysBusinessList(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/recommendmember.ashx")
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

  @Headers("connection:Keep-Alive") @GET("mobile/other/mreActivityList.ashx")
  Observable<JMreActivityListResult> syncMreActivityList(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/other/mreActivityDetail.ashx")
  Observable<JMreActivityDetailResult> syncMreActivityDetail(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/other/mreActivityEnroll.ashx")
  Observable<JResult> syncMreActivityEnroll(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/projecCtooperation.ashx")
  Observable<JResult> syncProjecCtooperation(@Query("v") int version, @Query("q") String q);

  @Headers("connection:Keep-Alive") @GET("/mobile/projectChange.ashx")
  Observable<JResult> syncProjectChange(@Query("v") int version, @Query("q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/subscription.ashx")
  Observable<JResult> syncSubscription(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/passwordChange.ashx")
  Observable<JResult> syncPasswordChange(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/memberProfileBasicEdit.ashx")
  Observable<JMemberProfileEditResult> memberProfileBasicEdit(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/memberProfileBasicEdit.ashx")
  Observable<JMemberProfileEditResult> memberProfileEdit(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/hotel/hotelStarLevelList.ashx")
  Observable<JHotelStarLevelListResult> syncHotelStarLevelList(@Query(value = "v") int version);

  @Headers("connection:Keep-Alive") @GET("mobile/member/memberBankList.ashx")
  Observable<JMemberBankListResult> syncBankList(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/memberBankEdit.ashx")
  Observable<JMemberBankListResult> syncBankEdit(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Multipart @Headers("connection:Keep-Alive") @POST("mobile/uploadFile.ashx")
  Observable<JUploadFileResult> uploadFile(@PartMap() Map<String, RequestBody> body);

  @Multipart @Headers("connection:Keep-Alive") @POST("mobile/member/memberImgEdit.ashx")
  Observable<JMemberImgEditResult> syncUploadMemberImg(@PartMap() Map<String, RequestBody> body);
  //

  @Headers("connection:Keep-Alive") @GET("mobile/member/memberLogout.ashx")
  Observable<JResult> syncMemberLogout(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/suggestion.ashx")
  Observable<JResult> syncSuggestion(@Query(value = "v") int version, @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/hotel/hotelRoomCatListByhotelID.ashx")
  Observable<JHotelRoomCatListByhotelIDResult> syncHotelRoomCatByHotelID(
      @Query(value = "v") int version, @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/sendCode.ashx")
  Observable<JSendCodeResult> syncSendCode(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/memberNoteRegister.ashx")
  Observable<JResult> syncMemberNoteRegister(@Query(value = "v") int version,
      @Query(value = "q") String q, @Header("Cookie") String cookie);

  @Headers("connection:Keep-Alive") @POST("mobile/member/codeLogin.ashx") @Multipart
  Observable<JCodeLoginResult> syncCodeLogin(@PartMap Map<String, RequestBody> body);

  @Headers({ "Content-Type: application/json;charset=UTF-8", "connection:Keep-Alive" })
  @GET("mobile/member/codeLogin.ashx") Observable<JMemberLoginResult> syncCodeLogin(
      @Header("Cookie") String cookie, @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/collect.ashx")
  Observable<JCollectResult> syncCollect(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/participation.ashx")
  Observable<JParticipationReslut> syncParticipation(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/release.ashx")
  Observable<JReleaseResult> syncRelease(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/myVacationList.ashx")
  Observable<JMyVacationListResult> syncMyVacationList(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/hotel/hotelRoomCatList.ashx")
  Observable<JHotelRoomCatListResult> syncHotelRoomCatList(@Query(value = "v") int version);

  @Headers("connection:Keep-Alive") @GET("mobile/hotelRoomFacilitesCatList.ashx")
  Observable<JHotelRoomFacilitesCatListResult> syncHotelRoomFacilitesCatList(
      @Query(value = "v") int version);

  @Headers("connection:Keep-Alive") @GET("mobile/tags/tagList.ashx")
  Observable<JTagListResult> syncTagList(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/newsDetail.ashx")
  Observable<JNewsDetailResult> syncTravelDetail(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/newsCriticism.ashx")
  Observable<JResult> syncNewsCriticism(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/newsHits.ashx")
  Observable<JNewsHitsResult> syncNewsHits(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/favoriteEdit.ashx")
  Observable<JResult> syncFavoriteEdit(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/corp/loupanProductVIPView.ashx")
  Observable<JLoupanProductVIPViewResult> syncLoupanProductVIPView(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/criticismList.ashx")
  Observable<JCriticismListResult> syncCriticismList(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/commonIntegral.ashx")
  Observable<JCommonIntegralResult> syncCommonIntegral(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/AwardDetailListV2.ashx")
  Observable<JAwardDetailListV2Result> syncAwardDetailListV2(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/getCheckinRecord.ashx")
  Observable<JCheckinRecordResult> syncCheckinRecord(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @Headers("connection:Keep-Alive") @GET("mobile/member/getMemberGiftcard.ashx")
  Observable<JMemberGiftcardResult> syncGetMemberGiftcard(@Query(value = "v") int version,
      @Query(value = "q") String q);

  @GET("ribots") Observable<List<Ribot>> getRibots();

  /******** Helper class that sets up a new services *******/
  class Creator {

    public static AiShangService newAiShangService() {
      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
      //
      //HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
      //// set your desired log level
      //logging.setLevel(HttpLoggingInterceptor.Level.BODY);
      //
      //OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
      //
      //// add logging as last interceptor
      //httpClient.interceptors().add(logging);  // <-- this is the important line!
      //httpClient.cookieJar(new CookieJarImpl(new MemoryCookieStore()));

      Retrofit retrofit = new Retrofit.Builder().baseUrl(AiShangService.AiShangHost)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
          .client(OkHttpUtils.getInstance().debug().getOkHttpClient())
          .build();

      return retrofit.create(AiShangService.class);
    }
  }
}
