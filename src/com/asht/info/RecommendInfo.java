package com.asht.info;


public class RecommendInfo {

	private int _id;// 数据库主健id
	private int recommendId;// 该条信息ID
	private int userId;// 用户ID
	private String recommendedName;// 姓名
	private long identity;// 证件号码
	private int certificateId;// 身份（0 患者，1医生）
	private String recommendedTime;// 推荐时间
	private int auditState;// 审核状态(0,审核过了，1没有过)
	private String auditTime;// 审核时间
	private String auditNote;// 审核备注
	private int isClick;// 用于Adapter的选中状态（0选中，1没有选中）（数据库保存没实际意义）

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(int recommendId) {
		this.recommendId = recommendId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getRecommendedName() {
		return recommendedName;
	}

	public void setRecommendedName(String recommendedName) {
		this.recommendedName = recommendedName;
	}

	public long getIdentity() {
		return identity;
	}

	public void setIdentity(long identity) {
		this.identity = identity;
	}

	public int getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(int certificateId) {
		this.certificateId = certificateId;
	}

	public String getRecommendedTime() {
		return recommendedTime;
	}

	public void setRecommendedTime(String recommendedTime) {
		this.recommendedTime = recommendedTime;
	}

	public int getAuditState() {
		return auditState;
	}

	public void setAuditState(int auditState) {
		this.auditState = auditState;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getAuditNote() {
		return auditNote;
	}

	public void setAuditNote(String auditNote) {
		this.auditNote = auditNote;
	}

	public int getIsClick() {
		return isClick;
	}

	public void setIsClick(int isClick) {
		this.isClick = isClick;
	}

}
