package com.asht.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.asht.R;

public class EditPwdAnswerActivity extends Activity implements OnClickListener {

	EditText et_payPwd, et_answer, et_answer2;
	Button btn_submit;
	Spinner spinner_answer,spinner_answer2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.edit_change_phone_number);
		findViewById(R.id.tv_title_back).setOnClickListener(this);
		et_answer = (EditText) findViewById(R.id.et_answer);
		et_answer2 = (EditText) findViewById(R.id.et_answer2);
		et_payPwd = (EditText) findViewById(R.id.et_payPwd);
		btn_submit = (Button) findViewById(R.id.btn_submit);
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
			break;
		default:
			break;
		}

	}

}
