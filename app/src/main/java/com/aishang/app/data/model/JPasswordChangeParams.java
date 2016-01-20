package com.aishang.app.data.model;

public class JPasswordChangeParams {

	/**
	 * memberAccount:string, cookies:string, oldPassword:string,
	 * newPassword:string, imei:string, googleLat:float, googleLnt:float
	 */

	private String memberAccount;
	private String cookies;
	private String oldPassword;
	private String newPassword;
	private String imei;
	private float googleLat;
	private float googleLnt;

	public String getMemberAccount() {
		return memberAccount;
	}

	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}

	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public float getGoogleLat() {
		return googleLat;
	}

	public void setGoogleLat(float googleLat) {
		this.googleLat = googleLat;
	}

	public float getGoogleLnt() {
		return googleLnt;
	}

	public void setGoogleLnt(float googleLnt) {
		this.googleLnt = googleLnt;
	}
}
