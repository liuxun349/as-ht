package com.asht.http;

import org.json.JSONObject;

import com.asht.utl.ConnCallback;

public class LawDeclareDAO {
	private String URL_NAME = "";
	/**
	 * 获取法律声明信息
	 */
	public void getLawDeclareInfo( ConnCallback callback) {
		String name = "GetLawDeclareInfo";
		JSONObject param = new JSONObject();
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}
	
	
}
