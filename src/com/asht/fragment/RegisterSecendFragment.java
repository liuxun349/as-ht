package com.asht.fragment;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TableRow;

import com.asht.R;
import com.asht.controller.Controller;
import com.asht.fragment.AshtFragment;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;
import com.asht.utl.AshtUtil;

public class RegisterSecendFragment extends AshtFragment {
	private Button btn_next;
	private EditText etx_nickName, etx_turename, etx_certificationNo,
			etx_mailePre, etx_mailelast, etx_xx;
	private String nickName, tureName, certificateNo, email;
	private Spinner etx_certificateType, etx_roleId;
	private int certificateType; // 证件类型(编号表示)
	private Activity mActivity;
	private TableRow tableRow;
	private View myScroller;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.register_secend, null, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Controller.setNomePagTop(getActivity(), true, true, "注册");
		init(getActivity());
		// 下一步
		btn_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				boolean isOk = checking();
				boolean isOk = true;
				if (isOk) {
					UserInfo userInfo = ApplictionManager.getInstance().userInfo;
					userInfo.setUserNickName(nickName);
					userInfo.setUserTrueName(tureName);
					userInfo.setUserCertificateNo(certificateNo);
					userInfo.setUserCertificateType(certificateType);
					userInfo.setUserEmail(email);

					System.out.println(" ===> " + userInfo.toJson().toString());
					nextFragment();
				}

			}
		});
	}

	/**
	 * 检查输入是否都合法
	 * 
	 * @return
	 */
	private boolean checking() {
		nickName = etx_nickName.getText().toString().trim();
		tureName = etx_turename.getText().toString().trim();
		certificateNo = etx_certificationNo.getText().toString().trim();
		email = etx_mailePre.getText().toString().trim();
		certificateType = 0;

		if (!AshtUtil.isEmail(email)) {
			System.out.println("密码格式不对");
			return false;
		} else if (!AshtUtil.IsChinese(tureName)) {
			System.out.println("真名格式不对");
			return false;
		} else if (!AshtUtil.IsIDcard(certificateNo)) {
			System.out.println("身份证格式不对");
			return false;
		}
		return true;
	}

	private void init(Activity activity) {
		mActivity = activity;
		btn_next = (Button) mActivity.findViewById(R.id.btnEdit);
		btn_next.setText("下一步");
		etx_certificateType = (Spinner) mActivity
				.findViewById(R.id.etx_certificateType);
		ArrayAdapter adapterCertificate = ArrayAdapter.createFromResource(
				getActivity(), R.array.documentType,
				android.R.layout.simple_spinner_item);
		adapterCertificate
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ArrayAdapter adapterRoleId = ArrayAdapter.createFromResource(
				getActivity(), R.array.yourIdentity,
				android.R.layout.simple_spinner_item);
		adapterRoleId
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		etx_certificateType.setAdapter(adapterCertificate);
		etx_certificationNo = (EditText) mActivity
				.findViewById(R.id.etx_certificateNo);
		etx_mailelast = (EditText) mActivity.findViewById(R.id.etx_mailePre);
		etx_roleId = (Spinner) mActivity.findViewById(R.id.etx_roleId);
		etx_roleId.setAdapter(adapterRoleId);
		etx_nickName = (EditText) mActivity.findViewById(R.id.etx_nickname);
		etx_turename = (EditText) mActivity.findViewById(R.id.etx_turename);
		myScroller = mActivity.findViewById(R.id.myscroll);
		myScroller.setOnTouchListener(onScrollerTouchListener);
	}

	private void nextFragment() {
		AshtFragment mFragment = new RegisterEndFragment();
		mFragment.setAshtFragmentCallback(callback);
		FragmentTransaction mTransaction = getFragmentManager()
				.beginTransaction();
		mTransaction.replace(R.id.fragment_container, mFragment);
		mTransaction.addToBackStack(null);
		mTransaction.commit();
	}

	
}
