package com.aishang.app.data.model;

/**
 * /mobile/member/businessList.ashx version 3
 * 
 * @author wang
 * 
 */
public class JBusinessListParam {
	// {memberAccount:string,
	// cookie:string,
	// filterType:int,
	// startDate:string,
	// endDate:string,
	// recStart:int,
	// recCount:int,
	// orderby:string(asc,desc),
	// memberLevel:int,
	// subMemberPhone:string,
	// }

	private String memberAccount;
	private String cookie;
	private int filterType;
	private String startDate;
	private String endDate;
	private int recStart;
	private int recCount;
	private String orderby;
	private int memberLevel;
	private String subMemberPhone;

	public String getMemberAccount() {
		return memberAccount;
	}

	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public int getFilterType() {
		return filterType;
	}

	public void setFilterType(int filterType) {
		this.filterType = filterType;
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

	public int getRecStart() {
		return recStart;
	}

	public void setRecStart(int recStart) {
		this.recStart = recStart;
	}

	public int getRecCount() {
		return recCount;
	}

	public void setRecCount(int recCount) {
		this.recCount = recCount;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public int getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}

	public String getSubMemberPhone() {
		return subMemberPhone;
	}

	public void setSubMemberPhone(String subMemberPhone) {
		this.subMemberPhone = subMemberPhone;
	}

}
