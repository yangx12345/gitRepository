package com.syxx.hr.common.enums;

public enum MarriageEnum {

	MARRIED("已婚"),
	UN_MARRIED("未婚"),
	DIVORCED("离异")
	;

	private String description;

	MarriageEnum(String description) {
		this.description = description;
	}

	MarriageEnum() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
