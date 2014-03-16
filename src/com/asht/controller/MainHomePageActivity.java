package com.asht.controller;

import com.asht.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

public class MainHomePageActivity extends Activity {
	private GridView mGridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);
		Controller.setNomePagTop(this, false, "互助抗癌");
	}

	public void onclick(View v) {
		Intent intent = null;
		intent = new Intent();
		switch (v.getId()) {
		case R.id.test_cases:
			intent.setClass(getApplicationContext(), MyCasesActivity.class);
			break;
		case R.id.test_add_tuijian:
			intent.setClass(getApplicationContext(), NewRecommendActivity.class);
			break;
		case R.id.test_tuijian:
			intent.setClass(getApplicationContext(),
					MutualRecommendationActivity.class);
			break;

		default:
			break;
		}
		startActivity(intent);
	}
}
