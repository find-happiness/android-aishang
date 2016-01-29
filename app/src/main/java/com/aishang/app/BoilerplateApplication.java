package com.aishang.app;

import android.app.Application;
import android.content.Context;

import com.aishang.app.data.model.JMemberLoginResult;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import com.aishang.app.BuildConfig;
import com.aishang.app.injection.component.ApplicationComponent;
import com.aishang.app.injection.component.DaggerApplicationComponent;
import com.aishang.app.injection.module.ApplicationModule;

public class BoilerplateApplication extends Application {

  ApplicationComponent mApplicationComponent;
  JMemberLoginResult memberLoginResult;
  String memberAccount;
  String memberPsw;

  @Override public void onCreate() {
    super.onCreate();

    if (BuildConfig.DEBUG) {
      //Timber.plant(new Timber.DebugTree());
      Fabric.with(this, new Crashlytics());
    }
  }

  public static BoilerplateApplication get(Context context) {
    return (BoilerplateApplication) context.getApplicationContext();
  }

  public ApplicationComponent getComponent() {
    if (mApplicationComponent == null) {
      mApplicationComponent = DaggerApplicationComponent.builder()
          .applicationModule(new ApplicationModule(this))
          .build();
    }
    return mApplicationComponent;
  }

  // Needed to replace the component with a test specific one
  public void setComponent(ApplicationComponent applicationComponent) {
    mApplicationComponent = applicationComponent;
  }

  public JMemberLoginResult getMemberLoginResult() {
    return memberLoginResult;
  }

  public void setMemberLoginResult(JMemberLoginResult memberLoginResult) {
    this.memberLoginResult = memberLoginResult;
  }

  public String getMemberAccount() {
    return memberAccount;
  }

  public void setMemberAccount(String memberAccount) {
    this.memberAccount = memberAccount;
  }

  public String getMemberPsw() {
    return memberPsw;
  }

  public void setMemberPsw(String memberPsw) {
    this.memberPsw = memberPsw;
  }
}
