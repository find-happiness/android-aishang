package com.aishang.app.data.model;

public class JLoupanProductCatListResult {
	/*
	 * { result: "success", catList: [ { id: 1, name: "旅居型产品" }, { id: 2, name:
	 * "收益型产品" } ] }
	 */

	private String result;
	private Cat[] catList;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Cat[] getCatList() {
		return catList;
	}

	public void setCatList(Cat[] catList) {
		this.catList = catList;
	}

	public class Cat {
		private int id;
		private String name;

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

	}

}
