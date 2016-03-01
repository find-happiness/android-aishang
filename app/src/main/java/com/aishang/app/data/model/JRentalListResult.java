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

    /**
     * ID : cd10ec35-e1c0-4a43-877e-0f977ce7d9c7
     * rentalCat : 1
     * memberID : 2
     * quoteID : 1
     * purchaseDate : 2014-12-13 12:00:00
     * resStartDate : 2016-12-23 12:00:00
     * resEndDate : 2018-09-12 12:00:00
     * price : 342432
     * description :
     * special :
     * createTime : 2016-01-29 03:48:35
     * updateTime :
     * loupanID : 0
     * name : 河池巴马华昱假日酒店
     * imageUrl : /upload/hotel/1/201310081156264985.jpg
     * googleLat : 24.1382876
     * googleLng : 107.25236330000007
     * zoneID : 159
     * zoneIDLevel2 : 120
     * address : 广西巴马县新建路368号（近巴马长寿博物馆）
     * tags :
     * tagIDs :
     * promotionText :
     * openDateText : 2006年
     * moveInDate :
     * priceText : ￥288元起
     * developers :
     * touristAreaID : 26
     * projectIntro :
     * transportation :
     * enviroment :
     * dispOrder : 99999
     * status : 0
     * usageRatio :
     * grassLand :
     * totalApart : 1000
     * landArea :
     * buildingArea :
     * parkingSpace :
     * propertyCost :
     * propertyCompany :
     * salePermit : ’‘
     * priceCatID : 0
     * loupanGUID : 00000000-0000-0000-0000-000000000000
     */

    private String ID;
    private int rentalCat;
    private int memberID;
    private int quoteID;
    private String purchaseDate;
    private String resStartDate;
    private String resEndDate;
   // private int price;
    private String description;
    private String special;
    private String createTime;
    private String updateTime;
    private int loupanID;
    private String name;
    private String imageUrl;
   // private int zoneID;
   // private int zoneIDLevel2;
    private String address;
    private String tags;
    private String tagIDs;
    private String promotionText;
    private String openDateText;
    private String moveInDate;
    private String priceText;
    private String developers;
  //  private int touristAreaID;
    private String projectIntro;
    private String transportation;
    private String enviroment;
  //  private int dispOrder;
    private int status;
    private String usageRatio;
    private String grassLand;
   // private int totalApart;
    private String landArea;
    private String buildingArea;
    private String parkingSpace;
    private String propertyCost;
    private String propertyCompany;
    private String salePermit;
    private int priceCatID;
    private String loupanGUID;

    public void setID(String ID) {
      this.ID = ID;
    }

    public void setRentalCat(int rentalCat) {
      this.rentalCat = rentalCat;
    }

    public void setMemberID(int memberID) {
      this.memberID = memberID;
    }

    public void setQuoteID(int quoteID) {
      this.quoteID = quoteID;
    }

    public void setPurchaseDate(String purchaseDate) {
      this.purchaseDate = purchaseDate;
    }

    public void setResStartDate(String resStartDate) {
      this.resStartDate = resStartDate;
    }

    public void setResEndDate(String resEndDate) {
      this.resEndDate = resEndDate;
    }

    //public void setPrice(int price) {
    //  this.price = price;
    //}

    public void setDescription(String description) {
      this.description = description;
    }

    public void setSpecial(String special) {
      this.special = special;
    }

    public void setCreateTime(String createTime) {
      this.createTime = createTime;
    }

    public void setUpdateTime(String updateTime) {
      this.updateTime = updateTime;
    }

    public void setLoupanID(int loupanID) {
      this.loupanID = loupanID;
    }

    public void setName(String name) {
      this.name = name;
    }

    public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
    }

    //public void setZoneID(int zoneID) {
    //  this.zoneID = zoneID;
    //}
    //
    //public void setZoneIDLevel2(int zoneIDLevel2) {
    //  this.zoneIDLevel2 = zoneIDLevel2;
    //}

    public void setAddress(String address) {
      this.address = address;
    }

    public void setTags(String tags) {
      this.tags = tags;
    }

    public void setTagIDs(String tagIDs) {
      this.tagIDs = tagIDs;
    }

    public void setPromotionText(String promotionText) {
      this.promotionText = promotionText;
    }

    public void setOpenDateText(String openDateText) {
      this.openDateText = openDateText;
    }

    public void setMoveInDate(String moveInDate) {
      this.moveInDate = moveInDate;
    }

    public void setPriceText(String priceText) {
      this.priceText = priceText;
    }

    public void setDevelopers(String developers) {
      this.developers = developers;
    }

    //public void setTouristAreaID(int touristAreaID) {
    //  this.touristAreaID = touristAreaID;
    //}

    public void setProjectIntro(String projectIntro) {
      this.projectIntro = projectIntro;
    }

    public void setTransportation(String transportation) {
      this.transportation = transportation;
    }

    public void setEnviroment(String enviroment) {
      this.enviroment = enviroment;
    }

    //public void setDispOrder(int dispOrder) {
    //  this.dispOrder = dispOrder;
    //}

    public void setStatus(int status) {
      this.status = status;
    }

    public void setUsageRatio(String usageRatio) {
      this.usageRatio = usageRatio;
    }

    public void setGrassLand(String grassLand) {
      this.grassLand = grassLand;
    }

    //public void setTotalApart(int totalApart) {
    //  this.totalApart = totalApart;
    //}

    public void setLandArea(String landArea) {
      this.landArea = landArea;
    }

    public void setBuildingArea(String buildingArea) {
      this.buildingArea = buildingArea;
    }

    public void setParkingSpace(String parkingSpace) {
      this.parkingSpace = parkingSpace;
    }

    public void setPropertyCost(String propertyCost) {
      this.propertyCost = propertyCost;
    }

    public void setPropertyCompany(String propertyCompany) {
      this.propertyCompany = propertyCompany;
    }

    public void setSalePermit(String salePermit) {
      this.salePermit = salePermit;
    }

    public void setPriceCatID(int priceCatID) {
      this.priceCatID = priceCatID;
    }

    public void setLoupanGUID(String loupanGUID) {
      this.loupanGUID = loupanGUID;
    }

    public String getID() {
      return ID;
    }

    public int getRentalCat() {
      return rentalCat;
    }

    public int getMemberID() {
      return memberID;
    }

    public int getQuoteID() {
      return quoteID;
    }

    public String getPurchaseDate() {
      return purchaseDate;
    }

    public String getResStartDate() {
      return resStartDate;
    }

    public String getResEndDate() {
      return resEndDate;
    }

    //public int getPrice() {
    //  return price;
    //}

    public String getDescription() {
      return description;
    }

    public String getSpecial() {
      return special;
    }

    public String getCreateTime() {
      return createTime;
    }

    public String getUpdateTime() {
      return updateTime;
    }

    public int getLoupanID() {
      return loupanID;
    }

    public String getName() {
      return name;
    }

    public String getImageUrl() {
      return imageUrl;
    }

    //public int getZoneID() {
    //  return zoneID;
    //}
    //
    //public int getZoneIDLevel2() {
    //  return zoneIDLevel2;
    //}

    public String getAddress() {
      return address;
    }

    public String getTags() {
      return tags;
    }

    public String getTagIDs() {
      return tagIDs;
    }

    public String getPromotionText() {
      return promotionText;
    }

    public String getOpenDateText() {
      return openDateText;
    }

    public String getMoveInDate() {
      return moveInDate;
    }

    public String getPriceText() {
      return priceText;
    }

    public String getDevelopers() {
      return developers;
    }

    //public int getTouristAreaID() {
    //  return touristAreaID;
    //}

    public String getProjectIntro() {
      return projectIntro;
    }

    public String getTransportation() {
      return transportation;
    }

    public String getEnviroment() {
      return enviroment;
    }

    //public int getDispOrder() {
    //  return dispOrder;
    //}

    public int getStatus() {
      return status;
    }

    public String getUsageRatio() {
      return usageRatio;
    }

    public String getGrassLand() {
      return grassLand;
    }

    //public int getTotalApart() {
    //  return totalApart;
    //}

    public String getLandArea() {
      return landArea;
    }

    public String getBuildingArea() {
      return buildingArea;
    }

    public String getParkingSpace() {
      return parkingSpace;
    }

    public String getPropertyCost() {
      return propertyCost;
    }

    public String getPropertyCompany() {
      return propertyCompany;
    }

    public String getSalePermit() {
      return salePermit;
    }

    public int getPriceCatID() {
      return priceCatID;
    }

    public String getLoupanGUID() {
      return loupanGUID;
    }
  }
}
