package com.asht.controller;

import java.util.ArrayList;
import java.util.List;

import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.R;
import com.asht.adapter.MessageAdapter;
import com.asht.model.Message;
import com.asht.utl.ApplictionManager;
import com.asht.view.WaitingDialog;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

public class MainMessageActivity extends Activity {
	private WaitingDialog waitingDialog;
	private ListView listMessage;
	private List<Message> sourList;

	private static final int GET_SOUCE_ACCESS = 0;
	private static final int GET_SOUCE_FAILED = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_page);
		listMessage = (ListView) findViewById(R.id.message_list);
		listMessage.setDividerHeight(0);
		waitingDialog = new WaitingDialog(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		updateMessage();

		super.onResume();
	}

	private void updateMessage() {
		waitingDialog.show();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				sourList = null;
				try {
					sourList = AsHt.getInstance().searchMessages(
							ApplictionManager.getInstance().userInfo);
				} catch (AsHtException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				android.os.Message msg = new android.os.Message();
				if (sourList == null) {
					msg.arg1 = GET_SOUCE_FAILED;
				} else {
					msg.arg1 = GET_SOUCE_ACCESS;
				}
				mHandler.sendMessage(msg);
			}
		}).start();
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			waitingDialog.dismiss();
			if (msg.arg1 == GET_SOUCE_ACCESS) {
				MessageAdapter adapter = new MessageAdapter(
						MainMessageActivity.this, sourList);
				listMessage.setAdapter(adapter);
			}
		};
	};
}
