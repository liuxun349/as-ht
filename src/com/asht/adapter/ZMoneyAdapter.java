package com.asht.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asht.R;
import com.asht.model.ZCoupon;

public class ZMoneyAdapter extends BaseAdapter {
	private Context mContext;
	private List<ZCoupon> zCoupons;

	static class ViewHolder {
		TextView id;
		FrameLayout title;
		LinearLayout contain;
		TextView zmoney;
		TextView state;
		TextView payCode;
		TextView dtValidateTime;
		TextView dtExchangeTime;
		TextView payTime;
		TextView zmoneyTitle;
		ImageView downIconDown;
	};

	public ZMoneyAdapter(Context context, List<ZCoupon> list) {
		// TODO Auto-generated constructor stub
		mContext = context;
		zCoupons = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return zCoupons.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return zCoupons.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		final ViewHolder viewHolder;
		if (arg1 == null) {
			viewHolder = new ViewHolder();
			arg1 = LayoutInflater.from(mContext).inflate(
					R.layout.zmoney_list_item, null);
			viewHolder.dtExchangeTime = (TextView) arg1
					.findViewById(R.id.zmoney_convert);
			viewHolder.dtValidateTime = (TextView) arg1
					.findViewById(R.id.zmoney_dtValidateTime);
			viewHolder.id = (TextView) arg1.findViewById(R.id.zmoney_id);
			viewHolder.payCode = (TextView) arg1
					.findViewById(R.id.zmoney_paycode);
			viewHolder.payTime = (TextView) arg1
					.findViewById(R.id.zmoney_paytime);
			viewHolder.state = (TextView) arg1.findViewById(R.id.zmoney_state);
			viewHolder.zmoney = (TextView) arg1
					.findViewById(R.id.zmoney_zmoney);
			viewHolder.zmoneyTitle = (TextView) arg1
					.findViewById(R.id.zmoney_title);
			viewHolder.contain = (LinearLayout) arg1
					.findViewById(R.id.zmoney_contain);
			viewHolder.title = (FrameLayout) arg1
					.findViewById(R.id.zmoney_linearlayout_title);
			viewHolder.downIconDown = (ImageView) arg1
					.findViewById(R.id.down_icon_default);

			arg1.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) arg1.getTag();
		}
		viewHolder.zmoneyTitle.setText(zCoupons.get(arg0).ZMoney);
		viewHolder.zmoney.setText(zCoupons.get(arg0).ZMoney);
		viewHolder.dtExchangeTime.setText(zCoupons.get(arg0).dtExchangeTime);
		viewHolder.dtValidateTime.setText(zCoupons.get(arg0).dtValidateTime);
		viewHolder.id.setText(zCoupons.get(arg0).counponId);
		viewHolder.payCode.setText(zCoupons.get(arg0).paycode);
		if (zCoupons.get(arg0).state.equals("1")) {
			viewHolder.payTime.setVisibility(View.VISIBLE);
			viewHolder.payTime.setText(zCoupons.get(arg0).dtPayTime);
		}
		viewHolder.state.setText(zCoupons.get(arg0).state);
		viewHolder.title.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("onclick");
				if (viewHolder.contain.getVisibility() == View.VISIBLE) {
					viewHolder.contain.setVisibility(View.GONE);
					viewHolder.downIconDown
							.setBackgroundResource(R.drawable.triangle);
				} else {
					viewHolder.contain.setVisibility(View.VISIBLE);
					viewHolder.downIconDown
							.setBackgroundResource(R.drawable.triangle_down);
				}
			}
		});
		return arg1;
	}
}
