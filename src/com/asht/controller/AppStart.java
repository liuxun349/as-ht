package com.asht.controller;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;
import com.asht.utl.ToastUtils;
import com.asht.view.WaitingDialog;

public class AppStart extends Activity implements Callback {
	AsyncDataLoader asyncDataLoader;
	private AshtSettings mAshtSettings;
	private String userPhoneNo;
	private String userPwd;
	private AsHt mAsht;
	private WaitingDialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);

		summit();

		// AshtSettings.initailze(this);
		// AsHt.initalize();

		// mAshtSettings = AshtSettings.getInstance();
		// dialog = new WaitingDialog(this);

		// Button btn = (Button) findViewById(R.id.btn);
		// WaitingDialog dialog = new WaitingDialog(this);
		// initCheck();
	}

	private void summit() {

		new AsyncDataLoader(new Callback() {

			@Override
			public void onStartAsync() {

				AsHt asht = ApplictionManager.getInstance().getAsHt();
				UserInfo user = ApplictionManager.getInstance().getUserInfo();
				user = new UserInfo();
				user.setUserPhoneNo("13000001011");
				Resume resume = new Resume();
				String path = Environment.getExternalStorageDirectory()
						.getPath();
				File f = new File(path + "/DCIM/100ANDRO/qq.jpg");
				String path2 = f.getAbsolutePath();
				resume.setLocalRecordImageUrl(path2);
				try {
					boolean name = asht.uploadCaseToGroup(user, "142", resume);
					System.out.println(name);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				try {
				} catch (Exception e) {
					e.printStackTrace();
					Log.w("Record", e.toString());
				}
			}

			@Override
			public void onPrepareAsync() {

			}

			@Override
			public void onFinishAsync() {
			}
		}).execute();

	}

	private void initCheck() {
		// TODO Auto-generated method stub

		if (mAshtSettings.getIsAutoLogin()) {  
			System.out.println(" true ? " + mAshtSettings.getIsAutoLogin());
			userPhoneNo = mAshtSettings.getUserId();
			try {
				userPwd = mAshtSettings.getUserPwd();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				loginActivity();
			}
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
		asyncDataLoader.execute(null);
	}

	@Override
	public void onStartAsync() {
		// TODO Auto-generated method stub
		System.out.println("do it ? ..");
		UserInfo userInfo = new UserInfo();
		mAsht = AsHt.getInstance();
		try {
			userInfo = mAsht.login(userPhoneNo, userPwd);
		} catch (AsHtException e) {
			// TODO Auto-generated catch block
			loginActivity();
		}
		ApplictionManager.getInstance().userInfo = userInfo;
		Intent intent = new Intent(AppStart.this, MainActivity.class);
		startActivity(intent);
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
