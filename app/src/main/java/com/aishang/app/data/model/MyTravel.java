package com.aishang.app.data.model;

/**
 * Created by song on 2016/3/13.
 */
public class MyTravel {

  private String title;
  private String shortDesc;
  private String imageUrl;
  private String date;
  private int hits;
  private int supports;
  private String staticUrl;
  private int newsID;

  public MyTravel(String title, String shortDesc, String imageUrl, String date, int hits,
      int supports, String staticUrl, int newsID) {
    this.title = title;
    this.shortDesc = shortDesc;
    this.imageUrl = imageUrl;
    this.date = date;
    this.hits = hits;
    this.supports = supports;
    this.staticUrl = staticUrl;
    this.newsID = newsID;
  }

  public int getNewsID() {
    return newsID;
  }

  public void setNewsID(int newsID) {
    this.newsID = newsID;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getShortDesc() {
    return shortDesc;
  }

  public void setShortDesc(String shortDesc) {
    this.shortDesc = shortDesc;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getHits() {
    return hits;
  }

  public void setHits(int hits) {
    this.hits = hits;
  }

  public int getSupports() {
    return supports;
  }

  public void setSupports(int supports) {
    this.supports = supports;
  }

  public String getStaticUrl() {
    return staticUrl;
  }

  public void setStaticUrl(String staticUrl) {
    this.staticUrl = staticUrl;
  }
}
