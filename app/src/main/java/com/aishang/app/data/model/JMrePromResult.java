package com.aishang.app.data.model;

public class JMrePromResult {
	private String result;
	private Ad[] adList;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Ad[] getAdList() {
		return adList;
	}

	public void setAdList(Ad[] adList) {
		this.adList = adList;
	}

	public class Ad {

		private String imageUrl;
		private String model;
		private String param;
		private String param2;

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getParam() {
			return param;
		}

		public void setParam(String param) {
			this.param = param;
		}

		public String getParam2() {
			return param2;
		}

		public void setParam2(String param2) {
			this.param2 = param2;
		}
		
	}
}
