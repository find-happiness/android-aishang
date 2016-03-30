package com.aishang.app.data.model;

/**
 * Created by song on 2016/3/30.
 */
public class JNewsCriticismParam {

  /**
   * memberAccount : 13883224451
   * cookie : DBF984A98014521448E818662D367694
   * newsID : 653
   * comment : sfsdfsdfsdsfsfs
   */

  private String memberAccount;
  private String cookie;
  private int newsID;
  private String comment;

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

  public int getNewsID() {
    return newsID;
  }

  public void setNewsID(int newsID) {
    this.newsID = newsID;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
