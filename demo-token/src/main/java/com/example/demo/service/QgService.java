package com.example.demo.service;

import com.example.demo.model.Qg;

/**
 * @author: AYH
 * @create: 2019-04-10 19:50
 **/
public interface QgService {
    int saveQg(Qg qg);
    int queryQgCount(int userId);
}
