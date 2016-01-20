package com.aishang.app.data.model;

/**
 * /mobile/loupan/loupanPicList.ashx version:1
 * 
 * @author wang
 * 
 */
public class JLoupanPicListParam {
	// {loupanID:int, loupanProductID:string}
	private int loupanID;
	private int loupanProductID;

	public int getLoupanID() {
		return loupanID;
	}

	public void setLoupanID(int loupanID) {
		this.loupanID = loupanID;
	}

	public int getLoupanProductID() {
		return loupanProductID;
	}

	public void setLoupanProductID(int loupanProductID) {
		this.loupanProductID = loupanProductID;
	}

}
