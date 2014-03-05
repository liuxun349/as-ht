package com.asht.model;

public class UpdateState {

	public static final int UK_SERVER_OK = 1, UK_SERVER_NET_ERROR = 2,
			UK_DB_OK = 3, UK_CREATE = 0, UK_ERROR = 4, UK_NO_DATA = 5;

	private int action = UK_CREATE;

	private String log;

	public void setLog(String log) {
		this.log = log;
	}

	public String getLog() {
		return log;
	}

	public int getAction() {
		return action;
	}

	public UpdateState(int action) {
		this.action = action;
	}

}
