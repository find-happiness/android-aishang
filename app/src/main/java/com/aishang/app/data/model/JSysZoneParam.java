package com.aishang.app.data.model;

/**
 * ajax loupan zone
 * 
 * version =1;
 * 
 * zoneID default 2;
 * 
 * @author wang
 * 
 */
public class JSysZoneParam {

	// {zoneID:2,lastUpdate:''}
	private int zoneID;
	private String lastUpdate;

	public int getZoneID() {
		return zoneID;
	}

	public void setZoneID(int zoneID) {
		this.zoneID = zoneID;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
