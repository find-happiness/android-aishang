package com.aishang.app.data.model;

/**
 * Created by song on 2016/2/16.
 */
public class JMreActivityDetailParam {
  public String memberAccount ;
  public String cookie ;
  public int activityID ;
  /// <summary>
  /// (0—无需详情 1—需要详情), 
  /// </summary>
  public int beDetail ;
  /// <summary>
  /// (0—无，1—有), 
  /// </summary>
  public int beReview ;
  /// <summary>
  /// (-1全部，0—未审核，1--审核通过  2--审核不通过)
  /// </summary>
  public int fReviewStatus ;
  public int recStart ;
  public int recCount ;

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

  public int getfReviewStatus() {
    return fReviewStatus;
  }

  public void setfReviewStatus(int fReviewStatus) {
    this.fReviewStatus = fReviewStatus;
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
