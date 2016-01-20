package com.aishang.app.data.model;
/**
 * /mobile/loupan/loupanPriceCatList.ashx   
 * version: 1
 * @author wang
 *
 */
public class JLoupanPriceCatListResult {
	// {result: string,
	// catList:[ {id:int, name:string, min:decimal, max:decimal}бн, ]
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
		private float min;
		private float max;

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

		public float getMin() {
			return min;
		}

		public void setMin(float min) {
			this.min = min;
		}

		public float getMax() {
			return max;
		}

		public void setMax(float max) {
			this.max = max;
		}

	}
}
