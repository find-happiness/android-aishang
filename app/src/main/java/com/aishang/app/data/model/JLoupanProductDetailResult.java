package com.aishang.app.data.model;

/**
 * /mobile/loupan/loupanProductDetail.ashx version:1
 * 
 * @author wang
 * 
 */
public class JLoupanProductDetailResult {
	// {result: string,
	// dataSet:
	// { loupanProductID:int, title:string , imageUrl:string,
	// description:string, inSale:int, hotSale:int, priceText:string,
	// price:decimal, promotion:string, saleCount:int, openDate:string,
	// moveInDate:string, tags:string, tagIDs:string, areaText:string,
	//
	// loupanData:{ loupanID:int, name:string, imageUrl:string,
	// distance:string, subZoneName:string,
	// priceText:string, promotion:string, Lat:decimal, Lng:decimal,
	// tags:string, tagIDs:string, address:string, developers:string,
	// openDate:string, moveInDate:string,
	// projectIntro:string, transportation:string, environment:string,
	// usageRatio:string, grassLand:string, totalApart:int,
	// landArea:string, buildingArea:string, parkingSpace:string,
	// propertyCost:string, propertyCompany:string, salePermit:string}
	//
	// mTouristAreaID:int, mTouristAreaName:string, mToursistDesc:string,
	//
	// touristList:[{id:int, name:string, subTitle:string,
	// imageUrl:string, Lat:decimal, Lng:decimal, }, бн],
	//
	// imageList:[ { title:string, thumbUrl:string, url:string, pos:int }, бн]
	// }
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

	public class Image {
		private String title;
		private String thumbUrl;
		private String url;
		private int pos;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getThumbUrl() {
			return thumbUrl;
		}

		public void setThumbUrl(String thumbUrl) {
			this.thumbUrl = thumbUrl;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public int getPos() {
			return pos;
		}

		public void setPos(int pos) {
			this.pos = pos;
		}

	}

	public class Tourist {
		private int id;
		private String name;
		private String subTitle;
		private String imageUrl;
		private float Lat;
		private float Lng;

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

		public String getSubTitle() {
			return subTitle;
		}

		public void setSubTitle(String subTitle) {
			this.subTitle = subTitle;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
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

	}

	public class Data {
		private int loupanProductID;
		private String title;
		private String imageUrl;
		private String description;
		private int inSale;
		private int hotSale;
		private String priceText;
		private float price;
		private String promotion;
		private int saleCount;
		private String openDate;
		private String moveInDate;
		private String tags;
		private String tagIDs;
		private String areaText;
		private int mTouristAreaID;
		private String mTouristAreaName;
		private String mToursistDesc;

		private LoupanData loupanData;
		private Tourist[] touristList;
		private Image[] imageList;

		public Tourist[] getTouristList() {
			return touristList;
		}

		public void setTouristList(Tourist[] touristList) {
			this.touristList = touristList;
		}

		public Image[] getImageList() {
			return imageList;
		}

		public void setImageList(Image[] imageList) {
			this.imageList = imageList;
		}

		public int getLoupanProductID() {
			return loupanProductID;
		}

