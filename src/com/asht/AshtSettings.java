package com.asht;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.asht.utl.Des3;

public class AshtSettings {
	private SharedPreferences mPreferences;
	private Context mContext;
	private static AshtSettings sInstance;
	public static void initailze(final Context context){
		sInstance = new AshtSettings(context);
	}
	public static AshtSettings getInstance(){
		return sInstance;
	}
	public AshtSettings(Context context){
		this.mContext = context;
		mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
	}
	public boolean getIsAutoLogin(){
		return mPreferences.getBoolean(PreferenceKeys.AUTO_LOGIN	, false);
	}
	public void setIsAutoLogin(boolean valuse){
		mPreferences.edit().putBoolean(PreferenceKeys.AUTO_LOGIN, valuse).apply();
	}
	public String getUserPwd() throws Exception{
		String pwd = mPreferences.getString(PreferenceKeys.USER_PWD, "");
		String secretKey = getSecurityKeyD();
		pwd = Des3.decode(pwd, secretKey);
		return pwd;
	}
	public void setUserPwd(String value) throws Exception{
		String securityKey = Des3.getSecurityKey();
		setSecurityKeyD(securityKey);
		mPreferences.edit().putString(PreferenceKeys.USER_PWD, Des3.encode(value, securityKey)).apply();
	}
	public String getSecurityKeyU(){
		return mPreferences.getString(PreferenceKeys.SECURITYKEY_U, "");
	}
	public void setSecurityKeyU(String value){
		mPreferences.edit().putString(PreferenceKeys.SECURITYKEY_U, value).apply();
	}
	public String getSecurityKeyD(){
		return mPreferences.getString(PreferenceKeys.SECURITYKEY_D, "");
	}
	public void setSecurityKeyD(String value){
		mPreferences.edit().putString(PreferenceKeys.SECURITYKEY_D, value).apply();
	}
	public void setUserId(String value) throws Exception{
		String securityKey = Des3.getSecurityKey();
		setSecurityKeyU(securityKey);
		mPreferences.edit().putString(PreferenceKeys.USER_ID, Des3.encode(value, securityKey)).apply();
	}
	public String getUserId() throws Exception{
		String user = mPreferences.getString(PreferenceKeys.USER_ID, "");
		String secretKey = getSecurityKeyU();
		user = Des3.decode(user, secretKey);
		return user;
	}
	private class PreferenceKeys{
		final static String AUTO_LOGIN = "auto_login";
		final static String USER_PWD = "purwsedr";
		final static String USER_ID = "iurwserd";
		final static String SECURITYKEY_U = "securitykeyu";
		final static String SECURITYKEY_D = "securitykeyd";
	}
}
