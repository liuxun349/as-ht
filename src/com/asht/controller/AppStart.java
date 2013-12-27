package com.asht.controller;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.AsyncDataLoader;
import com.asht.AsyncDataLoader.Callback;
import com.asht.R;
import com.asht.model.Record;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;

public class AppStart extends Activity {
	AsyncDataLoader asyncDataLoader;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		Button btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				asyncDataLoader.execute();
			}
		});
		asyncDataLoader = new AsyncDataLoader(new Callback() {
			private List<Record> records;

			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				System.out.println(" in ...");
				AsHt asht = ApplictionManager.getInstance().getAsHt();
				UserInfo user = ApplictionManager.getInstance().getUserInfo();
				user = new UserInfo();
				user.setUserId("13000000000");
				try {
					records = asht.getRecordGroup(user, false,"2013-12-25 20:06:15.0" );
//					asht.getRecordGroup(user, true, null);
					asht.getAllCaseFromGroup(user, "105");
				} catch (AsHtException e) { 
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.w("Record", e.toString());
				}

			}

			@Override
			public void onPrepare() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub

			}
		});

		// new Handler().postDelayed(new Runnable(){
		// public void run(){
		// if( !ApplictionManager.getInstance().getUser().isLogin()){
		// Intent intent = new Intent(AppStart.this , LoginActivity.class);
		// startActivity(intent);
		// }else{
		// Intent intent = new Intent(AppStart.this , MainActivity.class);
		// startActivity(intent);
		// }
		// AppStart.this.finish();
		// }
		// }, 1000);
	}

}
