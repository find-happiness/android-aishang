package com.aishang.app.data.model;
/**
 * /mobile/hotel/hotelPriceList.ashx  
 * version 1
 * @author wang
 *
 */
public class JHotelPriceListParam {
	// {filterType:int, filterWords:string, recStart:int, recCount:int,
	// fHotelID:int, fSortByID:int,
	// fMoveInDate:string(YYYY-MM-DD), fMoveOutDate:string(YYYY-MM-DD),
	// fZoneID:int, fTouristID:int, fPriceCatID:int, fPriceMin:decimal,
	// fPriceMax:decimal,
	// fRoomTypeID:int, fServiceIDs:string, fLandScapeIDs:string, fTagIDs:string
	// }

	private int filterType;
	private String filterWords;
	private int recStart;
	private int recCount;
	private int fHotelID;
	private int fSortByID;
	private String fMoveInDate;
	private String fMoveOutDate;
	private int fZoneID;
	private int fTouristID;
	private int fPriceCatID;
	private float fPriceMin;
	private float fPriceMax;
	private int fRoomTypeID;
	private String fServiceIDs;
	private String fLandScapeIDs;
	private String fTagIDs;

	public int getFilterType() {
		return filterType;
	}

	public void setFilterType(int filterType) {
		this.filterType = filterType;
	}

	public String getFilterWords() {
		return filterWords;
	}

	public void setFilterWords(String filterWords) {
		this.filterWords = filterWords;
	}

	public int getRecStart() {
		return recStart;
	}

	public void setRecStart(int recStart) {
		this.recStart = recStart;
	}

	public int getRecCount() {
		return recCount;
	}

	public void setRecCount(int recCount) {
		this.recCount = recCount;
	}

	public int getfHotelID() {
		return fHotelID;
	}

	public void setfHotelID(int fHotelID) {
		this.fHotelID = fHotelID;
	}

	public int getfSortByID() {
		return fSortByID;
	}

	public void setfSortByID(int fSortByID) {
		this.fSortByID = fSortByID;
	}

	public String getfMoveInDate() {
		return fMoveInDate;
	}

	public void setfMoveInDate(String fMoveInDate) {
		this.fMoveInDate = fMoveInDate;
	}

	public String getfMoveOutDate() {
		return fMoveOutDate;
	}

	public void setfMoveOutDate(String fMoveOutDate) {
		this.fMoveOutDate = fMoveOutDate;
	}

	public int getfZoneID() {
		return fZoneID;
	}

	public void setfZoneID(int fZoneID) {
		this.fZoneID = fZoneID;
	}

	public int getfTouristID() {
		return fTouristID;
	}

	public void setfTouristID(int fTouristID) {
		this.fTouristID = fTouristID;
	}

	public int getfPriceCatID() {
		return fPriceCatID;
	}

	public void setfPriceCatID(int fPriceCatID) {
		this.fPriceCatID = fPriceCatID;
	}

	public float getfPriceMin() {
		return fPriceMin;
	}

	public void setfPriceMin(float fPriceMin) {
		this.fPriceMin = fPriceMin;
	}

	public float getfPriceMax() {
		return fPriceMax;
	}

	public void setfPriceMax(float fPriceMax) {
		this.fPriceMax = fPriceMax;
	}

	public int getfRoomTypeID() {
		return fRoomTypeID;
	}

	public void setfRoomTypeID(int fRoomTypeID) {
		this.fRoomTypeID = fRoomTypeID;
	}

	public String getfServiceIDs() {
		return fServiceIDs;
	}

	public void setfServiceIDs(String fServiceIDs) {
		this.fServiceIDs = fServiceIDs;
	}

	public String getfLandScapeIDs() {
		return fLandScapeIDs;
	}

	public void setfLandScapeIDs(String fLandScapeIDs) {
		this.fLandScapeIDs = fLandScapeIDs;
	}

	public String getfTagIDs() {
		return fTagIDs;
	}

	public void setfTagIDs(String fTagIDs) {
		this.fTagIDs = fTagIDs;
	}

}
