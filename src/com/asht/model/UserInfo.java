/**
 * 用户基本信息
 */
package com.asht.model;

public class UserInfo {
	private String userId;
	private String loginPwd;
	private String payPwd;
	private String tureName;
	private final int roleId = 2;
	private int certificateType;
	private String certificateNo;
	private String email;
	private String nickName;
	private int sex;
	private int age;
	private String address;
	
	public UserInfo(){
		
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
