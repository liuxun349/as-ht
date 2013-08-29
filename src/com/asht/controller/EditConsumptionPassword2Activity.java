package com.asht.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.asht.R;

public class EditConsumptionPassword2Activity extends Activity implements OnClickListener {

	EditText et_newPwd,et_newPwd2;
	Button btn_submit,btn_getCheckNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.edit_consume_passwd_secend);
		findViewById(R.id.tv_title_back).setOnClickListener(this);
		et_newPwd=(EditText) findViewById(R.id.et_newPwd);
		et_newPwd2=(EditText) findViewById(R.id.et_newPwd2);
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
			submitNewsPwd();
			break;
		default:
			break;
		}

	}
	private void submitNewsPwd(){
		
	}
}
