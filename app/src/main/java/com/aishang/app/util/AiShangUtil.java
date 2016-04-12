package com.aishang.app.util;

import android.app.Activity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.data.model.JBusinessListParam;
import com.aishang.app.data.model.JCashWithDrawApplyparam;
import com.aishang.app.data.model.JCodeLoginParam;
import com.aishang.app.data.model.JCollectParam;
import com.aishang.app.data.model.JContactsAddParam;
import com.aishang.app.data.model.JFavoriteEditParam;
import com.aishang.app.data.model.JHotelDetailParam;
import com.aishang.app.data.model.JHotelListParam;
import com.aishang.app.data.model.JHotelRoomCatListByhotelIDParam;
import com.aishang.app.data.model.JLoupanPriceCatListParam;
import com.aishang.app.data.model.JLoupanProductDetailParam;
import com.aishang.app.data.model.JLoupanProductListParam;
import com.aishang.app.data.model.JLoupanProductVIPViewParam;
import com.aishang.app.data.model.JMemberBankAccount;
import com.aishang.app.data.model.JMemberBankEditParam;
import com.aishang.app.data.model.JMemberBankListParam;
import com.aishang.app.data.model.JMemberLoginParam;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.data.model.JMemberLogoutParam;
import com.aishang.app.data.model.JMemberNoteRegisterParams;
import com.aishang.app.data.model.JMemberProfileBasicEditParam;
import com.aishang.app.data.model.JMemberProfileParam;
import com.aishang.app.data.model.JMemberStatisticsParam;
import com.aishang.app.data.model.JMreActivityEnrollParam;
import com.aishang.app.data.model.JMreActivityListParam;
import com.aishang.app.data.model.JMyBusinessBuyInListParam;
import com.aishang.app.data.model.JMyVacationApplyListParams;
import com.aishang.app.data.model.JMyVacationListParam;
import com.aishang.app.data.model.JMyVacationListResult;
import com.aishang.app.data.model.JNewsCriticismParam;
import com.aishang.app.data.model.JNewsDetailParam;
import com.aishang.app.data.model.JNewsHitsParam;
import com.aishang.app.data.model.JNewsListParams;
import com.aishang.app.data.model.JParticipationParam;
import com.aishang.app.data.model.JPasswordChangeParams;
import com.aishang.app.data.model.JProjecCtooperationParam;
import com.aishang.app.data.model.JProjectChangeParam;
import com.aishang.app.data.model.JReleaseParam;
import com.aishang.app.data.model.JRentalListParam;
import com.aishang.app.data.model.JSendCodeParams;
import com.aishang.app.data.model.JSubscriptionParam;
import com.aishang.app.data.model.JSuggestionParam;
import com.aishang.app.data.model.JSysZoneParam;
import com.aishang.app.data.model.JTagListParam;
import com.aishang.app.data.remote.AiShangService;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by song on 2016/1/26.
 */
public class AiShangUtil {

  private static Gson gson = new Gson();

