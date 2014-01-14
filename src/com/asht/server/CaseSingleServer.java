//package com.asht.server;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.content.Context;
//
//import com.asht.interfaces.AddServerListener;
//import com.asht.interfaces.DeleteServerListener;
//import com.asht.interfaces.UpdateServerListener;
//import com.asht.model.Resume;
//import com.example.controller.AFinalController;
//
//public class CaseSingleServer {
//
//	public static void update(UpdateServerListener listener, Context context) {
//		if (listener != null) {
//
//			List<Resume> list = AFinalController.getDB(
//					context).findAll(Resume.class);
//			List<Object> objs = new ArrayList<Object>();
//			for (Resume info : list) {
//				objs.add(info);
//			}
//			Resume info = new Resume();
//			info.setAuditState(0);
//			info.setClinicalHistoryGroupId(list.size() + 1);
//			info.setClinicalHistoryGroupName("测试组" + 100 + list.size() + 1);
//			info.setCount(0);
//			info.setIsClick(1);
//			objs.add(info);
//			listener.update(objs, 0);
//		}
//	}
//
//	public static void add(AddServerListener listener, Context context,
//			Resume info) {
//		if (info == null) {
//			listener.add(null, 0);
//			return;
//		}
//		if (listener != null) {
//			listener.add(null, 0);
//		}
//
//	}
//
//	public static void delete(DeleteServerListener listener, Context context,
//			List<Resume> list) {
//		if (list == null) {
//			listener.delete(null, 0);
//			return;
//		}
//		if (listener != null) {
//			listener.delete(list, 0);
//		}
//	}
//
//}
