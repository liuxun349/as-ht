package com.asht.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asht.AsHtException;
import com.asht.model.Recommend;
import com.asht.model.UserInfo;

public class AccountService {
	private static final String NAMESPACE = "http://accountService.CXFWebservice.modules.www.ascs.com/";
	private static final String SERVICEURL = "http://115.28.48.85:8080/ascs/WS/AccountService?wsdl";
	private String method ;
	private HttpClient httpClient = new HttpClient(NAMESPACE,SERVICEURL);
	JSONObject json;
	/**
	 * 查询贡献值
	 * @param userInfo
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse searchContributions(UserInfo userInfo) throws AsHtException{
		method = "getContributions";
		json = new JSONObject();
		json.put("userPhoneNo", userInfo.getUserId());
		return get(method, json);
	}
	
	/**
	 * 查询Z币
	 * @param userInfo
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse getZGold(UserInfo userInfo) throws AsHtException{
		method = "getZGold";
		json = new JSONObject();
		json.put("userPhoneNo", userInfo.getUserId());
		return get(method, json);
	}
	
	/**
	 * 兑换Z券
	 * @param userInfo
	 * @param exchangeValue
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse exchangeZCoupon(UserInfo userInfo,int exchangeValue) throws AsHtException{
		method = "exchangeZCoupon";
		json = new JSONObject();
		json.put("userPhoneNo", userInfo.getUserId());
		json.put("willExchangeZValue", exchangeValue);
		return get(method, json);
	}
	
	/**
	 * 查看Z券信息
	 * @param userInfo
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse getZCouponsByOwner(UserInfo userInfo) throws AsHtException{
		method = "getZCouponsByOwner";
		json = new JSONObject();
		json.put("userPhoneNo", userInfo.getUserId());
		return get(method, json);
	}
	/**
	 * 添加意见反馈
	 * @param userInfo
	 * @param advice
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse addAdvice(UserInfo userInfo,String advice) throws AsHtException{
		method = "addAdvice";
		json = new JSONObject();
		json.put("userPhoneNo", userInfo.getUserId());
		json.put("advice", advice);
		return get(method, json);
	}
	/**
	 * 搜索反馈意见
	 * @param userInfo
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse searchAdvices(UserInfo userInfo) throws AsHtException{
		method = "searchAdvices";
		json = new JSONObject();
		json.put("userPhoneNo", userInfo.getUserId());
		return get(method, json);
	}
	/**
	 * 搜索消息
	 * @param userInfo
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse searchMessages(UserInfo userInfo) throws AsHtException{
		method = "searchMessages";
		json = new JSONObject();
		json.put("userPhoneNo", userInfo.getUserId());
		return get(method, json);
	}
	/**
	 * 推荐病人
	 * @param userInfo
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse recommendPatient(UserInfo userInfo,Recommend recommend) throws AsHtException{
		method = "recommendPatient";
		json = recommend.toJson();
		json.put("userPhoneNo", userInfo.getUserId());
		return get(method, json);
	}
	/**
	 * 获取所有自己的推荐信息
	 * @param userInfo
	 * @return
	 * @throws AsHtException 
	 */
	public AshtResponse getRecommendationsByPresenter(UserInfo userInfo) throws AsHtException{
		method = "getRecommendationsByPresenter";
		json = new JSONObject();
		json.put("userPhoneNo", userInfo.getUserId());
		return get(method,json);
	}
	private AshtResponse get(String method, JSONObject json) throws AsHtException {
		return httpClient.get(method, json.toJSONString());
	}
}
