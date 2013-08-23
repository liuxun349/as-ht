package com.asht.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.asht.R;

public class RegisterEndActivity extends Activity{
	private Button post;
	private EditText login_pwd, login_pwd_agin, pay_pwd, pay_pwd_agin, securityA_1,securityA_2;
	private Spinner securityQ_1,securityQ_2;
	private ArrayAdapter adapter1,adapter2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_end);
		init();
		setSpinner();
		
		post.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	private void setSpinner(){
		adapter1 = ArrayAdapter.createFromResource(this, R.array.securityQ, android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		securityQ_1.setAdapter(adapter1);
		securityQ_1.setOnItemClickListener(new SpinnerXMLSelectedListener());
		securityQ_2.setAdapter(adapter1);
		securityQ_2.setOnItemClickListener(new SpinnerXMLSelectedListener());
	}
	/**
	 * 实现选择接口
	 * @author Administrator
	 *
	 */
	private class SpinnerXMLSelectedListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private void init(){
		post = (Button) findViewById(R.id.register_end);
		login_pwd = (EditText) findViewById(R.id.login_pwd);
		login_pwd_agin = (EditText) findViewById(R.id.login_pwd_agin);
		pay_pwd = (EditText) findViewById(R.id.pay_pwd);
		pay_pwd_agin = (EditText) findViewById(R.id.pay_pwd_agin);
		securityA_1 = (EditText) findViewById(R.id.securityA_1);
		securityA_2 = (EditText) findViewById(R.id.securityA_2);
		securityQ_1 = (Spinner) findViewById(R.id.securityQ_1);
		securityQ_2 = (Spinner) findViewById(R.id.securityQ_2);
		
	}
	private boolean checkLoginPwd(){
		String pwd1 = login_pwd.getText().toString().trim();
		String pwd2 = login_pwd_agin.getText().toString().trim();
		if( !pwd1.equals(pwd2)){
			return false;
		}
		if( pwd1.length() < 6 | pwd1.length() > 16 ){
			return false;
		}			
		return true;
	}
	private boolean checkPayPwd(){
		String pwd1 = pay_pwd.getText().toString().trim();
		String pwd2 = pay_pwd_agin.getText().toString().trim();
		if( !pwd1.equals(pwd2)){
			return false;
		}
		if( pwd1.length() < 6 | pwd1.length() > 16 ){
			return false;
		}			
		return true;
	}
}
