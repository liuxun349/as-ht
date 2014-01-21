package com.asht.model;

import java.util.List;

import com.asht.AshtSettings;
import com.asht.utl.ApplictionManager;

public class User {
	private boolean isLogin = false;
	/**
	 * 贡献值
	 */
	private long contributions;
	/**
	 * 金币
	 */
	private long zGold;
	/**
	 * 推荐信息列表
	 */
	private List<Recommend> recommends;
	/**
	 * 消息列表
	 */
	private List<Message> messages;
	/**
	 * 意见列表
	 */
	private List<Advice> advices;
	/**
	 * 个人信息
	 */
	private UserInfo userInfo;

	public boolean exit() {
		AshtSettings.getInstance().setIsAutoLogin(false);
		ApplictionManager.getInstance().getUser().setIsLogin(false);
		try {
			AshtSettings.getInstance().setUserId("");
			AshtSettings.getInstance().setUserPwd("");
		} catch (Exception o) {
			return false;
		}
		return true;
	}

	public void setIsLogin(boolean isLogin) {
		// TODO Auto-generated method stub
		this.isLogin = isLogin;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public long getContributions() {
		return contributions;
	}

	public void setContributions(long contributions) {
		this.contributions = contributions;
	}

	public long getzGold() {
		return zGold;
	}

	public void setzGold(long zGold) {
		this.zGold = zGold;
	}

	public List<Recommend> getRecommends() {
		return recommends;
	}

	public void setRecommends(List<Recommend> recommends) {
		this.recommends = recommends;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<Advice> getAdvices() {
		return advices;
	}

	public void setAdvices(List<Advice> advices) {
		this.advices = advices;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

}
