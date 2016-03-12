package com.aishang.app.data.model;
/**
 * 
 * /mobile/hotel/hotelList.ashx  
 * Version: 1
 * @author wang
 *
 */
public class JHotelListResult {

	/**
	 * result: string, totalCount:int, hotelList:[ {hotelID:int, name:string,
	 * imageUrl:string, priceText:string, starLevel:string,
	 * promotionText:string, tags:string, tagIDs:string, moreDetail:
	 * {mTouristID:int, mTouristName:string, fax:string, phone:string,
	 * openDateText:string, decorationDateText: string, floors:string,
	 * propertyMgr:string, loupanID:int, Lat:decimal, Lng:decimal,
	 * imageCount:int} },бн]
	 */

	private String result;
	private int totalCount;
	private Hotel[] hotelList;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public Hotel[] getHotelList() {
		return hotelList;
	}

	public void setHotelList(Hotel[] hotelList) {
		this.hotelList = hotelList;
	}

	public class Hotel {

		private int hotelID;
		private String name;
		private String imageUrl;
		private String priceText;
		private String starLevel;
		private String promotionText;
		private String tags;
		private String tagIDs;
		private int maxRooms;
		private String roomDepict;
		private String propertyCat;
		private MoreDetail moreDetail;
		private String address;

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getRoomDepict() {
			return roomDepict;
		}

		public void setRoomDepict(String roomDepict) {
			this.roomDepict = roomDepict;
		}

		public String getPropertyCat() {
			return propertyCat;
		}

		public void setPropertyCat(String propertyCat) {
			this.propertyCat = propertyCat;
		}

		public int getMaxRooms() {
			return maxRooms;
		}

		public void setMaxRooms(int maxRooms) {
			this.maxRooms = maxRooms;
		}

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

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public String getPriceText() {
			return priceText;
		}

		public void setPriceText(String priceText) {
			this.priceText = priceText;
		}

		public String getStarLevel() {
			return starLevel;
		}

		public void setStarLevel(String starLevel) {
			this.starLevel = starLevel;
		}

		public String getPromotionText() {
			return promotionText;
		}

		public void setPromotionText(String promotionText) {
			this.promotionText = promotionText;
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

		public MoreDetail getMoreDetail() {
			return moreDetail;
		}

		public void setMoreDetail(MoreDetail moreDetail) {
			this.moreDetail = moreDetail;
		}

		public class MoreDetail {
			private int mTouristID;
			private String mTouristName;
			private String fax;
			private String phone;
			private String openDateText;
			private String decorationDateText;
			private String floors;
			private String propertyMgr;
			private int loupanID;
			private float Lat;
			private float Lng;
			private int imageCount;

			public int getmTouristID() {
				return mTouristID;
			}

			public void setmTouristID(int mTouristID) {
				this.mTouristID = mTouristID;
			}

			public String getmTouristName() {
				return mTouristName;
			}

			public void setmTouristName(String mTouristName) {
				this.mTouristName = mTouristName;
			}

			public String getFax() {
				return fax;
			}

			public void setFax(String fax) {
				this.fax = fax;
			}

			public String getPhone() {
				return phone;
			}

			public void setPhone(String phone) {
				this.phone = phone;
			}

			public String getOpenDateText() {
				return openDateText;
			}

			public void setOpenDateText(String openDateText) {
				this.openDateText = openDateText;
			}

			public String getDecorationDateText() {
				return decorationDateText;
			}

			public void setDecorationDateText(String decorationDateText) {
				this.decorationDateText = decorationDateText;
			}

			public String getFloors() {
				return floors;
			}

			public void setFloors(String floors) {
				this.floors = floors;
			}

			public String getPropertyMgr() {
				return propertyMgr;
			}

			public void setPropertyMgr(String propertyMgr) {
				this.propertyMgr = propertyMgr;
			}

			public int getLoupanID() {
				return loupanID;
			}

			public void setLoupanID(int loupanID) {
				this.loupanID = loupanID;
			}

			public float getLat() {
				return Lat;
			}

			public void setLat(float lat) {
				Lat = lat;
			}

			public float getLng() {
				return Lng;
			}

			public void setLng(float lng) {
				Lng = lng;
			}

			public int getImageCount() {
				return imageCount;
			}

			public void setImageCount(int imageCount) {
				this.imageCount = imageCount;
			}

		}

	}
}
