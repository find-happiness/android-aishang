package com.aishang.app.data.model;
/**
 * /mobile/loupan/loupanProductList.ashx  
 * version:1
 * @author wang
 *
 */
public class JLoupanProductListResult {
	// {result: string,
	// loupanCount:int
	// productCount:int,
	// recStart:int,
	// loupanList:[{
	// loupanID:int, name:string, imageUrl:string, distance:string, zoneID:int,
	// zoneName:string, priceText:string,
	// tags:string, tagIDs:string, address:string, developers:string,
	// openDate:string, moveInDate:string,
	// promotion:string, Lat:float, Lng:float, propertyCompany:string
	// } , бн],
	//
	// productList: [
	// { loupanProductID:int, title:string, loupanID:int, imageUrl:string,
	// promotion:string,
	// priceText:string, inSale: int, hotSale:int, saleCount:int,
	// openDate:string, moveInDate:string, areaText:string, price:decimal
	// }, бн ]

	private String result;
	private int loupanCount;
	private int productCount;
	private int recStart;
	private Loupan[] loupanList;
	private Product[] productList;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getLoupanCount() {
		return loupanCount;
	}

	public void setLoupanCount(int loupanCount) {
		this.loupanCount = loupanCount;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public int getRecStart() {
		return recStart;
	}

	public void setRecStart(int recStart) {
		this.recStart = recStart;
	}

	public Loupan[] getLoupanList() {
		return loupanList;
	}

	public void setLoupanList(Loupan[] loupanList) {
		this.loupanList = loupanList;
	}

	public Product[] getProductList() {
		return productList;
	}

	public void setProductList(Product[] productList) {
		this.productList = productList;
	}

	public class Product {
		private int loupanProductID;
		private String title;
		private int loupanID;
		private String imageUrl;
		private String promotion;
		private String priceText;
		private int inSale;
		private int hotSale;
		private int saleCount;
		private String openDate;
		private String moveInDate;
		private String areaText;
		private float price;

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

		public int getLoupanID() {
			return loupanID;
		}

		public void setLoupanID(int loupanID) {
			this.loupanID = loupanID;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public String getPromotion() {
			return promotion;
		}

		public void setPromotion(String promotion) {
			this.promotion = promotion;
		}

		public String getPriceText() {
			return priceText;
		}

		public void setPriceText(String priceText) {
			this.priceText = priceText;
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

		public String getAreaText() {
			return areaText;
		}

		public void setAreaText(String areaText) {
			this.areaText = areaText;
		}

		public float getPrice() {
			return price;
		}

		public void setPrice(float price) {
			this.price = price;
		}

	}

	public class Loupan {
		private int loupanID;
		private String name;
		private String imageUrl;
		private String distance;
		private int zoneID;
		private String zoneName;
		private String priceText;
		private String tags;
		private String tagIDs;
		private String address;
		private String developers;
		private String openDate;
		private String moveInDate;
		private String promotion;
		private float Lat;
		private float Lng;
		private String propertyCompany;

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

		public int getZoneID() {
			return zoneID;
		}

		public void setZoneID(int zoneID) {
			this.zoneID = zoneID;
		}

		public String getZoneName() {
			return zoneName;
		}

		public void setZoneName(String zoneName) {
			this.zoneName = zoneName;
		}

		public String getPriceText() {
			return priceText;
		}

		public void setPriceText(String priceText) {
			this.priceText = priceText;
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

		public String getPropertyCompany() {
			return propertyCompany;
		}

		public void setPropertyCompany(String propertyCompany) {
			this.propertyCompany = propertyCompany;
		}

	}

}
