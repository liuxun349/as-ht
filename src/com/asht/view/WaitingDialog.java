package com.asht.view;

import com.asht.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class WaitingDialog extends Dialog {
	AnimationDrawable rocketAnimation;
	public WaitingDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		setContentView(R.layout.waiting);
		Animation animation = AnimationUtils.loadAnimation(context, R.anim.loading);
		findViewById(R.id.waitingImage).setAnimation(animation);
	}

	

}
