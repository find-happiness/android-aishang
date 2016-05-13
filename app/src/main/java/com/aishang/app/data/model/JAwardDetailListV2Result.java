package com.aishang.app.data.model;

import java.util.List;

/**
 * Created by song on 2016/5/13.
 */
public class JAwardDetailListV2Result {

  /**
   * result : success
   * DataList : [{"createTime":"2016-05-05 04:43:31","dealMerchant":"点赞","preAmount":1001.2,"amount":0.1},{"createTime":"2016-05-05 04:43:31","dealMerchant":"点赞","preAmount":1001.2,"amount":0.1},{"createTime":"2016-05-05 03:14:53","dealMerchant":"评论","preAmount":1001.1,"amount":0.1},{"createTime":"2016-05-05 03:14:21","dealMerchant":"评论","preAmount":1001,"amount":0.1},{"createTime":"2016-05-05 03:13:54","dealMerchant":"评论","preAmount":1000.9,"amount":0.1}]
   * totalCount : 14
   */

  private String result;
  private int totalCount;
  /**
   * createTime : 2016-05-05 04:43:31
   * dealMerchant : 点赞
   * preAmount : 1001.2
   * amount : 0.1
   */

  private List<DataListBean> DataList;

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public List<DataListBean> getDataList() {
    return DataList;
  }

  public void setDataList(List<DataListBean> DataList) {
    this.DataList = DataList;
  }

  public static class DataListBean {
    private String createTime;
    private String dealMerchant;
    private float preAmount;
    private float amount;

    public String getCreateTime() {
      return createTime;
    }

    public void setCreateTime(String createTime) {
      this.createTime = createTime;
    }

    public String getDealMerchant() {
      return dealMerchant;
    }

    public void setDealMerchant(String dealMerchant) {
      this.dealMerchant = dealMerchant;
    }

    public float getPreAmount() {
      return preAmount;
    }

    public void setPreAmount(float preAmount) {
      this.preAmount = preAmount;
    }

    public float getAmount() {
      return amount;
    }

    public void setAmount(float amount) {
      this.amount = amount;
    }
  }
}
