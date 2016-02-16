package com.aishang.app.data.model;

/**
 * Created by song on 2016/2/16.
 */
public class JMyBusinessBuyInListResult {
  private String result;
  private int totalItem;
  private BuyIn[] buyinList;

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public int getTotalItem() {
    return totalItem;
  }

  public void setTotalItem(int totalItem) {
    this.totalItem = totalItem;
  }

  public BuyIn[] getBuyinList() {
    return buyinList;
  }

  public void setBuyinList(BuyIn[] buyinList) {
    this.buyinList = buyinList;
  }

  public class BuyIn {

    //SNO:string, name:string, title:string, phone:string, apartCatTxt:string, zoneText:string, areaText:string, priceText:string,  createDate:string, enabled:int

    private String SNO;
    private String name;
    private String title;
    private String phone;
    private String apartCatTxt;
    private String zoneText;
    private String areaText;
    private String priceText;
    private String createDate;
    private int enabled;

    public String getSNO() {
      return SNO;
    }

    public void setSNO(String SNO) {
      this.SNO = SNO;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getPhone() {
      return phone;
    }

    public void setPhone(String phone) {
      this.phone = phone;
    }

    public String getApartCatTxt() {
      return apartCatTxt;
    }

    public void setApartCatTxt(String apartCatTxt) {
      this.apartCatTxt = apartCatTxt;
    }

    public String getZoneText() {
      return zoneText;
    }

    public void setZoneText(String zoneText) {
      this.zoneText = zoneText;
    }

    public String getAreaText() {
      return areaText;
    }

    public void setAreaText(String areaText) {
      this.areaText = areaText;
    }

    public String getPriceText() {
      return priceText;
    }

    public void setPriceText(String priceText) {
      this.priceText = priceText;
    }

    public String getCreateDate() {
      return createDate;
    }

    public void setCreateDate(String createDate) {
      this.createDate = createDate;
    }

    public int getEnabled() {
      return enabled;
    }

    public void setEnabled(int enabled) {
      this.enabled = enabled;
    }
  }
}
