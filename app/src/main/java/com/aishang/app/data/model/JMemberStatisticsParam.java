package com.aishang.app.data.model;

/**
 * Created by song on 2016/1/29.
 */
public class JMemberStatisticsParam {

  // { memberAccount:string, password:string,
  // imei:string, googleLat:float, googleLng:float }
  private String memberAccount;
  private String password;
  private String imei;
  private float googleLat;
  private float googleLng;

  public String getMemberAccount() {
    return memberAccount;
  }

  public void setMemberAccount(String memberAccount) {
    this.memberAccount = memberAccount;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getImei() {
    return imei;
  }

  public void setImei(String imei) {
    this.imei = imei;
  }

  public float getGoogleLat() {
    return googleLat;
  }

  public void setGoogleLat(float googleLat) {
    this.googleLat = googleLat;
  }

  public float getGoogleLng() {
    return googleLng;
  }

  public void setGoogleLng(float googleLng) {
    this.googleLng = googleLng;
  }
}
