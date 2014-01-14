package com.asht.controller;

import com.asht.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_test);
	}

	public void onclick(View v) {
		Intent intent = null;
		intent = new Intent();
		switch (v.getId()) {
		case R.id.test_add_case:
			intent.setClass(getApplicationContext(), AppStart.class);
			break;
		case R.id.test_add_cases:
			intent.setClass(getApplicationContext(), AddCasesActivity.class);
			break;
		case R.id.test_case:
			intent.setClass(getApplicationContext(),
					MyCasesSingleActivity.class);
			break;
		case R.id.test_cases:
			intent.setClass(getApplicationContext(), MyCasesActivity.class);
			break;

		default:
			break;
		}
		startActivity(intent);
	}

}
