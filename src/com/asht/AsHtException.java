package com.asht;

public class AsHtException extends Exception {
	public int statusCode = -1;

	public AsHtException(String msg) {
		super(msg);
	}

	public AsHtException(Exception cause) {
		super(cause);
	}

	public AsHtException(String msg, int statusCode) {
		super(msg);
		this.statusCode = statusCode;
	}
}
