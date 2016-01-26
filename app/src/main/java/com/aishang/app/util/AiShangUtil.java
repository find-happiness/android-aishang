package com.aishang.app.util;

import com.aishang.app.data.model.JLoupanProductListParam;
import com.google.gson.Gson;

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
   * @param fZoneID
   *            1:word,2china
   * @param fTouristAreaID 景区id
   * @param fPriceCatID 价格id
   * @param fPriceMin 最小价格
   * @param fPriceMax 最大价格
   * @param fRoomTypeID
   * @param fServiceCatIDs
   * @param fLandScapeIDs
   * @param fTagIDs
   * @param beVIPHome
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
}
