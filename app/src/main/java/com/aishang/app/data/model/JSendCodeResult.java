package com.aishang.app.data.model;

/**
 * Created by song on 2016/3/13.
 */
public class JSendCodeResult {

  /**
   * result : success
   * status : true
   */

  private String result;
  private boolean status;

  public void setResult(String result) {
    this.result = result;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String getResult() {
    return result;
  }

  public boolean isStatus() {
    return status;
  }
}
