package com.syxx.hr.common.result;

public enum ResultEnum {

  SUCCESS(1,"success"),
  NO_PERMISSION(403,"权限不足"),
  NO_AUTH(401,"请先登录"),
  NOT_FOUND(404, "资源未找到"),
  PARAMETER_ERROR(1001, "参数不合法"),
  ERROR(0,"error");


  private Integer code;

  private String message;

  ResultEnum()
  {

  }

  ResultEnum(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  ResultEnum(Integer code) {
    this.code = code;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
