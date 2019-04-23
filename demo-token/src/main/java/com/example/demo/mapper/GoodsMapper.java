package com.example.demo.mapper;

import com.example.demo.model.Goods;

/**
 * @author: ayh
 * @create: 2019-04-10 17:32
 **/
public interface GoodsMapper {

    int saveGoods(Goods goods);

    int qgGoods(int count);

    Goods goodsInfo();
}
