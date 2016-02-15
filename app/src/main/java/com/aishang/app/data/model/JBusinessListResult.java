package com.aishang.app.data.model;
/**
 * /mobile/member/businessList.ashx version 3
 * @author wang
 *
 */
public class JBusinessListResult {
	// {
	// result:string,
	// awardTotal:decimal(10,2),
	// awardTotalNot:decimal(10,2),
	// recCount:int,
	// businessList:[
	// {sno:string,
	// idx:int,
	// typeText:string,
	// priceText:string,
	// dealText:string,
	// dealDateText:string,
	// creditPoint:int,
	// award:decimal(10,2),
	// statusAward:int,
	// comment:string
	// }, ….
	// ]
	// }
	private String result;
	private float awardTotal;
	private float awardTotalNot;
	private int recCount;
	private Business[] businessList;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public float getAwardTotal() {
		return awardTotal;
	}

	public void setAwardTotal(float awardTotal) {
		this.awardTotal = awardTotal;
	}

	public float getAwardTotalNot() {
		return awardTotalNot;
	}

	public void setAwardTotalNot(float awardTotalNot) {
		this.awardTotalNot = awardTotalNot;
	}

	public int getRecCount() {
		return recCount;
	}

	public void setRecCount(int recCount) {
		this.recCount = recCount;
	}

	public Business[] getBusinessList() {
		return businessList;
	}

	public void setBusinessList(Business[] businessList) {
		this.businessList = businessList;
	}

	public class Business {

		//
		// sno:string,
		// idx:int,
		// typeText:string,
		// priceText:string,
		// dealText:string,
		// dealDateText:string,
		// creditPoint:int,
		// awardSelf:decimal(10,2),
		// award2Parent:decimal(10,2),
		// statusAward:int,
		// comment:string
		// memberName:string,
		// memberPhone:string,

		public String sno;
		public int idx;
		private String typeText;
		private String priceText;
		private String dealText;
		private String dealDateText;
		private int creditPoint;
		private float awardSelf;
		private float award2Parent;
		private int statusAward;
		private String comment;
		private String memberName;
		private String memberPhone;

		public String getSno() {
			return sno;
		}

		public void setSno(String sno) {
			this.sno = sno;
		}

		public int getIdx() {
			return idx;
		}

		public void setIdx(int idx) {
			this.idx = idx;
		}

		public String getTypeText() {
			return typeText;
		}

		public void setTypeText(String typeText) {
			this.typeText = typeText;
		}

		public String getPriceText() {
			return priceText;
		}

		public void setPriceText(String priceText) {
			this.priceText = priceText;
		}

		public String getDealText() {
			return dealText;
		}

		public void setDealText(String dealText) {
			this.dealText = dealText;
		}

		public String getDealDateText() {
			return dealDateText;
		}

		public void setDealDateText(String dealDateText) {
			this.dealDateText = dealDateText;
		}

		public int getCreditPoint() {
			return creditPoint;
		}

		public void setCreditPoint(int creditPoint) {
			this.creditPoint = creditPoint;
		}

		public float getAwardSelf() {
			return awardSelf;
		}

		public void setAwardSelf(float awardSelf) {
			this.awardSelf = awardSelf;
		}

		public float getAward2Parent() {
			return award2Parent;
		}

		public void setAward2Parent(float award2Parent) {
			this.award2Parent = award2Parent;
		}

		public int getStatusAward() {
			return statusAward;
		}

		public void setStatusAward(int statusAward) {
			this.statusAward = statusAward;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public String getMemberName() {
			return memberName;
		}

		public void setMemberName(String memberName) {
			this.memberName = memberName;
		}

		public String getMemberPhone() {
			return memberPhone;
		}

		public void setMemberPhone(String memberPhone) {
			this.memberPhone = memberPhone;
		}
	}
}
