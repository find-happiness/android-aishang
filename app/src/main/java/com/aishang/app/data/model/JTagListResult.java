package com.aishang.app.data.model;

public class JTagListResult {
	// "result": "success",
	// "tagList": [
	// {
	// "id": 50,
	// "name": "白族民居式建筑"
	// }
	// ]
	// }

	private String result;
	private Tag[] tagList;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Tag[] getTagList() {
		return tagList;
	}

	public void setTagList(Tag[] tagList) {
		this.tagList = tagList;
	}

	public class Tag {

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
