package com.example.demo.service.serviceImpl;

import com.example.demo.mapper.QgMapper;
import com.example.demo.model.Qg;
import com.example.demo.service.QgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ayh
 * @create: 2019-04-10 19:52
 **/
@Service
public class QgServiceImpl implements QgService {

    @Autowired
    private QgMapper qgMapper;

    @Override
    public int saveQg(Qg qg) {
        return qgMapper.saveQg(qg);
    }

    @Override
    public int queryQgCount(int userId) {
        return qgMapper.queryQgCount(userId);
    }
}
