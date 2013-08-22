package com.asht.cotroller;

import android.app.Activity;
import android.content.Intent;
import android.database.DatabaseUtils;
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

		getcheckNum.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String phoneId = phoneNum.getText().toString().trim();

				UserBaseHandlerDAO mBaseHandlerDAO = new UserBaseHandlerDAO();
				mBaseHandlerDAO.requestSendMobileVerficationCode(phoneId);

			}
		});

		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String phoneId = phoneNum.getText().toString().trim();
				String cheknumber = checkNum.getText().toString().trim();
				if (phoneId.equals(null) && cheknumber.equals(null)) {
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
		getcheckNum = (Button) findViewById(R.id.register_next);
		checkNum = (EditText) findViewById(R.id.register_check_number);
	}

}
