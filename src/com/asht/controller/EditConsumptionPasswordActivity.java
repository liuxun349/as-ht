package com.asht.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.asht.R;

public class EditConsumptionPasswordActivity extends Activity implements OnClickListener {

	EditText et_careId,et_phone,et_inputName;
	Button btn_submit,btn_getCheckNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.edit_consume_passwd_first);
		findViewById(R.id.tv_title_back).setOnClickListener(this);
		et_careId=(EditText) findViewById(R.id.et_cardId);
		et_phone=(EditText) findViewById(R.id.et_phone);
		et_inputName = (EditText) findViewById(R.id.et_checkNumber);
		btn_submit = (Button) findViewById(R.id.btn_submit);
		btn_getCheckNumber = (Button) findViewById(R.id.btn_getCheckNumber);
		btn_submit.setOnClickListener(this);
		btn_getCheckNumber.setOnClickListener(this);
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
			Intent intent = new Intent(getApplicationContext(), EditConsumptionPassword2Activity.class);
			startActivity(intent);
			break;
		case R.id.get_check_number:
			getCheckNum();
			break;
		default:
			break;
		}

	}
	/**
	 * 获取手机验证码
	 */
	private void getCheckNum(){
	}
}
