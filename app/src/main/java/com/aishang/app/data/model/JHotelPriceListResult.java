package com.aishang.app.data.model;

/**
 * 
 * /mobile/hotel/hotelPriceList.ashx  
 * version 1
 * @author wang
 *
 */
public class JHotelPriceListResult {

	// {result: string,
	//
	// hotelList:[ {hotelID:int, name:string, address:string, phone:string,
	// fax:string,
	// imageUrl:string, Lng:decimal, Lng:decimal, tags:string, tagIDs:string,
	// starLevel:string, propertyMgr:string,
	// facilitieList:[{imageUrl:string, name:string, enable:int}, бн],
	// serviceList:[{imageUrl:string, name:string, enable:int},бн] }
	// , бн],
	//
	// totalPriceCount:int,
	// roomPriceList[
	// { priceID:int, title:string, hotelID:int, promotion:string, desc:string,
	// areaText:string, floors:string, maxGuest:int, basePrice:decimal,
	// roomType:string, bedType:string, bedCount:int,
	// validateStart:string, validateEnd:string, orderCount:int, leftCount:int,
	// priceCash:decimal, priceCredit:int,
	// holidayPriceCash:decimal, holidayPriceCredit:int,
	// facliliteList :[{imageUrl:string, name: string}, бн],
	// serviceList:[{imageUrl:string name:string},бн] }
	// ]
	// }

	private String result;
	private int totalPriceCount;
	private Hotel[] hotelList;
	private RoomPrice[] roomPriceList;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getTotalPriceCount() {
		return totalPriceCount;
	}

	public void setTotalPriceCount(int totalPriceCount) {
		this.totalPriceCount = totalPriceCount;
	}

	public Hotel[] getHotelList() {
		return hotelList;
	}

	public void setHotelList(Hotel[] hotelList) {
		this.hotelList = hotelList;
	}

	public RoomPrice[] getRoomPriceList() {
		return roomPriceList;
	}

	public void setRoomPriceList(RoomPrice[] roomPriceList) {
		this.roomPriceList = roomPriceList;
	}

	public class RoomPrice {
		private int priceID;
		private String title;
		private int hotelID;
		private String promotion;
		private String desc;
		private String areaText;
		private String floors;
		private int maxGuest;
		private float basePrice;
		private String roomType;
		private String bedType;
		private int bedCount;
		private String validateStart;
		private String validateEnd;
		private int orderCount;
		private int leftCount;
		private float priceCash;
		private int priceCredit;
		private float holidayPriceCash;
		private int holidayPriceCredit;
		private Hotel.Facilitie[] facliliteList;
		private Hotel.Service[] serviceList;

		public int getPriceID() {
			return priceID;
		}

