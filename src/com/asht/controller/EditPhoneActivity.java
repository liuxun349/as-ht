package com.asht.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.asht.R;

public class EditPhoneActivity extends Activity implements OnClickListener {

	EditText et_newPhone, et_checkNumber, et_payPwd;
	Button btn_submit, btn_getCheckNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.edit_change_phone_number);
		findViewById(R.id.tv_title_back).setOnClickListener(this);
		et_newPhone = (EditText) findViewById(R.id.et_newPhone);
		et_checkNumber = (EditText) findViewById(R.id.et_checkNumber);
		et_payPwd = (EditText) findViewById(R.id.et_payPwd);
		btn_getCheckNumber = (Button) findViewById(R.id.btn_getCheckNumber);
		btn_submit = (Button) findViewById(R.id.btn_submit);
		btn_getCheckNumber.setOnClickListener(this);
		btn_submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_title_back:
			setResult(RESULT_CANCELED);
			finish();
			break;
		case R.id.btn_submit:
			submitNewsPhone();
			break;
		default:
			break;
		}

	}

	private void submitNewsPhone() {
		String phone =et_newPhone.getText().toString();
//		SecurityCenterDAO.editNewPhone("", phone);
	}
}
