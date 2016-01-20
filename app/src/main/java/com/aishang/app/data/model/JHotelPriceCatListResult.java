package com.aishang.app.data.model;
/**
 * /mobile/hotel/hotelPriceCatList.ashx  
 * version: 1
 * @author wang
 *
 */
public class JHotelPriceCatListResult {
	// {result: string,
	// catList:[ {id:int, name:string}бн, ]
	// }

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
