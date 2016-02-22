package com.aishang.app.data.model;

/**
 * Created by song on 2016/2/22.
 */
public class JSubscriptionParam {
  //{ rentalID:string name:string, phone:string, address:string,
  //    negotiationTime:string,  zoneIDLevel2:string,  zoneIDLevel3:string,
  //    zoneIDLevel4:string,  zoneIText:string}

  private String rentalID;
  private String name;
  private String phone;
  private String address;
  private String negotiationTime;
  private String zoneIDLevel2;
  private String zoneIDLevel3;
  private String zoneIDLevel4;
  private String zoneIText;

  public String getRentalID() {
    return rentalID;
  }

  public void setRentalID(String rentalID) {
    this.rentalID = rentalID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getNegotiationTime() {
    return negotiationTime;
  }

  public void setNegotiationTime(String negotiationTime) {
    this.negotiationTime = negotiationTime;
  }

  public String getZoneIDLevel2() {
    return zoneIDLevel2;
  }

  public void setZoneIDLevel2(String zoneIDLevel2) {
    this.zoneIDLevel2 = zoneIDLevel2;
  }

  public String getZoneIDLevel3() {
    return zoneIDLevel3;
  }

  public void setZoneIDLevel3(String zoneIDLevel3) {
    this.zoneIDLevel3 = zoneIDLevel3;
  }

  public String getZoneIDLevel4() {
    return zoneIDLevel4;
  }

  public void setZoneIDLevel4(String zoneIDLevel4) {
    this.zoneIDLevel4 = zoneIDLevel4;
  }

  public String getZoneIText() {
    return zoneIText;
  }

  public void setZoneIText(String zoneIText) {
    this.zoneIText = zoneIText;
  }
}
