package com.aishang.app.data.model;

/**
 * Created by song on 2016/2/23.
 */
public class JMemberBankListResult {
  private String result;

  JMemberBankAccount[] bankAccountList;

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public JMemberBankAccount[] getBankAccountList() {
    return bankAccountList;
  }

  public void setBankAccountList(JMemberBankAccount[] bankAccountList) {
    this.bankAccountList = bankAccountList;
  }
}
