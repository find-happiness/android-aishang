package com.aishang.app.data.model;

import java.util.List;

/**
 * Created by song on 2016/5/13.
 */
public class JCommonIntegralResult {

  /**
   * result : success
   * houseList : [{"id":8957,"memberID":4502,"cardID":"NO.023000317","cardValidateStart":"","cardGUID":"a59f90df-00d3-42d5-ba34-ece08a290436","dealTime":"2016-04-15T16:48:30","dealMerchant":"海口观澜湖哈利路亚酒店公寓","dealType":2,"preAmount":0,"amount":-239,"freeCredit":239,"limitCredit":0,"comment":"用户消费","createTime":"2016-04-15T16:48:30","referModel":"vaReservation","referPKID":"2441"},{"id":8955,"memberID":4502,"cardID":"NO.023000317","cardValidateStart":"","cardGUID":"a59f90df-00d3-42d5-ba34-ece08a290436","dealTime":"2016-04-13T22:28:17","dealMerchant":"海口观澜湖哈利路亚酒店公寓","dealType":2,"preAmount":0,"amount":-239,"freeCredit":239,"limitCredit":0,"comment":"用户消费","createTime":"2016-04-13T22:28:17","referModel":"vaReservation","referPKID":"2439"},{"id":8954,"memberID":4502,"cardID":"NO.023000317","cardValidateStart":"","cardGUID":"a59f90df-00d3-42d5-ba34-ece08a290436","dealTime":"2016-03-21T11:47:03","dealMerchant":"北戴河海洋花园别墅度假村","dealType":2,"preAmount":0,"amount":-100,"freeCredit":100,"limitCredit":0,"comment":"用户消费","createTime":"2016-03-21T11:47:03","referModel":"vaReservation","referPKID":"2438"},{"id":8953,"memberID":4502,"cardID":"NO.023000317","cardValidateStart":"","cardGUID":"a59f90df-00d3-42d5-ba34-ece08a290436","dealTime":"2016-03-21T11:44:35","dealMerchant":"北戴河海洋花园别墅度假村","dealType":2,"preAmount":0,"amount":-100,"freeCredit":100,"limitCredit":0,"comment":"用户消费","createTime":"2016-03-21T11:44:35","referModel":"vaReservation","referPKID":"2437"},{"id":6874,"memberID":4502,"cardID":"","cardValidateStart":"","cardGUID":"00000000-0000-0000-0000-000000000000","dealTime":"2014-10-24T11:15:41","dealMerchant":"分享活动赠送通用限制积分","dealType":5,"preAmount":0,"amount":1,"freeCredit":0,"limitCredit":0,"comment":"(4502)唐昌春","createTime":"2014-10-24T11:15:41","referModel":"share","referPKID":"4502"}]
   * totalCount : 5
   * creditLeft : 0
   * awardLeft : 801.4
   */

  private String result;
  private int totalCount;
  private int creditLeft;
  private double awardLeft;
  /**
   * id : 8957
   * memberID : 4502
   * cardID : NO.023000317
   * cardValidateStart :
   * cardGUID : a59f90df-00d3-42d5-ba34-ece08a290436
   * dealTime : 2016-04-15T16:48:30
   * dealMerchant : 海口观澜湖哈利路亚酒店公寓
   * dealType : 2
   * preAmount : 0
   * amount : -239
   * freeCredit : 239
   * limitCredit : 0
   * comment : 用户消费
   * createTime : 2016-04-15T16:48:30
   * referModel : vaReservation
   * referPKID : 2441
   */

  private List<HouseListBean> houseList;

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public int getCreditLeft() {
    return creditLeft;
  }

  public void setCreditLeft(int creditLeft) {
    this.creditLeft = creditLeft;
  }

  public double getAwardLeft() {
    return awardLeft;
  }

  public void setAwardLeft(double awardLeft) {
    this.awardLeft = awardLeft;
  }

  public List<HouseListBean> getHouseList() {
    return houseList;
  }

  public void setHouseList(List<HouseListBean> houseList) {
    this.houseList = houseList;
  }

  public static class HouseListBean {
    private int id;
    private int memberID;
    private String cardID;
    private String cardValidateStart;
    private String cardGUID;
    private String dealTime;
    private String dealMerchant;
    private int dealType;
    private int preAmount;
    private int amount;
    private int freeCredit;
    private int limitCredit;
    private String comment;
    private String createTime;
    private String referModel;
    private String referPKID;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public int getMemberID() {
      return memberID;
    }

    public void setMemberID(int memberID) {
      this.memberID = memberID;
    }

    public String getCardID() {
      return cardID;
    }

    public void setCardID(String cardID) {
      this.cardID = cardID;
    }

    public String getCardValidateStart() {
      return cardValidateStart;
    }

    public void setCardValidateStart(String cardValidateStart) {
      this.cardValidateStart = cardValidateStart;
    }

    public String getCardGUID() {
      return cardGUID;
    }

    public void setCardGUID(String cardGUID) {
      this.cardGUID = cardGUID;
    }

    public String getDealTime() {
      return dealTime;
    }

    public void setDealTime(String dealTime) {
      this.dealTime = dealTime;
    }

    public String getDealMerchant() {
      return dealMerchant;
    }

    public void setDealMerchant(String dealMerchant) {
      this.dealMerchant = dealMerchant;
    }

    public int getDealType() {
      return dealType;
    }

    public void setDealType(int dealType) {
      this.dealType = dealType;
    }

    public int getPreAmount() {
      return preAmount;
    }

    public void setPreAmount(int preAmount) {
      this.preAmount = preAmount;
    }

    public int getAmount() {
      return amount;
    }

    public void setAmount(int amount) {
      this.amount = amount;
    }

    public int getFreeCredit() {
      return freeCredit;
    }

    public void setFreeCredit(int freeCredit) {
      this.freeCredit = freeCredit;
    }

    public int getLimitCredit() {
      return limitCredit;
    }

    public void setLimitCredit(int limitCredit) {
      this.limitCredit = limitCredit;
    }

    public String getComment() {
      return comment;
    }

    public void setComment(String comment) {
      this.comment = comment;
    }

    public String getCreateTime() {
      return createTime;
    }

    public void setCreateTime(String createTime) {
      this.createTime = createTime;
    }

    public String getReferModel() {
      return referModel;
    }

    public void setReferModel(String referModel) {
      this.referModel = referModel;
    }

    public String getReferPKID() {
      return referPKID;
    }

    public void setReferPKID(String referPKID) {
      this.referPKID = referPKID;
    }
  }
}
