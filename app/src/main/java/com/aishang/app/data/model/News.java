package com.aishang.app.data.model;

/**
 * Created by song on 2016/3/31.
 */
public class News {

  JNewsListResult.NewsListEntity news;

  int enshrinedCount;

  String userImageUrl;

  String zoneName;

  String userName;

  public News(JNewsListResult.NewsListEntity news, int enshrinedCount, String userImageUrl,
      String zoneName) {
    this.news = news;
    this.enshrinedCount = enshrinedCount;
    this.userImageUrl = userImageUrl;
    this.zoneName = zoneName;
  }

  public News(JNewsListResult.NewsListEntity news, int enshrinedCount, String userImageUrl,
      String zoneName, String userName) {
    this.news = news;
    this.enshrinedCount = enshrinedCount;
    this.userImageUrl = userImageUrl;
    this.zoneName = zoneName;
    this.userName = userName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public JNewsListResult.NewsListEntity getNews() {
    return news;
  }

  public void setNews(JNewsListResult.NewsListEntity news) {
    this.news = news;
  }

  public int getEnshrinedCount() {
    return enshrinedCount;
  }

  public void setEnshrinedCount(int enshrinedCount) {
    this.enshrinedCount = enshrinedCount;
  }

  public String getUserImageUrl() {
    return userImageUrl;
  }

  public void setUserImageUrl(String userImageUrl) {
    this.userImageUrl = userImageUrl;
  }

  public String getZoneName() {
    return zoneName;
  }

  public void setZoneName(String zoneName) {
    this.zoneName = zoneName;
  }
}
