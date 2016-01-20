package com.aishang.app.data.model;
/**
 * /mobile/member/memberRegister.ashx
 * @author wang
 *
 */
public class JMemberRegisterParam {
//	{memberName:string, 
//		mobilePhone:string, 
//		imei:string, 
//		password:string, 
//		recomMemberName: string, 
//		recomMobilePhone:string }
	
	private String memberName;
	private String mobilePhone;
	private String imei;
	private String password;
	private String recomMemberName;
	private String recomMobilePhone;
	private int memberSubCat;
	
	public int getMemberSubCat() {
		return memberSubCat;
	}
	public void setMemberSubCat(int memberSubCat) {
		this.memberSubCat = memberSubCat;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRecomMemberName() {
		return recomMemberName;
	}
	public void setRecomMemberName(String recomMemberName) {
		this.recomMemberName = recomMemberName;
	}
	public String getRecomMobilePhone() {
		return recomMobilePhone;
	}
	public void setRecomMobilePhone(String recomMobilePhone) {
		this.recomMobilePhone = recomMobilePhone;
	}
	

}
