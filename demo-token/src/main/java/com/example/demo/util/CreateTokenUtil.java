package com.example.demo.util;

import com.example.demo.model.User;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: Coffee
 * @Date: 2019/4/4
 */
public class CreateTokenUtil {

    public static String createToken(User user) {
        long millis = System.currentTimeMillis();
        String token = null;
        try {
            if (user != null) {
                token = user.getPhone() + user.getPwd() + millis;
                MD5Util.md5(token);
                return token;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
