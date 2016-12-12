package com.aishang.app.data.model;

/**
 * Created by happiness on 2016/6/7.
 */
public class JMemberGiftcardParam {

  /**
   * memberAccount : 13883224451
   * cookie : 8F2EDCF4A3C7C0C89E0F3BD413D25972
   * pageIndex : 1
   * pageSize : 6
   * status :
   */

  private String memberAccount;
  private String cookie;
  private String pageIndex;
  private String pageSize;
  private String status;
  private String hotelID;

  public String getHotelID() {
    return hotelID;
  }

  public void setHotelID(String hotelID) {
    this.hotelID = hotelID;
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

  public String getPageIndex() {
    return pageIndex;
  }

  public void setPageIndex(String pageIndex) {
    this.pageIndex = pageIndex;
  }

  public String getPageSize() {
    return pageSize;
  }

  public void setPageSize(String pageSize) {
    this.pageSize = pageSize;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
