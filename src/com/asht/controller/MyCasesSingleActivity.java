package com.asht.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.asht.R;
import com.asht.interfaces.UIHanleLintener;
import com.asht.interfaces.UINotification;
import com.asht.model.Record;
import com.asht.model.Resume;
import com.asht.model.UpdateState;
import com.asht.ui.PullToRefreshView;
import com.asht.ui.PullToRefreshView.OnFooterRefreshListener;
import com.asht.ui.PullToRefreshView.OnHeaderRefreshListener;
import com.asht.utl.FileCache;
import com.asht.utl.SharedPreferencesUtils;
import com.asht.view.ToastUtils;
import com.example.controller.AFinalController;
import com.example.controller.CasesSingleController;
import com.example.testafinal.MyApplication;
import com.lidroid.xutils.exception.DbException;

public class MyCasesSingleActivity extends Activity implements OnClickListener {

	GridView gv_myCasesSingle;
	PopupWindow pop_edit = null;
	PopupWindow pop_add = null;
	CasesSingleDeleteTitle casesSingleDeleteTitle;
	private View view_myCasesSingle_delete;
	private CasesDeleteIsOK casesDeleteIsOK;
	private CasesSingleController mCasesSingleController;
	private CasesAddCaseSingle mAddCaseSingle;
	private PullToRefreshView ptrv_cases;

