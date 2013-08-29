package com.asht.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;

import com.asht.R;

public class FindPayActivity extends Activity implements OnClickListener {

	Button btn_submit;
	Spinner spinner_pwdProtection1,spinner_pwdProtection2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.edit_finding_consume_passwd);
		findViewById(R.id.tv_title_back).setOnClickListener(this);
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
			submitNewsPhone();
			break;
		default:
			break;
		}

	}

	private void submitNewsPhone() {
	}
}
