package com.aishang.app.data.model;

/**
 * Created by song on 2016/2/19.
 */
public class JMreActivityEnrollParam {
  //activityID:int,
  //memberAccount:string, cookie:string,  (可以为空)
  //userName:string (报名人员名),
  //gender:int(-1—保密，0— 女, 1—男),
  //userPhone:string (报名电话),
  //guestCount:int (人数，默认 1),
  //userComment:string (报名备注)

  private int activityID;
  private String memberAccount;
  private String cookie;
  private String userName;
  private int gender;
  private String userPhone;
  private int guestCount;
  private String userComment;

  public int getActivityID() {
    return activityID;
  }

  public void setActivityID(int activityID) {
    this.activityID = activityID;
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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public int getGender() {
    return gender;
  }

  public void setGender(int gender) {
    this.gender = gender;
  }

  public String getUserPhone() {
    return userPhone;
  }

  public void setUserPhone(String userPhone) {
    this.userPhone = userPhone;
  }

  public int getGuestCount() {
    return guestCount;
  }

  public void setGuestCount(int guestCount) {
    this.guestCount = guestCount;
  }

  public String getUserComment() {
    return userComment;
  }

  public void setUserComment(String userComment) {
    this.userComment = userComment;
  }
}
