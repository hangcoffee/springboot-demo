package com.example.demo;

import com.example.demo.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Coffee
 * @Date: 2019/4/16
 */
public class Test03 {

    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setId(user.getId() == 0 ? 0  : 100);
        System.out.println(user.getId());

    }
}
