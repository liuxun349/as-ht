package com.asht.model;

import com.alibaba.fastjson.JSON;
import com.asht.AsHtException;
import com.asht.http.AshtResponse;

public class AppInfo extends AshtResponse {
	/**
	 * 版本号
	 */
	public int version;
	/**
	 * 版本名称
	 */
	public String versionName;
	/**
	 * 版本描述
	 */
	public String versionDetail;
	/**
	 * 版本下载地址
	 */
	public String versionAdress;

	public AppInfo() {
		// TODO Auto-generated constructor stub
	}

	public AppInfo(AshtResponse rs) {
		super(rs);
	}

	public static AppInfo paresAppInfo(AshtResponse rs) throws AsHtException {
		if (rs.result == null) {
			throw new AsHtException("暂无信息", 10012);
		}
		return JSON.parseObject(rs.result.toString(), AppInfo.class);
	}
}
