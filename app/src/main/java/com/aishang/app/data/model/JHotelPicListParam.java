package com.aishang.app.data.model;
/**
 * /mobile/hotel/hotelPicList.ashx
 * version:1
 * @author wang
 *
 */
public class JHotelPicListParam {
//{hotelID:int, hotelPriceID:int}
	private int hotelID;
	private int hotelPriceID;
	public int getHotelID() {
		return hotelID;
	}
	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}
	public int getHotelPriceID() {
		return hotelPriceID;
	}
	public void setHotelPriceID(int hotelPriceID) {
		this.hotelPriceID = hotelPriceID;
	}
}
