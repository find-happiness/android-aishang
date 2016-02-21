package com.aishang.app.util;

import android.widget.NumberPicker;

/**
 * Created by song on 2016/2/21.
 */
public interface NumberPickerDelegate {
  void OnPick(NumberPicker picker, int num);
}
