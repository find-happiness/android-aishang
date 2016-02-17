package com.aishang.app.data.model;

/**
 * Created by song on 2016/2/16.
 */
public class JRentalListParam {
  /// <summary>
  /// 租售类型  1租 2售
  /// </summary>
  private int filterType;
  /// <summary>
  /// SQL条件
  /// </summary>
  private String filterWords;
  /// <summary>
  /// 记录开始位置
  /// </summary>
  private int recStart;
  /// <summary>
  /// 获取多少条记录
  /// </summary>
  private int recCount;
  /// <summary>
  /// 区域ID?
  /// </summary>
  private int fZoneID;
  /// <summary>
  /// 价格ID?
  /// </summary>
  private int fPriceCatID;
  /// <summary>
  /// 最小价格
  /// </summary>
  private float fPriceMin;
  /// <summary>
  /// 最大价格
  /// </summary>
  private float fPriceMax;
  /// <summary>
  /// 房间类型ID
  /// </summary>
  private int fRoomTypeID;
  /// <summary>
  /// 0—不返回 itemList，1—返回itemList
  /// </summary>
  private int beDetail;

  public int getFilterType() {
    return filterType;
  }

  public void setFilterType(int filterType) {
    this.filterType = filterType;
  }

  public String getFilterWords() {
    return filterWords;
  }

  public void setFilterWords(String filterWords) {
    this.filterWords = filterWords;
  }

  public int getRecStart() {
    return recStart;
  }

  public void setRecStart(int recStart) {
    this.recStart = recStart;
  }

  public int getRecCount() {
    return recCount;
  }

  public void setRecCount(int recCount) {
    this.recCount = recCount;
  }

  public int getfZoneID() {
    return fZoneID;
  }

  public void setfZoneID(int fZoneID) {
    this.fZoneID = fZoneID;
  }

  public int getfPriceCatID() {
    return fPriceCatID;
  }

  public void setfPriceCatID(int fPriceCatID) {
    this.fPriceCatID = fPriceCatID;
  }

  public float getfPriceMin() {
    return fPriceMin;
  }

  public void setfPriceMin(float fPriceMin) {
    this.fPriceMin = fPriceMin;
  }

  public float getfPriceMax() {
    return fPriceMax;
  }

  public void setfPriceMax(float fPriceMax) {
    this.fPriceMax = fPriceMax;
  }

  public int getfRoomTypeID() {
    return fRoomTypeID;
  }

  public void setfRoomTypeID(int fRoomTypeID) {
    this.fRoomTypeID = fRoomTypeID;
  }

  public int getBeDetail() {
    return beDetail;
  }

  public void setBeDetail(int beDetail) {
    this.beDetail = beDetail;
  }
}
