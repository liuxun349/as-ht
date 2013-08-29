package com.asht.controller;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.asht.R;
import com.asht.dao.UserBaseHandlerDAO;
import com.asht.utl.ConnCallback;
import com.asht.utl.Settings;
import com.asht.utl.ToastUtils;

public class EditLoginPasswordActivity extends Activity implements
		OnClickListener {

	EditText et_oldLoginPwd, et_newLoginPwd, et_newLoginPwd2;
	Button btn_submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.edit_login_passwd);
		findViewById(R.id.tv_title_back).setOnClickListener(this);
		et_oldLoginPwd = (EditText) findViewById(R.id.et_oldLoginPwd);
		et_newLoginPwd = (EditText) findViewById(R.id.et_newLoginPwd);
		et_newLoginPwd2 = (EditText) findViewById(R.id.et_newLoginPwd2);
		btn_submit = (Button) findViewById(R.id.btn_submit);
		btn_submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_title_back:
			setResult(RESULT_CANCELED);
			finish();
			break;
		case R.id.btn_submit:
			submitNewsPwd();
			break;
		default:
			break;
		}

	}

	private void submitNewsPwd() {
		String oldPwd = et_oldLoginPwd.getText().toString();
		String newPwd = et_newLoginPwd.getText().toString();
		UserBaseHandlerDAO.modifyLoginPassword("100001", oldPwd, newPwd,
				new ConnCallback() {

					@Override
					public void connCode(int code, String result) {
						// TODO Auto-generated method stub
						try {
							if (code == 1) {
								JSONObject json = new JSONObject(result);
								if (json.getInt(Settings.RETURN_CODE) == Settings.RETURN_CODE_ACCESS) {
									ToastUtils.getInit(getApplicationContext())
											.show("修改登陆密码成功");
									System.out.println("修改登陆密码成功");
									setResult(RESULT_CANCELED);
									finish();
								} else {
									ToastUtils.getInit(getApplicationContext())
											.show("修改登陆密码失败");
									System.out.println("修改登陆密码失败");
								}
							}else{
								ToastUtils.getInit(getApplicationContext()).show("正在请求中 请稍后。。。");
							}

						} catch (Exception e) {
						}
					}

				});
	}
}
