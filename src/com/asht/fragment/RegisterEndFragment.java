package com.asht.fragment;

import org.json.JSONObject;

import android.app.Activity;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.R;
import com.asht.controller.Controller;
import com.asht.fragment.AshtFragment;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;
import com.asht.utl.ConnCallback;
import com.asht.utl.Settings;
import com.asht.view.ToastUtils;
import com.asht.view.WaitingDialog;

public class RegisterEndFragment extends AshtFragment {
	private Button post;
	private EditText login_pwd, login_pwd_agin, pay_pwd, pay_pwd_agin,
			securityA_1, securityA_2;
	private TextView infoView_login, infoView_pay;
	private Spinner securityQ_1, securityQ_2;
	private ArrayAdapter adapter1, adapter2;
	private String loginPwd, payPwd, securityA1, securityA2;
	private Activity mActivity;
	private WaitingDialog waitingDialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.register_end, null, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Controller.setNomePagTop(getActivity(), true, true, "注册");
		init(getActivity());
		setSpinner();

		waitingDialog = new WaitingDialog(mActivity);
		post.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getDataFromWidgt();
				waitingDialog.show();
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						boolean isSuccess = false;
						try {
							isSuccess = AsHt.getInstance().regist(
									ApplictionManager.getInstance().userInfo);
						} catch (AsHtException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Message msg = new Message();
						msg.arg1 = 2;
						msg.obj = isSuccess;
						mHandler.sendMessage(msg);
					}
				}).start();
			}
		});
	}

	private void getDataFromWidgt() {

		int Q1 = securityQ_1.getSelectedItemPosition();
		int Q2 = securityQ_2.getSelectedItemPosition();

		if (checkLoginPwd() && checkAnswerIsNull() && checkPayPwd()) {
			UserInfo userInfo = ApplictionManager.getInstance().userInfo;
			userInfo.setSecutrityQA(Q1, securityA1, Q2, securityA2);
			userInfo.setUserLoginPwd(loginPwd);
			userInfo.setUserPayPwd(payPwd);
		} else {
			System.out.println("输入有误....");
		}
	}

	private void setSpinner() {
		adapter1 = ArrayAdapter.createFromResource(mActivity,
				R.array.securityQ, android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		securityQ_1.setAdapter(adapter1);
		securityQ_2.setAdapter(adapter1);
	}

	private void init(Activity mActivity) {
		this.mActivity = mActivity;
		post = (Button) mActivity.findViewById(R.id.btnEdit);
		mActivity.findViewById(R.id.tv_title_back).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						callback.back();
					}
				});
		post.setText("确定注册");
		login_pwd = (EditText) mActivity.findViewById(R.id.login_pwd);
		login_pwd_agin = (EditText) mActivity.findViewById(R.id.login_pwd_agin);
		pay_pwd = (EditText) mActivity.findViewById(R.id.pay_pwd);
		pay_pwd_agin = (EditText) mActivity.findViewById(R.id.pay_pwd_agin);
		securityA_1 = (EditText) mActivity.findViewById(R.id.securityA_1);
		securityA_2 = (EditText) mActivity.findViewById(R.id.securityA_2);
		securityQ_1 = (Spinner) mActivity
				.findViewById(R.id.register_securityQ_1);
		securityQ_2 = (Spinner) mActivity
				.findViewById(R.id.register_securityQ_2);
		infoView_login = (TextView) mActivity
				.findViewById(R.id.wrongInfo_login);
		infoView_pay = (TextView) mActivity.findViewById(R.id.wrongInfo_pay);
		mActivity.findViewById(R.id.regist_scroller).setOnTouchListener(
				onScrollerTouchListener);
	}

	private boolean checkLoginPwd() {
		String pwd1 = login_pwd.getText().toString().trim();
		String pwd2 = login_pwd_agin.getText().toString().trim();
		if (!pwd1.equals(pwd2)) {
			infoView_pay.setVisibility(View.VISIBLE);
			ToastUtils.getInit(mActivity).show("密码不一致");
			return false;
		}
		if (pwd1.length() < 6 | pwd1.length() > 16) {
			infoView_pay.setVisibility(View.VISIBLE);
			ToastUtils.getInit(mActivity).show("密码长度太短");
			return false;
		}
		loginPwd = pwd1;
		return true;
	}

	private boolean checkPayPwd() {
		String pwd1 = pay_pwd.getText().toString().trim();
		String pwd2 = pay_pwd_agin.getText().toString().trim();
		if (!pwd1.equals(pwd2)) {
			infoView_pay.setVisibility(View.VISIBLE);
			return false;
		}
		if (pwd1.length() < 6 | pwd1.length() > 16) {
			infoView_pay.setVisibility(View.VISIBLE);
			return false;
		}
		payPwd = pwd1;
		return true;
	}

	private boolean checkAnswerIsNull() {
		String answer1 = securityA_1.getText().toString().trim();
		String answer2 = securityA_2.getText().toString().trim();
		if (answer1.equals("") || answer2.equals("")) {
			return false;
		}
		securityA1 = answer1;
		securityA2 = answer2;
		return true;
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.arg1 == 2) {
				waitingDialog.dismiss();

				if ((Boolean) msg.obj) {
					ToastUtils.getInit(getActivity()).show("注册成功");
					Controller.LoginActivity(mActivity);
				} else {
					ToastUtils.getInit(getActivity()).show("注册失败");
				}
			}
		};
	};

}
