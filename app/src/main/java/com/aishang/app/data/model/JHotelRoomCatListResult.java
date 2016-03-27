package com.aishang.app.data.model;

import java.util.List;

/**
 * Created by song on 2016/3/27.
 */
public class JHotelRoomCatListResult {

  /**
   * result : success
   * catList : [{"id":1,"name":"别墅"},{"id":2,"name":"标准间"},{"id":3,"name":"一室一厅"},{"id":4,"name":"花园洋房"},{"id":5,"name":"三室及以上"},{"id":6,"name":""}]
   */

  private String result;
  /**
   * id : 1
   * name : 别墅
   */

  private List<CatListEntity> catList;

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public List<CatListEntity> getCatList() {
    return catList;
  }

  public void setCatList(List<CatListEntity> catList) {
    this.catList = catList;
  }

  public static class CatListEntity {
    private int id;
    private String name;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
