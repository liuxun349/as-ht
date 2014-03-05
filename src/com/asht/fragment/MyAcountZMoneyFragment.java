package com.asht.fragment;

import java.util.ArrayList;
import java.util.List;

import com.asht.R;
import com.asht.adapter.ZMoneyAdapter;
import com.asht.fragment.AshtFragment;
import com.asht.model.ZCoupon;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MyAcountZMoneyFragment extends AshtFragment implements
		OnClickListener {
	private Context mContext;
	private ListView mListView;
	private ZMoneyAdapter mAdapter;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mListView = (ListView) getActivity().findViewById(R.id.zmoney_list);
		List<ZCoupon> list = new ArrayList<ZCoupon>();
		ZCoupon zCoupon = new ZCoupon();
		zCoupon.ZMoney = "123";
		list.add(zCoupon);
		list.add(zCoupon);
		list.add(zCoupon);
		list.add(zCoupon);
		list.add(zCoupon);
		list.add(zCoupon);
		list.add(zCoupon);
		mAdapter = new ZMoneyAdapter(getActivity(), list);
		mListView.setAdapter(mAdapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.my_account_myzmoney, null, false);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	};
}
