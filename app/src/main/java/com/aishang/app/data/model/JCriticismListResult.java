package com.aishang.app.data.model;

import java.util.List;

/**
 * Created by song on 2016/4/16.
 */
public class JCriticismListResult {

  /**
   * result : success
   * totalCount : 3
   * CriticismList : [{"nickname":"","content":"465464","createDate":"2016-04-16T16:52:13","imageUrl":"/MemberUpload/memberProfileEdit/4502/201603211652040147.jpg"},{"nickname":"","content":"dff","createDate":"2016-04-16T16:44:40","imageUrl":"/MemberUpload/memberProfileEdit/4502/201603211652040147.jpg"},{"nickname":"","content":"顶顶顶","createDate":"2016-04-15T17:44:57","imageUrl":"/MemberUpload/memberProfileEdit/4502/201603211652040147.jpg"}]
   */

  private String result;
  private int totalCount;
  /**
   * nickname :
   * content : 465464
   * createDate : 2016-04-16T16:52:13
   * imageUrl : /MemberUpload/memberProfileEdit/4502/201603211652040147.jpg
   */

  private List<CriticismListEntity> CriticismList;

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

  public List<CriticismListEntity> getCriticismList() {
    return CriticismList;
  }

  public void setCriticismList(List<CriticismListEntity> CriticismList) {
    this.CriticismList = CriticismList;
  }

  public static class CriticismListEntity {
    private String nickname;
    private String content;
    private String createDate;
    private String imageUrl;

    public String getNickname() {
      return nickname;
    }

    public void setNickname(String nickname) {
      this.nickname = nickname;
    }

    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }

    public String getCreateDate() {
      return createDate;
    }

    public void setCreateDate(String createDate) {
      this.createDate = createDate;
    }

    public String getImageUrl() {
      return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
    }
  }
}
