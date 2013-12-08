package com.asht;

import com.alibaba.fastjson.JSONObject;
import com.asht.http.AshtResponse;
import com.asht.http.HttpClient;
import com.asht.model.UserInfo;

public class AsHt {
	HttpClient httpClient = new HttpClient();
	String method;
	JSONObject json;
	/**
	 * 登录
	 * @param name
	 * @param passwd
	 * @return
	 */
	public UserInfo login(String name, String passwd){
		method = "login";
		json = new JSONObject();
		json.put("userPhoneNo", name);
		json.put("userLoginPwd", passwd);
		return new UserInfo(get(method, json));
	}
	/**
	 * 向手机发送验证码
	 * @return
	 */
	public AshtResponse sendVerificationCode(){
		method = "GenerateAndSendMobileVerificationCode";
		return get(method, null);
	}
	/**
	 * 验证手机与手机验证码
	 * @param method
	 * @param json
	 * @return
	 */
	public AshtResponse checkVerificationCode(String phoneNo,String checkNo){
		method = "CheckPhoneAndMobileVerificationCode";
		json = new JSONObject();
		json.put("userPhoneNo", phoneNo);
		json.put("checkNo", checkNo);
		return get(method, json);
	}
	/**
	 * 注册
	 * @return
	 */
	public AshtResponse getPass(UserInfo userInfo) {
		method = "Regist";
		json = userInfo.toJson();
		return get(method, json);
	}
	/**
	 * 发送密码到邮箱
	 * @param userPhoneNo
	 * @return
	 */
	public AshtResponse sendPasswdToEmai(String userPhoneNo){
		method = "equestSendPayPasswordToEmail";
		json = new JSONObject();
		json.put("userPhoneNo", userPhoneNo);
		return get(method, json);
	}
	/**
	 * 修改个人资料
	 * @return
	 */
	public AshtResponse modifyUserInfo(){
		method = "ModifyUserInfo";
		return get(method, json);
	}
	
	
	private AshtResponse get(String method,JSONObject json){
		return httpClient.get(method, json.toJSONString());
	}
}
