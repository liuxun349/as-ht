package com.asht.controller;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.asht.R;
import com.asht.dao.UserBaseHandlerDAO;
import com.asht.utl.ApplictionManager;
import com.asht.utl.AshtUtil;
import com.asht.utl.ConnCallback;
import com.asht.utl.Settings;

public class LoginActivity extends Activity {
	private EditText login_input_name, login_input_password;
	private Button btn_login;
	private CheckBox remember_user, display_passwd;
	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		getView();
		// 登录
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String userId = login_input_name.getText().toString().trim();
				String passwd = login_input_password.getText().toString()
						.trim();
				login(userId, passwd);

				if (AshtUtil.IsHandset(userId)) {
									}else{
					System.out.println("格式错误");
				}

			}
		});
	}

	private void getView() {
		login_input_name = (EditText) findViewById(R.id.login_input_name);
		login_input_password = (EditText) findViewById(R.id.login_input_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		remember_user = (CheckBox) findViewById(R.id.remember_user);
		display_passwd = (CheckBox) findViewById(R.id.display_passwd);
	}

	/**
	 * 登录
	 * 
	 * @param userId
	 * @param passwd
	 */
	private void login(String userId, String passwd) {
		UserBaseHandlerDAO mBaseHandlerDAO = new UserBaseHandlerDAO();
		mBaseHandlerDAO.login(userId, passwd, new ConnCallback() {

			@Override
			public void connCode(int code, String result) {
				// TODO Auto-generated method stub
				try {
					JSONObject json = new JSONObject(result);
					if (json.getInt(Settings.RETURN_CODE) == Settings.RETURN_CODE_ACCESS) {
						ApplictionManager.getInstance().getUser()
								.setIsLogin(true);
						Intent intent = new Intent(LoginActivity.this	, MainActivity.class);
						startActivity(intent);
						LoginActivity.this.finish();
						System.out.println("login yes!");
					} else {
						System.out.println("login failed!");
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("异常");
				}
			}
		});
	}
}
