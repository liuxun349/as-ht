package com.asht.controller;

import java.util.ArrayList;
import java.util.List;

import com.asht.R;
import com.asht.adapter.MessageAdapter;
import com.asht.model.Message;
import com.asht.view.WaitingDialog;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainMessageActivity extends Activity {
	private ListView listMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_page);
		listMessage = (ListView) findViewById(R.id.message_list);
		List<Message> list = new ArrayList<Message>();

		MessageAdapter adapter = new MessageAdapter(this, list);
		listMessage.setAdapter(adapter);
	}
}
