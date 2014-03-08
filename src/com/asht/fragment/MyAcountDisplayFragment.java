package com.asht.fragment;

import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.R;
import com.asht.fragment.AshtFragment;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;
import com.asht.view.ToastUtils;
import com.asht.view.WaitingDialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MyAcountDisplayFragment extends AshtFragment implements
		OnClickListener {
	private Context mContext;
	private Button postEdit;
	private TextView txtUserId;
	private TextView txtTrueName;
	private TextView txtPhone;
	private TextView txtNickName;
	private TextView txtAge;
	private TextView txtSex;
	private TextView txtContributionValue;
	private TextView txtZmoney;
	private TextView txtCertificateNo;
	private TextView txtCertificateType;
	private TextView txtEmail;

	private TextView txtHowUseUMoney;
	private TextView txtAboutUpdate;

	private Button searchContrbution;
	private Button searchZmoney;

	private WaitingDialog waitingDialog;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		txtAge = (TextView) getActivity().findViewById(R.id.age);
		txtUserId = (TextView) getActivity().findViewById(R.id.userid);
		txtTrueName = (TextView) getActivity().findViewById(R.id.trueName);
		txtPhone = (TextView) getActivity().findViewById(R.id.phone);
		txtSex = (TextView) getActivity().findViewById(R.id.sex);
		txtNickName = (TextView) getActivity().findViewById(R.id.nickName);
		txtContributionValue = (TextView) getActivity().findViewById(
				R.id.ContributionValue);
		txtCertificateNo = (TextView) getActivity().findViewById(
				R.id.CertificateNo);
		txtCertificateType = (TextView) getActivity().findViewById(
				R.id.CertificateType);

		txtHowUseUMoney = (TextView) getActivity().findViewById(
				R.id.howUseUMoney);
		txtAboutUpdate = (TextView) getActivity()
				.findViewById(R.id.aboutUpdate);
		txtEmail = (TextView) getActivity().findViewById(R.id.email);

		txtContributionValue = (TextView) getActivity().findViewById(
				R.id.ContributionValue);
		txtZmoney = (TextView) getActivity().findViewById(R.id.ZMoney);
		searchContrbution = (Button) getActivity().findViewById(
				R.id.searchContribution);
		searchZmoney = (Button) getActivity().findViewById(R.id.searchZMoney);

		searchContrbution.setOnClickListener(this);
		searchZmoney.setOnClickListener(this);

		waitingDialog = new WaitingDialog(getActivity());
		bundlSource();
	}

	private void bundlSource() {
		// TODO Auto-generated method stub
		UserInfo userInfo = ApplictionManager.getInstance().userInfo;
		txtUserId.setText(userInfo.getUserPhoneNo());
		txtTrueName.setText(userInfo.getUserTrueName());
		txtPhone.setText(userInfo.getUserPhoneNo());
		// txtContributionValue.setText(userInfo.getuser);
		txtCertificateNo.setText(userInfo.getUserCertificateNo());
		txtCertificateType.setText(getResources().getStringArray(
				R.array.documentType)[userInfo.getUserCertificateType()]);
		// txtHowUseUMoney.setText(text);
		txtAge.setText(userInfo.getUserAge() + "");
		txtEmail.setText(userInfo.getUserEmail());
		txtNickName.setText(userInfo.getUserNickName());
		txtSex.setText(getResources().getStringArray(R.array.sex)[userInfo
				.getUserSex()]);

	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		mContext = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.my_account_info_display, null, false);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.searchContribution:
			waitingDialog.show();
			search(0);
			break;
		case R.id.searchZMoney:
			waitingDialog.show();
			search(1);
			break;
		}
	}

	private void search(final int type) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boolean isSuccess = false;
				long rs = 0;
				if (type == 0) {
					try {
						rs = AsHt.getInstance().searchContributions(
								ApplictionManager.getInstance().userInfo);
						isSuccess = true;

					} catch (AsHtException e) {
						// TODO Auto-generated catch block
					}
				} else if (type == 1) {
					try {
						rs = AsHt.getInstance().getZGold(
								ApplictionManager.getInstance().userInfo);
						isSuccess = true;
					} catch (AsHtException e) {
						// TODO Auto-generated catch block
					}
				}
				Message msg = new Message();
				msg.obj = isSuccess;
				msg.arg1 = type;
				msg.arg2 = (int) rs;
				mHandler.sendMessage(msg);
			}
		}).start();
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			waitingDialog.dismiss();
			if ((Boolean) msg.obj) {
				if (msg.arg1 == 0) {
					txtContributionValue.setText(msg.arg2 + "");
				} else if (msg.arg1 == 1) {
					txtZmoney.setText(msg.arg2 + "");
				}
			} else {
				ToastUtils.getInit(getActivity()).show("获取失败");
			}
		};
	};

	public void setPostEditButton(final View v) {
		postEdit = (Button) v;
		postEdit.setText("编辑");
		postEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println(" .... ti jiao ...");
				boolean isOk = false;
				while (!isOk) {

					EditMyAccountInfoFragment mAcountFragment = new EditMyAccountInfoFragment();
					mAcountFragment.setPostEditButton(v);
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
			}
		});
	}
}