package com.aishang.app.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.aishang.app.injection.ApplicationContext;

@Singleton public class PreferencesHelper {

  public static final String PREF_FILE_NAME = "android_aishang_pref_file";
  private static final String VERSION_JSON = "version";

  private final SharedPreferences mPref;

  @Inject public PreferencesHelper(@ApplicationContext Context context) {
    mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
  }

  public void clear() {
    mPref.edit().clear().apply();
  }

  public void setVersionCheck(String json) {
    mPref.edit().putString(VERSION_JSON, json).commit();
  }

  public String getVersionCheck() {
    return mPref.getString(VERSION_JSON,"");
  }
}
