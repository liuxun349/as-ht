package com.asht.utl;

import java.util.LinkedList;
import java.util.List;

import com.asht.model.User;
import com.asht.model.UserInfo;

import android.app.Activity;
import android.app.Application;

public class ApplictionManager extends Application {
	private User user = new User();
	private List<Activity> activityList = new LinkedList<Activity>();
	public UserInfo userInfo = new UserInfo();
	private static ApplictionManager instance;

	private ApplictionManager() {
		user.setIsLogin(false);
	}

	public static ApplictionManager getInstance() {
		if (instance == null) {
			instance = new ApplictionManager();
		}
		return instance;
	}

	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	public UserInfo getUserInfo(){
		return userInfo;
	}
	public void exit() {
		for (Activity activity : activityList) {
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
		int id = android.os.Process.myPid();
		if (id != 0) {
			android.os.Process.killProcess(id);
		}
	}
	public User getUser(){
		return user;
	}
}