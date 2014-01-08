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
import com.asht.adapter.MyCasesSingleAdapter;
import com.asht.controller.MyCasesSingleActivity;
import com.asht.interfaces.UIHanleLintener;
import com.asht.interfaces.UINotification;
import com.asht.model.Record;
import com.asht.model.Resume;
import com.asht.server.ClinicalHistoryServer;
import com.lidroid.xutils.exception.DbException;

public class CasesSingleController implements OnItemClickListener,
		OnItemLongClickListener {

	private GridView gridView = null;
	Context mContext;// 上下文对象
	int spacing = 4;// 间隔
	int width, height;
	boolean isSelectMode = false;// 是否开启了选中模式

	private MyCasesSingleAdapter adapter;
	Resume Resume_tmp = null;
	private List<Resume> selectViews;
	private UIHanleLintener mHanleLintener;
	private UINotification mUINotification;
	private ClinicalHistoryServer mClinicalHistoryServer;
	private Record mRecord;

	public CasesSingleController(Context context, GridView gridView, Record info) {
		mContext = context;
		this.gridView = gridView;
		mRecord = info;
		int spacing = (int) mContext.getResources().getDimension(
				R.dimen.grid_spacing);
		this.spacing = spacing;

		// 获取分辨率
		DisplayMetrics dm = new DisplayMetrics();
		MyCasesSingleActivity activity = (MyCasesSingleActivity) context;
		;
		(activity).getWindowManager().getDefaultDisplay().getMetrics(dm);

		width = (dm.widthPixels - spacing * 3) / 4; // 当前分辨率 宽度

		height = width;

		adapter = new MyCasesSingleAdapter(mContext, null, width, height);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(this);
		gridView.setOnItemLongClickListener(this);
		mClinicalHistoryServer = new ClinicalHistoryServer(context,
				new ClinicalHistoryServer.ServerLinstener() {

					@Override
					public void queryResumes(
							List<Resume> list) {
						// TODO Auto-generated method stub
						if (list == null) {
							// 处理数据 再读取数据库
							updateHandler.sendEmptyMessage(10001);
						}
						mHanleLintener.update(true, true, true);
					}

					@Override
					public void isUpdateToserver(boolean fag) {
						// TODO Auto-generated method stub
						if (fag) {
							update();
						} else {
							updateHandler.sendEmptyMessage(10001);
						}
						mHanleLintener.update(true, true, true);
					}

					@Override
					public void deleteClinicalHistorys(boolean fag) {
						// TODO Auto-generated method stub
						if (fag) {
							int size = selectViews.size();
							for (int i = 0; i < size; i++) {
								Resume_tmp = selectViews.get(i);
								try {
									AFinalController.create(mContext)
											.getfinalDb()
											.delete(Resume_tmp);
								} catch (DbException e) {
									e.printStackTrace();
								}
								adapter.removeResume(Resume_tmp);
							}
							selectViews.clear();
							if (mUINotification != null) {
								mUINotification.delete();
								adapter.notifyDataSetChanged();
							}
							mHanleLintener.deletefinish(true);
						} else {
							mHanleLintener.deletefinish(false);
						}
					}

					@Override
					public void addResume(Resume info) {
						if (info != null) {
							try {
								AFinalController.create(mContext).getfinalDb()
										.save(info);
							} catch (DbException e) {
								e.printStackTrace();
							}

							mHanleLintener.addfinish(true);
						} else {
							mHanleLintener.addfinish(false);
						}
					}
				});
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

		Resume_tmp = (Resume) adapter.getItem(index);

		if (!isSelectMode) {
			return;
		}
		if (selectViews == null) {
			selectViews = new ArrayList<Resume>();
		}

		int oldSize = selectViews.size();
		if (Resume_tmp.isClick== 1) {
			Resume_tmp.isClick = 0;
			view.findViewById(R.id.iv_myCasesSingle_delete).setVisibility(
					View.VISIBLE);
			if (!selectViews.contains(Resume_tmp)) {
				selectViews.add(Resume_tmp);
			}
		} else {
			Resume_tmp.isClick = 1;
			view.findViewById(R.id.iv_myCasesSingle_delete).setVisibility(
					View.GONE);
			if (selectViews.contains(Resume_tmp)) {
				selectViews.remove(Resume_tmp);
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

	public void start() {
		mClinicalHistoryServer.isUpdateToserver();
	}

	public void update() {
		mClinicalHistoryServer.queryResumes();
	}

	public void deleteSelectCases() {
		mClinicalHistoryServer.deleteClinicalHistorys(selectViews);
	}

	private void addSelectCases(Resume info) {
		mClinicalHistoryServer.addResume(info);
	}

	public void selectAll() {

		selectViews.clear();
		List<Resume> info = adapter.getInfos();
		int size = info.size();
		for (int i = 0; i < size; i++) {
			Resume_tmp = info.get(i);
			Resume_tmp.isClick = 0;
			selectViews.add(Resume_tmp);
		}

		adapter.notifyDataSetChanged();

		if (mUINotification != null) {
			mUINotification.notificationSelected(size);
		}
	}

	public void selectClear() {
		isSelectMode = false;
		selectViews.clear();
		List<Resume> info = adapter.getInfos();
		int size = info.size();
		for (int i = 0; i < size; i++) {
			Resume_tmp = info.get(i);
			Resume_tmp.isClick = 1;
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
				try {
					adapter.setInfos(mRecord.resume.getAllFromDb());
				} catch (DbException e) {
					e.printStackTrace();
				}
				break;

			default:
				break;
			}
		};
	};
}
