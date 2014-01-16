package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;

import com.asht.AsHt;
import com.asht.AsHtException;
import com.asht.AsyncDataLoader;
import com.asht.R;
import com.asht.AsyncDataLoader.Callback;
import com.asht.adapter.MyCasesAdapter;
import com.asht.controller.AppStart;
import com.asht.controller.MainActivity;
import com.asht.controller.MyCasesActivity;
import com.asht.interfaces.UIHanleLintener;
import com.asht.interfaces.UINotification;
import com.asht.model.Record;
import com.asht.model.Resume;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;

@SuppressLint({ "UseSparseArrays", "HandlerLeak" })
public class CasesController implements OnItemClickListener,
		OnItemLongClickListener {
	GridView gridView = null;
	Context mContext;// 上下文对象
	int spacing = 4;// 间隔
	int width, height;
	boolean isSelectMode = false;// 是否开启了选中模式
	MyCasesAdapter adapter = null;
	List<Record> selectViews = new ArrayList<Record>();

	Record Record_tmp;

	UINotification mUINotification;
	UIHanleLintener mHanleLintener;

	public CasesController(Context context, GridView gridView) {
		mContext = context;
		this.gridView = gridView;

		int spacing = (int) mContext.getResources().getDimension(
				R.dimen.grid_spacing);
		this.spacing = spacing;

		DisplayMetrics dm = new DisplayMetrics();
		((MyCasesActivity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(dm);

		width = (dm.widthPixels - spacing) / 2; // 当前分辨率 宽度
		height = (int) (width + mContext.getResources().getDimension(
				R.dimen.grid_item_title_height));

		adapter = new MyCasesAdapter(mContext, null, width, height);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(this);
		gridView.setOnItemLongClickListener(this);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View view, int index,
			long arg3) {
		isSelectMode = true;
		onItemClick(arg0, view, index, arg3);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int index, long arg3) {

		Record_tmp = (Record) adapter.getItem(index);

		if (!isSelectMode) {
			mUINotification
					.onClick(index, view, Record_tmp, adapter.getInfos());
			return;
		}
		if (selectViews == null) {
			selectViews = new ArrayList<Record>();
		}

		int oldSize = selectViews.size();
		if (Record_tmp.isClick == 1) {
			Record_tmp.isClick = 0;
			view.findViewById(R.id.iv_myCases_delete).setVisibility(
					View.VISIBLE);
			if (!selectViews.contains(Record_tmp)) {
				selectViews.add(Record_tmp);
			}
		} else {
			Record_tmp.isClick = 1;
			view.findViewById(R.id.iv_myCases_delete).setVisibility(View.GONE);
			if (selectViews.contains(Record_tmp)) {
				selectViews.remove(Record_tmp);
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

		new AsyncDataLoader(new Callback() {
			private List<Record> records;

			@Override
			public void onStartAsync() {
				AsHt asht = AsHt.getInstance();
				UserInfo user = ApplictionManager.getInstance().getUserInfo();
				user = new UserInfo();
				user.setUserPhoneNo("13000001011");
				try {
					records = asht.getRecordGroup(user, true,
							"2013-12-25 20:06:15.0");
					System.out.println(" size: " + records.size());
				} catch (Exception e) {
					e.printStackTrace();
					Log.w("Record", e.toString());
				}

			}

			@Override
			public void onPrepareAsync() {
			}

			@Override
			public void onFinishAsync() {
				adapter.setInfos(records);
				updateHandler.sendEmptyMessage(10001);
				// mHanleLintener.update(fag, true, isTouch);
				mHanleLintener.update(true, true, true);

			}
		}).execute();

	}

	public void gengduo(final boolean fag, final boolean isTouch) {
		// CaseDao.update(mContext, new CaseUpdateListener() {
		//
		// @Override
		// public void update(List<Record> list, boolean isServer, int tag) {
		// adapter.setInfos(list);
		// updateHandler.sendEmptyMessage(10001);
		// mHanleLintener.gengduo(true, isTouch);
		// }
		// }, fag);
	}

	public void deleteSelectCasesGroup() {

		new AsyncDataLoader(new Callback() {

			@Override
			public void onStartAsync() {

				AsHt asht = AsHt.getInstance();
				UserInfo user = ApplictionManager.getInstance().getUserInfo();
				user = new UserInfo();
				user.setUserPhoneNo("13000001011");
				try {
					List<Record> lists = selectViews;
					for (Record record : lists) {
						try {
							boolean del = asht.deleteRecordGroup(user,
									record.medicalRecordGroupID);
							if (del) {
								adapter.removeRecord(record);
							}
						} catch (Exception e) {
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public void onPrepareAsync() {

			}

			@Override
			public void onFinishAsync() {
				selectClear();
				updateHandler.sendEmptyMessage(10001);
				mHanleLintener.deletefinish(true);

			}
		}).execute();

	}

	public void selectAll() {

		selectViews.clear();
		List<Record> info = adapter.getInfos();
		int size = info.size();
		for (int i = 0; i < size; i++) {
			Record_tmp = info.get(i);
			Record_tmp.isClick = 0;
			selectViews.add(Record_tmp);
		}

		adapter.notifyDataSetChanged();

		if (mUINotification != null) {
			mUINotification.notificationSelected(size);
		}
	}

	public void selectClear() {
		isSelectMode = false;
		selectViews.clear();
		List<Record> info = adapter.getInfos();
		int size = info.size();
		for (int i = 0; i < size; i++) {
			Record_tmp = info.get(i);
			Record_tmp.isClick = 1;
		}
		adapter.notifyDataSetChanged();
		if (mUINotification != null) {
			mUINotification.notificationLast();
		}
	}

	public int getSelectCasesCount() {
		int size = getSelectCasesGroupCount();
		int sum = 0;
		for (int i = 0; i < size; i++) {
			Record_tmp = selectViews.get(i);
			sum += Record_tmp.medicalRecordItemTotal;
		}
		return sum;
	}

	public int getSelectCasesGroupCount() {
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