  /**
   * 生成楼盘请求参数
   *
   * @param userZoneIDLevel2 二级区域
   * @param userLat google坐标
   * @param userLng 过噢噢乖了坐标
   * @param recStart 开始位置
   * @param recCount 总条数
   * @param fLoupanID 楼盘id
   * @param filterTypeID 过滤类型
   * @param filterWords 过滤关键字
   * @param fOrderBy 排序
   * @param fOpenDate 开盘日期
   * @param fMoveInDate 入住日期
   * @param fZoneID 1:word,2china
   * @param fTouristAreaID 景区id
   * @param fPriceCatID 价格id
   * @param fPriceMin 最小价格
   * @param fPriceMax 最大价格
   * @return json
   */
  public static String generLoupanProductParam(int userZoneIDLevel2, float userLat, float userLng,
      int recStart, int recCount, int fLoupanID, int filterTypeID, String filterWords, int fOrderBy,
      String fOpenDate, String fMoveInDate, int fZoneID, int fTouristAreaID, int fPriceCatID,
      float fPriceMin, float fPriceMax, int fRoomTypeID, String fServiceCatIDs,
      String fLandScapeIDs, String fTagIDs, int beVIPHome) {
    JLoupanProductListParam loupanProductAjaxParam = new JLoupanProductListParam();

    loupanProductAjaxParam.setUserZoneIDLevel2(userZoneIDLevel2);
    loupanProductAjaxParam.setUserLat(userLat);
    loupanProductAjaxParam.setUserLng(userLng);
    loupanProductAjaxParam.setRecStart(recStart);
    loupanProductAjaxParam.setRecCount(recCount);
    loupanProductAjaxParam.setfLoupanID(fLoupanID);
    loupanProductAjaxParam.setFilterTypeID(filterTypeID);
    loupanProductAjaxParam.setFilterWords(filterWords);
    loupanProductAjaxParam.setfOrderBy(fOrderBy);
    loupanProductAjaxParam.setfOpenDate(fOpenDate);
    loupanProductAjaxParam.setfMoveInDate(fMoveInDate);
    loupanProductAjaxParam.setfZoneID(fZoneID);
    loupanProductAjaxParam.setfTouristAreaID(fTouristAreaID);
    loupanProductAjaxParam.setfPriceCatID(fPriceCatID);
    loupanProductAjaxParam.setfPriceMin(fPriceMin);
    loupanProductAjaxParam.setfPriceMax(fPriceMax);
    loupanProductAjaxParam.setfRoomTypeID(fRoomTypeID);
    loupanProductAjaxParam.setfServiceCatIDs(fServiceCatIDs);
    loupanProductAjaxParam.setfLandScapeIDs(fLandScapeIDs);
    loupanProductAjaxParam.setfTagIDs(fTagIDs);
    loupanProductAjaxParam.setBeVIPHome(beVIPHome);

    return gson.toJson(loupanProductAjaxParam);
  }

  /**
   * @param filterWords 关键字搜索
   * @param recStart 分页加载开始的地方
   * @param recCount 分页一页加载个数
   * @param fSortID 排序方式 1—价格升序(默认) 2—价格降序
   * @param fMoveInDate 入住时间
   * @param fMoveOutDate 退房时间
   * @param fZoneID 区域id 不限1 全世界
   * @param fTouristID 景区id 不限0
   * @param fPriceCatID 价格id 不限0
   * @param fPriceMin 价格区间
   * @param fPriceMax 价格区间
   * @param fRoomTypeID 房间类型id 不限0
   * @param fServiceIDs 服务类型id 不限0
   * @param fStarLevelID 星级id 不限0
   * @param fTagIDs 标签id 不限0
   * @param beVIPHome 是否取首页置顶的。0—否 1—是
   * @param beDetail 是否需要更多细节moreDetail 0—否 1—是
   */
  public static String generHotelParam(int filterType, String filterWords, int recStart,
      int recCount, int fSortID, String fMoveInDate, String fMoveOutDate, int fZoneID,
      int fTouristID, int fPriceCatID, float fPriceMin, float fPriceMax, int fRoomTypeID,
      String fServiceIDs, int fStarLevelID, String fTagIDs, int beVIPHome, int beDetail) {
    JHotelListParam hotelAjaxParam = new JHotelListParam();

    hotelAjaxParam.setFilterType(filterType);
    hotelAjaxParam.setFilterWords(filterWords);
    hotelAjaxParam.setRecStart(recStart);
    hotelAjaxParam.setRecCount(recCount);
    hotelAjaxParam.setfSortID(fSortID);
    hotelAjaxParam.setfMoveInDate(fMoveInDate);
    hotelAjaxParam.setfMoveOutDate(fMoveOutDate);
    hotelAjaxParam.setfZoneID(fZoneID);
    hotelAjaxParam.setfTouristID(fTouristID);
    hotelAjaxParam.setfPriceCatID(fPriceCatID);
    hotelAjaxParam.setfPriceMin(fPriceMin);
    hotelAjaxParam.setfPriceMax(fPriceMax);
    hotelAjaxParam.setfRoomTypeID(fRoomTypeID);
    hotelAjaxParam.setfServiceIDs(fServiceIDs);
    hotelAjaxParam.setfStarLevelID(fStarLevelID);
    hotelAjaxParam.setfTagIDs(fTagIDs);
    hotelAjaxParam.setBeDetail(beDetail);
    hotelAjaxParam.setBeVIPHome(beVIPHome);

    return gson.toJson(hotelAjaxParam);
  }

