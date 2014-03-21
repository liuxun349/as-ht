//package com.example.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import net.tsz.afinal.FinalDb;
//import android.content.Context;
//import android.os.Handler;
//import android.util.DisplayMetrics;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.AdapterView.OnItemLongClickListener;
//import android.widget.GridView;
//
//import com.asht.AsHt;
//import com.asht.AsyncDataLoader;
//import com.asht.AsyncDataLoader.Callback;
//import com.asht.R;
//import com.asht.adapter.RecommendAdapter;
//import com.asht.controller.MutualRecommendationActivity;
//import com.asht.interfaces.UIHanleLintener;
//import com.asht.interfaces.UINotification;
//import com.asht.model.Recommend;
//import com.asht.model.Resume;
//import com.asht.model.UserInfo;
//import com.asht.utl.ApplictionManager;
//import com.example.controller.CasesSingleController.UploadCase;
//import com.lidroid.xutils.DbUtils;
//import com.lidroid.xutils.exception.DbException;
//
//public class RecommendController implements OnItemClickListener,
//		OnItemLongClickListener {
//
//	private GridView gridView = null;
//	Context mContext;// 上下文对象
//	int spacing = 4;// 间隔
//	int width, height;
//	boolean isSelectMode = false;// 是否开启了选中模式
//	int certificateId = 0;
//
//	private RecommendAdapter adapter;
//	Recommend Recommend_tmp = null;
//	private List<Recommend> selectViews;
//	private UIHanleLintener mHanleLintener;
//	private UINotification mUINotification;
//
//	public RecommendController(Context context, GridView gridView, int fanzhe) {
//		this.certificateId = fanzhe;
//		mContext = context;
//		this.gridView = gridView;
//		int spacing = (int) mContext.getResources().getDimension(
//				R.dimen.grid_spacing);
//		this.spacing = spacing;
//
//		// 获取分辨率
//		DisplayMetrics dm = new DisplayMetrics();
//		MutualRecommendationActivity activity = (MutualRecommendationActivity) context;
//		;
//		(activity).getWindowManager().getDefaultDisplay().getMetrics(dm);
//
//		width = (dm.widthPixels - spacing * 3) / 4; // 当前分辨率 宽度
//
//		height = width;
//
//		adapter = new RecommendAdapter(mContext, null, width, height);
//		gridView.setAdapter(adapter);
//		// gridView.setOnItemClickListener(this);
//		// gridView.setOnItemLongClickListener(this);
//
//	}
//
//	@Override
//	public boolean onItemLongClick(AdapterView<?> arg0, View view, int index,
//			long arg3) {
//		if (mUINotification == null) {
//			onItemClick(arg0, view, index, arg3);
//			return true;
//		}
//		isSelectMode = true;
//		onItemClick(arg0, view, index, arg3);
//		return true;
//	}
//
//	@Override
//	public void onItemClick(AdapterView<?> arg0, View view, int index, long arg3) {
//
//		Recommend_tmp = (Recommend) adapter.getItem(index);
//
//		if (!isSelectMode) {
//			return;
//		}
//		if (selectViews == null) {
//			selectViews = new ArrayList<Recommend>();
//		}
//
//		int oldSize = selectViews.size();
//		if (Recommend_tmp.getIsClick() == 1) {
//			Recommend_tmp.setIsClick(0);
//			view.findViewById(R.id.iv_myCasesSingle_delete).setVisibility(
//					View.VISIBLE);
//			if (!selectViews.contains(Recommend_tmp)) {
//				selectViews.add(Recommend_tmp);
//			}
//		} else {
//			Recommend_tmp.setIsClick(1);
//			view.findViewById(R.id.iv_myCasesSingle_delete).setVisibility(
//					View.GONE);
//			if (selectViews.contains(Recommend_tmp)) {
//				selectViews.remove(Recommend_tmp);
//			}
//		}
//		int size = selectViews.size();
//		if (size == 0) {
//			if (mUINotification != null) {
//				mUINotification.notificationLast();
//			}
//			isSelectMode = false;
//		} else if (size == 1 && oldSize == 0) {
//			if (mUINotification != null) {
//				mUINotification.notificationStart(size);
//			}
//		} else {
//			if (mUINotification != null) {
//				mUINotification.notificationSelected(size);
//			}
//		}
//	}
//
//	public void update(final boolean fag, final boolean isTouch) {
//		// RecommendDao.update(mContext, new RecommendUpdateListener() {
//		//
//		// @Override
//		// public void update(List<Recommend> list, boolean isServer,
//		// int tag) {
//		// adapter.setInfos(list);
//		// updateHandler.sendEmptyMessage(10001);
//		// mHanleLintener.update(fag, true, isTouch);
//		// }
//		// }, fag, fanzhe);
//
//		// Record r = mRecord;
//		// adapter.setInfos(AFinalController.getDB(mContext)
//		// .findAllByWhere(Resume.class,
//		// "imedicalrecordgroupid=" + r.medicalRecordGroupID));
//		// updateHandler.sendEmptyMessage(10001);
//		// // mHanleLintener.update(fag, true, isTouch);
//		// mHanleLintener.update(fag, true, isTouch);
//		if (!isTouch)
//			gridView.post(new Runnable() {
//
//				@Override
//				public void run() {
//					// diag.show();
//				}
//			});
//		new AsyncDataLoader(new Callback() {
//			private List<Recommend> mRecommend;
//			// 获得当前病例组下需要上传的
//			List<Resume> upLoad;
//			// 获得需要删除的
//			List<Resume> delete;
//
//			@Override
//			public void onStartAsync() {
//
//				if (fag) {
//					System.out.println("do it ? ..");
//					AsHt asht = AsHt.getInstance();
//					UserInfo user = ApplictionManager.getInstance()
//							.getUserInfo();
//					try {
//						// 服务器数据
//						List<Recommend> resumes = asht
//								.getRecommendationsByPresenter(user);
//						DbUtils db = AFinalController.getDB(mContext);
//						db.deleteAll(Recommend.class);
//						mRecommend = resumes;
//						db.saveAll(mRecommend);
//					} catch (Exception e) {
//						// mRecommend = AFinalController.getDB(mContext)
//						// .findAllByWhere(Recommend.class,
//						// ("certificateId = " + certificateId));
//						DbUtils db = AFinalController.getDB(mContext);
//						try {
//							mRecommend = db.findAll(Recommend.class);
//						} catch (DbException e1) {
//							e1.printStackTrace();
//						}
//						e.printStackTrace();
//					}
//
//				} else {
//					DbUtils db = AFinalController.getDB(mContext);
//					try {
//						mRecommend = db.findAll(Recommend.class);
//					} catch (DbException e1) {
//						e1.printStackTrace();
//					}
//				}
//			}
//
//			@Override
//			public void onPrepareAsync() {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onFinishAsync() {
//				// TODO Auto-generated method stub
//				adapter.setInfos(mRecommend);
//				updateHandler.sendEmptyMessage(10001);
//				// mHanleLintener.update(fag, true, isTouch);
//				mHanleLintener.update(fag, null, isTouch);
//
//				// 本地有删除数据 --再次请求服务器 同步数据
//				if (delete != null && delete.size() > 0) {
//					// 删除服务器数据
//
//				}
//				// if (diag != null && diag.isShowing()) {
//				// diag.dismiss();
//				// }
//			}
//		}).execute();
//
//	}
//
//	public void gengduo(boolean fag, final boolean isTouch) {
//		// RecommendDao.update(mContext, new RecommendUpdateListener() {
//		//
//		// @Override
//		// public void update(List<Recommend> list, boolean isServer,
//		// int tag) {
//		// adapter.setInfos(list);
//		// updateHandler.sendEmptyMessage(10001);
//		// mHanleLintener.gengduo(true, isTouch);
//		// }
//		// }, fag, fanzhe);
//	}
//
//	public void deleteSelectCases() {
//	}
//
//	public void selectAll() {
//
//		selectViews.clear();
//		List<Recommend> info = adapter.getInfos();
//		int size = info.size();
//		for (int i = 0; i < size; i++) {
//			Recommend_tmp = info.get(i);
//			Recommend_tmp.setIsClick(0);
//			selectViews.add(Recommend_tmp);
//		}
//
//		adapter.notifyDataSetChanged();
//
//		if (mUINotification != null) {
//			mUINotification.notificationSelected(size);
//		}
//	}
//
//	public void selectClear() {
//		isSelectMode = false;
//		selectViews.clear();
//		List<Recommend> info = adapter.getInfos();
//		int size = info.size();
//		for (int i = 0; i < size; i++) {
//			Recommend_tmp = info.get(i);
//			Recommend_tmp.setIsClick(1);
//		}
//		adapter.notifyDataSetChanged();
//		if (mUINotification != null) {
//			mUINotification.notificationLast();
//		}
//	}
//
//	public int getSelectCasesCount() {
//		return selectViews.size();
//	}
//
//	public void setUINotification(UINotification listener) {
//		mUINotification = listener;
//	}
//
//	public void setUIHandleLinstener(UIHanleLintener listener) {
//		mHanleLintener = listener;
//	}
//
//	Handler updateHandler = new Handler() {
//		public void handleMessage(android.os.Message msg) {
//			switch (msg.what) {
//			case 10001:
//				adapter.notifyDataSetChanged();
//				break;
//
//			default:
//				break;
//			}
//		};
//	};
//}
