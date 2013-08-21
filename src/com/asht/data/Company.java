package com.asht.data;

import org.json.JSONObject;

public class Company {

	/**
	 * 获取公司信息
	 */
	public JSONObject getComanyInfo() {
		String mname = "GetComanyInfo";
		JSONObject param = new JSONObject();
		JSONObject result = NetworkConnection.connection(mname, param);
		return result;
	}
	
	/**
	 * 设置公司信息
	 */
	public JSONObject setComanyInfo() {
		String mname = "SetComanyInfo";
		JSONObject param = new JSONObject();
		JSONObject result = NetworkConnection.connection(mname, param);
		return result;
	}
}
