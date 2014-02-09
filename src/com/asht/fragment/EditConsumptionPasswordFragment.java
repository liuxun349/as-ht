package com.asht.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.asht.R;
import com.asht.controller.Controller;

public class EditConsumptionPasswordFragment extends AshtFragment implements
		OnClickListener {

	EditText et_careId, et_phone, et_inputName;
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
		return inflater.inflate(R.layout.edit_consume_passwd_first, container,
				false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Controller.setNomePagTop(getActivity(), true, "修改消费密码");
		mActivity.findViewById(R.id.tv_title_back).setOnClickListener(this);
		et_careId = (EditText) mActivity.findViewById(R.id.et_cardId);
		et_phone = (EditText) mActivity.findViewById(R.id.et_phone);
		et_inputName = (EditText) mActivity.findViewById(R.id.et_checkNumber);
		btn_submit = (Button) mActivity.findViewById(R.id.btnEdit);
		btn_getCheckNumber = (Button) mActivity
				.findViewById(R.id.btn_getCheckNumber);
		btn_submit.setOnClickListener(this);
		btn_submit.setText("确定");
		btn_getCheckNumber.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_title_back:
			callback.back();
			break;
		case R.id.btn_submit:
			checkPhoneNo();
			break;
		case R.id.get_check_number:
			getCheckNum();
			break;
		default:
			break;
		}

	}

	private void checkPhoneNo() {
		nextFragment();
	}

	private void nextFragment() {

		AshtFragment mFragment = new EditConsumptionPassword2Fragment();
		mFragment.setAshtFragmentCallback(callback);
		FragmentTransaction mTransaction = getFragmentManager()
				.beginTransaction();
		mTransaction.replace(R.id.fragment_container, mFragment);
		mTransaction.addToBackStack(null);
		mTransaction.commit();
	}

	/**
	 * 获取手机验证码
	 */
	private void getCheckNum() {
	}
}
