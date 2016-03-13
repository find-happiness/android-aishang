package com.aishang.app.data.model;

/**
 * Created by song on 2016/3/13.
 */
public class JParticipationParam {

  /**
   * memberAccount : 13883224451
   * cookie : 0CCDAF264247461921FD4B1FEFF372B1
   * recStart : 0
   * recCount : 10
   */

  private String memberAccount;
  private String cookie;
  private int recStart;
  private int recCount;

  public void setMemberAccount(String memberAccount) {
    this.memberAccount = memberAccount;
  }

  public void setCookie(String cookie) {
    this.cookie = cookie;
  }

  public void setRecStart(int recStart) {
    this.recStart = recStart;
  }

  public void setRecCount(int recCount) {
    this.recCount = recCount;
  }

  public String getMemberAccount() {
    return memberAccount;
  }

  public String getCookie() {
    return cookie;
  }

  public int getRecStart() {
    return recStart;
  }

  public int getRecCount() {
    return recCount;
  }
}
