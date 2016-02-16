package com.aishang.app.data.model;

/**
 * Created by song on 2016/2/16.
 */
public class JRentalListParam {
  /// <summary>
  /// 租售类型  1租 2售
  /// </summary>
  public int filterType ;
  /// <summary>
  /// SQL条件
  /// </summary>
  public String filterWords ;
  /// <summary>
  /// 记录开始位置
  /// </summary>
  public int recStart ;
  /// <summary>
  /// 获取多少条记录
  /// </summary>
  public int recCount ;
  /// <summary>
  /// 区域ID?
  /// </summary>
  public int fZoneID ;
  /// <summary>
  /// 价格ID?
  /// </summary>
  public int fPriceCatID ;
  /// <summary>
  /// 最小价格
  /// </summary>
  public float fPriceMin ;
  /// <summary>
  /// 最大价格
  /// </summary>
  public float fPriceMax ;
  /// <summary>
  /// 房间类型ID
  /// </summary>
  public int fRoomTypeID ;
  /// <summary>
  /// 0—不返回 itemList，1—返回itemList
  /// </summary>
  public int beDetail ;



}
