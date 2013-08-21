package com.asht.dao;

import org.json.JSONException;
import org.json.JSONObject;

public class UserExchangeZCouponDAO {
	/**
	 * 兑换Z券
	 * 
	 * @param UserID
	 * @param ZMoneyCount
	 * @param PayPassword
	 * @return
	 */
	public JSONObject exchangeZCoupon(String UserID, String ZMoneyCount,
			String PayPassword) {
		String mname = "ExchangeZCoupon";
		JSONObject param = new JSONObject();

		try {
			param.put("UserID", UserID);
			param.put("ZMoneyCount", ZMoneyCount);
			param.put("PayPassword", PayPassword);
		} catch (Exception e) {
		}
		JSONObject result = NetworkConnectionDAO.connection(mname, param);
		return result;
	}

	/**
	 * 获取Z券详细信息
	 * 
	 * @param ZCouponID
	 * @return
	 */
	public JSONObject getZCouponInfo(String ZCouponID) {
		String mname = "GetZCouponInfo";
		JSONObject param = new JSONObject();

		try {
			param.put("ZCouponID", ZCouponID);
		} catch (Exception e) {
		}
		JSONObject result = NetworkConnectionDAO.connection(mname, param);
		return result;
	}

	/**
	 * 搜索Z券
	 * 
	 * @return
	 */
	public JSONObject searchZCoupons(String Start, String Count, String Field,
			String Value, String Junction) {
		String name = "SearchZCoupons";

		JSONObject param = new JSONObject();
		JSONObject pager = new JSONObject();
		JSONObject searchConditions = new JSONObject();
		try {
			pager.put("Start", Start);
			pager.put("Count", Count);
			searchConditions.put("Field", Field);
			searchConditions.put("Value", Value);
			searchConditions.put("Junction", Junction);

			param.put("Pager", pager);
			param.put("SearchConditions", searchConditions);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject result = NetworkConnectionDAO.connection(name, param);
		return result;
	}

}
