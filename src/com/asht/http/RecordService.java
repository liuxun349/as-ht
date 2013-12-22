package com.asht.http;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.asht.model.Resume;
import com.asht.model.UserInfo;

public class RecordService {
	private String method ;
	private HttpClient httpClient = new HttpClient();
	private JSONObject json;
	
	/**
	 * 获取用户自己的病例组（分页查询，10个病例）
	 * @param user
	 * @param getRecent 是否是获取最近的10个病例
	 * @param beforeCurrentTime 当前时间之前的10个病例
	 * @return
	 */
	public AshtResponse getRecordGroup(UserInfo user,boolean getRecent,Date beforeCurrentTime){
		method = "getMedicalRecordGroupsByOwner";
		json = new JSONObject();
		json.put("userPhoneNo", user.getUserId());
		json.put("getRecent", getRecent);
		json.put("beforeCurrentTiem", beforeCurrentTime);
		return get(method, json);
	} 
	/**
	 * 创建新的病例组
	 * @param user
	 * @param newGroupName 病例组名称
	 * @return
	 */
	public AshtResponse addRecordGroup(UserInfo user,String newGroupName){
		method = "addMedicalRecordGroup";
		json = new JSONObject();
		json.put("userPhoneNo", user.getUserId());
		json.put("MedicalRecordGroupName", newGroupName);
		return get(method, json);
	}
	/**
	 * 删除病例组（单个）
	 * @param user
 	 * @param groupId 需要被删除的病例组id
	 * @return
	 */
	public AshtResponse deleteRecordGroup(UserInfo user,String groupId){
		method = "deleteMedicalRecordGroups";
		json = new JSONObject();
		json.put("userPhoneNo", user.getUserId());
		json.put("MedicalRecordGroupID", groupId);
		return get(method, json);
	}
	/**
	 * 上传病例（单个）
	 * @param user
	 * @param groupId
	 * @param resume
	 * @return
	 */
	public AshtResponse uploadCaseToGroup(UserInfo user,String groupId,Resume resume){
		method = "uploadMedicalRecordItemsToGroup";
		json = new JSONObject();
		json.put("userPhoneNo", user.getUserId());
		json.put("MedicalRecordGroupID", groupId);
		json.put("MedicalRecordItemFile", resume);
		return get(method, json);
	}
	/**
	 * 删除病例（单个）
	 * @param user
	 * @param groupId 病例组id
	 * @param caseName 病例名称
	 * @return
	 */
	public AshtResponse deleteCaseFromGroup(UserInfo user,String groupId,String caseName){
		method = "deleteMedicalRecordItems";
		json = new JSONObject();
		json.put("userPhone", user.getUserId());
		json.put("MedicalRecordGroupID", groupId);
		json.put("MedicalRecordItemFileName", caseName);
		return get(method, json);
	}
	/**
	 * 获取病例组所有病例图片名称
	 * @param user
	 * @param groupId
	 * @return
	 */
	public AshtResponse getAllCaseFromGroup(UserInfo user,String groupId){
		method = "getMedicalRecordItemsBigCaseByGroup";
		json = new JSONObject();
		json.put("userPhoneNo", user.getUserId());
		json.put("MedicalRecordGroupID", groupId);
		return get(method, json);
	}
	/**
	 * 获取病例组中指定的病例
	 * @param user
	 * @param groupId 病例组id
	 * @param type	病例图片的类型（0 缩略图，1 原图）
	 * @return
	 */
	public AshtResponse getCaseImageFromGroup(UserInfo user,String groupId,int type){
		method = "getMedicalRecordItemsSmallCaseByGroup";
		json = new JSONObject();
		json.put("userPhoneNo", user.getUserId());
		json.put("medicalRecordGroupID", groupId);
		json.put("type", type);
		return get(method, json);
	}
	private AshtResponse get(String method, JSONObject json) {
		return httpClient.get(method, json.toJSONString());
	}
}
