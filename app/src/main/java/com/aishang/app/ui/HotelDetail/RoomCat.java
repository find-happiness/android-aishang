package com.aishang.app.ui.HotelDetail;

import com.aishang.app.data.model.JHotelRoomCatListByhotelIDResult;
import java.util.List;

/**
 * Created by song on 2016/3/11.
 */
public class RoomCat {
  private JHotelRoomCatListByhotelIDResult.GRoomTypeListEntity roomTypeListEntity;
  private List<JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity> catListEntities;

  public RoomCat(JHotelRoomCatListByhotelIDResult.GRoomTypeListEntity roomTypeListEntity,
      List<JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity> catListEntities) {
    this.roomTypeListEntity = roomTypeListEntity;
    this.catListEntities = catListEntities;
  }

  public JHotelRoomCatListByhotelIDResult.GRoomTypeListEntity getRoomTypeListEntity() {
    return roomTypeListEntity;
  }

  public void setRoomTypeListEntity(
      JHotelRoomCatListByhotelIDResult.GRoomTypeListEntity roomTypeListEntity) {
    this.roomTypeListEntity = roomTypeListEntity;
  }

  public List<JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity> getCatListEntities() {
    return catListEntities;
  }

  public void setCatListEntities(
      List<JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity> catListEntities) {
    this.catListEntities = catListEntities;
  }

  public void addCatEntity(JHotelRoomCatListByhotelIDResult.HotelRoomCatListEntity entity) {
    catListEntities.add(entity);
  }
}
