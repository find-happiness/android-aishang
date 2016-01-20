package com.aishang.app.data.model;

public class JVersionCheckParam {

	// platform:string, screen:string, version:string (decimal (10,2)),
	// branch:string


	public String platform;
	public String screen;
	public String version;
	public String branch;

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

}