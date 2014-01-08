package com.example.testafinal;

import android.app.Application;

import com.asht.model.Record;

public class MyApplication extends Application {

	private static Record mRecord;

	public static Record getmRecord() {
		return mRecord;
	}

	public static void setmRecord(
			Record mRecord) {
		MyApplication.mRecord = mRecord;
	}

}
