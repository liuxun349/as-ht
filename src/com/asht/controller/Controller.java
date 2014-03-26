<<<<<<< HEAD
package com.asht.controller;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asht.R;
import com.asht.fragment.AdviceDetailFragment;
import com.asht.fragment.AdvicesListFragment;
import com.asht.fragment.EditConsumptionPasswordFragment;
import com.asht.fragment.EditLoginPasswordFragment;
import com.asht.fragment.EditPhoneFragment;
import com.asht.fragment.EditPwdAnswerFragment;
import com.asht.fragment.FindPayPwdFragment;
import com.asht.fragment.MessageDetailFragment;
import com.asht.fragment.RegisterFirstFragment;
import com.asht.fragment.SafetyCenterFragment;
import com.asht.fragment.SendAdviceFragment;
import com.asht.model.Advice;
import com.asht.model.Message;
import com.asht.utl.ApplictionManager;

public class Controller {
	public static final String PAGE_HOME = "home";
	public static final String PAGE_MESSAGE = "message";
	public static final String PAGE_SAFETY = "safety";
	public static final String PAGE_MYACCOUNT = "myaccount";
	public static final String PAGE_MORE = "more";
	public static Bundle bundle;

	public static void setNomePagTop(Activity mActivity, boolean haveBackBtn,
			String titleName) {
		if (haveBackBtn) {
			Button back = (Button) mActivity.findViewById(R.id.tv_title_back);
			back.setVisibility(View.VISIBLE);
		}
		TextView titleNameTxv = (TextView) mActivity
				.findViewById(R.id.app_title_name);
		titleNameTxv.setText(titleName);
	}

	public static void setNomePagTop(Activity mActivity, boolean haveBackBtn,
			boolean haveRightBtn, String titleName) {
		if (haveBackBtn) {
			Button back = (Button) mActivity.findViewById(R.id.tv_title_back);
			back.setVisibility(View.VISIBLE);
		}
		if (haveRightBtn) {
			Button back = (Button) mActivity.findViewById(R.id.btnEdit);
			back.setVisibility(View.VISIBLE);
		}
		TextView titleNameTxv = (TextView) mActivity
				.findViewById(R.id.app_title_name);
		titleNameTxv.setText(titleName);
	}

	public static void setNomePagTop(Activity mActivity, String titleName,
			boolean haveBackBtn, boolean haveRightBtn, String btnName) {
		if (haveBackBtn) {
			Button back = (Button) mActivity.findViewById(R.id.tv_title_back);
			back.setVisibility(View.VISIBLE);
		}
		if (haveRightBtn) {
			Button back = (Button) mActivity.findViewById(R.id.btnEdit);
			back.setText(btnName);
			back.setVisibility(View.VISIBLE);
		}
		TextView titleNameTxv = (TextView) mActivity
				.findViewById(R.id.app_title_name);
		titleNameTxv.setText(titleName);
	}

	/**
	 * 主页
	 * 
	 * @param context
	 */
	public static void MainHomePageActivity(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		intent.putExtra("page", PAGE_HOME);
		context.startActivity(intent);
	}

	/**
	 * 消息页面
	 * 
	 * @param context
	 */
	public static void MainMessagePageActivity(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		intent.putExtra("page", PAGE_MESSAGE);
		context.startActivity(intent);
	}

	/**
	 * 我的账号页面
	 * 
	 * @param context
	 */
	public static void MainMyAccountPageActivity(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		intent.putExtra("page", PAGE_MYACCOUNT);
		context.startActivity(intent);
	}

	/**
	 * 更多页面
	 * 
	 * @param context
	 */
	public static void MainMorePageActivity(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		intent.putExtra("page", PAGE_MORE);
		context.startActivity(intent);
	}

	/**
	 * 登录
	 * 
	 * @param context
	 */
	public static void LoginActivity(Context context) {
		Intent intent = new Intent(context, LoginActivity.class);
		context.startActivity(intent);
	}

