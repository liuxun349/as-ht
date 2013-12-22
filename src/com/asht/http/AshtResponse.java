package com.asht.http;

import android.util.Log;

import com.alibaba.fastjson.JSON;

public class AshtResponse {
	
	public static String TAG = "AshtResponse";
	public String message;
	public JSON result;
	public String code;
	public boolean isRight;
	
	public AshtResponse(){
		
	}
	public AshtResponse(AshtResponse ashtResponse) {
		// TODO Auto-generated constructor stub
		message = ashtResponse.message;
		result = ashtResponse.result;
		code = ashtResponse.code;
		isRight = ashtResponse.isRight;
	}

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
