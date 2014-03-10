package com.asht.fragment;

import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.R;
import com.asht.controller.Controller;
import com.asht.utl.ApplictionManager;
import com.asht.view.ToastUtils;
import com.asht.view.WaitingDialog;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class SendAdviceFragment extends AshtFragment implements OnClickListener {
	private TextView txtContent;
	private WaitingDialog waitingDialog;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Controller.setNomePagTop(getActivity(), true, "新的意见反馈");
		getActivity().findViewById(R.id.tv_title_back).setOnClickListener(this);
		getActivity().findViewById(R.id.advice_send).setOnClickListener(this);
		txtContent = (TextView) getActivity().findViewById(R.id.advice_content);
		waitingDialog = new WaitingDialog(getActivity());
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
				waitingDialog.show();
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						boolean isSuccess = false;
						try {
							AsHt.getInstance().addAdvice(
									ApplictionManager.getInstance().userInfo,
									content);
							isSuccess = true;
						} catch (AsHtException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Message msg = new Message();
						msg.obj = isSuccess;
						mHandler.sendMessage(msg);
					}
				}).start();
			}
			break;
		}
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			waitingDialog.dismiss();
			if ((Boolean) msg.obj) {
				ToastUtils.getInit(getActivity()).show("您得意见已经发送到服务器。");
				callback.back();
			} else {
				ToastUtils.getInit(getActivity()).show("提交失败 ");
			}
		};
	};

	private boolean checkTxt() {
		// TODO Auto-generated method stub
		String content = txtContent.getText().toString().trim();
		if (content != null && content != "") {
			return true;
		}
		return false;
	}
}
