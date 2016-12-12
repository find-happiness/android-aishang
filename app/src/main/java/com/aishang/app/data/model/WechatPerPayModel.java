package com.aishang.app.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by song on 2016/9/17.
 */
public class WechatPerPayModel {

  /**
   * appid : wxb4ba3c02aa476ea1
   * partnerid : 1305176001
   * package : Sign=WXPay
   * noncestr : 5c99ccebac6aef73b70ef3c9d1a97631
   * timestamp : 1474117136
   * prepayid : wx20160917205856fc0010097a0028271394
   * sign : 3C16CE0AB643ADAEC491A787878EDF87
   */

  private String appid;
  private String partnerid;
  @SerializedName("package") private String packageX;
  private String noncestr;
  private double timestamp;
  private String prepayid;
  private String sign;

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public String getPartnerid() {
    return partnerid;
  }

  public void setPartnerid(String partnerid) {
    this.partnerid = partnerid;
  }

  public String getPackageX() {
    return packageX;
  }

  public void setPackageX(String packageX) {
    this.packageX = packageX;
  }

  public String getNoncestr() {
    return noncestr;
  }

  public void setNoncestr(String noncestr) {
    this.noncestr = noncestr;
  }

  public double getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(double timestamp) {
    this.timestamp = timestamp;
  }

  public String getPrepayid() {
    return prepayid;
  }

  public void setPrepayid(String prepayid) {
    this.prepayid = prepayid;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }
}
