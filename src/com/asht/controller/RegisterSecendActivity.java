package com.asht.controller;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.asht.R;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;
import com.asht.utl.AshtUtil;

public class RegisterSecendActivity extends Activity {
	private Button btn_next;
	private EditText etx_nickName, etx_turename, etx_certificateType,
			etx_certificationNo, etx_roleId, etx_mailePre, etx_mailelast;
	private String nickName,tureName,certificateNo,email;
	private int certificateType;	//证件类型(编号表示)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_secend);
		init();
		//下一步
		btn_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean isOk = checking();
				if( isOk ){
					UserInfo userInfo = ApplictionManager.getInstance().userInfo;
					userInfo.setUserNickName(nickName);
					userInfo.setUserTrueName(tureName);
					userInfo.setUserCertificateNo(certificateNo);
					userInfo.setUserCertificateType(certificateType);
					userInfo.setUserEmail(email);
					
					System.out.println(" ===> "+userInfo.toJson().toString());
					Intent intent = new Intent(RegisterSecendActivity.this, RegisterEndActivity.class);
					startActivity(intent);
				}
				
			}
		});
	}
	/**
	 * 检查输入是否都合法
	 * @return
	 */
	private boolean checking(){
		nickName = etx_nickName.getText().toString().trim();
		tureName = etx_turename.getText().toString().trim();
		certificateNo = etx_certificationNo.getText().toString().trim();
		email = etx_mailePre.getText().toString().trim() + "@" +etx_mailelast.getText().toString().trim();
		certificateType = 0;
		
		if( !AshtUtil.isEmail(email)){
			System.out.println("密码格式不对");
			return false;
		}else if( !AshtUtil.IsChinese(tureName)){
			System.out.println("真名格式不对");
			return false;
		}else if( !AshtUtil.IsIDcard(certificateNo)){
			System.out.println("身份证格式不对");
			return false;
		}
		return true;
	}
	
	private void init(){
		btn_next = (Button) findViewById(R.id.btn_next);
		etx_certificateType = (EditText) findViewById(R.id.etx_certificateType);
		etx_certificationNo = (EditText) findViewById(R.id.etx_certificateNo);
		etx_mailelast = (EditText) findViewById(R.id.etx_mailelast);
		etx_mailePre = (EditText) findViewById(R.id.etx_mailelast);
		etx_roleId = (EditText) findViewById(R.id.etx_roleId);
		etx_nickName = (EditText) findViewById(R.id.etx_nickname);
		etx_turename = (EditText) findViewById(R.id.etx_turename);
		
	}
}
