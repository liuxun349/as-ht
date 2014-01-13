/**
 * 推荐人信息类
 */
package com.asht.model;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asht.AsHtException;
import com.asht.http.AshtResponse;

public class Recommend extends AshtResponse {
	/**
	 * 推荐人手机号
	 */
	private String recommendPhoneNo;
	/**
	 * 推荐人真实姓名
	 */
	private String recommendtrueName;
	/**
	 * 推荐人证件类型
	 */
	private String recommendCertificateTypeId;
	/**
	 * 推荐人证件号
	 */
	private String recommendCertificateId;
	/**
	 * 推荐人邮箱
	 */
	private String recommendeMail;
	/**
	 * 推荐时间
	 */
	private String RecommendDateTime;
	/**
	 * 审核状态
	 */
	private String RecommendState;
	/**
	 * 审核时间
	 */
	private String examineDateTime;

	public Recommend() {
		// TODO Auto-generated constructor stub
	}

	public Recommend(String recommendPhoneNo, String recommendtrueName,
			String recommendCertificateTypeId, String recommendCertificateId,
			String recommendeMail ) {
		this.recommendCertificateId = recommendCertificateId;
		this.recommendCertificateTypeId = recommendCertificateTypeId;
		this.recommendeMail = recommendeMail;
		this.recommendPhoneNo = recommendPhoneNo;
		this.recommendtrueName = recommendtrueName;
	}

	public Recommend(AshtResponse rs) {
		// TODO Auto-generated constructor stub
		super(rs);
		Recommend recommend = JSON.parseObject(((JSON) result).toJSONString(),
				Recommend.class);
		this.recommendCertificateId = recommend.recommendCertificateId;
		this.recommendCertificateTypeId = recommend.recommendCertificateTypeId;
		this.recommendeMail = recommend.recommendeMail;
		this.recommendPhoneNo = recommend.recommendPhoneNo;
		this.recommendtrueName = recommend.recommendtrueName;
	}

	public static List<Recommend> getRecommends(AshtResponse rs)
			throws AsHtException {
		if (!rs.success) {
			throw new AsHtException(rs.message);
		} else if (rs.result == null) {
			return null;
		}
		return JSON.parseArray(((JSON) rs.result).toJSONString(),
				Recommend.class);
	}

	public String getRecommendPhoneNo() {
		return recommendPhoneNo;
	}

	public void setRecommendPhoneNo(String recommendPhoneNo) {
		this.recommendPhoneNo = recommendPhoneNo;
	}

	public String getRecommendtrueName() {
		return recommendtrueName;
	}

	public void setRecommendtrueName(String recommendtrueName) {
		this.recommendtrueName = recommendtrueName;
	}

	public String getRecommendCertificateTypeId() {
		return recommendCertificateTypeId;
	}

	public void setRecommendCertificateTypeId(String recommendCertificateTypeId) {
		this.recommendCertificateTypeId = recommendCertificateTypeId;
	}

	public String getRecommendCertificateId() {
		return recommendCertificateId;
	}

	public void setRecommendCertificateId(String recommendCertificateId) {
		this.recommendCertificateId = recommendCertificateId;
	}

	public String getRecommendeMail() {
		return recommendeMail;
	}

	public void setRecommendeMail(String recommendeMail) {
		this.recommendeMail = recommendeMail;
	}

	public String getRecommendDateTime() {
		return RecommendDateTime;
	}

	public void setRecommendDateTime(String recommendDateTime) {
		RecommendDateTime = recommendDateTime;
	}

	public String getRecommendState() {
		return RecommendState;
	}

	public void setRecommendState(String recommendState) {
		RecommendState = recommendState;
	}

	public String getExamineDateTime() {
		return examineDateTime;
	}

	public void setExamineDateTime(String examineDateTime) {
		this.examineDateTime = examineDateTime;
	}

	// @Override
	public JSONObject toJson() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		json.put("recommendPhoneNo", recommendPhoneNo);
		json.put("recommendtrueName", recommendtrueName);
		json.put("recommendCertificateTypeId", recommendCertificateTypeId);
		json.put("recommendCertificateId", recommendCertificateId);
		json.put("recommendeMail", recommendeMail);
		return json;
	}

}
