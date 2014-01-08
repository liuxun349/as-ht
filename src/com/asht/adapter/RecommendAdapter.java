package com.asht.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.asht.info.RecommendInfo;
import com.asht.R;

public class RecommendAdapter extends BaseAdapter {

	private List<RecommendInfo> infos;
	private Context mContext;
	private int width, height;

	public void setInfos(List<RecommendInfo> info) {
		if (infos.equals(info)) {
			return;
		}
		this.infos = info;
	}

	public List<RecommendInfo> getInfos() {
		return infos;
	}

	public void addRecommendInfo(RecommendInfo info) {
		if (infos != null && infos.contains(info)) {
			infos.add(info);
		}
	}

	public void removeRecommendInfo(RecommendInfo info) {
		if (infos != null && infos.contains(info)) {
			infos.remove(info);
		}
	}

	public RecommendAdapter(Context context, List<RecommendInfo> lists,
			int width, int height) {
		infos = lists;
		if (infos == null) {
			infos = new ArrayList<RecommendInfo>();
		}
		mContext = context;
		this.height = height;
		this.width = width;
	}

	@Override
	public int getCount() {
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
			View item_view = View.inflate(mContext, R.layout.recommend_item,
					null);
			myCasesItemView.iv_delete = item_view
					.findViewById(R.id.iv_recommend_delete);
			myCasesItemView.cbIsShenHe = (CheckBox) item_view
					.findViewById(R.id.cb_iv_recommend_isTongGuo);
			myCasesItemView.tv_title = (TextView) item_view
					.findViewById(R.id.txt_name);
			convertView = item_view;
			// 获取分辨率
			AbsListView.LayoutParams ll = new AbsListView.LayoutParams(width,
					height, 1);
			convertView.setLayoutParams(ll);
			convertView.setTag(myCasesItemView);
			convertView.setBackgroundColor(Color.parseColor("#33333333"));
		}
		myCasesItemView = (MyCasesItemView) convertView.getTag();

		RecommendInfo info = infos.get(position);
		String name = (info.getCertificateId() == 0 ? "患者" : "医生")
				+ info.getRecommendedName();
		myCasesItemView.tv_title.setText(name);
		myCasesItemView.cbIsShenHe.setChecked(info.getAuditState() == 0);
		if (info.getIsClick() == 0) {
			myCasesItemView.iv_delete.setVisibility(View.VISIBLE);
		} else {
			myCasesItemView.iv_delete.setVisibility(View.GONE);
		}
		return convertView;
	}

	public class MyCasesItemView {
		public TextView tv_title;
		public CheckBox cbIsShenHe;
		public View iv_delete;
	}

}
