package com.asht.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import org.json.JSONObject;
import org.kobjects.base64.Base64;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import com.asht.R;
import com.asht.http.CasePictureDAO;
import com.asht.http.TransFileDAO;
import com.asht.http.UserBaseHandlerDAO;
import com.asht.model.Resume;
import com.asht.utl.ApplictionManager;
import com.asht.utl.AshtUtil;
import com.asht.utl.ConnCallback;
import com.asht.utl.Settings;

public class LoginActivity extends Activity {
	private EditText login_input_name, login_input_password;
	private Button btn_login, register;
	private CheckBox remember_user, display_passwd;
	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		getView();
		setLinsener();
		

	}

	private void getView() {
		login_input_name = (EditText) findViewById(R.id.login_input_name);
		login_input_password = (EditText) findViewById(R.id.login_input_password);
		btn_login = (Button) findViewById(R.id.btn_login);
//		remember_user = (CheckBox) findViewById(R.id.remember_user);
//		display_passwd = (CheckBox) findViewById(R.id.display_passwd);
		register = (Button) findViewById(R.id.login_to_register);
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
						System.out.println("返回："+result);
						Intent intent = new Intent(LoginActivity.this,
								MainActivity.class);
						startActivity(intent);
						LoginActivity.this.finish();
						System.out.println("login yes!");
					} else {
						System.out.println("login failed!");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
	}

	private void setLinsener() {

		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String userId = login_input_name.getText().toString().trim();
				String passwd = login_input_password.getText().toString()
						.trim();
				login(userId, passwd);

				if (AshtUtil.IsHandset(userId)) {
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
					System.out.println("保存。。。");
				} else {
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
							login_input_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);     
						    }   
						else {    
						    // 隐藏密码     
							login_input_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);    
						}    
					}
				});
		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this,
						RegisterFirstActivity.class);
				startActivity(intent);

			}
		});
	}

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
}
