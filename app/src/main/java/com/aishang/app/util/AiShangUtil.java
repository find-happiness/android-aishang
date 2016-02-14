package com.aishang.app.util;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.aishang.app.data.model.JHotelDetailParam;
import com.aishang.app.data.model.JHotelListParam;
import com.aishang.app.data.model.JLoupanPriceCatListParam;
import com.aishang.app.data.model.JLoupanProductDetailParam;
import com.aishang.app.data.model.JLoupanProductListParam;
import com.aishang.app.data.model.JMemberLoginParam;
import com.aishang.app.data.model.JMemberStatisticsParam;
import com.aishang.app.data.model.JNewsListParams;
import com.aishang.app.data.model.JSysZoneParam;
import com.aishang.app.data.remote.AiShangService;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    params.setZoneID(1);
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
}
