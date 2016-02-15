package com.aishang.app.data.model;

public class JMyVacationApplyListParams {

	/**
	 * memberAccount:string, cookie:string, filterVACardID:string,
	 * filterStartDate:string, filerEndDate:string(YYYY-MM-DD), recStart:int,
	 * recCount:int
	 */

	private String memberAccount;
	private String cookie;
	private String filterVACardID;
	private String filterStartDate;
	private String filerEndDate;
	private int recStart;
	private int recCount;
	private int status;

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

	public String getFilterVACardID() {
		return filterVACardID;
	}

	public void setFilterVACardID(String filterVACardID) {
		this.filterVACardID = filterVACardID;
	}

	public String getFilterStartDate() {
		return filterStartDate;
	}

	public void setFilterStartDate(String filterStartDate) {
		this.filterStartDate = filterStartDate;
	}

	public String getFilerEndDate() {
		return filerEndDate;
	}

	public void setFilerEndDate(String filerEndDate) {
		this.filerEndDate = filerEndDate;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	

}
