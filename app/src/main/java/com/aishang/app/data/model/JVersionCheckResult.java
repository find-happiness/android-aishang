package com.aishang.app.data.model;

public class JVersionCheckResult {

	/**
	 * result:string, version:string(decimal (10,2)), sourceType:int,
	 * sourceUrl:string
	 */

	private String result;
	private float version;
	private String sourceUrl;
	private int sourceType;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public float getVersion() {
		return version;
	}

	public void setVersion(float version) {
		this.version = version;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public int getSourceType() {
		return sourceType;
	}

	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}

	@Override public String toString() {
		return "JVersionCheckResult{" +
				"result='" + result + '\'' +
				", version=" + version +
				", sourceUrl='" + sourceUrl + '\'' +
				", sourceType=" + sourceType +
				'}';
	}
}