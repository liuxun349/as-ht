package com.asht.controller;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.asht.R;

public class MyCasesSingleActivity_old extends Activity implements OnClickListener {

	GridView gv_myCasesSingle;
	CasesSingleAdapter casesSingleAdapter;
	private List<MyCasesSingleInfo> casesSingleInfos = null;
	private static int Spacing = 4;
	PopupWindow pop_edit = null;
	private boolean isDelete = false;
	CasesSingleDeleteTitle casesSingleDeleteTitle;
	private View view_myCasesSingle_delete;
	private CasesDeleteIsOK casesDeleteIsOK;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_case_single);
		findViewById(R.id.tv_title_back).setOnClickListener(this);
		initView();
		initData();
		int spacing = (int) getResources().getDimension(R.dimen.grid_spacing);
		Spacing = spacing;
		findViewById(R.id.tv_localimport).setOnClickListener(this);
		findViewById(R.id.tv_cameraimport).setOnClickListener(this);
	}

	private void initView() {
		gv_myCasesSingle = (GridView) findViewById(R.id.gv_myCasesSingle);
		gv_myCasesSingle.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (isDelete) {
					selectManager(arg2);
				} else {
					// Intent intent = new Intent(MyCasesSingleActivity.this,
					// MyCasesSingleActivity.class);
					// startActivity(intent);
				}
			}
		});
		gv_myCasesSingle
				.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> arg0,
							View arg1, int arg2, long arg3) {
						isDelete = true;
						if (isDelete) {
							selectManager(arg2);
							showDeleteTitle();
						}
						return true;
					}
				});
		initDeleteTitle();
		initDeleteIsOK();
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

	private void initData() {
		casesSingleInfos = new ArrayList<MyCasesSingleActivity_old.MyCasesSingleInfo>();
		for (int i = 0; i < 31; i++) {
			MyCasesSingleInfo info = new MyCasesSingleInfo();
			if (i % 2 == 0) {
				info.shenhe = true;
			} else {
				info.shenhe = false;
			}
			info.tongguo = false;
			casesSingleInfos.add(info);
		}
		casesSingleAdapter = new CasesSingleAdapter(this, casesSingleInfos);
		gv_myCasesSingle.setAdapter(casesSingleAdapter);
	}

	private void showDeleteTitle() {
		casesSingleDeleteTitle.main.setVisibility(View.VISIBLE);
		view_myCasesSingle_delete.setVisibility(View.VISIBLE);
	}

	private void HideDeleteTitle() {
		casesSingleDeleteTitle.main.setVisibility(View.GONE);
		view_myCasesSingle_delete.setVisibility(View.GONE);
	}

	private void selectManager(int index) {
		setSelectByIndex(index);
		setDeleteTitle(getSelectCount());
	}

	private void setSelectByIndex(int index) {
		MyCasesSingleInfo casesInfo = ((MyCasesSingleInfo) casesSingleAdapter
				.getItem(index));
		casesInfo.tongguo = !casesInfo.tongguo;
		casesSingleAdapter.notifyDataSetChanged();
	}

	private int getSelectCount() {
		int select = 0;
		int count = casesSingleAdapter.getCount();
		for (int i = 0; i < count; i++) {
			boolean fag = ((MyCasesSingleInfo) casesSingleAdapter.getItem(i)).tongguo;
			if (fag)
				select++;
		}
		return select;
	}

	private int select(boolean fag) {
		int count = casesSingleAdapter.getCount();
		for (int i = 0; i < count; i++) {
			MyCasesSingleInfo casesInfo = ((MyCasesSingleInfo) casesSingleAdapter
					.getItem(i));
			if (fag) {
				casesInfo.tongguo = true;
			} else {
				casesInfo.tongguo = false;
			}
		}
		casesSingleAdapter.notifyDataSetChanged();
		return fag ? count : 0;
	}

	private void selectClean() {
		select(false);
		casesSingleDeleteTitle.tv_selectTitle.setText("没有选中");
		isDelete = false;
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
		case R.id.tv_localimport:
			break;
		default:
			break;
		}
	}

	class CasesSingleAdapter extends BaseAdapter {
		private Context mcontext;
		List<MyCasesSingleInfo> casesInfos;

		public CasesSingleAdapter(Context context, List<MyCasesSingleInfo> lists) {
			mcontext = context;
			casesInfos = lists;
		}

		@Override
		public int getCount() {
			return casesInfos.size();
		}

		@Override
		public Object getItem(int position) {
			return casesInfos.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			MyCasesSingleItemView hview = null;
			if (convertView == null) {
				convertView = View.inflate(mcontext,
						R.layout.activity_my_case_single_item, null);
				hview = new MyCasesSingleItemView();
				hview.cbIsShenHe = (CheckBox) convertView
						.findViewById(R.id.cb_myCasesSingle_isTongGuo);
				hview.iv1 = (ImageView) convertView
						.findViewById(R.id.iv_myCasesSingle_pic);
				hview.iv_delete = (ImageView) convertView
						.findViewById(R.id.iv_myCasesSingle_delete);
				// 获取分辨率
				DisplayMetrics dm = new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getMetrics(dm);

				int nowWidth = (dm.widthPixels - Spacing * 3) / 4; // 当前分辨率 宽度
				AbsListView.LayoutParams ll = new AbsListView.LayoutParams(
						nowWidth, nowWidth, 1);
				convertView.setLayoutParams(ll);
				convertView.setTag(hview);
			}
			hview = (MyCasesSingleItemView) convertView.getTag();
			hview.iv1.setImageResource(R.drawable.ic_launcher);
			MyCasesSingleInfo info = casesInfos.get(position);
			hview.cbIsShenHe.setChecked(info.shenhe);
			if (info.tongguo) {
				hview.iv_delete.setVisibility(View.VISIBLE);
			} else {
				hview.iv_delete.setVisibility(View.GONE);
			}

			return convertView;
		}

	}

	class MyCasesSingleInfo {
		boolean shenhe = false;

		boolean tongguo = false;

		public boolean isShenhe() {
			return shenhe;
		}

		public void setShenhe(boolean shenhe) {
			this.shenhe = shenhe;
		}

		public boolean isTongguo() {
			return tongguo;
		}

		public void setTongguo(boolean tongguo) {
			this.tongguo = tongguo;
		}

	}

	class MyCasesSingleItemView {
		public ImageView iv1, iv_delete;
		public CheckBox cbIsShenHe;

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

	OnClickListener casesDeleteOnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_myCasesSingle_selected_allSelect:
				setDeleteTitle(select(true));
				break;
			case R.id.btn_myCasesSingle_selected_cleanSelect:
				selectClean();
				break;
			default:
				break;
			}
		}
	};

	private void delete() {
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
		}
		if (!pop_edit.isShowing()) {
			pop_edit.showAtLocation(casesDeleteIsOK.main, Gravity.BOTTOM, 0, 0);
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
			}
		}
	};
}
