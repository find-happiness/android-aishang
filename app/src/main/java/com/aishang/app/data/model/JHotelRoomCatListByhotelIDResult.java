package com.aishang.app.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by song on 2016/3/11.
 */
public class JHotelRoomCatListByhotelIDResult implements Serializable{

  /**
   * result : success
   * hotelRoomCatList : [{"roomCatID":439,"hotelID":161,"roomCatName":"2-1","imageUrl":"","status":1,"description":"暂无","count":1,"roomTypeID":3,"totalAreaMin":20,"totalAreaMax":30,"bedTypeID":0,"bedCount":1,"basicPrice":100,"prePayedFee":100,"maxGuest":1,"floors":"2","comment":"暂无","createTime":"2016-03-02T13:36:06","updateTime":"2016-03-02T13:36:06","dispOrder":999,"ImagesList":["/upload/hotel/161/439/201603041538100953.jpg","/upload/hotel/161/439/201603101402116157.jpg","/upload/hotel/161/439/201603101402143458.jpg"],"bedTypeName":""},{"roomCatID":424,"hotelID":161,"roomCatName":"个性化别墅","imageUrl":"/upload/hotel/161/424/201602041421166576.jpg","status":1,"description":"暂无","count":1,"roomTypeID":3,"totalAreaMin":100,"totalAreaMax":300,"bedTypeID":1,"bedCount":2,"basicPrice":700,"prePayedFee":700,"maxGuest":4,"floors":"2","comment":"暂无","createTime":"2016-02-04T14:21:20","updateTime":"2016-02-04T14:21:20","dispOrder":999,"ImagesList":["/upload/hotel/161/424/201603041507593487.jpg"],"bedTypeName":"大床"},{"roomCatID":423,"hotelID":161,"roomCatName":"海景别墅","imageUrl":"/upload/hotel/161/423/201602041419518477.jpg","status":1,"description":"暂无","count":1,"roomTypeID":5,"totalAreaMin":100,"totalAreaMax":300,"bedTypeID":1,"bedCount":4,"basicPrice":800,"prePayedFee":800,"maxGuest":4,"floors":"2","comment":"暂无","createTime":"2016-02-04T14:19:54","updateTime":"2016-02-04T14:19:54","dispOrder":999,"ImagesList":"","bedTypeName":"大床"}]
   * gRoomTypeList : [{"roomTypeID":1,"roomTypeName":"别墅","dispOrder":9999,"createTime":"2013-09-01T14:55:16"},{"roomTypeID":2,"roomTypeName":"标准间","dispOrder":9999,"createTime":"2013-09-01T14:55:16"},{"roomTypeID":3,"roomTypeName":"一室一厅","dispOrder":9999,"createTime":"2013-09-01T14:55:16"},{"roomTypeID":4,"roomTypeName":"花园洋房","dispOrder":9999,"createTime":"2013-09-09T12:01:28"},{"roomTypeID":5,"roomTypeName":"三室及以上","dispOrder":9999,"createTime":"2013-09-09T12:01:49"},{"roomTypeID":6,"roomTypeName":"","dispOrder":9999,"createTime":"2016-03-02T16:14:42"}]
   */

  private String result;
  /**
   * roomCatID : 439
   * hotelID : 161
   * roomCatName : 2-1
   * imageUrl :
   * status : 1
   * description : 暂无
   * count : 1
   * roomTypeID : 3
   * totalAreaMin : 20
   * totalAreaMax : 30
   * bedTypeID : 0
   * bedCount : 1
   * basicPrice : 100
   * prePayedFee : 100
   * maxGuest : 1
   * floors : 2
   * comment : 暂无
   * createTime : 2016-03-02T13:36:06
   * updateTime : 2016-03-02T13:36:06
   * dispOrder : 999
   * ImagesList : ["/upload/hotel/161/439/201603041538100953.jpg","/upload/hotel/161/439/201603101402116157.jpg","/upload/hotel/161/439/201603101402143458.jpg"]
   * bedTypeName :
   */

  private List<HotelRoomCatListEntity> hotelRoomCatList;
  /**
   * roomTypeID : 1
   * roomTypeName : 别墅
   * dispOrder : 9999
   * createTime : 2013-09-01T14:55:16
   */

  private List<GRoomTypeListEntity> gRoomTypeList;

  public void setResult(String result) {
    this.result = result;
  }

  public void setHotelRoomCatList(List<HotelRoomCatListEntity> hotelRoomCatList) {
    this.hotelRoomCatList = hotelRoomCatList;
  }

  public void setGRoomTypeList(List<GRoomTypeListEntity> gRoomTypeList) {
    this.gRoomTypeList = gRoomTypeList;
  }

  public String getResult() {
    return result;
  }

