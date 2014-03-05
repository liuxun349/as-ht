package com.asht.utl;

public interface ConnCallback {
	public static final int CODE_START = 0, CODE_STOP = 1;

	public void connCode(int code, String result);
}
