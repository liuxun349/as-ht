package com.example.controller;

import java.util.List;

public interface ViewLinstener {
	/**
	 * 刷新
	 * 
	 * @param fag
	 *            是否从服务器更新
	 * @param isTouch
	 *            是否是滑动属性 true 下拉刷新 false 点击的更新按钮
	 */
	public void update(final boolean fag, final boolean isTouch);

	public void deleteSelectAll();

	public void gengduo();

	public void add(List<?> infos);
}
