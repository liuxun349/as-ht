package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;

import com.asht.AsHt;
import com.asht.AsyncDataLoader;
import com.asht.AsyncDataLoader.Callback;
import com.asht.R;
import com.asht.adapter.MyCasesAdapter;
import com.asht.controller.MyCasesActivity;
import com.asht.interfaces.UIHanleLintener;
import com.asht.interfaces.UINotification;
import com.asht.model.Record;
import com.asht.model.Resume;
import com.asht.model.UpdateState;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;
import com.asht.view.ToastUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.db.table.DbModel;
import com.lidroid.xutils.exception.DbException;

@SuppressLint({ "UseSparseArrays", "HandlerLeak" })
public class CasesController implements OnItemClickListener,
		OnItemLongClickListener, ViewLinstener {
	GridView gridView = null;
	Context mContext;// 上下文对象
	int spacing = 4;// 间隔
	int width, height;
	boolean isSelectMode = false;// 是否开启了选中模式
	MyCasesAdapter adapter = null;
	List<Record> selectViews = new ArrayList<Record>();

	Record Record_tmp;

	UINotification mUINotification;
	UIHanleLintener mHanleLintener;//

	// Diag diag;

	public CasesController(Context context, GridView gridView) {
		mContext = context;
		this.gridView = gridView;
		// if (diag == null) {
		// diag = new Diag(mContext);
		// }
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
		if (Record_tmp == null) {
			ToastUtils.getInit(mContext).show("不能选择添加病例组");
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

	public synchronized void update(final boolean isServer,
			final boolean isTouch) {
		new AsyncDataLoader(new Callback() {
			private List<Record> records = new ArrayList<Record>();
			private UpdateState state = null;

			@Override
			public void onStartAsync() {
				if (isServer) {

					AsHt asht = AsHt.getInstance();
					UserInfo user = ApplictionManager.getInstance()
							.getUserInfo();
					try {
						List<Record> records = asht.getRecordGroup(user, true,
								"2013-12-25 20:06:15.0");
						state = new UpdateState(UpdateState.UK_SERVER_OK);
						DbUtils db = AFinalController.getDB(mContext);
						db.deleteAll(Resume.class);
						db.deleteAll(Record.class);

						db.saveAll(records);
						for (Record record : records) {
							List<Resume> resumes = record.getResumeList();
							for (Resume resume : resumes) {
								resume.record = record;
								db.saveBindingId(resume);
							}
						}
						this.records = records;
						this.records.add(0, null);
					} catch (Exception e) {
						state = new UpdateState(UpdateState.UK_DB_OK);
						DbUtils db = AFinalController.getDB(mContext);
						List<Record> r = null;
						try {
							r = db.findAll(Record.class);
						} catch (DbException e1) {
							e1.printStackTrace();
						}
						this.records = r;
						this.records.add(0, null);
						e.printStackTrace();
					}
				} else {
					state = new UpdateState(UpdateState.UK_DB_OK);
					DbUtils db = AFinalController.getDB(mContext);
					List<Record> r = null;
					try {
						// r = db.findAll(Record.class,);
						r = db.findAll(Selector.from(Record.class).orderBy(
								"updateTime", false));
					} catch (DbException e) {
						e.printStackTrace();
					}
					this.records = r;
					this.records.add(0, null);
				}

			}

			@Override
			public void onPrepareAsync() {
			}

			@Override
			public void onFinishAsync() {
				adapter.setInfos(records);
				updateHandler.sendEmptyMessage(10001);

				mHanleLintener.update(isServer, state, isTouch);
			}
		}).execute();

	}

	public void gengduo(final boolean fag, final boolean isTouch) {
		new AsyncDataLoader(new Callback() {
			private List<Record> records;
			private UpdateState mUpdateState = null;

			@Override
			public void onStartAsync() {
				AsHt asht = AsHt.getInstance();
				UserInfo user = ApplictionManager.getInstance().getUserInfo();
				records = new ArrayList<Record>();
				try {
					DbUtils db = AFinalController.getDB(mContext);
					// DbModel db = AFinalController
					// .getDB(mContext).find
					// .findDbModelBySQL(
					// "select updateTime from record order by updateTime limit 1;");

					Cursor cursor = db
							.execQuery("select updateTime from record order by updateTime limit 1;");

					String time = "";
					if (cursor != null && cursor.getCount() > 0) {
						cursor.moveToFirst();
						time = cursor.getString(cursor
								.getColumnIndex("updateTime"));
					}

					List<Record> records = asht.getRecordGroup(user, false,
							time);
					db.saveAll(records);
					for (Record record : records) {
						for (Resume r : record.getResumeList()) {
							r.record = record;
							db.saveBindingId(r);
						}
					}
					this.records.addAll(records);
					if (records == null || records.size() == 0) {
						mUpdateState = new UpdateState(UpdateState.UK_NO_DATA);
						mUpdateState.setLog("亲，已经没有数据了");
					} else {
						mUpdateState = new UpdateState(UpdateState.UK_SERVER_OK);
						mUpdateState.setLog("亲，加载更多完成");
						for (Record info : records) {
							AFinalController.getDB(mContext).save(info);
						}
					}
				} catch (Exception e) {
					mUpdateState = new UpdateState(UpdateState.UK_ERROR);
					mUpdateState.setLog(e.getMessage());
					e.printStackTrace();
				}

			}

			@Override
			public void onPrepareAsync() {
			}

			@Override
			public void onFinishAsync() {
				if (mUpdateState.getAction() == UpdateState.UK_SERVER_OK) {
					adapter.addRecords(records);
					int size = records.size();
					adapter.notifyDataSetChanged();
					updateHandler.sendEmptyMessage(10001);
				}
				mHanleLintener.gengduo(fag, mUpdateState, isTouch);
			}
		}).execute();

	}

	public void selectAll() {
		int i_null = 0;
		selectViews.clear();
		List<Record> info = adapter.getInfos();
		int size = info.size();
		for (int i = 0; i < size; i++) {
			Record_tmp = info.get(i);
			if (Record_tmp == null) {
				i_null++;
				continue;
			}
			Record_tmp.isClick = 0;
			selectViews.add(Record_tmp);
		}

		adapter.notifyDataSetChanged();

		if (mUINotification != null) {
			mUINotification.notificationSelected(size - i_null);
		}
	}

	public void selectClear() {
		isSelectMode = false;
		selectViews.clear();
		List<Record> info = adapter.getInfos();
		int size = info.size();
		for (int i = 0; i < size; i++) {
			Record_tmp = info.get(i);
			if (Record_tmp == null) {
				continue;
			}
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
			if (Record_tmp == null) {
				continue;
			}
			sum += Record_tmp.getMedicalRecordItemTotal();
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

	@Override
	public void deleteSelectAll() {
		new AsyncDataLoader(new Callback() {
			boolean fag = false;
			List<Record> recList = null;

			@Override
			public void onStartAsync() {

				recList = new ArrayList<Record>(selectViews);

				AsHt asht = AsHt.getInstance();
				UserInfo user = ApplictionManager.getInstance().getUserInfo();
				List<String> ids = new ArrayList<String>();
				for (Record record : recList) {
					ids.add(record.getMedicalRecordGroupID());
				}
				try {
					fag = asht.deleteRecordGroup(user, ids);
				} catch (Exception e) {
					fag = false;
				}

			}

			@Override
			public void onPrepareAsync() {

			}

			@Override
			public void onFinishAsync() {

				if (fag) {
					for (Record r : recList) {
						// AFinalController.getDB(mContext).deleteByWhere(
						// Resume.class,
						// "imedicalrecordgroupid = "
						// + r.getMedicalRecordGroupID());
						try {
							// AFinalController.getDB(mContext).deleteById(
							// Record.class, r.getId());
							AFinalController
									.getDB(mContext)
									.delete(Record.class,
											WhereBuilder.b(
													" medicalRecordGroupID ",
													" = ",
													r.getMedicalRecordGroupID()));
							AFinalController
									.getDB(mContext)
									.delete(Resume.class,
											WhereBuilder.b(
													" imedicalrecordgroupid ",
													" = ",
													r.getMedicalRecordGroupID()));
						} catch (DbException e) {
							e.printStackTrace();
						}
						adapter.removeRecord(r);
					}
				}

				selectClear();
				updateHandler.sendEmptyMessage(10001);
				mHanleLintener.deletefinish(fag);
			}
		}).execute();

	}

	@Override
	public void gengduo() {

	}

	@Override
	public void add(List<?> infos) {

	}

}
