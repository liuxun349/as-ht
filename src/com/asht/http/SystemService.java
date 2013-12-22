package com.asht.http;

import com.alibaba.fastjson.JSONObject;
import com.asht.http.AshtResponse;
import com.asht.http.HttpClient;
import com.asht.model.User;
import com.asht.model.UserInfo;

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
	/**
	 * 获得密保问题
	 * @param no
	 * @param question
	 * @return
	 */
	public AshtResponse getQuestion(String no,String question){
		method = "getPasswordProtectionQuestion";
		json = new JSONObject();
		json.put("no", no);
		json.put("question", question);
		return get(method, json);
	}
	/**
	 * 注册
	 * @param userInfo
	 * @return
	 */
	public AshtResponse regist(UserInfo userInfo){
		method = "Regist";
		json = userInfo.toJson();
		return get(method, json);
	}
	/**
	 * 登录
	 * @param name
	 * @param passwd
	 * @return
	 */
	public AshtResponse login(String name, String passwd) {
		method = "login";
		json = new JSONObject();
		json.put("userPhoneNo", name);
		json.put("userLoginPwd", passwd);
		return get(method, json);
	}
	/**
	 * 发送登录密码到邮箱
	 * @param userPhoneNo
	 * @return
	 */
	public AshtResponse sendPasswdToEmai(String userPhoneNo) {
		method = "equestSendPayPasswordToEmail";
		json = new JSONObject();
		json.put("userPhoneNo", userPhoneNo);
		return get(method, json);
	}
	/**
	 * 下载app
	 * @return
	 */
	public AshtResponse DownLoadApp(){
		method = "downloadApp";
		return get(method, null);
	}
	/**
	 * 获取公司信息
	 * @return
	 */
	public AshtResponse getCompanyInfo(){
		method = "getCompanyInfo";
		return get(method, null);
	}
	/**
	 * 获取法律声明
	 * @return
	 */
	public AshtResponse getLawDeclareInfo(){
		method = "getLawDeclareInfo";
		return get(method, null);
	}
	private AshtResponse get(String method, JSONObject json) {
		return httpClient.get(method, json.toJSONString());
	}
}
