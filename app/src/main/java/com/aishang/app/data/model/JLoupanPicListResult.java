package com.aishang.app.data.model;
/**
 * /mobile/loupan/loupanPicList.ashx 
 * version:1
 * @author wang
 *
 */
public class JLoupanPicListResult {
	// {result: string,
	// loupanID:int, loupanName:string,
	// catList:[{catID:int, name:string, startPos:int}],
	// imageTotal:int,
	// imageList: [ { idx:int, loupanProductID:int, catID:int ,url:string,
	// title:string }, бн ]
	// }
	private String result;
	private int loupanID;
	private String loupanName;
	private int imageTotal;
	private Cat[] catList;
	private Image[] imageList;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getLoupanID() {
		return loupanID;
	}

	public void setLoupanID(int loupanID) {
		this.loupanID = loupanID;
	}

	public String getLoupanName() {
		return loupanName;
	}

	public void setLoupanName(String loupanName) {
		this.loupanName = loupanName;
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
		private int loupanProductID;
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

		public int getLoupanProductID() {
			return loupanProductID;
		}

		public void setLoupanProductID(int loupanProductID) {
			this.loupanProductID = loupanProductID;
		}

	}
}