		public void setLoupanProductID(int loupanProductID) {
			this.loupanProductID = loupanProductID;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public int getInSale() {
			return inSale;
		}

		public void setInSale(int inSale) {
			this.inSale = inSale;
		}

		public int getHotSale() {
			return hotSale;
		}

		public void setHotSale(int hotSale) {
			this.hotSale = hotSale;
		}

		public String getPriceText() {
			return priceText;
		}

		public void setPriceText(String priceText) {
			this.priceText = priceText;
		}

		public float getPrice() {
			return price;
		}

		public void setPrice(float price) {
			this.price = price;
		}

		public String getPromotion() {
			return promotion;
		}

		public void setPromotion(String promotion) {
			this.promotion = promotion;
		}

		public int getSaleCount() {
			return saleCount;
		}

		public void setSaleCount(int saleCount) {
			this.saleCount = saleCount;
		}

		public String getOpenDate() {
			return openDate;
		}

		public void setOpenDate(String openDate) {
			this.openDate = openDate;
		}

		public String getMoveInDate() {
			return moveInDate;
		}

		public void setMoveInDate(String moveInDate) {
			this.moveInDate = moveInDate;
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

		public String getAreaText() {
			return areaText;
		}

		public void setAreaText(String areaText) {
			this.areaText = areaText;
		}

		public int getmTouristAreaID() {
			return mTouristAreaID;
		}

		public void setmTouristAreaID(int mTouristAreaID) {
			this.mTouristAreaID = mTouristAreaID;
		}

		public String getmTouristAreaName() {
			return mTouristAreaName;
		}

		public void setmTouristAreaName(String mTouristAreaName) {
			this.mTouristAreaName = mTouristAreaName;
		}

		public String getmToursistDesc() {
			return mToursistDesc;
		}

		public void setmToursistDesc(String mToursistDesc) {
			this.mToursistDesc = mToursistDesc;
		}

		public LoupanData getLoupanData() {
			return loupanData;
		}

		public void setLoupanData(LoupanData loupanData) {
			this.loupanData = loupanData;
		}

		public class LoupanData {
			private int loupanID;
			private String name;
			private String imageUrl;
			private String distance;
			private String subZoneName;
			private String priceText;
			private String promotion;
			private float Lat;
			private float Lng;
			private String tags;
			private String tagIDs;
			private String address;
			private String developers;
			private String openDate;
			private String moveInDate;
			private String projectIntro;
			private String transportation;
			private String environment;
			private String usageRatio;
			private String grassLand;
			private int totalApart;
			private String landArea;
			private String buildingArea;
			private String parkingSpace;
			private String propertyCost;
			private String propertyCompany;
			private String salePermit;
			private String mapStaticImg;

			public String getMapStaticImg() {
				return mapStaticImg;
			}

			public void setMapStaticImg(String mapStaticImg) {
				this.mapStaticImg = mapStaticImg;
			}

			public int getLoupanID() {
				return loupanID;
			}

			public void setLoupanID(int loupanID) {
				this.loupanID = loupanID;
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

			public String getDistance() {
				return distance;
			}

			public void setDistance(String distance) {
				this.distance = distance;
			}

			public String getSubZoneName() {
				return subZoneName;
			}

			public void setSubZoneName(String subZoneName) {
				this.subZoneName = subZoneName;
			}

			public String getPriceText() {
				return priceText;
			}

			public void setPriceText(String priceText) {
				this.priceText = priceText;
			}

			public String getPromotion() {
				return promotion;
			}

			public void setPromotion(String promotion) {
				this.promotion = promotion;
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

			public String getAddress() {
				return address;
			}

			public void setAddress(String address) {
				this.address = address;
			}

			public String getDevelopers() {
				return developers;
			}

			public void setDevelopers(String developers) {
				this.developers = developers;
			}

			public String getOpenDate() {
				return openDate;
			}

			public void setOpenDate(String openDate) {
				this.openDate = openDate;
			}

			public String getMoveInDate() {
				return moveInDate;
			}

			public void setMoveInDate(String moveInDate) {
				this.moveInDate = moveInDate;
			}

			public String getProjectIntro() {
				return projectIntro;
			}

			public void setProjectIntro(String projectIntro) {
				this.projectIntro = projectIntro;
			}

			public String getTransportation() {
				return transportation;
			}

			public void setTransportation(String transportation) {
				this.transportation = transportation;
			}

			public String getEnvironment() {
				return environment;
			}

			public void setEnvironment(String environment) {
				this.environment = environment;
			}

			public String getUsageRatio() {
				return usageRatio;
			}

			public void setUsageRatio(String usageRatio) {
				this.usageRatio = usageRatio;
			}

			public String getGrassLand() {
				return grassLand;
			}

			public void setGrassLand(String grassLand) {
				this.grassLand = grassLand;
			}

			public int getTotalApart() {
				return totalApart;
			}

			public void setTotalApart(int totalApart) {
				this.totalApart = totalApart;
			}

			public String getLandArea() {
				return landArea;
			}

			public void setLandArea(String landArea) {
				this.landArea = landArea;
			}

			public String getBuildingArea() {
				return buildingArea;
			}

			public void setBuildingArea(String buildingArea) {
				this.buildingArea = buildingArea;
			}

			public String getParkingSpace() {
				return parkingSpace;
			}

			public void setParkingSpace(String parkingSpace) {
				this.parkingSpace = parkingSpace;
			}

			public String getPropertyCost() {
				return propertyCost;
			}

			public void setPropertyCost(String propertyCost) {
				this.propertyCost = propertyCost;
			}

			public String getPropertyCompany() {
				return propertyCompany;
			}

			public void setPropertyCompany(String propertyCompany) {
				this.propertyCompany = propertyCompany;
			}

			public String getSalePermit() {
				return salePermit;
			}

			public void setSalePermit(String salePermit) {
				this.salePermit = salePermit;
			}

		}
	}
}
