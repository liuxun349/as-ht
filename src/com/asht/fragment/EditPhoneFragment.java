package com.asht.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.R;
import com.asht.controller.Controller;
import com.asht.fragment.AshtFragment;
import com.asht.utl.ApplictionManager;
import com.asht.view.WaitingDialog;

public class EditPhoneFragment extends AshtFragment implements OnClickListener {

	private EditText et_newPhone, et_checkNumber, et_payPwd;
	private Button btn_submit, btn_getCheckNumber;
	private WaitingDialog waitingDialog;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if ((Boolean) msg.obj) {
				waitingDialog.dismiss();
			}
		};
	};
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
		Controller.setNomePagTop(getActivity(), "更换手机", true, true, "确定");

		et_newPhone = (EditText) mActivity.findViewById(R.id.et_newPhone);
		et_checkNumber = (EditText) mActivity.findViewById(R.id.et_checkNumber);
		et_payPwd = (EditText) mActivity.findViewById(R.id.et_payPwd);
		btn_getCheckNumber = (Button) mActivity
				.findViewById(R.id.getCheckNumber);
		btn_submit = (Button) mActivity.findViewById(R.id.btnEdit);
		btn_getCheckNumber.setOnClickListener(this);
		btn_submit.setOnClickListener(this);

		waitingDialog = new WaitingDialog(getActivity());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_title_back:
			callback.back();
			break;
		case R.id.btnEdit:
			submitNewsPhone();
			break;
		default:
			break;
		}

	}

	private void submitNewsPhone() {
		String phone = et_newPhone.getText().toString();
		final String checkNo = et_checkNumber.getText().toString().trim();
		final String payPasswd = et_payPwd.getText().toString().trim();
		final String newUserPhoneNo = et_newPhone.getText().toString().trim();
		waitingDialog.show();

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boolean isSuccess = false;
				try {
					isSuccess = AsHt.getInstance().modifyMobileNumber(
							ApplictionManager.getInstance().userInfo, checkNo,
							payPasswd, newUserPhoneNo);
				} catch (AsHtException e) {
					// TODO Auto-generated catch block
					isSuccess = false;
				}
				if (isSuccess) {
					System.out.println(" success ");
				}
				Message message = new Message();
				message.obj = isSuccess;
				mHandler.sendMessage(message);
			}
		}).start();
	}
}
