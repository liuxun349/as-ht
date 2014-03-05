package com.asht.utl;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

@SuppressLint("SimpleDateFormat")
public class SharedPreferencesUtils {
	static SharedPreferences sp;
	static SharedPreferencesUtils sputils;
	static SimpleDateFormat df = null;

	static final String TIME = "time";

	@SuppressLint("CommitPrefEdits")
	public static void setTime(Context context) {
		checkNull(context);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("updateRecord", "上次更新：" + df.format(new Date()));
		editor.commit();
	}

	public static String getTime(Context context) {
		checkNull(context);
		return sp.getString("updateRecord", "还未更新过");
	}

	private static SharedPreferencesUtils checkNull(Context context) {
		return sputils = sputils == null ? sputils = new SharedPreferencesUtils(
				context) : sputils;
	}

	private SharedPreferencesUtils(Context context) {
		df = new SimpleDateFormat("MM-dd HH:mm:ss");
		sp = context.getSharedPreferences("update_time", Context.MODE_PRIVATE);
	}

}
