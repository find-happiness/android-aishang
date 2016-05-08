package com.aishang.app.data.model;

import java.io.Serializable;

/**
 * Created by song on 2016/2/16.
 */
public class JMreActivityListResult implements Serializable {
  /// <summary>
  /// 返回结果 success or 具体错误信息
  /// </summary>
  public String result;
  private JActivityCatItem[] activityCatList;

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public JActivityCatItem[] getActivityCatList() {
    return activityCatList;
  }

  public void setActivityCatList(JActivityCatItem[] activityCatList) {
    this.activityCatList = activityCatList;
  }

  public class JActivityCatItem implements Serializable {

    private int catID;
    private String catName;
    private int totalCount;
    private JActivityItem[] activityList;

    public JActivityItem[] getActivityList() {
      return activityList;
    }

    public void setActivityList(JActivityItem[] activityList) {
      this.activityList = activityList;
    }

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
  }

  public class JActivityItem implements Serializable {
    private int activityID;
    private String title;
    private String shortDesc;
    private String description;
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
    private String avgScores;
    private String score1Title;
    private String score2Title;
    private String score3Title;

    public String getScore1Title() {
      return score1Title;
    }

    public void setScore1Title(String score1Title) {
      this.score1Title = score1Title;
    }

    public String getScore2Title() {
      return score2Title;
    }

    public void setScore2Title(String score2Title) {
      this.score2Title = score2Title;
    }

    public String getScore3Title() {
      return score3Title;
    }

    public void setScore3Title(String score3Title) {
      this.score3Title = score3Title;
    }

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

    public String getAvgScores() {
      return avgScores;
    }

    public void setAvgScores(String avgScores) {
      this.avgScores = avgScores;
    }
  }
}
