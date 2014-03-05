package com.asht.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.asht.R;
import com.asht.controller.Controller;

public class EditConsumptionPassword2Fragment extends AshtFragment implements
		OnClickListener {

	EditText et_newPwd, et_newPwd2;
	Button btn_submit, btn_getCheckNumber;
	private Activity mActivity;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		mActivity = activity;
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.edit_consume_passwd_secend, container,
				false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Controller.setNomePagTop(getActivity(), true, "修改消费密码");

		mActivity.findViewById(R.id.tv_title_back).setOnClickListener(this);
		et_newPwd = (EditText) mActivity.findViewById(R.id.et_newPwd);
		et_newPwd2 = (EditText) mActivity.findViewById(R.id.et_newPwd2);
		btn_submit = (Button) mActivity.findViewById(R.id.btn_submit);
		btn_submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_title_back:
			callback.back();
			break;
		case R.id.btn_submit:
			submitNewsPwd();
			break;
		default:
			break;
		}

	}

	private void submitNewsPwd() {

	}
}
