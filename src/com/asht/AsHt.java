package com.asht;

import com.alibaba.fastjson.JSONObject;
import com.asht.controller.SystemService;
import com.asht.http.AshtResponse;
import com.asht.http.HttpClient;
import com.asht.model.UserInfo;

public class AsHt {
	HttpClient httpClient = new HttpClient();
	String method;
	JSONObject json;
	SystemService systemService = new SystemService();

	/**
	 * 登录
	 * 
	 * @param name
	 * @param passwd
	 * @return
	 */
	public UserInfo login(String name, String passwd) {
		return new UserInfo(systemService.login(name, passwd));
	}

	/**
	 * 向手机发送验证码
	 * 
	 * @return
	 */
	public AshtResponse sendVerificationCode() {

		return systemService.sendVerificationCode();
	}

	/**
	 * 验证手机与手机验证码
	 * 
	 * @param method
	 * @param json
	 * @return
	 */
	public AshtResponse checkVerificationCode(String phoneNo, String checkNo) {
		return systemService.checkVerificationCode(phoneNo, checkNo);
	}

	/**
	 * 注册
	 * 
	 * @return
	 */
	public AshtResponse getPass(UserInfo userInfo) {
		return systemService.regist(userInfo);
	}

	/**
	 * 发送密码到邮箱
	 * 
	 * @param userPhoneNo
	 * @return
	 */
	public AshtResponse sendPasswdToEmai(String userPhoneNo) {
			return systemService.sendPasswdToEmai(userPhoneNo);
	}

	/**
	 * 修改个人资料
	 * 
	 * @return
	 */
	public AshtResponse modifyUserInfo() {
		method = "ModifyUserInfo";
		return get(method, json);
	}
	private AshtResponse get(String method, JSONObject json) {
		return httpClient.get(method, json.toJSONString());
	}
}