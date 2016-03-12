package com.aishang.app.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/**
 * Created by song on 2016/3/12.
 */
public class HotelOrder implements Parcelable {

  private JHotelRoomCatListByhotelIDResult.GRoomTypeListEntity type;
  private JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity roomCat;

  private int roomNum;

  public HotelOrder(JHotelRoomCatListByhotelIDResult.GRoomTypeListEntity type,
      JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity roomCat, int roomNum) {
    this.type = type;
    this.roomCat = roomCat;
    this.roomNum = roomNum;
  }

  public int getRoomNum() {
    return roomNum;
  }

  public void setRoomNum(int roomNum) {
    this.roomNum = roomNum;
  }

  public JHotelRoomCatListByhotelIDResult.GRoomTypeListEntity getType() {
    return type;
  }

  public void setType(JHotelRoomCatListByhotelIDResult.GRoomTypeListEntity type) {
    this.type = type;
  }

  public JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity getRoomCat() {
    return roomCat;
  }

  public void setRoomCat(JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity roomCat) {
    this.roomCat = roomCat;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeSerializable(this.type);
    dest.writeSerializable(this.roomCat);
    dest.writeInt(this.roomNum);
  }

  protected HotelOrder(Parcel in) {
    this.type = (JHotelRoomCatListByhotelIDResult.GRoomTypeListEntity) in.readSerializable();
    this.roomCat = (JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity) in.readSerializable();
    this.roomNum = in.readInt();
  }

  public static final Creator<HotelOrder> CREATOR = new Creator<HotelOrder>() {
    public HotelOrder createFromParcel(Parcel source) {
      return new HotelOrder(source);
    }

    public HotelOrder[] newArray(int size) {
      return new HotelOrder[size];
    }
  };
}