		public void setPriceID(int priceID) {
			this.priceID = priceID;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public int getHotelID() {
			return hotelID;
		}

		public void setHotelID(int hotelID) {
			this.hotelID = hotelID;
		}

		public String getPromotion() {
			return promotion;
		}

		public void setPromotion(String promotion) {
			this.promotion = promotion;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public String getAreaText() {
			return areaText;
		}

		public void setAreaText(String areaText) {
			this.areaText = areaText;
		}

		public String getFloors() {
			return floors;
		}

		public void setFloors(String floors) {
			this.floors = floors;
		}

		public int getMaxGuest() {
			return maxGuest;
		}

		public void setMaxGuest(int maxGuest) {
			this.maxGuest = maxGuest;
		}

		public float getBasePrice() {
			return basePrice;
		}

		public void setBasePrice(float basePrice) {
			this.basePrice = basePrice;
		}

		public String getRoomType() {
			return roomType;
		}

		public void setRoomType(String roomType) {
			this.roomType = roomType;
		}

		public String getBedType() {
			return bedType;
		}

		public void setBedType(String bedType) {
			this.bedType = bedType;
		}

		public int getBedCount() {
			return bedCount;
		}

		public void setBedCount(int bedCount) {
			this.bedCount = bedCount;
		}

		public String getValidateStart() {
			return validateStart;
		}

		public void setValidateStart(String validateStart) {
			this.validateStart = validateStart;
		}

		public String getValidateEnd() {
			return validateEnd;
		}

		public void setValidateEnd(String validateEnd) {
			this.validateEnd = validateEnd;
		}

		public int getOrderCount() {
			return orderCount;
		}

		public void setOrderCount(int orderCount) {
			this.orderCount = orderCount;
		}

		public int getLeftCount() {
			return leftCount;
		}

		public void setLeftCount(int leftCount) {
			this.leftCount = leftCount;
		}

		public float getPriceCash() {
			return priceCash;
		}

		public void setPriceCash(float priceCash) {
			this.priceCash = priceCash;
		}

		public int getPriceCredit() {
			return priceCredit;
		}

		public void setPriceCredit(int priceCredit) {
			this.priceCredit = priceCredit;
		}

		public float getHolidayPriceCash() {
			return holidayPriceCash;
		}

		public void setHolidayPriceCash(float holidayPriceCash) {
			this.holidayPriceCash = holidayPriceCash;
		}

		public int getHolidayPriceCredit() {
			return holidayPriceCredit;
		}

		public void setHolidayPriceCredit(int holidayPriceCredit) {
			this.holidayPriceCredit = holidayPriceCredit;
		}

		public Hotel.Facilitie[] getFacliliteList() {
			return facliliteList;
		}

		public void setFacliliteList(Hotel.Facilitie[] facliliteList) {
			this.facliliteList = facliliteList;
		}

		public Hotel.Service[] getServiceList() {
			return serviceList;
		}

		public void setServiceList(Hotel.Service[] serviceList) {
			this.serviceList = serviceList;
		}

	}

	public class Hotel {
		private int hotelID;
		private String name;
		private String address;
		private String phone;
		private String fax;
		private String imageUrl;
		private float Lng;
		private float Lat;
		private String tags;
		private String tagIDs;
		private String starLevel;
		private String propertyMgr;
		private Facilitie[] facilitieList;
		private Service[] serviceList;

		public int getHotelID() {
			return hotelID;
		}

		public void setHotelID(int hotelID) {
			this.hotelID = hotelID;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getFax() {
			return fax;
		}

		public void setFax(String fax) {
			this.fax = fax;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public float getLng() {
			return Lng;
		}

		public void setLng(float lng) {
			Lng = lng;
		}

		public float getLat() {
			return Lat;
		}

		public void setLat(float lat) {
			Lat = lat;
		}

		public String getTags() {
			return tags;
		}

		public void setTags(String tags) {
			this.tags = tags;
		}

		public String getTagIDs() {
			return tagIDs;
		}

		public void setTagIDs(String tagIDs) {
			this.tagIDs = tagIDs;
		}

		public String getStarLevel() {
			return starLevel;
		}

		public void setStarLevel(String starLevel) {
			this.starLevel = starLevel;
		}

		public String getPropertyMgr() {
			return propertyMgr;
		}

		public void setPropertyMgr(String propertyMgr) {
			this.propertyMgr = propertyMgr;
		}

		public Facilitie[] getFacilitieList() {
			return facilitieList;
		}

		public void setFacilitieList(Facilitie[] facilitieList) {
			this.facilitieList = facilitieList;
		}

		public Service[] getServiceList() {
			return serviceList;
		}

		public void setServiceList(Service[] serviceList) {
			this.serviceList = serviceList;
		}

		public class Facilitie {
			private String imageUrl;
			private String name;
			private int enable;

			public String getImageUrl() {
				return imageUrl;
			}

			public void setImageUrl(String imageUrl) {
				this.imageUrl = imageUrl;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public int getEnable() {
				return enable;
			}

			public void setEnable(int enable) {
				this.enable = enable;
			}

		}

		public class Service {
			private String imageUrl;
			private String name;
			private int enable;

			public String getImageUrl() {
				return imageUrl;
			}

			public void setImageUrl(String imageUrl) {
				this.imageUrl = imageUrl;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public int getEnable() {
				return enable;
			}

			public void setEnable(int enable) {
				this.enable = enable;
			}

		}
	}

}
