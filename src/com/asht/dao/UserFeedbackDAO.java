package com.asht.dao;

import org.json.JSONException;
import org.json.JSONObject;

public class UserFeedbackDAO {

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
		JSONObject result = NetworkConnectionDAO.connection(mname, param);
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
		JSONObject result = NetworkConnectionDAO.connection(mname, param);
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
		JSONObject result = NetworkConnectionDAO.connection(mname, param);
		return result;
	}
	
	/**
	 * 根据条件查找意见反馈
	 * 
	 * @return
	 */
	public JSONObject searchAdvices(String Start, String Count,
			String Field, String Value, String Junction) {
		String name = "SearchAdvices";

		JSONObject param = new JSONObject();
		JSONObject pager = new JSONObject();
		JSONObject searchConditions = new JSONObject();
		try {
			pager.put("Start", Start);
			pager.put("Count", Count);
			searchConditions.put("Field", Field);
			searchConditions.put("Value", Value);
			searchConditions.put("Junction", Junction);

			param.put("Pager", pager);
			param.put("SearchConditions", searchConditions);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject result = NetworkConnectionDAO.connection(name, param);
		return result;
	}
}
