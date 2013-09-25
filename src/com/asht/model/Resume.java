package com.asht.model;

import java.io.File;
import java.io.Serializable;
import java.util.Hashtable;

import org.json.JSONObject;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;


public class Resume {
	private static final long serialVersionUID = -6193310436318894856L;
	private String candidateName;
	private String resumeFileType;
	private String resume;
	private int imedicalRecordItemId;

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getResumeFileType() {
		return resumeFileType;
	}

	public void setResumeFileType(String resumeFileType) {
		this.resumeFileType = resumeFileType;
	}
	public void setRecordItemId(int id){
		this.imedicalRecordItemId = id;
	}

	public void setResume(String resume ) {
		this.resume = resume;  
	}
	public JSONObject toJson(){
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("name", candidateName);
			jsonObject.put("type", resumeFileType);
			jsonObject.put("image", resume);
			jsonObject.put("imedicalRecordItemId", imedicalRecordItemId);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jsonObject;
	}
}
