/**
 * 用户基本信息
 */
package com.asht.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.asht.http.AshtResponse;

public class UserInfo extends AshtResponse {
	private String userId;
	private String loginPwd;
	private String payPwd;
	private String tureName;
	private int roleId;
	private int certificateType;
	private String certificateNo;
	private String email;
	private String nickName;
	private int sex;
	private int age;
	private String address;
	private JSONArray securityQA;

	public UserInfo() {

	}

	public UserInfo(AshtResponse response) {
		super(response);
		parseJson();
	}

	public UserInfo(String userId, String loginPwd, String payPwd,
			String tureName, int certificateType, String certificateNo,
			String email, String nickName, int sex, int age, String address) {
		super();
		this.userId = userId;
		this.loginPwd = loginPwd;
		this.payPwd = payPwd;
		this.tureName = tureName;
		this.certificateType = certificateType;
		this.certificateNo = certificateNo;
		this.email = email;
		this.nickName = nickName;
		this.sex = sex;
		this.age = age;
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

	private void parseJson() {
		JSONObject rs = (JSONObject) result;
		userId = rs.getString("vcharPhoneNumber");
		loginPwd = (String) rs.get("vcharLoginPwd");
		payPwd = (String) rs.get("vcharPayPwd");
		tureName = (String) rs.get("vcharTrueName");
		roleId = (Integer) rs.get("iroleID");
		nickName = (String) rs.get("vcharNickname");
		email = (String) rs.get("vcharEmail");
		certificateType = (Integer) rs.get("icertificateType");
		certificateNo = (String) rs.get("icertificateNo");
		securityQA = (JSONArray) rs.get("tbUserPasswordquestionAnswer");
	}

	public JSONObject toJson() {
		JSONObject jObject = new JSONObject();
		jObject.put("vcharPhoneNumber", userId);
		jObject.put("vcharLoginPwd", loginPwd);
		jObject.put("vcharPayPwd", payPwd);
		jObject.put("vcharTrueName", tureName);
		jObject.put("iroleID", roleId);
		jObject.put("vcharNickname", nickName);
		jObject.put("vcharEmail", email);
		jObject.put("icertificateType", certificateType);
		jObject.put("icertificateNo", certificateNo);
		jObject.put("tbUserPasswordquestionAnswer", securityQA);

		return jObject;
	}

	public JSONArray getSecurityQA() {
		return securityQA;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getPayPwd() {
		return payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

	public String getTureName() {
		return tureName;
	}

	public void setTureName(String tureName) {
		this.tureName = tureName;
	}

	public int getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(int certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRoleId() {
		return roleId;
	}

}
