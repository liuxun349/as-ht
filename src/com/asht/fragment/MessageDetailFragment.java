package com.asht.fragment;

import com.asht.R;
import com.asht.controller.Controller;
import com.asht.model.Message;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MessageDetailFragment extends AshtFragment implements
		OnClickListener {
	private TextView titleNameTxv, messageNameTxv, messageContentTxv;
	private Message msg;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.message_content, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Controller.setNomePagTop(getActivity(), true, "消息详情");
		titleNameTxv = (TextView) getActivity().findViewById(R.id.title_text);
		messageContentTxv = (TextView) getActivity().findViewById(R.id.content);
		messageNameTxv = (TextView) getActivity().findViewById(R.id.title);

		messageContentTxv.setText(msg.message);
		messageNameTxv.setText(msg.messageTitle);

		getActivity().findViewById(R.id.tv_title_back).setOnClickListener(this);
	}

	public void bundleSource(Message msg) {
		this.msg = msg;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.tv_title_back:
			callback.back();
			break;
		}
	}
}
