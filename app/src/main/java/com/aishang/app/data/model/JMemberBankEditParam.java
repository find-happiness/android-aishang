package com.aishang.app.data.model;

/**
 * Created by song on 2016/2/23.
 */
public class JMemberBankEditParam {
  private String memberAccount;
  private String cookie;
  private String imei;

  private float googleLat;
  private float googleLng;

  private int bReturnList;

  JMemberBankAccount[] bankAccountList;

  public String getMemberAccount() {
    return memberAccount;
  }

  public void setMemberAccount(String memberAccount) {
    this.memberAccount = memberAccount;
  }

  public String getCookie() {
    return cookie;
  }

  public void setCookie(String cookie) {
    this.cookie = cookie;
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

  public int getbReturnList() {
    return bReturnList;
  }

  public void setbReturnList(int bReturnList) {
    this.bReturnList = bReturnList;
  }

  public JMemberBankAccount[] getBankAccountList() {
    return bankAccountList;
  }

  public void setBankAccountList(JMemberBankAccount[] bankAccountList) {
    this.bankAccountList = bankAccountList;
  }
}
