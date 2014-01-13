package com.asht.utl;

import android.util.Log;

public class YJDebug {

	private static boolean YJ_DEBUG = true;

	public static void showLog(String fag, Class<?> c) {
		if (YJ_DEBUG) {
			Log.e(c.getSimpleName(), fag);
		}
	}

}
