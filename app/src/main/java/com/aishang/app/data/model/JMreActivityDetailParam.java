package com.aishang.app.data.model;

/**
 * Created by song on 2016/2/19.
 */
public class JMreActivityDetailParam {
  //{ memberAccount:'', cookie:'',activityID:" + request("activityID") + ", beDetail:1, beReview:1, recStart:" + pageIndex + ", recCount:10}

  private String memberAccount;
  private String cookie;
  private int activityID;
  private int beDetail;
  private int beReview;
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

  public int getActivityID() {
    return activityID;
  }

  public void setActivityID(int activityID) {
    this.activityID = activityID;
  }

  public int getBeDetail() {
    return beDetail;
  }

  public void setBeDetail(int beDetail) {
    this.beDetail = beDetail;
  }

  public int getBeReview() {
    return beReview;
  }

  public void setBeReview(int beReview) {
    this.beReview = beReview;
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
