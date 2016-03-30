package com.aishang.app.data.model;

/**
 * Created by song on 2016/3/30.
 */
public class JFavoriteEditParam {

  /**
   * memberAccount : 13883224451
   * cookie : DBF984A98014521448E818662D367694
   * data : {"id":0,"title":"没挨宰的三亚快乐自由行！","model":"enshrine","pk":"653","params":""}
   */

  private String memberAccount;
  private String cookie;
  /**
   * id : 0
   * title : 没挨宰的三亚快乐自由行！
   * model : enshrine
   * pk : 653
   * params :
   */

  private DataEntity data;

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

  public DataEntity getData() {
    return data;
  }

  public void setData(DataEntity data) {
    this.data = data;
  }

  public static class DataEntity {
    private int id;
    private String title;
    private String model;
    private String pk;
    private String params;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getModel() {
      return model;
    }

    public void setModel(String model) {
      this.model = model;
    }

    public String getPk() {
      return pk;
    }

    public void setPk(String pk) {
      this.pk = pk;
    }

    public String getParams() {
      return params;
    }

    public void setParams(String params) {
      this.params = params;
    }
  }
}
