package com.asht.controller;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TabHost;

import cn.jpush.android.api.JPushInterface;

import com.asht.R;
import com.asht.utl.ApplictionManager;
import com.asht.utl.ExampleUtil;

public class MainActivity extends TabActivity {
	TabHost tabHost;
	private RadioButton main_tab_myacount, main_tab_message, main_tab_home,
			main_tab_safety, main_tab_more;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initTab(savedInstanceState);
		init();
		ApplictionManager.getInstance().addActivity(this);
		Controller.getInstance().registerMessageReceiver(MainActivity.this); // used
																				// for
		// receive msg

	}

	public void init() {
		main_tab_myacount = (RadioButton) findViewById(R.id.main_tab_home);
		main_tab_message = (RadioButton) findViewById(R.id.main_tab_catagory);
		main_tab_home = (RadioButton) findViewById(R.id.main_tab_car);
		main_tab_safety = (RadioButton) findViewById(R.id.main_tab_buy);
		main_tab_more = (RadioButton) findViewById(R.id.main_tab_more);
		main_tab_myacount.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("myacount");

			}
		});

		main_tab_message.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("message");

			}
		});
		main_tab_home.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("home");

			}
		});
		// main_tab_safety.setOnClickListener(new OnClickListener() {
		//
		// public void onClick(View view) {
		// tabHost.setCurrentTabByTag("safety");
		//
		// }
		// });
		main_tab_more.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("more");

			}
		});
	}

	public void initTab(Bundle bundle) {
		tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec("myacount").setIndicator("myacount")
				.setContent(new Intent(this, MyAcountActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("message").setIndicator("message")
				.setContent(new Intent(this, MainMessageActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("home").setIndicator("home")
				.setContent(new Intent(this, MainHomePageActivity.class)));
		// tabHost.addTab(tabHost.newTabSpec("safety").setIndicator("safety")
		// .setContent(new Intent(this, MainSafetyCenterActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("more").setIndicator("more")
				.setContent(new Intent(this, MainMoreActivity.class)));
		if (bundle == null) {
			tabHost.setCurrentTabByTag("home");
		} else {
			tabHost.setCurrentTabByTag(bundle.getString("page"));
		}
	}

	public boolean dispatchKeyEvent(KeyEvent event) {
		int keyCode = event.getKeyCode();
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (event.getRepeatCount() == 0) {
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						MainActivity.this);
				alertDialog.setTitle(MainActivity.this
						.getString(R.string.app_name));
				alertDialog.setPositiveButton(
						MainActivity.this.getString(R.string.text_sure),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								ApplictionManager.getInstance().exit();
							}
						});
				alertDialog.setNegativeButton(
						MainActivity.this.getString(R.string.cancel),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
							}
						});
				alertDialog.show();
			}
		}
		return super.dispatchKeyEvent(event);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Controller.getInstance().unregisterMessageReceiver(this);
	}

	@Override
	protected void onResume() {
		JPushInterface.onResume(this);
		super.onResume();
	}

	@Override
	protected void onPause() {
		JPushInterface.onPause(this);
		super.onPause();
	}

}