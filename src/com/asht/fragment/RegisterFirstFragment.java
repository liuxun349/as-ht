package com.asht.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.R;
import com.asht.controller.Controller;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;
import com.asht.utl.Settings;
import com.asht.view.CodeTextView;
import com.asht.view.CodeTextView.CanClickListener;
import com.asht.view.WaitingDialog;

public class RegisterFirstFragment extends AshtFragment implements
		OnClickListener {

	private static final int SUCCESS = 1;
	private static final int FAIL = 2;

	private EditText phoneNum;
	private CodeTextView getcheckNum;
	private EditText checkNum;
	private Button next;
	private UserInfo userInfo;
	private WaitingDialog waitingDialog;
	private String captchaId;
	private Button back;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			waitingDialog.dismiss();
			if (msg.arg1 == SUCCESS) {
				getcheckNum.startCharge(getcheckNum);
			} else {

			}
		};
	};

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.register_first, null, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Controller.setNomePagTop(getActivity(), true, true, "注册");

		init(getActivity());
		setListener();

	}

	private void setListener() {
		// TODO Auto-generated method stub
		// 验证手机号与手机验证码
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String phoneId = phoneNum.getText().toString().trim();
				String cheknumber = checkNum.getText().toString().trim();
				if (!(phoneId.equals("") && cheknumber.equals(""))) {
					userInfo = new UserInfo();
					userInfo.setUserPhoneNo(phoneId);
					userInfo.setCaptchaId(captchaId);
					userInfo.setCheckName(cheknumber);

					ApplictionManager.getInstance().userInfo = userInfo;
					nextFragment();
				}
			}

		});
		getcheckNum.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println("获得验证码");
				waitingDialog.show();
				sendRequest();

			}
		});
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callback.back();
			}
		});
	}

	private void init(Activity mActivity) {
		userInfo = new UserInfo();
		phoneNum = (EditText) mActivity.findViewById(R.id.register_phone);
		getcheckNum = (CodeTextView) mActivity
				.findViewById(R.id.get_check_number);
		checkNum = (EditText) mActivity
				.findViewById(R.id.register_check_number);
		next = (Button) mActivity.findViewById(R.id.btnEdit);
		next.setText("下一步");
		back = (Button) mActivity.findViewById(R.id.tv_title_back);
		waitingDialog = new WaitingDialog(mActivity);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0 == getcheckNum) {

		}
	}

	public void sendRequest() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String phoneNo = phoneNum.getText().toString().trim();
				boolean isSuccess = false;
				System.out.println(" phone " + phoneNo);
				try {
					isSuccess = true;
					String rs = (String) AsHt.getInstance()
							.sendVerificationCode(phoneNo, null,
									Settings.REASON_TYPE_REGIST);
					if (rs != null) {
						captchaId = JSON.parseObject(rs).getString("CaptchaId");
					} else {
						captchaId = null;
					}
				} catch (AsHtException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Message msg = new Message();
				if (isSuccess) {
					msg.arg1 = SUCCESS;
					mHandler.sendMessage(msg);
				} else {
					msg.arg1 = FAIL;
					mHandler.sendMessage(msg);
				}
			}
		}).start();
	}

	private void nextFragment() {
		AshtFragment mFragment = new RegisterSecendFragment();
		mFragment.setAshtFragmentCallback(callback);
		FragmentTransaction mTransaction = getFragmentManager()
				.beginTransaction();
		mTransaction.replace(R.id.fragment_container, mFragment);
		mTransaction.addToBackStack(null);
		mTransaction.commit();
	}
}
