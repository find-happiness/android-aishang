package com.aishang.app.data.model;

/**
 * Created by song on 2016/4/13.
 */
public class AdapterImgModel {
  float x;
  float y;
  int index;

  public static AdapterImgModel create(float x, float y, int index) {
    return new AdapterImgModel(x, y, index);
  }

  private AdapterImgModel(float x, float y, int index) {
    this.x = x;
    this.y = y;
    this.index = index;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
  }
}
