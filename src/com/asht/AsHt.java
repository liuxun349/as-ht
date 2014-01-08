package com.asht;

import java.util.Date;
import java.util.List;

import android.accounts.Account;
import android.speech.RecognitionService;

import com.alibaba.fastjson.JSONObject;
import com.asht.controller.SystemService;
import com.asht.http.AccountService;
import com.asht.http.AshtResponse;
import com.asht.http.HttpClient;
import com.asht.http.RecordService;
import com.asht.http.UserService;
import com.asht.model.Record;
import com.asht.model.Resume;
import com.asht.model.UserInfo;

public class AsHt {
	String method;
	JSONObject json;
	private SystemService systemService = new SystemService();
	private RecordService recordService = new RecordService();
	private UserService userService = new UserService();
	private AccountService accountService = new AccountService();

	/**
	 * 登录
	 * 
	 * @param name
	 * @param passwd
	 * @return
	 * @throws AsHtException
	 */
	public UserInfo login(String name, String passwd) throws AsHtException {
		return new UserInfo(systemService.login(name, passwd));
	}

	/**
	 * 向手机发送验证码
	 * 
	 * @return
	 * @throws AsHtException
	 */
	public AshtResponse sendVerificationCode() throws AsHtException {

		return systemService.sendVerificationCode();
	}

	/**
	 * 验证手机与手机验证码
	 * 
	 * @param method
	 * @param json
	 * @return
	 * @throws AsHtException
	 */
	public AshtResponse checkVerificationCode(String phoneNo, String checkNo)
			throws AsHtException {
		return systemService.checkVerificationCode(phoneNo, checkNo);
	}

	/**
	 * 注册
	 * 
	 * @return
	 * @throws AsHtException
	 */
	public AshtResponse getPass(UserInfo userInfo) throws AsHtException {
		return systemService.regist(userInfo);
	}

	/**
	 * 发送密码到邮箱
	 * 
	 * @param userPhoneNo
	 * @return
	 * @throws AsHtException
	 */
	public AshtResponse sendPasswdToEmai(String userPhoneNo)
			throws AsHtException {
		return systemService.sendPasswdToEmai(userPhoneNo);
	}

	/**
	 * 获取用户自己的病例组（分页查询，10个病例）
	 * 
	 * @param user
	 * @param getRecent
	 *            是否是获取最近的10个病例
	 * @param beforeCurrentTime
	 *            当前时间之前的10个病例
	 * @return
	 * @throws AsHtException
	 */
	public List<Record> getRecordGroup(UserInfo user, boolean getRecent,
			String beforeCurrentTime) throws AsHtException {
		AshtResponse asht = recordService.getRecordGroup(user, getRecent,
				beforeCurrentTime);
		return Record.getRecords(asht);
	}

	/**
	 * 创建新的病例组
	 * 
	 * @param user
	 * @param newGroupName
	 *            病例组名称
	 * @return
	 * @throws AsHtException
	 */
	public Record addRecordGroup(UserInfo user, String newGroupName)
			throws AsHtException {
		Record record = new Record();
		record.medicalRecordGroupName = newGroupName;
		String str  =  recordService.addRecordGroup(user, newGroupName).result+""; 
		record.medicalRecordGroupID = str;
		return record;
	}

	/**
	 * 删除病例组（单个）
	 * 
	 * @param user
	 * @param groupId
	 *            需要被删除的病例组id
	 * @return
	 * @throws AsHtException
	 */
	public AshtResponse deleteRecordGroup(UserInfo user, String groupId)
			throws AsHtException {
		return recordService.deleteRecordGroup(user, groupId);
	}

	/**
	 * 上传病例（单个）
	 * 
	 * @param user
	 * @param groupId
	 * @param resume
	 * @return
	 * @throws AsHtException
	 */
	public AshtResponse uploadCaseToGroup(UserInfo user, String groupId,
			Resume resume) throws AsHtException {
		return recordService.uploadCaseToGroup(user, groupId, resume);
	}

	/**
	 * 删除病例（单个）
	 * 
	 * @param user
	 * @param groupId
	 *            病例组id
	 * @param caseName
	 *            病例名称
	 * @return
	 * @throws AsHtException
	 */
	public AshtResponse deleteCaseFromGroup(UserInfo user, String groupId,
			String caseName) throws AsHtException {
		return recordService.deleteCaseFromGroup(user, groupId, caseName);
	}

	/**
	 * 获取病例组所有病例图片地址
	 * 
	 * @param user
	 * @param groupId
	 * @return
	 * @throws AsHtException
	 */
	public List<Resume> getAllCaseFromGroup(UserInfo user, String groupId)
			throws AsHtException {
		return Resume.getResumes(recordService.getAllCaseFromGroup(user,
				groupId));
	}

	/**
	 * 获取病例组中指定的病例
	 * 
	 * @param user
	 * @param groupId
	 *            病例组id
	 * @param type
	 *            病例图片的类型（0 缩略图，1 原图）
	 * @return
	 * @throws AsHtException
	 */
	public Resume getCaseImageFromGroup(UserInfo user, String groupId, int type)
			throws AsHtException {
		return new Resume(recordService.getCaseImageFromGroup(user, groupId,
				type));
	}
}
