package com.asht.model;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asht.AsHtException;
import com.asht.http.AshtResponse;

public class Message {
	/**
	 * 消息编号
	 */
	public String imessageid;
	/**
	 * 消息标题
	 */
	public String txttitle;
	/**
	 * 消息内容
	 */
	public String txtmassage;
	/**
	 * 发送时间
	 */
	public String dtinputtime;

	public static List<Message> getMessages(AshtResponse rs)
			throws AsHtException {
		if (!rs.success) {
			throw new AsHtException(rs.message);
		} else if (rs.result == null) {
			return null;
		}
		return JSON.parseArray(rs.result.toString(), Message.class);
	}

	public String getTime() {
		JSONObject json = JSON.parseObject(dtinputtime);
		return json.getString("month") + json.getString("day");
	}
}
