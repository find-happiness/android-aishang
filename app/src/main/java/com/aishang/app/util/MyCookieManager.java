package com.aishang.app.util;

import android.util.Log;
import java.io.IOException;
import java.net.CookieManager;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Created by song on 2016/4/28.
 */
public class MyCookieManager extends CookieManager {
  private static final String TAG = "MyCookieManager";

  @Override
  public void put(URI uri, Map<String, List<String>> stringListMap) throws IOException {
    super.put(uri, stringListMap);
    if (stringListMap != null && stringListMap.get("Set-Cookie") != null)
      for (String string : stringListMap.get("Set-Cookie")) {
        if (string.contains("ASP.NET_SessionId")) {
         //TODO
          Log.i(TAG, "put: " + string);
        }
      }
  }
}