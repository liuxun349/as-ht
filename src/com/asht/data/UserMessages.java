package com.asht.data;

import org.json.JSONException;
import org.json.JSONObject;

public class UserMessages {
	/**
	 * 根据条件查找意见反馈
	 * 
	 * @return
	 */
	public JSONObject searchMessages(String Start, String Count,
			String Field, String Value, String Junction) {
		String name = "SearchMessages";

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
		JSONObject result = NetworkConnection.connection(name, param);
		return result;
	}
}
