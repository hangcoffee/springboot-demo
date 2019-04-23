package com.example.demo.exception;

/**
 * @Auther: Coffee
 * @Date: 2019/4/6
 */
public enum  UserException {
    PASSWORD_WRONG(1001, "密码错误")
    ;

    UserException(Integer code, String message) {
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
