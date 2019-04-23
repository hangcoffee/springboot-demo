package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: ayh
 * @create: 2019-04-03 16:01
 **/
public interface UserMapper {
    User loginByAccount(@Param("phone")String phone, @Param("pwd")String pwd);
}
