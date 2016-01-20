package com.aishang.app.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.os.Environment.MEDIA_MOUNTED;

/**
 * Created by song on 2016/1/20.
 */
public class CommonUtil {
  private static final String TAG = "CommonUtil";
  private static final String EXTERNAL_STORAGE_PERMISSION =
      "android.permission.WRITE_EXTERNAL_STORAGE";

  /**
   * @param content the content that will be encrypt
   * @return the encrypted content
   */
  public static String getEncodeMD5(String content) {
    try {
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      md5.update(content.getBytes("UTF-8"));
      byte[] encryption = md5.digest();

      StringBuffer strBuf = new StringBuffer();
      for (int i = 0; i < encryption.length; i++) {
        if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
          strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
        } else {
          strBuf.append(Integer.toHexString(0xff & encryption[i]));
        }
      }

      return strBuf.toString();
    } catch (NoSuchAlgorithmException e) {
      return "";
    } catch (UnsupportedEncodingException e) {
      return "";
    }
  }

  public static int[] getHeightWithScreenWidth(Activity ctx, float widthRatio, float heightRatio) {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ctx.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    int mScreenHeight = localDisplayMetrics.heightPixels;
    int mScreenWidth = localDisplayMetrics.widthPixels;
    int[] data = new int[2];
    data[0] = mScreenWidth;
    data[1] = (int) (heightRatio * (mScreenWidth / widthRatio));
    return data;
  }

  /**
   * 获取软件版本号
   */
  public static int getVersionCode(Context context) {
    int versionCode = -1;
    // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
    try {
      versionCode =
          context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return versionCode;
  }

  /**
   * Returns application cache directory. Cache directory will be created on SD card
   * <i>("/Android/data/[app_package_name]/cache")</i> if card is mounted and app has appropriate
   * permission. Else -
   * Android defines cache directory on device's file system.
   *
   * @param context Application context
   * @return Cache {@link File directory}
   */
  public static File getCacheDirectory(Context context) {
    File appCacheDir = null;
    if (MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(
        context)) {
      appCacheDir = getExternalCacheDir(context);
    }
    if (appCacheDir == null) {
      appCacheDir = context.getCacheDir();
    }
    if (appCacheDir == null) {
      Log.w(TAG, "Can't define system cache directory! The app should be re-installed.");
    }
    return appCacheDir;
  }

  private static File getExternalCacheDir(Context context) {
    File dataDir = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data");
    File appCacheDir = new File(new File(dataDir, context.getPackageName()), "cache");
    if (!appCacheDir.exists()) {
      if (!appCacheDir.mkdirs()) {
        Log.w(TAG, "Unable to create external cache directory");
        return null;
      }
      try {
        new File(appCacheDir, ".nomedia").createNewFile();
      } catch (IOException e) {
        Log.i(TAG, "Can't create \".nomedia\" file in application external cache directory");
      }
    }
    return appCacheDir;
  }

  private static boolean hasExternalStoragePermission(Context context) {
    int perm = context.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION);
    return perm == PackageManager.PERMISSION_GRANTED;
  }
}
