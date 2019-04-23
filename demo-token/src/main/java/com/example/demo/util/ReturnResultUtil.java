package com.example.demo.util;

/**
 * @Auther: Coffee
 * @Date: 2019/4/6
 *
 * 统一返回信息
 */
public class ReturnResultUtil {
    /**
     * 成功，不带数据
     * @return
     */
    public static ReturnResult returnSuccess() {
        ReturnResult returnResult = new ReturnResult();
        returnResult.setCode(200);
        return returnResult;
    }

    /**
     * 成功，带数据
     * @param data
     * @return
     */
    public static ReturnResult returnSuccess(Object data) {
        ReturnResult returnResult = new ReturnResult();
        returnResult.setCode(200);
        returnResult.setData(data);
        return returnResult;
    }

    /**
     * 失败，返回错误信息
     * @param message
     * @return
     */
    public static ReturnResult returnFailed(Integer code, String message) {
        ReturnResult returnResult = new ReturnResult();
        returnResult.setCode(code);
        returnResult.setMessage(message);
        return returnResult;
    }
}
