package com.aishang.app.data.model;

/**
 * Created by song on 2016/9/14.
 */
public class JMyVacationApplyResult {
  private String result;
  private String reservID;
  private boolean suc;

  public String getReservID() {
    return reservID;
  }

  public void setReservID(String reservID) {
    this.reservID = reservID;
  }

  public boolean isSuc() {
    return suc;
  }

  public void setSuc(boolean suc) {
    this.suc = suc;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}
