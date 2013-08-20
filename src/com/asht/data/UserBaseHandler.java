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
	 * 
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
	 * 
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
	public JSONObject modifyLoginPassword(String userId, String oldpwd,
			String newpwd) {
		String name = "ModifyLoginPassword";
		JSONObject param = new JSONObject();
		try {
			param.put("UserId", userId);
			param.put("OldLoginPassword", oldpwd);
			param.put("NewLoginPassword", newpwd);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject result = NetworkConnection.connection(name, param);
		return result;
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
	public JSONObject resetLoginPassword(String userId, String verficationCode,
			String newLoginPassword) {
		String name = "ResetLoginPassword";
		JSONObject param = new JSONObject();
		try {
			param.put("UserId", userId);
			param.put("VerficationCode", verficationCode);
			param.put("NewLoginPassword", newLoginPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
		JSONObject result = NetworkConnection.connection(name, param);
		return result;
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
	public JSONObject modifyPayPassword(String userId, String oldPaypwd,
			String newPaypwd) {
		String name = "ModifyPayPassword";
		JSONObject param = new JSONObject();
		try {
			param.put("UserId", userId);
			param.put("OldPayPassword", oldPaypwd);
			param.put("NewPayPassword", newPaypwd);
		} catch (Exception e) {
			// TODO: handle exception
		}
		JSONObject result = NetworkConnection.connection(name, param);
		return result;
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
	public JSONObject modifyAnswerOfSecurityQuestion(String userId,
			String securityQuestionId, String answerOfSecurity) {
		String name = "ModifyAnswerOfSecurityQuestion";
		JSONObject param = new JSONObject();
		try {
			param.put("UserId", userId);
			param.put("SecurityQuestionId", securityQuestionId);
			param.put("AnswerOfSecurity", answerOfSecurity);
		} catch (Exception e) {
			// TODO: handle exception
		}
		JSONObject result = NetworkConnection.connection(name, param);
		return result;
	}
	/**
	 * 判断用户是否有密码保护 
	 * @param userId
	 * @return
	 */
	public JSONObject isUserPasswordProtected(String userId) {
		String name = "IsUserPasswordProtected";
		JSONObject param = new JSONObject();
		try {
			param.put("UserId", userId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		JSONObject result = NetworkConnection.connection(name, param);
		return result;
	}
	/**
	 * 验证密码保护问题答案
	 * @param userId
	 * @param securityQuestionId
	 * @param answerOfSecurityQuestion
	 * @return
	 */
	public JSONObject validateAnswerOfSecurityQuestion(String userId,String securityQuestionId,
			String answerOfSecurityQuestion){
		String name = "ValidateAnswerOfSecurityQuestion";
		JSONObject param = new JSONObject();
		try {
			param.put("UserId", userId);
			param.put("SecurityQuestionId", securityQuestionId);
			param.put("AnswerOfSecurityQuestion", answerOfSecurityQuestion);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		JSONObject result = NetworkConnection.connection(name, param);
		return result;
	}
	/**
	 * 验证手机验证码
	 * @param userId
	 * @param reasonType
	 * @param verficationCode
	 * @return
	 */
	public JSONObject validateMobileVerficationCode(String userId,String reasonType,
			String verficationCode){
		String name = "ValidateMobileVerficationCode";
		JSONObject param = new JSONObject();
		try {
			param.put("UserId", userId);
			param.put("ReasonType", reasonType);
			param.put("VerficationCode", verficationCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		JSONObject result = NetworkConnection.connection(name, param);
		return result;
	}
	/**
	 * 修改手机号 
	 * @param userId
	 * @param newPhoneNumber
	 * @return
	 */
	public JSONObject modifyMobileNumber(String userId,String newPhoneNumber){
		String name = "ModifyMobileNumber";
		JSONObject param = new JSONObject();
		try {
			param.put("UserId", userId);
			param.put("NewPhoneNumber", newPhoneNumber);
		} catch (Exception e) {
			// TODO: handle exception
		}
		JSONObject result = NetworkConnection.connection(name, param);
		return result;
	}
	/**
	 * 请求发送手机验证码到特定手机
	 * @param userId
	 * @param phoneOfSendingTo 特定手机号
	 * @param reasonType 请求类型（Settings.REASON_TYPE_REGIST 注册 .....）
	 * @return
	 */
	public JSONObject requestSendMobileVerificationCode(String userId,String phoneOfSendingTo,
			int reasonType){
		String name = "RequestSendMobileVerificationCode";
		JSONObject param = new JSONObject();
		try {
			param.put("UserId", userId);
			param.put("PhoneOfSendingTo", phoneOfSendingTo);
			param.put("ReasonType", reasonType);
		} catch (Exception e) {
			// TODO: handle exception
		}
		JSONObject result = NetworkConnection.connection(name, param);
		return result;
	}
	
}
