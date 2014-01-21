package com.asht.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;

import com.asht.R;
import com.asht.controller.Controller;
import com.asht.fragment.AshtFragment;

public class FindPayPwdFragment extends AshtFragment implements OnClickListener {

	Button btn_submit;
	Spinner spinner_pwdProtection1, spinner_pwdProtection2;
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
		return inflater.inflate(R.layout.edit_finding_consume_passwd,
				container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Controller.setNomePagTop(getActivity(), true, "找回消费密码");

		mActivity.findViewById(R.id.tv_title_back).setOnClickListener(this);
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
			submitNewsPhone();
			break;
		default:
			break;
		}

	}

	private void submitNewsPhone() {
	}
}
