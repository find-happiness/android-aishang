package com.aishang.app.data.model;

/**
 * Created by song on 2016/2/16.
 */
public class JMreActivityDetailResult {
  /// <summary>
  /// 返回结果 success or 具体错误信息
  /// </summary>
  public String result;
  public JDetail[] detail;
  public int totalReview;
  public JReview[] reviewList;

  public class JDetail {
    public int activityID;
    public int catID;
    public String catName;
    /// <summary>
    ///  (是否能编辑 0—否，1—是，逻辑待定) 
    /// </summary>
    public int canEdit;
    public String title;
    public String shortDesc;
    public String description;
    /// <summary>
    /// (举行开始时间 YYYY-MM-DD)
    /// </summary>
    public String startTime;
    /// <summary>
    /// (举行结束时间)
    /// </summary>
    public String endTime;
    /// <summary>
    /// (举行地点)
    /// </summary>
    public String position;
    /// <summary>
    ///  (人数限制 0--不限制，>0 有限制，默认0) 
    /// </summary>
    public int maxMember;
    /// <summary>
    /// (当前人数，默认 0，要求 >0 )
    /// </summary>
    public int curMembers;
    /// <summary>
    /// (报名开始时间)
    /// </summary>
    public String enrollStartTime;
    /// <summary>
    /// (报名结束时间)
    /// </summary>
    public String enrollEndTime;
    /// <summary>
    ///  (费用 0--免费  >0 --收费，单位 ￥)
    /// </summary>
    public int fee;
    /// <summary>
    /// (宣传图片地址)
    /// </summary>
    public String imageUrl;
    /// <summary>
    /// (相关专题/内容URL地址)
    /// </summary>
    public String relateUrl;
    /// <summary>
    /// (联系人)
    /// </summary>
    public String contact;
    /// <summary>
    /// (联系电话)
    /// </summary>
    public String contactPhone;
    /// <summary>
    /// (参与状态 0—取消 1--参与，2-延期参与)
    /// </summary>
    public int enrollStatus;
    /// <summary>
    ///  (评价状态 0—不能评价， 1—可评价，2—已评价)
    /// </summary>
    public int beScored;
    public JScoreTitle[] scoreTitles;
  }

  public class JScoreTitle {
    /// <summary>
    /// (评价标题)
    /// </summary>
    public String title;
    /// <summary>
    /// (最大分数)
    /// </summary>
    public float max;
  }

  /// <summary>
  /// 
  /// </summary>
  public class JReview {
    public int enrollID;
    public String userName;
    public String userPhone;
    /// <summary>
    /// (0-非会员    >0—会员) 
    /// </summary>
    public int memberID;
    /// <summary>
    /// (评论时间)
    /// </summary>
    public String reviewTime;
    /// <summary>
    /// (评价内容) 
    /// </summary>
    public String reviewContent;
    /// <summary>
    /// (评论审核：0--未审核，1--审核通过  2--审核不通过)
    /// </summary>
    public int reviewCheckStatus;
    public float score1;
    public float score2;
    public float score3;
  }
}
