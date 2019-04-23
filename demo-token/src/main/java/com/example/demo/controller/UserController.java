package com.example.demo.controller;

import com.example.demo.exception.CommonException;
import com.example.demo.exception.UserException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.ReturnResult;
import com.example.demo.util.ReturnResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ayh
 * @create: 2019-04-03 16:21
 **/
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ReturnResult login(String phone, String pwd) throws Exception{
            return userService.loginByAccount(phone, pwd);
    }

    @GetMapping("/v/loginOut")
    public ReturnResult loginOut(String token) throws Exception{
        return userService.loginOut(token);
    }
}
