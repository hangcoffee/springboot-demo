package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.util.ReturnResult;

import java.util.List;

/**
 * @author: ayh
 * @create: 2019-04-03 16:06
 **/

public interface UserService {

    ReturnResult loginByAccount(String phone, String pwd) throws Exception;

    ReturnResult loginOut(String token) throws Exception;
}
