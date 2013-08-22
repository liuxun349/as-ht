package com.asht.cotroller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.asht.R;

public class RegisterSecendActivity extends Activity {
	private Button btn_next;
	private EditText etx_nickName, etx_turename, etx_certificateType,
			etx_certificationNo, etx_roleId, etx_mailePre, etx_mailelast;
	private String nickName,tureName,certificateType,certificateNo,email;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_secend);
		init();
		
		btn_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				checking();
			}
		});
	}
	/**
	 * 检查输入是否都合法
	 * @return
	 */
	private boolean checking(){
		nickName = etx_nickName.getText().toString().trim();
		return true;
	}
	
	private void init(){
		btn_next = (Button) findViewById(R.id.btn_next);
		etx_certificateType = (EditText) findViewById(R.id.etx_certificateType);
		etx_certificationNo = (EditText) findViewById(R.id.etx_certificateNo);
		etx_mailelast = (EditText) findViewById(R.id.etx_mailelast);
		etx_mailePre = (EditText) findViewById(R.id.etx_mailelast);
		etx_roleId = (EditText) findViewById(R.id.etx_roleId);
		etx_nickName = (EditText) findViewById(R.id.etx_nickname);
		etx_turename = (EditText) findViewById(R.id.etx_turename);
		
	}
}
