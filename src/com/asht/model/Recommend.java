/**
 * 推荐人信息类
 */
package com.asht.model;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asht.AsHtException;
import com.asht.http.AshtResponse;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

@Table(name = "Recommend")
@net.tsz.afinal.annotation.sqlite.Table(name = "Recommend")
public class Recommend extends AshtResponse {

	@Id
	@net.tsz.afinal.annotation.sqlite.Id
	private int _id;// 数据库主健id
	// private int isClick;// 用于Adapter的选中状态（0选中，1没有选中）（数据库保存没实际意义）

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
	private String recommendDateTime;
	/**
	 * 审核状态 审核状态(0,审核过了，1没有过)
	 */
	private String recommendState;
	/**
	 * 审核时间
	 */
	private String examineDateTime;

	/**
	 * 推荐人类型
	 */
	private String recommendRoleId;

	public Recommend() {
	}

	public Recommend(String recommendPhoneNo, String recommendtrueName,
			String recommendCertificateTypeId, String recommendCertificateId,
			String recommendeMail, String recommendRoleId) {
		this.recommendPhoneNo = recommendPhoneNo;
		this.recommendtrueName = recommendtrueName;
		this.recommendCertificateTypeId = recommendCertificateTypeId;
		this.recommendCertificateId = recommendCertificateId;
		this.recommendeMail = recommendeMail;
		this.recommendRoleId = recommendRoleId;
	}

	// public Recommend(String recommendPhoneNo, String recommendtrueName,
	// String recommendCertificateTypeId, String recommendCertificateId,
	// String recommendeMail) {
	// this.recommendCertificateId = recommendCertificateId;
	// this.recommendCertificateTypeId = recommendCertificateTypeId;
	// this.recommendeMail = recommendeMail;
	// this.recommendPhoneNo = recommendPhoneNo;
	// this.recommendtrueName = recommendtrueName;
	// }

	// public Recommend(AshtResponse rs) {
	// super(rs);
	// Recommend recommend = JSON.parseObject(((JSON) result).toJSONString(),
	// Recommend.class);
	// this.recommendCertificateId = recommend.recommendCertificateId;
	// this.recommendCertificateTypeId = recommend.recommendCertificateTypeId;
	// this.recommendeMail = recommend.recommendeMail;
	// this.recommendPhoneNo = recommend.recommendPhoneNo;
	// this.recommendtrueName = recommend.recommendtrueName;
	// }

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

	/**
	 * 推荐人手机号
	 */
	public String getRecommendPhoneNo() {
		return recommendPhoneNo;
	}

	/**
	 * 推荐人手机号
	 */
	public void setRecommendPhoneNo(String recommendPhoneNo) {
		this.recommendPhoneNo = recommendPhoneNo;
	}

	/**
	 * 真实姓名
	 */
	public String getRecommendtrueName() {
		return recommendtrueName;
	}

	/**
	 * 设置真实姓名
	 */
	public void setRecommendtrueName(String recommendtrueName) {
		this.recommendtrueName = recommendtrueName;
	}

	/**
	 * 推荐人证件类型
	 */
	public String getRecommendCertificateTypeId() {
		return recommendCertificateTypeId;
	}

	/**
	 * 推荐人证件类型
	 */
	public void setRecommendCertificateTypeId(String recommendCertificateTypeId) {
		this.recommendCertificateTypeId = recommendCertificateTypeId;
	}

	/**
	 * 推荐人证件类型
	 */
	public String getRecommendCertificateId() {
		return recommendCertificateId;
	}

	/**
	 * 推荐人证件号
	 */
	public void setRecommendCertificateId(String recommendCertificateId) {
		this.recommendCertificateId = recommendCertificateId;
	}

	/**
	 * 推荐人邮箱
	 */
	public String getRecommendeMail() {
		return recommendeMail;
	}

	/**
	 * 推荐人邮箱
	 */
	public void setRecommendeMail(String recommendeMail) {
		this.recommendeMail = recommendeMail;
	}

	/**
	 * 推荐时间
	 */
	public String getRecommendDateTime() {
		return recommendDateTime;
	}

	/**
	 * 推荐时间
	 */
	public void setRecommendDateTime(String recommendDateTime) {
		this.recommendDateTime = recommendDateTime;
	}

	/**
	 * 审核状态
	 */
	public String getRecommendState() {
		return recommendState;
	}

	/**
	 * 审核状态
	 */
	public void setRecommendState(String recommendState) {
		this.recommendState = recommendState;
	}

	/**
	 * 审核时间
	 */
	public String getExamineDateTime() {
		return examineDateTime;
	}

	/**
	 * 审核时间
	 */
	public void setExamineDateTime(String examineDateTime) {
		this.examineDateTime = examineDateTime;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	/**
	 * 推荐人类型
	 */
	public String getRecommendRoleId() {
		return recommendRoleId;
	}

	/**
	 * 推荐人类型
	 */
	public void setRecommendRoleId(String recommendRoleId) {
		this.recommendRoleId = recommendRoleId;
	}

	// @Override
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("recommendPhoneNo", recommendPhoneNo);
		json.put("recommendtrueName", recommendtrueName);
		json.put("recommendCertificateTypeId", recommendCertificateTypeId);
		json.put("recommendCertificateId", recommendCertificateId);
		json.put("recommendRoleId", recommendRoleId);
		json.put("recommendeMail", recommendeMail);
		return json;
	}

}
