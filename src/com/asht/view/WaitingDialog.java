package com.asht.view;

import com.asht.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class WaitingDialog {
	AnimationDrawable rocketAnimation;
	private Dialog dialog;
	public WaitingDialog(Context context) {
		// TODO Auto-generated constructor stub
		View view = LayoutInflater.from(context).inflate(R.layout.waiting, null);
		Animation animation = AnimationUtils.loadAnimation(context, R.anim.loading);
		view.findViewById(R.id.waitingImage).setAnimation(animation);
		dialog = new AlertDialog.Builder(context).setView(view).setTitle("加载中：").create();
	}
	public void show(){
		dialog.show();
	}
	public void dismiss(){
		dialog.dismiss();
	}
}
