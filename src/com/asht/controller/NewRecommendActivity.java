package com.asht.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.asht.AsHt;
import com.asht.AsyncDataLoader;
import com.asht.R;
import com.asht.AsyncDataLoader.Callback;
import com.asht.model.Recommend;
import com.asht.model.Record;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;

public class NewRecommendActivity extends Activity implements
		OnItemSelectedListener, OnClickListener {

	// 声明控件对象
	private Spinner spinner_documentType, spinner_yourIdentity, spinner_email;

	private ArrayAdapter adapter_documentType, adapter_yourIdentity,
			adapter_email;
	private EditText et_name, et_;

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

		// 将adapter添加到spinner中
		spinner_documentType.setAdapter(adapter_documentType);
		spinner_yourIdentity.setAdapter(adapter_yourIdentity);
		spinner_email.setAdapter(adapter_email);

		// 为spinner添加事件监听
		spinner_documentType.setOnItemSelectedListener(this);
		spinner_yourIdentity.setOnItemSelectedListener(this);
		spinner_email.setOnItemSelectedListener(this);

		findViewById(R.id.btn_newRecommend_summit).setOnClickListener(this);

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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_newRecommend_summit:
			summit();
			break;

		default:
			break;
		}

	}

	private void summit() {

		// if (et.getText() == null || et.getText().equals("")) {
		// ToastUtils.getInit(getApplicationContext()).show(
		// R.string.add_case_summit_null);
		// }

		findViewById(R.id.ll_waiting).setVisibility(View.VISIBLE);

		new AsyncDataLoader(new Callback() {
			Record record;

			@Override
			public void onStartAsync() {

				AsHt asht = AsHt.getInstance();
				UserInfo user = ApplictionManager.getInstance().getUserInfo();
				try {
					// record = asht.addRecordGroup(user,
					// et.getText().toString());
					asht.recommendPatient(user, new Recommend());
				} catch (Exception e) {
					e.printStackTrace();
					Log.w("Record", e.toString());
				}
			}

			@Override
			public void onPrepareAsync() {

			}

			@Override
			public void onFinishAsync() {
				// if (record != null) {
				// ToastUtils.getInit(getApplicationContext()).show("添加完成");
				// } else {
				// ToastUtils.getInit(getApplicationContext()).show("添加失败");
				// }

				findViewById(R.id.ll_waiting).setVisibility(View.GONE);
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						NewRecommendActivity.this);
				alertDialog.setTitle("添加成功");
				alertDialog.setMessage("返回病例组");
				alertDialog.setPositiveButton(getString(R.string.text_sure),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
							}
						});
				alertDialog.show();
			}
		}).execute();

	}
}
