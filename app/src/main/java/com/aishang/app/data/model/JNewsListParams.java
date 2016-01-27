package com.aishang.app.data.model;

/**
 * /mobile/NewsList.ashx version :V2
 * 
 * @author wang
 * 
 */
public class JNewsListParams {

	// {zoneID:int, catID:int, recStart:int, recCount:int, filterTypeID:int,
	// beVIPHome:int, tagIDs:string, fKeyWords:string, fOrderType:int}

	private int zoneID;
	private int catID;
	private int recStart;
	private int recCount;
	private int filterTypeID;
	private int beVIPHome;
	private String tagIDs;
	private String fKeyWords;
	private int fOrderType;
	
	public int getBeVIPHome() {
		return beVIPHome;
	}

	public void setBeVIPHome(int beVIPHome) {
		this.beVIPHome = beVIPHome;
	}

	public String getTagIDs() {
		return tagIDs;
	}

	public void setTagIDs(String tagIDs) {
		this.tagIDs = tagIDs;
	}

	public String getfKeyWords() {
		return fKeyWords;
	}

	public void setfKeyWords(String fKeyWords) {
		this.fKeyWords = fKeyWords;
	}

	public int getfOrderType() {
		return fOrderType;
	}

	public void setfOrderType(int fOrderType) {
		this.fOrderType = fOrderType;
	}

	public int getZoneID() {
		return zoneID;
	}

	public void setZoneID(int zoneID) {
		this.zoneID = zoneID;
	}

	public int getCatID() {
		return catID;
	}

	public void setCatID(int catID) {
		this.catID = catID;
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

	public int getFilterTypeID() {
		return filterTypeID;
	}

	public void setFilterTypeID(int filterTypeID) {
		this.filterTypeID = filterTypeID;
	}

}
