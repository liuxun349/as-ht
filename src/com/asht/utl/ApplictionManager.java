package com.asht.utl;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;

import com.asht.fragment.AshtFragment;
import com.asht.model.User;
import com.asht.model.UserInfo;

public class ApplictionManager extends Application {
	private User user = new User();
	private List<Activity> activityList = new LinkedList<Activity>();
	public UserInfo userInfo = user.getUserInfo();
	private static ApplictionManager instance;
	public AshtFragment currentFragment = null;

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

	public UserInfo getUserInfo() {
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

	public User getUser() {
		return user;
	}
}