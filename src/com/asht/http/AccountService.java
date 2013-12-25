package com.asht.http;

import com.alibaba.fastjson.JSONObject;

public class AccountService {
	private static final String WEBSERVICE = "http://accountService.CXFWebservice.modules.www.ascs.com/";
	private HttpClient httpClient = new HttpClient();
	private String method;
	private JSONObject json;
	
	private AshtResponse get(String method, JSONObject json) {
		return httpClient.get(method, json.toJSONString(), WEBSERVICE);
	}
}
