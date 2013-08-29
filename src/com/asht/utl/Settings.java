package com.asht.utl;

public class Settings {
	
//	public static String WEB_SERVER = "http://192.168.0.109:8063/"; // webserver 地址
	public static String WEB_SERVER = "http://192.168.2.13:8063/";
	
	public static String NAMESPACE = "http://webService.cd.anticancer.cn/";
	
	public static String RETURN_CODE = "ReturnCode";
	public static String RETURN_MESSAGE = "Message";
	public static String RETURN_USER_TOKEN = "UserToken";
	public static String RETURN_RESULT = "Result";
	
	public static int REASON_TYPE_REGIST = 0; // 注册
	public static int REASON_TYPE_RESET_LOGIN_PASSWORD = 1; // 找回登录密码
	public static int REASON_TYPE_RESET_PAY_PASSWORD = 2; // 找回消费密码
	public static int REASON_TYPE_EXCHANGE_PHONE_NUMBER = 3; // 更换手机
	
	public static int RETURN_CODE_ACCESS = 200;	//成功
	public static int RETURN_CODE_FAILED = 300;	//失败
	
	public static String WRONG_INPUT_FORMAT_TITLE = "输入格式错误";
}
