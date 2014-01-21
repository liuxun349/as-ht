package com.asht.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.asht.R;
import com.asht.controller.Controller;
import com.asht.model.Advice;
import com.asht.model.Message;

public class AdviceAdapter extends BaseAdapter {
	static class ViewHolder {
		ImageView adviceIcon;
		TextView adviceContent;
		TextView adviceDate;
		TextView adviceState;
	}

	private Activity mActivity;
	private List<Advice> mAdvices;

	public AdviceAdapter(Activity activity, List<Advice> source) {
		// TODO Auto-generated constructor stub
		mActivity = activity;
		if (source != null) {
			mAdvices = source;
		}else{
			mAdvices = new ArrayList<Advice>();
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mAdvices.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mAdvices.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			int adviceContentWidth = mActivity.getWindowManager()
					.getDefaultDisplay().getWidth();
			adviceContentWidth /= 4;
			convertView = LayoutInflater.from(mActivity).inflate(
					R.layout.advice_list_item, null);
			viewHolder.adviceIcon = (ImageView) convertView
					.findViewById(R.id.advice_icon);
			viewHolder.adviceContent = (TextView) convertView
					.findViewById(R.id.advice_content);
			LayoutParams lp = viewHolder.adviceContent.getLayoutParams();
			lp.width = adviceContentWidth;
			viewHolder.adviceDate.setLayoutParams(lp);
			viewHolder.adviceState = (TextView) convertView
					.findViewById(R.id.advice_state);
			viewHolder.adviceDate = (TextView) convertView
					.findViewById(R.id.advice_time);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.adviceDate.setText(mAdvices.get(position).dtInputTime);
		viewHolder.adviceContent.setText(mAdvices.get(position).advice);
		viewHolder.adviceState.setText(mAdvices.get(position).state);
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println(" position " + position);
				Controller.openAdvice((Context) mActivity,
						mAdvices.get(position));
			}
		});
		return convertView;
	}

}
