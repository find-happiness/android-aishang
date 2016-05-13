package com.aishang.app.data.model;

/**
 * Created by song on 2016/5/13.
 */
public class JCommonIntegralParam {

  /**
   * memberAccount : 13883224451
   * cookie : E806660AA82C3A713D5C628C3EE74989
   * recStart : 0
   * recCount : 10
   * startDate :
   * endDate :
   */

  private String memberAccount;
  private String cookie;
  private int recStart;
  private int recCount;
  private String startDate;
  private String endDate;

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
}
