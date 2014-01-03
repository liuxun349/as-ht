package com.asht.http;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asht.AsHtException;

public  class AshtResponse {
	
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
	public JSONObject toJson(){
		return null;
	}
	
	public static AshtResponse getResponse(String res) throws AsHtException{
		// TODO Auto-generated constructor stub
		try {
			return (AshtResponse)JSON.parseObject(res, AshtResponse.class);
		} catch (Exception e) {
			System.out.println("ddddddddd");
			e.printStackTrace();
			Log.d("TAG", e.toString());
			throw new AsHtException("读入错误", 1);
		}
	}
}
