package com.aishang.app.data.model;

/**
 * Created by song on 2016/3/30.
 */
public class JNewsHitsParam {
  int newsId;
  public String memberAccount;
  public String cookie;

  public int getNewsId() {
    return newsId;
  }

  public void setNewsId(int newsId) {
    this.newsId = newsId;
  }

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
}
