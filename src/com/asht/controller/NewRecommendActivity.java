package com.asht.controller;

import android.app.Activity;
import android.content.Intent;
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
import com.asht.AsyncDataLoader.Callback;
import com.asht.R;
import com.asht.model.Recommend;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;
import com.asht.view.ToastUtils;
import com.asht.view.WaitingDialog;
import com.yj.compress.Phone;
import com.yj.compress.Validate;

public class NewRecommendActivity extends Activity implements
		OnItemSelectedListener, OnClickListener {

	// 声明控件对象
	private Spinner spinner_documentType, spinner_yourIdentity, spinner_email;

	@SuppressWarnings("rawtypes")
	private ArrayAdapter adapter_documentType, adapter_yourIdentity,
			adapter_email;
	private EditText et_name, et_phone, et_shengfenzheng, et_email;
	private WaitingDialog mDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_recommend);
		mDialog = new WaitingDialog(this);
		// 根据控件的ID得到代表该控件的对象
		spinner_documentType = (Spinner) findViewById(R.id.spinner_documentType);
		spinner_yourIdentity = (Spinner) findViewById(R.id.spinner_yourIdentity);
		spinner_email = (Spinner) findViewById(R.id.spinner_email);

		et_name = (EditText) findViewById(R.id.et_recommend_name);
		et_phone = (EditText) findViewById(R.id.et_recommend_phone);
		et_shengfenzheng = (EditText) findViewById(R.id.et_recommend_certificateId);
		et_email = (EditText) findViewById(R.id.et_recommend_email_start);

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
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		if (b != null) {
			int hz = b.getInt("hz", 0);
			spinner_yourIdentity.setSelection(hz);
		}
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
			mDialog.show();
			summit();
			break;

		default:
			break;
		}

	}

	private void summit() {

		String phone = et_phone.getText().toString().trim();
		String email = et_email.getText().toString().trim();
		String shengfenzheng = et_shengfenzheng.getText().toString().trim();
		String name = et_name.getText().toString().trim();
		if (!Phone.isMobileNO(phone)) {
			ToastUtils.getInit(getApplicationContext()).show("请填写您正确的手机号码");
			mDialog.dismiss();
			return;
		}
		if (name.length() == 0) {

			ToastUtils.getInit(getApplicationContext()).show("请填写您的姓名");
			mDialog.dismiss();
			return;
		}
		if (!Phone.isEmail(email, false)) {
			ToastUtils.getInit(getApplicationContext()).show("请填写您正确的邮件地址");
			mDialog.dismiss();
			return;
		}
		if (spinner_documentType.getSelectedItemPosition() == 0) {
			String sfz;
			try {
				sfz = new Validate().IDCardValidate(shengfenzheng);
			} catch (Exception e1) {
				sfz = "证件号码不正确";
				e1.printStackTrace();
			}
			if (sfz.length() > 0) {
				ToastUtils.getInit(getApplicationContext()).show(sfz);
				mDialog.dismiss();
				return;
			}
		}
		new AsyncDataLoader(new Callback() {

			@Override
			public void onStartAsync() {
				String phone = et_phone.getText().toString().trim();
				String email = et_email.getText().toString().trim();
				String shengfenzheng = et_shengfenzheng.getText().toString()
						.trim();
				String name = et_name.getText().toString().trim();
				AsHt asht = AsHt.getInstance();
				UserInfo user = ApplictionManager.getInstance().getUserInfo();
				try {
					int index = spinner_yourIdentity.getSelectedItemPosition();

					boolean fag = asht.recommendPatient(
							user,
							new Recommend(phone, name, spinner_documentType
									.getSelectedItemPosition() + "",
									shengfenzheng, email
											+ spinner_email.getSelectedItem()
													.toString(),
									index == 1 ? "1001" : "1002"));
					System.out.println("chenggongle_______" + fag);
				} catch (Exception e) {
					e.printStackTrace();
					Log.w("Record", e.toString());
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onPrepareAsync() {

			}

			@Override
			public void onFinishAsync() {
				ToastUtils.getInit(getApplicationContext()).show("添加完成");

				mDialog.dismiss();
			}
		}).execute();

	}
}
