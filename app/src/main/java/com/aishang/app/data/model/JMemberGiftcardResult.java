package com.aishang.app.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/**
 * Created by happiness on 2016/6/7.
 */
public class JMemberGiftcardResult implements Parcelable {

  /**
   * result : success
   * memberGiftcardList : [{"GUID":"3b4201fd-1e1c-4525-9dda-d3074e35cc01","giftName":"优惠券1","value":300,"startDate":"2016-05-27T14:09:47","endDate":"2016-08-27T14:09:49","status":0,"commet":"未使用","remarks":"","memberGUID":"3b4201fd-1e1c-4525-9dda-d3074e35cc05"},{"GUID":"3b4201fd-1e1c-4525-9dda-d3074e35cc02","giftName":"优惠券2","value":400,"startDate":"2016-05-27T14:10:15","endDate":"2016-05-27T14:10:18","status":1,"commet":"已使用","remarks":"","memberGUID":"3b4201fd-1e1c-4525-9dda-d3074e35cc05"}]
   */

  private String result;
  /**
   * GUID : 3b4201fd-1e1c-4525-9dda-d3074e35cc01
   * giftName : 优惠券1
   * value : 300
   * startDate : 2016-05-27T14:09:47
   * endDate : 2016-08-27T14:09:49
   * status : 0
   * commet : 未使用
   * remarks :
   * memberGUID : 3b4201fd-1e1c-4525-9dda-d3074e35cc05
   */

  private List<MemberGiftcardListBean> memberGiftcardList;

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public List<MemberGiftcardListBean> getMemberGiftcardList() {
    return memberGiftcardList;
  }

  public void setMemberGiftcardList(List<MemberGiftcardListBean> memberGiftcardList) {
    this.memberGiftcardList = memberGiftcardList;
  }

  public static class MemberGiftcardListBean implements Parcelable {
    private String GUID;
    private String giftName;
    private int value;
    private String startDate;
    private String endDate;
    private int status;
    private String commet;
    private String remarks;
    private String memberGUID;

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

    @Override public int describeContents() {
      return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
      dest.writeString(this.GUID);
      dest.writeString(this.giftName);
      dest.writeInt(this.value);
      dest.writeString(this.startDate);
      dest.writeString(this.endDate);
      dest.writeInt(this.status);
      dest.writeString(this.commet);
      dest.writeString(this.remarks);
      dest.writeString(this.memberGUID);
    }

    public MemberGiftcardListBean() {
    }

    protected MemberGiftcardListBean(Parcel in) {
      this.GUID = in.readString();
      this.giftName = in.readString();
      this.value = in.readInt();
      this.startDate = in.readString();
      this.endDate = in.readString();
      this.status = in.readInt();
      this.commet = in.readString();
      this.remarks = in.readString();
      this.memberGUID = in.readString();
    }

    public static final Parcelable.Creator<MemberGiftcardListBean> CREATOR =
        new Parcelable.Creator<MemberGiftcardListBean>() {
          @Override public MemberGiftcardListBean createFromParcel(Parcel source) {
            return new MemberGiftcardListBean(source);
          }

          @Override public MemberGiftcardListBean[] newArray(int size) {
            return new MemberGiftcardListBean[size];
          }
        };
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.result);
    dest.writeTypedList(this.memberGiftcardList);
  }

  public JMemberGiftcardResult() {
  }

  protected JMemberGiftcardResult(Parcel in) {
    this.result = in.readString();
    this.memberGiftcardList = in.createTypedArrayList(MemberGiftcardListBean.CREATOR);
  }

  public static final Parcelable.Creator<JMemberGiftcardResult> CREATOR =
      new Parcelable.Creator<JMemberGiftcardResult>() {
        @Override public JMemberGiftcardResult createFromParcel(Parcel source) {
          return new JMemberGiftcardResult(source);
        }

        @Override public JMemberGiftcardResult[] newArray(int size) {
          return new JMemberGiftcardResult[size];
        }
      };
}
