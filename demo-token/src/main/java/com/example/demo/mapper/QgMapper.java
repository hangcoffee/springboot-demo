package com.example.demo.mapper;

import com.example.demo.model.Qg;
import org.apache.ibatis.annotations.Param;

/**
 * @author: AYH
 * @create: 2019-04-10 19:43
 **/
public interface QgMapper {
    int saveQg(Qg qg);
    int queryQgCount(@Param("userId")int userId);
}
