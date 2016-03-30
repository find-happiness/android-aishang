package com.aishang.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by song on 2016/3/30.
 */
public class SoftInputLinearLayout extends LinearLayout {

  public SoftInputLinearLayout(final Context context, final AttributeSet attrs) {
    super(context, attrs);
  }

  public SoftInputLinearLayout(Context context) {
    super(context);
  }

  private OnSoftKeyboardListener onSoftKeyboardListener;

  @Override protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
    if (onSoftKeyboardListener != null) {
      final int newSpec = View.MeasureSpec.getSize(heightMeasureSpec);
      final int oldSpec = getMeasuredHeight();
      // If layout became smaller, that means something forced it to resize. Probably soft keyboard :)
      if (oldSpec > newSpec) {
        onSoftKeyboardListener.onShown();
      } else if (oldSpec < newSpec) {
        onSoftKeyboardListener.onHidden();
      }
    }
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  public final void setOnSoftKeyboardListener(final OnSoftKeyboardListener listener) {
    this.onSoftKeyboardListener = listener;
  }

  // Simplest possible listener :)
  public interface OnSoftKeyboardListener {
    public void onShown();

    public void onHidden();
  }
}