package com.asht.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.R;
import com.asht.controller.Controller;
import com.asht.utl.ApplictionManager;

@SuppressLint("ValidFragment")
public class EditLoginPasswordFragment extends AshtFragment implements
		OnClickListener {
	private Activity mActivity;
	private EditText et_oldLoginPwd, et_newLoginPwd, et_newLoginPwd2;
	private Button btn_submit;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

		};
	};

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		mActivity = activity;
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.edit_login_passwd, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Controller.setNomePagTop(getActivity(), true, "修改登录密码");
		mActivity.findViewById(R.id.tv_title_back).setOnClickListener(this);
		et_oldLoginPwd = (EditText) mActivity.findViewById(R.id.et_oldLoginPwd);
		et_newLoginPwd = (EditText) mActivity.findViewById(R.id.et_newLoginPwd);
		et_newLoginPwd2 = (EditText) mActivity
				.findViewById(R.id.et_newLoginPwd2);
		btn_submit = (Button) mActivity.findViewById(R.id.btnEdit);
		btn_submit.setText("确定");
		btn_submit.setOnClickListener(this);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_title_back:
			System.out.println(" out .. ");
			callback.back();
			break;
		case R.id.btn_submit:
			submitNewsPwd();
			break;
		default:
			break;
		}

	}

	private void submitNewsPwd() {
		final String oldPwd = et_oldLoginPwd.getText().toString();
		final String newPwd = et_newLoginPwd.getText().toString();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boolean isSuccess = false;

				try {
					isSuccess = AsHt.getInstance().modifyLoginPasswd(
							ApplictionManager.getInstance().userInfo, oldPwd,
							newPwd);
				} catch (AsHtException e) {
					// TODO Auto-generated catch block
					isSuccess = false;
				}
				if (isSuccess) {
					System.out.println(" success ");
				}
			}
		}).start();
		// UserBaseHandlerDAO.modifyLoginPassword("100001", oldPwd, newPwd,
		// new ConnCallback() {
		//
		// @Override
		// public void connCode(int code, String result) {
		// // TODO Auto-generated method stub
		// try {
		// if (code == 1) {
		// JSONObject json = new JSONObject(result);
		// if (json.getInt(Settings.RETURN_CODE) == Settings.RETURN_CODE_ACCESS)
		// {
		// ToastUtils.getInit(getApplicationContext())
		// .show("修改登陆密码成功");
		// System.out.println("修改登陆密码成功");
		// setResult(RESULT_CANCELED);
		// finish();
		// } else {
		// ToastUtils.getInit(getApplicationContext())
		// .show("修改登陆密码失败");
		// System.out.println("修改登陆密码失败");
		// }
		// }else{
		// ToastUtils.getInit(getApplicationContext()).show("正在请求中 请稍后。。。");
		// }
		//
		// } catch (Exception e) {
		// }
		// }
		//
		// });
	}
}
