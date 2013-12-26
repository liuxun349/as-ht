package com.asht.http;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public abstract class AshtResponse {
	
	public static String TAG = "AshtResponse";
	public String message;
	public Object result;
	public String code;
	public boolean success;
	
	public AshtResponse(){
		
	}
	public AshtResponse(AshtResponse ashtResponse) {
		// TODO Auto-generated constructor stub
		message = ashtResponse.message;
		result = ashtResponse.result;
		code = ashtResponse.code;
		success = ashtResponse.success;
	}
	public abstract JSONObject toJson();
	
	public static AshtResponse getResponse(String res) {
		// TODO Auto-generated constructor stub
		try {
			return JSON.parseObject(res, AshtResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
			Log.d("TAG", e.toString());
			return JSON
					.parseObject(HttpClient.ERROR, AshtResponse.class);

		}
	}
}
