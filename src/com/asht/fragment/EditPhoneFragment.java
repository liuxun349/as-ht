package com.asht.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.asht.R;
import com.asht.controller.Controller;
import com.asht.fragment.AshtFragment;

public class EditPhoneFragment extends AshtFragment implements OnClickListener {

	private EditText et_newPhone, et_checkNumber, et_payPwd;
	private Button btn_submit, btn_getCheckNumber;

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
		return inflater.inflate(R.layout.edit_change_phone_number, container,
				false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mActivity.findViewById(R.id.tv_title_back).setOnClickListener(this);
		Controller.setNomePagTop(getActivity(), true, true, "更换手机");

		et_newPhone = (EditText) mActivity.findViewById(R.id.et_newPhone);
		et_checkNumber = (EditText) mActivity.findViewById(R.id.et_checkNumber);
		et_payPwd = (EditText) mActivity.findViewById(R.id.et_payPwd);
		btn_getCheckNumber = (Button) mActivity
				.findViewById(R.id.btn_getCheckNumber);
		btn_submit = (Button) mActivity.findViewById(R.id.btnEdit);
		btn_submit.setText("确定");
		btn_getCheckNumber.setOnClickListener(this);
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
			submitNewsPhone();
			break;
		default:
			break;
		}

	}

	private void submitNewsPhone() {
		String phone = et_newPhone.getText().toString();
		// SecurityCenterDAO.editNewPhone("", phone);
	}
}
