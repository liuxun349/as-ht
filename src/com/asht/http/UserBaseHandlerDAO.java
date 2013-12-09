package com.asht.http;

import org.json.JSONException;
import org.json.JSONObject;

import com.asht.model.UserInfo;
import com.asht.utl.ConnCallback;

public class UserBaseHandlerDAO {
	
	private static final String URL_NAME = "TbUserInfo";
	/**
	 * 注册
	 * 
	 * @param user
	 *            用户注册信息
	 * @return JSON包含 ReturnCode,Message,Result
	 */
	public void regist(UserInfo user, ConnCallback callback) {
		String name = "regist";
		JSONObject param = new JSONObject();
		com.alibaba.fastjson.JSONObject param1 = user.toJson();
		try {
			param.put("data", param1);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(param.toString());
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

	/**
	 * 登录
	 * 
	 * @param userPhoneNumber
	 * @param loginPassword
	 * @param loginFrom
	 * @return
	 */
	public void login(String userPhoneNumber, String loginPassword,
			ConnCallback callback) {
		String name = "login";
		String loginFrom = "AS-HT";

		JSONObject param = new JSONObject();
		JSONObject param1 = new JSONObject();
		try {
			param1.put("vcharPhoneNumber", userPhoneNumber);
			param1.put("vcharLoginPwd", loginPassword);
			param.put("data", param1);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

	/**
	 * 申请发送登录密码到用户邮箱
	 * 
	 * @param userId
	 * @param securityQA
	 * @return
	 */
	public void requestSendLoginPasswordToEmail(String userId,
			ConnCallback callback) {
		String name = "requestSendLoginPasswordToEmail";
		JSONObject param = new JSONObject();
		JSONObject param1 = new JSONObject();
		try {
			param1.put("UserId", userId);
			param.put("data", param1);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject result = NetworkConnectionDAO.connection(name, param);
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

	/**
	 * 申请发送支付密码到用户邮箱
	 * 
	 * @param userId
	 * @param securityQA
	 * @return
	 */
	public void requestSendPayPasswordToEmail(String userId,
			JSONObject securityQA, ConnCallback callback) {
		String name = "RequestSendPayPasswordToEmail";
		JSONObject param = new JSONObject();
		JSONObject param1 = new JSONObject();
		try {
			param.put("UserId", userId);
			param.put("SecurityQA", securityQA);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

	/**
	 * 修改登录密码
	 * 
	 * @param userId
	 *            手机号
	 * @param oldpwd
	 *            旧密码
	 * @param newpwd
	 *            新密码
	 * @return
	 */
	public static void modifyLoginPassword(String userId, String oldpwd,
			String newpwd, ConnCallback callback) {
		String name = "modifyLoginPassword";
		JSONObject param = new JSONObject();
		JSONObject param1 = new JSONObject();
		JSONObject json = new JSONObject();
		try {
			param.put("vcharUserId", userId);
			param.put("oldLoginPassword", oldpwd);
			param.put("newLoginPassword", newpwd);
			json.put("data", param);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new ConnServer(URL_NAME, name, callback).execute(json.toString());
	}


	/**
	 * ResetLoginPassword
	 * 
	 * @param userId
	 *            用户手机号
	 * @param verficationCode
	 *            手机验证码
	 * @param newLoginPassword
	 *            新密码
	 * @return
	 */
	public void resetLoginPassword(String userId, String verficationCode,
			String newLoginPassword, ConnCallback callback) {
		String name = "resetLoginPassword";
		JSONObject param = new JSONObject();
		JSONObject param1 = new JSONObject();
		try {
			param.put("UserId", userId);
			param.put("VerficationCode", verficationCode);
			param.put("NewLoginPassword", newLoginPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

	/**
	 * 修改支付密码
	 * 
	 * @param userId
	 *            手机号
	 * @param oldPaypwd
	 *            旧支付密码
	 * @param newPaypwd
	 *            新支付密码
	 * @return
	 */
	public void modifyPayPassword(String userId, String oldPaypwd,
			String newPaypwd, ConnCallback callback) {
		String name = "ModifyPayPassword";
		JSONObject param = new JSONObject();
		JSONObject param1 = new JSONObject();
		try {
			param.put("UserId", userId);
			param.put("OldPayPassword", oldPaypwd);
			param.put("NewPayPassword", newPaypwd);
		} catch (Exception e) {
			// TODO: handle exception
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

	/**
	 * 修改密码保护问题答案
	 * 
	 * @param userId
	 * @param securityQuestionId
	 *            第几个问题
	 * @param answerOfSecurity
	 *            答案
	 * @return
	 */
	public void modifyAnswerOfSecurityQuestion(String userId,
			String securityQuestionId, String answerOfSecurity,
			ConnCallback callback) {
		String name = "ModifyAnswerOfSecurityQuestion";
		JSONObject param = new JSONObject();
		JSONObject param1 = new JSONObject();
		try {
			param.put("UserId", userId);
			param.put("SecurityQuestionId", securityQuestionId);
			param.put("AnswerOfSecurity", answerOfSecurity);
		} catch (Exception e) {
			// TODO: handle exception
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

	/**
	 * 判断用户是否有密码保护
	 * 
	 * @param userId
	 * @return
	 */
	public void isUserPasswordProtected(String userId,
			ConnCallback callback) {
		String name = "IsUserPasswordProtected";
		JSONObject param = new JSONObject();
		JSONObject param1 = new JSONObject();
		try {
			param.put("UserId", userId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

	/**
	 * 验证密码保护问题答案
	 * 
	 * @param userId
	 * @param securityQuestionId
	 * @param answerOfSecurityQuestion
	 * @return
	 */
	public void validateAnswerOfSecurityQuestion(String userId,
			String securityQuestionId, String answerOfSecurityQuestion,
			ConnCallback callback) {
		String name = "ValidateAnswerOfSecurityQuestion";
		JSONObject param = new JSONObject();
		JSONObject param1 = new JSONObject();
		try {
			param.put("UserId", userId);
			param.put("SecurityQuestionId", securityQuestionId);
			param.put("AnswerOfSecurityQuestion", answerOfSecurityQuestion);

		} catch (Exception e) {
			// TODO: handle exception
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

	/**
	 * 
	 * @param userId
	 * @param reasonType
	 * @param verficationCode
	 * @return
	 */
	public void validateMobileVerficationCode(String userId,
			String reasonType, String verficationCode, ConnCallback callback) {
		String name = "ValidateMobileVerficationCode";
		JSONObject param = new JSONObject();
		JSONObject param1 = new JSONObject();
		try {
			param.put("UserId", userId);
			param.put("ReasonType", reasonType);
			param.put("VerficationCode", verficationCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

	/**
	 * 修改手机号
	 * 
	 * @param userId
	 * @param newPhoneNumber
	 * @return
	 */
	public void modifyMobileNumber(String userId, String newPhoneNumber,
			ConnCallback callback) {
		String name = "ModifyMobileNumber";
		JSONObject param1 = new JSONObject();
		JSONObject param = new JSONObject();
		try {
			param.put("UserId", userId);
			param.put("NewPhoneNumber", newPhoneNumber);
		} catch (Exception e) {
			// TODO: handle exception
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

	/**
	 * 请求发送手机验证码到特定手机
	 * 
	 * @param userId
	 * @param phoneOfSendingTo
	 *            特定手机号
	 * @param reasonType
	 *            请求类型（Settings.REASON_TYPE_REGIST 注册 .....）
	 * @return
	 */
	public void requestSendMobileVerificationCode(String userId,
			String phoneOfSendingTo, int reasonType, ConnCallback callback) {
		String name = "RequestSendMobileVerificationCode";
		JSONObject param = new JSONObject();
		JSONObject param1 = new JSONObject();
		try {
			param.put("UserId", userId);
			param.put("PhoneOfSendingTo", phoneOfSendingTo);
			param.put("ReasonType", reasonType);
		} catch (Exception e) {
			// TODO: handle exception
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

	/**
	 * 生成并发送手机验证码----服务器端未完成
	 * 
	 * @param phoneId
	 * @return
	 */
	public void requestSendMobileVerficationCode(String phoneId,
			ConnCallback callback) {
		String name = "RequestSendMobileVerficationCode";
		JSONObject param = new JSONObject();
		JSONObject param1 = new JSONObject();
		try {
			param.put("phoneId", phoneId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

}
