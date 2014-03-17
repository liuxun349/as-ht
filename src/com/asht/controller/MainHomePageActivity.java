package com.asht.controller;

import com.asht.R;
import com.asht.adapter.MainGridItemAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.GridView;

public class MainHomePageActivity extends Activity {
	private GridView mGridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);
		DisplayMetrics dm = new DisplayMetrics();

		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		int spacing = (int) this.getResources().getDimension(
				R.dimen.grid_spacing);
		int width = (dm.widthPixels - spacing) / 2; // 当前分辨率 宽度
		int height = (int) (this.getResources()
				.getDimension(R.dimen.main_grid_height));
		mGridView = (GridView) findViewById(R.id.gridview_main);
		System.out.println("with " + width + " height " + height + " dm.width "
				+ dm.widthPixels + " dm.heig " + dm.heightPixels);
		MainGridItemAdapter adapter = new MainGridItemAdapter(this, width,
				height);
		mGridView.setAdapter(adapter);
		Controller.setNomePagTop(this, false, "互助抗癌");
		Controller.getInstance().registerMessageReceiver(this);
	}

	public void onclick(View v) {
		Intent intent = null;
		intent = new Intent();
		switch (v.getId()) {
		case R.id.test_add_cases:
			intent.setClass(getApplicationContext(), AddCasesActivity.class);
			break;
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

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Controller.getInstance().unregisterMessageReceiver(this);
		super.onDestroy();
	}
}
