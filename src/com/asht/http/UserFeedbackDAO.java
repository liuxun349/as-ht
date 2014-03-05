package com.asht.http;

import org.json.JSONException;
import org.json.JSONObject;

import com.asht.utl.ConnCallback;

public class UserFeedbackDAO {
	private String URL_NAME = "";
	/**
	 * 添加意见反馈
	 * 
	 * @return
	 */
	public void getZCouponInfo(String AdviceContent, String UserID,
			ConnCallback callback) {
		String  name = "GetZCouponInfo";
		JSONObject param = new JSONObject();

		try {
			param.put("AdviceContent", AdviceContent);
			param.put("UserID", UserID);
		} catch (Exception e) {
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

	/**
	 * 处理反馈意见（单个或批量）
	 * 
	 * @return
	 */
	public void processAdvices(String AdviceID, String ProcessNote,
			ConnCallback callback) {
		String  name = "ProcessAdvices";
		JSONObject param = new JSONObject();

		try {
			param.put("AdviceID", AdviceID);
			param.put("ProcessNote", ProcessNote);
		} catch (Exception e) {
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

	/**
	 * 删除反馈意见（单个或批量）
	 * 
	 * @return
	 */
	public void deleteAdvices(String AdviceID, ConnCallback callback) {
		String   name = "DeleteAdvices";
		JSONObject param = new JSONObject();

		try {
			param.put("AdviceId", AdviceID);
		} catch (Exception e) {
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

	/**
	 * 根据条件查找意见反馈
	 * 
	 * @return
	 */
	public void searchAdvices(String Start, String Count, String Field,
			String Value, String Junction, ConnCallback callback) {
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
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}
}
