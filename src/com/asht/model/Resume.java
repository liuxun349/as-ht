/**
 * 病例图片类
 */
package com.asht.model;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asht.http.AshtResponse;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;


public class Resume extends AshtResponse{
	private static final long serialVersionUID = -6193310436318894856L;
	/**
	 * 病例图片id
	 */
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
	private String istate;
	/**
	 * 病理图片地址（原图）
	 */
	private String imedicalrecorditemfilename;
	/**
	 * 缩略图地址
	 */
	private String minFileName;
	public Resume(){
		
	}
	public Resume(AshtResponse rs){
		super(rs);
		Resume resume = JSON.parseObject(result.toString(),Resume.class);
		this.imedicalrecordgroupid = resume.imedicalrecordgroupid;
		this.medicalRecordItemFile = resume.medicalRecordItemFile;
		this.istate = resume.istate;
		this.imedicalrecorditemfilename = resume.imedicalrecorditemfilename;
		this.minFileName = resume.minFileName;
	}
	public String getMedicalRecordImageFileToByte(){
		if( localRecordImageUrl == null)
			return "";
		else{
			 Bitmap bm = BitmapFactory.decodeFile(localRecordImageUrl);
             
             ByteArrayOutputStream baos = new ByteArrayOutputStream();
             bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
             byte[] b = baos.toByteArray();
             System.out.println(" size ... "+b.length);
             return Base64.encodeToString(b, Base64.DEFAULT);
		}
	}
	
	public static List<Resume> getResumes(AshtResponse rs){
		return JSON.parseArray(((JSON) rs.result).toJSONString(),Resume.class);
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
