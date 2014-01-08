package com.asht.server;

import java.util.List;

import android.content.Context;

import com.asht.model.Resume;

public class ClinicalHistoryServer {

	public interface ServerLinstener {
		void addResume(Resume info);

		void deleteClinicalHistorys(boolean fag);

		void queryResumes(List<Resume> list);

		void isUpdateToserver(boolean fag);
	}

	ServerLinstener linstener;
	Context mContext;

	public ClinicalHistoryServer(Context context, ServerLinstener linstener) {
		// TODO Auto-generated constructor stub
		this.linstener = linstener;
		mContext = context;
	}

	public void addResume(Resume info) {
		// TODO Auto-generated method stub
		// 上传到服务器
		if (true) {
			linstener.addResume(info);
		} else {
			linstener.addResume(null);
		}
	}

	public void deleteClinicalHistorys(List ids) {
		if (true) {
			linstener.deleteClinicalHistorys(true);
		} else {
			linstener.deleteClinicalHistorys(false);
		}
	}

	public void queryResumes() {
		if (true) {
			linstener.queryResumes(null);
		} else {
			linstener.queryResumes(null);
		}
	}

	// 对比服务器
	public void isUpdateToserver() {
		if (true) {
			linstener.isUpdateToserver(true);
		} else {
			linstener.isUpdateToserver(false);
		}
	}
}
