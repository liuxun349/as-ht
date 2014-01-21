package com.asht.model;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.asht.AsHtException;
import com.asht.http.AshtResponse;

public class Message {
	/**
	 * 消息编号
	 */
	public String messageID;
	/**
	 * 消息标题
	 */
	public String messageTitle;
	/**
	 * 消息内容
	 */
	public String message;
	/**
	 * 发送时间
	 */
	public String inputTime;
	
	public static List<Message> getMessages(AshtResponse rs) throws AsHtException{
		if(!rs.success){
			throw new AsHtException(rs.message);
		}else if( rs.result == null){
			return null;
		}
		return JSON.parseArray(rs.result.toString(), Message.class);
	}
}
