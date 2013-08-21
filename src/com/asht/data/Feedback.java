package com.asht.data;

import org.json.JSONObject;

public class Feedback {

	/**
	 * 添加意见反馈
	 * 
	 * @return
	 */
	public JSONObject getZCouponInfo(String AdviceContent, String UserID) {
		String mname = "GetZCouponInfo";
		JSONObject param = new JSONObject();

		try {
			param.put("AdviceContent", AdviceContent);
			param.put("UserID", UserID);
		} catch (Exception e) {
		}
		JSONObject result = NetworkConnection.connection(mname, param);
		return result;
	}
	
	/**
	 * 处理反馈意见（单个或批量）
	 * 
	 * @return
	 */
	public JSONObject processAdvices(String AdviceID, String ProcessNote) {
		String mname = "ProcessAdvices";
		JSONObject param = new JSONObject();

		try {
			param.put("AdviceID", AdviceID);
			param.put("ProcessNote", ProcessNote);
		} catch (Exception e) {
		}
		JSONObject result = NetworkConnection.connection(mname, param);
		return result;
	}
	
	/**
	 * 删除反馈意见（单个或批量）
	 * 
	 * @return
	 */
	public JSONObject deleteAdvices(String AdviceID) {
		String mname = "DeleteAdvices";
		JSONObject param = new JSONObject();

		try {
			param.put("AdviceId", AdviceID);
		} catch (Exception e) {
		}
		JSONObject result = NetworkConnection.connection(mname, param);
		return result;
	}
}
