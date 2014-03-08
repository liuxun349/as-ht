/**
 * 病例组类 
 */
package com.asht.model;

import java.util.List;

import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.Table;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.asht.AsHtException;
import com.asht.http.AshtResponse;
import com.example.controller.AFinalController;

@Table(name = "record")
public class Record extends AshtResponse {

	@Id
	private int id;
	/**
	 * 病例组id
	 */
	private String medicalRecordGroupID = "";
	/**
	 * 病例组名称
	 */
	private String medicalRecordGroupName = "";
	/**
	 * 病例组状态
	 */
	private String state = "";
	/**
	 * 病例组内病例总数
	 */
	private int medicalRecordItemTotal;
	/**
	 * 更新时间
	 */
	private String updateTime = "";

	/**
	 * 是否选中了属性// 用于Adapter的选中状态（0选中，1没有选中）（数据库保存没实际意义）
	 */
	public int isClick = 1;

	private String img1;
	private String img2;
	private String img3;
	private String img4;
	private int isUpdate;// 0 没有更新 1更新

	// /**
	// * 得到当前病例组下面的所有病例
	// */
	// @Finder(valueColumn = "medicalRecordGroupID", targetColumn = "groupid")
	// public FinderLazyLoader<Resume> resume = null;// 获取病例信息

	public Record() {
	}

	public Record(AshtResponse rs) {
		super(rs);
		Record record = JSON.parseObject(((JSON) result).toJSONString(),
				Record.class);
		record(record);
	}

	public void record(Record record) {
		medicalRecordGroupID = record.medicalRecordGroupID;
		medicalRecordGroupName = record.medicalRecordGroupName;
		state = record.state;
		medicalRecordItemTotal = record.medicalRecordItemTotal;
	}

	public static List<Record> getRecords(AshtResponse ashtResponse)
			throws AsHtException {
		if (!ashtResponse.success) {
			throw new AsHtException(ashtResponse.message);
		} else if (ashtResponse.result == null) {
			return null;
		}
		return JSON.parseArray(
				((JSONArray) ashtResponse.result).toJSONString(), Record.class);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMedicalRecordGroupID() {
		return medicalRecordGroupID;
	}

	public void setMedicalRecordGroupID(String medicalRecordGroupID) {
		this.medicalRecordGroupID = medicalRecordGroupID;
	}

	public String getMedicalRecordGroupName() {
		return medicalRecordGroupName;
	}

	public void setMedicalRecordGroupName(String medicalRecordGroupName) {
		this.medicalRecordGroupName = medicalRecordGroupName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getMedicalRecordItemTotal() {
		return medicalRecordItemTotal;
	}

	public void setMedicalRecordItemTotal(int medicalRecordItemTotal) {
		this.medicalRecordItemTotal = medicalRecordItemTotal;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getImg3() {
		return img3;
	}

	public void setImg3(String img3) {
		this.img3 = img3;
	}

	public String getImg4() {
		return img4;
	}

	public void setImg4(String img4) {
		this.img4 = img4;
	}

	// @Override
	public JSONObject toJson() {
		return null;
	}

	// public List<Resume> getResumes(Context context) {
	// return AFinalController.getDB(context).findAll(Resume.class,
	// "imedicalrecordgroupid = " + this.medicalRecordGroupID);
	// }
	//
	// public List<Resume> getResumes(Context context, int size) {
	// return AFinalController.getDB(context).findAllByWhere(
	// Resume.class,
	// "imedicalrecordgroupid = " + this.medicalRecordGroupID
	// + " order by id desc limit " + size);
	// }
	//
	// public List<Resume> getResumesByState(Context context, int state) {
	// return AFinalController.getDB(context).findAllByWhere(
	// Resume.class,
	// "imedicalrecordgroupid = " + this.getMedicalRecordGroupID()
	// + " and state =" + state);
	// }
	//
	// public void deleteAll(Context context) {
	// AFinalController.getDB(context).deleteByWhere(Resume.class,
	// "imedicalrecordgroupid = " + this.medicalRecordGroupID);
	// }
	//
	// public void deleteAllByState(Context context, int state) {
	// AFinalController.getDB(context).deleteByWhere(
	// Resume.class,
	// "imedicalrecordgroupid = " + this.getMedicalRecordGroupID()
	// + " and state = " + state);
	// }
	//
	// public void deleteAllByNoState(Context context, int state) {
	// AFinalController.getDB(context).deleteByWhere(
	// Resume.class,
	// "imedicalrecordgroupid = " + this.getMedicalRecordGroupID()
	// + " and state <> " + state);
	// }

	public int getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o instanceof Record) {
			Record r = (Record) o;
			if (r.medicalRecordGroupID == this.medicalRecordGroupID) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}
}
