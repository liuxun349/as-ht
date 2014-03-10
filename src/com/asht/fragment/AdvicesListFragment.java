package com.asht.fragment;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ListView;

import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.R;
import com.asht.adapter.AdviceAdapter;
import com.asht.controller.Controller;
import com.asht.model.Advice;
import com.asht.utl.ApplictionManager;
import com.asht.view.ToastUtils;
import com.asht.view.WaitingDialog;

public class AdvicesListFragment extends AshtFragment {
	private ListView mListView;
	private List<Advice> sourceAdvices;
	private View addAdvice;
	private WaitingDialog waitingDialog;

	private static final int GET_ADVICE_LIST_SUCCESS = 1;
	private static final int GET_ADVICE_LIST_FAILED = 2;

	public AdvicesListFragment() {

	}

	public AdvicesListFragment(List<Advice> source) {
		// TODO Auto-generated constructor stub
		this.sourceAdvices = source;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Controller.setNomePagTop(getActivity(), "意见反馈", true, true, "我要反馈");
		waitingDialog = new WaitingDialog(getActivity());

		mListView = (ListView) getActivity().findViewById(R.id.advice_list);
		setTouchListener();
		addAdvice = getActivity().findViewById(R.id.btnEdit);
		getActivity().findViewById(R.id.tv_title_back).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						callback.back();
					}
				});
		addAdvice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Controller.newAdvice(getActivity());
			}
		});
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		updateAdvice();
	}

	private void updateAdvice() {
		// TODO Auto-generated method stub
		waitingDialog.show();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boolean isSuccess = false;
				try {
					sourceAdvices = AsHt.getInstance().searchAdvices(
							ApplictionManager.getInstance().userInfo);
					isSuccess = true;
				} catch (AsHtException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Message msg = new Message();
				if (sourceAdvices != null) {
					msg.arg1 = GET_ADVICE_LIST_SUCCESS;
				} else {
					msg.arg1 = GET_ADVICE_LIST_FAILED;
				}
				mHandler.sendMessage(msg);
			}
		}).start();
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			waitingDialog.dismiss();
			if (msg.arg1 == GET_ADVICE_LIST_SUCCESS) {
				AdviceAdapter adapter = new AdviceAdapter(getActivity(),
						sourceAdvices);
				mListView.setAdapter(adapter);
				System.out.println(" 1 " + sourceAdvices.get(0).txtadvice + " "
						+ sourceAdvices.get(0).dtinputtime);
				ToastUtils.getInit(getActivity()).show("更新成功");
			} else if (msg.arg1 == GET_ADVICE_LIST_FAILED) {
				ToastUtils.getInit(getActivity()).show("未获得数据");
			}
		};
	};

	private void setTouchListener() {
		// TODO Auto-generated method stub
		final int width = getActivity().getWindowManager().getDefaultDisplay()
				.getWidth() / 2;
		mListView.setOnTouchListener(new OnTouchListener() {
			private float mLastx = 0;
			private float x = 0;

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					mLastx = x = arg1.getX();
					break;
				case MotionEvent.ACTION_MOVE:
					mLastx = arg1.getX();
					break;
				case MotionEvent.ACTION_UP:
					if (mLastx - x > width) {
						callback.back();
					}
					break;
				}
				return true;
			}
		});

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.advice_list, null, false);
	}
}
