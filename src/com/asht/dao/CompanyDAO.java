package com.asht.dao;

import org.json.JSONObject;

import com.asht.utl.ConnCallback;

public class CompanyDAO {
	private String URL_NAME ="";
	/**
	 * 获取公司信息
	 */
	public void getComanyInfo( ConnCallback callback) {
		String name = "GetComanyInfo";
		JSONObject param = new JSONObject();
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}
	
	
}
