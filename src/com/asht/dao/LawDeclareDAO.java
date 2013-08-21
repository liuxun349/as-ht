package com.asht.dao;

import org.json.JSONObject;

public class LawDeclareDAO {

	/**
	 * 获取法律声明信息
	 */
	public JSONObject getLawDeclareInfo() {
		String mname = "GetLawDeclareInfo";
		JSONObject param = new JSONObject();
		JSONObject result = NetworkConnectionDAO.connection(mname, param);
		return result;
	}
	
	
}