	/**
	 * 注册
	 * 
	 * @param context
	 */
	public static void RegisterActivity(Context context) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		RegisterFirstFragment mFragment = new RegisterFirstFragment();
		ApplictionManager.getInstance().currentFragment = mFragment;
		context.startActivity(intent);
	}

	/**
	 * 修改登录密码页面
	 * 
	 * @param context
	 */
	public static void EditLoginPwd(Context context) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		ApplictionManager.getInstance().currentFragment = new EditLoginPasswordFragment();
		context.startActivity(intent);
	}

	/**
	 * 找回登录密码页面
	 * 
	 * @param context
	 */
	public static void findLoginPwd(Context context) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		ApplictionManager.getInstance().currentFragment = new EditLoginPasswordFragment();
		context.startActivity(intent);
	}

	/**
	 * 找回消费密码页面
	 * 
	 * @param context
	 */
	public static void findPayPwd(Context context) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		ApplictionManager.getInstance().currentFragment = new FindPayPwdFragment();
		context.startActivity(intent);
	}

	/**
	 * 修改手机号页面
	 * 
	 * @param context
	 */
	public static void changePhoneNo(Context context) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		ApplictionManager.getInstance().currentFragment = new EditPhoneFragment();
		context.startActivity(intent);
	}

	/**
	 * 修改消费密码页面
	 * 
	 * @param context
	 */
	public static void EditPayPwd(Context context) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		ApplictionManager.getInstance().currentFragment = new EditConsumptionPasswordFragment();
		context.startActivity(intent);
	}

	/**
	 * 修改密保问题
	 * 
	 * @param context
	 */
	public static void editPwdAnswer(Context context) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		ApplictionManager.getInstance().currentFragment = new EditPwdAnswerFragment();
		context.startActivity(intent);
	}

	/**
	 * 打开Message详细界面
	 * 
	 * @param context
	 * @param msg
	 */
	public static void openMessage(Context context, Message msg) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		MessageDetailFragment mFragment = new MessageDetailFragment();
		mFragment.bundleSource(msg);
		ApplictionManager.getInstance().currentFragment = mFragment;
		System.out.println(" 1 ");
		context.startActivity(intent);
	}

	/**
	 * 意见反馈界面
	 * 
	 * @param context
	 * @param advices
	 */
	public static void Advice(Context context, List<Advice> advices) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		AdvicesListFragment mFragment = new AdvicesListFragment(advices);
		ApplictionManager.getInstance().currentFragment = mFragment;
		context.startActivity(intent);
	}

	/**
	 * 打开Advice详细界面
	 * 
	 * @param context
	 * @param advice
	 */
	public static void openAdvice(Context context, Advice advice) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		AdviceDetailFragment mFragment = new AdviceDetailFragment(advice);
		ApplictionManager.getInstance().currentFragment = mFragment;
		context.startActivity(intent);
	}

	/**
	 * 增加新反馈
	 * 
	 * @param context
	 */
	public static void newAdvice(Context context) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		SendAdviceFragment mFragment = new SendAdviceFragment();
		ApplictionManager.getInstance().currentFragment = mFragment;
		context.startActivity(intent);
	}

	/**
	 * 安全中心页面
	 * 
	 * @param context
	 */
	public static void SecurityCenter(Context context) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		SafetyCenterFragment mFragment = new SafetyCenterFragment();
		ApplictionManager.getInstance().currentFragment = mFragment;
		context.startActivity(intent);
	}

}
=======
package com.asht.controller;

import java.util.List;

