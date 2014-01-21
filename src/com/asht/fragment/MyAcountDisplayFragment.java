package com.asht.fragment;

import com.asht.R;
import com.asht.fragment.AshtFragment;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MyAcountDisplayFragment extends AshtFragment {
	private Context mContext;

	private TextView txtUserId;
	private TextView txtTrueName;
	private TextView txtPhone;
	private TextView txtNickName;
	private TextView txtAge;
	private TextView txtSex;
	private TextView txtContributionValue;
	private TextView txtCertificateNo;
	private TextView txtCertificateType;

	private TextView txtHowUseUMoney;
	private TextView txtAboutUpdate;

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
		bundlSource();
	}

	private void bundlSource() {
		// TODO Auto-generated method stub
		UserInfo userInfo = ApplictionManager.getInstance().userInfo;
		txtAge.setText(""+userInfo.getUserAge());
		txtUserId.setText(userInfo.getUserPhoneNo());
		txtTrueName.setText(userInfo.getUserTrueName());
		txtPhone.setText(userInfo.getUserPhoneNo());
		// txtContributionValue.setText(userInfo.getuser);
		// txtCertificateNo.setText(text);
		// txtCertificateType.setText(text);
		// txtHowUseUMoney.setText(text);
		// txtAge.setText("12");

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
}
