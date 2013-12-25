package com.asht.http;

import com.alibaba.fastjson.JSONObject;
import com.asht.model.UserInfo;

public class UserService {
	private static final String WEBSERVICE = "http://userService.CXFWebservice.modules.www.ascs.com/";
	private HttpClient httpClient = new HttpClient();
	private String method;
	private JSONObject json;

	/**
	 * 
	 * @param userInfo
	 * @return
	 */
	public AshtResponse modifyInfo(UserInfo userInfo) {
		method = "modifyUserInfo";
		return get(method, userInfo.toJson());
	}
	/**
	 * 
	 * @param oldpwd
	 * @param newpwd
	 * @return
	 */
	public AshtResponse modifyLoginPasswd(String oldpwd, String newpwd) {
		method = "modifyLoginPassword";
		json = new JSONObject();
		json.put("oldLoginPwd", oldpwd);
		json.put("newLoginPwd", newpwd);
		return get(method, json);
	}
	/**
	 * 
	 * @param userInfo
	 * @param checkNo
	 * @param usercertificate
	 * @param newPayPasswd
	 * @return
	 */
	public AshtResponse modifyPayPasswd(UserInfo userInfo,String checkNo,String usercertificate,String newPayPasswd){
		method = "modifyPayPassword";
		json = new JSONObject();
		json.put("userPhoneNo", userInfo.getUserId());
		json.put("checkNo", userInfo.getUserId());
		json.put("userCertificateNo", userInfo.getUserId());
		json.put("newPayPassword", userInfo.getUserId());
		return get(method, json);
	}
	/**
	 * 
	 * @param userInfo
	 * @param questionNo
	 * @param answer
	 * @return
	 */
	public AshtResponse modifyAnswerOfSecurityQuestion(UserInfo userInfo,int questionNo,String answer){
		method = "modifyAnswerOfSecrurityQuestion";
		json = new JSONObject();
		json.put("userPhoneNo", userInfo.getUserId());
		json.put("questionNo", questionNo);
		json.put("answer", answer);
		return get(method, json);
	}
	/**
	 * 
	 * @param userInfo
	 * @return
	 */
	public AshtResponse isUserSetPasswdProtected(UserInfo userInfo){
		method = "isUserPasswordProtected";
		json = new JSONObject();
		json.put("userPhoneNo", userInfo.getUserId());
		return get(method, json);
	}
	/**
	 * 
	 * @param userInfo
	 * @param questionNo
	 * @param questionContent
	 * @return
	 */
	public AshtResponse checkPasswdAnswerIsRight(UserInfo userInfo,int questionNo,String questionContent){
		method = "isPasswordProtectedValid";
		json.put("userPhoneNo", userInfo.getUserId());
		json.put("questionNo", questionNo);
		json.put("questionContent", questionContent);
		return get(method, json);
	}
	/**
	 * 
	 * @param userInfo
	 * @param checkNo
	 * @param payPasswd
	 * @param newUserPhoneNo
	 * @return
	 */
	public AshtResponse modifyMobileNumber(UserInfo userInfo,String checkNo,String payPasswd,String newUserPhoneNo){
		method = "modifyMobileNumber";
		json.put("userPhoneNo", userInfo.getUserId());
		json.put("checkNo", checkNo);
		json.put("userPayPwd", payPasswd);
		json.put("newUserPhoneNo", newUserPhoneNo);
		return get(method, json);
		
	}
	private AshtResponse get(String method, JSONObject json) {
		return httpClient.get(method, json.toJSONString(), WEBSERVICE);
	}
}
