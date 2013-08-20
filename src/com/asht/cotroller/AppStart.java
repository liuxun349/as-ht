package com.asht.cotroller;

import com.asht.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class AppStart extends Activity {
	public class Appstart extends Activity{

		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);	
			setContentView(R.layout.welcome);
			
			new Handler().postDelayed(new Runnable(){
				public void run(){
					Intent intent = new Intent (Appstart.this,MainActivity.class);			
					startActivity(intent);			
					Appstart.this.finish();
				}
			}, 1000);
		   }
	}
}
