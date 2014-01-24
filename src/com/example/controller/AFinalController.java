package com.example.controller;

import net.tsz.afinal.FinalDb;
import android.content.Context;

public class AFinalController {

	private static AFinalController aFinalController;

	private FinalDb finalDb;

	private synchronized static AFinalController getinit(Context context) {
		if (aFinalController == null) {
			aFinalController = new AFinalController(context);
		}
		return aFinalController;
	}

	private AFinalController(Context context) {
		finalDb = FinalDb.create(context);
	}

	public static AFinalController create(Context context) {
		return getinit(context);
	}

	public FinalDb getfinalDb() {
		return finalDb;
	}

	public static FinalDb getDB(Context context) {
		// TODO Auto-generated method stub
		return create(context).getfinalDb();
	}
}
