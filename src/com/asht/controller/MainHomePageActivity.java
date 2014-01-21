package com.asht.controller;

import com.asht.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class MainHomePageActivity extends Activity {
	private GridView mGridView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);
	}
}
