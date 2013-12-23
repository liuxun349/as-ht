package com.asht.model;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asht.http.AshtResponse;

public class Record extends AshtResponse {
	/**
	 * 病例组id
	 */
	public String medicalRecordGroupID;
	/**
	 * 病例组名称
	 */
	public String medicalRecordGroupName;
	/**
	 * 病例组状态
	 */
	public String state;
	/**
	 * 病例组内病例总数
	 */
	public int medicalRecordItemTotal;

	public Record() {
		// TODO Auto-generated constructor stub
	}

	public Record(AshtResponse rs) {
		Record record = JSON.parseObject(result.toJSONString(), Record.class);
		record(record);
	}

	public void record(Record record) {
		medicalRecordGroupID = record.medicalRecordGroupID;
		medicalRecordGroupName = record.medicalRecordGroupName;
		state = record.state;
		medicalRecordItemTotal = record.medicalRecordItemTotal;
	}

	public static List<Record> getRecords(AshtResponse ashtResponse) {
		List<Record> records;
		records = JSON.parseArray(ashtResponse.result.toString(), Record.class);
		return records;
	}
}