package com.aishang.app.data.model;

/**
 * /** /mobile/hotel/hotelDetail.ashx version: 1
 * 
 * @author wang
 * 
 */
public class JHotelDetailResult {
	private String result;
	private Data dataSet;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Data getDataSet() {
		return dataSet;
	}

	public void setDataSet(Data dataSet) {
		this.dataSet = dataSet;
	}

	public class Data {

		private BaseInfo baseInfo;
		private ImageCat[] imageCatList;
		private SelectImage[] selectImageList;
		private RoomCat[] roomCatList;

		public BaseInfo getBaseInfo() {
			return baseInfo;
		}

		public void setBaseInfo(BaseInfo baseInfo) {
			this.baseInfo = baseInfo;
		}

		public class BaseInfo {

			private int hotelID;
			private String name;
			private String imageUrl;
			private int mTouristID;
			private String mTouristName;
			private String fax;
			private String phone;
			private String starLevel;
			private String openDateText;
			private String decorationDateText;
			private String floors;
			private String propertyMgr;
			private String tags;
			private String tagIDs;
			private int loupanID;
			private float Lat;
			private float Lng;
			private String description;
			private String special;
			private String rules;
			private String transportation;
			private int imageTotal;
			private String promotionText;
			private String address;
			private String ctripUrl;

			public String getCtripUrl() {
				return ctripUrl;
			}

			public void setCtripUrl(String ctripUrl) {
				this.ctripUrl = ctripUrl;
			}

			private Faclilite[] facliliteList;
			private Service[] serviceList;

			public Faclilite[] getFacliliteList() {
				return facliliteList;
			}

			public void setFacliliteList(Faclilite[] facliliteList) {
				this.facliliteList = facliliteList;
			}

			public Service[] getServiceList() {
				return serviceList;
			}

			public void setServiceList(Service[] serviceList) {
				this.serviceList = serviceList;
			}

			public String getAddress() {
				return address;
			}

