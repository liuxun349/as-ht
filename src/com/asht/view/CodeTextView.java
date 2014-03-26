/**
 * @author LLH
 * @emil lzliuxu@gmail.com
 */
package com.asht.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class CodeTextView extends TextView {
	/** 按钮不能操作的时间，即是用户等待的时间,默认为60秒 */
	private int cannotOperateTime = 60;
	/** 按钮是否可以点击 默认为true */
	private boolean isCanClick = true;
	/** 能点击时显示的文字 */
	private String canClickText;
	/** 事件 */
	private CanClickListener canClickListener;
	/** 计时 */
	private int countTime;

	private Handler mhandler = new Handler();

	Runnable mUpdateTimeRunable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			countTime += 1000;
			int allTime = cannotOperateTime * 1000;
			if (countTime >= allTime) {
				CodeTextView.this.setEnabled(true);
				CodeTextView.this.setText(canClickText);
//				canClickListener.canClick(true);
			} else {
				mhandler.postDelayed(mUpdateTimeRunable, 1000);
				setText("剩余" + (allTime - countTime) / 1000 + "秒");
			}
		}
	};

	public CodeTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CodeTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public CodeTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.canClickText = getText().toString();// 保存可点击时的文字信息
		this.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

//				startCharge(v);
			}
		});
	}

	/**
	 * 设置 再次操作的间隔时间
	 * 
	 * @param cannotOperateTime
	 *            再次操作的间隔时间，单位为秒
	 */
	public void setCannotOperateTime(int cannotOperateTime) {
		this.cannotOperateTime = cannotOperateTime;
	}

	/**
	 * 设置能点击时显示的文字
	 * 
	 * @param canClickText
	 */
	public void setCanClickText(String canClickText) {
		this.canClickText = canClickText;
		this.setText(canClickText);

	}

	/**
	 * 开始计时,限制操作
	 */
	public void startCharge(View v) {
		v.setEnabled(false);// 设置不可用
		countTime = 0;// 初始化为0
		mhandler.removeCallbacks(mUpdateTimeRunable);
		mhandler.postDelayed(mUpdateTimeRunable, 1000);
		if (canClickListener!=null) {
			
			canClickListener.canClick(false);
		}
	}

	public void setCanClickListener(CanClickListener canClickListener) {
		this.canClickListener = canClickListener;
	}

	/** 接口 */
	public interface CanClickListener {
		void canClick(boolean can);
	}

}
