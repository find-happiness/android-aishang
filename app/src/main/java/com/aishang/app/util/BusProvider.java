package com.aishang.app.util;

import com.squareup.otto.Bus;

/**
 * Created by song on 2016/2/16.
 */
public class BusProvider {
  private static final Bus BUS = new Bus();

  public static Bus getInstance() {
    return BUS;
  }

  private BusProvider() {
    // No instances.
  }
}
