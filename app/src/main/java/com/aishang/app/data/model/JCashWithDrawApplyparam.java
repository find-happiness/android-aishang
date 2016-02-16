package com.aishang.app.data.model;

public class JCashWithDrawApplyparam {

	// { memberAccount:string,
	// cookie:string,
	// imei:string,
	// googleLat:float,
	// googleLng:float,
	// bankName:string,
	// accountNumber:string,
	// holder:string,
	// amount:decimal(10,2)
	// }

	private String memberAccount;
	private String cookie;
	private String imei;
	private float googleLat;
	private float googleLng;
	private String bankName;
	private String accountNumber;
	private String holder;
	private float amount;

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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}
