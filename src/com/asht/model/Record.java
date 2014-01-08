/**
 * 病例组类 
 */
package com.asht.model;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.asht.AsHtException;
import com.asht.http.AshtResponse;
import com.lidroid.xutils.db.annotation.Finder;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.NotNull;
import com.lidroid.xutils.db.sqlite.FinderLazyLoader;

public class Record extends AshtResponse {

	/**
	 * 病例组id
	 */
	@Id(column = "id")
	@NoAutoIncrement
	@NotNull
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
	/**
	 * 更新时间
	 */
	public String updateTime;

	/**
	 * 是否选中了属性// 用于Adapter的选中状态（0选中，1没有选中）（数据库保存没实际意义）
	 */
	public int isClick = 1;

	/**
	 * 得到当前病例组下面的所有病例
	 */
	@Finder(valueColumn = "medicalRecordGroupID", targetColumn = "groupid")
	public FinderLazyLoader<Resume> resume = null;// 获取病例信息

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
	String str = ashtResponse.result+"";
		return JSON.parseArray(
				((JSONArray) ashtResponse.result).toJSONString(), Record.class);
	}

	// @Override
	public JSONObject toJson() {
		// TODO Auto-generated method stub
		return null;
	}

}
