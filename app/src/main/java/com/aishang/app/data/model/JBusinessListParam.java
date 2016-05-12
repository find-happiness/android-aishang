package com.aishang.app.data.model;

/**
 * /mobile/member/businessList.ashx version 3
 * 
 * @author wang
 * 
 */
public class JBusinessListParam {

	/**
	 * memberAccount : 13883224451
	 * cookie : A3250BAEFDDF7EBD0C7BB2AE7B5F0247
	 * recIndex : 1
	 * recCount : 5
	 */

	private String memberAccount;
	private String cookie;
	private int recIndex;
	private int recCount;

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

	public int getRecIndex() {
		return recIndex;
	}

	public void setRecIndex(int recIndex) {
		this.recIndex = recIndex;
	}

	public int getRecCount() {
		return recCount;
	}

	public void setRecCount(int recCount) {
		this.recCount = recCount;
	}
}
