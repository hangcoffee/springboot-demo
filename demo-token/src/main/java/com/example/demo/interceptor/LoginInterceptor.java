package com.example.demo.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.exception.CommonException;
import com.example.demo.exception.UserException;
import com.example.demo.util.PrintUtil;
import com.example.demo.util.ReturnResult;
import com.example.demo.util.ReturnResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: Coffee
 * @Date: 2019/4/6
 * 用户登陆拦截
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        // 获取请求参数
        String token = request.getParameter("token");
        ReturnResult returnResult = null;
        String userJson = null;
        PrintUtil printUtil = null;
        // 1.判断浏览器token是否存在
        if (StringUtils.isEmpty(token)) {
            returnResult = ReturnResultUtil.returnFailed(CommonException.USER_NO_LOGIN.getCode(), CommonException.USER_NO_LOGIN.getMessage());
        } else {
            userJson = (String) redisTemplate.opsForValue().get(token);
        }
        // 2.判断redis中token
        if (StringUtils.isEmpty(userJson)) {
            returnResult = ReturnResultUtil.returnFailed(CommonException.USER_NO_LOGIN.getCode(), CommonException.USER_NO_LOGIN.getMessage());
        }
        // 3.返回结果
        if (!StringUtils.isEmpty(returnResult)) {
            printUtil = new PrintUtil(response, "text/html;charset=UTF-8");
            printUtil.print(JSONObject.toJSONString(returnResult));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