	static String imageuri = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_case_single);

		// FinalDb db = FinalDb.create(this);
		// for (int i = 0; i < str.length; i++) {
		// ClinicalHistoryInfo info = new ClinicalHistoryInfo();
		// info.setState(0);
		// info.setClinicalHistoryGroupId(mRecord
		// .getClinicalHistoryGroupId());
		// info.setClinicalHistoryId(1000 + i);
		// info.setUrl(str[i]);
		// info.setIsClick(1);
		// db.save(info);
		// }
		//
		// mRecord = FinalDb.create(getApplicationContext())
		// .findById(mRecord.get_id(),
		// Record.class);
		// mRecord.setCount(mRecord.getCount()
		// + str.length);
		// FinalDb.create(getApplicationContext()).update(
		// mRecord);

		findViewById(R.id.tv_title_back).setOnClickListener(this);
		initView();
		initData();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK) {

			if (requestCode == 1000) {
				Bundle bundle = data.getExtras();
				@SuppressWarnings("unchecked")
				final List<String> tDataList = (List<String>) bundle
						.getSerializable("dataList");

				// 没有选择数据
				if (tDataList == null) {
					return;
				}
				if (tDataList.size() < 1) {
					return;
				}
				List<Resume> lists = new ArrayList<Resume>();
				for (String string : tDataList) {
					Resume r = new Resume();
					r.setLocalRecordImageUrl(string);
					lists.add(r);
				}
				mCasesSingleController.add(lists);
			} else {
				String uri = imageuri;
				// System.out.println(uri);
				if (uri == null) {
					ToastUtils.getInit(getApplicationContext()).show("照片失败1");
					return;
				}
				// Cursor c = managedQuery(uri, null, null, null, null);
				// c.moveToFirst();
				// String path = c.getString(c.getColumnIndex(Media.DATA));

				File f = new File(uri.toString());

				if (!f.exists()) {
					ToastUtils.getInit(getApplicationContext()).show("照片不存在");
					return;
				}
				List<Resume> lists = new ArrayList<Resume>();
				Resume r = new Resume();
				r.setLocalRecordImageUrl(f.getAbsolutePath());
				lists.add(r);
				ToastUtils.getInit(getApplicationContext()).show(
						"aa" + lists.size());
				mCasesSingleController.add(lists);
			}
			mCasesSingleController.isUpdate = true;
		}

	}

	private void initView() {
		gv_myCasesSingle = (GridView) findViewById(R.id.gv_myCasesSingle);
		ptrv_cases = (PullToRefreshView) findViewById(R.id.ptrv_cases);
		ptrv_cases.setFoot(false);

		initDeleteTitle();
		initDeleteIsOK();
		initAddCaseSingle();
		view_myCasesSingle_delete = findViewById(R.id.fl_myCasesSingle_delete);
		view_myCasesSingle_delete.setOnClickListener(this);
	}

	private void initDeleteTitle() {
		casesSingleDeleteTitle = new CasesSingleDeleteTitle();
		casesSingleDeleteTitle.main = findViewById(R.id.fl_myCases_selected_title);
		casesSingleDeleteTitle.tv_selectTitle = (TextView) findViewById(R.id.tv_myCasesSingle_selected_txt);
		casesSingleDeleteTitle.btn_allSelect = (Button) findViewById(R.id.btn_myCasesSingle_selected_allSelect);
		casesSingleDeleteTitle.btn_clean = (Button) findViewById(R.id.btn_myCasesSingle_selected_cleanSelect);
		casesSingleDeleteTitle.btn_allSelect
				.setOnClickListener(casesDeleteOnClick);
		casesSingleDeleteTitle.btn_clean.setOnClickListener(casesDeleteOnClick);
	}

	private void initDeleteIsOK() {
		casesDeleteIsOK = new CasesDeleteIsOK();
		casesDeleteIsOK.main = View.inflate(this, R.layout.casesdelete, null);
		casesDeleteIsOK.tv_selectDeleteTitle = (TextView) casesDeleteIsOK.main
				.findViewById(R.id.tv_myCases_delte_txt);
		casesDeleteIsOK.btn_clearDelete = (Button) casesDeleteIsOK.main
				.findViewById(R.id.btn_myCases_delete_cancel);
		casesDeleteIsOK.btn_okDelete = (Button) casesDeleteIsOK.main
				.findViewById(R.id.btn_myCases_delete_ok);
		casesDeleteIsOK.btn_clearDelete
				.setOnClickListener(casesDeleteIsOKOnClick);
		casesDeleteIsOK.btn_okDelete.setOnClickListener(casesDeleteIsOKOnClick);

	}

	private void initAddCaseSingle() {
		mAddCaseSingle = new CasesAddCaseSingle();
		mAddCaseSingle.view_cameraimport
				.setOnClickListener(addCaseSingleOnClick);
		mAddCaseSingle.view_localimport
				.setOnClickListener(addCaseSingleOnClick);
	}

	Record mRecord;

	private void initData() {

		mRecord = ((MyApplication) getApplication()).getmRecord();

		((TextView) findViewById(R.id.tv_caseSingleTitle)).setText(mRecord
				.getMedicalRecordGroupName());
		mCasesSingleController = new CasesSingleController(this,
				gv_myCasesSingle, mRecord);
		mCasesSingleController.setUIHandleLinstener(mHanleLintener);
		mCasesSingleController.setUINotification(uiNotification);

		setPro(true);
		mCasesSingleController.update(true, false);
		((TextView) findViewById(R.id.tv_add_cases_single))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						addCase();
					}
				});

		ptrv_cases.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {

			@Override
			public void onHeaderRefresh(PullToRefreshView view) {

				setPro(true);
				mCasesSingleController.update(true, true);
			}
		});
		ptrv_cases.setOnFooterRefreshListener(new OnFooterRefreshListener() {

			@Override
			public void onFooterRefresh(PullToRefreshView view) {
				// mCasesSingleController.gengduo(true, true);
				ptrv_cases.onFooterRefreshComplete();
			}
		});

		ptrv_cases.onHeaderRefreshComplete(SharedPreferencesUtils
				.getTime(getApplicationContext()));
	}

	private void setPro(final boolean fag) {
		findViewById(R.id.prigress).post(new Runnable() {

			@Override
			public void run() {
				findViewById(R.id.tv_add_cases_single).setVisibility(
						fag ? View.GONE : View.VISIBLE);
				findViewById(R.id.prigress).setVisibility(
						fag ? View.VISIBLE : View.GONE);
			}
		});

	}

	private void showDeleteTitle() {
		casesSingleDeleteTitle.main.setVisibility(View.VISIBLE);
		view_myCasesSingle_delete.setVisibility(View.VISIBLE);
	}

	private void HideDeleteTitle() {
		casesSingleDeleteTitle.main.setVisibility(View.GONE);
		view_myCasesSingle_delete.setVisibility(View.GONE);
	}

	private void selectClean() {
		casesSingleDeleteTitle.tv_selectTitle.setText("没有选中");
		HideDeleteTitle();
	}

	private void setDeleteTitle(int selectCount) {
		casesSingleDeleteTitle.tv_selectTitle.setText("已选" + selectCount + "个");
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.tv_title_back) {
			finish();
			return;
		}
		switch (v.getId()) {
		case R.id.fl_myCasesSingle_delete:
			delete();
			break;
		default:
			break;
		}
	}

	class CasesSingleDeleteTitle {
		public View main;
		public TextView tv_selectTitle;
		public Button btn_clean;
		public Button btn_allSelect;
	}

	public class CasesDeleteIsOK {
		public View main;
		public TextView tv_selectDeleteTitle;
		public Button btn_clearDelete;
		public Button btn_okDelete;
	}

	/**
	 * 病例添加 （选择器）
	 * 
	 * @author mac
	 * 
	 */
	public class CasesAddCaseSingle {
		public View main;
		// 本地选择
		public View view_localimport;
		// 照相机选择
		public View view_cameraimport;

		public CasesAddCaseSingle() {
			main = View.inflate(MyCasesSingleActivity.this,
					R.layout.pop_add_case_single, null);
			view_localimport = main.findViewById(R.id.tv_localimport);
			view_cameraimport = main.findViewById(R.id.tv_cameraimport);
		}
	}

	OnClickListener casesDeleteOnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_myCasesSingle_selected_allSelect:
				mCasesSingleController.selectAll();
				break;
			case R.id.btn_myCasesSingle_selected_cleanSelect:
				mCasesSingleController.selectClear();
				break;
			default:
				break;
			}
		}
	};

	private void delete() {
		int size = mCasesSingleController.getSelectCasesCount();
		casesDeleteIsOK.tv_selectDeleteTitle.setText("确定" + size + "张吗？");
		// 创建PopupWindow对象
		if (pop_edit == null) {
			pop_edit = new PopupWindow(casesDeleteIsOK.main,
					WindowManager.LayoutParams.MATCH_PARENT,
					WindowManager.LayoutParams.WRAP_CONTENT, false);
			// 需要设置一下此参数，点击外边可消失
			pop_edit.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
			// 设置点击窗口外边窗口消失
			pop_edit.setOutsideTouchable(true);
			// 设置此参数获得焦点，否则无法点击
			pop_edit.setFocusable(true);
			pop_edit.setOnDismissListener(new OnDismissListener() {

				@Override
				public void onDismiss() {
					// TODO Auto-generated method stub
					mCasesSingleController.selectClear();
				}
			});
		}
		if (!pop_edit.isShowing()) {
			pop_edit.showAtLocation(casesDeleteIsOK.main, Gravity.BOTTOM, 0, 0);
		}
	}

	private void addCase() {
		int size = mCasesSingleController.getSelectCasesCount();
		casesDeleteIsOK.tv_selectDeleteTitle.setText("确定" + size + "张吗？");
		// 创建PopupWindow对象
		if (pop_add == null) {
			pop_add = new PopupWindow(mAddCaseSingle.main,
					WindowManager.LayoutParams.MATCH_PARENT,
					WindowManager.LayoutParams.WRAP_CONTENT, false);
			// 需要设置一下此参数，点击外边可消失
			pop_add.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
			// 设置点击窗口外边窗口消失
			pop_add.setOutsideTouchable(true);
			// 设置此参数获得焦点，否则无法点击
			pop_add.setFocusable(true);
		}
		if (pop_edit != null && pop_edit.isShowing()) {
			pop_edit.dismiss();
		}
		if (!pop_add.isShowing()) {
			pop_add.showAtLocation(mAddCaseSingle.main, Gravity.BOTTOM, 0, 0);
		}
	}

	OnClickListener casesDeleteIsOKOnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v.getId() == casesDeleteIsOK.btn_clearDelete.getId()) {
				if (pop_edit != null && pop_edit.isShowing()) {
					pop_edit.dismiss();
				} else {

				}
			} else {
				mCasesSingleController.deleteSelectAll();
			}
		}
	};
	OnClickListener addCaseSingleOnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = null;
			if (v.getId() == mAddCaseSingle.view_cameraimport.getId()) {
				intent = new Intent("android.media.action.IMAGE_CAPTURE");
				String path = FileCache.getPath();
				imageuri = null;
				if (path == null) {
					ToastUtils.getInit(getApplicationContext()).show(
							"启动照相机失败，没有SD卡");
					return;
				}
				String imagePath = new File(path, System.currentTimeMillis()
						+ ".jpg").getAbsolutePath();
				Uri uri = Uri.fromFile(new File(imagePath));
				imageuri = imagePath;
				intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
				startActivityForResult(intent, 0);
				overridePendingTransition(R.anim.activity_in,
						R.anim.activity_out);
				pop_add.dismiss();
			} else {
				intent = new Intent();
				intent.setClass(getApplicationContext(), AlbumActivity.class);
				startActivityForResult(intent, 1000);
				overridePendingTransition(R.anim.activity_in,
						R.anim.activity_out);
				pop_add.dismiss();
			}

		}
	};

	UIHanleLintener mHanleLintener = new UIHanleLintener() {

		@Override
		public void update(boolean isServer, UpdateState state, boolean isTouch) {
			// TODO Auto-generated method stub
			if (isTouch)
				ptrv_cases.onHeaderRefreshComplete();
			setPro(false);
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
		public void gengduo(boolean fag, UpdateState state, boolean isTouch) {
			// TODO Auto-generated method stub

		}
	};

	/***
	 * 选择发生改变了
	 */
	UINotification uiNotification = new UINotification() {

		/**
		 * 选中了多少
		 */
		@Override
		public void notificationSelected(int size) {
			setDeleteTitle(size);
		}

		/**
		 * 取消选择了/
		 */
		@Override
		public void notificationLast() {
			setDeleteTitle(0);
			HideDeleteTitle();

		}

		/**
		 * 开始选择
		 */
		@Override
		public void notificationStart(int size) {
			showDeleteTitle();
			setDeleteTitle(size);
		}

		/**
		 * 删除完成
		 */
		@Override
		public void delete() {
			notificationLast();
			if (pop_edit != null && pop_edit.isShowing()) {
				pop_edit.dismiss();
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public void onClick(int index, View citem, Object info, List<?> list) {
			Intent intent = new Intent(getApplicationContext(),
					ViewPagerActivity.class);
			Bundle b = new Bundle();
			// b.putSerializable("dataList", (Serializable) list);
			((MyApplication) getApplication()).setResumes((List<Resume>) list);
			b.putInt("index", index);
			intent.putExtras(b);
			startActivity(intent);
			overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
		}

	};

	@SuppressLint("SimpleDateFormat")
	public void finish() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
		Record r = ((MyApplication) getApplication()).getmRecord();
		try {
			AFinalController.getDB(getApplicationContext()).delete(r);
			r.setUpdateTime(sdf.format(date));
			AFinalController.getDB(getApplicationContext()).save(r);

		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((MyApplication) getApplication()).clearResumes();
		if (mCasesSingleController.isUpdate) {
			setResult(RESULT_OK);
		}
		super.finish();
	};
}
