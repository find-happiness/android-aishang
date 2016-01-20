package com.aishang.app.data.model;

public class JMemberProfileParam {

	// {memberAccount:string, cookies:string, imei:string, googleLat:float,
	// googleLng:float}

	private String memberAccount;
	private String cookies;
	private String imei;

	private float googleLat;
	private float googleLng;

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

	public float getGoogleLng() {
		return googleLng;
	}

	public void setGoogleLng(float googleLng) {
		this.googleLng = googleLng;
	}

}
