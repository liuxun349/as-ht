package com.asht.controller;

import com.asht.R;
import com.asht.utl.ApplictionManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * 我的账户
 * 
 * @author Administrator
 *
 */
public class MyAcountActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		if( !ApplictionManager.getInstance().getUser().isLogin()){
			Intent intent = new Intent(this	, RegisterFirstActivity.class);
			startActivity(intent);
		}
	}
}
