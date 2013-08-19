package com.asht.database;

import org.json.JSONStringer;

public class DateBaseUser {
	
	/**
	 * 生成并发送手机验证码
	 * @param phoneNUm
	 * @return 手机验证码
	 */
	public void generateAndSendMobileVerificationCode(String phoneNUm){
		
	}
	/**
	 * 注册
	 */
	public void Regist(){
		
	}
	
	/**
	 * 登录验证
	 * @param userid
	 * @param passwd
	 * @return JSON数据包，成功则是用户信息，失败则是失败信息
	 */
	public JSONStringer login(String userid,String passwd){
		return new JSONStringer();
	}
	/**
	 * 判断是否已设置密保
	 * @param userid
	 * @return
	 */
	public boolean isUserPasswordProtected(String userid){
		return true;
	}
}
