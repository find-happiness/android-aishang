package com.aishang.app.data.model;

import java.util.List;

/**
 * Created by song on 2016/3/26.
 */
public class JMyVacationListResult {

  /**
   * result : success
   * summary : {"vaCount":2,"vaCreditTotal":0,"vaCreditUsed":0,"vaCreditLeft":0,"exCreditTotal":0,"exCreditUsed":0,"exCreditLeft":0,"awardTotal":1002,"awardUsed":200,"awardLeft":802}
   * myVaList : [{"idx":0,"cardName":"全球养生旅居卡","benefit":"陶卫","signDate":"2014-12-31","hotelName":"重庆-金竹雲山|单间配套","hotelID":16,"hotelGUID":"","roomText":"1栋1单元-3-3-43","lat":0,"lng":0,"cardID":"NO.023011414","contractID":"","effectiveDate":"2015-12-31","daysYearLimit":0,"daysYearLimitExpire":"","expireDate":"2044-07-01","daysYearNormal":28,"creditTotal":0,"creditUsed":0,"creditLeft":0,"creditLimitTotal":0,"creditLimitUsed":0,"creditLimitLeft":0,"apartID":"1栋1单元-3-3-43","lengthOfService":40,"earningsTotal":16772,"earningsUsed":0,"earningsLeft":16772,"buyprice":119800,"earningsYear":0,"status":1,"type":0,"roomGUID":""},{"idx":0,"cardName":"全球养生托管卡","benefit":"陶卫","signDate":"2014-12-31","hotelName":"重庆-金竹雲山|单间配套","hotelID":16,"hotelGUID":"","roomText":"1栋1单元-3-3-43","lat":0,"lng":0,"cardID":"NO.023011412","contractID":"","effectiveDate":"2015-12-31","daysYearLimit":0,"daysYearLimitExpire":"","expireDate":"2044-07-01","daysYearNormal":28,"creditTotal":0,"creditUsed":0,"creditLeft":0,"creditLimitTotal":0,"creditLimitUsed":0,"creditLimitLeft":0,"apartID":"1栋1单元-3-3-43","lengthOfService":40,"earningsTotal":16772,"earningsUsed":0,"earningsLeft":16772,"buyprice":119800,"earningsYear":0,"status":1,"type":1,"roomGUID":"73280279-27d3-411e-9f16-81fae1d92537"}]
   */

  private String result;
  /**
   * vaCount : 2
   * vaCreditTotal : 0
   * vaCreditUsed : 0
   * vaCreditLeft : 0
   * exCreditTotal : 0
   * exCreditUsed : 0
   * exCreditLeft : 0
   * awardTotal : 1002
   * awardUsed : 200
   * awardLeft : 802
   */

  private JMyVacationListSummary summary;
  /**
   * idx : 0
   * cardName : 全球养生旅居卡
   * benefit : 陶卫
   * signDate : 2014-12-31
   * hotelName : 重庆-金竹雲山|单间配套
   * hotelID : 16
   * hotelGUID :
   * roomText : 1栋1单元-3-3-43
   * lat : 0
   * lng : 0
   * cardID : NO.023011414
   * contractID :
   * effectiveDate : 2015-12-31
   * daysYearLimit : 0
   * daysYearLimitExpire :
   * expireDate : 2044-07-01
   * daysYearNormal : 28
   * creditTotal : 0
   * creditUsed : 0
   * creditLeft : 0
   * creditLimitTotal : 0
   * creditLimitUsed : 0
   * creditLimitLeft : 0
   * apartID : 1栋1单元-3-3-43
   * lengthOfService : 40
   * earningsTotal : 16772
   * earningsUsed : 0
   * earningsLeft : 16772
   * buyprice : 119800
   * earningsYear : 0
   * status : 1
   * type : 0
   * roomGUID :
   */

  private JMyVacationListMyVaList[] myVaList;
  /**
   * excardID : 2
   * cardNumber : NO.20160729151515
   * integral : 2391
   */

  private List<MyexCardListEntity> myexCardList;
  private List<?> hotelRoomCatList;
  /**
   * ID : 2
   * cardNumber : NO.20160729151515
   * memberGUID : 3b4201fd-1e1c-4525-9dda-d3074e35cc05
   * refModel : hotel
   * refID : 175
   * validDate : 2017-09-01T00:00:00
   * integral : 2391
   * createTime : 2016-07-29T15:15:37
   * comment : 测试
   */

  private List<MemberexCardListEntity> memberexCardList;

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public JMyVacationListSummary getSummary() {
    return summary;
  }

  public void setSummary(JMyVacationListSummary summary) {
    this.summary = summary;
  }

  public JMyVacationListMyVaList[] getMyVaList() {
    return myVaList;
  }

  public void setMyVaList(JMyVacationListMyVaList[] myVaList) {
    this.myVaList = myVaList;
  }

  public List<MyexCardListEntity> getMyexCardList() {
    return myexCardList;
  }

  public void setMyexCardList(List<MyexCardListEntity> myexCardList) {
    this.myexCardList = myexCardList;
  }

