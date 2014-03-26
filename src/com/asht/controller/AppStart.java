<<<<<<< HEAD
package com.asht.controller;

import android.app.Activity;
import android.os.Bundle;
import cn.jpush.android.api.JPushInterface;

import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.AshtSettings;
import com.asht.AsyncDataLoader;
import com.asht.R;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;
import com.asht.view.WaitingDialog;

public class AppStart extends Activity {
	AsyncDataLoader asyncDataLoader;
	private AshtSettings mAshtSettings;
	private String userPhoneNo = "";
	private String userPwd = "";
	private AsHt mAsht;
	public static boolean isForeground = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		AshtSettings.initailze(this);
		AsHt.initalize();
		mAshtSettings = AshtSettings.getInstance();

		WaitingDialog dialog = new WaitingDialog(this);
		// new Handler().postDelayed(new Runnable() {
		//
		// @Override
		// public void run() {
		// // TODO Auto-generated method stub
		// initCheck();
		// }
		// }, 0);

		// TODO Auto-generated method stub
		initCheck();

		// {
		// System.out.println("...");
		// UserInfo userInfo = new UserInfo("13000001011", "123456", "1232",
		// "LLH", 1, "1", "dislw@qq.com", "liu", 1, 21, "advdswd");
		// ApplictionManager.getInstance().userInfo = userInfo;
		// ApplictionManager.getInstance().getUser().setLogin(true);
		// ApplictionManager.getInstance().getUser().setUserInfo(userInfo);
		// Controller.MainHomePageActivity(AppStart.this);
		// }
	}

	private void initCheck() {
		// TODO Auto-generated method stub

		if (mAshtSettings.getIsAutoLogin()) {
			try {
				userPhoneNo = mAshtSettings.getUserId();
				userPwd = mAshtSettings.getUserPwd();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				loginActivity();
			}
			System.out.println(" u " + userPhoneNo + " p " + userPwd);
			if (userPhoneNo.equals("") | userPwd.equals("")) {
				return;
			}
			autoLogin();
			return;
		}
		loginActivity();

	}

	private void loginActivity() {
		// TODO Auto-generated method stub
		Controller.LoginActivity(AppStart.this);
	}

	private void autoLogin() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				UserInfo userInfo = new UserInfo();
				mAsht = AsHt.getInstance();
				try {
					userInfo = mAsht.login(userPhoneNo, userPwd);
				} catch (AsHtException e) {
					// TODO Auto-generated catch block
					loginActivity();
				}
				if (userInfo.success == true) {
					ApplictionManager.getInstance().userInfo = userInfo;
					ApplictionManager.getInstance().getUser().setLogin(true);
					ApplictionManager.getInstance().getUser()
							.setUserInfo(userInfo);
					Controller.MainHomePageActivity(AppStart.this);
				} else {
					loginActivity();
				}
				AppStart.this.finish();
			}
		}).start();
	}

	@Override
	protected void onResume() {
		JPushInterface.onResume(this);
		super.onResume();
	}

	@Override
	protected void onPause() {
		JPushInterface.onPause(this);
		super.onPause();
	}
}
=======
package com.asht.controller;

import android.app.Activity;
import android.os.Bundle;

import cn.jpush.android.api.JPushInterface;

import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.AshtSettings;
import com.asht.AsyncDataLoader;
import com.asht.R;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;
import com.asht.view.WaitingDialog;

public class AppStart extends Activity {
	AsyncDataLoader asyncDataLoader;
	private AshtSettings mAshtSettings;
	private String userPhoneNo = "";
	private String userPwd = "";
	private AsHt mAsht;
	public static boolean isForeground = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		AshtSettings.initailze(this);
		AsHt.initalize();
		mAshtSettings = AshtSettings.getInstance();
		WaitingDialog dialog = new WaitingDialog(this);
		initCheck();

	}

	private void initCheck() {
		// TODO Auto-generated method stub

		if (mAshtSettings.getIsAutoLogin()) {
			try {
				userPhoneNo = mAshtSettings.getUserId();
				userPwd = mAshtSettings.getUserPwd();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				loginActivity();
			}
			System.out.println(" u " + userPhoneNo + " p " + userPwd);
			if (userPhoneNo.equals("") | userPwd.equals("")) {
				return;
			}
			autoLogin();
			return;
		}
		loginActivity();

	}

	private void loginActivity() {
		// TODO Auto-generated method stub
		Controller.LoginActivity(AppStart.this);
		this.finish();
	}

	private void autoLogin() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				UserInfo userInfo = new UserInfo();
				mAsht = AsHt.getInstance();
				try {
					userInfo = mAsht.login(userPhoneNo, userPwd);
					// mAsht.sendVerificationCode("13541315803", null, 0);
				} catch (AsHtException e) {
					// TODO Auto-generated catch block
					loginActivity();
				}
				if (userInfo.success == true) {
					ApplictionManager.getInstance().userInfo = userInfo;
					ApplictionManager.getInstance().getUser().setLogin(true);
					ApplictionManager.getInstance().getUser()
							.setUserInfo(userInfo);
					Controller.MainHomePageActivity(AppStart.this);
				} else {
					loginActivity();
				}
				AppStart.this.finish();
			}
		}).start();
	}

	@Override
	protected void onResume() {
		JPushInterface.onResume(this);
		super.onResume();
	}

	@Override
	protected void onPause() {
		JPushInterface.onPause(this);
		super.onPause();
	}
}
>>>>>>> FETCH_HEAD
