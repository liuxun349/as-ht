package com.example.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalDb;
import net.tsz.afinal.core.AsyncTask;
import android.R.integer;
import android.content.Context;
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
import com.asht.AsyncDataLoader.Callback;
import com.asht.R;
import com.asht.adapter.MyCasesSingleAdapter;
import com.asht.controller.MyCasesSingleActivity;
import com.asht.interfaces.UIHanleLintener;
import com.asht.interfaces.UINotification;
import com.asht.model.Record;
import com.asht.model.Resume;
import com.asht.model.UserInfo;
import com.asht.utl.ApplictionManager;
import com.yj.compress.YJBitmap;

public class CasesSingleController implements OnItemClickListener,
		OnItemLongClickListener, ViewLinstener {

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
			mUINotification
					.onClick(index, view, Resume_tmp, adapter.getInfos());
			return;
		}
		if (selectViews == null) {
			selectViews = new ArrayList<Resume>();
		}

		int oldSize = selectViews.size();
		if (Resume_tmp.isClick == 1) {
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
		if (selectViews == null) {
			selectViews = new ArrayList<Resume>();
		}
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
					// adapter.setInfos(mRecord.resume.getAllFromDb());
					adapter.notifyDataSetChanged();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			default:
				break;
			}
		};
	};

	@Override
	public void update(final boolean fag, final boolean isTouch) {
		// Record r = mRecord;
		// adapter.setInfos(AFinalController.getDB(mContext)
		// .findAllByWhere(Resume.class,
		// "imedicalrecordgroupid=" + r.medicalRecordGroupID));
		// updateHandler.sendEmptyMessage(10001);
		// // mHanleLintener.update(fag, true, isTouch);
		// mHanleLintener.update(fag, true, isTouch);
		new AsyncDataLoader(new Callback() {
			private List<Resume> mResume;
			// 获得当前病例组下需要上传的
			List<Resume> upLoad;
			// 获得需要删除的
			List<Resume> delete;

			@Override
			public void onStartAsync() {

				System.out.println("do it ? ..");
				AsHt asht = AsHt.getInstance();
				UserInfo user = ApplictionManager.getInstance().getUserInfo();
				user = new UserInfo();
				user.setUserPhoneNo("13000001011");
				try {
					Record r = mRecord;
					// 服务器数据
					List<Resume> resumes = asht.getAllCaseFromGroup(user,
							r.medicalRecordGroupID);
					FinalDb db = AFinalController.getDB(mContext);
					// 获得当前病例组下需要上传的
					upLoad = db.findAllByWhere(Resume.class,
							"imedicalrecordgroupid = " + r.medicalRecordGroupID
									+ " and state =" + 2);
					// 获得需要删除的
					delete = db.findAllByWhere(Resume.class,
							"imedicalrecordgroupid = " + r.medicalRecordGroupID
									+ " and state =" + 1);

					for (Resume resume : delete) {
						for (Resume resume2 : resumes) {
							if (resume.getImedicalrecorditemid() == resume2
									.getImedicalrecorditemid()) {
								resume2.setState(resume.getState());
							}
						}
					}

					// 清空本地数据
					db.deleteByWhere(
							Resume.class,
							("imedicalrecordgroupid = " + r.medicalRecordGroupID)
									+ "");

					resumes.addAll(upLoad);
					// 保存本地数据
					for (Resume resume : resumes) {
						db.save(resume);
					}
					mResume = resumes;

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public void onPrepareAsync() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onFinishAsync() {
				// TODO Auto-generated method stub
				adapter.setInfos(mResume);
				updateHandler.sendEmptyMessage(10001);
				// mHanleLintener.update(fag, true, isTouch);
				mHanleLintener.update(fag, true, isTouch);

				// 有数据未上传 --再次请求数据上传
				if (upLoad != null && upLoad.size() > 0) {
					// 上传图片
					for (Object obj : upLoad) {
						Resume resume = (Resume) obj;
						resume.setState(2);
						resume.setImedicalrecordgroupid(Integer
								.parseInt(mRecord.medicalRecordGroupID));
						new UploadCase(resume).execute();
					}
				}
				// 本地有删除数据 --再次请求服务器 同步数据
				if (delete != null && delete.size() > 0) {
					// 删除服务器数据

				}

			}
		}).execute();

	}

	@Override
	public void deleteSelectAll() {

		new AsyncDataLoader(new Callback() {
			private List<Resume> mResume;

			boolean fag = false;

			@Override
			public void onStartAsync() {
				mResume = new ArrayList<Resume>();
				mResume.addAll(selectViews);
				AsHt asht = AsHt.getInstance();
				UserInfo user = ApplictionManager.getInstance().getUserInfo();
				user = new UserInfo();
				user.setUserPhoneNo("13000001011");
				try {
					List<String> deletes = new ArrayList<String>();
					for (Resume info : mResume) {
						if (info.getImedicalrecorditemid() != 0) {
							adapter.removeResume(info);
						} else {
							deletes.add(info.getImedicalrecorditemid() + "");
						}
						// info.setState(1);
						// AFinalController.getDB(mContext).update(info);
					}
					fag = asht.deleteCaseFromGroup(user,
							mRecord.medicalRecordGroupID, deletes);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onPrepareAsync() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onFinishAsync() {

				if (fag) {
					for (Resume r : mResume) {
						AFinalController.getDB(mContext).delete(r);
						adapter.removeResume(r);
					}
					if (mUINotification != null) {
						mUINotification.delete();
					}
					update(true, false);
					adapter.notifyDataSetChanged();
				}
				selectClear();
				mHanleLintener.deletefinish(fag);
			}

		}).execute();

	}

	@Override
	public void gengduo() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(final List<?> infos) {
		for (Object obj : infos) {
			Resume resume = (Resume) obj;
			resume.setState(2);
			resume.setImedicalrecordgroupid(Integer
					.parseInt(mRecord.medicalRecordGroupID));
			new UploadCase(resume).execute();
			AFinalController.getDB(mContext).save(resume);
		}
		adapter.addResumes((List<Resume>) infos);
		adapter.notifyDataSetChanged();
	}

	class UploadCase extends AsyncTask<Void, Long, Void> {

		private Resume resume;

		public UploadCase(Resume resume) {
			this.resume = resume;
		}

		public Resume setAttribute(Resume r) {
			if (r != null) {
				resume.setAttribute(r);
				YJBitmap.create(mContext).addImageCache(
						"http://115.28.48.85:8080/ascs/"
								+ r.getImedicalrecorditemfilename(),
						resume.getLocalRecordImageUrl());
				resume.setState(3);
			}
			AFinalController.getDB(mContext).update(
					resume,
					"imedicalrecordgroupid ="
							+ resume.getImedicalrecordgroupid()
							+ " and localRecordImageUrlId ='"
							+ resume.getLocalRecordImageUrlId() + "'");
			return resume;
		}

		public Resume getResume() {
			return resume;
		}

		@Override
		protected Void doInBackground(Void... params) {

			System.out.println("do it ? ..");
			AsHt asht = AsHt.getInstance();
			UserInfo user = ApplictionManager.getInstance().getUserInfo();
			user = new UserInfo();
			user.setUserPhoneNo("13000001011");
			try {
				setAttribute(asht.uploadCaseToGroup(user,
						mRecord.medicalRecordGroupID,
						resume.getLocalRecordImageUrl()));
			} catch (AsHtException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			adapter.notifyDataSetChanged();
		}
	}
}
