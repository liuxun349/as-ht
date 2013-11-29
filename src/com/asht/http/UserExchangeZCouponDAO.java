package com.asht.http;

import org.json.JSONException;
import org.json.JSONObject;

import com.asht.utl.ConnCallback;

public class UserExchangeZCouponDAO {
	private String URL_NAME = "";
	/**
	 * 兑换Z券
	 * 
	 * @param UserID
	 * @param ZMoneyCount
	 * @param PayPassword
	 * @return
	 */
	public void exchangeZCoupon(String UserID, String ZMoneyCount,
			String PayPassword, ConnCallback callback) {
		String name = "ExchangeZCoupon";
		JSONObject param = new JSONObject();

		try {
			param.put("UserID", UserID);
			param.put("ZMoneyCount", ZMoneyCount);
			param.put("PayPassword", PayPassword);
		} catch (Exception e) {
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

	/**
	 * 获取Z券详细信息
	 * 
	 * @param ZCouponID
	 * @return
	 */
	public void getZCouponInfo(String ZCouponID, ConnCallback callback) {
		String name = "GetZCouponInfo";
		JSONObject param = new JSONObject();

		try {
			param.put("ZCouponID", ZCouponID);
		} catch (Exception e) {
		}
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

	/**
	 * 搜索Z券
	 * 
	 * @return
	 */
	public void searchZCoupons(String Start, String Count, String Field,
			String Value, String Junction, ConnCallback callback) {
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
		new ConnServer(URL_NAME, name, callback).execute(param.toString());
	}

}
