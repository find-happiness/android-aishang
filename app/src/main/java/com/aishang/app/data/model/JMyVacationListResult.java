package com.aishang.app.data.model;

/**
 * Created by song on 2016/3/26.
 */
public class JMyVacationListResult {
  private String result;
  private JMyVacationListSummary summary;
  private JMyVacationListMyVaList[] myVaList;

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

  public class JMyVacationListSummary {
    /***
     * "vaCount": 4, "vaTotal": 0, "vaUsed": 0, "vaLeft": 0, "exTotal": 28,
     * "exUsed": 0, "exLeft": 28 vaCount:int, vaCreditTotal:int,
     * vaCreditUsed:int, vaCreditLeft:int, exCreditTotal:int,
     * exCreditUsed:int, exCreditLeft:int
     */

    private int vaCount;
    private int vaCreditTotal;
    private int vaCreditUsed;
    private int vaCreditLeft;
    private int exCreditTotal;
    private int exCreditUsed;
    private int exCreditLeft;

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
  }

  public class JMyVacationListMyVaList {

    /**
     * {idx:int, cardName:string, benefit:string,
     * signDate:string(YYYY-MM-DD), hotelName:string, hotelID:int,
     * hotelGUID:string, lat:decimal(10,2), lng:decimal(10,2),
     * cardID:string, contractID:string, effectiveDate:string,
     * expireDate:string (YYYY-MM-DD), daysYearLimit:int,
     * daysYearLimitExpire:string(YYYY-MM-DD), daysYearNormal:int,
     * creditTotal:int, creditUsed:int, creditLeft:int,
     * creditLimitTotal:int, creditLimitUsed:int, creditLimitLeft:int }
     */

    // private int curDays;
    // private int usedDays;
    // private int leftDays;

    private int idx;
    private String cardName;
    private String benefit;
    private String signDate;
    private String hotelName;
    private int hotelID;
    private String hotelGUID;
    //
    private float lat;
    private float lng;
    private String cardID;
    private String contractID;
    private String effectiveDate;
    private String expireDate;
    private int daysYearLimit;
    private String daysYearLimitExpire;
    private int daysYearNormal;
    private int creditTotal;
    private int creditUsed;
    private int creditLeft;
    private int creditLimitTotal;
    private int credtLimitUsed;
    private int creditLimitLeft;

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

    public int getCredtLimitUsed() {
      return credtLimitUsed;
    }

    public void setCredtLimitUsed(int credtLimitUsed) {
      this.credtLimitUsed = credtLimitUsed;
    }

    public int getCreditLimitLeft() {
      return creditLimitLeft;
    }

    public void setCreditLimitLeft(int creditLimitLeft) {
      this.creditLimitLeft = creditLimitLeft;
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

    public String getHotelGUID() {
      return hotelGUID;
    }

    public void setHotelGUID(String hotelGUID) {
      this.hotelGUID = hotelGUID;
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

    public String getExpireDate() {
      return expireDate;
    }

    public void setExpireDate(String expireDate) {
      this.expireDate = expireDate;
    }

    public int getIdx() {
      return idx;
    }

    public void setIdx(int idx) {
      this.idx = idx;
    }

    public int getHotelID() {
      return hotelID;
    }

    public void setHotelID(int hotelID) {
      this.hotelID = hotelID;
    }

    public int getDaysYearNormal() {
      return daysYearNormal;
    }

    public void setDaysYearNormal(int daysYearNormal) {
      this.daysYearNormal = daysYearNormal;
    }

    public float getLat() {
      return lat;
    }

    public void setLat(float lat) {
      this.lat = lat;
    }

    public float getLng() {
      return lng;
    }

    public void setLng(float lng) {
      this.lng = lng;
    }

    public String getDaysYearLimitExpire() {
      return daysYearLimitExpire;
    }

    public void setDaysYearLimitExpire(String daysYearLimitExpire) {
      this.daysYearLimitExpire = daysYearLimitExpire;
    }

    public int getDaysYearLimit() {
      return daysYearLimit;
    }

    public void setDaysYearLimit(int daysYearLimit) {
      this.daysYearLimit = daysYearLimit;
    }
  }
}