			public void setAddress(String address) {
				this.address = address;
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

			public String getStarLevel() {
				return starLevel;
			}

			public void setStarLevel(String starLevel) {
				this.starLevel = starLevel;
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

			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public String getSpecial() {
				return special;
			}

			public void setSpecial(String special) {
				this.special = special;
			}

			public String getRules() {
				return rules;
			}

			public void setRules(String rules) {
				this.rules = rules;
			}

			public String getTransportation() {
				return transportation;
			}

			public void setTransportation(String transportation) {
				this.transportation = transportation;
			}

			public int getImageTotal() {
				return imageTotal;
			}

			public void setImageTotal(int imageTotal) {
				this.imageTotal = imageTotal;
			}

			public String getPromotionText() {
				return promotionText;
			}

			public void setPromotionText(String promotionText) {
				this.promotionText = promotionText;
			}

			public class Faclilite {

				// imageUrl:string, name: string, enable:int

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

		public SelectImage[] getSelectImageList() {
			return selectImageList;
		}

		public void setSelectImageList(SelectImage[] selectImageList) {
			this.selectImageList = selectImageList;
		}

		public RoomCat[] getRoomCatList() {
			return roomCatList;
		}

		public void setRoomCatList(RoomCat[] roomCatList) {
			this.roomCatList = roomCatList;
		}

		public class RoomCat {
			private int id;
			private String name;
			private String basicPrice;
			private String roomType;
			private String areaText;
			private String promotion;
			private String desc;
			private String floors;
			private int maxGuest;
			private float basePrice;
			private int priceCount;
			private Price[] priceList;

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getBasicPrice() {
				return basicPrice;
			}

			public void setBasicPrice(String basicPrice) {
				this.basicPrice = basicPrice;
			}

			public String getRoomType() {
				return roomType;
			}

			public void setRoomType(String roomType) {
				this.roomType = roomType;
			}

			public String getAreaText() {
				return areaText;
			}

			public void setAreaText(String areaText) {
				this.areaText = areaText;
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

			public int getPriceCount() {
				return priceCount;
			}

			public void setPriceCount(int priceCount) {
				this.priceCount = priceCount;
			}

			public Price[] getPriceList() {
				return priceList;
			}

			public void setPriceList(Price[] priceList) {
				this.priceList = priceList;
			}

			public class Price {

				/**
				 * "priceID": 11, "title": "大别墅", "desc": "暂无",
				 * "priceCashStart": 0.0, "priceCreditStart": 0,
				 * "holidayPriceCashStart": 0.0, "holidayPriceCreditStart": 0,
				 * "lastDate": "",
				 */
				private int priceID;
				private String title;
				private String desc;
				private float priceCashStart;
				private int priceCreditStart;
				private float holidayPriceCashStart;
				private int holidayPriceCreditStart;
				private String lastDate;

				private DayPrice[] dayPriceList;

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

				public String getDesc() {
					return desc;
				}

				public void setDesc(String desc) {
					this.desc = desc;
				}

				public float getPriceCashStart() {
					return priceCashStart;
				}

				public void setPriceCashStart(float priceCashStart) {
					this.priceCashStart = priceCashStart;
				}

				public int getPriceCreditStart() {
					return priceCreditStart;
				}

				public void setPriceCreditStart(int priceCreditStart) {
					this.priceCreditStart = priceCreditStart;
				}

				public float getHolidayPriceCashStart() {
					return holidayPriceCashStart;
				}

				public void setHolidayPriceCashStart(float holidayPriceCashStart) {
					this.holidayPriceCashStart = holidayPriceCashStart;
				}

				public int getHolidayPriceCreditStart() {
					return holidayPriceCreditStart;
				}

				public void setHolidayPriceCreditStart(
						int holidayPriceCreditStart) {
					this.holidayPriceCreditStart = holidayPriceCreditStart;
				}

				public String getLastDate() {
					return lastDate;
				}

				public void setLastDate(String lastDate) {
					this.lastDate = lastDate;
				}

				public DayPrice[] getDayPriceList() {
					return dayPriceList;
				}

				public void setDayPriceList(DayPrice[] dayPriceList) {
					this.dayPriceList = dayPriceList;
				}

				public class DayPrice {

					// "day": "2013-10-11",
					// "priceCash": 1.00,
					// "priceCredit": 1.0

					private String day;
					private float priceCash;
					private float priceCredit;

					public String getDay() {
						return day;
					}

					public void setDay(String day) {
						this.day = day;
					}

					public float getPriceCash() {
						return priceCash;
					}

					public void setPriceCash(float priceCash) {
						this.priceCash = priceCash;
					}

					public float getPriceCredit() {
						return priceCredit;
					}

					public void setPriceCredit(float priceCredit) {
						this.priceCredit = priceCredit;
					}

				}

			}
		}

		public class SelectImage {
			private String url;
			private String title;
			private int pos;

			public String getUrl() {
				return url;
			}

			public void setUrl(String url) {
				this.url = url;
			}

			public String getTitle() {
				return title;
			}

			public void setTitle(String title) {
				this.title = title;
			}

			public int getPos() {
				return pos;
			}

			public void setPos(int pos) {
				this.pos = pos;
			}
		}

		public ImageCat[] getImageCatList() {
			return imageCatList;
		}

		public void setImageCatList(ImageCat[] imageCatList) {
			this.imageCatList = imageCatList;
		}

		public class ImageCat {
			private int catID;
			private String name;
			private int startPos;

			public int getCatID() {
				return catID;
			}

			public void setCatID(int catID) {
				this.catID = catID;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public int getStartPos() {
				return startPos;
			}

			public void setStartPos(int startPos) {
				this.startPos = startPos;
			}

			private Image[] imageList;

			public Image[] getImageList() {
				return imageList;
			}

			public void setImageList(Image[] imageList) {
				this.imageList = imageList;
			}

			public class Image {
				// idx:int, priceID, catID:int ,url:string, title:string
				private int idx;
				private int priceID;
				private int catID;
				private String url;
				private String title;

				public int getIdx() {
					return idx;
				}

				public void setIdx(int idx) {
					this.idx = idx;
				}

				public int getPriceID() {
					return priceID;
				}

				public void setPriceID(int priceID) {
					this.priceID = priceID;
				}

				public int getCatID() {
					return catID;
				}

				public void setCatID(int catID) {
					this.catID = catID;
				}

				public String getUrl() {
					return url;
				}

				public void setUrl(String url) {
					this.url = url;
				}

				public String getTitle() {
					return title;
				}

				public void setTitle(String title) {
					this.title = title;
				}
			}
		}

	}
}
