package com.asht.fragment;

import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.R;
import com.asht.controller.Controller;
import com.asht.utl.ApplictionManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class SendAdviceFragment extends AshtFragment implements OnClickListener {
	private TextView txtContent;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Controller.setNomePagTop(getActivity(), true, "新的意见反馈");
		getActivity().findViewById(R.id.tv_title_back).setOnClickListener(this);
		getActivity().findViewById(R.id.advice_send).setOnClickListener(this);
		txtContent = (TextView) getActivity().findViewById(R.id.advice_content);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.new_advice, null, false);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.tv_title_back:
			callback.back();
			break;

		case R.id.advice_send:
			if (checkTxt()) {
				final String content = txtContent.getText().toString().trim();
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							AsHt.getInstance().addAdvice(
									ApplictionManager.getInstance().userInfo,
									content);
						} catch (AsHtException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();
			}
			break;
		}
	}

	private boolean checkTxt() {
		// TODO Auto-generated method stub
		String content = txtContent.getText().toString().trim();
		if (content != null && content != "") {
			return true;
		}
		return false;
	}
}
