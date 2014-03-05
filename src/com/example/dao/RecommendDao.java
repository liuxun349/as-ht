//package com.example.dao;
//
//import java.util.List;
//
//import android.content.Context;
//
//import com.asht.info.RecommendInfo;
//import com.asht.interfaces.AddServerListener;
//import com.asht.interfaces.UpdateServerListener;
//import com.asht.server.RecommendServer;
//import com.example.controller.AFinalController;
//
//public class RecommendDao {
//
//	public interface RecommendUpdateListener {
//		/**
//		 * 返回更新成功的数据
//		 * 
//		 * @param list
//		 *            更新的数据集合
//		 * @param isServer
//		 *            false：本地数据，true：服务器数据
//		 * @param tag
//		 *            服务器返回的状态
//		 */
//		void update(List<RecommendInfo> list, boolean isServer, int tag);
//	};
//
//	public interface RecommendAddListener {
//		/**
//		 * 推荐人到服务器
//		 * 
//		 * @param info
//		 *            向服务器添加的数据
//		 * @param tag
//		 *            服务器返回的状态
//		 */
//		void add(RecommendInfo info, int tag);
//	};
//
//	public static synchronized void update(final Context context,
//			final RecommendUpdateListener listener, boolean fag,
//			final boolean fanzhe) {
//		if (listener == null) {
//			return;
//		}
//
//		List<RecommendInfo> list = AFinalController.getDB(context)
//				.findAllByWhere(RecommendInfo.class,
//						"certificateId = " + (fanzhe ? 0 : 1));
//		if (list != null && list.size() > 0) {
//			listener.update(list.subList(0, list.size()), false, -1);
//		} else {
//			RecommendInfo re = new RecommendInfo();
//			re.setRecommendedName("测是1");
//			re.setAuditNote("测是1");
//			re.setCertificateId(fanzhe ? 0 : 1);
//			re.setAuditState(1);
//			re.setRecommendId((int) (1000 + 1));
//			re.setIsClick(1);
//
//			AFinalController.getDB(context).save(re);
//			list = AFinalController.getDB(context).findAllByWhere(
//					RecommendInfo.class, "certificateId = " + (fanzhe ? 0 : 1));
//			listener.update(list.subList(0, list.size()), false, -1);
//		}
//		if (!fag) {
//			return;
//		}
//
//		final UpdateServerListener listenerServer = new UpdateServerListener() {
//
//			@Override
//			public void update(List<?> list, int tag) {
//				AFinalController.getDB(context).deleteByWhere(
//						RecommendInfo.class,
//						"certificateId = " + (fanzhe ? 0 : 1));
//				if (list == null) {
//					return;
//				}
//				for (Object obj : list) {
//					RecommendInfo info = (RecommendInfo) obj;
//					AFinalController.getDB(context).save(info);
//				}
//				List<RecommendInfo> infos = AFinalController.getDB(context)
//						.findAllByWhere(RecommendInfo.class,
//								"certificateId = " + (fanzhe ? 0 : 1));
//				listener.update(infos, true, tag);
//			}
//		};
//
//		RecommendServer.update(listenerServer, context, fanzhe);
//	}
//
//	public static synchronized void add(final Context context,
//			final RecommendAddListener listener, RecommendInfo info) {
//
//		RecommendServer.add(new AddServerListener() {
//			@Override
//			public void add(Object info, int tag) {
//				if (info != null) {
//					RecommendInfo info2 = (RecommendInfo) info;
//					AFinalController.getDB(context).save(info2);
//					List<RecommendInfo> list = AFinalController.getDB(context)
//							.findAllByWhere(RecommendInfo.class,
//									"recommendId = " + info2.getRecommendId());
//					if (list != null && list.size() > 0) {
//						listener.add(list.get(0), tag);
//					} else {
//						listener.add(null, tag);
//					}
//				} else {
//					listener.add(null, tag);
//				}
//			}
//		}, context, info);
//
//	}
//}
