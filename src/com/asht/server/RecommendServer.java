//package com.asht.server;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.content.Context;
//
//import com.asht.info.RecommendInfo;
//import com.asht.interfaces.AddServerListener;
//import com.asht.interfaces.UpdateServerListener;
//import com.example.controller.AFinalController;
//
//public class RecommendServer {
//
//	public static void update(UpdateServerListener listener, Context context,
//			boolean fanzhe) {
//		if (listener != null) {
//
//			List<RecommendInfo> list = AFinalController.getDB(context)
//					.findAllByWhere(RecommendInfo.class,
//							"certificateId = " + (fanzhe ? 0 : 1));
//			List<Object> objs = new ArrayList<Object>();
//
//			for (RecommendInfo info : list) {
//				objs.add(info);
//			}
//			RecommendInfo re = new RecommendInfo();
//			re.setRecommendedName("测试" + list.size());
//			re.setAuditNote("测试" + list.size());
//			re.setCertificateId(fanzhe ? 0 : 1);
//			re.setAuditState(list.size() % 3 == 0 ? 0 : 1);
//			re.setRecommendId((int) (list.size() + 1000 + 1));
//			re.setIsClick(1);
//			objs.add(re);
//			listener.update(objs, 0);
//		}
//	}
//
//	public static void add(AddServerListener listener, Context context,
//			RecommendInfo info) {
//		if (info == null) {
//			listener.add(null, 0);
//			return;
//		}
//		if (listener != null) {
//			listener.add(info, 0);
//		}
//	}
//}
