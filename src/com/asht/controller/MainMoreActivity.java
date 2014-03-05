<<<<<<< HEAD
package com.asht.controller;

import com.asht.AshtSettings;
import com.asht.R;
import com.asht.utl.ApplictionManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MainMoreActivity extends Activity implements OnClickListener {
	private LinearLayout exitCurrentAccount;
	private View advice;
	private View about;
	private View update;
	private View setting;
	private View securityCenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more);
		getView();
		setListener();
	}

	private void getView() {
		exitCurrentAccount = (LinearLayout) findViewById(R.id.accountExit);
		advice = findViewById(R.id.more_advice);
		about = findViewById(R.id.more_about);
		update = findViewById(R.id.more_update);
		setting = findViewById(R.id.more_setting);
		securityCenter = findViewById(R.id.more_security_center);
	}

	private void setListener() {
		exitCurrentAccount.setOnClickListener(this);
		advice.setOnClickListener(this);
		setting.setOnClickListener(this);
		securityCenter.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.accountExit:
			Log.d("MainMore", "退出账号");
			ApplictionManager.getInstance().getUser().exit();
			Controller.LoginActivity(this);
			break;

		case R.id.more_advice:
			Log.e("error", "建议警告");
			Controller.Advice(this, null);
			break;
		case R.id.more_about:
			break;
		case R.id.more_setting:
			break;
		case R.id.more_security_center:
			Controller.SecurityCenter(this);
			break;
		}
	}
}
=======
package com.asht.controller;

import com.asht.AshtSettings;
import com.asht.R;
import com.asht.utl.ApplictionManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MainMoreActivity extends Activity implements OnClickListener {
	private LinearLayout exitCurrentAccount;
	private View advice;
	private View about;
	private View update;
	private View setting;
	private View securityCenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more);
		getView();
		setListener();
	}

	private void getView() {
		exitCurrentAccount = (LinearLayout) findViewById(R.id.accountExit);
		advice = findViewById(R.id.more_advice);
		about = findViewById(R.id.more_about);
		update = findViewById(R.id.more_update);
		setting = findViewById(R.id.more_setting);
		securityCenter = findViewById(R.id.more_security_center);
	}

	private void setListener() {
		exitCurrentAccount.setOnClickListener(this);
		advice.setOnClickListener(this);
		setting.setOnClickListener(this);
		securityCenter.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.accountExit:
			Log.d("MainMore", "退出账号");
			ApplictionManager.getInstance().getUser().exit();
			Controller.LoginActivity(this);
			break;

		case R.id.more_advice:
			Controller.Advice(this, null);
			break;
		case R.id.more_about:
			break;
		case R.id.more_setting:
			break;
		case R.id.more_security_center:
			Controller.SecurityCenter(this);
			break;
		}
	}
}
>>>>>>> 2c11daef6a591655fb0aa1dd0439ff8866142c20
