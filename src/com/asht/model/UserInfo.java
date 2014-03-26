/**
 * 用户基本信息
 */
package com.asht.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.asht.AsHtException;
import com.asht.http.AshtResponse;

public class UserInfo extends AshtResponse {
	private String userPhoneNo;
	private String userLoginPwd;
	private String userPayPwd;
	private String userTrueName;
	private int userRole = 1001;
	private int userCertificateType;
	private String userCertificateNo;
	private String userEmail;
	private String userNickName;
	private int userSex;
	private int userAge;
	private String address;
	private JSONArray securityQA;

	public UserInfo() {

	}

	public UserInfo(AshtResponse response) throws AsHtException {
		super(response);
		parseJson();
	}

	public UserInfo(String userId, String userLoginPwd, String userPayPwd,
			String tureName, int certificateType, String certificateNo,
			String email, String nickName, int sex, int age, String address) {
		super();
		this.userPhoneNo = userId;
		this.userLoginPwd = userLoginPwd;
		this.userPayPwd = userPayPwd;
		this.userTrueName = tureName;
		this.userCertificateType = certificateType;
		this.userCertificateNo = certificateNo;
		this.userEmail = email;
		this.userNickName = nickName;
		this.userSex = sex;
		this.userAge = age;
		this.address = address;
	}

	public void setSecutrityQA(int Q1_id, String A1, int Q2_id, String A2) {
		securityQA = new JSONArray();
		try {
			JSONObject item1 = new JSONObject();
			JSONObject item2 = new JSONObject();
			item1.put("iquestionId", Q1_id);
			item1.put("vcharAnswer", A1);
			item2.put("iquestionId", Q2_id);
			item2.put("vcharAnswer", A2);
			securityQA.add(item1);
			securityQA.add(item2);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void parseJson() throws AsHtException {
		if (result == null) {
			throw new AsHtException("密码错误！", 10011);
		}
		UserInfo rs = JSON.parseObject(result.toString(), UserInfo.class);
		if (rs == null)
			return;
		userPhoneNo = rs.userPhoneNo;
		userLoginPwd = rs.userLoginPwd;
		userAge = rs.userAge;
		userPayPwd = rs.userPayPwd;
		userTrueName = rs.userTrueName;
		userRole = rs.userRole;
		userNickName = rs.userNickName;
		userEmail = rs.userEmail;
		userCertificateType = rs.userCertificateType;
		userCertificateNo = rs.userCertificateNo;
	}

	public JSONObject toJson() {
		JSONObject jObject = new JSONObject();
		jObject.put("userPhoneNo", userPhoneNo);
		jObject.put("userLoginPwd", userLoginPwd);
		jObject.put("userPayPwd", userPayPwd);
		jObject.put("userTrueName", userTrueName);
//		jObject.put("userRole", userRole);
		jObject.put("userNickName", userNickName);
		jObject.put("userEmail", userEmail);
		jObject.put("userCertificateType", userCertificateType);
		jObject.put("userCertificateNo", userCertificateNo);
		jObject.put("userSex", userSex);
		jObject.put("userAge", userAge);
		// jObject.put("tbUserPasswordquestionAnswer", securityQA);

		return jObject;
	}

	public JSONArray getSecurityQA() {
		return securityQA;
	}

	public String getUserPhoneNo() {
		return userPhoneNo;
	}

	public void setUserPhoneNo(String userPhoneNo) {
		this.userPhoneNo = userPhoneNo;
	}

	public String getUserLoginPwd() {
		return userLoginPwd;
	}

	public void setUserLoginPwd(String userLoginPwd) {
		this.userLoginPwd = userLoginPwd;
	}

	public String getUserPayPwd() {
		return userPayPwd;
	}

	public void setUserPayPwd(String userPayPwd) {
		this.userPayPwd = userPayPwd;
	}

	public String getUserTrueName() {
		return userTrueName;
	}

	public void setUserTrueName(String userTrueName) {
		this.userTrueName = userTrueName;
	}

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	public int getUserCertificateType() {
		return userCertificateType;
	}

	public void setUserCertificateType(int userCertificateType) {
		this.userCertificateType = userCertificateType;
	}

	public String getUserCertificateNo() {
		return userCertificateNo;
	}

	public void setUserCertificateNo(String userCertificateNo) {
		this.userCertificateNo = userCertificateNo;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public int getUserSex() {
		return userSex;
	}

	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setSecurityQA(JSONArray securityQA) {
		this.securityQA = securityQA;
	}

}