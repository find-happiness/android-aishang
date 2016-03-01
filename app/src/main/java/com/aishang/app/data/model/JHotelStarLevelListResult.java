package com.aishang.app.data.model;

import java.util.List;

/**
 * Created by song on 2016/3/1.
 */
public class JHotelStarLevelListResult {

  /**
   * result : success
   * starList : [{"id":1,"name":"一星"},{"id":2,"name":"二星"},{"id":3,"name":"三星"},{"id":4,"name":"四星"},{"id":5,"name":"五星"},{"id":6,"name":"六星"},{"id":7,"name":"七星"}]
   */

  private String result;
  /**
   * id : 1
   * name : 一星
   */

  private List<StarListEntity> starList;

  public void setResult(String result) {
    this.result = result;
  }

  public void setStarList(List<StarListEntity> starList) {
    this.starList = starList;
  }

  public String getResult() {
    return result;
  }

  public List<StarListEntity> getStarList() {
    return starList;
  }

  public static class StarListEntity {
    private int id;
    private String name;

    public void setId(int id) {
      this.id = id;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getId() {
      return id;
    }

    public String getName() {
      return name;
    }
  }
}
