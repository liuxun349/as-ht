package com.asht.adapter;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalBitmap;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.asht.R;
import com.asht.model.Record;
import com.asht.model.Resume;
import com.asht.utl.Settings;

public class MyCasesAdapter extends BaseAdapter {

	private List<Record> infos = new ArrayList<Record>();
	private Context mContext;
	private static int width, height;
	FinalBitmap mBitmap;

	public void setInfos(List<Record> info) {
		if (infos.equals(info)) {
			return;
		}
		if (infos == null) {
			infos = new ArrayList<Record>();
		}
		this.infos = info;
	}

	public List<Record> getInfos() {
		return infos;
	}

	public void addRecord(Record info) {
		if (infos == null) {
			infos = new ArrayList<Record>();
		}
		if (!infos.contains(info)) {
			infos.add(info);
		}
	}

	public void addRecords(List<Record> list) {
		if (infos == null) {
			infos = new ArrayList<Record>();
		}
		infos.addAll(list);
	}

	public void removeRecord(Record info) {
		if (infos != null && infos.contains(info)) {
			infos.remove(info);
		}
	}

	public MyCasesAdapter(Context context, List<Record> lists, int width,
			int height) {
		infos = lists;
		if (infos == null) {
			infos = new ArrayList<Record>();
		}
		mContext = context;
		mBitmap = FinalBitmap.create(mContext);
		this.height = height;
		this.width = width;
	}

	@Override
	public int getCount() {
		if (infos == null) {
			infos = new ArrayList<Record>();
		}
		return infos.size();
	}

	@Override
	public Object getItem(int position) {
		return infos.get(position);
	}

	@Override
	public long getItemId(int position) {
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
					.findViewById(R.id.iv_myCases_pic2);
			myCasesItemView.iv3 = (ImageView) item_view
					.findViewById(R.id.iv_myCases_pic3);
			myCasesItemView.iv4 = (ImageView) item_view
					.findViewById(R.id.iv_myCases_pic4);
			myCasesItemView.iv_delete = item_view
					.findViewById(R.id.iv_myCases_delete);
			myCasesItemView.cbIsShenHe = (CheckBox) item_view
					.findViewById(R.id.cb_myCases_isTongGuo);
			myCasesItemView.tv_title = (TextView) item_view
					.findViewById(R.id.tv_myCases_title);
			convertView = item_view;
			int spacing = (int) mContext.getResources().getDimension(
					R.dimen.grid_spacing);
			// 获取分辨率
			AbsListView.LayoutParams ll = new AbsListView.LayoutParams(width,
					height, 1);
			LinearLayout.LayoutParams llitem1 = new LinearLayout.LayoutParams(
					(width - spacing * 2) / 2, height / 2);
			LinearLayout.LayoutParams llitem2 = new LinearLayout.LayoutParams(
					(width - spacing * 2) / 2, height / 2);
			LinearLayout.LayoutParams llitem3 = new LinearLayout.LayoutParams(
					(width - spacing * 2) / 2, height / 2);
			LinearLayout.LayoutParams llitem4 = new LinearLayout.LayoutParams(
					(width - spacing * 2) / 2, height / 2);

			myCasesItemView.iv1.setLayoutParams(llitem1);
			llitem2.leftMargin = spacing;
			myCasesItemView.iv2.setLayoutParams(llitem2);
			myCasesItemView.iv3.setLayoutParams(llitem3);
			llitem4.leftMargin = spacing;
			myCasesItemView.iv4.setLayoutParams(llitem4);
			convertView.setLayoutParams(ll);
			convertView.setTag(myCasesItemView);
		}
		myCasesItemView = (MyCasesItemView) convertView.getTag();

		Record myCasesInfo = infos.get(position);

		myCasesItemView.tv_title.setText(myCasesInfo
				.getMedicalRecordGroupName());
		myCasesItemView.cbIsShenHe.setChecked(myCasesInfo.getState()
				.equals("1"));
		if (myCasesInfo.isClick == 0) {
			myCasesItemView.iv_delete.setVisibility(View.VISIBLE);
		} else {
			myCasesItemView.iv_delete.setVisibility(View.GONE);
		}
		// if (myCasesInfo.getIsUpdate() == 1) {
		// // 需要更新
		// CaseView cv = new CaseView();
		// cv.setRecord(myCasesInfo);
		// cv.setView(myCasesItemView);
		// MainThread.getInit(mContext).add(cv);
		// }

		List<Resume> result = myCasesInfo.getResumeList();

		if (result == null) {
		} else {
			int size = result.size();
			switch (result.size()) {
			case 0:
				myCasesItemView.iv1.setImageBitmap(null);
				myCasesItemView.iv2.setImageBitmap(null);
				myCasesItemView.iv3.setImageBitmap(null);
				myCasesItemView.iv4.setImageBitmap(null);
				break;
			case 1:
				mBitmap.display(myCasesItemView.iv1, Settings.WEB_URL
						+ result.get(0).getMinFileName(), width / 4, height / 4);
				myCasesItemView.iv2.setImageBitmap(null);
				myCasesItemView.iv3.setImageBitmap(null);
				myCasesItemView.iv4.setImageBitmap(null);
				break;
			case 2:

				mBitmap.display(myCasesItemView.iv1, Settings.WEB_URL
						+ result.get(0).getMinFileName(), width / 4, height / 4);
				mBitmap.display(myCasesItemView.iv2, Settings.WEB_URL
						+ result.get(1).getMinFileName(), width / 4, height / 4);
				myCasesItemView.iv3.setImageBitmap(null);
				myCasesItemView.iv4.setImageBitmap(null);
				break;
			case 3:
				mBitmap.display(myCasesItemView.iv1, Settings.WEB_URL
						+ result.get(0).getMinFileName(), width / 4, height / 4);
				mBitmap.display(myCasesItemView.iv2, Settings.WEB_URL
						+ result.get(1).getMinFileName(), width / 4, height / 4);
				mBitmap.display(myCasesItemView.iv3, Settings.WEB_URL
						+ result.get(2).getMinFileName(), width / 4, height / 4);
				myCasesItemView.iv4.setImageBitmap(null);
				break;
			case 4:

				mBitmap.display(myCasesItemView.iv1, Settings.WEB_URL
						+ result.get(0).getMinFileName(), width / 4, height / 4);
				mBitmap.display(myCasesItemView.iv2, Settings.WEB_URL
						+ result.get(1).getMinFileName(), width / 4, height / 4);
				mBitmap.display(myCasesItemView.iv3, Settings.WEB_URL
						+ result.get(2).getMinFileName(), width / 4, height / 4);
				mBitmap.display(myCasesItemView.iv4, Settings.WEB_URL
						+ result.get(3).getMinFileName(), width / 4, height / 4);
				break;
			}
		}

		return convertView;
	}

	public class MyCasesItemView {
		public TextView tv_title;
		public ImageView iv1, iv2, iv3, iv4;
		public CheckBox cbIsShenHe;
		public View iv_delete;
	}

}
