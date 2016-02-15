package com.aishang.app.data.model;

import java.io.Serializable;

/**
 * /mobile/member/memberLogin.ashx version 2
 *
 * @author wang
 */
public class JMemberLoginResult implements Serializable {

  private String result;
  private Data data;

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }

  public class Data {
    private String cookies;
    private String imageUrl;
    private String memberName;
    private String awardTotal;
    private String awardLeft;
    private String awardTotalNot;
    private float awardSelf;
    private float awardFromChild;
    private float awardFromChildNot;
    private int creditTotal;
    private int creditUsed;
    private int creditLeft;
    private String lastLogin;
    private int activeMemberCount;
    private int candidateMemberCount;
    private int totalBussinessInfo;
    private int dealBussiness;
    private Role[] roleList;
    private int bChartAgree;
    private int memberID;

    public int getDealBussiness() {
      return dealBussiness;
    }

    public void setDealBussiness(int dealBussiness) {
      this.dealBussiness = dealBussiness;
    }

    public int getMemberID() {
      return memberID;
    }

    public void setMemberID(int memberID) {
      this.memberID = memberID;
    }

    public String getCookies() {
      return cookies;
    }

    public int getbChartAgree() {
      return bChartAgree;
    }

    public void setbChartAgree(int bChartAgree) {
      this.bChartAgree = bChartAgree;
    }

    public void setCookies(String cookies) {
      this.cookies = cookies;
    }

    public String getImageUrl() {
      return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
    }

    public String getMemberName() {
      return memberName;
    }

    public void setMemberName(String memberName) {
      this.memberName = memberName;
    }

    public String getAwardTotal() {
      return awardTotal;
    }

    public void setAwardTotal(String awardTotal) {
      this.awardTotal = awardTotal;
    }

    public String getAwardLeft() {
      return awardLeft;
    }

    public void setAwardLeft(String awardLeft) {
      this.awardLeft = awardLeft;
    }

    public String getAwardTotalNot() {
      return awardTotalNot;
    }

    public void setAwardTotalNot(String awardTotalNot) {
      this.awardTotalNot = awardTotalNot;
    }

    public float getAwardSelf() {
      return awardSelf;
    }

    public void setAwardSelf(float awardSelf) {
      this.awardSelf = awardSelf;
    }

    public float getAwardFromChild() {
      return awardFromChild;
    }

    public void setAwardFromChild(float awardFromChild) {
      this.awardFromChild = awardFromChild;
    }

    public float getAwardFromChildNot() {
      return awardFromChildNot;
    }

    public void setAwardFromChildNot(float awardFromChildNot) {
      this.awardFromChildNot = awardFromChildNot;
    }

    public int getActiveMemberCount() {
      return activeMemberCount;
    }

    public void setActiveMemberCount(int activeMemberCount) {
      this.activeMemberCount = activeMemberCount;
    }

    public int getCandidateMemberCount() {
      return candidateMemberCount;
    }

    public void setCandidateMemberCount(int candidateMemberCount) {
      this.candidateMemberCount = candidateMemberCount;
    }

    public int getTotalBussinessInfo() {
      return totalBussinessInfo;
    }

    public void setTotalBussinessInfo(int totalBussinessInfo) {
      this.totalBussinessInfo = totalBussinessInfo;
    }

    public int getCreditTotal() {
      return creditTotal;
    }

    public void setCreditTotal(int creditTotal) {
      this.creditTotal = creditTotal;
    }

    public int getCreditUsed() {
      return creditUsed;
    }

    public void setCreditUsed(int creditUsed) {
      this.creditUsed = creditUsed;
    }

    public int getCreditLeft() {
      return creditLeft;
    }

    public void setCreditLeft(int creditLeft) {
      this.creditLeft = creditLeft;
    }

    public String getLastLogin() {
      return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
      this.lastLogin = lastLogin;
    }

    public Role[] getRoleList() {
      return roleList;
    }

    public void setRoleList(Role[] roleList) {
      this.roleList = roleList;
    }

    public class Role {
      private int roldID;
      private String roleName;

      public int getRoldID() {
        return roldID;
      }

      public void setRoldID(int roldID) {
        this.roldID = roldID;
      }

      public String getRoleName() {
        return roleName;
      }

      public void setRoleName(String roleName) {
        this.roleName = roleName;
      }
    }
  }
}
