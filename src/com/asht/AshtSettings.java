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
		String secretKey = getSecurityKey();
		pwd = Des3.decode(pwd, secretKey);
		return pwd;
	}
	public void setUserPwd(String value) throws Exception{
		String securityKey = Des3.getSecurityKey();
		setSecurityKey(securityKey);
		mPreferences.edit().putString(PreferenceKeys.USER_PWD, Des3.encode(value, securityKey)).apply();
	}
	public String getSecurityKey(){
		return mPreferences.getString(PreferenceKeys.SECURITYKEY, "");
	}
	public void setSecurityKey(String value){
		mPreferences.edit().putString(PreferenceKeys.SECURITYKEY, value).apply();
	}
	public void setUserId(String value){
		mPreferences.edit().putString(PreferenceKeys.USER_ID, value).apply();
	}
	public String getUserId(){
		return mPreferences.getString(PreferenceKeys.USER_ID, "");
	}
	private class PreferenceKeys{
		final static String AUTO_LOGIN = "auto_login";
		final static String USER_PWD = "purwsedr";
		final static String USER_ID = "iurwserd";
		final static String SECURITYKEY = "securitykey";
	}
}
