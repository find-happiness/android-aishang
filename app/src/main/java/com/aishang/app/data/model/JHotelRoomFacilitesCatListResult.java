package com.aishang.app.data.model;

import java.util.List;

/**
 * Created by song on 2016/3/27.
 */
public class JHotelRoomFacilitesCatListResult {

  /**
   * result : success
   * hotelRoomFacilitesCatList : [{"roomFacilitesCatID":1,"facilitesType":1,"facilitesName":"宽带上网","imageUrl":"
   * ","dispOrder":99,"createTime":"2013-10-15T09:52:20"},{"roomFacilitesCatID":2,"facilitesType":1,"facilitesName":"空调","imageUrl":"
   * ","dispOrder":99,"createTime":"2013-10-15T09:52:20"},{"roomFacilitesCatID":3,"facilitesType":1,"facilitesName":"24小时热水","imageUrl":"
   * ","dispOrder":99,"createTime":"2013-10-15T09:52:20"},{"roomFacilitesCatID":4,"facilitesType":1,"facilitesName":"吹风机","imageUrl":"99","dispOrder":99,"createTime":"2013-10-15T09:52:20"},{"roomFacilitesCatID":5,"facilitesType":2,"facilitesName":"沙发","imageUrl":"","dispOrder":99,"createTime":"2016-02-29T14:30:44"}]
   */

  private String result;
  /**
   * roomFacilitesCatID : 1
   * facilitesType : 1
   * facilitesName : 宽带上网
   * imageUrl :
   * dispOrder : 99
   * createTime : 2013-10-15T09:52:20
   */

  private List<HotelRoomFacilitesCatListEntity> hotelRoomFacilitesCatList;

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public List<HotelRoomFacilitesCatListEntity> getHotelRoomFacilitesCatList() {
    return hotelRoomFacilitesCatList;
  }

  public void setHotelRoomFacilitesCatList(
      List<HotelRoomFacilitesCatListEntity> hotelRoomFacilitesCatList) {
    this.hotelRoomFacilitesCatList = hotelRoomFacilitesCatList;
  }

  public static class HotelRoomFacilitesCatListEntity {
    private int roomFacilitesCatID;
    private int facilitesType;
    private String facilitesName;
    private String imageUrl;
    private int dispOrder;
    private String createTime;

    public int getRoomFacilitesCatID() {
      return roomFacilitesCatID;
    }

    public void setRoomFacilitesCatID(int roomFacilitesCatID) {
      this.roomFacilitesCatID = roomFacilitesCatID;
    }

    public int getFacilitesType() {
      return facilitesType;
    }

    public void setFacilitesType(int facilitesType) {
      this.facilitesType = facilitesType;
    }

    public String getFacilitesName() {
      return facilitesName;
    }

    public void setFacilitesName(String facilitesName) {
      this.facilitesName = facilitesName;
    }

    public String getImageUrl() {
      return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
    }

    public int getDispOrder() {
      return dispOrder;
    }

    public void setDispOrder(int dispOrder) {
      this.dispOrder = dispOrder;
    }

    public String getCreateTime() {
      return createTime;
    }

    public void setCreateTime(String createTime) {
      this.createTime = createTime;
    }
  }
}
