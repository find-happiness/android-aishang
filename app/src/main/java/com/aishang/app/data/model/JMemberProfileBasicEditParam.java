package com.aishang.app.data.model;

/**
 * Created by song on 2016/2/23.
 */
public class JMemberProfileBasicEditParam {
  private String memberAccount;
  private String cookie;
  private String imei;
  private float googleLat;
  private float googleLng;
  private Data data;

  public String getMemberAccount() {
    return memberAccount;
  }

  public void setMemberAccount(String memberAccount) {
    this.memberAccount = memberAccount;
  }

  public String getCookie() {
    return cookie;
  }

  public void setCookie(String cookie) {
    this.cookie = cookie;
  }

  public String getImei() {
    return imei;
  }

  public void setImei(String imei) {
    this.imei = imei;
  }

  public float getGoogleLat() {
    return googleLat;
  }

  public void setGoogleLat(float googleLat) {
    this.googleLat = googleLat;
  }

  public float getGoogleLng() {
    return googleLng;
  }

  public void setGoogleLng(float googleLng) {
    this.googleLng = googleLng;
  }

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }

  public class Data {
    public String gender;
    public String imageUrl;
    public String email;
    public String certifyType;
    public String certifyID;

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
  }

}
