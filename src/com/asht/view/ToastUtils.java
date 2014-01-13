package com.asht.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.asht.R;

@SuppressLint("ShowToast")
public class ToastUtils extends Handler {
	private Toast toast;
	private static ToastUtils tu;
	TextView tv;
	LinearLayout ll = null;

	private ToastUtils(Context context) {
		toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
		ll = new LinearLayout(context);
		tv = new TextView(context);
		tv.setTextColor(Color.BLACK);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, 14);
		tv.setPadding(10, 5, 10, 4);
		ll.addView(tv);
		ll.setBackgroundResource(R.drawable.dialog_full_holo_light);
		toast.setView(ll);
	}

	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		toast.show();
	}

	public static ToastUtils getInit(Context c) {
		if (tu == null) {
			tu = new ToastUtils(c);
		}
		return tu;
	}

	public void show(String str) {
		tv.setText(str);
		sendEmptyMessage(1);
	}

	public void show(int id) {
		tv.setText(id);
		sendEmptyMessage(1);
	}
}
