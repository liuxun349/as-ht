package com.asht.controller;

import com.asht.R;
import com.asht.utl.ApplictionManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class AppStart extends Activity {

		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);	
			setContentView(R.layout.welcome);
			
			new Handler().postDelayed(new Runnable(){
				public void run(){
					if( !ApplictionManager.getInstance().getUser().isLogin()){
						Intent intent = new Intent(AppStart.this	, LoginActivity.class);
						startActivity(intent);
					}else{
						Intent intent = new Intent(AppStart.this	, MainActivity.class);
						startActivity(intent);
					}		
					AppStart.this.finish();
				}
			}, 1000);
		   }
	
}
