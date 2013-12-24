package com.asht.model;

import java.io.File;
import java.io.Serializable;
import java.util.Hashtable;

import org.json.JSONObject;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import com.alibaba.fastjson.JSON;
import com.asht.http.AshtResponse;

import android.graphics.Bitmap;


public class Resume extends AshtResponse{
	private static final long serialVersionUID = -6193310436318894856L;
	/**
	 * 病例组id
	 */
	private String medicalRecordGroupID;
	/**
	 * 病理图片　
	 */
	private Bitmap medicalRecordItemFile;
	/**
	 * 病例图片状态
	 */
	private String state;
	/**
	 * 病理图片名称
	 */
	private int medicalRecordItemFileName;
	
	public Resume(){
		
	}
	public Resume(AshtResponse rs){
		super(rs);
		Resume resume = JSON.parseObject(result.toString(),Resume.class);
		this.medicalRecordGroupID = resume.medicalRecordGroupID;
		this.medicalRecordItemFile = resume.medicalRecordItemFile;
		this.state = resume.state;
		this.medicalRecordItemFileName = resume.medicalRecordItemFileName;
	}
	public String getMedicalRecordGroupID() {
		return medicalRecordGroupID;
	}
	public void setMedicalRecordGroupID(String medicalRecordGroupID) {
		this.medicalRecordGroupID = medicalRecordGroupID;
	}
	public Bitmap getMedicalRecordItemFile() {
		return medicalRecordItemFile;
	}
	public void setMedicalRecordItemFile(Bitmap medicalRecordItemFile) {
		this.medicalRecordItemFile = medicalRecordItemFile;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getMedicalRecordItemFileName() {
		return medicalRecordItemFileName;
	}
	public void setMedicalRecordItemFileName(int medicalRecordItemFileName) {
		this.medicalRecordItemFileName = medicalRecordItemFileName;
	}
	
	
	
	

}
