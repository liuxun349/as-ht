/**
 * 病例图片类
 */
package com.asht.model;

import java.io.Serializable;
import java.util.List;

import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.Table;
import net.tsz.afinal.utils.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asht.AsHtException;
import com.asht.http.AshtResponse;

@Table(name = "resume")
public class Resume extends AshtResponse implements Serializable {
	public static final long serialVersionUID = -6193310436318894856L;
	/**
	 * 病例图片id
	 */
	@Id
	public int id;

	private int imedicalrecorditemid;

	private String localRecordImageUrlId = "";
	/**
	 * 病例组id
	 */
	private int imedicalrecordgroupid = 0;
	/**
	 * 上传病例图片位置
	 */
	private String localRecordImageUrl = "";
	/**
	 * 病例图片状态
	 */
	private String istate;
	/**
	 * 病理图片地址（原图）
	 */
	private String imedicalrecorditemfilename;
	/**
	 * 缩略图地址
	 */
	private String minFileName;

	/**
	 * 是否选中了属性// 用于Adapter的选中状态（0选中，1没有选中）（数据库保存没实际意义）
	 */
	public int isClick = 1;
	/**
	 * 该病例本地状态 1、已经删除但是服务器未响应 2、文件上传失败3、正常数据
	 */
	private int state = 3;

	// /**
	// * 当前病例属于的病例组
	// */
	// @Foreign(column = "groupid", foreign = "medicalRecordGroupID")
	// public Record record = null;

	public Resume() {

	}

	public Resume(AshtResponse rs) {
		super(rs);
		Resume resume = JSON.parseObject(result.toString(), Resume.class);
		this.imedicalrecordgroupid = resume.imedicalrecordgroupid;
		this.istate = resume.istate;
		this.imedicalrecorditemfilename = resume.imedicalrecorditemfilename;
		this.minFileName = resume.minFileName;
		state = 3;
	}

	public static List<Resume> getResumes(AshtResponse rs) throws AsHtException {
		if (!rs.success) {
			throw new AsHtException(rs.message);
		} else if (rs.result == null) {
			return null;
		}
		return JSON.parseArray(((JSON) rs.result).toJSONString(), Resume.class);
	}

	public String getLocalRecordImageUrl() {
		return localRecordImageUrl;
	}

	public void setLocalRecordImageUrl(String localRecordImageUrl) {
		this.localRecordImageUrl = localRecordImageUrl;
		this.localRecordImageUrlId = Utils.crc64Long(Utils.getBytes(System
				.currentTimeMillis() + localRecordImageUrl))
				+ "";
	}

	@Override
	public JSONObject toJson() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getImedicalrecorditemid() {
		return imedicalrecorditemid;
	}

	public void setImedicalrecorditemid(int imedicalrecorditemid) {
		this.imedicalrecorditemid = imedicalrecorditemid;
	}

	public int getImedicalrecordgroupid() {
		return imedicalrecordgroupid;
	}

	public void setImedicalrecordgroupid(int medicalRecordGroupID) {
		this.imedicalrecordgroupid = medicalRecordGroupID;
	}

	public String getIstate() {
		return istate;
	}

	public void setIstate(String istate) {
		this.istate = istate;
	}

	public String getImedicalrecorditemfilename() {
		return imedicalrecorditemfilename;
	}

	public void setImedicalrecorditemfilename(String imedicalrecorditemfilename) {
		this.imedicalrecorditemfilename = imedicalrecorditemfilename;
	}

	public String getMinFileName() {
		return minFileName;
	}

	public void setMinFileName(String minFileName) {
		this.minFileName = minFileName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocalRecordImageUrlId() {
		return localRecordImageUrlId;
	}

	public void setLocalRecordImageUrlId(String localRecordImageUrlId) {
		this.localRecordImageUrlId = localRecordImageUrlId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setAttribute(Resume r) {
		this.imedicalrecorditemid = r.imedicalrecorditemid;
		this.imedicalrecorditemfilename = r.imedicalrecorditemfilename;
		this.imedicalrecordgroupid = r.imedicalrecordgroupid;
		this.istate = r.istate;
		this.minFileName = r.minFileName;
		this.state = 3;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o instanceof Resume) {

			Resume r = (Resume) o;

			if (r.imedicalrecordgroupid != 0 && this.imedicalrecordgroupid != 0
					&& r.imedicalrecordgroupid == this.imedicalrecorditemid) {
				return true;
			} else if (r.localRecordImageUrl != null
					&& !r.localRecordImageUrl.equals("")
					&& r.localRecordImageUrl.equals(this.localRecordImageUrl)) {
				return true;
			}
			// if (r.imedicalrecorditemid == this.imedicalrecorditemid) {
			// return true;
			// } else if (r.localRecordImageUrl!=null) {
			// return true;
			// }
		}
		return false;

	}
}
