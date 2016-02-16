package com.aishang.app.data.model;

/**
 * Created by song on 2016/2/16.
 */
public class JRentalListResult {
  private String result;
  private int totalCount;
  private RentalItem[] RentalList;

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

  public RentalItem[] getRentalList() {
    return RentalList;
  }

  public void setRentalList(RentalItem[] rentalList) {
    RentalList = rentalList;
  }

  public class RentalItem {
    //rentalID:string, quoteID:int, name:string, imageUrl:string,
    //priceText:string,starLevel:string, promotionText:string,
    //tags:string, tagIDs:string, Lat:decimal, Lng:decimal, purchaseDatestirng:string,
    //resStartDate:string(YYYY-MM-DD),resEndDate:string(YYYY-MM-DD)

    private String rentalID;
    private int quoteID;
    private String name;
    private String imageUrl;
    private String priceText;
    private String starLevel;
    private String promotionText;
    private String tags;
    private String tagIDs;
    private float Lat;
    private float Lng;
    private String purchaseDatestirng;
    private String resStartDate;
    private String resEndDate;

    public String getRentalID() {
      return rentalID;
    }

    public void setRentalID(String rentalID) {
      this.rentalID = rentalID;
    }

    public int getQuoteID() {
      return quoteID;
    }

    public void setQuoteID(int quoteID) {
      this.quoteID = quoteID;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getImageUrl() {
      return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
    }

    public String getPriceText() {
      return priceText;
    }

    public void setPriceText(String priceText) {
      this.priceText = priceText;
    }

    public String getStarLevel() {
      return starLevel;
    }

    public void setStarLevel(String starLevel) {
      this.starLevel = starLevel;
    }

    public String getPromotionText() {
      return promotionText;
    }

    public void setPromotionText(String promotionText) {
      this.promotionText = promotionText;
    }

    public String getTags() {
      return tags;
    }

    public void setTags(String tags) {
      this.tags = tags;
    }

    public String getTagIDs() {
      return tagIDs;
    }

    public void setTagIDs(String tagIDs) {
      this.tagIDs = tagIDs;
    }

    public float getLat() {
      return Lat;
    }

    public void setLat(float lat) {
      Lat = lat;
    }

    public float getLng() {
      return Lng;
    }

    public void setLng(float lng) {
      Lng = lng;
    }

    public String getPurchaseDatestirng() {
      return purchaseDatestirng;
    }

    public void setPurchaseDatestirng(String purchaseDatestirng) {
      this.purchaseDatestirng = purchaseDatestirng;
    }

    public String getResStartDate() {
      return resStartDate;
    }

    public void setResStartDate(String resStartDate) {
      this.resStartDate = resStartDate;
    }

    public String getResEndDate() {
      return resEndDate;
    }

    public void setResEndDate(String resEndDate) {
      this.resEndDate = resEndDate;
    }
  }
}
