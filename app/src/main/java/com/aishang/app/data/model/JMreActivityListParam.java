package com.aishang.app.data.model;

/**
 * Created by song on 2016/2/16.
 */
public class JMreActivityListParam {
  int corpID;
  int catID;
  int beAll;
  String memberAccount;
  String cookie;
  int beEnrolled;
  int recStart;
  int recCount;
  String fStartTime;
  String fEndTime;

  public String getfStartTime() {
    return fStartTime;
  }

  public void setfStartTime(String fStartTime) {
    this.fStartTime = fStartTime;
  }

  public String getfEndTime() {
    return fEndTime;
  }

  public void setfEndTime(String fEndTime) {
    this.fEndTime = fEndTime;
  }

  public int getCorpID() {
    return corpID;
  }

  public void setCorpID(int corpID) {
    this.corpID = corpID;
  }

  public int getCatID() {
    return catID;
  }

  public void setCatID(int catID) {
    this.catID = catID;
  }

  public int getBeAll() {
    return beAll;
  }

  public void setBeAll(int beAll) {
    this.beAll = beAll;
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

  public int getBeEnrolled() {
    return beEnrolled;
  }

  public void setBeEnrolled(int beEnrolled) {
    this.beEnrolled = beEnrolled;
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
