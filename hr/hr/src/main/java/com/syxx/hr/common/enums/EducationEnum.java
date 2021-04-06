package com.syxx.hr.common.enums;

public enum EducationEnum {
	DOCTOR("博士"),
	MASTER("硕士"),
	UNDERGRADUATE("本科"),
	JUNIOR_COLLEGE("大专"),
	HIGH_SCHOOL("高中"),
	JUNIOR_HIGH_SCHOOL("初中"),
	PRIMARY_SCHOOL("小学"),
	OTHER("其他")
	;


	private String description;

	EducationEnum(String description) {
		this.description = description;
	}

	EducationEnum() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
