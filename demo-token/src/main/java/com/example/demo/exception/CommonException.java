package com.example.demo.exception;

/**
 * @Auther: Coffee
 * @Date: 2019/4/6
 * 通用异常
 */
public enum  CommonException {
    SYS_EXCEPTION(-1, "系统繁忙，请稍后重试"),
    USER_NO_LOGIN(1, "用户登录超时")
    ;

    CommonException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

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
