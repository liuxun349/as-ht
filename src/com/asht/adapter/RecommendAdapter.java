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

import com.asht.R;
import com.asht.model.Recommend;

public class RecommendAdapter extends BaseAdapter {

	private List<Recommend> infos;
	private Context mContext;
	private int width, height;

	public void setInfos(List<Recommend> info) {
		if (infos.equals(info)) {
			return;
		}
		this.infos = info;
	}

	public List<Recommend> getInfos() {
		return infos;
	}

	public void addRecommend(Recommend info) {
		if (infos != null && infos.contains(info)) {
			infos.add(info);
		}
	}

	public void removeRecommend(Recommend info) {
		if (infos != null && infos.contains(info)) {
			infos.remove(info);
		}
	}

	public RecommendAdapter(Context context, List<Recommend> lists, int width,
			int height) {
		infos = lists;
		if (infos == null) {
			infos = new ArrayList<Recommend>();
		}
		mContext = context;
		this.height = height;
		this.width = width;
	}

	@Override
	public int getCount() {
		if (infos == null) {
			infos = new ArrayList<Recommend>();
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
		RecommendView myRecommendView;
		if (convertView == null) {
			myRecommendView = new RecommendView();
			View item_view = View.inflate(mContext, R.layout.recommend_item,
					null);
			myRecommendView.cbIsShenHe = (CheckBox) item_view
					.findViewById(R.id.cb_iv_recommend_isTongGuo);
			myRecommendView.tv_title = (TextView) item_view
					.findViewById(R.id.txt_name);
			myRecommendView.view_add = item_view.findViewById(R.id.view_add);
			convertView = item_view;
			// 获取分辨率
			AbsListView.LayoutParams ll = new AbsListView.LayoutParams(width,
					height, 1);
			convertView.setLayoutParams(ll);
			convertView.setTag(myRecommendView);
			convertView.setBackgroundColor(Color.parseColor("#33333333"));
		}
		myRecommendView = (RecommendView) convertView.getTag();

		Recommend info = infos.get(position);
		if (info == null) {
			myRecommendView.view_add.setVisibility(View.VISIBLE);
			myRecommendView.tv_title.setText("");
			myRecommendView.cbIsShenHe.setVisibility(View.GONE);
			return convertView;
		} else {
			if (myRecommendView.view_add.getVisibility() == View.VISIBLE)
				myRecommendView.view_add.setVisibility(View.GONE);
			if (myRecommendView.cbIsShenHe.getVisibility() == View.GONE)
				myRecommendView.cbIsShenHe.setVisibility(View.VISIBLE);
		}

		String name = (info.getRecommendRoleId() == "1001" ? "患者" : "医生")
				+ info.getRecommendtrueName();
		myRecommendView.tv_title.setText(name);
		myRecommendView.cbIsShenHe.setChecked(info.getRecommendState().equals(
				"1"));
		// myRecommend.cbIsShenHe.setChecked(info.getAuditState() == 0);
		// if (info.getIsClick() == 0) {
		// myRecommend.iv_delete.setVisibility(View.VISIBLE);
		// } else {
		// myRecommend.iv_delete.setVisibility(View.GONE);
		// }
		return convertView;
	}

	public class RecommendView {
		public TextView tv_title;
		public CheckBox cbIsShenHe;
		public View iv_delete, view_add;
	}

}
