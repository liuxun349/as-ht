/**
 * Z券
 */
package com.asht.model;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.asht.AsHtException;
import com.asht.http.AshtResponse;

public class ZCoupon extends AshtResponse {
	public String counponId = "";
	public String paycode = "";
	public String ZMoney = "";
	public String dtValidateTime = "";
	public String state = "";
	public String dtExchangeTime = "";
	public String dtPayTime = "";

	public ZCoupon() {

	}

	public ZCoupon(AshtResponse rs) {
		super(rs);
		ZCoupon zCoupon = JSON.parseObject(rs.result.toString(), ZCoupon.class);
		this.counponId = zCoupon.counponId;
		this.paycode = zCoupon.paycode;
		this.ZMoney = zCoupon.ZMoney;
		this.dtValidateTime = zCoupon.dtValidateTime;
		this.state = zCoupon.state;
		this.dtExchangeTime = zCoupon.dtExchangeTime;
		this.dtPayTime = zCoupon.dtPayTime;

	}

	public static List<ZCoupon> getZCoupons(AshtResponse rs)
			throws AsHtException {
		if (!rs.success) {
			throw new AsHtException(rs.message);
		} else if (rs.result == null) {
			return null;
		}
		return JSON.parseArray(rs.result.toString(), ZCoupon.class);
	}
}
