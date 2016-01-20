package com.aishang.app.data.model;

public class JHotelZoneListResult {
	// {
	// "result": "success",
	// "lastUpdate": "2013-11-10 11:23:17",
	// "zoneID": 2,
	// "name": "ол╣Щ",
	// "zoneList": [
	// {
	// "zoneID": 35,
	// "name": "И╩╦Я╩А",
	// "level": 2,
	// "centerLat": 36.05942100000000,
	// "centerLng": 103.82630799999992
	// }
	// ]
	// }

	private String result;
	private String lastUpdate;
	private int zoneID;
	private String name;
	private Zone[] zoneList;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getZoneID() {
		return zoneID;
	}

	public void setZoneID(int zoneID) {
		this.zoneID = zoneID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Zone[] getZoneList() {
		return zoneList;
	}

	public void setZoneList(Zone[] zoneList) {
		this.zoneList = zoneList;
	}

	public class Zone {

		private int zoneID;
		private String name;
		private int level;
		private float centerLat;
		private float centerLng;

		public int getZoneID() {
			return zoneID;
		}

		public void setZoneID(int zoneID) {
			this.zoneID = zoneID;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public float getCenterLat() {
			return centerLat;
		}

		public void setCenterLat(float centerLat) {
			this.centerLat = centerLat;
		}

		public float getCenterLng() {
			return centerLng;
		}

		public void setCenterLng(float centerLng) {
			this.centerLng = centerLng;
		}

	}
}
