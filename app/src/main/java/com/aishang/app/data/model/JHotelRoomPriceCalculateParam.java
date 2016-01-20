package com.aishang.app.data.model;

/**
 * /mobile/hotel/hotelPriceList.ashx version: 1
 * 
 * @author wang
 * 
 */
public class JHotelRoomPriceCalculateParam {
	// {hotelID:int, priceID:int, startDate:string(YYYY-MM-DD), endDate:string}
	private int hotelID;
	private int priceID;
	private int roomCatID;
	private String startDate;
	private String endDate;

	public int getRoomCatID() {
		return roomCatID;
	}

	public void setRoomCatID(int roomCatID) {
		this.roomCatID = roomCatID;
	}

	public int getHotelID() {
		return hotelID;
	}

	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}

	public int getPriceID() {
		return priceID;
	}

	public void setPriceID(int priceID) {
		this.priceID = priceID;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
