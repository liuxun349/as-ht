package com.asht.fragment;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.R;
import com.asht.adapter.ZMoneyAdapter;
import com.asht.model.ZCoupon;
import com.asht.utl.ApplictionManager;
import com.asht.view.ToastUtils;
import com.asht.view.WaitingDialog;

public class MyAcountZMoneyFragment extends AshtFragment implements
		OnClickListener {
	private Context mContext;
	private ListView mListView;
	private ZMoneyAdapter mAdapter;
	private Button btnPost;
	private EditText zvalue;
	private WaitingDialog waitingDialog;
	private List<ZCoupon> sourceZCoupon;

	private static final int GET_ZCOUPON_LIST_FAILED = 1;
	private static final int GET_ZCOUPON_LIST_SUCCESS = 2;
	private static final int EXCHANGE_ZB_FAILED = 3;
	private static final int EXCHANGE_ZB_SUCCESS = 4;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mListView = (ListView) getActivity().findViewById(R.id.zmoney_list);
		zvalue = (EditText) getActivity().findViewById(R.id.zvalue);
		btnPost = (Button) getActivity().findViewById(R.id.btnchange);
		btnPost.setOnClickListener(this);
		waitingDialog = new WaitingDialog(getActivity());
		waitingDialog.show();
		updateZCoupon();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.my_account_myzmoney, null, false);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0 == btnPost) {
			ExchangeZb();
		}

	};

	private void updateZCoupon() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				sourceZCoupon = null;
				try {
					sourceZCoupon = AsHt.getInstance().getZCouponsByOwner(
							ApplictionManager.getInstance().userInfo);
				} catch (AsHtException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Message msg = new Message();
				if (sourceZCoupon == null) {
					msg.arg1 = GET_ZCOUPON_LIST_FAILED;
				} else {
					msg.arg1 = GET_ZCOUPON_LIST_SUCCESS;
				}
				mHandler.sendMessage(msg);
			}
		}).start();
	}

	private void ExchangeZb() {
		final String value = zvalue.getText().toString().trim();
		waitingDialog.show();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				boolean isSuccess = false;
				try {
					AsHt.getInstance().exchangeZCoupon(
							ApplictionManager.getInstance().userInfo,
							Integer.parseInt(value));
					isSuccess = true;
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AsHtException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Message msg = new Message();
				if (isSuccess) {
					msg.arg1 = EXCHANGE_ZB_SUCCESS;
				} else {
					msg.arg1 = EXCHANGE_ZB_FAILED;
				}
				mHandler.sendMessage(msg);
			}
		}).start();
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			waitingDialog.dismiss();
			if (msg.arg1 == GET_ZCOUPON_LIST_SUCCESS) {
				mAdapter = new ZMoneyAdapter(getActivity(), sourceZCoupon);
				mListView.setAdapter(mAdapter);
				ToastUtils.getInit(getActivity()).show("获得成功");
			} else if (msg.arg1 == GET_ZCOUPON_LIST_FAILED) {
				ToastUtils.getInit(getActivity()).show("暂无Z券信息");
			} else if (msg.arg1 == EXCHANGE_ZB_FAILED) {
				ToastUtils.getInit(getActivity()).show("Z币不足，兑换失败");
			} else if (msg.arg1 == EXCHANGE_ZB_SUCCESS) {
				ToastUtils.getInit(getActivity()).show("兑换成功");
				updateZCoupon();
			}
		};
	};
}
