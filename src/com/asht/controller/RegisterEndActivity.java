package com.asht.controller;

import org.json.JSONObject;

import android.app.Activity;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.asht.R;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;
import com.asht.utl.ConnCallback;
import com.asht.utl.Settings;

public class RegisterEndActivity extends Activity{
	private Button post;
	private EditText login_pwd, login_pwd_agin, pay_pwd, pay_pwd_agin, securityA_1,securityA_2;
	private TextView infoView_login, infoView_pay;
	private Spinner securityQ_1,securityQ_2;
	private ArrayAdapter adapter1,adapter2;
	private String loginPwd,payPwd,securityA1,securityA2;
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
				getDataFromWidgt();
//				new UserBaseHandlerDAO().regist(ApplictionManager.getInstance().userInfo, new ConnCallback() {
//					
//					@Override
//					public void connCode(int code, String result) {
//						// TODO Auto-generated method stub
//						System.out.println( code+" == ");
//						try {
//							JSONObject rs = new JSONObject(result);
//							if(rs.getInt(Settings.RETURN_CODE) == Settings.RETURN_CODE_ACCESS){
//								System.out.println("注册成功");
//							}else{
//								System.out.println("失败");
//							}
//						} catch (Exception e) {
//							// TODO: handle exception
//						}
//					}
//				}) ;
			}
		});
	}
	private void getDataFromWidgt(){
		int Q1 = securityQ_1.getSelectedItemPosition();
		int Q2 = securityQ_2.getSelectedItemPosition();
		if( checkLoginPwd() && checkAnswerIsNull() && checkPayPwd()){
			UserInfo userInfo = ApplictionManager.getInstance().userInfo;
			userInfo.setSecutrityQA(Q1, securityA1, Q2, securityA2);
			userInfo.setUserLoginPwd(loginPwd);
			userInfo.setUserPayPwd(payPwd);
		}else{
			System.out.println("输入有误....");
		}
	}
	private void setSpinner(){
		adapter1 = ArrayAdapter.createFromResource(this, R.array.securityQ, android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		securityQ_1.setAdapter(adapter1);
		securityQ_1.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
		securityQ_2.setAdapter(adapter1);
		securityQ_2.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
	}
	/**
	 * 实现选择接口
	 * @author Administrator
	 *
	 */
	private class SpinnerXMLSelectedListener implements OnItemSelectedListener{


		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
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
		infoView_login = (TextView) findViewById(R.id.wrongInfo_login);
		infoView_pay = (TextView) findViewById(R.id.wrongInfo_pay);
		
	}
	private boolean checkLoginPwd(){
		String pwd1 = login_pwd.getText().toString().trim();
		String pwd2 = login_pwd_agin.getText().toString().trim();
		if( !pwd1.equals(pwd2)){
			infoView_pay.setVisibility(View.VISIBLE);
			return false;
		}
		if( pwd1.length() < 6 | pwd1.length() > 16 ){
			infoView_pay.setVisibility(View.VISIBLE);
			return false;
		}			
		loginPwd = pwd1;
		return true;
	}
	private boolean checkPayPwd(){
		String pwd1 = pay_pwd.getText().toString().trim();
		String pwd2 = pay_pwd_agin.getText().toString().trim();
		if( !pwd1.equals(pwd2)){
			infoView_pay.setVisibility(View.VISIBLE);
			return false;
		}
		if( pwd1.length() < 6 | pwd1.length() > 16 ){
			infoView_pay.setVisibility(View.VISIBLE);
			return false;
		}			
		payPwd = pwd1;
		return true;
	}
	private boolean checkAnswerIsNull(){
		String answer1 = securityA_1.getText().toString().trim();
		String answer2 = securityA_2.getText().toString().trim();
		if( answer1.equals("") || answer2.equals("")){
			return false;
		}
		securityA1 = answer1;
		securityA2 = answer2;
		return true;
	}
}
