package com.asht.data;

import org.json.JSONException;
import org.json.JSONObject;

import com.asht.model.UserInfo;

public class UserBaseHandler {
	/**
	 * 注册
	 * 
	 * @param user
	 *            用户注册信息
	 * @return JSON包含 ReturnCode,Message,Result
	 */
	public JSONObject regist(UserInfo user) {
		String name = "Regist";

		JSONObject param = user.toJson();
		JSONObject result = NetworkConnection.connection(name, param);

		return result;
	}

	/**
	 * 登录
	 * 
	 * @param userPhoneNumber
	 * @param loginPassword
	 * @param loginFrom
	 * @return
	 */
	public JSONObject login(String userPhoneNumber, String loginPassword,
			String loginFrom) {
		String name = "Login";

		JSONObject param = new JSONObject();
		try {
			param.put("UserPhoneNumber", userPhoneNumber);
			param.put("LoginPassword", loginPassword);
			param.put("LoginFrom", loginFrom);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject result = NetworkConnection.connection(name, param);
		return result;
	}
	/**
	 * 申请发送登录密码到用户邮箱
	 * @param userId
	 * @param securityQA
	 * @return
	 */
	public JSONObject requestSendLoginPasswordToEmail(String userId) {
		String name = "RequestSendLoginPasswordToEmail";
		JSONObject param = new JSONObject();
		try {
			param.put("UserId", userId);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject result = NetworkConnection.connection(name, param);
		return result;
	}
	/**
	 * 申请发送支付密码到用户邮箱
	 * @param userId
	 * @param securityQA
	 * @return
	 */
	public JSONObject requestSendPayPasswordToEmail(String userId,
			JSONObject securityQA) {
		String name = "RequestSendPayPasswordToEmail";
		JSONObject param = new JSONObject();
		try {
			param.put("UserId", userId);
			param.put("SecurityQA", securityQA);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject result = NetworkConnection.connection(name, param);
		return result;
	}
}