  public List<?> getHotelRoomCatList() {
    return hotelRoomCatList;
  }

  public void setHotelRoomCatList(List<?> hotelRoomCatList) {
    this.hotelRoomCatList = hotelRoomCatList;
  }

  public List<MemberexCardListEntity> getMemberexCardList() {
    return memberexCardList;
  }

  public void setMemberexCardList(List<MemberexCardListEntity> memberexCardList) {
    this.memberexCardList = memberexCardList;
  }

  public static class JMyVacationListSummary {
    private int vaCount;
    private int vaCreditTotal;
    private int vaCreditUsed;
    private int vaCreditLeft;
    private int exCreditTotal;
    private int exCreditUsed;
    private int exCreditLeft;
    private float awardTotal;
    private float awardUsed;
    private float awardLeft;

    public int getVaCount() {
      return vaCount;
    }

    public void setVaCount(int vaCount) {
      this.vaCount = vaCount;
    }

    public int getVaCreditTotal() {
      return vaCreditTotal;
    }

    public void setVaCreditTotal(int vaCreditTotal) {
      this.vaCreditTotal = vaCreditTotal;
    }

    public int getVaCreditUsed() {
      return vaCreditUsed;
    }

    public void setVaCreditUsed(int vaCreditUsed) {
      this.vaCreditUsed = vaCreditUsed;
    }

    public int getVaCreditLeft() {
      return vaCreditLeft;
    }

    public void setVaCreditLeft(int vaCreditLeft) {
      this.vaCreditLeft = vaCreditLeft;
    }

    public int getExCreditTotal() {
      return exCreditTotal;
    }

    public void setExCreditTotal(int exCreditTotal) {
      this.exCreditTotal = exCreditTotal;
    }

    public int getExCreditUsed() {
      return exCreditUsed;
    }

    public void setExCreditUsed(int exCreditUsed) {
      this.exCreditUsed = exCreditUsed;
    }

    public int getExCreditLeft() {
      return exCreditLeft;
    }

    public void setExCreditLeft(int exCreditLeft) {
      this.exCreditLeft = exCreditLeft;
    }

    public float getAwardTotal() {
      return awardTotal;
    }

    public void setAwardTotal(int awardTotal) {
      this.awardTotal = awardTotal;
    }

    public float getAwardUsed() {
      return awardUsed;
    }

    public void setAwardUsed(int awardUsed) {
      this.awardUsed = awardUsed;
    }

    public float getAwardLeft() {
      return awardLeft;
    }

    public void setAwardLeft(int awardLeft) {
      this.awardLeft = awardLeft;
    }
  }

  public static class JMyVacationListMyVaList {
    private int idx;
    private String cardName;
    private String benefit;
    private String signDate;
    private String hotelName;
    private int hotelID;
    private String hotelGUID;
    private String roomText;
    private int lat;
    private int lng;
    private String cardID;
    private String contractID;
    private String effectiveDate;
    private int daysYearLimit;
    private String daysYearLimitExpire;
    private String expireDate;
    private int daysYearNormal;
    private int creditTotal;
    private int creditUsed;
    private int creditLeft;
    private int creditLimitTotal;
    private int creditLimitUsed;
    private int creditLimitLeft;
    private String apartID;
    private int lengthOfService;
    private int earningsTotal;
    private int earningsUsed;
    private int earningsLeft;
    private int buyprice;
    private int earningsYear;
    private int status;
    private int type;
    private String roomGUID;

    public int getIdx() {
      return idx;
    }

    public void setIdx(int idx) {
      this.idx = idx;
    }

    public String getCardName() {
      return cardName;
    }

    public void setCardName(String cardName) {
      this.cardName = cardName;
    }

    public String getBenefit() {
      return benefit;
    }

    public void setBenefit(String benefit) {
      this.benefit = benefit;
    }

    public String getSignDate() {
      return signDate;
    }

    public void setSignDate(String signDate) {
      this.signDate = signDate;
    }

    public String getHotelName() {
      return hotelName;
    }

    public void setHotelName(String hotelName) {
      this.hotelName = hotelName;
    }

    public int getHotelID() {
      return hotelID;
    }

    public void setHotelID(int hotelID) {
      this.hotelID = hotelID;
    }

    public String getHotelGUID() {
      return hotelGUID;
    }

    public void setHotelGUID(String hotelGUID) {
      this.hotelGUID = hotelGUID;
    }

    public String getRoomText() {
      return roomText;
    }

    public void setRoomText(String roomText) {
      this.roomText = roomText;
    }

    public int getLat() {
      return lat;
    }

    public void setLat(int lat) {
      this.lat = lat;
    }

    public int getLng() {
      return lng;
    }

    public void setLng(int lng) {
      this.lng = lng;
    }

    public String getCardID() {
      return cardID;
    }

    public void setCardID(String cardID) {
      this.cardID = cardID;
    }

    public String getContractID() {
      return contractID;
    }

    public void setContractID(String contractID) {
      this.contractID = contractID;
    }

    public String getEffectiveDate() {
      return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
      this.effectiveDate = effectiveDate;
    }

    public int getDaysYearLimit() {
      return daysYearLimit;
    }

