package com.asht.model;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.asht.AsHtException;
import com.asht.http.AshtResponse;

public class Advice extends AshtResponse{
	/**
	 * 意见编号
	 */
	public String adviceId;
	/**
	 * 意见内容
	 */
	public String advice;
	/**
	 * 提出时间
	 */
	public String dtInputTime;
	/**
	 * 状态
	 */
	public String state;
	/**
	 * 处理时间
	 */
	public String dtCloseTime;
	/**
	 * 处理结果
	 */
	public String txtResult;
	public Advice() {
		// TODO Auto-generated constructor stub
	}
	public Advice(AshtResponse rs) {
		// TODO Auto-generated constructor stub
		super(rs);
		
	}
	public Advice(String advice) {
		// TODO Auto-generated constructor stub
		this.advice = advice;
	}
	
	public static List<Advice> getUserAdvices(AshtResponse rs ) throws AsHtException{
		List<Advice> list = null;
		if(!rs.success ){
			throw new AsHtException(rs.message);
		}
		if( rs.result != null){
			list = JSON.parseArray(rs.result.toString(), Advice.class);
		}
		return list;
	}
}
