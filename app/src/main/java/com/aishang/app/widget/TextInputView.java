package com.aishang.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.aishang.app.R;
import com.rengwuxian.materialedittext.MaterialEditText;

public class TextInputView extends RelativeLayout {

  private static final String TAG = "TextInputView";
  private MaterialEditText mEditText;
  private TextView mHintText;

  public TextInputView(Context context) {
    super(context);
    init(null, 0);
  }

  public TextInputView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(attrs, 0);
  }

  public TextInputView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init(attrs, defStyle);
  }

  private void init(AttributeSet attrs, int defStyle) {

    LayoutInflater inflater = LayoutInflater.from(getContext());
    View view = inflater.inflate(R.layout.text_input, null);
    mHintText = (TextView) view.findViewById(R.id.hint);
    mEditText = (MaterialEditText) view.findViewById(R.id.edit);
    if (attrs != null) {
      // Load attributes
      final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TextInputLayout);

      int textSize = a.getResourceId(R.styleable.TextInputView_strSize, 0);
      if (textSize > 0) {
        mEditText.setTextSize(textSize);
        mHintText.setTextSize(textSize);
      }

      String hintText = a.getString(R.styleable.TextInputView_hintText);
      if (!TextUtils.isEmpty(hintText)) {
        mEditText.setHint(hintText);
        mHintText.setText(hintText);
      }

      int color = a.getColor(R.styleable.TextInputView_editColor, Color.BLACK);
      mEditText.setTextColor(color);

      int hintColor = a.getColor(R.styleable.TextInputView_hintTextColor, Color.GRAY);
      mHintText.setHintTextColor(hintColor);

      boolean editable = a.getBoolean(R.styleable.TextInputView_editTextable, false);

      Log.i(TAG, "init: " + editable);

      mEditText.setEnabled(editable);
      a.recycle();
    }
    addView(view);
  }

  public Editable getText() {
    return mEditText.getText();
  }

  public void setText(String text) {
    mEditText.setText(text);
  }
}
