package com.asht.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.asht.AsHt;
import com.asht.AsyncDataLoader;
import com.asht.AsyncDataLoader.Callback;
import com.asht.R;
import com.asht.model.Record;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;
import com.asht.view.ToastUtils;
import com.asht.view.WaitingDialog;
import com.example.controller.AFinalController;

public class AddCasesActivity extends Activity implements OnClickListener {
	EditText et = null;
	boolean isUpdate = false;
	private WaitingDialog mDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_my_cases);
		mDialog = new WaitingDialog(this);
		findViewById(R.id.tv_title_back).setOnClickListener(this);
		findViewById(R.id.btn_sendCases).setOnClickListener(this);
		et = (EditText) findViewById(R.id.et_addMyCases);
		isUpdate = false;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_title_back:
			finish();
			break;
		case R.id.btn_sendCases:
			mDialog.show();
			summit();
			break;

		default:
			break;
		}

	}

	private void summit() {
		if (et.getText() == null || et.getText().equals("")) {
			ToastUtils.getInit(getApplicationContext()).show(
					R.string.add_case_summit_null);
			return;
		}

		new AsyncDataLoader(new Callback() {
			Record record;

			@Override
			public void onStartAsync() {

				AsHt asht = AsHt.getInstance();
				UserInfo user = ApplictionManager.getInstance().getUserInfo();
				try {
					record = asht.addRecordGroup(user, et.getText().toString());
					AFinalController.getDB(getApplicationContext())
							.save(record);
				} catch (Exception e) {
					record = null;
					e.printStackTrace();
				}
			}

			@Override
			public void onPrepareAsync() {

			}

			@Override
			public void onFinishAsync() {
				mDialog.dismiss();
				String str = record != null ? "添加病例组成功" : "添加病例组失败";
				ToastUtils.getInit(getApplicationContext()).show(str);
				if (record == null) {
				} else {
					isUpdate = true;
					finish();
				}
			}
		}).execute();

	}

	@Override
	public void finish() {
		if (isUpdate) {
			setResult(RESULT_OK);
		}
		super.finish();
	}
}
