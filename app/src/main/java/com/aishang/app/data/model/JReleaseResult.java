package com.aishang.app.data.model;

import java.util.List;

/**
 * Created by song on 2016/3/13.
 */
public class JReleaseResult {

  /**
   * result : success
   * totalCount : 1
   * listNews : [{"ID":642,"catID":"","GUID":"","memberGUID":"3b4201fd-1e1c-4525-9dda-d3074e35cc05","title":"重庆白领月薪\u201c6\u201d字头
   * 看看你适合在哪个区域买房？","source":"","createDate":"2016-02-24T09:33:05","updateDate":"2016-03-13T15:46:07.9848232+08:00","status":1,"visibleZoneID":"","hits":0,"supports":0,"AuthorID":"","content":"asfadfafasfa","shortDesc":"","imageUrl":"undefined","staticUrl":"","dispOrder":999999,"pinTop":0,"validateStartDate":"2016-02-24T09:33:05","validateEndDate":"","tags":"","tagIDs":""}]
   */

  private String result;
  private int totalCount;
  /**
   * ID : 642
   * catID :
   * GUID :
   * memberGUID : 3b4201fd-1e1c-4525-9dda-d3074e35cc05
   * title : 重庆白领月薪“6”字头  看看你适合在哪个区域买房？
   * source :
   * createDate : 2016-02-24T09:33:05
   * updateDate : 2016-03-13T15:46:07.9848232+08:00
   * status : 1
   * visibleZoneID :
   * hits : 0
   * supports : 0
   * AuthorID :
   * content : asfadfafasfa
   * shortDesc :
   * imageUrl : undefined
   * staticUrl :
   * dispOrder : 999999
   * pinTop : 0
   * validateStartDate : 2016-02-24T09:33:05
   * validateEndDate :
   * tags :
   * tagIDs :
   */

  private List<ListNewsEntity> listNews;

  public void setResult(String result) {
    this.result = result;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public void setListNews(List<ListNewsEntity> listNews) {
    this.listNews = listNews;
  }

  public String getResult() {
    return result;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public List<ListNewsEntity> getListNews() {
    return listNews;
  }

  public static class ListNewsEntity {
    private int ID;
    private String catID;
    private String GUID;
    private String memberGUID;
    private String title;
    private String source;
    private String createDate;
    private String updateDate;
    private int status;
    private String visibleZoneID;
    private int hits;
    private int supports;
    private String AuthorID;
    private String content;
    private String shortDesc;
    private String imageUrl;
    private String staticUrl;
    private int dispOrder;
    private int pinTop;
    private String validateStartDate;
    private String validateEndDate;
    private String tags;
    private String tagIDs;

    public void setID(int ID) {
      this.ID = ID;
    }

    public void setCatID(String catID) {
      this.catID = catID;
    }

    public void setGUID(String GUID) {
      this.GUID = GUID;
    }

    public void setMemberGUID(String memberGUID) {
      this.memberGUID = memberGUID;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public void setSource(String source) {
      this.source = source;
    }

    public void setCreateDate(String createDate) {
      this.createDate = createDate;
    }

    public void setUpdateDate(String updateDate) {
      this.updateDate = updateDate;
    }

    public void setStatus(int status) {
      this.status = status;
    }

    public void setVisibleZoneID(String visibleZoneID) {
      this.visibleZoneID = visibleZoneID;
    }

    public void setHits(int hits) {
      this.hits = hits;
    }

    public void setSupports(int supports) {
      this.supports = supports;
    }

    public void setAuthorID(String AuthorID) {
      this.AuthorID = AuthorID;
    }

    public void setContent(String content) {
      this.content = content;
    }

    public void setShortDesc(String shortDesc) {
      this.shortDesc = shortDesc;
    }

    public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
    }

    public void setStaticUrl(String staticUrl) {
      this.staticUrl = staticUrl;
    }

    public void setDispOrder(int dispOrder) {
      this.dispOrder = dispOrder;
    }

    public void setPinTop(int pinTop) {
      this.pinTop = pinTop;
    }

    public void setValidateStartDate(String validateStartDate) {
      this.validateStartDate = validateStartDate;
    }

    public void setValidateEndDate(String validateEndDate) {
      this.validateEndDate = validateEndDate;
    }

    public void setTags(String tags) {
      this.tags = tags;
    }

    public void setTagIDs(String tagIDs) {
      this.tagIDs = tagIDs;
    }

    public int getID() {
      return ID;
    }

    public String getCatID() {
      return catID;
    }

    public String getGUID() {
      return GUID;
    }

    public String getMemberGUID() {
      return memberGUID;
    }

    public String getTitle() {
      return title;
    }

    public String getSource() {
      return source;
    }

    public String getCreateDate() {
      return createDate;
    }

    public String getUpdateDate() {
      return updateDate;
    }

    public int getStatus() {
      return status;
    }

    public String getVisibleZoneID() {
      return visibleZoneID;
    }

    public int getHits() {
      return hits;
    }

    public int getSupports() {
      return supports;
    }

    public String getAuthorID() {
      return AuthorID;
    }

    public String getContent() {
      return content;
    }

    public String getShortDesc() {
      return shortDesc;
    }

    public String getImageUrl() {
      return imageUrl;
    }

    public String getStaticUrl() {
      return staticUrl;
    }

    public int getDispOrder() {
      return dispOrder;
    }

    public int getPinTop() {
      return pinTop;
    }

    public String getValidateStartDate() {
      return validateStartDate;
    }

    public String getValidateEndDate() {
      return validateEndDate;
    }

    public String getTags() {
      return tags;
    }

    public String getTagIDs() {
      return tagIDs;
    }
  }
}