    public void setDaysYearLimit(int daysYearLimit) {
      this.daysYearLimit = daysYearLimit;
    }

    public String getDaysYearLimitExpire() {
      return daysYearLimitExpire;
    }

    public void setDaysYearLimitExpire(String daysYearLimitExpire) {
      this.daysYearLimitExpire = daysYearLimitExpire;
    }

    public String getExpireDate() {
      return expireDate;
    }

    public void setExpireDate(String expireDate) {
      this.expireDate = expireDate;
    }

    public int getDaysYearNormal() {
      return daysYearNormal;
    }

    public void setDaysYearNormal(int daysYearNormal) {
      this.daysYearNormal = daysYearNormal;
    }

    public int getCreditTotal() {
      return creditTotal;
    }

    public void setCreditTotal(int creditTotal) {
      this.creditTotal = creditTotal;
    }

    public int getCreditUsed() {
      return creditUsed;
    }

    public void setCreditUsed(int creditUsed) {
      this.creditUsed = creditUsed;
    }

    public int getCreditLeft() {
      return creditLeft;
    }

    public void setCreditLeft(int creditLeft) {
      this.creditLeft = creditLeft;
    }

    public int getCreditLimitTotal() {
      return creditLimitTotal;
    }

    public void setCreditLimitTotal(int creditLimitTotal) {
      this.creditLimitTotal = creditLimitTotal;
    }

    public int getCreditLimitUsed() {
      return creditLimitUsed;
    }

    public void setCreditLimitUsed(int creditLimitUsed) {
      this.creditLimitUsed = creditLimitUsed;
    }

    public int getCreditLimitLeft() {
      return creditLimitLeft;
    }

    public void setCreditLimitLeft(int creditLimitLeft) {
      this.creditLimitLeft = creditLimitLeft;
    }

    public String getApartID() {
      return apartID;
    }

    public void setApartID(String apartID) {
      this.apartID = apartID;
    }

    public int getLengthOfService() {
      return lengthOfService;
    }

    public void setLengthOfService(int lengthOfService) {
      this.lengthOfService = lengthOfService;
    }

    public int getEarningsTotal() {
      return earningsTotal;
    }

    public void setEarningsTotal(int earningsTotal) {
      this.earningsTotal = earningsTotal;
    }

    public int getEarningsUsed() {
      return earningsUsed;
    }

    public void setEarningsUsed(int earningsUsed) {
      this.earningsUsed = earningsUsed;
    }

    public int getEarningsLeft() {
      return earningsLeft;
    }

    public void setEarningsLeft(int earningsLeft) {
      this.earningsLeft = earningsLeft;
    }

    public int getBuyprice() {
      return buyprice;
    }

    public void setBuyprice(int buyprice) {
      this.buyprice = buyprice;
    }

    public int getEarningsYear() {
      return earningsYear;
    }

    public void setEarningsYear(int earningsYear) {
      this.earningsYear = earningsYear;
    }

    public int getStatus() {
      return status;
    }

    public void setStatus(int status) {
      this.status = status;
    }

    public int getType() {
      return type;
    }

    public void setType(int type) {
      this.type = type;
    }

    public String getRoomGUID() {
      return roomGUID;
    }

    public void setRoomGUID(String roomGUID) {
      this.roomGUID = roomGUID;
    }
  }

  public static class MyexCardListEntity {
    private int excardID;
    private String cardNumber;
    private int integral;

    public int getExcardID() {
      return excardID;
    }

    public void setExcardID(int excardID) {
      this.excardID = excardID;
    }

    public String getCardNumber() {
      return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
      this.cardNumber = cardNumber;
    }

    public int getIntegral() {
      return integral;
    }

    public void setIntegral(int integral) {
      this.integral = integral;
    }
  }

  public static class MemberexCardListEntity {
    private int ID;
    private String cardNumber;
    private String memberGUID;
    private String refModel;
    private String refID;
    private String validDate;
    private int integral;
    private String createTime;
    private String comment;

    public int getID() {
      return ID;
    }

    public void setID(int ID) {
      this.ID = ID;
    }

    public String getCardNumber() {
      return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
      this.cardNumber = cardNumber;
    }

    public String getMemberGUID() {
      return memberGUID;
    }

    public void setMemberGUID(String memberGUID) {
      this.memberGUID = memberGUID;
    }

    public String getRefModel() {
      return refModel;
    }

    public void setRefModel(String refModel) {
      this.refModel = refModel;
    }

    public String getRefID() {
      return refID;
    }

    public void setRefID(String refID) {
      this.refID = refID;
    }

    public String getValidDate() {
      return validDate;
    }

    public void setValidDate(String validDate) {
      this.validDate = validDate;
    }

    public int getIntegral() {
      return integral;
    }

    public void setIntegral(int integral) {
      this.integral = integral;
    }

    public String getCreateTime() {
      return createTime;
    }

    public void setCreateTime(String createTime) {
      this.createTime = createTime;
    }

    public String getComment() {
      return comment;
    }

    public void setComment(String comment) {
      this.comment = comment;
    }
  }
}
