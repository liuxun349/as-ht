package com.asht.controller;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.AshtSettings;
import com.asht.AsyncDataLoader;
import com.asht.AsyncDataLoader.Callback;
import com.asht.R;
import com.asht.model.Resume;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;
import com.asht.view.WaitingDialog;

public class AppStart extends Activity implements Callback {
	AsyncDataLoader asyncDataLoader;
	private AshtSettings mAshtSettings;
	private String userPhoneNo = "";
	private String userPwd = "";
	private AsHt mAsht;
	private WaitingDialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		AshtSettings.initailze(this);
		AsHt.initalize();
		mAshtSettings = AshtSettings.getInstance();
		dialog = new WaitingDialog(this);

		// WaitingDialog dialog = new WaitingDialog(this);
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
		Intent intent = new Intent(AppStart.this, LoginActivity.class);
		startActivity(intent);
	}

	private void autoLogin() {
		// TODO Auto-generated method stub
		asyncDataLoader = new AsyncDataLoader(this);
		asyncDataLoader.execute();
	}

	@Override
	public void onStartAsync() {
		// TODO Auto-generated method stub

		UserInfo userInfo = new UserInfo();
		mAsht = AsHt.getInstance();
		try {
			userInfo = mAsht.login(userPhoneNo, userPwd);
		} catch (AsHtException e) {
			// TODO Auto-generated catch block
			Controller.LoginActivity(AppStart.this);
		}
		if (userInfo.success == true) {
			ApplictionManager.getInstance().userInfo = userInfo;
			ApplictionManager.getInstance().getUser().setLogin(true);
			ApplictionManager.getInstance().getUser().setUserInfo(userInfo);
			Controller.MainHomePageActivity(AppStart.this);
		} else {
			Controller.LoginActivity(AppStart.this);
		}
	}

	@Override
	public void onPrepareAsync() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinishAsync() {
		// TODO Auto-generated method stub
		AppStart.this.finish();
	}
}
