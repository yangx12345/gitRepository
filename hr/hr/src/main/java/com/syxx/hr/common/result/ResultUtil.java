package com.syxx.hr.common.result;



public class ResultUtil {

	public static Result<Object> success(Object data){
       return new Result(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(),data);
	}

	public static Result success(String message){
       return new Result(ResultEnum.SUCCESS.getCode(),message);
	}

	public static Result success(String message,Object data){
		return new Result(ResultEnum.SUCCESS.getCode(),message,data);
	}

	public static Result error(){
       return new Result(ResultEnum.ERROR.getCode(),ResultEnum.ERROR.getMessage());
	}

	public static Result error(String error){
       return new Result(ResultEnum.ERROR.getCode(),error);
	}

	public static Result error(Integer code,String error){
       return new Result(code,error);
	}

	public static Result parameterError(){
        return new Result(ResultEnum.PARAMETER_ERROR.getCode(),ResultEnum.PARAMETER_ERROR.getMessage());
	}

	public static Result judgmentResult(Boolean flag)
	{
		if (flag == null || !flag)
		{
			return new Result(ResultEnum.ERROR.getCode(),ResultEnum.ERROR.getMessage());
		}
		return new Result(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage());
	}

}
