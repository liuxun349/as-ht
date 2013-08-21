package com.asht.data;

import org.json.JSONObject;

public class LawDeclare {

	/**
	 * 获取法律声明信息
	 */
	public JSONObject getLawDeclareInfo() {
		String mname = "GetLawDeclareInfo";
		JSONObject param = new JSONObject();
		JSONObject result = NetworkConnection.connection(mname, param);
		return result;
	}
	
	/**
	 * 获取法律声明信息
	 */
	public JSONObject setLawDeclareInfo(String LawDeclareInfo) {
		String mname = "SetLawDeclareInfo";
		JSONObject param = new JSONObject();
		try {
			param.put("LawDeclareInfo", LawDeclareInfo);
		} catch (Exception e) {
			// TODO: handle exception
		}
		JSONObject result = NetworkConnection.connection(mname, param);
		return result;
	}
}
