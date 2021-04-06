package com.syxx.hr.common.enums;

public enum JobLevelEnum {

	POSITIVE("正高级"),
	DEPUTY_SENIOR("副高级"),
	INTERMEDIATE("中级"),
	PRIMARY("初级"),
	MEMBER_LEVEL("员级")
	;

	private String description;

	JobLevelEnum(String description) {
		this.description = description;
	}

	JobLevelEnum() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
