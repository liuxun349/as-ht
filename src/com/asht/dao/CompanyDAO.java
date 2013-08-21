package com.asht.dao;

import org.json.JSONObject;

public class CompanyDAO {

	/**
	 * 获取公司信息
	 */
	public JSONObject getComanyInfo() {
		String mname = "GetComanyInfo";
		JSONObject param = new JSONObject();
		JSONObject result = NetworkConnectionDAO.connection(mname, param);
		return result;
	}
	
	
}
