package com.asht;

import java.util.List;


import com.alibaba.fastjson.JSONObject;
import com.asht.controller.SystemService;
import com.asht.http.AccountService;
import com.asht.http.AshtResponse;
import com.asht.http.RecordService;
import com.asht.http.UserService;
import com.asht.model.Advice;
import com.asht.model.Message;
import com.asht.model.Recommend;
import com.asht.model.Record;
import com.asht.model.Resume;
import com.asht.model.UserInfo;
import com.asht.model.ZCoupon;

public class AsHt {
	private static AsHt mInstance;
	private SystemService systemService = new SystemService();
	private RecordService recordService = new RecordService();
	private UserService userService = new UserService();
	private AccountService accountService = new AccountService();
	
	public static void initalize(){
		mInstance = new AsHt();
	}
	public static AsHt getInstance(){
		return mInstance;
	}
//--------------------------------SystemService-------------------------------
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
	 * 注册
	 * 
	 * @param userInfo
	 * @return
	 * @throws AsHtException 
	 */
	public boolean regist(UserInfo userInfo) throws AsHtException {
<<<<<<< HEAD
		return response(systemService.regist(userInfo));
=======
		AshtResponse rs = systemService.regist(userInfo);
		if( rs.success && rs.result != null){
			return (Boolean) rs.result;
		}
		return false;
>>>>>>> 75b8d38929e96f51c7affae9e408f4402fe8289f
	}
	/**
	 * 向手机发送验证码
	 * 
	 * @return
	 * @throws AsHtException
	 */
	public boolean sendVerificationCode() throws AsHtException {
		return response(systemService.sendVerificationCode());
	}

	/**
	 * 验证手机与手机验证码
	 * 
	 * @param method
	 * @param json
	 * @return
	 * @throws AsHtException
	 */
	public boolean checkVerificationCode(String phoneNo, String checkNo)
			throws AsHtException {
		return response(systemService.checkVerificationCode(phoneNo, checkNo));
	}

	/**
	 * 注册
	 * 
	 * @return
	 * @throws AsHtException
	 */
	public boolean getPass(UserInfo userInfo) throws AsHtException {
		return response(systemService.regist(userInfo));
	}

	/**
	 * 发送密码到邮箱
	 * 
	 * @param userPhoneNo
	 * @return
	 * @throws AsHtException
	 */
	public boolean sendPasswdToEmai(String userPhoneNo)
			throws AsHtException {
		return response(systemService.sendPasswdToEmai(userPhoneNo));
	}
	
//----------------------------------UserService--------------------------------------------

	/**
	 * 修改个人资料
	 * @param userInfo
	 * @return
	 * @throws AsHtException 
	 */
	public boolean modifyInfo(UserInfo userInfo) throws AsHtException {
		return response(userService.modifyInfo(userInfo));
	}
	/**
	 * 修改登录密码
	 * @param oldpwd
	 * @param newpwd
	 * @return
	 * @throws AsHtException 
	 */
	public boolean modifyLoginPasswd(String oldpwd, String newpwd) throws AsHtException {
		return response(userService.modifyLoginPasswd(oldpwd, newpwd));
	}
	/**
	 * 修改支付密码
	 * @param userInfo
	 * @param checkNo
	 * @param usercertificate
	 * @param newPayPasswd
	 * @return
	 * @throws AsHtException  
	 */
	public boolean modifyPayPasswd(UserInfo userInfo,String checkNo,String usercertificate,String newPayPasswd) throws AsHtException{
		return response(userService.modifyPayPasswd(userInfo, checkNo, usercertificate, newPayPasswd));
	}
	/**
	 * 修改密报问题
	 * @param userInfo
	 * @param questionNo
	 * @param answer
	 * @return
	 * @throws AsHtException 
	 */
	public boolean modifyAnswerOfSecurityQuestion(UserInfo userInfo,int questionNo,String answer) throws AsHtException{
		return response(userService.modifyAnswerOfSecurityQuestion(userInfo, questionNo, answer));
	}
	/**
	 * 判断用户是否设置密保问题
	 * @param userInfo
	 * @return
	 * @throws AsHtException 
	 */
	public boolean isUserSetPasswdProtected(UserInfo userInfo) throws AsHtException{
		return ((JSONObject)responseObject(userService.isUserSetPasswdProtected(userInfo))).getBooleanValue("isPasswordProtection");
	}
	/**
	 * 检查用户输入密保答案是否与设置相同
	 * @param userInfo
	 * @param questionNo
	 * @param questionContent
	 * @return
	 * @throws AsHtException 
	 */
	public boolean checkPasswdAnswerIsRight(UserInfo userInfo,int questionNo,String questionContent) throws AsHtException{
		return ((JSONObject)responseObject(userService.checkPasswdAnswerIsRight(userInfo, questionNo, questionContent))).getBooleanValue("isPasswordProtectionValid");
	}
	/**
	 * 修改手机号
	 * @param userInfo
	 * @param checkNo
	 * @param payPasswd
	 * @param newUserPhoneNo
	 * @return
	 * @throws AsHtException 
	 */
	public boolean modifyMobileNumber(UserInfo userInfo,String checkNo,String payPasswd,String newUserPhoneNo) throws AsHtException{
		return response(userService.modifyMobileNumber(userInfo, checkNo, payPasswd, newUserPhoneNo));
		
	}	
//------------------------------AccountService-----------------------------------------
	/**
	 * 查询贡献值
	 * @param userInfo
	 * @return
	 * @throws AsHtException 
	 */
	public long searchContributions(UserInfo userInfo) throws AsHtException{
		return ((JSONObject)responseObject(accountService.searchContributions(userInfo))).getLongValue("contributions");
	}
	
