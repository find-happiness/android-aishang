package com.aishang.app.ui.TravelFavorites;

import com.aishang.app.data.model.MyTravel;
import java.util.List;

/**
 * Created by song on 2016/3/13.
 */
public class AdapterContentModel {

  private int total;
  List<MyTravel> myTravels;

  public AdapterContentModel(int total, List<MyTravel> myTravels) {
    this.total = total;
    this.myTravels = myTravels;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public List<MyTravel> getMyTravels() {
    return myTravels;
  }

  public void setMyTravels(List<MyTravel> myTravels) {
    this.myTravels = myTravels;
  }
}
