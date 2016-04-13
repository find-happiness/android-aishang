package com.aishang.app.data.model;

import java.util.List;

/**
 * Created by song on 2016/4/13.
 */
public class SmallImgModel {
  int index;
  String[] photos;
  int[] size;
  List<int[]> locations;

  public static SmallImgModel create(int index, String[] photos, List<int[]> locations,
      int[] size) {
    return new SmallImgModel(index, photos, locations, size);
  }

  private SmallImgModel(int index, String[] photos, List<int[]> locations, int[] size) {
    this.index = index;
    this.photos = photos;
    this.locations = locations;
    this.size = size;
  }

  public int[] getSize() {
    return size;
  }

  public void setSize(int[] size) {
    this.size = size;
  }

  public List<int[]> getLocations() {
    return locations;
  }

  public void setLocations(List<int[]> locations) {
    this.locations = locations;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public String[] getPhotos() {
    return photos;
  }

  public void setPhotos(String[] photos) {
    this.photos = photos;
  }
}
