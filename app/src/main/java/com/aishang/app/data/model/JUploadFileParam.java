package com.aishang.app.data.model;

/**
 * /mobile/uploadFile.ashx (HTTPS) 版本2
 * 
 * @author wang
 * 
 */
public class JUploadFileParam {
	// {memberAccount:string, cookies:string,
	// model:string, credential:string, param1:string, param2:string,
	// param3:string}

	private String memberAccount;
	private String cookies;
	private String model;
	private String credential;
	private String param1;
	private String param2;
	private String param3;

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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCredential() {
		return credential;
	}

	public void setCredential(String credential) {
		this.credential = credential;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public String getParam3() {
		return param3;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
	}

}
