package com.aishang.app.data.model;

/**
 * Created by song on 2016/5/13.
 */
public class JAwardDetailListV2Param {

  /**
   * memberAccount : 13883224451
   * cookie : E806660AA82C3A713D5C628C3EE74989
   * startDate :
   * endDate :
   * recIndex : 1
   * recCount : 5
   * IsEarnings : true
   */

  private String memberAccount;
  private String cookie;
  private String startDate;
  private String endDate;
  private int recIndex;
  private int recCount;
  private boolean IsEarnings;

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

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public int getRecIndex() {
    return recIndex;
  }

  public void setRecIndex(int recIndex) {
    this.recIndex = recIndex;
  }

  public int getRecCount() {
    return recCount;
  }

  public void setRecCount(int recCount) {
    this.recCount = recCount;
  }

  public boolean isIsEarnings() {
    return IsEarnings;
  }

  public void setIsEarnings(boolean IsEarnings) {
    this.IsEarnings = IsEarnings;
  }
}
