package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;

import com.asht.R;
import com.asht.adapter.RecommendAdapter;
import com.asht.controller.MutualRecommendationActivity;
import com.asht.info.RecommendInfo;
import com.asht.interfaces.UIHanleLintener;
import com.asht.interfaces.UINotification;

public class ClincalHistoryGroupController implements OnItemClickListener,
		OnItemLongClickListener {

	private GridView gridView = null;
	Context mContext;// 上下文对象
	int spacing = 4;// 间隔
	int width, height;
	boolean isSelectMode = false;// 是否开启了选中模式
	boolean fanzhe = false;

	private RecommendAdapter adapter;
	RecommendInfo RecommendInfo_tmp = null;
	private List<RecommendInfo> selectViews;
	private UIHanleLintener mHanleLintener;
	private UINotification mUINotification;

	public ClincalHistoryGroupController(Context context, GridView gridView,
			boolean fanzhe) {
		this.fanzhe = fanzhe;
		mContext = context;
		this.gridView = gridView;
		int spacing = (int) mContext.getResources().getDimension(
				R.dimen.grid_spacing);
		this.spacing = spacing;

		// 获取分辨率
		DisplayMetrics dm = new DisplayMetrics();
		MutualRecommendationActivity activity = (MutualRecommendationActivity) context;
		;
		(activity).getWindowManager().getDefaultDisplay().getMetrics(dm);

		width = (dm.widthPixels - spacing * 3) / 4; // 当前分辨率 宽度

		height = width;

		adapter = new RecommendAdapter(mContext, null, width, height);
		gridView.setAdapter(adapter);
		// gridView.setOnItemClickListener(this);
		// gridView.setOnItemLongClickListener(this);

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View view, int index,
			long arg3) {
		if (mUINotification == null) {
			onItemClick(arg0, view, index, arg3);
			return true;
		}
		isSelectMode = true;
		onItemClick(arg0, view, index, arg3);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int index, long arg3) {

		RecommendInfo_tmp = (RecommendInfo) adapter.getItem(index);

		if (!isSelectMode) {
			return;
		}
		if (selectViews == null) {
			selectViews = new ArrayList<RecommendInfo>();
		}

		int oldSize = selectViews.size();
		if (RecommendInfo_tmp.getIsClick() == 1) {
			RecommendInfo_tmp.setIsClick(0);
			view.findViewById(R.id.iv_myCasesSingle_delete).setVisibility(
					View.VISIBLE);
			if (!selectViews.contains(RecommendInfo_tmp)) {
				selectViews.add(RecommendInfo_tmp);
			}
		} else {
			RecommendInfo_tmp.setIsClick(1);
			view.findViewById(R.id.iv_myCasesSingle_delete).setVisibility(
					View.GONE);
			if (selectViews.contains(RecommendInfo_tmp)) {
				selectViews.remove(RecommendInfo_tmp);
			}
		}
		int size = selectViews.size();
		if (size == 0) {
			if (mUINotification != null) {
				mUINotification.notificationLast();
			}
			isSelectMode = false;
		} else if (size == 1 && oldSize == 0) {
			if (mUINotification != null) {
				mUINotification.notificationStart(size);
			}
		} else {
			if (mUINotification != null) {
				mUINotification.notificationSelected(size);
			}
		}
	}

	public void update(final boolean fag, final boolean isTouch) {
		// RecommendDao.update(mContext, new RecommendUpdateListener() {
		//
		// @Override
		// public void update(List<RecommendInfo> list, boolean isServer,
		// int tag) {
		// adapter.setInfos(list);
		// updateHandler.sendEmptyMessage(10001);
		// mHanleLintener.update(fag,true, isTouch);
		// }
		// }, fag, fanzhe);
	}

	public void gengduo(boolean fag, final boolean isTouch) {
		// RecommendDao.update(mContext, new RecommendUpdateListener() {
		//
		// @Override
		// public void update(List<RecommendInfo> list, boolean isServer,
		// int tag) {
		// adapter.setInfos(list);
		// updateHandler.sendEmptyMessage(10001);
		// mHanleLintener.gengduo(true, isTouch);
		// }
		// }, fag, fanzhe);
	}

	public void deleteSelectCases() {
	}

	public void selectAll() {

		selectViews.clear();
		List<RecommendInfo> info = adapter.getInfos();
		int size = info.size();
		for (int i = 0; i < size; i++) {
			RecommendInfo_tmp = info.get(i);
			RecommendInfo_tmp.setIsClick(0);
			selectViews.add(RecommendInfo_tmp);
		}

		adapter.notifyDataSetChanged();

		if (mUINotification != null) {
			mUINotification.notificationSelected(size);
		}
	}

	public void selectClear() {
		isSelectMode = false;
		selectViews.clear();
		List<RecommendInfo> info = adapter.getInfos();
		int size = info.size();
		for (int i = 0; i < size; i++) {
			RecommendInfo_tmp = info.get(i);
			RecommendInfo_tmp.setIsClick(1);
		}
		adapter.notifyDataSetChanged();
		if (mUINotification != null) {
			mUINotification.notificationLast();
		}
	}

	public int getSelectCasesCount() {
		return selectViews.size();
	}

	public void setUINotification(UINotification listener) {
		mUINotification = listener;
	}

	public void setUIHandleLinstener(UIHanleLintener listener) {
		mHanleLintener = listener;
	}

	Handler updateHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 10001:
				adapter.notifyDataSetChanged();
				break;

			default:
				break;
			}
		};
	};
}
