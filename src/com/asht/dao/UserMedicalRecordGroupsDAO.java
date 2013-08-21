/**
 * 病例
 */
package com.asht.dao;

import org.json.JSONObject;

public class UserMedicalRecordGroupsDAO {
	/**
	 * 获取用户的所有病历组
	 * @param userId
	 * @return
	 */
	public JSONObject getMedicalRecordGroupsByUserId(String userId){
		String name = "GetMedicalRecordGroupsByUserId";
		JSONObject param = new JSONObject();
		try {
			param.put("UserId", userId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		JSONObject result = NetworkConnectionDAO.connection(name, param);
		return result;
	}
	/**
	 * 删除用户病历组
	 * @param medicalRecordGroupId
	 * @return
	 */
	public JSONObject deleteMedicalRecordGroups(String medicalRecordGroupId){
		String name = "DeleteMedicalRecordGroups";
		JSONObject param = new JSONObject();
		try {
			param.put("MedicalRecordGroupId", medicalRecordGroupId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		JSONObject result = NetworkConnectionDAO.connection(name, param);
		return result;
	}
	/**
	 * 上传病历（单个或批量）
	 * @param MedicalRecordGroupId
	 * @return
	 */
	public JSONObject uploadMedicalRecordItemsToGroup(String MedicalRecordGroupId){
		String name = "UploadMedicalRecordItemsToGroup";
		JSONObject param = new JSONObject();
		try {
			param.put("MedicalRecordGroupId", MedicalRecordGroupId);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		JSONObject result = NetworkConnectionDAO.connection(name, param);
		return result;
	}
	/**
	 * 删除用户病历图片
	 * @param medicalRecordItemId
	 * @return
	 */
	public JSONObject deleteMedicalRecordItems(String medicalRecordItemId){
		String name = "DeleteMedicalRecordItems";
		JSONObject param = new JSONObject();
		try {
			param.put("MedicalRecordItemId", medicalRecordItemId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		JSONObject result = NetworkConnectionDAO.connection(name, param);
		return result;
	}
	/**
	 * 获取病历组中的病历
	 * @param medicalGroupId
	 * @return
	 */
	public JSONObject getMedicalRecordItemsByGroup(String medicalGroupId){
		String name = "GetMedicalRecordItemsByGroup";
		JSONObject param = new JSONObject();
		try {
			param.put("MedicalGroupId", medicalGroupId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		JSONObject result = NetworkConnectionDAO.connection(name, param);
		return result;
	}
	
}
