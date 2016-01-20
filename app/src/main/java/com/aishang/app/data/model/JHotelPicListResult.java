package com.aishang.app.data.model;
/**
 * /mobile/hotel/hotelPicList.ashx
 * version:1
 * @author wang
 *
 */
public class JHotelPicListResult {
	// {result: string,
	// hotelID:int, hotelName:string,
	// catList:[{catID:int, name:string, startPos:int}, ...],
	// imageTotal:int,
	// imageList: [ { idx:int, catID:int ,url:string, title:string}, бн ]
	// }
	private String result;
	private int hotelID;
	private String hotelName;
	private int imageTotal;
	private Cat[] catList;
	private Image[] imageList;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getHotelID() {
		return hotelID;
	}

	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public int getImageTotal() {
		return imageTotal;
	}

	public void setImageTotal(int imageTotal) {
		this.imageTotal = imageTotal;
	}

	public Cat[] getCatList() {
		return catList;
	}

	public void setCatList(Cat[] catList) {
		this.catList = catList;
	}

	public Image[] getImageList() {
		return imageList;
	}

	public void setImageList(Image[] imageList) {
		this.imageList = imageList;
	}

	public class Cat {
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

	}

	public class Image {
		private int idx;
		private int catID;
		private String url;
		private String title;

		public int getIdx() {
			return idx;
		}

		public void setIdx(int idx) {
			this.idx = idx;
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
