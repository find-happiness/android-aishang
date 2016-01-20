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
    public JMemberIM[] imList;
    public JMemberBankAccount[] bankAccountList;
    public JMemberAddress[] addressList;

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

  public class JMemberIM {

    public int id;
    public String imType;
    public String imCode;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getImType() {
      return imType;
    }

    public void setImType(String imType) {
      this.imType = imType;
    }

    public String getImCode() {
      return imCode;
    }

    public void setImCode(String imCode) {
      this.imCode = imCode;
    }
  }

  public class JMemberAddress {
    public int id;
    public String type;
    public String name;
    public String street;
    public String city;
    public String province;
    public String country;
    public String postcode;

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

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getStreet() {
      return street;
    }

    public void setStreet(String street) {
      this.street = street;
    }

    public String getCity() {
      return city;
    }

    public void setCity(String city) {
      this.city = city;
    }

    public String getProvince() {
      return province;
    }

    public void setProvince(String province) {
      this.province = province;
    }

    public String getCountry() {
      return country;
    }

    public void setCountry(String country) {
      this.country = country;
    }

    public String getPostcode() {
      return postcode;
    }

    public void setPostcode(String postcode) {
      this.postcode = postcode;
    }
  }

  public class JMemberBankAccount {
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
}
