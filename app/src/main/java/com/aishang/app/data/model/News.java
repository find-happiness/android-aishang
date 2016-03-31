package com.aishang.app.data.model;

/**
 * Created by song on 2016/3/31.
 */
public class News {

  JNewsListResult.NewsListEntity news;

  int enshrinedCount;

  String userImageUrl;

  String zoneName;

  public News(JNewsListResult.NewsListEntity news, int enshrinedCount, String userImageUrl,
      String zoneName) {
    this.news = news;
    this.enshrinedCount = enshrinedCount;
    this.userImageUrl = userImageUrl;
    this.zoneName = zoneName;
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
