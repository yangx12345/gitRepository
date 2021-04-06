package com.syxx.hr.common.enums;

public enum WorkStateEnum {
	WORK("在职"),
	RESIGN("离职")
	;

	private String description;

	WorkStateEnum(String description) {
		this.description = description;
	}

	WorkStateEnum() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
