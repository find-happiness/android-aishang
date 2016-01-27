package com.aishang.app.data.model;

/**
 * /mobile/NewsList.ashx version:V2
 * 
 * @author wang
 * 
 */
public class JNewsListResult {

	private String result;
	private int totalCount;
	private JNewsCatListItem[] newsCatList;

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

	public JNewsCatListItem[] getNewsCatList() {
		return newsCatList;
	}

	public void setNewsCatList(JNewsCatListItem[] newsCatList) {
		this.newsCatList = newsCatList;
	}

	public class JNewsCatListItem {
		private int catID;
		private String catName;
		private int totalCount;
		private JNewsListItem[] newsList;

		public int getCatID() {
			return catID;
		}

		public void setCatID(int catID) {
			this.catID = catID;
		}

		public String getCatName() {
			return catName;
		}

		public void setCatName(String catName) {
			this.catName = catName;
		}

		public int getTotalCount() {
			return totalCount;
		}

		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}

		public JNewsListItem[] getNewsList() {
			return newsList;
		}

		public void setNewsList(JNewsListItem[] newsList) {
			this.newsList = newsList;
		}

	}

	public class JNewsListItem {
		private int newsID;
		private String newsGUID;
		private String title;
		private String shortDesc;
		private String imageUrl;
		private String date;
		private int hits;
		private int supports;
		private String staticUrl;

		public int getNewsID() {
			return newsID;
		}

		public void setNewsID(int newsID) {
			this.newsID = newsID;
		}

		public String getNewsGUID() {
			return newsGUID;
		}

		public void setNewsGUID(String newsGUID) {
			this.newsGUID = newsGUID;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getShortDesc() {
			return shortDesc;
		}

		public void setShortDesc(String shortDesc) {
			this.shortDesc = shortDesc;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public int getHits() {
			return hits;
		}

		public void setHits(int hits) {
			this.hits = hits;
		}

		public int getSupports() {
			return supports;
		}

		public void setSupports(int supports) {
			this.supports = supports;
		}

		public String getStaticUrl() {
			return staticUrl;
		}

		public void setStaticUrl(String staticUrl) {
			this.staticUrl = staticUrl;
		}
	}
}
