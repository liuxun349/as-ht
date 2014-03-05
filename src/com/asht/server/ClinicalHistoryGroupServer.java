//package com.asht.server;
//
//import java.util.List;
//
//import net.tsz.afinal.FinalDb;
//import android.content.Context;
//
//import com.asht.info.ClinicalHistoryGroupInfo;
//
//public class ClinicalHistoryGroupServer {
//
//	public interface ServerLinstener {
//		void addClinicalHistoryGroupInfo(ClinicalHistoryGroupInfo info);
//
//		void deleteClinicalHistoryGroups(boolean fag);
//
//		void queryClinicalHistoryGroupInfos(List<ClinicalHistoryGroupInfo> list);
//
//		void isUpdateToserver(boolean fag);
//	}
//
//	ServerLinstener linstener;
//	Context mContext;
//	FinalDb finalDb;
//
//	public ClinicalHistoryGroupServer(Context context, ServerLinstener linstener) {
//		// TODO Auto-generated constructor stub
//		this.linstener = linstener;
//		mContext = context;
//		finalDb = FinalDb.create(context);
//	}
//
//	public void addClinicalHistoryGroupInfo(ClinicalHistoryGroupInfo info) {
//		// TODO Auto-generated method stub
//		// 上传到服务器
//		if (true) {
//			info.setClinicalHistoryGroupId((int) (System.currentTimeMillis() / 10000000));
//			linstener.addClinicalHistoryGroupInfo(info);
//		} else {
//			linstener.addClinicalHistoryGroupInfo(null);
//		}
//	}
//
//	public void deleteClinicalHistoryGroups(List ids) {
//		if (true) {
//			linstener.deleteClinicalHistoryGroups(true);
//		} else {
//			linstener.deleteClinicalHistoryGroups(false);
//		}
//	}
//
//	public void queryClinicalHistoryGroupInfos() {
//
//		if (true) {
//			linstener.queryClinicalHistoryGroupInfos(null);
//		} else {
//			linstener.queryClinicalHistoryGroupInfos(null);
//		}
//	}
//
//	// 对比服务器
//	public void isUpdateToserver() {
//		if (true) {
//			linstener.isUpdateToserver(true);
//		} else {
//			linstener.isUpdateToserver(false);
//		}
//	}
//}
