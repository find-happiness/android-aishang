package com.aishang.app.data.model;

/**
 * Created by song on 2016/3/13.
 */
public class JCollectParam {

  private String memberAccount;
  private String cookie;
  private int recStart;
  private int recCount;

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
}
