package com.asht.controller;

import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.AshtSettings;
import com.asht.R;
import com.asht.utl.ApplictionManager;
import com.asht.utl.AshtUtil;
import com.asht.utl.ExampleUtil;
import com.asht.view.WaitingDialog;

public class LoginActivity extends Activity {
	private EditText login_input_name, login_input_password;
	private Button btn_login, register;
	private CheckBox remember_user, display_passwd;
	private String userName, userPwd;
	private WaitingDialog mDialog;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.arg1 == 0) {
				mDialog.dismiss();
				Toast.makeText(getApplicationContext(), (String) msg.obj, 0)
						.show();
			} else if (msg.arg1 == 1) {
				mDialog.dismiss();
				loginSuccess();
			}
		};
	};
	private Thread loginThread;

	private Thread login() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg = new Message();
				try {
					ApplictionManager.getInstance().userInfo = AsHt
							.getInstance().login(userName, userPwd);
				} catch (AsHtException e) {
					// TODO Auto-generated catch block
					System.out.println(" " + e.getMessage());
					msg.arg1 = 0;
					msg.obj = e.getMessage();
					mHandler.sendMessage(msg);
					return;
				}
				msg.arg1 = 1;
				mHandler.sendMessage(msg);
			}
		});
		t1.start();
		return t1;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i("AsHt", "LoginActivity");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		getView();
		setLinsener();
		mDialog = new WaitingDialog(this);
	}

	private void getView() {
		login_input_name = (EditText) findViewById(R.id.login_input_name);
		login_input_password = (EditText) findViewById(R.id.login_input_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		remember_user = (CheckBox) findViewById(R.id.front_autoSignIn);
		if (AshtSettings.getInstance().getIsAutoLogin()) {
			remember_user.setChecked(true);
		} else {
			remember_user.setChecked(false);
		}
		display_passwd = (CheckBox) findViewById(R.id.front_remenberMe);
		register = (Button) findViewById(R.id.login_to_register);
	}

	private void setLinsener() {

		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				userName = login_input_name.getText().toString().trim();
				userPwd = login_input_password.getText().toString().trim();
				if (AshtUtil.IsHandset(userName)) {
					mDialog.show();
					loginThread = login();
				} else {
					System.out.println("格式错误");
				}

			}
		});
		remember_user.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					AshtSettings.getInstance().setIsAutoLogin(true);

					System.out.println("保存。。。");
				} else {
					AshtSettings.getInstance().setIsAutoLogin(false);
					System.out.println("不保存");
				}
			}
		});
		display_passwd
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							// 显示密码
							login_input_password
									.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
						} else {
							// 隐藏密码
							login_input_password
									.setInputType(InputType.TYPE_CLASS_TEXT
											| InputType.TYPE_TEXT_VARIATION_PASSWORD);
						}
					}
				});
		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Controller.RegisterActivity(LoginActivity.this);
				LoginActivity.this.finish();
			}
		});
	}

	private void loginSuccess() {
		ApplictionManager.getInstance().getUser().setIsLogin(true);
		ApplictionManager.getInstance().getUser()
				.setUserInfo(ApplictionManager.getInstance().userInfo);
		try {
			AshtSettings.getInstance().setUserId(userName);
			AshtSettings.getInstance().setUserPwd(userPwd);
			JPushInterface.setAliasAndTags(getApplicationContext(), userName,
					null, mAliasCallback);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Controller.MainHomePageActivity(this);
		finish();
	}

	// 退出
	public boolean dispatchKeyEvent(KeyEvent event) {
		int keyCode = event.getKeyCode();
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (event.getRepeatCount() == 0) {
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						LoginActivity.this);
				alertDialog.setTitle(LoginActivity.this
						.getString(R.string.app_name));
				alertDialog.setPositiveButton(
						LoginActivity.this.getString(R.string.text_sure),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								ApplictionManager.getInstance().exit();
							}
						});
				alertDialog.setNegativeButton(
						LoginActivity.this.getString(R.string.cancel),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
							}
						});
				alertDialog.show();
			}
		}
		return super.dispatchKeyEvent(event);
	}

	private static final int MSG_SET_ALIAS = 1001;
	private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

		@Override
		public void gotResult(int code, String alias, Set<String> tags) {
			String logs;
			switch (code) {
			case 0:
				logs = "Set tag and alias success";
				System.out.println(logs);
				break;

			case 6002:
				logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
				System.out.println(logs);
				if (ExampleUtil.isConnected(getApplicationContext())) {
					mHandler.sendMessageDelayed(
							mHandler.obtainMessage(MSG_SET_ALIAS, alias),
							1000 * 60);
				} else {
					System.out.println("No network");
				}
				break;

			default:
				logs = "Failed with errorCode = " + code;
				System.out.println(logs);
			}

			ExampleUtil.showToast(logs, getApplicationContext());
		}

	};

}