	/**
	 * 查询Z币
	 * @param userInfo
	 * @return
	 * @throws AsHtException 
	 */
	public long getZGold(UserInfo userInfo) throws AsHtException{
		return (long)((JSONObject)responseObject(accountService.getZGold(userInfo))).getLongValue("zGold");
	}
	
	/**
	 * 兑换Z券
	 * @param userInfo
	 * @param exchangeValue
	 * @return
	 * @throws AsHtException 
	 */
	public ZCoupon exchangeZCoupon(UserInfo userInfo,int exchangeValue) throws AsHtException{
		return new ZCoupon(accountService.exchangeZCoupon(userInfo, exchangeValue));
	}
	
	/**
	 * 查看Z券信息
	 * @param userInfo
	 * @return
	 * @throws AsHtException 
	 */
	public List<ZCoupon> getZCouponsByOwner(UserInfo userInfo) throws AsHtException{
		return ZCoupon.getZCoupons(accountService.getZCouponsByOwner(userInfo));
	}
	/**
	 * 添加意见反馈
	 * @param userInfo
	 * @param advice
	 * @return
	 * @throws AsHtException 
	 */
	public boolean addAdvice(UserInfo userInfo,String advice) throws AsHtException{
		return response(accountService.addAdvice(userInfo, advice));
	}
	/**
	 * 搜索反馈意见
	 * @param userInfo
	 * @return
	 * @throws AsHtException 
	 */
	public List<Advice> searchAdvices(UserInfo userInfo) throws AsHtException{
		return Advice.getUserAdvices(accountService.searchAdvices(userInfo));
	}
	/**
	 * 搜索消息
	 * @param userInfo
	 * @return
	 * @throws AsHtException 
	 */
	public List<Message> searchMessages(UserInfo userInfo) throws AsHtException{
		return Message.getMessages(accountService.searchMessages(userInfo));
	}
	/**
	 * 推荐病人
	 * @param userInfo
	 * @return
	 * @throws AsHtException 
	 */
	public boolean recommendPatient(UserInfo userInfo,Recommend recommend) throws AsHtException{
		return response(accountService.recommendPatient(userInfo, recommend));
	}
	/**
	 * 获取所有自己的推荐信息
	 * @param userInfo
	 * @return
	 * @throws AsHtException 
	 */
	public List<Recommend> getRecommendationsByPresenter(UserInfo userInfo) throws AsHtException{
		return Recommend.getRecommends(accountService.getRecommendationsByPresenter(userInfo));
	}

//---------------------------------RecordService---------------------------------
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
		return Record.getRecords(recordService.getRecordGroup(user, getRecent,
				beforeCurrentTime));
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
		record.medicalRecordGroupID = ""+ (recordService.addRecordGroup(user, newGroupName).result); 
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
	public boolean deleteRecordGroup(UserInfo user, String groupId)
			throws AsHtException {
		return response(recordService.deleteRecordGroup(user, groupId));
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
	public boolean uploadCaseToGroup(UserInfo user, String groupId,
			Resume resume) throws AsHtException {
		return response(recordService.uploadCaseToGroup(user, groupId, resume));
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
	public boolean deleteCaseFromGroup(UserInfo user, String groupId,
			String caseId) throws AsHtException {
		return response(recordService.deleteCaseFromGroup(user, groupId, caseId));
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
	public List<Resume> getCaseImageFromGroup(UserInfo user, String groupId, int type)
			throws AsHtException {
		return Resume.getResumes(recordService.getCaseImageFromGroup(user, groupId,
				type));
	} 
	/**
	 * 处理返回信息（针对true ，false）
	 * @param rs 服务器返回
	 * @return
	 * @throws AsHtException
	 */
	private boolean response(AshtResponse rs) throws AsHtException{
		if( rs.success ){
			return true;
		}else {
			throw new AsHtException(rs.message);
		}
	}
	private Object responseObject(AshtResponse rs) throws AsHtException {
		if(rs.success && rs.result != null){
			return rs.result;
		}else{
			throw new AsHtException(rs.message);
		}
	}
		
}
