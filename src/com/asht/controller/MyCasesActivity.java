package com.asht.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.asht.R;
import com.asht.interfaces.UIHanleLintener;
import com.asht.interfaces.UINotification;
import com.asht.model.Record;
import com.asht.ui.PullToRefreshView;
import com.asht.ui.PullToRefreshView.OnFooterRefreshListener;
import com.asht.ui.PullToRefreshView.OnHeaderRefreshListener;
import com.example.controller.CasesController;
import com.example.testafinal.MyApplication;

public class MyCasesActivity extends Activity implements OnClickListener {

	private GridView gv_myCases = null;
	private TextView txt_title_back = null;
	private CasesDeleteTitle casesDeleteTitle;
	private CasesDeleteIsOK casesDeleteIsOK;
	private View view_myCases_delete;
	private PopupWindow pop_edit = null;
	CasesController mCasesController;
	private PullToRefreshView ptrv_cases;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_cases);

		initView();
		initListener();
		initData();
	}

	@Override
	protected void onResume() {
		super.onResume();

		mCasesController.update(false, false);
	}

	private void initView() {
		ptrv_cases = (PullToRefreshView) findViewById(R.id.ptrv_cases);

		gv_myCases = (GridView) findViewById(R.id.gv_myCases);
		txt_title_back = (TextView) findViewById(R.id.tv_title_back);
		initDeleteTitle();
		initDeleteIsOK();
		view_myCases_delete = findViewById(R.id.fl_myCases_delete);
		view_myCases_delete.setOnClickListener(this);
	}

	private void initDeleteIsOK() {
		casesDeleteIsOK = new CasesDeleteIsOK();
		casesDeleteIsOK.main = View.inflate(MyCasesActivity.this,
				R.layout.casesdelete, null);
		casesDeleteIsOK.tv_selectDeleteTitle = (TextView) casesDeleteIsOK.main
				.findViewById(R.id.tv_myCases_delte_txt);
		casesDeleteIsOK.btn_clearDelete = casesDeleteIsOK.main
				.findViewById(R.id.btn_myCases_delete_cancel);
		casesDeleteIsOK.btn_okDelete = casesDeleteIsOK.main
				.findViewById(R.id.btn_myCases_delete_ok);
		casesDeleteIsOK.btn_clearDelete
				.setOnClickListener(casesDeleteIsOKOnClick);
		casesDeleteIsOK.btn_okDelete.setOnClickListener(casesDeleteIsOKOnClick);

	}

	private void initDeleteTitle() {
		casesDeleteTitle = new CasesDeleteTitle();
		casesDeleteTitle.main = findViewById(R.id.fl_myCases_selected_title);
		casesDeleteTitle.tv_selectTitle = (TextView) findViewById(R.id.tv_myCases_selected_txt);
		casesDeleteTitle.btn_allSelect = (Button) findViewById(R.id.btn_myCases_selected_allSelect);
		casesDeleteTitle.btn_clean = (Button) findViewById(R.id.btn_myCases_selected_cleanSelect);
		casesDeleteTitle.btn_allSelect.setOnClickListener(casesDeleteOnClick);
		casesDeleteTitle.btn_clean.setOnClickListener(casesDeleteOnClick);
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
				mCasesController.deleteSelectCasesGroup();
			}
		}
	};

	OnClickListener casesDeleteOnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_myCases_selected_allSelect:
				mCasesController.selectAll();
				break;
			case R.id.btn_myCases_selected_cleanSelect:
				mCasesController.selectClear();
				break;
			default:
				break;
			}
		}
	};

	private void setDeleteTitle(int selectCount) {
		casesDeleteTitle.tv_selectTitle.setText("已选" + selectCount + "个");
	}

	private void initListener() {
		txt_title_back.setOnClickListener(this);
	}

	private void initData() {
		mCasesController = new CasesController(this, gv_myCases);
		ptrv_cases.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {

			@Override
			public void onHeaderRefresh(PullToRefreshView view) {
				// TODO Auto-generated method stub
				mCasesController.update(true, true);
			}
		});
		ptrv_cases.setOnFooterRefreshListener(new OnFooterRefreshListener() {

			@Override
			public void onFooterRefresh(PullToRefreshView view) {
				// TODO Auto-generated method stub

				mCasesController.gengduo(true, true);
			}
		});

		mCasesController.setUIHandleLinstener(mHanleLintener);
		mCasesController.setUINotification(uiNotification);
		mCasesController.update(false, false);
	}

	private void showDeleteTitle() {
		casesDeleteTitle.main.setVisibility(View.VISIBLE);
		view_myCases_delete.setVisibility(View.VISIBLE);
	}

	private void HideDeleteTitle() {
		casesDeleteTitle.main.setVisibility(View.GONE);
		view_myCases_delete.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_title_back:
			finish();
			break;
		case R.id.fl_myCases_delete:

			delete(mCasesController.getSelectCasesGroupCount(),
					mCasesController.getSelectCasesCount());
			break;

		default:
			break;
		}

	}

	class CasesDeleteTitle {
		public View main;
		public TextView tv_selectTitle;
		public Button btn_clean;
		public Button btn_allSelect;
	}

	public class CasesDeleteIsOK {
		public View main;
		public TextView tv_selectDeleteTitle;
		public View btn_clearDelete;
		public View btn_okDelete;
	}

	private void delete(int groupCount, int casesCount) {
		// 创建PopupWindow对象

		casesDeleteIsOK.tv_selectDeleteTitle.setText("确定删除" + groupCount
				+ "个病例组（包含" + casesCount + "张病例）吗");

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
		}

		if (!pop_edit.isShowing()) {
			pop_edit.showAtLocation(casesDeleteIsOK.main, Gravity.BOTTOM, 0, 0);
		}
	}

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

		@Override
		public void onClick(Object info) {
			Intent intent = new Intent(MyCasesActivity.this,
					MyCasesSingleActivity.class);
			saveTmp(MyCasesActivity.this, (Record) info);
			startActivity(intent);
		}

	};

	public void saveTmp(Context context, Record info) {
		MyApplication.setmRecord(info);
	}

	UIHanleLintener mHanleLintener = new UIHanleLintener() {

		@Override
		public void update(boolean isServer, boolean fag, boolean isTouch) {
			// TODO Auto-generated method stub

			if (!isTouch) {
				Toast.makeText(getApplicationContext(), "加载更新完成",
						Toast.LENGTH_LONG).show();
				return;
			}
			ptrv_cases.postDelayed(new Runnable() {

				@Override
				public void run() {
					ptrv_cases.onHeaderRefreshComplete();
					Toast.makeText(getApplicationContext(), "上拉更新完成",
							Toast.LENGTH_LONG).show();
				}
			}, 2000);
		}

		@Override
		public void deletefinish(boolean fag) {
			// TODO Auto-generated method stub
			if (pop_edit != null && pop_edit.isShowing()) {
				pop_edit.dismiss();
			}
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
			ptrv_cases.postDelayed(new Runnable() {

				@Override
				public void run() {
					ptrv_cases.onFooterRefreshComplete();

					Toast.makeText(getApplicationContext(), "下拉更新完成",
							Toast.LENGTH_LONG).show();

				}
			}, 2000);
		}
	};

}
