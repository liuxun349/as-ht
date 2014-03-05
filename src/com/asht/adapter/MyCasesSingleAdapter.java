package com.asht.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.asht.R;
import com.asht.model.Resume;
import com.asht.utl.Settings;
import com.yj.compress.YJBitmap;

public class MyCasesSingleAdapter extends BaseAdapter {

	private List<Resume> infos;
	private Context mContext;
	YJBitmap mYjBitmap;
	private int width, height;

	public void setInfos(List<Resume> info) {
		if (infos.equals(info)) {
			return;
		}

		this.infos = info;
		if (infos == null) {
			infos = new ArrayList<Resume>();
		}
	}

	public List<Resume> getInfos() {
		return infos;
	}

	public void addResume(Resume info) {
		if (infos == null) {
			infos = new ArrayList<Resume>();
		}
		if (infos != null && infos.contains(info)) {
			infos.add(info);
		}
	}

	public void addResumes(List<Resume> info) {
		if (infos == null) {
			infos = new ArrayList<Resume>();
		}
		if (info != null) {
			for (Resume resume : info) {
				if (resume != null)
					infos.add(resume);
			}
		}
	}

	public void removeResume(Resume info) {
		if (infos != null && infos.contains(info)) {
			infos.remove(info);
		}
	}

	public MyCasesSingleAdapter(Context context, List<Resume> lists, int width,
			int height) {
		infos = lists;
		if (infos == null) {
			infos = new ArrayList<Resume>();
		}
		mContext = context;
		this.height = height;
		this.width = width;
		mYjBitmap = YJBitmap.create(context);
	}

	@Override
	public int getCount() {
		if (infos == null) {
			infos = new ArrayList<Resume>();
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
		MyCasesSingleItemView hview = null;
		if (convertView == null) {
			convertView = View.inflate(mContext,
					R.layout.activity_my_case_single_item, null);
			hview = new MyCasesSingleItemView();
			hview.cbIsShenHe = (CheckBox) convertView
					.findViewById(R.id.cb_myCasesSingle_isTongGuo);
			hview.iv1 = (ImageView) convertView
					.findViewById(R.id.iv_myCasesSingle_pic);
			hview.iv_delete = (ImageView) convertView
					.findViewById(R.id.iv_myCasesSingle_delete);
			hview.tv_case_upload = (TextView) convertView
					.findViewById(R.id.tv_case_upload);

			AbsListView.LayoutParams ll = new AbsListView.LayoutParams(width,
					height, 1);
			convertView.setLayoutParams(ll);
			convertView.setTag(hview);
		}

		hview = (MyCasesSingleItemView) convertView.getTag();
		Resume info = infos.get(position);
		// bit.display(hview.iv1,
		// Settings.WEB_URL + info.getMinFileName());
		if (info.getState() != 3) {
			mYjBitmap.display(hview.iv1, info.getLocalRecordImageUrl(), width,
					width);
			hview.cbIsShenHe.setVisibility(View.GONE);
			hview.iv_delete.setVisibility(View.GONE);
			hview.tv_case_upload.setVisibility(View.VISIBLE);
			if (info.getState() == 2) {
				(hview.tv_case_upload).setText("上传");
			} else {
				(hview.tv_case_upload).setText("删除");
			}
		} else {

			hview.tv_case_upload.setVisibility(View.GONE);
			mYjBitmap.display(hview.iv1, Settings.WEB_URL
					+ info.getMinFileName(), width, width);
			hview.cbIsShenHe.setChecked("1".equals(info.getIstate()));

			hview.cbIsShenHe.setVisibility(View.VISIBLE);
			if (info.isClick == 0) {
				hview.iv_delete.setVisibility(View.VISIBLE);
			} else {
				hview.iv_delete.setVisibility(View.GONE);
			}
		}
		return convertView;
	}

	public class MyCasesSingleItemView {
		public ImageView iv1, iv_delete;
		public CheckBox cbIsShenHe;
		public TextView tv_case_upload;
	}

}
