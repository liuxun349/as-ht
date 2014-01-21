package com.asht.controller;

import android.app.Activity;
import android.app.Dialog;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.R;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;
import com.asht.utl.Settings;
import com.asht.view.WaitingDialog;


public class RegisterFirstActivity extends Activity implements OnClickListener{

	private static final int SUCCESS = 1;
	private static final int FAIL = 2;
	
	private EditText phoneNum;
	private Button getcheckNum;
	private EditText checkNum;
	private Button next;
	private UserInfo userInfo;
	private WaitingDialog waitingDialog;
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			waitingDialog.dismiss();
			if(msg.arg1 == SUCCESS){
				
			}else{
				
			}
		};
	};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_first);
		init();

		//获得手机号，并发送验证码
//		getcheckNum.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				String phoneId = phoneNum.getText().toString().trim();
//
//				UserBaseHandlerDAO mBaseHandlerDAO = new UserBaseHandlerDAO();
////				mBaseHandlerDAO.requestSendMobileVerficationCode(phoneId);
//
//			}
//		});

		//验证手机号与手机验证码
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("...................");
				String phoneId = phoneNum.getText().toString().trim();
				String cheknumber = checkNum.getText().toString().trim();
				if (!(phoneId.equals("") && cheknumber.equals(""))) {
//					UserBaseHandlerDAO mBaseHandlerDAO = new UserBaseHandlerDAO();
					// 验证手机号与手机验证码是否相同，未完成此功能
					//if( ... )
						userInfo = new UserInfo();
						userInfo.setUserPhoneNo(phoneId);
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
		waitingDialog = new WaitingDialog(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0 ==  getcheckNum){
			waitingDialog.show();
			sendRequest();
		}
	}
	public void sendRequest(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String phoneNo = phoneNum.getText().toString().trim();
				boolean isSuccess = false;
				try {
					isSuccess = AsHt.getInstance().sendVerificationCode(phoneNo,null,Settings.REASON_TYPE_REGIST);
				} catch (AsHtException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Message msg = new Message();
				if(isSuccess){
					msg.arg1 = SUCCESS;
					mHandler.sendMessage(msg);
				}else{
					msg.arg1 = FAIL;
					mHandler.sendMessage(msg);
				}
			}
		}).start();
	}
}
