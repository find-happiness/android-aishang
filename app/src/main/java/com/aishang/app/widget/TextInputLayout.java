package com.aishang.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.Space;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.aishang.app.R;

public class TextInputLayout extends RelativeLayout {

  private EditText mEditText;
  private TextView mHintText;

  public TextInputLayout(Context context) {
    super(context);
    init(null, 0);
  }

  public TextInputLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(attrs, 0);
  }

  public TextInputLayout(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init(attrs, defStyle);
  }

  private void init(AttributeSet attrs, int defStyle) {

    LayoutInflater inflater = LayoutInflater.from(getContext());
    View view = inflater.inflate(R.layout.text_input, null);
    mHintText = (TextView) view.findViewById(R.id.hint);
    mEditText = (EditText) view.findViewById(R.id.edit);

    // Load attributes
    final TypedArray a =
        getContext().obtainStyledAttributes(attrs, R.styleable.TextInputLayout, defStyle, 0);

    int zoomViewResId = a.getResourceId(R.styleable.TextInputLayout_android_textSize, 0);
    if (zoomViewResId > 0) {
      mEditText.setTextSize(zoomViewResId);
      mHintText.setTextSize(zoomViewResId);
    }

    int headerViewResId = a.getResourceId(R.styleable.TextInputLayout_hint, 0);
    if (headerViewResId > 0) {
      mEditText.setText(headerViewResId);
      mHintText.setText(headerViewResId);
    }

    int color = a.getColor(R.styleable.ImgWithText_textColor, Color.BLACK);
    mEditText.setTextColor(color);

    int hintColor = a.getColor(R.styleable.TextInputLayout_hintColor, Color.BLACK);
    mHintText.setHintTextColor(hintColor);

    boolean editable = a.getBoolean(R.styleable.TextInputLayout_editable, true);
    mEditText.setEnabled(editable);

    a.recycle();

    addView(view);
  }

  public Editable getText() {
    return mEditText.getText();
  }

  public void setText(String text) {
    mEditText.setText(text);
  }
}
