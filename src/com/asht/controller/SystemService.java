package com.asht.controller;

import com.alibaba.fastjson.JSONObject;
import com.asht.http.AshtResponse;
import com.asht.http.HttpClient;

public class SystemService {
	HttpClient httpClient = new HttpClient();
	String method;
	JSONObject json;

	/**
	 * 验证手机与手机验证码
	 * 
	 * @param method
	 * @param json
	 * @return
	 */
	public AshtResponse checkVerificationCode(String phoneNo, String checkNo) {
		method = "CheckPhoneAndMobileVerificationCode";
		json = new JSONObject();
		json.put("userPhoneNo", phoneNo);
		json.put("checkNo", checkNo);
		return get(method, json);
	}

	/**
	 * 向手机发送验证码
	 * 
	 * @return
	 */
	public AshtResponse sendVerificationCode() {
		method = "GenerateAndSendMobileVerificationCode";
		return get(method, null);
	}
	
	public AshtResponse getQuestion(String no,String question){
		method = "getPasswordProtectionQuestion";
		json = new JSONObject();
		json.put("no", no);
		json.put("question", question);
		return get(method, json);
	}

	private AshtResponse get(String method, JSONObject json) {
		return httpClient.get(method, json.toJSONString());
	}
}
