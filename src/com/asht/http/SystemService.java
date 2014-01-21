package com.asht.http;

import com.alibaba.fastjson.JSONObject;
import com.asht.AsHtException;
import com.asht.http.AshtResponse;
import com.asht.http.HttpClient;
import com.asht.model.UserInfo;

public class SystemService {
	private static final String NAMESPACE = "http://systemService.CXFWebservice.modules.www.ascs.com/";
	private static final String SERVICEURL = "http://115.28.48.85:8080/ascs/WS/SystemService?wsdl";
	private String method ;
	private HttpClient httpClient = new HttpClient(NAMESPACE,SERVICEURL);
	JSONObject json;

	/**
	 * 验证手机与手机验证码
	 * 
	 * @param method
	 * @param json
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse checkVerificationCode(String phoneNo, String checkNo) throws AsHtException {
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
	 * @throws AsHtException 
	 */
	public AshtResponse sendVerificationCode(String phoneNo,String reciverPhoneNo,int type) throws AsHtException {
		method = "generateAndSendMobileVerificationCode";
		json = new JSONObject();
		json.put("userPhoneNo", phoneNo);
		json.put("receivePhoneNo", reciverPhoneNo);
		json.put("type", type);
		return get(method, json);
	}

	/**
	 * 获得密保问题
	 * 
	 * @param no
	 * @param question
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse getQuestion(String no, String question) throws AsHtException {
		method = "getPasswordProtectionQuestion";
		json = new JSONObject();
		json.put("no", no);
		json.put("question", question);
		return get(method, json);
	}

	/**
	 * 注册
	 * 
	 * @param userInfo
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse regist(UserInfo userInfo) throws AsHtException {
		method = "regist";
		json = userInfo.toJson();
		return get(method, json);
	}

	/**
	 * 登录
	 * 
	 * @param name
	 * @param passwd
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse login(String name, String passwd) throws AsHtException {
		method = "login";
		json = new JSONObject();
		json.put("userPhoneNo", name);
		json.put("userLoginPwd", passwd);
		return get(method, json);
	}

	/**
	 * 发送登录密码到邮箱
	 * 
	 * @param userPhoneNo
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse sendPasswdToEmai(String userPhoneNo) throws AsHtException {
		method = "equestSendPayPasswordToEmail";
		json = new JSONObject();
		json.put("userPhoneNo", userPhoneNo);
		return get(method, json);
	}

	/**
	 * 下载app
	 * 
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse DownLoadApp() throws AsHtException {
		method = "downloadApp";
		return get(method, null);
	}

	/**
	 * 获取公司信息
	 * 
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse getCompanyInfo() throws AsHtException {
		method = "getCompanyInfo";
		return get(method, null);
	}

	/**
	 * 获取法律声明
	 * 
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse getLawDeclareInfo() throws AsHtException {
		method = "getLawDeclareInfo";
		return get(method, null);
	}

	private AshtResponse get(String method, JSONObject json) throws AsHtException {
		return httpClient.get(method, json.toJSONString());
	}
}
