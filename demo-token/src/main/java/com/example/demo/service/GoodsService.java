package com.example.demo.service;

import com.example.demo.model.Goods;

/**
 * @author: AYH
 * @create: 2019-04-10 17:42
 **/
public interface GoodsService {
    int saveGoods(Goods goods);

    int qgGoods(int count);

    Goods goodsInfo();
}
