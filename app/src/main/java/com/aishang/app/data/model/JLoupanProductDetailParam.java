package com.aishang.app.data.model;

/**
 * /mobile/loupan/loupanProductDetail.ashx ;version:1 bTourist:0 not need
 * touristList,1 need touristList; bGallery:0 not need imageList,1 need imgList;
 * 
 * @author wang
 * 
 */
public class JLoupanProductDetailParam {
	// { loupanProductID:int, bTourist:int}
	private int loupanProductID;
	private int bTourist;
	private int bGallery;

	public int getbGallery() {
		return bGallery;
	}

	public void setbGallery(int bGallery) {
		this.bGallery = bGallery;
	}

	public int getLoupanProductID() {
		return loupanProductID;
	}

	public void setLoupanProductID(int loupanProductID) {
		this.loupanProductID = loupanProductID;
	}

	public int getbTourist() {
		return bTourist;
	}

	public void setbTourist(int bTourist) {
		this.bTourist = bTourist;
	}

}
