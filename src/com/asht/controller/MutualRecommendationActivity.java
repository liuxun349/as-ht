package com.asht.controller;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RadioButton;

import com.asht.AsHt;
import com.asht.AsyncDataLoader;
import com.asht.AsyncDataLoader.Callback;
import com.asht.R;
import com.asht.adapter.AdvancedPagerAdapter;
import com.asht.adapter.RecommendAdapter;
import com.asht.adapter.RecommendAdapter.OnItem;
import com.asht.model.Recommend;
import com.asht.model.UserInfo;
import com.asht.ui.PullToRefreshView;
import com.asht.ui.PullToRefreshView.OnFooterRefreshListener;
import com.asht.ui.PullToRefreshView.OnHeaderRefreshListener;
import com.asht.utl.ApplictionManager;
import com.example.controller.AFinalController;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

public class MutualRecommendationActivity extends Activity implements
		OnPageChangeListener {

	// 患 和 医
	private RadioButton rb_illness, rb_doctor;
	// 患textview 医textview
	private PullToRefreshView tv_illness, tv_doctor;
	View view_prigress;
	GridView gv_illness, gv_doctor;

	// pageview的adapter
	private AdvancedPagerAdapter advancedPagerAdapter;

	// 内容container
	private ViewPager vp_content;

	// 要显示的textview的集合
	private ArrayList<View> views;

	boolean isUpdate;

	RecommendAdapter adapter_ilness, adapter_doctor;

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
		tv_illness.setFoot(false);
		tv_doctor.setFoot(false);

		gv_illness = (GridView) tv_illness.findViewById(R.id.gridview);
		gv_doctor = (GridView) tv_doctor.findViewById(R.id.gridview);
		view_prigress = findViewById(R.id.prigress);
		view_prigress.setVisibility(View.GONE);
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
		int spacing = (int) this.getResources().getDimension(
				R.dimen.grid_spacing);

		// 获取分辨率
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		int width = (dm.widthPixels - spacing * 3) / 4; // 当前分辨率 宽度

		int height = width;

		adapter_ilness = new RecommendAdapter(this.getApplicationContext(),
				null, width, height);
		adapter_doctor = new RecommendAdapter(this.getApplicationContext(),
				null, width, height);
		gv_doctor.setAdapter(adapter_doctor);
		gv_illness.setAdapter(adapter_ilness);
		gv_doctor.setOnItemClickListener(gv_doctor_onitem);
		gv_illness.setOnItemClickListener(gv_itemClick);
		findViewById(R.id.tv_title_back).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						MutualRecommendationActivity.this.finish();
					}
				});
		isUpdate = true;
		update();
		adapter_ilness.setOnItem(new OnItem() {

			@Override
			public void onItemClick(Recommend info) {
				if (info == null) {
					addRecommend(0);
				} else {
					openRecommend(info);
				}
			}
		});
		adapter_doctor.setOnItem(new OnItem() {

			@Override
			public void onItemClick(Recommend info) {
				if (info == null) {
					addRecommend(1);
				} else {
					openRecommend(info);
				}
			}
		});
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
			isUpdate = true;
			update();
		}
	};
	OnFooterRefreshListener illnessOnFooterRefreshListener = new OnFooterRefreshListener() {

		@Override
		public void onFooterRefresh(PullToRefreshView view) {
			// recommendController1.gengduo(true, true);
		}
	};

	OnHeaderRefreshListener doctorOnHeaderRefreshListener = new OnHeaderRefreshListener() {

		@Override
		public void onHeaderRefresh(PullToRefreshView view) {
			isUpdate = true;
			update();
		}
	};
	OnFooterRefreshListener doctorOnFooterRefreshListener = new OnFooterRefreshListener() {

		@Override
		public void onFooterRefresh(PullToRefreshView view) {
			// recommendController2.gengduo(true, true);
		}
	};

	OnClickListener RadioButtonOnclick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.rb_illness:
				vp_content.setCurrentItem(0, true);
				// recommendController1.update(false, false);
				break;
			case R.id.rb_doctor:
				vp_content.setCurrentItem(1, true);
				// recommendController2.update(false, false);
			default:
				break;
			}

		}
	};

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			isUpdate = true;
			update();
		}
	};

	void update() {
		if (view_prigress.getVisibility() == View.VISIBLE) {
			return;
		} else {
			view_prigress.post(new Runnable() {

				@Override
				public void run() {
					view_prigress.setVisibility(View.VISIBLE);
				}
			});
		}
		new AsyncDataLoader(new Callback() {
			private List<Recommend> recommends_illnes = new ArrayList<Recommend>(),
					recommends_doctor = new ArrayList<Recommend>();

			@Override
			public void onStartAsync() {

				List<Recommend> mRecommend;
				AsHt asht = AsHt.getInstance();
				UserInfo user = ApplictionManager.getInstance().getUserInfo();
				try {
					if (isUpdate) {
						// 服务器数据
						List<Recommend> resumes = asht
								.getRecommendationsByPresenter(user);
						DbUtils db = AFinalController
								.getDB(MutualRecommendationActivity.this);
						db.deleteAll(Recommend.class);
						mRecommend = new ArrayList<Recommend>(resumes);
						db.saveAll(mRecommend);
						isUpdate = false;
						for (Recommend recommend : mRecommend) {
							if (recommend != null) {
								if (recommend.getRecommendRoleId().equals(
										"1001")) {
									recommends_illnes.add(recommend);
								} else {
									recommends_doctor.add(recommend);
								}
							}
						}
						recommends_illnes.add(0, null);
						recommends_doctor.add(0, null);
					}
				} catch (Exception e) {
					DbUtils db = AFinalController
							.getDB(MutualRecommendationActivity.this);
					try {
						mRecommend = db.findAll(Recommend.class);
						for (Recommend recommend : mRecommend) {
							if (recommend != null) {
								if (recommend.getRecommendCertificateTypeId() == null) {
									recommends_illnes.add(recommend);
								} else {
									recommends_doctor.add(recommend);
								}
							}
						}
					} catch (DbException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}

			}

			@Override
			public void onPrepareAsync() {
			}

			@Override
			public void onFinishAsync() {
				adapter_doctor.setInfos(recommends_doctor);
				adapter_ilness.setInfos(recommends_illnes);
				adapter_doctor.notifyDataSetChanged();
				adapter_ilness.notifyDataSetChanged();
				view_prigress.post(new Runnable() {

					@Override
					public void run() {
						view_prigress.setVisibility(View.GONE);
						tv_illness.onHeaderRefreshComplete();
						tv_doctor.onHeaderRefreshComplete();
					}
				});
			}
		}).execute();
	}

	OnItemClickListener gv_itemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int index,
				long arg3) {
			Recommend recommend;
			recommend = adapter_ilness.getInfos().get(index);
			if (recommend == null) {
				addRecommend(0);
			} else {
				openRecommend(recommend);
			}

		}
	};
	OnItemClickListener gv_doctor_onitem = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int index,
				long arg3) {
			Recommend recommend;
			recommend = adapter_doctor.getInfos().get(index);
			if (recommend == null) {
				addRecommend(1);
			} else {
				openRecommend(recommend);
			}

		}
	};

	void addRecommend(int hz) {
		Intent intent = new Intent();
		intent = intent.setClass(getApplicationContext(),
				NewRecommendActivity.class);
		intent.putExtra("hz", hz);
		startActivityForResult(intent, 100);
	}

	void openRecommend(Recommend recommend) {
		Intent intent = new Intent();
		intent = intent.setClass(getApplicationContext(),
				RecommendInfoActivity.class);

		Bundle b = new Bundle();
		b.putString("recommendPhoneNo", recommend.getRecommendPhoneNo());
		b.putString("recommendtrueName", recommend.getRecommendtrueName());
		b.putString("recommendCertificateTypeId",
				recommend.getRecommendCertificateTypeId());
		b.putString("recommendCertificateId",
				recommend.getRecommendCertificateId());
		b.putString("recommendeMail", recommend.getRecommendeMail());
		b.putString("recommendDateTime", recommend.getRecommendDateTime());
		b.putString("recommendState", recommend.getRecommendState());
		b.putString("examineDateTime", recommend.getExamineDateTime());
		b.putString("recommendRoleId", recommend.getRecommendRoleId());
		intent.putExtras(b);
		startActivity(intent);
	}
}
