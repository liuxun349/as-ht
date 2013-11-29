package com.asht.http;

import org.json.JSONObject;

import com.asht.utl.ConnCallback;

public class CompanyDAO {
	private String URL_NAME ="TbCompanyInfo";
	/**
	 * 获取公司信息
	 */
	public void getComanyInfo( ConnCallback callback) {
		String name = "getComanyInfo";
		JSONObject param = new JSONObject();
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}
	
	
}