  /**
   * 生成请求的Json ?q={}
   *
   * @param recStart 开始位置
   * @param zoneID 区域ID 默认是1
   * @param catID 新闻的CatId 0全部
   * @param filterTypeID 分类/排序方式, 0—系统默认
   * @param beVIPHome 是否首页推荐用的， 0—否，1—是
   * @param tagIDs tag的ID，采用”,”分割
   * @param fKeyWords 查找字符串。
   * @param fOrderType 排序类型：1—日期desc, 2—日期ASC, 3-点击DESC 4—点击ASC， 5—标题DESC，6—标题ASC
   * @param recCount 请求条数
   * @return 生成后的JSon
   */
  public static String generNewsParam(int recStart, int zoneID, int catID, int filterTypeID,
      int beVIPHome, String tagIDs, String fKeyWords, int fOrderType, int recCount) {
    JNewsListParams params = new JNewsListParams();
    params.setCatID(catID);
    params.setZoneID(zoneID);
    params.setFilterTypeID(filterTypeID);
    params.setRecStart(recStart);
    params.setRecCount(recCount);
    params.setTagIDs(tagIDs);
    params.setBeVIPHome(beVIPHome);
    params.setfKeyWords(fKeyWords);
    params.setfOrderType(fOrderType);

    return gson.toJson(params);
  }

  /**
   * 生成入住日期 明天
   *
   * @return 返回日期的格式化后的string
   */
  public static String gennerCheckinData() {

    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(System.currentTimeMillis() + 86400000L);
    return String.format("%d-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
        cal.get(Calendar.DAY_OF_MONTH));
  }

  /**
   * 生成离开日期 后天
   *
   * @return 返回日期的格式化后的string
   */
  public static String gennerCheckoutData() {
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(System.currentTimeMillis() + 2 * 86400000L);
    return String.format("%d-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
        cal.get(Calendar.DAY_OF_MONTH));
  }

  public static String dateFormat(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return sdf.format(date);
  }

  public static String gennerLogin(String psd, String phone) {
    JMemberLoginParam param = new JMemberLoginParam();
    param.setMemberAccount(phone);
    param.setPassword(CommonUtil.getEncodeMD5(psd));

    return gson.toJson(param);
  }

  public static String gennerMemberStatistics(String psd, String phone) {
    JMemberStatisticsParam param = new JMemberStatisticsParam();
    param.setMemberAccount(phone);
    param.setPassword(psd);

    return gson.toJson(param);
  }

  public static String gennerSysZone(int zoneID) {
    JSysZoneParam p = new JSysZoneParam();
    p.setZoneID(zoneID);
    p.setLastUpdate("");
    return gson.toJson(p);
  }

  public static String generHotelDetailParam(int hotelID, int bGallery, int bSelectImage,
      int bRoomCat, int bPriceList, int bBase, String checkinDate, String checkoutDate,
      int bHotelFaclilite, int bHotelService, int bRoomFacilite, int bRoomService) {

    JHotelDetailParam param = new JHotelDetailParam();
    param.setbGallery(bGallery);
    param.setbPriceList(bPriceList);
    param.setbRoomCat(bRoomCat);
    param.setbSelectImage(bSelectImage);
    param.setbBase(bBase);
    param.setHotelID(hotelID);
    param.setCheckInDate(checkinDate);
    param.setCheckOutDate(checkoutDate);
    param.setbHotelFaclilite(bHotelFaclilite);
    param.setbHotelService(bHotelService);
    param.setbHotelFaclilite(bRoomFacilite);
    param.setbHotelService(bHotelService);
    return gson.toJson(param);
  }

