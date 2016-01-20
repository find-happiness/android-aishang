package com.aishang.app.data.model;
/**
 * /mobile/hotel/hotelPriceList.ashx  
 * version: 1
 * @author wang
 *
 */
public class JHotelRoomPriceCalculateResult {
	// {result: string,
	// startDate:string, endDate:string,
	// lowestCredit:int, lowestCash:float,
	// totalDays:int, totalCredit:int, totalCash:float
	// }

	private String result;
	private String startDate;
	private String endDate;
	private int lowestCredit;
	private float lowestCash;
	private int totalDays;
	private int totalCredit;
	private float totalCash;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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

	public int getLowestCredit() {
		return lowestCredit;
	}

	public void setLowestCredit(int lowestCredit) {
		this.lowestCredit = lowestCredit;
	}

	public float getLowestCash() {
		return lowestCash;
	}

	public void setLowestCash(float lowestCash) {
		this.lowestCash = lowestCash;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public int getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(int totalCredit) {
		this.totalCredit = totalCredit;
	}

	public float getTotalCash() {
		return totalCash;
	}

	public void setTotalCash(float totalCash) {
		this.totalCash = totalCash;
	}

}
