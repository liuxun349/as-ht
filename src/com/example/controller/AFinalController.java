package com.example.controller;

import com.lidroid.xutils.DbUtils;

import net.tsz.afinal.FinalDb;
import android.content.Context;

public class AFinalController {

	private static AFinalController aFinalController;

	private DbUtils finalDb;

	private synchronized static AFinalController getinit(Context context) {
		if (aFinalController == null) {
			aFinalController = new AFinalController(context);
		}
		return aFinalController;
	}

	private AFinalController(Context context) {
		finalDb = DbUtils.create(context);
		finalDb.configDebug(true);
	}

	public static AFinalController create(Context context) {
		return getinit(context);
	}

	public DbUtils getfinalDb() {
		return finalDb;
	}

	public static DbUtils getDB(Context context) {
		// TODO Auto-generated method stub
		return create(context).getfinalDb();
	}
}
