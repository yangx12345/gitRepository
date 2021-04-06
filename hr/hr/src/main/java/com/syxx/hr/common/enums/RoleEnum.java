package com.syxx.hr.common.enums;

public enum RoleEnum {
	ADMIN(0,"系统管理员"),
	HR(1,"人事"),
	EMPLOYEE(2,"普通员工")
	;

	private Integer code;
	private String description;

	RoleEnum(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	RoleEnum() {
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
