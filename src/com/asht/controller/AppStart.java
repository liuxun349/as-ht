package com.asht.controller;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.AshtSettings;
import com.asht.AsyncDataLoader;
import com.asht.AsyncDataLoader.Callback;
import com.asht.R;
import com.asht.model.Recommend;
import com.asht.model.Record;
import com.asht.model.Resume;
import com.asht.model.User;
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

		Button btn = (Button) findViewById(R.id.btn);
		WaitingDialog dialog = new WaitingDialog(this);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
<<<<<<< HEAD
//				initCheck();
				UserInfo user = new UserInfo();
				user.setUserPhoneNo("13000001011");
				try {
					Resume resume = new Resume();
					AsHt.getInstance().getCaseImageFromGroup(user, "142", 1);
				} catch (AsHtException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
=======
				AsHt asht = ApplictionManager.getInstance().getAsHt();
				UserInfo user = ApplictionManager.getInstance().getUserInfo();
				user = new UserInfo();
				try {
					// asht.addRecordGroup(user, "新建病例");
					// Resume resume = new Resume();
					// resume.setLocalRecordImageUrl("/mnt/sdcard/download/timg.jpeg");
					// asht.uploadCaseToGroup(user, "123", resume);
					// asht.deleteRecordGroup(user, "122");
					// asht.deleteCaseFromGroup(user, "123",
					// "FILESPACE/MEDICALRECORD_GROUP/3_20131227132700424/20140101132003842.jpg");
					// records = asht.getRecordGroup(user, true,
					// "2013-12-25 20:06:15.0");
					// System.out.println(" size: "+records.size());
					// List<Resume> resumes = asht.getAllCaseFromGroup(user,
					// "123");
					//
					// System.out.println(" size2: "+resumes.size());
					user = new UserInfo("13000000001", "123456", "llh", "对了",
							1, "510203100103203042", "dfa@qq.com", "LLH",1,
							20, "四川");

					asht.regist(user);
					
				} catch (AsHtException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.w("Record", e.toString());
>>>>>>> 75b8d38929e96f51c7affae9e408f4402fe8289f
				}
			}
		}, 3000);
	}

	private void initCheck() {
		// TODO Auto-generated method stub

		if (mAshtSettings.getIsAutoLogin()) {
			System.out.println(" true ? " + mAshtSettings.getIsAutoLogin());

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
			}

	private void autoLogin() {
		// TODO Auto-generated method stub
		asyncDataLoader = new AsyncDataLoader(this);
		asyncDataLoader.execute(null);
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