  public static String generLoupanPriceCatListParam(int filter) {
    // gener param
    JLoupanPriceCatListParam p = new JLoupanPriceCatListParam();
    p.setFilter(filter);
    return gson.toJson(p);
  }

  public static String generLoupanProductDetail(int loupanProductID, int bTourist, int bGallery) {
    JLoupanProductDetailParam param = new JLoupanProductDetailParam();
    param.setbTourist(bTourist);
    param.setbGallery(bGallery);
    param.setLoupanProductID(loupanProductID);
    return gson.toJson(param);
  }

  /**
   * set webview content
   *
   * @param content content html
   */
  public static void setWebViewContent(final WebView wv, String content) {
    content = CommonUtil.htmldecode(content);
    wv.getSettings().setDefaultTextEncodingName("utf-8");
    wv.loadDataWithBaseURL(AiShangService.AiShangHost, content, "text/html; charset=UTF-8", null,
        null);// 这种写法可以正确解码

    // updateExpandable(wv);

    wv.setWebViewClient(new WebViewClient() {
      boolean loadingFinished = true;
      boolean redirect = false;

      @Override public boolean shouldOverrideUrlLoading(WebView view, String urlNewString) {
        if (!loadingFinished) {
          redirect = true;
        }

        loadingFinished = false;
        wv.loadUrl(urlNewString);
        return true;
      }

      public void onPageStarted(WebView view, String url) {
        loadingFinished = false;
        // SHOW LOADING IF IT ISNT ALREADY VISIBLE
      }

      @Override public void onPageFinished(WebView view, String url) {
        if (!redirect) {
          loadingFinished = true;
        }

        if (loadingFinished && !redirect) {
          wv.setLayoutParams(
              new android.widget.LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                  LinearLayout.LayoutParams.WRAP_CONTENT));
        } else {
          redirect = false;
        }
      }
    });
  }

  public static String gennerMyOrderParam(int recStart, String startDate, String endDate,
      int status, String cardID, String cookie, String memberAccount) {

    JMyVacationApplyListParams param = new JMyVacationApplyListParams();
    param.setCookie(cookie);
    param.setMemberAccount(memberAccount);
    param.setRecCount(10);
    param.setRecStart(recStart);
    param.setFilterStartDate(startDate);
    param.setFilerEndDate(endDate);
    param.setFilterVACardID(cardID);
    param.setStatus(status);

    return gson.toJson(param);
  }

  /**
   * 生成请求的参数
   */
  public static String generBusinessListJson(String memberAccount, String cookie, int filterType,
      String startDate, String endDate, int recStart, int recCount, String orderby, int memberLevel,
      String subMemberPhone) {

    JBusinessListParam param = new JBusinessListParam();
    param.setMemberAccount(memberAccount);
    param.setCookie(cookie);
    param.setFilterType(filterType);
    param.setStartDate(startDate);
    param.setEndDate(endDate);
    param.setOrderby(orderby);
    param.setRecStart(recStart);
    param.setRecCount(recCount);
    param.setMemberLevel(memberLevel);
    param.setSubMemberPhone(subMemberPhone);

    return gson.toJson(param);
  }

  public static String generContactsAddParam(String phone, String name, String cookie,
      String member) {

    JContactsAddParam p = new JContactsAddParam();
    p.setCookie(cookie);
    p.setMemberAccount(member);

    JContactsAddParam.Item[] item = new JContactsAddParam.Item[1];
    item[0] = p.new Item();
    item[0].setName(name);
    item[0].setPhone(phone);
    p.setItemList(item);
    return new Gson().toJson(p);
  }

  public static String generMyBusinessBuyInListParam(String orderby, int start, int recCount,
      String cookie, String member) {

    JMyBusinessBuyInListParam p = new JMyBusinessBuyInListParam();
    p.setCookie(cookie);
    p.setMemberAccount(member);
    p.setOrderby(orderby);
    p.setRecCount(recCount);
    p.setRecStart(start);
    return new Gson().toJson(p);
  }

  public static String generCashWithDrawApplyParam(String accountNumber, String amount,
      String holder, String bankName, String member, String cookie) {

    JCashWithDrawApplyparam param = new JCashWithDrawApplyparam();
    param.setAccountNumber(accountNumber);
    param.setAmount(Float.parseFloat(amount));
    param.setBankName(bankName);
    param.setCookie(cookie);
    param.setGoogleLat(0.f);
    param.setGoogleLng(0.f);
    param.setHolder(holder);
    param.setImei("");
    param.setMemberAccount(member);

    return gson.toJson(param);
  }

  public static String generRentalListParam(int filterType, String filterWords, int recStart,
      int recCount, int fZoneID, int fPriceCatID, float fPriceMin, float fPriceMax, int fRoomTypeID,
      int beDetail) {

    JRentalListParam param = new JRentalListParam();
    param.setBeDetail(beDetail);
    param.setRecStart(recStart);
    param.setFilterType(filterType);
    param.setFilterWords(filterWords);
    param.setfPriceCatID(fPriceCatID);
    param.setfPriceMax(fPriceMax);
    param.setfPriceMin(fPriceMin);
    param.setfRoomTypeID(fRoomTypeID);
    param.setfZoneID(fZoneID);
    param.setRecCount(recCount);

    return gson.toJson(param);
  }

  public static String gennerMreActivityDetailParam(int corpID, int catID, int beAll,
      String memberAccount, String cookie, int beEnrolled, int recStart, int recCount) {

    JMreActivityListParam param = new JMreActivityListParam();
    param.setBeAll(beAll);
    param.setBeEnrolled(beEnrolled);
    param.setCatID(catID);
    param.setCookie(cookie);
    param.setMemberAccount(memberAccount);
    param.setCorpID(corpID);
    param.setRecStart(recStart);
    param.setRecCount(recCount);
    return gson.toJson(param);
  }

  public static String generMreActivityEnrollParam(int activityID, String memberAccount,
      String cookie, String userName, int gender, String userPhone, int guestCount,
      String userComment) {

    JMreActivityEnrollParam param = new JMreActivityEnrollParam();
    param.setActivityID(activityID);
    param.setMemberAccount(memberAccount);
    param.setCookie(cookie);
    param.setUserName(userName);
    param.setGender(gender);
    param.setUserPhone(userPhone);
    param.setGuestCount(guestCount);
    param.setUserComment(userComment);

    return gson.toJson(param);
  }

  public static String generProjecCtooperationParam(String projectName, String projectAddress,
      String projectCount, String contacts, String contactsPhone, String contactsEmail,
      String special) {

    JProjecCtooperationParam param = new JProjecCtooperationParam();
    param.setProjectName(projectName);
    param.setProjectAddress(projectAddress);
    param.setProjectCount(projectCount);
    param.setContacts(contacts);
    param.setContactsPhone(contactsPhone);
    param.setContactsEmail(contactsEmail);
    param.setSpecial(special);
    return gson.toJson(param);
  }

  public static String generProjectChangeParam(String propertyOwner, String IDNumber,
      String houseAddress, String houseNumber, String roomType, float area, String resStartDate,
      String resEndDate, String contactsPhone, String contactsMobile, String Email, String Img) {

    JProjectChangeParam param = new JProjectChangeParam();
    param.setPropertyOwner(propertyOwner);
    param.setIDNumber(IDNumber);
    param.setHouseAddress(houseAddress);
    param.setHouseNumber(houseNumber);
    param.setRoomType(roomType);
    param.setArea(area);
    param.setResStartDate(resStartDate);
    param.setResEndDate(resEndDate);
    param.setContactsPhone(contactsPhone);
    param.setContactsMobile(contactsMobile);
    param.setEmail(Email);
    param.setImg(Img);

    return gson.toJson(param);
  }

  public static String generSubscriptionParam(String rentalID, String name, String phone,
      String address, String negotiationTime, String zoneIDLevel2, String zoneIDLevel3,
      String zoneIDLevel4, String zoneIText) {

    JSubscriptionParam param = new JSubscriptionParam();
    param.setAddress(address);
    param.setName(name);
    param.setRentalID(rentalID);
    param.setPhone(phone);
    param.setNegotiationTime(negotiationTime);
    param.setZoneIDLevel2(zoneIDLevel2);
    param.setZoneIDLevel3(zoneIDLevel3);
    param.setZoneIDLevel4(zoneIDLevel4);
    param.setZoneIText(zoneIText);

    return gson.toJson(param);
  }

  public static String generChangePwdParams(String oldPwd, String newPwd, String member,
      String cookie) {
    JPasswordChangeParams params = new JPasswordChangeParams();
    params.setCookies(cookie);
    params.setMemberAccount(member);
    params.setGoogleLat(0);
    params.setGoogleLnt(0);
    params.setImei("");
    params.setNewPassword(newPwd);
    params.setOldPassword(oldPwd);
    return gson.toJson(params);
  }

  public static String generMemberProfileParam(String memberAccount, String cookies) {

    JMemberProfileParam param = new JMemberProfileParam();
    param.setMemberAccount(memberAccount);
    param.setCookies(cookies);
    param.setImei("");
    param.setGoogleLat(0f);
    param.setGoogleLng(0f);

    return gson.toJson(param);
  }

  public static String generMemberProfileBasicEdit(String cookies, String memberAccount, int gender,
      String email, String certifyType, String certifyID) {

    JMemberProfileBasicEditParam p = new JMemberProfileBasicEditParam();

    p.setCookie(cookies);
    p.setGoogleLat(1.f);
    p.setGoogleLng(1.f);
    p.setImei("");
    p.setMemberAccount(memberAccount);

    JMemberProfileBasicEditParam.Data data = p.new Data();
    data.setCertifyID(certifyID);
    data.setGender(gender + "");
    data.setCertifyType(certifyType);
    p.setData(data);
    return gson.toJson(p);
  }

  public static String generMamberBankListParam(String memberAccount, String cookies) {

    JMemberBankListParam param = new JMemberBankListParam();
    param.setMemberAccount(memberAccount);
    param.setCookie(cookies);
    param.setImei("");
    param.setGoogleLat(0f);
    param.setGoogleLng(0f);

    return gson.toJson(param);
  }

  public static String generMamberBankEditParam(String memberAccount, String cookies,
      int bReturnList, JMemberBankAccount[] accounts) {

    JMemberBankEditParam param = new JMemberBankEditParam();
    param.setMemberAccount(memberAccount);
    param.setCookie(cookies);
    param.setImei("");
    param.setGoogleLat(0f);
    param.setGoogleLng(0f);
    param.setBankAccountList(accounts);
    param.setbReturnList(bReturnList);

    return gson.toJson(param);
  }

  public static String generMamberLogoutParam(String memberAccount, String cookies) {

    JMemberLogoutParam param = new JMemberLogoutParam();
    param.setMemberAccount(memberAccount);
    param.setCookie(cookies);

    return gson.toJson(param);
  }

  public static String generSuggestionParam(String memberAccount, String cookies, String title,
      String content, String contact) {

    JSuggestionParam param = new JSuggestionParam();
    param.setMemberAccount(memberAccount);
    param.setCookie(cookies);
    param.setContact(contact);
    param.setContent(content);
    param.setTitle(title);

    return gson.toJson(param);
  }

  public static String generHotelRoomCatByHotelIDParam(int hotelid) {
    JHotelRoomCatListByhotelIDParam param = new JHotelRoomCatListByhotelIDParam();
    param.setHotelID(hotelid);
    return gson.toJson(param);
  }

  public static String generSendCodeParam(String phone, String hint, boolean isLogin) {
    JSendCodeParams param = new JSendCodeParams();
    param.setTel(phone);
    param.setHint(hint);
    param.setIsLogin(isLogin);
    return gson.toJson(param);
  }

  public static String generMemberNoteRegisterParam(String phone, String code, String psw) {
    JMemberNoteRegisterParams param = new JMemberNoteRegisterParams();
    param.setMobilePhone(phone);
    param.setCode(code);
    param.setPassword(psw);
    return gson.toJson(param);
  }

  public static String generCodeLoginParam(String phone, String code) {
    JCodeLoginParam param = new JCodeLoginParam();
    param.setTel(phone);
    param.setCode(code);
    return gson.toJson(param);
  }

  public static String generReleaseParam(String memberAccount, String cookies, int start,
      int count) {

    JReleaseParam param = new JReleaseParam();
    param.setMemberAccount(memberAccount);
    param.setCookie(cookies);
    param.setRecCount(start);
    param.setRecStart(count);

    return gson.toJson(param);
  }

  public static String generCollectParam(String memberAccount, String cookies, int start,
      int count) {

    JCollectParam param = new JCollectParam();
    param.setMemberAccount(memberAccount);
    param.setCookie(cookies);
    param.setRecCount(start);
    param.setRecStart(count);

    return gson.toJson(param);
  }

  public static String generParticipationParam(String memberAccount, String cookies, int start,
      int count) {

    JParticipationParam param = new JParticipationParam();
    param.setMemberAccount(memberAccount);
    param.setCookie(cookies);
    param.setRecCount(start);
    param.setRecStart(count);

    return gson.toJson(param);
  }

  public static String generMyVacationListParam(String memberAccount, String cookies,
      String queryDate) {

    JMyVacationListParam param = new JMyVacationListParam();
    param.setMemberAccount(memberAccount);
    param.setCookie(cookies);
    param.setQueryDate(queryDate);

    return gson.toJson(param);
  }

  public static String generNewsCriticismParam(String memberAccount, String cookies, int newsID,
      String comment) {

    JNewsCriticismParam param = new JNewsCriticismParam();
    param.setMemberAccount(memberAccount);
    param.setCookie(cookies);
    param.setNewsID(newsID);
    param.setComment(comment);

    return gson.toJson(param);
  }

  public static String generTagListParam(int filter, int recStart, int recCount, int orderBy) {

    JTagListParam param = new JTagListParam();
    param.setFilter(filter);
    param.setOrderBy(orderBy);
    param.setRecCount(recCount);
    param.setRecStart(recStart);

    return gson.toJson(param);
  }

  public static String generTravelDetail(int newsID) {

    JNewsDetailParam param = new JNewsDetailParam();
    param.setNewsID(newsID);

    return gson.toJson(param);
  }

  public static String generNewsHitsParam(String memberAccount, String cookies, int newsID) {

    JNewsHitsParam param = new JNewsHitsParam();
    //param.setMemberAccount(memberAccount);
    //param.setCookie(cookies);
    param.setNewsId(newsID);

    return gson.toJson(param);
  }

  public static String generFavoriteEditParam(String memberAccount, String cookies, int newsID) {

    JFavoriteEditParam param = new JFavoriteEditParam();

    param.setCookie(cookies);
    param.setMemberAccount(memberAccount);
    JFavoriteEditParam.DataEntity dataEntity = new JFavoriteEditParam.DataEntity();
    dataEntity.setPk(newsID + "");
    dataEntity.setModel("enshrine");
    //param.setData();
    //param.setMemberAccount(memberAccount);
    //param.setCookie(cookies);
    param.setData(dataEntity);

    return gson.toJson(param);
  }

  public static boolean checkMemberLogin(Activity ctx) {

    JMemberLoginResult result = BoilerplateApplication.get(ctx).getMemberLoginResult();

    if (result == null) {
      return false;
    } else {
      return true;
    }
  }

  public static String generLoupanProductVIPViewParam(String memberAccount, String cookie, int bLoupanList) {

    JLoupanProductVIPViewParam param = new JLoupanProductVIPViewParam();
    param.setMemberAccount(memberAccount);
    param.setCookie(cookie);
    param.setBLoupanList(bLoupanList);

    return gson.toJson(param);
  }
}
