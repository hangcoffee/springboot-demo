package com.example.demo.service.serviceImpl;

import com.example.demo.mapper.GoodsMapper;
import com.example.demo.model.Goods;
import com.example.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ayh
 * @create: 2019-04-10 17:43
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public int saveGoods(Goods goods) {
        return goodsMapper.saveGoods(goods);
    }

    @Override
    public Goods goodsInfo() {
        return goodsMapper.goodsInfo();
    }

    @Override
    public int qgGoods(int count) {
        return goodsMapper.qgGoods(count);
    }

}
