package com.asht.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.asht.R;
import com.asht.dao.UserBaseHandlerDAO;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;


public class RegisterFirstActivity extends Activity {

	private EditText phoneNum;
	private Button getcheckNum;
	private EditText checkNum;
	private Button next;
	private UserInfo userInfo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_first);
		init();

		//获得手机号，并发送验证码
		getcheckNum.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String phoneId = phoneNum.getText().toString().trim();

				UserBaseHandlerDAO mBaseHandlerDAO = new UserBaseHandlerDAO();
//				mBaseHandlerDAO.requestSendMobileVerficationCode(phoneId);

			}
		});

		//验证手机号与手机验证码
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("...................");
				String phoneId = phoneNum.getText().toString().trim();
				String cheknumber = checkNum.getText().toString().trim();
				System.out.println("===?");
				if (!(phoneId.equals("") && cheknumber.equals(""))) {
					UserBaseHandlerDAO mBaseHandlerDAO = new UserBaseHandlerDAO();
					// 验证手机号与手机验证码是否相同，未完成此功能
					//if( ... )
						userInfo = new UserInfo();
						userInfo.setUserId(phoneId);
						ApplictionManager.getInstance().userInfo = userInfo;
						Intent intent = new Intent(RegisterFirstActivity.this,RegisterSecendActivity.class);
						startActivity(intent);
				}
			}

		});

	}  
	
	private void init() {
		userInfo = new UserInfo();
		phoneNum = (EditText) findViewById(R.id.register_phone);
		getcheckNum = (Button) findViewById(R.id.get_check_number);
		checkNum = (EditText) findViewById(R.id.register_check_number);
		next = (Button) findViewById(R.id.register_frist_next);
	}

}
