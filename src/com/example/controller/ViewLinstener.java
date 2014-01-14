package com.example.controller;

public interface ViewLinstener {
	/**
	 * 刷新
	 * @param fag 
	 * @param isTouch 是否是滑动属性 true 下拉刷新 false 点击的更新按钮
	 */
	public void update(final boolean fag, final boolean isTouch);
	public void deleteSelectAll();
	public void gengduo();
}
