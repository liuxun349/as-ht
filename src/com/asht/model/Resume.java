/**
 * 病例图片类
 */
package com.asht.model;

import java.io.ByteArrayOutputStream;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asht.AsHtException;
import com.asht.http.AshtResponse;
import com.lidroid.xutils.db.annotation.Foreign;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.NotNull;

public class Resume extends AshtResponse {
	private static final long serialVersionUID = -6193310436318894856L;
	/**
	 * 病例图片id
	 */
	@Id
	@NoAutoIncrement
	@NotNull
	private int imedicalrecorditemid;
	/**
	 * 病例组id
	 */
	private int imedicalrecordgroupid;
	/**
	 * 病理图片　
	 */
	private Bitmap medicalRecordItemFile;
	/**
	 * 上传病例图片位置
	 */
	private String localRecordImageUrl;
	/**
	 * 缩略图图片
	 */
	private Bitmap minRecordItemFile;
	/**
	 * 病例图片状态
	 */
	public String istate;
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
	 * 当前病例属于的病例组
	 */
	@Foreign(column = "groupid", foreign = "medicalRecordGroupID")
	public Record record = null;

	public Resume() {

	}

	public Resume(AshtResponse rs) {
		super(rs);
		Resume resume = JSON.parseObject(result.toString(), Resume.class);
		this.imedicalrecordgroupid = resume.imedicalrecordgroupid;
		this.medicalRecordItemFile = resume.medicalRecordItemFile;
		this.istate = resume.istate;
		this.imedicalrecorditemfilename = resume.imedicalrecorditemfilename;
		this.minFileName = resume.minFileName;
	}

	public String getMedicalRecordImageFileToByte() {
		if (localRecordImageUrl == null) {
			return "";
		} else {
			Bitmap bm = BitmapFactory.decodeFile(localRecordImageUrl);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
			byte[] b = baos.toByteArray();
			return Base64.encodeToString(b, Base64.DEFAULT);
		}
	}

	public static List<Resume> getResumes(AshtResponse rs) throws AsHtException {
		if (!rs.success) {
			throw new AsHtException(rs.message);
		} else if (rs.result == null) {
			return null;
		}
		return JSON.parseArray(((JSON) rs.result).toJSONString(), Resume.class);
	}

	public Bitmap getMedicalRecordItemFile() {
		return medicalRecordItemFile;
	}

	public String getLocalRecordImageUrl() {
		return localRecordImageUrl;
	}

	public void setLocalRecordImageUrl(String localRecordImageUrl) {
		this.localRecordImageUrl = localRecordImageUrl;
	}

	public void setMedicalRecordItemFile(Bitmap medicalRecordItemFile) {
		this.medicalRecordItemFile = medicalRecordItemFile;
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

	public void setImedicalrecordgroupid(int imedicalrecordgroupid) {
		this.imedicalrecordgroupid = imedicalrecordgroupid;
	}

	public Bitmap getMinRecordItemFile() {
		return minRecordItemFile;
	}

	public void setMinRecordItemFile(Bitmap minRecordItemFile) {
		this.minRecordItemFile = minRecordItemFile;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
