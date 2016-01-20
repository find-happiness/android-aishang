package com.aishang.app.data.model;

/**
 * /mobile/hotel/hotelDetail.ashx version: 1
 * 
 * @author wang
 * 
 */
public class JHotelDetailParam {

	// {hotelID:int, bGallery:int, bSelectImage:int, bRoomCat:int,
	// bPriceList:int}
	// checkInDate:string(yyyy-mm-dd), checkOutDate:string(yyyy-mm-dd)
	// bHotelFaclilite:int, bHotelService:int, bRoomFacilite:int,
	// bRoomService:int,
	private int hotelID;
	private int bGallery;
	private int bSelectImage;
	private int bRoomCat;
	private int bPriceList;
	private int bBase;
	private String checkInDate;
	private String checkOutDate;
	private int bHotelFaclilite;
	private int bHotelService;
	private int bRoomFacilite;
	private int bRoomService;

	public int getbHotelFaclilite() {
		return bHotelFaclilite;
	}

	public void setbHotelFaclilite(int bHotelFaclilite) {
		this.bHotelFaclilite = bHotelFaclilite;
	}

	public int getbHotelService() {
		return bHotelService;
	}

	public void setbHotelService(int bHotelService) {
		this.bHotelService = bHotelService;
	}

	public int getbRoomFacilite() {
		return bRoomFacilite;
	}

	public void setbRoomFacilite(int bRoomFacilite) {
		this.bRoomFacilite = bRoomFacilite;
	}

	public int getbRoomService() {
		return bRoomService;
	}

	public void setbRoomService(int bRoomService) {
		this.bRoomService = bRoomService;
	}

	public int getbBase() {
		return bBase;
	}

	public void setbBase(int bBase) {
		this.bBase = bBase;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public int getHotelID() {
		return hotelID;
	}

	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}

	public int getbGallery() {
		return bGallery;
	}

	public void setbGallery(int bGallery) {
		this.bGallery = bGallery;
	}

	public int getbSelectImage() {
		return bSelectImage;
	}

	public void setbSelectImage(int bSelectImage) {
		this.bSelectImage = bSelectImage;
	}

	public int getbRoomCat() {
		return bRoomCat;
	}

	public void setbRoomCat(int bRoomCat) {
		this.bRoomCat = bRoomCat;
	}

	public int getbPriceList() {
		return bPriceList;
	}

	public void setbPriceList(int bPriceList) {
		this.bPriceList = bPriceList;
	}
}
