package com.aishang.app.data.model;

/**
 * Created by song on 2016/2/19.
 */
public class JMreActivityDetailResult {
  //"result": "success",
  //    "detail": {
  //  "activityID": 2,
  //      "title": "暖冬计划",
  //      "shortDesc": "<p>\r\n\t&nbsp;自2011年始，美尔每年初冬开启“美尔暖冬计划”，举办免费暖冬淘房活动，精品淘房路线受追捧，年年引爆新春南方置业潮……\r\n</p>",
  //      "description": "<img style=\"width:446px;height:445px;\" alt=\"\" src=\"/admin/attached/image/20141216/20141216115542_2970.jpg\" width=\"495\" height=\"557\" /> \r\n<p>\r\n\t这几天，山城重庆又一波寒流来袭，这个冬天，你准备好怎么保暖了吗？\r\n</p>\r\n<p>\r\n\t加长版羽绒服？开电热毯？吹暖气？喝姜汤？吃羊肉汤锅？增加运动量？泡热水澡？\r\n</p>\r\n<p>\r\n\tNO！NO！NO！\r\n</p>\r\n<p>\r\n\t真正冬暖在南方！\r\n</p>\r\n<p>\r\n\t美尔旅居倾力打造“美尔免费暖冬之旅”，12月15日起即可出团，1000+体验名额等你来抢！\r\n</p>\r\n<img style=\"width:478px;height:211px;\" alt=\"\" src=\"/admin/attached/image/20141216/20141216115600_6060.jpg\" width=\"1127\" height=\"398\" /> \r\n<p>\r\n\t此次暖冬之旅设海南、云南腾冲、云南西双版纳三个目的地，三条精品路线收录当地精品旅游地产项目，美尔旅居专业顾问全程跟团，为您解答南方淘房疑问，带您实地探访比较，途中还有更多惊喜体验发生。\r\n</p>\r\n<img alt=\"\" src=\"/admin/attached/image/20141216/20141216115617_6300.jpg\" />",
  //      "catName": "日常活动",
  //      "startTime": "2014-12-15",
  //      "endTime": "2015-01-30",
  //      "position": "海南三亚、云南腾冲、云南西双版纳",
  //      "maxMember": 1000,
  //      "curMembers": 3,
  //      "enrollStartTime": "2014-12-15",
  //      "enrollEndTime": "2015-01-30",
  //      "fee": 0,
  //      "imageUrl": "/upload/activity/2/201412161206021484.jpg",
  //      "relateUrl": "",
  //      "contact": "",
  //      "contactPhone": "023-68505555",
  //      "enrollStatus": 1,
  //      "beScored": 1
  //},
  //    "totalReview": 0,
  //    "reviewList": []

  private String result;
  private JDetail detail;
  private int totalReview;

  private JReview reviewList;

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public JDetail getDetail() {
    return detail;
  }

  public void setDetail(JDetail detail) {
    this.detail = detail;
  }

  public int getTotalReview() {
    return totalReview;
  }

  public void setTotalReview(int totalReview) {
    this.totalReview = totalReview;
  }

  public JReview getReviewList() {
    return reviewList;
  }

  public void setReviewList(JReview reviewList) {
    this.reviewList = reviewList;
  }

  public class JReview {

    //userName:string (用户名，匿名时候，除开第一个，其它用*替换),
    //memberImageUrl:string (会员头像),
    //reviewTime:string (评论时间)，
    //reviewContent:string (评价内容),
    //score1:decimal, score2:decimal, score3:decimal

    private String userName;
    private String memberImageUrl;
    private String reviewTime;
    private String reviewContent;

    private float score1;
    private float score2;
    private float score3;

    public String getUserName() {
      return userName;
    }

    public void setUserName(String userName) {
      this.userName = userName;
    }

    public String getMemberImageUrl() {
      return memberImageUrl;
    }

    public void setMemberImageUrl(String memberImageUrl) {
      this.memberImageUrl = memberImageUrl;
    }

    public String getReviewTime() {
      return reviewTime;
    }

    public void setReviewTime(String reviewTime) {
      this.reviewTime = reviewTime;
    }

    public String getReviewContent() {
      return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
      this.reviewContent = reviewContent;
    }

    public float getScore1() {
      return score1;
    }

    public void setScore1(float score1) {
      this.score1 = score1;
    }

    public float getScore2() {
      return score2;
    }

    public void setScore2(float score2) {
      this.score2 = score2;
    }

    public float getScore3() {
      return score3;
    }

    public void setScore3(float score3) {
      this.score3 = score3;
    }
  }

  public class JDetail {
    private int activityID;
    private String title;
    private String shortDesc;
    private String description;
    private String catName;
    private String startTime;
    private String endTime;
    private String position;
    private int maxMember;
    private int curMembers;
    private String enrollStartTime;
    private String enrollEndTime;
    private int fee;
    private String imageUrl;
    private String relateUrl;
    private String contact;
    private String contactPhone;
    private int enrollStatus;
    private int beScored;

    public int getActivityID() {
      return activityID;
    }

    public void setActivityID(int activityID) {
      this.activityID = activityID;
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

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getCatName() {
      return catName;
    }

    public void setCatName(String catName) {
      this.catName = catName;
    }

    public String getStartTime() {
      return startTime;
    }

    public void setStartTime(String startTime) {
      this.startTime = startTime;
    }

    public String getEndTime() {
      return endTime;
    }

    public void setEndTime(String endTime) {
      this.endTime = endTime;
    }

    public String getPosition() {
      return position;
    }

    public void setPosition(String position) {
      this.position = position;
    }

    public int getMaxMember() {
      return maxMember;
    }

    public void setMaxMember(int maxMember) {
      this.maxMember = maxMember;
    }

    public int getCurMembers() {
      return curMembers;
    }

    public void setCurMembers(int curMembers) {
      this.curMembers = curMembers;
    }

    public String getEnrollStartTime() {
      return enrollStartTime;
    }

    public void setEnrollStartTime(String enrollStartTime) {
      this.enrollStartTime = enrollStartTime;
    }

    public String getEnrollEndTime() {
      return enrollEndTime;
    }

    public void setEnrollEndTime(String enrollEndTime) {
      this.enrollEndTime = enrollEndTime;
    }

    public int getFee() {
      return fee;
    }

    public void setFee(int fee) {
      this.fee = fee;
    }

    public String getImageUrl() {
      return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
    }

    public String getRelateUrl() {
      return relateUrl;
    }

    public void setRelateUrl(String relateUrl) {
      this.relateUrl = relateUrl;
    }

    public String getContact() {
      return contact;
    }

    public void setContact(String contact) {
      this.contact = contact;
    }

    public String getContactPhone() {
      return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
      this.contactPhone = contactPhone;
    }

    public int getEnrollStatus() {
      return enrollStatus;
    }

    public void setEnrollStatus(int enrollStatus) {
      this.enrollStatus = enrollStatus;
    }

    public int getBeScored() {
      return beScored;
    }

    public void setBeScored(int beScored) {
      this.beScored = beScored;
    }
  }
}
