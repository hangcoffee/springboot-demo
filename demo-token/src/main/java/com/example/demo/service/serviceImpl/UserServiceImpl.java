package com.example.demo.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.constant.LoginConstant;
import com.example.demo.exception.CommonException;
import com.example.demo.exception.UserException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.CreateTokenUtil;
import com.example.demo.util.RedisUtil;
import com.example.demo.util.ReturnResult;
import com.example.demo.util.ReturnResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: ayh
 * @create: 2019-04-03 16:07
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 登录
     * @param phone
     * @param pwd
     * @return
     * @throws Exception
     */
    @Override
    public ReturnResult loginByAccount(String phone, String pwd) throws Exception{
        User user = null;
        ReturnResult returnResult = null;
        user = userMapper.loginByAccount(phone, pwd);
        if (user != null) {
            String token = LoginConstant.TOKEN_PRE + CreateTokenUtil.createToken(user);
            String oldToken = (String) redisUtil.get(user.getId()+"");
            // 若已存在则替换
            if (oldToken != null) {
                redisUtil.remove(user.getId()+ "");
                redisUtil.remove(oldToken);
            }
            redisUtil.set(token, JSON.toJSONString(user), LoginConstant.OUT_TIME);
            redisUtil.set(user.getId()+"", token, LoginConstant.OUT_TIME);

            Map<String, Object> map = new HashMap<String, Object>();
//            int a = 5 / 0;
            map.put("token", token);
            returnResult = ReturnResultUtil.returnSuccess(map);
        } else {
            returnResult = ReturnResultUtil.returnFailed(UserException.PASSWORD_WRONG.getCode(), UserException.PASSWORD_WRONG.getMessage());
        }
        return returnResult;
    }

    /**
     * 登出
     * @param token
     * @return
     */
    @Override
    public ReturnResult loginOut(String token) throws Exception{
        String userJson = (String) redisUtil.get(token);
        User user = JSONObject.parseObject(userJson, User.class);
        redisUtil.remove(token);
        redisUtil.remove(user.getId()+"");
        return ReturnResultUtil.returnSuccess();
    }

}
