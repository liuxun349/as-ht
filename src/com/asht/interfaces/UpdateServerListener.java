package com.asht.interfaces;

import java.util.List;

public interface UpdateServerListener {
	// fag： 0代表服务器获取的数据，1：代表本地数据
	void update(List<?> list, int tag);
};