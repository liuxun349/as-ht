package com.asht;

import java.util.Date;
import java.util.List;

import android.speech.RecognitionService;

import com.alibaba.fastjson.JSONObject;
import com.asht.controller.SystemService;
import com.asht.http.AshtResponse;
import com.asht.http.HttpClient;
import com.asht.http.RecordService;
import com.asht.http.UserService;
import com.asht.model.Record;
import com.asht.model.Resume;
import com.asht.model.UserInfo;

public class AsHt {
	HttpClient httpClient = new HttpClient();
	String method;
	JSONObject json;
	private SystemService systemService = new SystemService();
	private RecordService recordService = new RecordService();
	private UserService userService = new UserService();
	/**
	 * 登录
	 * 
	 * @param name
	 * @param passwd
	 * @return
	 */
	public UserInfo login(String name, String passwd) {
		return new UserInfo(systemService.login(name, passwd));
	}

	/**
	 * 向手机发送验证码
	 * 
	 * @return
	 */
	public AshtResponse sendVerificationCode() {

		return systemService.sendVerificationCode();
	}

	/**
	 * 验证手机与手机验证码
	 * 
	 * @param method
	 * @param json
	 * @return
	 */
	public AshtResponse checkVerificationCode(String phoneNo, String checkNo) {
		return systemService.checkVerificationCode(phoneNo, checkNo);
	}

	/**
	 * 注册
	 * 
	 * @return
	 */
	public AshtResponse getPass(UserInfo userInfo) {
		return systemService.regist(userInfo);
	}

	/**
	 * 发送密码到邮箱
	 * 
	 * @param userPhoneNo
	 * @return
	 */
	public AshtResponse sendPasswdToEmai(String userPhoneNo) {
			return systemService.sendPasswdToEmai(userPhoneNo);
	}

	/**
	 * 修改个人资料
	 * 
	 * @return
	 */
	public AshtResponse modifyUserInfo() {
		method = "ModifyUserInfo";
		return get(method, json);
	}
	/**
	 * 获取用户自己的病例组（分页查询，10个病例）
	 * @param user
	 * @param getRecent 是否是获取最近的10个病例
	 * @param beforeCurrentTime 当前时间之前的10个病例
	 * @return
	 */
	public List<Record> getRecordGroup(UserInfo user,boolean getRecent,Date beforeCurrentTime){
		return Record.getRecords(recordService.getRecordGroup(user, getRecent, beforeCurrentTime));
	} 
	/**
	 * 创建新的病例组
	 * @param user
	 * @param newGroupName 病例组名称
	 * @return
	 */
	public AshtResponse addRecordGroup(UserInfo user,String newGroupName){
		return recordService.addRecordGroup(user, newGroupName);
	}
	/**
	 * 删除病例组（单个）
	 * @param user
 	 * @param groupId 需要被删除的病例组id
	 * @return
	 */
	public AshtResponse deleteRecordGroup(UserInfo user,String groupId){
		return recordService.deleteRecordGroup(user, groupId);
	}
	/**
	 * 上传病例（单个）
	 * @param user
	 * @param groupId
	 * @param resume
	 * @return
	 */
	public AshtResponse uploadCaseToGroup(UserInfo user,String groupId,Resume resume){
		return recordService.uploadCaseToGroup(user, groupId, resume);
	}
	/**
	 * 删除病例（单个）
	 * @param user
	 * @param groupId 病例组id
	 * @param caseName 病例名称
	 * @return
	 */
	public AshtResponse deleteCaseFromGroup(UserInfo user,String groupId,String caseName){
		return recordService.deleteCaseFromGroup(user, groupId, caseName);
	}
	/**
	 * 获取病例组所有病例图片名称
	 * @param user
	 * @param groupId
	 * @return
	 */
	public AshtResponse getAllCaseFromGroup(UserInfo user,String groupId){
		return recordService.getAllCaseFromGroup(user, groupId);
	}
	/**
	 * 获取病例组中指定的病例
	 * @param user
	 * @param groupId 病例组id
	 * @param type	病例图片的类型（0 缩略图，1 原图）
	 * @return
	 */
	public Resume getCaseImageFromGroup(UserInfo user,String groupId,int type){
		return new Resume(recordService.getCaseImageFromGroup(user, groupId, type));
	}
}
