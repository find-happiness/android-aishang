package com.aishang.app.data.model;

/**
 * Created by song on 2016/1/29.
 */
public class JMemberStatisticsResult {
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

  @Override public String toString() {
    return "ClassPojo [result = " + result + ", data = " + data + "]";
  }

  public class Data {
    private int creditTotal;

    private float awardLeft;

    private float awardTotalNot;

    private int activeMemberCount;

    private float awardFromChild;

    private int candidateMemberCount;

    private int totalBussinessInfo;

    private float awardTotal;

    private float awardFromChildNot;

    private float awardSelfNot;

    private int vaCardCount;

    private float awardSelf;

    private int creditLeft;

    private int vaApplyInProcess;

    private int creditUsed;

    private int vaApplyTotal;

    private int memberID;

    public int getCreditTotal() {
      return creditTotal;
    }

    public void setCreditTotal(int creditTotal) {
      this.creditTotal = creditTotal;
    }

    public float getAwardLeft() {
      return awardLeft;
    }

    public void setAwardLeft(float awardLeft) {
      this.awardLeft = awardLeft;
    }

    public float getAwardTotalNot() {
      return awardTotalNot;
    }

    public void setAwardTotalNot(float awardTotalNot) {
      this.awardTotalNot = awardTotalNot;
    }

    public int getActiveMemberCount() {
      return activeMemberCount;
    }

    public void setActiveMemberCount(int activeMemberCount) {
      this.activeMemberCount = activeMemberCount;
    }

    public float getAwardFromChild() {
      return awardFromChild;
    }

    public void setAwardFromChild(float awardFromChild) {
      this.awardFromChild = awardFromChild;
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

    public float getAwardTotal() {
      return awardTotal;
    }

    public void setAwardTotal(float awardTotal) {
      this.awardTotal = awardTotal;
    }

    public float getAwardFromChildNot() {
      return awardFromChildNot;
    }

    public void setAwardFromChildNot(float awardFromChildNot) {
      this.awardFromChildNot = awardFromChildNot;
    }

    public float getAwardSelfNot() {
      return awardSelfNot;
    }

    public void setAwardSelfNot(float awardSelfNot) {
      this.awardSelfNot = awardSelfNot;
    }

    public int getVaCardCount() {
      return vaCardCount;
    }

    public void setVaCardCount(int vaCardCount) {
      this.vaCardCount = vaCardCount;
    }

    public float getAwardSelf() {
      return awardSelf;
    }

    public void setAwardSelf(float awardSelf) {
      this.awardSelf = awardSelf;
    }

    public int getCreditLeft() {
      return creditLeft;
    }

    public void setCreditLeft(int creditLeft) {
      this.creditLeft = creditLeft;
    }

    public int getVaApplyInProcess() {
      return vaApplyInProcess;
    }

    public void setVaApplyInProcess(int vaApplyInProcess) {
      this.vaApplyInProcess = vaApplyInProcess;
    }

    public int getCreditUsed() {
      return creditUsed;
    }

    public void setCreditUsed(int creditUsed) {
      this.creditUsed = creditUsed;
    }

    public int getVaApplyTotal() {
      return vaApplyTotal;
    }

    public void setVaApplyTotal(int vaApplyTotal) {
      this.vaApplyTotal = vaApplyTotal;
    }

    public int getMemberID() {
      return memberID;
    }

    public void setMemberID(int memberID) {
      this.memberID = memberID;
    }

    @Override public String toString() {
      return "ClassPojo [creditTotal = "
          + creditTotal
          + ", awardLeft = "
          + awardLeft
          + ", awardTotalNot = "
          + awardTotalNot
          + ", activeMemberCount = "
          + activeMemberCount
          + ", awardFromChild = "
          + awardFromChild
          + ", candidateMemberCount = "
          + candidateMemberCount
          + ", totalBussinessInfo = "
          + totalBussinessInfo
          + ", awardTotal = "
          + awardTotal
          + ", awardFromChildNot = "
          + awardFromChildNot
          + ", awardSelfNot = "
          + awardSelfNot
          + ", vaCardCount = "
          + vaCardCount
          + ", awardSelf = "
          + awardSelf
          + ", creditLeft = "
          + creditLeft
          + ", vaApplyInProcess = "
          + vaApplyInProcess
          + ", creditUsed = "
          + creditUsed
          + ", vaApplyTotal = "
          + vaApplyTotal
          + ", memberID = "
          + memberID
          + "]";
    }
  }
}
