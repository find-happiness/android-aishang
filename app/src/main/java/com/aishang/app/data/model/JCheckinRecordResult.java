package com.aishang.app.data.model;

import java.util.List;

/**
 * Created by happiness on 2016/6/1.
 */
public class JCheckinRecordResult {

  /**
   * result : success
   * checkinRecordList : [{"GUID":"84bb3b9f-8c8e-4b42-b065-35252a11f6aa","orderID":"46b5b473-79a2-49cf-9e17-402f546a2997","guestType":1,"referID":"24e7be04-f478-4434-910a-7195c3b175a8","roomGUID":"73280279-27d3-411e-9f16-81fae1d92537","roomName":"qe","roomNo":"123","contacts":"123","contactPHone":"18716269169","checkinName":"123","checkinIDcard":"5xxxxxxxxxxxxxxxxxxxxx5","inTime":"2016-05-19T00:00:00","outTime":"2016-05-20T00:00:00","checkInNum":1,"payType":1,"credit":1,"roomCharge":500,"deposit":1,"guestNum":1,"guestNumComment":"1","source":1,"comment":"1","createTime":"2016-06-01T14:18:09","createBy":"b3a272e2-6444-4231-873e-9399d61ed684","createByName":"1"},{"GUID":"59356e8f-265a-4e69-9e79-5723b2bc8fbb","orderID":"567a7626-fa15-4f6e-840f-f5dfefb8eadd","guestType":1,"referID":"7dedb256-4c3d-4b90-a386-4417fb8083a0","roomGUID":"73280279-27d3-411e-9f16-81fae1d92537","roomName":"测c试","roomNo":"1028","contacts":"朱c笔帷","contactPHone":"18716269169","checkinName":"","checkinIDcard":"","inTime":"2016-05-22T00:00:00","outTime":"2016-05-28T00:00:00","checkInNum":1,"payType":1,"credit":"","roomCharge":1200,"deposit":1,"guestNum":3,"guestNumComment":"两c人","source":1,"comment":"预订c测试","createTime":"2016-05-18T17:53:43","createBy":"42816f29-04c9-4f65-8266-e90aee9cf89a","createByName":"C朱c笔帷"},{"GUID":"91d26b0e-65ff-40d4-abf0-91744ab845cc","orderID":"7fd3c492-8352-41f0-ac8d-ddaa10b643cf","guestType":1,"referID":"50601426-4e78-4218-893c-5367baf2d499","roomGUID":"73280279-27d3-411e-9f16-81fae1d92537","roomName":"阿西吧！","roomNo":"74748","contacts":"神兽保佑代码无BUG","contactPHone":"","checkinName":"神兽保佑代码无BUG","checkinIDcard":"5xxxxxxxxxxxxxxxxxxxxxx5","inTime":"2016-05-20T00:00:00","outTime":"2016-05-22T00:00:00","checkInNum":2,"payType":1,"credit":200,"roomCharge":800,"deposit":200,"guestNum":1,"guestNumComment":"神兽保佑代码无BUG","source":1,"comment":"神兽保佑代码无BUG","createTime":"2016-05-19T17:47:18","createBy":"d12374a1-41d7-4bfb-9ef3-603b2afdad45","createByName":"admin"},{"GUID":"1207ed63-597e-4d22-a76c-b2d69cb6f182","orderID":"707f6851-e07c-4de5-887f-c730cc98843a","guestType":1,"referID":"5ba1f1c8-eebe-414f-ad43-eb6acf018603","roomGUID":"73280279-27d3-411e-9f16-81fae1d92537","roomName":"ada","roomNo":"123","contacts":"addwdw","contactPHone":"","checkinName":"","checkinIDcard":"12313231213123131","inTime":"2016-05-21T00:00:00","outTime":"2016-05-23T00:00:00","checkInNum":2,"payType":1,"credit":121,"roomCharge":200,"deposit":200,"guestNum":1,"guestNumComment":"1","source":1,"comment":"1","createTime":"2016-05-19T18:05:32","createBy":"d2f89c52-f7ff-4805-b46c-dadb85fa5b51","createByName":"1"}]
   */

  private String result;
  /**
   * GUID : 84bb3b9f-8c8e-4b42-b065-35252a11f6aa
   * orderID : 46b5b473-79a2-49cf-9e17-402f546a2997
   * guestType : 1
   * referID : 24e7be04-f478-4434-910a-7195c3b175a8
   * roomGUID : 73280279-27d3-411e-9f16-81fae1d92537
   * roomName : qe
   * roomNo : 123
   * contacts : 123
   * contactPHone : 18716269169
   * checkinName : 123
   * checkinIDcard : 5xxxxxxxxxxxxxxxxxxxxx5
   * inTime : 2016-05-19T00:00:00
   * outTime : 2016-05-20T00:00:00
   * checkInNum : 1
   * payType : 1
   * credit : 1
   * roomCharge : 500
   * deposit : 1
   * guestNum : 1
   * guestNumComment : 1
   * source : 1
   * comment : 1
   * createTime : 2016-06-01T14:18:09
   * createBy : b3a272e2-6444-4231-873e-9399d61ed684
   * createByName : 1
   */

  private List<CheckinRecordListBean> checkinRecordList;

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public List<CheckinRecordListBean> getCheckinRecordList() {
    return checkinRecordList;
  }

  public void setCheckinRecordList(List<CheckinRecordListBean> checkinRecordList) {
    this.checkinRecordList = checkinRecordList;
  }

  public static class CheckinRecordListBean {

    private String inTime;
    private String outTime;

    private int payType;

    private int roomCharge;

    public String getInTime() {
      return inTime;
    }

    public void setInTime(String inTime) {
      this.inTime = inTime;
    }

    public String getOutTime() {
      return outTime;
    }

    public void setOutTime(String outTime) {
      this.outTime = outTime;
    }

    public int getPayType() {
      return payType;
    }

    public void setPayType(int payType) {
      this.payType = payType;
    }

    public int getRoomCharge() {
      return roomCharge;
    }

    public void setRoomCharge(int roomCharge) {
      this.roomCharge = roomCharge;
    }
  }
}
