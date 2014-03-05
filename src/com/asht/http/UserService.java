
package com.asht.http;

import com.alibaba.fastjson.JSONObject;
import com.asht.AsHtException;
import com.asht.model.UserInfo;
import com.asht.utl.Settings;

public class UserService {
	private static final String NAMESPACE = "http://userService.CXFWebservice.modules.www.ascs.com/";
	private static final String SERVICEURL = Settings.WEB_URL+"ascs/WS/UserService?wsdl";
	private String method ;
	private HttpClient httpClient = new HttpClient(NAMESPACE,SERVICEURL);
	JSONObject json;

	/**
	 * 修改个人资料
	 * @param userInfo
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse modifyInfo(UserInfo userInfo) throws AsHtException {
		method = "modifyUserInfo";
		return get(method, userInfo.toJson());
	}
	/**
	 * 修改登录密码
	 * @param oldpwd
	 * @param newpwd
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse modifyLoginPasswd(UserInfo user,String oldpwd, String newpwd) throws AsHtException {
		method = "modifyLoginPassword";
		json = new JSONObject();
		json.put("userPhoneNo", user.getUserPhoneNo());
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
	 * @throws AsHtException 
	 */
	public AshtResponse modifyPayPasswd(UserInfo userInfo,String checkNo,String usercertificate,String newPayPasswd) throws AsHtException{
		method = "modifyPayPassword";
		json = new JSONObject();
		json.put("userPhoneNo", userInfo.getUserPhoneNo());
		json.put("checkNo", userInfo.getUserPhoneNo());
		json.put("userCertificateNo", userInfo.getUserPhoneNo());
		json.put("newPayPassword", userInfo.getUserPhoneNo());
		return get(method, json);
	}
	/**
	 * 
	 * @param userInfo
	 * @param questionNo
	 * @param answer
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse modifyAnswerOfSecurityQuestion(UserInfo userInfo,int questionNo,String answer) throws AsHtException{
		method = "modifyAnswerOfSecrurityQuestion";
		json = new JSONObject();
		json.put("userPhoneNo", userInfo.getUserPhoneNo());
		json.put("questionNo", questionNo);
		json.put("answer", answer);
		return get(method, json);
	}
	/**
	 * 
	 * @param userInfo
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse isUserSetPasswdProtected(UserInfo userInfo) throws AsHtException{
		method = "isUserPasswordProtected";
		json = new JSONObject();
		json.put("userPhoneNo", userInfo.getUserPhoneNo());
		return get(method, json);
	}
	/**
	 * 
	 * @param userInfo
	 * @param questionNo
	 * @param questionContent
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse checkPasswdAnswerIsRight(UserInfo userInfo,int questionNo,String questionContent) throws AsHtException{
		method = "isPasswordProtectedValid";
		json.put("userPhoneNo", userInfo.getUserPhoneNo());
		json.put("questionNo", questionNo);
		json.put("questionContent", questionContent);
		return get(method, json);
	}
	/**
	 * 修改手机号
	 * @param userInfo
	 * @param checkNo
	 * @param payPasswd
	 * @param newUserPhoneNo
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse modifyMobileNumber(UserInfo userInfo,String checkNo,String payPasswd,String newUserPhoneNo) throws AsHtException{
		method = "modifyMobileNumber";
		json.put("userPhoneNo", userInfo.getUserPhoneNo());
		json.put("checkNo", checkNo);
		json.put("userPayPwd", payPasswd);
		json.put("newUserPhoneNo", newUserPhoneNo);
		return get(method, json);
		
	}
	private AshtResponse get(String method, JSONObject json) throws AsHtException {
		return httpClient.get(method, json.toJSONString());
	}
}
