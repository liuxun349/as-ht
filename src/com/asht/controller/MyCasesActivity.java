package com.asht.controller;

import java.util.ArrayList;
import java.util.List;

import com.asht.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class MyCasesActivity extends Activity implements OnClickListener {

	private GridView gv_myCases = null;
	private TextView txt_title_back = null;
	MyCasesAdapter myCasesAdapter;
	private boolean isDelete = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_cases);
		initView();
		initListener();
		initData();
	}

	private void initView() {
		gv_myCases = (GridView) findViewById(R.id.gv_myCases);
		txt_title_back = (TextView) findViewById(R.id.tv_title_back);
		findViewById(R.id.fl_myCases_delete).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				findViewById(R.id.ll_myCases_delete_isOk).setVisibility(View.VISIBLE);
			}
		});
	};

	private void initListener() {
		txt_title_back.setOnClickListener(this);
	}

	private void initData() {
		List<MyCasesInfo> list = new ArrayList<MyCasesActivity.MyCasesInfo>();
		for (int i = 0; i < 50; i++) {
			MyCasesInfo info = new MyCasesInfo();
			if (i % 2 == 0) {
				info.shenhe = true;
			} else {
				info.shenhe = false;
			}
			info.tongguo = false;
			list.add(info);
		}
		myCasesAdapter = new MyCasesAdapter(list, getApplicationContext());
		gv_myCases.setAdapter(myCasesAdapter);
		gv_myCases.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (isDelete) {
					((MyCasesInfo) myCasesAdapter.getItem(arg2)).tongguo = !((MyCasesInfo) myCasesAdapter
							.getItem(arg2)).tongguo;
					myCasesAdapter.notifyDataSetChanged();showDeleteTitle();
				}
			}

		});
		gv_myCases.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				isDelete = true;
				return false;
			}
		});
	}
	
	
	void showDeleteTitle(){
		findViewById(R.id.fl_myCases_selected_title).setVisibility(View.VISIBLE);
		findViewById(R.id.fl_myCases_delete).setVisibility(View.VISIBLE);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_title_back:

			break;

		default:
			break;
		}

	}

	class MyCasesInfo {
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

	class MyCasesAdapter extends BaseAdapter implements ListAdapter {

		private List<MyCasesInfo> myCasesInfos;
		private Context mContext;

		public MyCasesAdapter(List<MyCasesInfo> lists, Context context) {
			myCasesInfos = lists;
			mContext = context;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return myCasesInfos.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return myCasesInfos.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			MyCasesItemView myCasesItemView;
			if (convertView == null) {

				myCasesItemView = new MyCasesItemView();
				View item_view = View.inflate(mContext,
						R.layout.activity_my_cases_item, null);
				myCasesItemView.iv1 = (ImageView) item_view
						.findViewById(R.id.iv_myCases_pic1);
				myCasesItemView.iv2 = (ImageView) item_view
						.findViewById(R.id.iv_pic2);
				myCasesItemView.iv3 = (ImageView) item_view
						.findViewById(R.id.iv_myCases_pic3);
				myCasesItemView.iv4 = (ImageView) item_view
						.findViewById(R.id.iv_myCases_pic4);
				myCasesItemView.iv_delete = (ImageView) item_view
						.findViewById(R.id.iv_myCases_delete);
				myCasesItemView.cbIsShenHe = (CheckBox) item_view
						.findViewById(R.id.cb_myCases_isTongGuo);
				myCasesItemView.tv_title = (TextView) item_view
						.findViewById(R.id.tv_myCases_title);
				convertView = item_view;
				convertView.setTag(myCasesItemView);
			}
			myCasesItemView = (MyCasesItemView) convertView.getTag();

			MyCasesInfo myCasesInfo = myCasesInfos.get(position);
			System.out.println(position + "  " + myCasesInfo.shenhe + "  "
					+ myCasesInfo.shenhe);
			myCasesItemView.tv_title.setText("��" + position);
			myCasesItemView.cbIsShenHe.setChecked(myCasesInfo.shenhe);
			if (myCasesInfo.tongguo) {
				myCasesItemView.iv_delete.setVisibility(View.VISIBLE);
			} else {
				myCasesItemView.iv_delete.setVisibility(View.GONE);

			}
			return convertView;
		}
	}

	class MyCasesItemView {
		public TextView tv_title;
		public ImageView iv1, iv2, iv3, iv4, iv_delete;
		public CheckBox cbIsShenHe;

	}

}
