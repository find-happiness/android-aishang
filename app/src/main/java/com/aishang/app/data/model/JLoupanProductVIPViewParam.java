package com.aishang.app.data.model;

/**
 * Created by song on 2016/4/12.
 */
public class JLoupanProductVIPViewParam {

  /**
   * memberAccount : 02368400000
   * cookie : CAA82AACA9D475482AA516AC8DE8D4CA
   * bLoupanList : 1
   */

  private String memberAccount;
  private String cookie;
  private int bLoupanList;

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

  public int getBLoupanList() {
    return bLoupanList;
  }

  public void setBLoupanList(int bLoupanList) {
    this.bLoupanList = bLoupanList;
  }
}
