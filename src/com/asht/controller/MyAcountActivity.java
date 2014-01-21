package com.asht.controller;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.asht.R;
import com.asht.fragment.EditMyAccountInfoFragment;
import com.asht.fragment.MyAcountDisplayFragment;
import com.asht.fragment.MyAcountZMoneyFragment;
import com.asht.utl.ApplictionManager;

/**
 * 我的账户
 * 
 * @author Administrator
 * 
 */
public class MyAcountActivity extends FragmentActivity implements
		OnClickListener {
	private TextView myAccount;
	private TextView myZMoney;
	private MyAcountDisplayFragment mAcountFragment;
	private MyAcountZMoneyFragment mAcountZMoneyFragment;
	private EditMyAccountInfoFragment myAccountEditInfo;
	private Button editInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_account_show);
		myAccount = (TextView) findViewById(R.id.my_account);
		myZMoney = (TextView) findViewById(R.id.my_zmoney);
		editInfo = (Button) findViewById(R.id.btnEdit);
		myAccountEditInfo = new EditMyAccountInfoFragment();
		mAcountFragment = new MyAcountDisplayFragment();
		mAcountZMoneyFragment = new MyAcountZMoneyFragment();
		myAccount.setOnClickListener(this);
		myZMoney.setOnClickListener(this);
		editInfo.setOnClickListener(this);
		// if (ApplictionManager.getInstance().getUser().isLogin()) {
		// getSupportFragmentManager().beginTransaction()
		// .replace(R.id.my_container, mAcountFragment).commit();
		// }
		// ;
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (ApplictionManager.getInstance().getUser().isLogin()) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.my_container, mAcountFragment).commit();
		}
		;
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.my_account:
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.my_container, mAcountFragment).commit();

			break;

		case R.id.my_zmoney:
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.my_container, mAcountZMoneyFragment).commit();
			break;
		case R.id.btnEdit:
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.my_container, myAccountEditInfo).commit();
			break;
		}
	}
}
