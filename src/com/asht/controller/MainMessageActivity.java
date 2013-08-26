package com.asht.controller;

import com.asht.R;
import com.asht.view.WaitingDialog;

import android.app.Activity;
import android.os.Bundle;

public class MainMessageActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.foundfile404);
		new WaitingDialog(this).show();
	}
}
