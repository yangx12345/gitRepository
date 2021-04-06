package com.syxx.hr.common.result;


import lombok.Data;

@Data
public class Result<T> {
	//状态码
	private Integer code;
	//提示信息
	private String msg;
	//数据
	private T data;

	public Result() {

	}
	//自定义返回结果的构造方法
	public Result(Integer code, String msg,T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Result(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
