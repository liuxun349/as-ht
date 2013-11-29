/**
 * 病例
 */
package com.asht.http;

import org.json.JSONObject;

import com.asht.utl.ConnCallback;

public class UserMedicalRecordGroupsDAO {
	private String URL_NAME = "";
	/**
	 * 获取用户的所有病历组
	 * 
	 * @param userId
	 * @return
	 */
	public void getMedicalRecordGroupsByUserId(String userId,
			ConnCallback callback) {
		String name = "GetMedicalRecordGroupsByUserId";
		JSONObject param = new JSONObject();
		try {
			param.put("UserId", userId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

	/**
	 * 删除用户病历组
	 * 
	 * @param medicalRecordGroupId
	 * @return
	 */
	public void deleteMedicalRecordGroups(String medicalRecordGroupId,
			ConnCallback callback) {
		String name = "DeleteMedicalRecordGroups";
		JSONObject param = new JSONObject();
		try {
			param.put("MedicalRecordGroupId", medicalRecordGroupId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

	/**
	 * 上传病历（单个或批量）
	 * 
	 * @param MedicalRecordGroupId
	 * @return
	 */
	public void uploadMedicalRecordItemsToGroup(
			String MedicalRecordGroupId, ConnCallback callback) {
		String name = "UploadMedicalRecordItemsToGroup";
		JSONObject param = new JSONObject();
		try {
			param.put("MedicalRecordGroupId", MedicalRecordGroupId);

		} catch (Exception e) {
			// TODO: handle exception
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

	/**
	 * 删除用户病历图片
	 * 
	 * @param medicalRecordItemId
	 * @return
	 */
	public void deleteMedicalRecordItems(String medicalRecordItemId,
			ConnCallback callback) {
		String name = "DeleteMedicalRecordItems";
		JSONObject param = new JSONObject();
		try {
			param.put("MedicalRecordItemId", medicalRecordItemId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

	/**
	 * 获取病历组中的病历
	 * 
	 * @param medicalGroupId
	 * @return
	 */
	public void getMedicalRecordItemsByGroup(String medicalGroupId,
			ConnCallback callback) {
		String name = "GetMedicalRecordItemsByGroup";
		JSONObject param = new JSONObject();
		try {
			param.put("MedicalGroupId", medicalGroupId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

}
