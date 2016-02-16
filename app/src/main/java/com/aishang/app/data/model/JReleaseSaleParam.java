package com.aishang.app.data.model;

/**
 * Created by song on 2016/2/16.
 */
public class JReleaseSaleParam {
  /// <summary>
  /// 租售类型  1出租 2出售
  /// </summary>
  public int rentalCat;
  /// <summary>
  /// 用户帐号
  /// </summary>
  public String memberAccount;
  /// <summary>
  /// 密码（cookie）
  /// </summary>
  public String cookie;
  /// <summary>
  /// 楼引用ID(出租:酒店ID 出售:楼盘ID)
  /// </summary>
  public int quoteID;
  /// <summary>
  /// 购买日期(出售)
  /// </summary>
  public String purchaseDate;
  /// <summary>
  /// 入住日期(出租)
  /// </summary>
  public String resStartDate;
  /// <summary>
  /// 退房日期(出租)
  /// </summary>
  public String resEndDate;
  /// <summary>
  /// 项目/酒店描述
  /// </summary>
  public String description;
  /// <summary>
  /// 价格
  /// </summary>
  public float price;
  /// <summary>
  /// 特色
  /// </summary>
  public String special;
}
