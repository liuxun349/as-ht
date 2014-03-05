package com.asht.interfaces;

import com.asht.model.UpdateState;

public interface UIHanleLintener {
	void addfinish(boolean fag);

	void deletefinish(boolean fag);

	/**
	 * 数据更新完成
	 * 
	 * @param isServer
	 *            数据是否从服务器获取
	 * @param fag
	 *            访问数据是否成功
	 * @param isTouch
	 *            是否是下拉刷新更新的数据
	 */
	void update(boolean isServer, UpdateState state, boolean isTouch);

	void gengduo(boolean isServer, UpdateState state, boolean isTouch);
}