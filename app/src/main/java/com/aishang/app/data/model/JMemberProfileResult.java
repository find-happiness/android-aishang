package com.aishang.app.data.model;

public class JMemberProfileResult {

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
    public String memberName;
    public String gender;
    public String imageUrl;
    public String mobilePhone;
    public String email;
    public String certifyType;
    public String certifyID;
    public String weixin;
    public String nickname;
    public String address;
    public String dateOfBirth;
    public JMemberIM[] imList;
    public JMemberBankAccount[] bankAccountList;
    public JMemberAddress[] addressList;

    public String getDateOfBirth() {
      return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
    }

    public String getWeixin() {
      return weixin;
    }

    public void setWeixin(String weixin) {
      this.weixin = weixin;
    }

    public String getNickname() {
      return nickname;
    }

    public void setNickname(String nickname) {
      this.nickname = nickname;
    }

    public String getAddress() {
      return address;
    }

    public void setAddress(String address) {
      this.address = address;
    }

    public String getMemberName() {
      return memberName;
    }

    public void setMemberName(String memberName) {
      this.memberName = memberName;
    }

    public String getGender() {
      return gender;
    }

    public void setGender(String gender) {
      this.gender = gender;
    }

    public String getImageUrl() {
      return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
    }

    public String getMobilePhone() {
      return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
      this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getCertifyType() {
      return certifyType;
    }

    public void setCertifyType(String certifyType) {
      this.certifyType = certifyType;
    }

    public String getCertifyID() {
      return certifyID;
    }

    public void setCertifyID(String certifyID) {
      this.certifyID = certifyID;
    }

    public JMemberIM[] getImList() {
      return imList;
    }

    public void setImList(JMemberIM[] imList) {
      this.imList = imList;
    }

    public JMemberBankAccount[] getBankAccountList() {
      return bankAccountList;
    }

    public void setBankAccountList(JMemberBankAccount[] bankAccountList) {
      this.bankAccountList = bankAccountList;
    }

    public JMemberAddress[] getAddressList() {
      return addressList;
    }

    public void setAddressList(JMemberAddress[] addressList) {
      this.addressList = addressList;
    }
  }

}
