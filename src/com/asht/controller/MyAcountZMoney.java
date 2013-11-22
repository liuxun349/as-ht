package com.asht.controller;

import com.asht.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class MyAcountZMoney extends LinearLayout {
	private Context mContext;

	public MyAcountZMoney(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		mContext = context;
		init();
	}

	private void init() {
		this.inflate(mContext, R.layout.my_account_myzmoney, null);
	};
}