import com.asht.R;
import com.asht.fragment.AdviceDetailFragment;
import com.asht.fragment.AdvicesListFragment;
import com.asht.fragment.EditConsumptionPasswordFragment;
import com.asht.fragment.EditLoginPasswordFragment;
import com.asht.fragment.EditPhoneFragment;
import com.asht.fragment.EditPwdAnswerFragment;
import com.asht.fragment.FindPayPwdFragment;
import com.asht.fragment.MessageDetailFragment;
import com.asht.fragment.RegisterFirstFragment;
import com.asht.fragment.SafetyCenterFragment;
import com.asht.fragment.SendAdviceFragment;
import com.asht.model.Advice;
import com.asht.model.Message;
import com.asht.utl.ApplictionManager;
import com.asht.utl.ExampleUtil;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Controller {
	public static final String PAGE_HOME = "home";
	public static final String PAGE_MESSAGE = "message";
	public static final String PAGE_SAFETY = "safety";
	public static final String PAGE_MYACCOUNT = "myaccount";
	public static final String PAGE_MORE = "more";
	public static Bundle bundle;
	private MessageReceiver mMessageReceiver;
	private static Controller mInstance;

	public static void initalize() {
		mInstance = new Controller();
	}

	public static Controller getInstance() {
		if (mInstance == null) {
			initalize();
		}
		return mInstance;
	}

	public Controller() {
		// TODO Auto-generated constructor stub
		mMessageReceiver = new MessageReceiver();
	}

	public static void setNomePagTop(Activity mActivity, boolean haveBackBtn,
			String titleName) {
		if (haveBackBtn) {
			Button back = (Button) mActivity.findViewById(R.id.tv_title_back);
			back.setVisibility(View.VISIBLE);
		}
		TextView titleNameTxv = (TextView) mActivity
				.findViewById(R.id.app_title_name);
		titleNameTxv.setText(titleName);
	}

	public static void setNomePagTop(Activity mActivity, boolean haveBackBtn,
			boolean haveRightBtn, String titleName) {
		if (haveBackBtn) {
			Button back = (Button) mActivity.findViewById(R.id.tv_title_back);
			back.setVisibility(View.VISIBLE);
		}
		if (haveRightBtn) {
			Button back = (Button) mActivity.findViewById(R.id.btnEdit);
			back.setVisibility(View.VISIBLE);
		}
		TextView titleNameTxv = (TextView) mActivity
				.findViewById(R.id.app_title_name);
		titleNameTxv.setText(titleName);
	}

	public static void setNomePagTop(Activity mActivity, String titleName,
			boolean haveBackBtn, boolean haveRightBtn, String btnName) {
		if (haveBackBtn) {
			Button back = (Button) mActivity.findViewById(R.id.tv_title_back);
			back.setVisibility(View.VISIBLE);
		}
		if (haveRightBtn) {
			Button back = (Button) mActivity.findViewById(R.id.btnEdit);
			back.setText(btnName);
			back.setVisibility(View.VISIBLE);
		}
		TextView titleNameTxv = (TextView) mActivity
				.findViewById(R.id.app_title_name);
		titleNameTxv.setText(titleName);
	}

	// ------------------------------ jpush 广播 －－－－－－－

	// for receive customer msg from jpush server
	public static final String MESSAGE_RECEIVED_ACTION = "cn.jpush.android.intent.MESSAGE_RECEIVED";
	public static final String KEY_TITLE = "title";
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_EXTRAS = "extras";

	public void registerMessageReceiver(Context mContext) {
		IntentFilter filter = new IntentFilter();
		filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		filter.addAction(MESSAGE_RECEIVED_ACTION);

		mContext.registerReceiver(mMessageReceiver, filter);
	}

	public void unregisterMessageReceiver(Context mContext) {
		mContext.unregisterReceiver(mMessageReceiver);
	}

	public class MessageReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
				String messge = intent.getStringExtra(KEY_MESSAGE);
				String extras = intent.getStringExtra(KEY_EXTRAS);
				StringBuilder showMsg = new StringBuilder();
				showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
				if (!ExampleUtil.isEmpty(extras)) {
					showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
				}
				// setCostomMsg(showMsg.toString());
				System.out.println(" out ... " + showMsg);
			}
		}
	}

	// -------------------------------------------------

	/**
	 * 主页
	 * 
	 * @param context
	 */
	public static void MainHomePageActivity(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		intent.putExtra("page", PAGE_HOME);
		context.startActivity(intent);
	}

	/**
	 * 消息页面
	 * 
	 * @param context
	 */
	public static void MainMessagePageActivity(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		intent.putExtra("page", PAGE_MESSAGE);
		context.startActivity(intent);
	}

	/**
	 * 我的账号页面
	 * 
	 * @param context
	 */
	public static void MainMyAccountPageActivity(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		intent.putExtra("page", PAGE_MYACCOUNT);
		context.startActivity(intent);
	}

	/**
	 * 更多页面
	 * 
	 * @param context
	 */
	public static void MainMorePageActivity(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		intent.putExtra("page", PAGE_MORE);
		context.startActivity(intent);
	}

	/**
	 * 登录
	 * 
	 * @param context
	 */
	public static void LoginActivity(Context context) {
		Intent intent = new Intent(context, LoginActivity.class);
		context.startActivity(intent);
	}

	/**
	 * 注册
	 * 
	 * @param context
	 */
	public static void RegisterActivity(Context context) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		RegisterFirstFragment mFragment = new RegisterFirstFragment();
		ApplictionManager.getInstance().currentFragment = mFragment;
		context.startActivity(intent);
	}

	/**
	 * 修改登录密码页面
	 * 
	 * @param context
	 */
	public static void EditLoginPwd(Context context) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		ApplictionManager.getInstance().currentFragment = new EditLoginPasswordFragment();
		context.startActivity(intent);
	}

	/**
	 * 找回登录密码页面
	 * 
	 * @param context
	 */
	public static void findLoginPwd(Context context) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		ApplictionManager.getInstance().currentFragment = new EditLoginPasswordFragment();
		context.startActivity(intent);
	}

	/**
	 * 找回消费密码页面
	 * 
	 * @param context
	 */
	public static void findPayPwd(Context context) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		ApplictionManager.getInstance().currentFragment = new FindPayPwdFragment();
		context.startActivity(intent);
	}

	/**
	 * 修改手机号页面
	 * 
	 * @param context
	 */
	public static void changePhoneNo(Context context) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		ApplictionManager.getInstance().currentFragment = new EditPhoneFragment();
		context.startActivity(intent);
	}

	/**
	 * 修改消费密码页面
	 * 
	 * @param context
	 */
	public static void EditPayPwd(Context context) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		ApplictionManager.getInstance().currentFragment = new EditConsumptionPasswordFragment();
		context.startActivity(intent);
	}

	/**
	 * 修改密保问题
	 * 
	 * @param context
	 */
	public static void editPwdAnswer(Context context) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		ApplictionManager.getInstance().currentFragment = new EditPwdAnswerFragment();
		context.startActivity(intent);
	}

	/**
	 * 打开Message详细界面
	 * 
	 * @param context
	 * @param msg
	 */
	public static void openMessage(Context context, Message msg) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		MessageDetailFragment mFragment = new MessageDetailFragment();
		mFragment.bundleSource(msg);
		ApplictionManager.getInstance().currentFragment = mFragment;
		System.out.println(" 1 ");
		context.startActivity(intent);
	}

	/**
	 * 意见反馈界面
	 * 
	 * @param context
	 * @param advices
	 */
	public static void Advice(Context context, List<Advice> advices) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		AdvicesListFragment mFragment = new AdvicesListFragment(advices);
		ApplictionManager.getInstance().currentFragment = mFragment;
		context.startActivity(intent);
	}

	/**
	 * 打开Advice详细界面
	 * 
	 * @param context
	 * @param advice
	 */
	public static void openAdvice(Context context, Advice advice) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		AdviceDetailFragment mFragment = new AdviceDetailFragment(advice);
		ApplictionManager.getInstance().currentFragment = mFragment;
		context.startActivity(intent);
	}

	/**
	 * 增加新反馈
	 * 
	 * @param context
	 */
	public static void newAdvice(Context context) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		SendAdviceFragment mFragment = new SendAdviceFragment();
		ApplictionManager.getInstance().currentFragment = mFragment;
		context.startActivity(intent);
	}

	/**
	 * 安全中心页面
	 * 
	 * @param context
	 */
	public static void SecurityCenter(Context context) {
		Intent intent = new Intent(context, ContainerFragmentActivity.class);
		SafetyCenterFragment mFragment = new SafetyCenterFragment();
		ApplictionManager.getInstance().currentFragment = mFragment;
		context.startActivity(intent);
	}

}
>>>>>>> FETCH_HEAD
