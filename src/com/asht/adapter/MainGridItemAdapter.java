package com.asht.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.asht.R;
import com.asht.controller.AddCasesActivity;
import com.asht.controller.MutualRecommendationActivity;
import com.asht.controller.MyCasesActivity;
import com.asht.controller.NewRecommendActivity;

public class MainGridItemAdapter extends BaseAdapter {

	private Context mContext;

	static class ViewHolder {
		ImageView icon;
		TextView name;
	}

	private int[] mimagesNor = { R.drawable.item_pay_nor,
			R.drawable.item_crowdfunding_nor, R.drawable.item_financial_nor,
			R.drawable.item_p2p_nor,

			R.drawable.item_virtualcurrency_nor, R.drawable.item_vip_nor };
	private int[] mimagesPress = { R.drawable.item_pay_press,
			R.drawable.item_crowdfunding_press,
			R.drawable.item_financial_press, R.drawable.item_p2p_press,
			R.drawable.item_virtualcurrency_press, R.drawable.item_vip_press };

	private int[] name = { R.string.main_txt_mycase,
			R.string.main_txt_recommend, R.string.main_txt_news,
			R.string.main_txt_community, R.string.main_txt_keephealth,
			R.string.main_txt_assistant };
	int width, height;

	public MainGridItemAdapter(Context c, int width, int height) {
		// TODO Auto-generated constructor stub
		mContext = c;
		this.width = width;
		this.height = height;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mimagesNor.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		final ViewHolder mhHolder;
		if (convertView == null) {

			mhHolder = new ViewHolder();
			View item_view = View.inflate(mContext, R.layout.gridview_item,
					null);
			mhHolder.icon = (ImageView) item_view.findViewById(R.id.item_image);
			mhHolder.name = (TextView) item_view.findViewById(R.id.item_name);
			convertView = item_view;
			AbsListView.LayoutParams ll = new AbsListView.LayoutParams(width,
					height, 1);
			convertView.setLayoutParams(ll);
			convertView.setTag(mhHolder);

		} else {
			mhHolder = (ViewHolder) convertView.getTag();
		}
		mhHolder.icon.setImageResource(mimagesNor[position]);
		mhHolder.name
				.setText(mContext.getResources().getString(name[position]));
		convertView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
					mhHolder.icon.setImageResource(mimagesPress[position]);
				}
				if (arg1.getAction() == MotionEvent.ACTION_UP) {
					mhHolder.icon.setImageResource(mimagesNor[position]);
					startIntent(position);
				}
				return true;
			}
		});
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
		return convertView;

	}

	private void startIntent(int position) {
		Intent intent = null;
		intent = new Intent();
		switch (position) {
		case 1:
			System.out.println(" " + position);
			intent.setClass(mContext, AddCasesActivity.class);
			break;
		case 0:
			System.out.println(" " + position);
			intent.setClass(mContext, MyCasesActivity.class);
			break;
		case 2:
			intent.setClass(mContext, NewRecommendActivity.class);
			break;
		case 3:
			intent.setClass(mContext, MutualRecommendationActivity.class);
			break;
		default:
			intent = null;
			break;
		}
		if (intent != null)
			mContext.startActivity(intent);
	}
}
