package com.spaceemotion.denizenquestlogger.quest;

public class IllegalQuestException extends Exception {
	private static final long serialVersionUID = 1L;

	private String reason;

	public IllegalQuestException(String r) {
		this.reason = r;
	}

	public String getReason() {
		return this.reason;
	}
}
