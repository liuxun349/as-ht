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
import com.asht.AsyncDataLoader;
import com.asht.AsyncDataLoader.Callback;
import com.asht.R;
import com.asht.model.Record;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;
import com.asht.view.ToastUtils;

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
				user = new UserInfo();
				user.setUserPhoneNo("13000001011");
				try {
					// asht.uploadCaseToGroup(user, groupId, resume)
					// Resume r = new Resume();
					// asht.deleteCaseFromGroup(user, "142",
					// r.getImedicalrecorditemid()+"")
					record = asht.addRecordGroup(user, et.getText().toString());
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
						AddCasesActivity.this);
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
