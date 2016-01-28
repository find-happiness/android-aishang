package com.aishang.app.data.model;

/**
 * ajax loupan zone result
 * 
 * version = 1;
 * @author wang
 *
 */
public class JSysZoneResult {

	// {
	// "result": "success",
	// "lastUpdate": "2013-11-12 11:15:31",
	// "zoneID": 2,
	// "name": "中国",
	// "zoneList": [
	// {
	// "zoneID": 20,
	// "name": "山东省",
	// "level": 2,
	// "centerLat": 36.66853000000000,
	// "centerLng": 117.02035899999998
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
