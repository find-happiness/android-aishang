package com.aishang.app.data.model;

/**
 * Created by song on 2016/9/14.
 */
public class JHotelRoomPriceParam {
  int hotelID;
  int roomCatID;
  String sDate;
  String eDate;

  public JHotelRoomPriceParam(int hotelID, int roomCatID, String sDate, String eDate) {
    this.hotelID = hotelID;
    this.roomCatID = roomCatID;
    this.sDate = sDate;
    this.eDate = eDate;
  }

  public int getHotelID() {
    return hotelID;
  }

  public void setHotelID(int hotelID) {
    this.hotelID = hotelID;
  }

  public int getRoomCatID() {
    return roomCatID;
  }

  public void setRoomCatID(int roomCatID) {
    this.roomCatID = roomCatID;
  }

  public String getsDate() {
    return sDate;
  }

  public void setsDate(String sDate) {
    this.sDate = sDate;
  }

  public String geteDate() {
    return eDate;
  }

  public void seteDate(String eDate) {
    this.eDate = eDate;
  }
}
