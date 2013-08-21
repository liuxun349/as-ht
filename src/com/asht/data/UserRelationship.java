package com.asht.data;

import org.json.JSONException;
import org.json.JSONObject;

public class UserRelationship {

	/**
	 * 推荐病人或医生处理
	 */
	public JSONObject recommendUser(String presenterUserID,
			String userPhoneNumber, String name, String userType,
			String certificateType, String certificateNo) {
		String mname = "recommendUser";
		JSONObject param = new JSONObject();

		try {
			param.put("PresenterUserID", presenterUserID);
			param.put("UserPhoneNumber", userPhoneNumber);
			param.put("Name", name);
			param.put("UserType", userType);
			param.put("CertificateType", certificateType);
			param.put("CertificateNo", certificateNo);
		} catch (Exception e) {
		}
		JSONObject result = NetworkConnection.connection(mname, param);
		return result;
	}

	/**
	 * 推荐病人或医生处理
	 */
	public JSONObject auditRecommendations(String id, String ReferenceUserID,
			String RecommendedUserID, String vcharAuditUserID, String AuditNote) {
		String mname = "AuditRecommendations";
		JSONObject param = new JSONObject();

		try {
			param.put("id", id);
			param.put("ReferenceUserID", ReferenceUserID);
			param.put("RecommendedUserID", RecommendedUserID);
			param.put("vcharAuditUserID", vcharAuditUserID);
			param.put("AuditNote", AuditNote);
		} catch (Exception e) {
		}
		JSONObject result = NetworkConnection.connection(mname, param);
		return result;
	}

	/**
	 * 搜索推荐信息
	 * 
	 * @return
	 */
	public JSONObject searchRecommendations(String Start, String Count,
			String Field, String Value, String Junction) {
		String name = "SearchRecommendations";

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
