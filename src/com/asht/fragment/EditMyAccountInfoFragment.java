package com.asht.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.R;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;
import com.asht.view.ToastUtils;
import com.asht.view.WaitingDialog;

public class EditMyAccountInfoFragment extends AshtFragment implements
		OnClickListener {
	private EditText nickName;
	private EditText trueName;
	private EditText CertificateNo;
	private EditText age;
	private EditText email;

	private Spinner sex;
	private Spinner CertificateType;
	private WaitingDialog waitingDialog;

	private Button postEdit;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		nickName = (EditText) getActivity().findViewById(R.id.nickName);
		trueName = (EditText) getActivity().findViewById(R.id.trueName);
		CertificateNo = (EditText) getActivity().findViewById(
				R.id.CertificateNo);
		age = (EditText) getActivity().findViewById(R.id.ageEdit);
		email = (EditText) getActivity().findViewById(R.id.email);

		sex = (Spinner) getActivity().findViewById(R.id.sex);
		CertificateType = (Spinner) getActivity().findViewById(
				R.id.CertificateType);

		ArrayAdapter adapter1 = ArrayAdapter.createFromResource(getActivity(),
				R.array.documentType, android.R.layout.simple_spinner_item);
		ArrayAdapter adapter2 = ArrayAdapter.createFromResource(getActivity(),
				R.array.sex, android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		CertificateType.setAdapter(adapter1);
		sex.setAdapter(adapter2);

		bundleSource();
		waitingDialog = new WaitingDialog(getActivity());
	}

	private void bundleSource() {
		// TODO Auto-generated method stub
		UserInfo userInfo = ApplictionManager.getInstance().userInfo;
		nickName.setText(userInfo.getUserNickName());
		trueName.setText(userInfo.getUserTrueName());
		CertificateNo.setText(userInfo.getUserCertificateNo());
		CertificateType.setSelection(userInfo.getUserCertificateType());
		email.setText(userInfo.getUserEmail());
		sex.setSelection(userInfo.getUserSex());
		age.setText(userInfo.getUserAge() + "");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.my_account_info_edit, null, false);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0 == postEdit) {
			System.out.println("提交。。。");
		}
	}

	public void setPostEditButton(final View v) {
		postEdit = (Button) v;
		postEdit.setText("完成修改");
		postEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println(" .... ti jiao ...");
				editInfo();
			}
		});
	}

	private void editInfo() {
		UserInfo userInfo = ApplictionManager.getInstance().userInfo;
		boolean isChanged = false;
		if (!nickName.getText().toString().trim()
				.equals(userInfo.getUserNickName())) {
			isChanged = true;
			userInfo.setUserNickName(nickName.getText().toString().trim());
		}
		if (!trueName.getText().toString().trim()
				.equals(userInfo.getUserTrueName())) {
			isChanged = true;
			userInfo.setUserTrueName(trueName.getText().toString().trim());
		}
		if (!CertificateNo.getText().toString().trim()
				.equals(userInfo.getUserCertificateNo())) {
			isChanged = true;
			userInfo.setUserCertificateNo(CertificateNo.getText().toString()
					.trim());
		}
		if (!email.getText().toString().trim().equals(userInfo.getUserEmail())) {
			isChanged = true;
			userInfo.setUserEmail(email.getText().toString().trim());
		}
		if (!age.getText().toString().trim().equals(userInfo.getUserAge())) {
			isChanged = true;
			userInfo.setUserAge(Integer.parseInt(age.getText().toString()
					.trim()));
		}
		if (CertificateType.getSelectedItemPosition() != userInfo
				.getUserCertificateType()) {
			isChanged = true;
			userInfo.setUserCertificateType(CertificateType
					.getSelectedItemPosition());
		}
		if (sex.getSelectedItemPosition() != userInfo.getUserSex()) {
			isChanged = true;
			userInfo.setUserSex(sex.getSelectedItemPosition());
		}
		;
		if (isChanged) {
			waitingDialog.show();
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					boolean isSuccess = false;
					try {
						isSuccess = AsHt.getInstance().modifyInfo(
								ApplictionManager.getInstance().userInfo);
						System.out.println(" huilai " + isSuccess);
					} catch (AsHtException e) {
						// TODO Auto-generated catch block
					}
					Message msg = new Message();
					msg.arg1 = 2;
					msg.obj = isSuccess;
					mHandler.sendMessage(msg);
				}
			}).start();
		} else {
			Message msg = new Message();
			msg.arg1 = 1;
		}
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.arg1 == 2) {
				waitingDialog.dismiss();
				if ((Boolean) msg.obj) {
					ToastUtils.getInit(getActivity()).show("修改成功");
				} else {
					ToastUtils.getInit(getActivity()).show("修改失败");
				}
			}
			boolean isOk = false;
			while (!isOk) {
				MyAcountDisplayFragment mAcountFragment = new MyAcountDisplayFragment();
				mAcountFragment.setPostEditButton(postEdit);
				try {
					isOk = true;
					getActivity().getSupportFragmentManager()
							.beginTransaction()
							.replace(R.id.my_container, mAcountFragment)
							.commit();
				} catch (Exception e) {
					// TODO: handle exception
					isOk = false;
				}
			}
		};
	};
}