  public List<HotelRoomCatListEntity> getHotelRoomCatList() {
    return hotelRoomCatList;
  }

  public List<GRoomTypeListEntity> getGRoomTypeList() {
    return gRoomTypeList;
  }

  public static class HotelRoomCatListEntity implements Serializable{
    private int roomCatID;
    private int hotelID;
    private String roomCatName;
    private String imageUrl;
    private int status;
    private String description;
    private int count;
    private int roomTypeID;
    private int totalAreaMin;
    private int totalAreaMax;
    private int bedTypeID;
    private int bedCount;
    private int basicPrice;
    private int prePayedFee;
    private int maxGuest;
    private String floors;
    private String comment;
    private String createTime;
    private String updateTime;
    private int dispOrder;
    private String bedTypeName;
    //private List<String> ImagesList;

    public void setRoomCatID(int roomCatID) {
      this.roomCatID = roomCatID;
    }

    public void setHotelID(int hotelID) {
      this.hotelID = hotelID;
    }

    public void setRoomCatName(String roomCatName) {
      this.roomCatName = roomCatName;
    }

    public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
    }

    public void setStatus(int status) {
      this.status = status;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public void setCount(int count) {
      this.count = count;
    }

    public void setRoomTypeID(int roomTypeID) {
      this.roomTypeID = roomTypeID;
    }

    public void setTotalAreaMin(int totalAreaMin) {
      this.totalAreaMin = totalAreaMin;
    }

    public void setTotalAreaMax(int totalAreaMax) {
      this.totalAreaMax = totalAreaMax;
    }

    public void setBedTypeID(int bedTypeID) {
      this.bedTypeID = bedTypeID;
    }

    public void setBedCount(int bedCount) {
      this.bedCount = bedCount;
    }

    public void setBasicPrice(int basicPrice) {
      this.basicPrice = basicPrice;
    }

    public void setPrePayedFee(int prePayedFee) {
      this.prePayedFee = prePayedFee;
    }

    public void setMaxGuest(int maxGuest) {
      this.maxGuest = maxGuest;
    }

    public void setFloors(String floors) {
      this.floors = floors;
    }

    public void setComment(String comment) {
      this.comment = comment;
    }

    public void setCreateTime(String createTime) {
      this.createTime = createTime;
    }

    public void setUpdateTime(String updateTime) {
      this.updateTime = updateTime;
    }

    public void setDispOrder(int dispOrder) {
      this.dispOrder = dispOrder;
    }

    public void setBedTypeName(String bedTypeName) {
      this.bedTypeName = bedTypeName;
    }

    //public void setImagesList(List<String> ImagesList) {
    //  this.ImagesList = ImagesList;
    //}

    public int getRoomCatID() {
      return roomCatID;
    }

    public int getHotelID() {
      return hotelID;
    }

    public String getRoomCatName() {
      return roomCatName;
    }

    public String getImageUrl() {
      return imageUrl;
    }

    public int getStatus() {
      return status;
    }

    public String getDescription() {
      return description;
    }

    public int getCount() {
      return count;
    }

    public int getRoomTypeID() {
      return roomTypeID;
    }

    public int getTotalAreaMin() {
      return totalAreaMin;
    }

    public int getTotalAreaMax() {
      return totalAreaMax;
    }

    public int getBedTypeID() {
      return bedTypeID;
    }

    public int getBedCount() {
      return bedCount;
    }

    public int getBasicPrice() {
      return basicPrice;
    }

    public int getPrePayedFee() {
      return prePayedFee;
    }

    public int getMaxGuest() {
      return maxGuest;
    }

    public String getFloors() {
      return floors;
    }

    public String getComment() {
      return comment;
    }

    public String getCreateTime() {
      return createTime;
    }

    public String getUpdateTime() {
      return updateTime;
    }

    public int getDispOrder() {
      return dispOrder;
    }

    public String getBedTypeName() {
      return bedTypeName;
    }

    //public List<String> getImagesList() {
    //  return ImagesList;
    //}
  }

  public static class GRoomTypeListEntity implements Serializable{
    private int roomTypeID;
    private String roomTypeName;
    private int dispOrder;
    private String createTime;

    public void setRoomTypeID(int roomTypeID) {
      this.roomTypeID = roomTypeID;
    }

    public void setRoomTypeName(String roomTypeName) {
      this.roomTypeName = roomTypeName;
    }

    public void setDispOrder(int dispOrder) {
      this.dispOrder = dispOrder;
    }

    public void setCreateTime(String createTime) {
      this.createTime = createTime;
    }

    public int getRoomTypeID() {
      return roomTypeID;
    }

    public String getRoomTypeName() {
      return roomTypeName;
    }

    public int getDispOrder() {
      return dispOrder;
    }

    public String getCreateTime() {
      return createTime;
    }
  }
}
