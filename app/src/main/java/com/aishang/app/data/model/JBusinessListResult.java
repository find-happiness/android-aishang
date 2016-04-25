package com.aishang.app.data.model;

import java.util.List;

/**
 * /mobile/member/businessList.ashx version 3
 * @author wang
 *
 */
public class JBusinessListResult {

	/**
	 * result : success
	 * listJData : [{"name":"谭智君","telephone":"18983979230","createTime":"2016-04-25T09:11:53","status":"仅提交","commission":0},{"name":"谭智君","telephone":"18983979230","createTime":"2016-04-25T09:11:13","status":"仅提交","commission":0},{"name":"谭智君","telephone":"18983979230","createTime":"2016-04-25T09:09:21","status":"仅提交","commission":0},{"name":"谭智君","telephone":"18983979230","createTime":"2016-04-21T15:23:02","status":"仅提交","commission":0},{"name":"谭智君","telephone":"18983979230","createTime":"2016-04-21T13:48:02","status":"仅提交","commission":0},{"name":"谭智君","telephone":"18983979230","createTime":"2016-04-21T11:58:18","status":"仅提交","commission":0}]
	 */

	private String result;
	/**
	 * name : 谭智君
	 * telephone : 18983979230
	 * createTime : 2016-04-25T09:11:53
	 * status : 仅提交
	 * commission : 0
	 */

	private List<ListJDataBean> listJData;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<ListJDataBean> getListJData() {
		return listJData;
	}

	public void setListJData(List<ListJDataBean> listJData) {
		this.listJData = listJData;
	}

	public static class ListJDataBean {
		private String name;
		private String telephone;
		private String createTime;
		private String status;
		private int commission;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public int getCommission() {
			return commission;
		}

		public void setCommission(int commission) {
			this.commission = commission;
		}
	}
}
