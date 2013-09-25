package com.asht.utl;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
	private  Toast toast;
	private static ToastUtils tu;
	private ToastUtils(Context context){
		toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
	}
	
	public static ToastUtils getInit(Context c){
		if(tu==null){
			tu = new ToastUtils(c);
		}
		return tu;
	}
	
	public void show(String str){
		toast.setText(str);
		toast.show();
	}
}
