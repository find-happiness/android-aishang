package com.aishang.app.data.model;

/**
 * /mobile/suggestion.ashx (HTTPS)
 * @author wang
 *
 */
public class JSuggestionParam {

	/**
	 * { memberAccount:string, cookie:string, title:string, content:string,
	 * contact:string }
	 */

	private String memberAccount;
	private String cookie;
	private String title;
	private String content;
	private String contact;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

}
