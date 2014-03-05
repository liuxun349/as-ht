//package com.example.dao;
//
//import java.util.List;
//
//import android.content.Context;
//
//import com.asht.interfaces.AddServerListener;
//import com.asht.interfaces.DeleteServerListener;
//import com.asht.interfaces.UpdateServerListener;
//import com.asht.model.Record;
//import com.asht.server.CaseServer;
//import com.example.controller.AFinalController;
//
//public class CaseDao {
//
//	public interface CaseUpdateListener {
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
//		void update(List<Record> list, boolean isServer,
//				int tag);
//	};
//
//	public interface CaseAddListener {
//		/**
//		 * 推荐人到服务器
//		 * 
//		 * @param info
//		 *            向服务器添加的数据
//		 * @param tag
//		 *            服务器返回的状态
//		 */
//		void add(Record info, int tag);
//	};
//
//	public interface CaseDeleteListener {
//		/**
//		 * 推荐人到服务器
//		 * 
//		 * @param info
//		 *            向服务器添加的数据
//		 * @param tag
//		 *            服务器返回的状态
//		 */
//		void delete(List<Record> list, int tag);
//	};
//
//	public static synchronized void update(final Context context,
//			final CaseUpdateListener listener, boolean fag) {
//		if (listener == null) {
//			return;
//		}
//
//		List<Record> list = AFinalController.getDB(context)
//				.findAll(Record.class);
//		if (list != null && list.size() > 0) {
//			listener.update(list.subList(0, list.size()), false, -1);
//		} else {
//			Record info = new Record();
//			info.setAuditState(0);
//			info.setClinicalHistoryGroupId(list.size() + 1);
//			info.setClinicalHistoryGroupName("测试组" + 100 + list.size() + 1);
//			info.setCount(0);
//			info.setIsClick(1);
//
//			AFinalController.getDB(context).save(info);
//			list = AFinalController.getDB(context).findAll(
//					Record.class);
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
//				if (list == null) {
//					return;
//				}
//				for (Object obj : list) {
//					Record info = (Record) obj;
//
//					AFinalController.getDB(context).deleteByWhere(
//							Record.class,
//							"clinicalHistoryGroupId = "
//									+ info.getClinicalHistoryGroupId());
//					AFinalController.getDB(context).save(info);
//				}
//				List<Record> infos = AFinalController.getDB(
//						context).findAll(Record.class);
//				listener.update(infos, true, tag);
//			}
//		};
//
//		CaseServer.update(listenerServer, context);
//	}
//
//	public static synchronized void add(final Context context,
//			final CaseAddListener listener, Record info) {
//
//		CaseServer.add(new AddServerListener() {
//
//			@Override
//			public void add(Object info, int tag) {
//				if (info != null) {
//					Record info2 = (Record) info;
//					AFinalController.getDB(context).save(info2);
//					List<Record> list = AFinalController
//							.getDB(context)
//							.findAllByWhere(
//									Record.class,
//									" clinicalHistoryGroupId ="
//											+ info2.getClinicalHistoryGroupId());
//					if (list != null && list.size() > 0) {
//						listener.add(list.get(0), tag);
//					} else
//						listener.add(null, tag);
//				}
//			}
//		}, context, info);
//	}
//
//	public static synchronized void delete(final Context context,
//			final CaseDeleteListener listener,
//			List<Record> list) {
//
//		CaseServer.delete(new DeleteServerListener() {
//
//			@SuppressWarnings("unchecked")
//			@Override
//			public void delete(List<?> list, int tag) {
//
//				if (list == null) {
//					listener.delete(null, tag);
//				} else {
//					for (Object obj : list) {
//						Record info = (Record) obj;
//
//						AFinalController.getDB(context).deleteByWhere(
//								Record.class,
//								"clinicalHistoryGroupId = "
//										+ info.getClinicalHistoryGroupId());
//					}
//
//					listener.delete((List<Record>) list, tag);
//				}
//
//			}
//		}, context, list);
//
//	}
//}
