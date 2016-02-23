package com.aishang.app.data.model;

import java.io.Serializable;

public class JMemberBankAccount implements Serializable {
  public int id;
  public String type;
  public String holder;
  public String bankName;
  public String accountNumber;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getHolder() {
    return holder;
  }

  public void setHolder(String holder) {
    this.holder = holder;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }
}
