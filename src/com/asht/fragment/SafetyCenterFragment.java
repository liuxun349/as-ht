package com.asht.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;

import com.asht.R;
import com.asht.controller.Controller;

public class SafetyCenterFragment extends AshtFragment implements
		OnClickListener {
	private View editPayPwd;
	private View editLoginPwd;
	private View findPayPwd;
	private View findLoginPwd;
	private View changePhoneNo;
	private Context mContext;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Controller.setNomePagTop(getActivity(), true, "安全中心");
		init(getActivity());
		setListener();
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		mContext = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.security_center, null, false);
		view.setOnTouchListener(new OnTouchListener() {
			private float mLastx = 0;
			private float x = 0;

			public boolean onTouch(View arg0, MotionEvent arg1) {

				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					mLastx = x = arg1.getX();
					break;
				case MotionEvent.ACTION_MOVE:
					mLastx = arg1.getX();
					break;
				case MotionEvent.ACTION_UP:
					if (mLastx - x > ((Activity) mContext).getWindowManager()
							.getDefaultDisplay().getWidth() / 2) {
						callback.back();
					}
					break;
				}
				return true;
			}
		});
		return view;
	}

	private void init(Activity mActivity) {
		editLoginPwd = mActivity.findViewById(R.id.safety_editloginPwd);
		editPayPwd = mActivity.findViewById(R.id.safety_editPayPwd);
		findPayPwd = mActivity.findViewById(R.id.safety_findPayPwd);
		changePhoneNo = mActivity.findViewById(R.id.safety_changePhoneNo);
	}

	private void setListener() {
		editLoginPwd.setOnClickListener(this);
		editPayPwd.setOnClickListener(this);
		findPayPwd.setOnClickListener(this);
		changePhoneNo.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == editLoginPwd) {
			Controller.EditLoginPwd(mContext);
		} else if (v == editPayPwd) {
			Controller.EditPayPwd(mContext);
		} else if (v == findPayPwd) {
			Controller.findPayPwd(mContext);
		} else if (v == changePhoneNo) {
			Controller.changePhoneNo(mContext);
		}
	}

}
