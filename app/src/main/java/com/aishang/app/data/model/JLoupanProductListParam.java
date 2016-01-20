package com.aishang.app.data.model;
/**
 * /mobile/loupan/loupanProductList.ashx  
 * version:1
 * @author wang
 *
 */
public class JLoupanProductListParam {
	// {userZoneIDLevel2:int, userLat:float, userLng:float,
	// recStart:int, recCount:int, fLoupanID:int,
	// filterTypeID:int, filterWords:string, fOrderBy:int,
	// fOpenDate:string(YYYY-MM-DD), fMoveInDate:string(YYYY-MM-DD)
	// fZoneID:int, fTouristAreaID:int,
	// fPriceCatID:int, fPriceMin:decimal, fPriceMax:decimal,
	// fRoomTypeID:int, fServiceCatIDs:string, fLandScapeIDs:string,
	// fTagIDs:string,
	// beVIPHome:int
	// }

	private int userZoneIDLevel2;
	private float userLat;
	private float userLng;
	private int recStart;
	private int recCount;
	private int fLoupanID;
	private int filterTypeID;
	private String filterWords;
	private int fOrderBy;
	private String fOpenDate;
	private String fMoveInDate;
	private int fZoneID;
	private int fTouristAreaID;
	private int fPriceCatID;
	private float fPriceMin;
	private float fPriceMax;
	private int fRoomTypeID;
	private String fServiceCatIDs;
	private String fLandScapeIDs;
	private String fTagIDs;
	private int beVIPHome;

	

	public int getUserZoneIDLevel2() {
		return userZoneIDLevel2;
	}

	public void setUserZoneIDLevel2(int userZoneIDLevel2) {
		this.userZoneIDLevel2 = userZoneIDLevel2;
	}

	public float getUserLat() {
		return userLat;
	}

	public void setUserLat(float userLat) {
		this.userLat = userLat;
	}

	public float getUserLng() {
		return userLng;
	}

	public void setUserLng(float userLng) {
		this.userLng = userLng;
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

	public int getfLoupanID() {
		return fLoupanID;
	}

	public void setfLoupanID(int fLoupanID) {
		this.fLoupanID = fLoupanID;
	}

	public int getFilterTypeID() {
		return filterTypeID;
	}

	public void setFilterTypeID(int filterTypeID) {
		this.filterTypeID = filterTypeID;
	}

	public String getFilterWords() {
		return filterWords;
	}

	public void setFilterWords(String filterWords) {
		this.filterWords = filterWords;
	}

	public int getfOrderBy() {
		return fOrderBy;
	}

	public void setfOrderBy(int fOrderBy) {
		this.fOrderBy = fOrderBy;
	}

	public String getfOpenDate() {
		return fOpenDate;
	}

	public void setfOpenDate(String fOpenDate) {
		this.fOpenDate = fOpenDate;
	}

	public String getfMoveInDate() {
		return fMoveInDate;
	}

	public void setfMoveInDate(String fMoveInDate) {
		this.fMoveInDate = fMoveInDate;
	}

	public int getfZoneID() {
		return fZoneID;
	}

	public void setfZoneID(int fZoneID) {
		this.fZoneID = fZoneID;
	}

	public int getfTouristAreaID() {
		return fTouristAreaID;
	}

	public void setfTouristAreaID(int fTouristAreaID) {
		this.fTouristAreaID = fTouristAreaID;
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

	public String getfServiceCatIDs() {
		return fServiceCatIDs;
	}

	public void setfServiceCatIDs(String fServiceCatIDs) {
		this.fServiceCatIDs = fServiceCatIDs;
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

	public int getBeVIPHome() {
		return beVIPHome;
	}

	public void setBeVIPHome(int beVIPHome) {
		this.beVIPHome = beVIPHome;
	}

}
