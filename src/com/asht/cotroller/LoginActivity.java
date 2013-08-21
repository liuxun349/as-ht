package com.asht.cotroller;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.asht.R;
import com.asht.dao.UserBaseHandlerDAO;
import com.asht.utl.ApplictionManager;
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

		// 登录
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String userId = login_input_name.getText().toString().trim();
				String passwd = login_input_password.getText().toString()
						.trim();
				
				login(userId, passwd);
				
			}
		});
	}

	private void init() {
		login_input_name = (EditText) findViewById(R.id.login_input_name);
		login_input_password = (EditText) findViewById(R.id.login_input_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		remember_user = (CheckBox) findViewById(R.id.remember_user);
		display_passwd = (CheckBox) findViewById(R.id.display_passwd);
	}
	/**
	 * 登录
	 * @param userId
	 * @param passwd
	 */
	private void login(String userId,String passwd){
		UserBaseHandlerDAO mBaseHandlerDAO = new UserBaseHandlerDAO();
		JSONObject result = mBaseHandlerDAO.login(userId, passwd);
		try {
			if( result.getInt(Settings.RETURN_CODE) == Settings.RETURN_CODE_ACCESS){
				ApplictionManager.getInstance().getUser().setIsLogin(true);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
