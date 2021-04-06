package com.syxx.hr.common.enums;

public enum RegisterStatusEnum {

	REVIEWING("0","审核中"),
	REVIEWED("1","通过"),
	UN_REVIEW("2","拒绝"),
	;

	private String code;
	private String description;

	RegisterStatusEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}

	RegisterStatusEnum() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
