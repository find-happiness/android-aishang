package com.aishang.app.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by happiness on 2016/6/7.
 */
public class JMemberGiftcardResult implements Parcelable {

  /**
   * result : success
   * total : 6
   * memberGiftcardList : [{"GUID":"d764007d-3bf5-4735-ae5f-fc939b131e57","giftName":"优惠劵3","value":568,"startDate":"2016-06-30T00:00:00","endDate":"2017-06-30T00:00:00","strStartDate":"2016-06-30","strEndDate":"2017-06-30","status":1,"commet":"注册送抵用券","remarks":"","memberGUID":"3b4201fd-1e1c-4525-9dda-d3074e35cc05","referModule":"","referPKID":""},{"GUID":"d764007d-3bf5-4735-ae5f-fc939b131e58","giftName":"优惠劵4","value":568,"startDate":"2016-06-30T00:00:00","endDate":"2017-06-30T00:00:00","strStartDate":"2016-06-30","strEndDate":"2017-06-30","status":1,"commet":"注册送抵用券","remarks":"","memberGUID":"3b4201fd-1e1c-4525-9dda-d3074e35cc05","referModule":"","referPKID":""},{"GUID":"d764007d-3bf5-4735-ae5f-fc939b131e59","giftName":"优惠劵5","value":568,"startDate":"2016-06-30T00:00:00","endDate":"2017-06-30T00:00:00","strStartDate":"2016-06-30","strEndDate":"2017-06-30","status":1,"commet":"注册送抵用券","remarks":"","memberGUID":"3b4201fd-1e1c-4525-9dda-d3074e35cc05","referModule":"","referPKID":""},{"GUID":"bd1ecbd2-bab3-4f0f-9706-b55177911116","giftName":"黄水精装度假房免费住","value":666,"startDate":"2016-07-01T00:00:00","endDate":"2016-09-28T00:00:00","strStartDate":"2016-07-01","strEndDate":"2016-09-28","status":0,"commet":"爱尚旅居邀您\u201c清凉一夏\u201d黄水精装度假房免费住！分享活动获得\u201c666元代金券\u201d可免费入住黄水精装度假房两晚","remarks":"分享活动","memberGUID":"3b4201fd-1e1c-4525-9dda-d3074e35cc05","referModule":"hotel","referPKID":"175"},{"GUID":"3b4201fd-1e1c-4525-9dda-d3074e35cc01","giftName":"优惠券1","value":300,"startDate":"2016-05-27T00:00:00","endDate":"2016-08-27T00:00:00","strStartDate":"2016-05-27","strEndDate":"2016-08-27","status":1,"commet":"未使用","remarks":"","memberGUID":"3b4201fd-1e1c-4525-9dda-d3074e35cc05","referModule":"","referPKID":""},{"GUID":"3b4201fd-1e1c-4525-9dda-d3074e35cc02","giftName":"优惠券2","value":400,"startDate":"2016-05-27T00:00:00","endDate":"2016-05-27T00:00:00","strStartDate":"2016-05-27","strEndDate":"2016-05-27","status":1,"commet":"已使用","remarks":"","memberGUID":"3b4201fd-1e1c-4525-9dda-d3074e35cc05","referModule":"","referPKID":""}]
   */

  private String result;
  private int total;
  /**
   * GUID : d764007d-3bf5-4735-ae5f-fc939b131e57
   * giftName : 优惠劵3
   * value : 568
   * startDate : 2016-06-30T00:00:00
   * endDate : 2017-06-30T00:00:00
   * strStartDate : 2016-06-30
   * strEndDate : 2017-06-30
   * status : 1
   * commet : 注册送抵用券
   * remarks :
   * memberGUID : 3b4201fd-1e1c-4525-9dda-d3074e35cc05
   * referModule :
   * referPKID :
   */

  private List<MemberGiftcardListEntity> memberGiftcardList;

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public List<MemberGiftcardListEntity> getMemberGiftcardList() {
    return memberGiftcardList;
  }

  public void setMemberGiftcardList(List<MemberGiftcardListEntity> memberGiftcardList) {
    this.memberGiftcardList = memberGiftcardList;
  }

