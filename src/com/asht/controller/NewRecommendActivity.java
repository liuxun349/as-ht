package com.asht.controller;

import com.asht.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class NewRecommendActivity extends Activity implements OnItemSelectedListener {

	// 声明控件对象
	private Spinner spinner_documentType, spinner_yourIdentity, spinner_email;

	private ArrayAdapter adapter_documentType, adapter_yourIdentity,
			adapter_email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_recommend);

		// 根据控件的ID得到代表该控件的对象
		spinner_documentType = (Spinner) findViewById(R.id.spinner_documentType);
		spinner_yourIdentity = (Spinner) findViewById(R.id.spinner_yourIdentity);
		spinner_email = (Spinner) findViewById(R.id.spinner_email);

		// 初始化数据
		adapter_documentType = ArrayAdapter.createFromResource(this,
				R.array.documentType, android.R.layout.simple_spinner_item);
		adapter_yourIdentity = ArrayAdapter.createFromResource(this,
				R.array.yourIdentity, android.R.layout.simple_spinner_item);
		adapter_email = ArrayAdapter.createFromResource(this, R.array.email,
				android.R.layout.simple_spinner_item);

		// 设置下拉列表的风格
		adapter_documentType
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter_yourIdentity
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter_email
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		//将adapter添加到spinner中
		spinner_documentType.setAdapter(adapter_documentType);
		spinner_yourIdentity.setAdapter(adapter_yourIdentity);
		spinner_email.setAdapter(adapter_email);
		
		// 为spinner添加事件监听
		spinner_documentType.setOnItemSelectedListener(this);
		spinner_yourIdentity.setOnItemSelectedListener(this);
		spinner_email.setOnItemSelectedListener(this);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
}
