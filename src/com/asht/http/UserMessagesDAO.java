package com.asht.http;

import org.json.JSONException;
import org.json.JSONObject;

import com.asht.utl.ConnCallback;

public class UserMessagesDAO {
	private String URL_NAME = "";
	/**
	 * 根据条件查找意见反馈
	 * 
	 * @return
	 */
	public void searchMessages(String Start, String Count, String Field,
			String Value, String Junction, ConnCallback callback) {
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
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}
}
