package com.asht.controller;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.asht.R;

/**
 * 我的账户
 * 
 * @author Administrator
 * 
 */
public class MyAcountActivity extends Activity implements OnClickListener {

	private Button editOrPost;
	private ViewPager viewPager;// 页卡内容
	private ImageView imageView;// 动画图片
	private TextView textView1, textView2;
	private List<View> views;// Tab页面列表
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度
	// 各个页卡
	private MyAcountSelfInfo mSelfInfo;
	private MyAcountZMoney mZMoney;

	private boolean editIsPress = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_account_show);
		initView();
		InitImageView(); 
	}

	private void initView() {
		editOrPost = (Button) findViewById(R.id.btnEdit);
		viewPager = (ViewPager) findViewById(R.id.vPager);
		views = new ArrayList<View>();
		System.out.println("1");
		mSelfInfo = new MyAcountSelfInfo(this);
//		System.out.println("2");
		mZMoney = new MyAcountZMoney(this);
//		System.out.println("3");
//		views.add(mSelfInfo);
//		views.add(mZMoney);
		View view1=getLayoutInflater().inflate(R.layout.my_account, null);
		View view2=getLayoutInflater().inflate(R.layout.my_account_myzmoney, null);
		views.add(view1);
		views.add(view2);
		
		MyViewPagerAdapter myViewPagerAdapter =new MyViewPagerAdapter(views); 
		System.out.println("4");
		viewPager.setAdapter(myViewPagerAdapter);
		System.out.println("5");
		viewPager.setCurrentItem(0);
		System.out.println("6");
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

		textView1 = (TextView) findViewById(R.id.text1);
		textView2 = (TextView) findViewById(R.id.text2);

		textView1.setOnClickListener(this);
		textView2.setOnClickListener(this);
	}

	/**
	 * 2 * 初始化动画，这个就是页卡滑动时，下面的横线也滑动的效果，在这里需要计算一些数据 3
	 */

	private void InitImageView() {
		imageView = (ImageView) findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a)
				.getWidth();// 获取图片宽度
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 获取分辨率宽度
		offset = (screenW / 2 - bmpW) / 2;// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		imageView.setImageMatrix(matrix);// 设置动画初始位置
		System.out.println(" == offset "+offset);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == editOrPost) {
			if (!editIsPress) { // 编辑
				editIsPress = true;
				editOrPost.setText("确定");
				changeStateToEdit();
			}
		}
	}

	private void changeStateToEdit() {

	}

	public class MyViewPagerAdapter extends PagerAdapter {
		private List<View> mListViews;

		public MyViewPagerAdapter(List<View> mListViews) {
			System.out.println(" -0 - 0- 0-");
			this.mListViews = mListViews;
			System.out.println(" -0 - 0- 0- 2");
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(mListViews.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mListViews.get(position), 0);
			return mListViews.get(position);
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
		int two = one * 2;// 页卡1 -> 页卡3 偏移量
		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageSelected(int arg0) {
			/*
			 * 两种方法，这个是一种，下面还有一种，显然这个比较麻烦 Animation animation = null; switch
			 * (arg0) { case 0: if (currIndex == 1) { animation = new
			 * TranslateAnimation(one, 0, 0, 0); } else if (currIndex == 2) {
			 * animation = new TranslateAnimation(two, 0, 0, 0); } break; case
			 * 1: if (currIndex == 0) { animation = new
			 * TranslateAnimation(offset, one, 0, 0); } else if (currIndex == 2)
			 * { animation = new TranslateAnimation(two, one, 0, 0); } break;
			 * case 2: if (currIndex == 0) { animation = new
			 * TranslateAnimation(offset, two, 0, 0); } else if (currIndex == 1)
			 * { animation = new TranslateAnimation(one, two, 0, 0); } break;
			 * 
			 * }
			 */
			Animation animation = new TranslateAnimation(one * currIndex, one
					* arg0, 0, 0);// 显然这个比较简洁，只有一行代码。
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(300);
			imageView.startAnimation(animation);
			Toast.makeText(MyAcountActivity.this, "您选择了"+ viewPager.getCurrentItem()+"页卡", Toast.LENGTH_SHORT).show();
		}

	}
}
