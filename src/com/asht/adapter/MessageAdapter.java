package com.asht.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.asht.R;
import com.asht.controller.Controller;
import com.asht.model.Message;

public class MessageAdapter extends BaseAdapter {
	static class ViewHolder {
		ImageView messageIcon;
		TextView messageTitle;
		TextView messageTime;
	}

	private Context mContext;
	private List<Message> messageSource;

	public MessageAdapter(Context context, List<Message> source) {
		// TODO Auto-generated constructor stub
		mContext = context;
		if (source != null) {
			messageSource = source;
		} else {
			messageSource = new ArrayList<Message>();
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return messageSource.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return messageSource.get(arg0);
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
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.messages_list_item, null);
			viewHolder.messageIcon = (ImageView) convertView
					.findViewById(R.id.message_icon);
			viewHolder.messageTime = (TextView) convertView
					.findViewById(R.id.message_time);
			viewHolder.messageTitle = (TextView) convertView
					.findViewById(R.id.message_title);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.messageTime.setText(messageSource.get(position).dtinputtime);
		viewHolder.messageTitle.setText(messageSource.get(position).txtmassage);
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println(" position " + position);
				Controller.openMessage(mContext, messageSource.get(position));
			}
		});
		return convertView;
	}

}
