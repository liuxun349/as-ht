package com.asht.controller;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.asht.adapter.AdvancedPagerAdapter;
import com.asht.interfaces.UIHanleLintener;
import com.asht.interfaces.UINotification;
import com.asht.ui.PullToRefreshView;
import com.asht.ui.PullToRefreshView.OnFooterRefreshListener;
import com.asht.ui.PullToRefreshView.OnHeaderRefreshListener;
import com.example.controller.RecommendController;
import com.asht.R;

public class MutualRecommendationActivity extends Activity implements
		OnPageChangeListener {

	// 患 和 医
	private RadioButton rb_illness, rb_doctor;

	// 患textview
	private PullToRefreshView tv_illness;

	// 医textview
	private PullToRefreshView tv_doctor;

	// pageview的adapter
	private AdvancedPagerAdapter advancedPagerAdapter;

	// 内容container
	private ViewPager vp_content;

	// 要显示的textview的集合
	private ArrayList<View> views;

	RecommendController recommendController1, recommendController2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recommend);

		// 对象初始化
		views = new ArrayList<View>();
		tv_illness = (PullToRefreshView) View.inflate(getApplicationContext(),
				R.layout.recommend, null);
		tv_doctor = (PullToRefreshView) View.inflate(getApplicationContext(),
				R.layout.recommend, null);

		// 添加到集合中
		views.add(tv_illness);
		views.add(tv_doctor);
		advancedPagerAdapter = new AdvancedPagerAdapter(views);

		// 根据控件的ID得到代表该控件的对象
		rb_illness = (RadioButton) findViewById(R.id.rb_illness);
		rb_doctor = (RadioButton) findViewById(R.id.rb_doctor);
		rb_doctor.setOnClickListener(RadioButtonOnclick);
		rb_illness.setOnClickListener(RadioButtonOnclick);
		vp_content = (ViewPager) findViewById(R.id.vp_content);

		vp_content.setAdapter(advancedPagerAdapter);
		tv_illness.setOnHeaderRefreshListener(illnessOnHeaderRefreshListener);
		tv_illness.setOnFooterRefreshListener(illnessOnFooterRefreshListener);
		tv_doctor.setOnHeaderRefreshListener(doctorOnHeaderRefreshListener);
		tv_doctor.setOnFooterRefreshListener(doctorOnFooterRefreshListener);

		vp_content.setOnPageChangeListener(this);

		if (recommendController1 == null) {
			recommendController1 = new RecommendController(
					MutualRecommendationActivity.this,
					(GridView) tv_illness.findViewById(R.id.gridview), true);
			recommendController1.setUIHandleLinstener(new UIHanleLintener() {

				@Override
				public void update(boolean isServer, boolean fag,
						boolean isTouch) {
					// TODO Auto-generated method stub

					if (!isTouch) {
						Toast.makeText(getApplicationContext(), "加载更新完成",
								Toast.LENGTH_LONG).show();
						return;
					}
					tv_illness.postDelayed(new Runnable() {

						@Override
						public void run() {
							tv_illness.onHeaderRefreshComplete();

							Toast.makeText(getApplicationContext(), "上拉更新完成",
									Toast.LENGTH_LONG).show();
						}
					}, 2000);
				}

				@Override
				public void deletefinish(boolean fag) {
					// TODO Auto-generated method stub

				}

				@Override
				public void addfinish(boolean fag) {
					// TODO Auto-generated method stub

				}

				@Override
				public void gengduo(boolean fag, boolean isTouch) {

					if (!isTouch) {
						Toast.makeText(getApplicationContext(), "加载更多更新完成",
								Toast.LENGTH_LONG).show();
						return;
					}
					tv_illness.postDelayed(new Runnable() {

						@Override
						public void run() {
							tv_illness.onFooterRefreshComplete();

							Toast.makeText(getApplicationContext(), "下拉更新完成",
									Toast.LENGTH_LONG).show();

						}
					}, 2000);
				}
			});
			recommendController1.setUINotification(new UINotification() {


				@Override
				public void notificationStart(int size) {
					// TODO Auto-generated method stub

				}

				@Override
				public void notificationSelected(int size) {
					// TODO Auto-generated method stub

				}

				@Override
				public void notificationLast() {
					// TODO Auto-generated method stub

				}

				@Override
				public void delete() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onClick(int index, View citem, Object object,
						List<?> list) {
					// TODO Auto-generated method stub
					
				}
			});
		}

		// TODO Auto-generated method stub
		if (recommendController2 == null) {
			recommendController2 = new RecommendController(
					MutualRecommendationActivity.this,
					(GridView) tv_doctor.findViewById(R.id.gridview), false);
			recommendController2.setUIHandleLinstener(new UIHanleLintener() {

				@Override
				public void update(boolean isServer, boolean fag,
						boolean isTouch) {
					// TODO Auto-generated method stub

					if (!isTouch) {
						Toast.makeText(getApplicationContext(), "加载更新完成",
								Toast.LENGTH_LONG).show();
						return;
					}
					tv_doctor.postDelayed(new Runnable() {

						@Override
						public void run() {
							tv_doctor.onHeaderRefreshComplete();
							Toast.makeText(getApplicationContext(), "上拉更新完成",
									Toast.LENGTH_LONG).show();
						}
					}, 2000);
				}

				@Override
				public void deletefinish(boolean fag) {
					// TODO Auto-generated method stub

				}

				@Override
				public void addfinish(boolean fag) {
					// TODO Auto-generated method stub

				}

				@Override
				public void gengduo(boolean fag, boolean isTouch) {

					if (!isTouch) {
						Toast.makeText(getApplicationContext(), "加载更多更新完成",
								Toast.LENGTH_LONG).show();
						return;
					}
					tv_doctor.postDelayed(new Runnable() {

						@Override
						public void run() {
							tv_doctor.onFooterRefreshComplete();

							Toast.makeText(getApplicationContext(), "下拉更新完成",
									Toast.LENGTH_LONG).show();

						}
					}, 2000);
				}
			});
			recommendController2.setUINotification(new UINotification() {


				@Override
				public void notificationStart(int size) {
					// TODO Auto-generated method stub

				}

				@Override
				public void notificationSelected(int size) {
					// TODO Auto-generated method stub

				}

				@Override
				public void notificationLast() {
					// TODO Auto-generated method stub

				}

				@Override
				public void delete() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onClick(int index, View citem, Object object,
						List<?> list) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		recommendController1.update(false, false);
		recommendController2.update(false, false);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		switch (arg0) {
		// 患
		case 0:
			rb_illness.setChecked(true);
			break;
		// 医
		case 1:
			rb_doctor.setChecked(true);
			break;
		default:
			break;
		}
	}

	OnHeaderRefreshListener illnessOnHeaderRefreshListener = new OnHeaderRefreshListener() {

		@Override
		public void onHeaderRefresh(PullToRefreshView view) {
			recommendController1.update(true, true);
		}
	};
	OnFooterRefreshListener illnessOnFooterRefreshListener = new OnFooterRefreshListener() {

		@Override
		public void onFooterRefresh(PullToRefreshView view) {
			recommendController1.gengduo(true, true);
		}
	};

	OnHeaderRefreshListener doctorOnHeaderRefreshListener = new OnHeaderRefreshListener() {

		@Override
		public void onHeaderRefresh(PullToRefreshView view) {
			recommendController2.update(true, true);
		}
	};
	OnFooterRefreshListener doctorOnFooterRefreshListener = new OnFooterRefreshListener() {

		@Override
		public void onFooterRefresh(PullToRefreshView view) {
			recommendController2.gengduo(true, true);
		}
	};

	OnClickListener RadioButtonOnclick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.rb_illness:
				vp_content.setCurrentItem(0, true);
				recommendController1.update(false, false);
				break;
			case R.id.rb_doctor:
				vp_content.setCurrentItem(1, true);
				recommendController2.update(false, false);
			default:
				break;
			}

		}
	};

}
