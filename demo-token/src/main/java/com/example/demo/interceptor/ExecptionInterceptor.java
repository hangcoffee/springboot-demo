package com.example.demo.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.exception.CommonException;
import com.example.demo.util.PrintUtil;
import com.example.demo.util.ReturnResult;
import com.example.demo.util.ReturnResultUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: Coffee
 * @Date: 2019/4/6
 */
public class ExecptionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 允许跨域访问
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        // 有中文，需转码
        if (e != null) {
            PrintUtil printUtil = new PrintUtil(response, "text/html;charset=UTF-8");
            ReturnResult result = ReturnResultUtil.returnFailed(CommonException.SYS_EXCEPTION.getCode(), CommonException.SYS_EXCEPTION.getMessage());
            // 将异常发送到前端
            printUtil.print(JSONObject.toJSONString(result));
        }
    }
}