  public static class MemberGiftcardListEntity implements Parcelable {
    private String GUID;
    private String giftName;
    private int value;
    private String startDate;
    private String endDate;
    private String strStartDate;
    private String strEndDate;
    private int status;
    private String commet;
    private String remarks;
    private String memberGUID;
    private String referModule;
    private String referPKID;

    public String getGUID() {
      return GUID;
    }

    public void setGUID(String GUID) {
      this.GUID = GUID;
    }

    public String getGiftName() {
      return giftName;
    }

    public void setGiftName(String giftName) {
      this.giftName = giftName;
    }

    public int getValue() {
      return value;
    }

    public void setValue(int value) {
      this.value = value;
    }

    public String getStartDate() {
      return startDate;
    }

    public void setStartDate(String startDate) {
      this.startDate = startDate;
    }

    public String getEndDate() {
      return endDate;
    }

    public void setEndDate(String endDate) {
      this.endDate = endDate;
    }

    public String getStrStartDate() {
      return strStartDate;
    }

    public void setStrStartDate(String strStartDate) {
      this.strStartDate = strStartDate;
    }

    public String getStrEndDate() {
      return strEndDate;
    }

    public void setStrEndDate(String strEndDate) {
      this.strEndDate = strEndDate;
    }

    public int getStatus() {
      return status;
    }

    public void setStatus(int status) {
      this.status = status;
    }

    public String getCommet() {
      return commet;
    }

    public void setCommet(String commet) {
      this.commet = commet;
    }

    public String getRemarks() {
      return remarks;
    }

    public void setRemarks(String remarks) {
      this.remarks = remarks;
    }

    public String getMemberGUID() {
      return memberGUID;
    }

    public void setMemberGUID(String memberGUID) {
      this.memberGUID = memberGUID;
    }

    public String getReferModule() {
      return referModule;
    }

    public void setReferModule(String referModule) {
      this.referModule = referModule;
    }

    public String getReferPKID() {
      return referPKID;
    }

    public void setReferPKID(String referPKID) {
      this.referPKID = referPKID;
    }

    @Override public int describeContents() {
      return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
      dest.writeString(this.GUID);
      dest.writeString(this.giftName);
      dest.writeInt(this.value);
      dest.writeString(this.startDate);
      dest.writeString(this.endDate);
      dest.writeString(this.strStartDate);
      dest.writeString(this.strEndDate);
      dest.writeInt(this.status);
      dest.writeString(this.commet);
      dest.writeString(this.remarks);
      dest.writeString(this.memberGUID);
      dest.writeString(this.referModule);
      dest.writeString(this.referPKID);
    }

    public MemberGiftcardListEntity() {
    }

    protected MemberGiftcardListEntity(Parcel in) {
      this.GUID = in.readString();
      this.giftName = in.readString();
      this.value = in.readInt();
      this.startDate = in.readString();
      this.endDate = in.readString();
      this.strStartDate = in.readString();
      this.strEndDate = in.readString();
      this.status = in.readInt();
      this.commet = in.readString();
      this.remarks = in.readString();
      this.memberGUID = in.readString();
      this.referModule = in.readString();
      this.referPKID = in.readString();
    }

    public static final Creator<MemberGiftcardListEntity> CREATOR =
        new Creator<MemberGiftcardListEntity>() {
          @Override public MemberGiftcardListEntity createFromParcel(Parcel source) {
            return new MemberGiftcardListEntity(source);
          }

          @Override public MemberGiftcardListEntity[] newArray(int size) {
            return new MemberGiftcardListEntity[size];
          }
        };
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.result);
    dest.writeInt(this.total);
    dest.writeList(this.memberGiftcardList);
  }

  public JMemberGiftcardResult() {
  }

  protected JMemberGiftcardResult(Parcel in) {
    this.result = in.readString();
    this.total = in.readInt();
    this.memberGiftcardList = new ArrayList<MemberGiftcardListEntity>();
    in.readList(this.memberGiftcardList, MemberGiftcardListEntity.class.getClassLoader());
  }

  public static final Creator<JMemberGiftcardResult> CREATOR =
      new Creator<JMemberGiftcardResult>() {
        @Override public JMemberGiftcardResult createFromParcel(Parcel source) {
          return new JMemberGiftcardResult(source);
        }

        @Override public JMemberGiftcardResult[] newArray(int size) {
          return new JMemberGiftcardResult[size];
        }
      };
}
