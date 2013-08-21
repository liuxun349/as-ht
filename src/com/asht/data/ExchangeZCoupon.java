package com.asht.data;

import org.json.JSONObject;

public class ExchangeZCoupon {
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
		JSONObject result = NetworkConnection.connection(mname, param);
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
		JSONObject result = NetworkConnection.connection(mname, param);
		return result;
	}

}
