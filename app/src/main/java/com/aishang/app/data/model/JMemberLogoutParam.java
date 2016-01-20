package com.aishang.app.data.model;

/**
 * member logout /mobile/member/memberLogout.ashx
 * 
 * @author wang
 * 
 */
public class JMemberLogoutParam {
	// {memberAccount:string, cookie:string}
	private String memberAccount;
	private String cookie;

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

}
