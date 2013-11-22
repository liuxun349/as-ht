package com.asht.controller;

import com.asht.R;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class MyAcountSelfInfo extends View {
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

	public MyAcountSelfInfo(Context context) {
		super(context);
		mContext = context;
		init();

	}
  
	private void init() {
		inflate(mContext, R.layout.my_account, null);
		txtAge = (TextView) findViewById(R.id.age);
		txtUserId = (TextView) findViewById(R.id.userid);
		txtTrueName = (TextView) findViewById(R.id.trueName);
		txtPhone = (TextView) findViewById(R.id.phone);
		txtSex = (TextView) findViewById(R.id.sex);
		txtNickName = (TextView) findViewById(R.id.nickName);
		txtContributionValue = (TextView) findViewById(R.id.ContributionValue);
		txtCertificateNo = (TextView) findViewById(R.id.CertificateNo);
		txtCertificateType = (TextView) findViewById(R.id.CertificateType);

		txtHowUseUMoney = (TextView) findViewById(R.id.howUseUMoney);
		txtAboutUpdate = (TextView) findViewById(R.id.aboutUpdate);
		
	}
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		System.out.println("....y");
		super.draw(canvas);
	}
}
