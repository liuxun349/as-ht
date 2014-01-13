package com.asht.controller;

import android.content.Context;
import android.content.Intent;

public class Controller {
	public static void MainHomePageActivity(Context context){
		Intent intent = new Intent(context,MainActivity.class);
		intent.putExtra("page", "home");
		context.startActivity(intent);
	}
	public static void MainMessagePageActivity(Context context){
		Intent intent = new Intent(context,MainActivity.class);
		intent.putExtra("page", "message");
		context.startActivity(intent);		
	}
	public static void MainSafetyCenterPageActivity(Context context){
		Intent intent = new Intent(context,MainActivity.class);
		intent.putExtra("page", "safety");
		context.startActivity(intent);
	}
	public static void MainMorePageActivity(Context context){
		Intent intent = new Intent(context,MainActivity.class);
		intent.putExtra("page", "more");
		context.startActivity(intent);
	}
	public static void LoginActivity(Context context){
		Intent intent = new Intent(context, LoginActivity.class);
		context.startActivity(intent);
	}
	public static void RegisterActivity(Context context){
		Intent intent = new Intent(context,RegisterFirstActivity.class);
		context.startActivity(intent);
	}
}
