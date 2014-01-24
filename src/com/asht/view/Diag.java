package com.asht.view;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;

import com.asht.R;

public class Diag extends Dialog {

	public Diag(Context context) {
		super(context, R.style.CustomDialog);
		setContentView(R.layout.yj_pro);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		return true;
	}

}
