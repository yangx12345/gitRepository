package com.syxx.hr.common.exception;


import com.syxx.hr.common.result.ResultEnum;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException {

	private Integer code;

	private String msg;

	public BusinessException(Integer code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public BusinessException(String msg) {
		super(msg);
		this.code = ResultEnum.ERROR.getCode();
		this.msg = msg;
	}
}
