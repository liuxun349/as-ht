<<<<<<< HEAD
package com.asht.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.R;
import com.asht.controller.Controller;
import com.asht.utl.ApplictionManager;
import com.asht.view.WaitingDialog;

public class EditConsumptionPassword2Fragment extends AshtFragment implements
		OnClickListener {

	EditText et_newPwd, et_newPwd2;
	Button btn_submit, btn_getCheckNumber;
	private Activity mActivity;
	private Bundle mBundle;
	private WaitingDialog waitingDialog;
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			if((Boolean)msg.obj){
				waitingDialog.dismiss();
			}
			
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
		return inflater.inflate(R.layout.edit_consume_passwd_secend, container,
				false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Controller.setNomePagTop(getActivity(), "修改消费密码", true, true, "确定");

		mActivity.findViewById(R.id.tv_title_back).setOnClickListener(this);
		mActivity.findViewById(R.id.btnEdit).setOnClickListener(this);
		et_newPwd = (EditText) mActivity.findViewById(R.id.et_newPwd);
		et_newPwd2 = (EditText) mActivity.findViewById(R.id.et_newPwd2);
		
		waitingDialog = new WaitingDialog(getActivity());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_title_back:
			callback.back();
			break;
		case R.id.btnEdit:
			submitNewsPwd();
			break;
		default:
			break;
		}

	}

	private void submitNewsPwd() {
		final String newPwd = et_newPwd.getText().toString().trim();
		final String newPwd2 = et_newPwd2.getText().toString().trim();

		if (!newPwd.equals(newPwd2)) {
			return;
		}
		waitingDialog.show();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boolean isSuccess = false;
				try {
					isSuccess = AsHt.getInstance().modifyPayPasswd(
							ApplictionManager.getInstance().userInfo,
							mBundle.getString("checkNo"),
							mBundle.getString("cardNo"), newPwd);
				} catch (AsHtException e) {
					// TODO Auto-generated catch block
					isSuccess = false;
				}
				if (isSuccess) {
					System.out.println(" success ");
					Message message = new Message();
					message.obj = isSuccess;
					mHandler.sendMessage(message);
				}
			}
		}).start();
	}

	public void setBundle(Bundle bundle) {
		mBundle = bundle;
	}
}
=======
package com.asht.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.asht.view.WaitingDialog;

public class EditConsumptionPassword2Fragment extends AshtFragment implements
		OnClickListener {

	EditText et_newPwd, et_newPwd2;
	Button btn_submit, btn_getCheckNumber;
	private Activity mActivity;
	private Bundle mBundle;
	private WaitingDialog waitingDialog;
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			if((Boolean)msg.obj){
				waitingDialog.dismiss();
			}
			
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
		return inflater.inflate(R.layout.edit_consume_passwd_secend, container,
				false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Controller.setNomePagTop(getActivity(), "修改消费密码", true, true, "确定");

		mActivity.findViewById(R.id.tv_title_back).setOnClickListener(this);
		mActivity.findViewById(R.id.btnEdit).setOnClickListener(this);
		et_newPwd = (EditText) mActivity.findViewById(R.id.et_newPwd);
		et_newPwd2 = (EditText) mActivity.findViewById(R.id.et_newPwd2);
		
		waitingDialog = new WaitingDialog(getActivity());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_title_back:
			callback.back();
			break;
		case R.id.btnEdit:
			submitNewsPwd();
			break;
		default:
			break;
		}

	}

	private void submitNewsPwd() {
		final String newPwd = et_newPwd.getText().toString().trim();
		final String newPwd2 = et_newPwd2.getText().toString().trim();

		if (!newPwd.equals(newPwd2)) {
			return;
		}
		waitingDialog.show();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boolean isSuccess = false;
				try {
					isSuccess = AsHt.getInstance().modifyPayPasswd(
							ApplictionManager.getInstance().userInfo,
							mBundle.getString("checkNo"),
							mBundle.getString("cardNo"), newPwd);
				} catch (AsHtException e) {
					// TODO Auto-generated catch block
					isSuccess = false;
				}
				if (isSuccess) {
					System.out.println(" success ");
					Message message = new Message();
					message.obj = isSuccess;
					mHandler.sendMessage(message);
				}
			}
		}).start();
	}

	public void setBundle(Bundle bundle) {
		mBundle = bundle;
	}
}
>>>>>>> FETCH_HEAD
