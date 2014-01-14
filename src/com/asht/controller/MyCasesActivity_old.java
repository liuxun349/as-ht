//package com.asht.controller;
//
//import java.util.HashMap;
//
//import net.tsz.afinal.FinalDb;
//import android.app.Activity;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.util.DisplayMetrics;
//import android.view.Gravity;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.WindowManager;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.AdapterView.OnItemLongClickListener;
//import android.widget.Button;
//import android.widget.GridView;
//import android.widget.PopupWindow;
//import android.widget.TextView;
//
//import com.asht.adapter.MyCasesAdapter;
//import com.asht.info.ClinicalHistoryGroupInfo;
//import com.example.controller.AFinalController;
//import com.example.dao.CalinicalHistoryGroupDbDao;
//import com.example.testafinal.R;
//
//public class MyCasesActivity_old extends Activity implements OnClickListener {
//
//	private GridView gv_myCases = null;
//	private TextView txt_title_back = null;
//	private MyCasesAdapter myCasesAdapter;
//	private boolean isDelete = false;
//	private CasesDeleteTitle casesDeleteTitle;
//	private CasesDeleteIsOK casesDeleteIsOK;
//	private View view_myCases_delete;
//	private PopupWindow pop_edit = null;
//	private static int Spacing = 4;
//	private String[] name = { "糖尿病", "高血压", "感冒", "低血压", "脑充血", "结石", "胃病",
//			"心脏病", "多动症", "羊癫疯" };
//	HashMap<Integer, ClinicalHistoryGroaupInfo> selectHashMap = new HashMap<Integer, ClinicalHistoryGroupInfo>();
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_my_cases);
//
////		FinalDb db = FinalDb.create(this);
////		for (int i = 0; i < name.length; i++) {
////			ClinicalHistoryGroupInfo info = new ClinicalHistoryGroupInfo();
////			info.setAuditState(0);
////			info.setClinicalHistoryGroupId(100 + i);
////			info.setClinicalHistoryGroupName(name[i]);
////			info.setCount(0);
////			info.setIsClick(1);
////			db.save(info);
////		}
//
//		initView();
//		initListener();
//		initData();
//	}
//
//	private void initView() {
//		gv_myCases = (GridView) findViewById(R.id.gv_myCases);
//		txt_title_back = (TextView) findViewById(R.id.tv_title_back);
//		initDeleteTitle();
//		initDeleteIsOK();
//		view_myCases_delete = findViewById(R.id.fl_myCases_delete);
//		view_myCases_delete.setOnClickListener(this);
//	}
//
//	private void initDeleteIsOK() {
//		casesDeleteIsOK = new CasesDeleteIsOK();
//		casesDeleteIsOK.main = View.inflate(MyCasesActivity_old.this,
//				R.layout.casesdelete, null);
//		casesDeleteIsOK.tv_selectDeleteTitle = (TextView) casesDeleteIsOK.main
//				.findViewById(R.id.tv_myCases_delte_txt);
//		casesDeleteIsOK.btn_clearDelete = (Button) casesDeleteIsOK.main
//				.findViewById(R.id.btn_myCases_delete_cancel);
//		casesDeleteIsOK.btn_okDelete = (Button) casesDeleteIsOK.main
//				.findViewById(R.id.btn_myCases_delete_ok);
//		casesDeleteIsOK.btn_clearDelete
//				.setOnClickListener(casesDeleteIsOKOnClick);
//		casesDeleteIsOK.btn_okDelete.setOnClickListener(casesDeleteIsOKOnClick);
//
//	}
//
//	private void initDeleteTitle() {
//		casesDeleteTitle = new CasesDeleteTitle();
//		casesDeleteTitle.main = findViewById(R.id.fl_myCases_selected_title);
//		casesDeleteTitle.tv_selectTitle = (TextView) findViewById(R.id.tv_myCases_selected_txt);
//		casesDeleteTitle.btn_allSelect = (Button) findViewById(R.id.btn_myCases_selected_allSelect);
//		casesDeleteTitle.btn_clean = (Button) findViewById(R.id.btn_myCases_selected_cleanSelect);
//		casesDeleteTitle.btn_allSelect.setOnClickListener(casesDeleteOnClick);
//		casesDeleteTitle.btn_clean.setOnClickListener(casesDeleteOnClick);
//	}
//
//	OnClickListener casesDeleteIsOKOnClick = new OnClickListener() {
//
//		@Override
//		public void onClick(View v) {
//			if (v.getId() == casesDeleteIsOK.btn_clearDelete.getId()) {
//				if (pop_edit != null && pop_edit.isShowing()) {
//					pop_edit.dismiss();
//				} else {
//
//				}
//			}
//		}
//	};
//
//	OnClickListener casesDeleteOnClick = new OnClickListener() {
//
//		@Override
//		public void onClick(View v) {
//			switch (v.getId()) {
//			case R.id.btn_myCases_selected_allSelect:
//
//				setDeleteTitle(select(true));
//				break;
//			case R.id.btn_myCases_selected_cleanSelect:
//				selectClean();
//				break;
//			default:
//				break;
//			}
//		}
//	};
//
//	private void setDeleteTitle(int selectCount) {
//		casesDeleteTitle.tv_selectTitle.setText("已选" + selectCount + "个");
//	}
//
//	private void initListener() {
//		txt_title_back.setOnClickListener(this);
//	}
//
//	private void initData() {
//
//		int spacing = (int) getResources().getDimension(R.dimen.grid_spacing);
//		Spacing = spacing;
//
//		DisplayMetrics dm = new DisplayMetrics();
//		getWindowManager().getDefaultDisplay().getMetrics(dm);
//
//		int width = (dm.widthPixels - Spacing) / 2; // 当前分辨率 宽度
//		int height = (int) (width + getResources().getDimension(
//				R.dimen.grid_item_title_height));
//
//		AFinalController ac = AFinalController.create(getApplicationContext());
//		CalinicalHistoryGroupDbDao mCalinicalHistoryGroupDbDao = ac
//				.getCalinicalHistoryGroupDao();
//
//		// myCasesAdapter = new MyCasesAdapter(getApplicationContext(),
//		// mCalinicalHistoryGroupDbDao.queryAll(), width, height);
//		gv_myCases.setAdapter(myCasesAdapter);
//
//		gv_myCases.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				if (isDelete) {
//					selectManager(arg2);
//					if (getSelectCount() == 0) {
//						isDelete = false;
//						selectClean();
//					}
//				} else {
//					// Intent intent = new Intent(MyCasesActivity.this,
//					// MyCasesSingleActivity.class);
//					// startActivity(intent);
//				}
//			}
//
//		});
//		gv_myCases.setOnItemLongClickListener(new OnItemLongClickListener() {
//
//			@Override
//			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				isDelete = true;
//				if (isDelete) {
//					selectManager(arg2);
//					showDeleteTitle();
//					if (getSelectCount() == 0) {
//						isDelete = false;
//						selectClean();
//					}
//				}
//				return true;
//			}
//		});
//	}
//
//	private void showDeleteTitle() {
//		casesDeleteTitle.main.setVisibility(View.VISIBLE);
//		view_myCases_delete.setVisibility(View.VISIBLE);
//	}
//
//	private void HideDeleteTitle() {
//		casesDeleteTitle.main.setVisibility(View.GONE);
//		view_myCases_delete.setVisibility(View.GONE);
//	}
//
//	private void selectManager(int index) {
//		setSelectByIndex(index);
//		setDeleteTitle(getSelectCount());
//	}
//
//	private void setSelectByIndex(int index) {
//		ClinicalHistoryGroupInfo casesInfo = ((ClinicalHistoryGroupInfo) myCasesAdapter
//				.getItem(index));
//		if (casesInfo.getIsClick() == 0) {
//			casesInfo.setIsClick(1);
//		} else {
//			casesInfo.setIsClick(0);
//		}
//		myCasesAdapter.notifyDataSetChanged();
//	}
//
//	private int getSelectCount() {
//		int select = 0;
//		int count = myCasesAdapter.getCount();
//		for (int i = 0; i < count; i++) {
//			boolean fag = ((ClinicalHistoryGroupInfo) myCasesAdapter.getItem(i))
//					.getIsClick() == 0;
//			if (fag)
//				select++;
//		}
//		return select;
//	}
//
//	private int select(boolean fag) {
//		int count = myCasesAdapter.getCount();
//		for (int i = 0; i < count; i++) {
//			ClinicalHistoryGroupInfo casesInfo = ((ClinicalHistoryGroupInfo) myCasesAdapter
//					.getItem(i));
//			if (fag) {
//				casesInfo.setIsClick(0);
//			} else {
//				casesInfo.setIsClick(1);
//			}
//		}
//		myCasesAdapter.notifyDataSetChanged();
//		return fag ? count : 0;
//	}
//
//	private void selectClean() {
//		select(false);
//		casesDeleteTitle.tv_selectTitle.setText("没有选中");
//		isDelete = false;
//		HideDeleteTitle();
//	}
//
//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.tv_title_back:
//			finish();
//			break;
//		case R.id.fl_myCases_delete:
//			delete();
//			break;
//
//		default:
//			break;
//		}
//
//	}
//
//	class CasesDeleteTitle {
//		public View main;
//		public TextView tv_selectTitle;
//		public Button btn_clean;
//		public Button btn_allSelect;
//	}
//
//	public class CasesDeleteIsOK {
//		public View main;
//		public TextView tv_selectDeleteTitle;
//		public Button btn_clearDelete;
//		public Button btn_okDelete;
//	}
//
//	private void delete() {
//		// 创建PopupWindow对象
//		if (pop_edit == null) {
//			pop_edit = new PopupWindow(casesDeleteIsOK.main,
//					WindowManager.LayoutParams.MATCH_PARENT,
//					WindowManager.LayoutParams.WRAP_CONTENT, false);
//			// 需要设置一下此参数，点击外边可消失
//			pop_edit.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//			// 设置点击窗口外边窗口消失
//			pop_edit.setOutsideTouchable(true);
//			// 设置此参数获得焦点，否则无法点击
//			pop_edit.setFocusable(true);
//		}
//		if (!pop_edit.isShowing()) {
//			pop_edit.showAtLocation(casesDeleteIsOK.main, Gravity.BOTTOM, 0, 0);
//		}
//	}
//}
