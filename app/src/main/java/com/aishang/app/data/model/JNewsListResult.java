package com.aishang.app.data.model;

import java.util.List;

/**
 * /mobile/NewsList.ashx version:V2
 *
 * @author wang
 */
public class JNewsListResult {

  /**
   * result : success
   * totalCount : 6
   * newsList : [{"newsID":655,"newsGUID":"","title":"养生资讯测试","shortDesc":"O(∩_∩)O哈哈哈~","imageUrl":"","date":"2016-03-24
   * 09:44:05","hits":1,"supports":0,"staticUrl":"/html/mobile/2016/3/655.html","source":"测试数据"},{"newsID":653,"newsGUID":"","title":"没挨宰的三亚快乐自由行！","shortDesc":"","imageUrl":"/upload/news/2016/201603031446293001.jpg","date":"2016-03-03
   * 14:46:56","hits":2,"supports":0,"staticUrl":"","source":""},{"newsID":652,"newsGUID":"","title":"跟着简途北上九寨","shortDesc":"","imageUrl":"","date":"2016-03-03
   * 14:43:54","hits":3,"supports":0,"staticUrl":"","source":""}]
   * newsCatList : [{"catID":2,"catName":"旅居指南","totalCount":6,"newsList":[{"newsID":655,"newsGUID":"","title":"养生资讯测试","shortDesc":"O(∩_∩)O哈哈哈~","imageUrl":"","date":"2016-03-24
   * 09:44:05","hits":1,"supports":0,"staticUrl":"/html/mobile/2016/3/655.html","source":"测试数据"},{"newsID":653,"newsGUID":"","title":"没挨宰的三亚快乐自由行！","shortDesc":"","imageUrl":"/upload/news/2016/201603031446293001.jpg","date":"2016-03-03
   * 14:46:56","hits":2,"supports":0,"staticUrl":"","source":""},{"newsID":652,"newsGUID":"","title":"跟着简途北上九寨","shortDesc":"","imageUrl":"","date":"2016-03-03
   * 14:43:54","hits":3,"supports":0,"staticUrl":"","source":""}]}]
   * enshrinedCountList : [0,1,0]
   * zoneName : ["全世界","海南省","四川省"]
   * userImageUrlList : ["","/MemberUpload/memberProfileEdit/19095/201603071728129763.jpg","/MemberUpload/memberProfileEdit/19095/201603071728129763.jpg"]
   */

  private String result;
  private int totalCount;
  /**
   * newsID : 655
   * newsGUID :
   * title : 养生资讯测试
   * shortDesc : O(∩_∩)O哈哈哈~
   * imageUrl :
   * date : 2016-03-24 09:44:05
   * hits : 1
   * supports : 0
   * staticUrl : /html/mobile/2016/3/655.html
   * source : 测试数据
   */

  private List<NewsListEntity> newsList;
  /**
   * catID : 2
   * catName : 旅居指南
   * totalCount : 6
   * newsList : [{"newsID":655,"newsGUID":"","title":"养生资讯测试","shortDesc":"O(∩_∩)O哈哈哈~","imageUrl":"","date":"2016-03-24
   * 09:44:05","hits":1,"supports":0,"staticUrl":"/html/mobile/2016/3/655.html","source":"测试数据"},{"newsID":653,"newsGUID":"","title":"没挨宰的三亚快乐自由行！","shortDesc":"","imageUrl":"/upload/news/2016/201603031446293001.jpg","date":"2016-03-03
   * 14:46:56","hits":2,"supports":0,"staticUrl":"","source":""},{"newsID":652,"newsGUID":"","title":"跟着简途北上九寨","shortDesc":"","imageUrl":"","date":"2016-03-03
   * 14:43:54","hits":3,"supports":0,"staticUrl":"","source":""}]
   */

  private List<NewsCatListEntity> newsCatList;
  private List<Integer> enshrinedCountList;
  private List<String> zoneName;
  private List<String> userImageUrlList;

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

  public List<NewsListEntity> getNewsList() {
    return newsList;
  }

  public void setNewsList(List<NewsListEntity> newsList) {
    this.newsList = newsList;
  }

  public List<NewsCatListEntity> getNewsCatList() {
    return newsCatList;
  }

  public void setNewsCatList(List<NewsCatListEntity> newsCatList) {
    this.newsCatList = newsCatList;
  }

  public List<Integer> getEnshrinedCountList() {
    return enshrinedCountList;
  }

  public void setEnshrinedCountList(List<Integer> enshrinedCountList) {
    this.enshrinedCountList = enshrinedCountList;
  }

  public List<String> getZoneName() {
    return zoneName;
  }

  public void setZoneName(List<String> zoneName) {
    this.zoneName = zoneName;
  }

  public List<String> getUserImageUrlList() {
    return userImageUrlList;
  }

  public void setUserImageUrlList(List<String> userImageUrlList) {
    this.userImageUrlList = userImageUrlList;
  }

  public static class NewsListEntity {
    private int newsID;
    private String newsGUID;
    private String title;
    private String shortDesc;
    private String imageUrl;
    private String date;
    private int hits;
    private int supports;
    private String staticUrl;
    private String source;

    public int getNewsID() {
      return newsID;
    }

    public void setNewsID(int newsID) {
      this.newsID = newsID;
    }

    public String getNewsGUID() {
      return newsGUID;
    }

    public void setNewsGUID(String newsGUID) {
      this.newsGUID = newsGUID;
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

    public String getSource() {
      return source;
    }

    public void setSource(String source) {
      this.source = source;
    }
  }

  public static class NewsCatListEntity {
    private int catID;
    private String catName;
    private int totalCount;
    /**
     * newsID : 655
     * newsGUID :
     * title : 养生资讯测试
     * shortDesc : O(∩_∩)O哈哈哈~
     * imageUrl :
     * date : 2016-03-24 09:44:05
     * hits : 1
     * supports : 0
     * staticUrl : /html/mobile/2016/3/655.html
     * source : 测试数据
     */

    private List<NewsListEntity> newsList;

    public int getCatID() {
      return catID;
    }

    public void setCatID(int catID) {
      this.catID = catID;
    }

    public String getCatName() {
      return catName;
    }

    public void setCatName(String catName) {
      this.catName = catName;
    }

    public int getTotalCount() {
      return totalCount;
    }

    public void setTotalCount(int totalCount) {
      this.totalCount = totalCount;
    }

    public List<NewsListEntity> getNewsList() {
      return newsList;
    }

    public void setNewsList(List<NewsListEntity> newsList) {
      this.newsList = newsList;
    }
  }
}
