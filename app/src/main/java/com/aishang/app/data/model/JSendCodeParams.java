package com.aishang.app.data.model;

/**
 * Created by song on 2016/3/13.
 */
public class JSendCodeParams {

  /**
   * tel : 15723114043
   * hint : 您的注册验证码！
   */

  private String tel;
  private String hint;
  private boolean isLogin;

  public boolean isLogin() {
    return isLogin;
  }

  public void setIsLogin(boolean isLogin) {
    this.isLogin = isLogin;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public void setHint(String hint) {
    this.hint = hint;
  }

  public String getTel() {
    return tel;
  }

  public String getHint() {
    return hint;
  }
}
