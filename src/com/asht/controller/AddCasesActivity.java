package com.asht.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.AsyncDataLoader;
import com.asht.AsyncDataLoader.Callback;
import com.asht.R;
import com.asht.model.Record;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;
import com.asht.view.ToastUtils;
import com.example.controller.AFinalController;

public class AddCasesActivity extends Activity implements OnClickListener {
	EditText et = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_my_cases);
		findViewById(R.id.tv_title_back).setOnClickListener(this);
		findViewById(R.id.btn_sendCases).setOnClickListener(this);
		et = (EditText) findViewById(R.id.et_addMyCases);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_title_back:
			finish();
			break;
		case R.id.btn_sendCases:
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

		findViewById(R.id.ll_waiting).setVisibility(View.VISIBLE);

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

				findViewById(R.id.ll_waiting).setVisibility(View.GONE);
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						AddCasesActivity.this);
				alertDialog.setTitle(record != null ? "添加病例组成功" : "添加病例组失败");
				alertDialog.setMessage(record != null ? "返回病例组" : "网络错误时候重试");
				alertDialog.setPositiveButton(getString(R.string.text_sure),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								if (record == null) {
									summit();
								} else {
									finish();
								}
							}
						});
				alertDialog.show();
			}
		}).execute();

	}
}
